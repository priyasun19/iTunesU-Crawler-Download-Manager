package crawler;
import org.w3c.dom.*;
public class crawl implements Runnable{
	 /*Itunes U Universities and colleges link*/
	static final String  mainurl="http://deimos3.apple.com/WebObjects/Core.woa/Browse/itunesu.com.1555792676";
	static DAO db;
	static Thread child;
	static Object lock=new Object();
	String univname="";
	public crawl()
	{
		child=new Thread(this,"child");

		db=new DAO();
            
	}
	public void setMainTable()
	{
            System.out.println(Thread.currentThread().getName());
		parsing parse=new parsing(Thread.currentThread().getName());
		NodeList nl=parse.getUrls(mainurl,"");
		db.addUnivurl(nl);
        }
	public void setWaitingTable(String url,String univname)
	{
		boolean flag=false;
		flag=db.isCrawled(url);
		if(!flag)
		{
			parsing parse=new parsing(Thread.currentThread().getName());
			/*Retrieve all urls in the XML document*/
		    NodeList nl=parse.getUrls(url,univname);
		    
		    /*Place the urls in waiting queue*/
		    if(nl!=null){
		    	db.addToWaiting(nl);
		    }
		    /*Place the crawled url in completed queue*/
			db.addToCompleted(url);
		}
	}
	
	public void crawlUniv(String univname)
	{
		String tempurl="";
		while(db.waitingHasEle())
		{
			synchronized(lock)
			{
				while((tempurl=db.getTempurl()).equals("") && db.waitingHasEle())
				{
					try{
					lock.wait();}catch(Exception e){}
				}
			}
			if(!tempurl.equals(""))
			{
				try{
				setWaitingTable(tempurl,univname);}catch(Exception e){}
				db.removeTempurl(tempurl);
				synchronized (lock) {
					lock.notifyAll();
				}
			}		
		}
	}
	
	public static void main(String args[])
	{
		crawl c=new crawl();
		/*save all universities link in MAIN_TABLE*/
		c.setMainTable();
	}
	public void run()
	{
		/*crawl for each url in MAIN_TABLE*/
		if(Thread.currentThread()==child)
		{
		while(db.maintabHasEle())
		{
			String u=db.getUnivurl();
			univname=db.getUnivname(u);
			try{
				setWaitingTable(u,univname);
				ThreadGroup th=new ThreadGroup("sub");
				for(int i=1;i<=4;i++)
				{
					Thread t=new Thread(th,this,"c"+i);
					t.start();
				}
				Thread tcpy[]=new Thread[4];
				th.enumerate(tcpy);
				while(!tcpy[0].getState().equals(Thread.State.TERMINATED)||!tcpy[1].getState().equals(Thread.State.TERMINATED)||!tcpy[2].getState().equals(Thread.State.TERMINATED)||!tcpy[3].getState().equals(Thread.State.TERMINATED))
				{
				}
				db.removeUnivurl(u);
				db.clearWaitingTable();
				db.clearCompletedTable();
			}
			catch(Exception e)
			{
				
			}
		}
		}
		else
		{
			crawlUniv(univname);
		}
	}
}

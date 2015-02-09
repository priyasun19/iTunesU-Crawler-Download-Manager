

import java.sql.*;
import java.net.*;
import org.w3c.dom.*;

public class DAO
{
	Connection getConnection() throws SQLException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		Connection condb = DriverManager.getConnection("jdbc:mysql:///test", "root","");
		return condb;
	}
	
	void addUnivurl(NodeList nl)
	{
		int i=0;
		Connection con=null;
		Statement stat=null;
		try
		{
			con=getConnection();
			stat=con.createStatement(); 
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		/**
	      *Get the value of url attribute in all nodes
	      *itunes' urls may have different protocol
	      *Convert everything to http
	      *Save the urls in MAIN_TABLE; 
	    */
		while(i<nl.getLength())
		{
			if(!nl.item(i).getNodeName().equalsIgnoreCase("gotourl"))
			{
				NamedNodeMap nmap=nl.item(i).getAttributes();
				String univurl=nmap.getNamedItem("url").getNodeValue();
				String univname="";
				if(nmap.getNamedItem("draggingName")!=null)
				univname=nmap.getNamedItem("draggingName").getNodeValue();
				if(!univurl.startsWith("http"))
				{
					String temp[]=univurl.split("://");
					univurl=univurl.replaceFirst(temp[0],"http");
				}
				try
				{
					/*
					URL u =new URL(univurl);
					URLConnection uc=u.openConnection();
					uc.setRequestProperty("User-Agent","iTunes/9.1");
    				String temp[]=uc.getContentType().split("/");
    	          	if((temp[0].startsWith("xml") || temp[1].startsWith("xml")))
    	          	{
    	          	*/
						if(!univurl.endsWith(".png")||!univurl.endsWith(".jpg"))
						{
    	          		stat.executeUpdate("insert into MAIN_TABLE values('"+univurl+"','"+univname+"')");
    	          		if(crawl.child.getState()==Thread.State.NEW)
    	          		{
    	          			crawl.child.setPriority(8);
    	          			crawl.child.start();
    	          		}
						}
    	          	//}
				}
				catch(Exception se)
				{
					
				}
			}
			i++;
		}  //end of while
	  /*Close connection*/
		try{
			con.close();
		}
		catch(SQLException se){
			System.out.println(se); 
		}
	}

	 /**
	   *Check whether the MAIN_TABLE contains urls 
	 */
	boolean maintabHasEle()
	{
		int x=0;
		try
		{
			Connection con=getConnection();
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery("select count(*) from MAIN_TABLE");
			if(rs.next())
				x=rs.getInt(1);
			con.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		if(x>0)
			return true;
		else
			return false;
	}

	 /**
	     *Get the first url from MAIN_TABLE
	 */
	String getUnivurl()
	{
		String url="";
		try
		{
			Connection con=getConnection();
			Statement stat=con.createStatement(); 
			ResultSet rs=stat.executeQuery("select * from MAIN_TABLE limit 1");
			if(rs.next())
			{
				url=rs.getString(1);
			}
			con.close();
		}
		catch(SQLException se)
		{
			
		}
		return url;
	}
	
	/**
	 * Remove url traversed from WAITING_TABLE
	*/
	public void removeTempurl(String url)
	{
		Connection con=null;
		Statement stat=null;
		try
		{
			con=getConnection();
			stat=con.createStatement(); 
			stat.executeUpdate("delete from WAITING_TABLE where tempurl='"+url+"'");
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	 }
	 
	/**
	 * Remove url traversed from MAIN_TABLE
	*/
	public void removeUnivurl(String url)
	{
		Connection con=null;
		Statement stat=null;
		try
		{
			con=getConnection();
			stat=con.createStatement(); 
			stat.executeUpdate("delete from MAIN_TABLE where univurl='"+url+"'");
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	 /**
	     *Add urls to waiting queue
	 */
	void addToWaiting(NodeList nl)
	{
		String tempurl="";
		int i=0;
		Connection con=null;
		Statement stat=null;
		try
		{
			con=getConnection();
			stat=con.createStatement(); 
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	  /**
	      *Get the value of url attribute in all nodes
	      *itunes' urls may have different protocol
	      *Convert everything to http
	  */
		while(i<nl.getLength())
		{
			if(nl.item(i).getNodeName().equalsIgnoreCase("#text"))
			{
				tempurl=nl.item(i).getNodeValue();
			}
			else
			{
			NamedNodeMap nmap=nl.item(i).getAttributes();
			tempurl=nmap.getNamedItem("url").getNodeValue();
			}
			if(!tempurl.startsWith("http"))
			{
				String temp[]=tempurl.split("://");
				tempurl=tempurl.replaceFirst(temp[0],"http");
			}
	   /**
	     *main-url of university is not included
	   */
	 
			try{
				/*
				URL u =new URL(tempurl);
				URLConnection uc=u.openConnection();
				uc.setRequestProperty("User-Agent","iTunes/9.1");
				String temp[]=uc.getContentType().split("/");
	          	if((temp[0].startsWith("xml") || temp[1].startsWith("xml")))
	          	{
	          	*/
				if(!tempurl.endsWith(".png")||!tempurl.endsWith(".jpg"))
				{
	          		int x=stat.executeUpdate("insert into WAITING_TABLE(tempurl) values('"+tempurl+"')");
	          		synchronized (crawl.lock) {
	          			crawl.lock.notifyAll();
					}
				}
	          	//}
			}
			catch(Exception e){
				
			}
			i++;
		}
		try
		{
			con.close();
		}
		catch(SQLException e)
		{
		}
	}
	
	/**
	 * Add url traversed to CRAWLED_TABLE
	*/
    public void addToCompleted(String url)
    {
    	Connection con=null;
    	Statement stat=null;
    	try
    	{
    		con=getConnection();
    		stat=con.createStatement();
    		int x=stat.executeUpdate("insert into CRAWLED_TABLE values('"+url+"')");
    		con.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
    
    /**
	 * Check whether the url has been crawled
	*/
    boolean isCrawled(String url)
    {
    	boolean flag=false;
    	Connection con=null;
    	Statement stat=null;
    	try
    	{
    		con=getConnection();
    		stat=con.createStatement();
    		ResultSet rs=stat.executeQuery("select *from CRAWLED_TABLE where url='"+url+"'");
    		if(rs.next())
    			flag=true;
    		con.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return flag;
    }
    
    
	boolean waitingHasEle()
	{
		int x=0;
		boolean flag=false;
		try
		{
			Connection con=getConnection();
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery("select count(*) from WAITING_TABLE");
			if(rs.next())
			{
				x=rs.getInt(1);
			}
			if(x>0)
				flag=true;
			con.close();
		}
		catch(SQLException e)
		{
		}
		return flag;
	}
	  
	 /**
	     *Retrieve first url in waiting queue 
	 */
	synchronized String getTempurl() 
	{
		String url="";
		try
		{
			Connection con=getConnection();
			Statement stat=con.createStatement(); 
			ResultSet rs=stat.executeQuery("select *from WAITING_TABLE where flag='false' limit 1");
			if(rs.next())
			{
				url=rs.getString(1);
				stat.executeUpdate("update WAITING_TABLE set flag='true' where tempurl='"+url+"'");
			}
			con.close();
		}
		catch(SQLException e)
		{
			
		}
		return url;
	}
	 
	 
/*void putDB(String artist,String genre,String des,String play,String ext,String purl,int cc)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection condb = DriverManager.getConnection("jdbc:mysql:///test", "root","");
			Statement stat=condb.createStatement();
		    stat.executeUpdate("insert into tracks(atrist,genre,descr,plist,fextn,purl,sno) values('"+artist+"','"+genre+"','"+des+"','"+play+"','"+ext+"','"+purl+"','"+cc+"')");
			condb.close();
		}
		catch(Exception e){

		}
	}*/
	void putDB(String artist,String genre,String des,String play,String ext,String purl,String songName,String duration,String previewLength,String releaseDate,String dateModified,String longdes,String url1,String type,String sav,String univname,String rank,String popularity,String trackno,String discno,String explicit,String year)
	{
		try{
                    System.out.println("in db");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection condb = DriverManager.getConnection("jdbc:mysql:///test", "root","");
                       songName=songName.replace('\'', ' ');
                       des=des.replace('\'',' ');
                       artist=artist.replace('\'',' ');
                         genre=genre.replace('\'',' ');
                            play=play.replace('\'',' ');
                               univname=univname.replace('\'',' ');
                               longdes=longdes.replace('\'',' ');
			Statement stat=condb.createStatement();
                        java.util.Date d=new java.util.Date();
                        String d1=d.toString();
                     ResultSet rs=stat.executeQuery("select * from tracks where purl='"+purl+"'");
                     if(!rs.next())
                     {

		   // stat.executeUpdate("insert into tracks(atrist,genre,descr,plist,fextn,purl,univname) values('"+artist+"','"+genre+"','"+des+"','"+play+"','"+ext+"','"+purl+"','"+univname+"')");
		    stat.executeUpdate("insert into tracks(atrist,genre,descr,plist,fextn,purl,songname,duration,plength,releasedate,datemodified,longdes,url1,type,sav,univname,lastviewed,rank,popularity,trackno,discno,explicit,year) values('"+artist+"','"+genre+"','"+des+"','"+play+"','"+ext+"','"+purl+"','"+songName+"','"+duration+"','"+previewLength+"','"+releaseDate+"','"+dateModified+"','"+longdes+"','"+url1+"','"+type+"','"+sav+"','"+univname+"','"+d1+"','"+rank+"','"+popularity+"','"+trackno+"','"+discno+"','"+explicit+"','"+year+"')");
			System.out.println("insert into tracks(atrist,genre,descr,plist,fextn,purl,songname,duration,plength,releasedate,datemodified,longdes,url1,type,sav,univname,lastviewed,rank,popularity,trackno,discno,explicit,year) values('"+artist+"','"+genre+"','"+des+"','"+play+"','"+ext+"','"+purl+"','"+songName+"','"+duration+"','"+previewLength+"','"+releaseDate+"','"+dateModified+"','"+longdes+"','"+url1+"','"+type+"','"+sav+"','"+univname+"','"+d1+"','"+rank+"','"+popularity+"','"+trackno+"','"+discno+"','"+explicit+"','"+year+"')");
                    }
                        condb.close();
		}
		catch(Exception e){
			System.out.println(e);

		}
	}

	public void clearCompletedTable()
	{
		Connection con=null;
    	Statement stat=null;
    	try
    	{
    		con=getConnection();
    		stat=con.createStatement();
    		int x=stat.executeUpdate("delete from CRAWLED_TABLE");
    		con.close();
    	}
    	catch(Exception e)
    	{
    		 
    	}
	}
	public void clearWaitingTable()
	{
		Connection con=null;
    	Statement stat=null;
    	try
    	{
    		con=getConnection();
    		stat=con.createStatement();
    		int x=stat.executeUpdate("delete from WAITING_TABLE");
    		con.close();
    	}
    	catch(Exception e)
    	{
    		 
    	}
	}
	
	public String getUnivname(String url)
	{
		String univname="";
		try
		{
		Connection con=getConnection();
		Statement stat=con.createStatement(); 
		ResultSet rs=stat.executeQuery("select univname from MAIN_TABLE where univurl='"+url+"'");
		if(rs.next())
		{
			univname=rs.getString(1);
		}
		}
		catch(Exception e)
		{
			
		}
		return univname;
	}
}

package crawler;
import java.net.*;
import java.io.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class parsing {
	  XPath xpath; 
	  String XML_FILE_NAME="";
	  public parsing(String XML_FILE_NAME)
	  {
		  xpath = XPathFactory.newInstance().newXPath();
		  this.XML_FILE_NAME=XML_FILE_NAME;
	  }
	  
	  /**
	     *Retrieve the XML file using right user-agent
	  */
	  boolean getXmlFile(String url) 
	  {
		  FileOutputStream fos=null;
		  InputStreamReader isr=null;
		  OutputStreamWriter osw=null;
		  try{
	          	URL u=new URL(url);
	          	URLConnection con=u.openConnection();
	          	con.setRequestProperty("User-Agent", "iTunes/9.1");
	          	String temp[]=con.getContentType().split("/");
	          	if(!(temp[0].startsWith("xml") || temp[1].startsWith("xml")))
	          	{
	          		return false;
	          	}
	          	isr=new InputStreamReader(con.getInputStream(),"UTF-8");
	          	fos=new FileOutputStream(new File(XML_FILE_NAME),false);
	          	osw=new OutputStreamWriter(fos,"UTF-8");
	      }
	      catch(Exception e)
	      {
	    	  	
	      }
	      char[] buff=new char[1];
	      while(true)
	      { 
	    	  try{
	    		  if(isr.read(buff,0,1)!=-1)
	    		  {
	    			  osw.write(buff,0,1);
	    			  osw.flush();
	    		  }
	    		  else
	    			  break;
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  break;
	    	  }
	      }
	      return true;
	  }

	  /**
	    *Obtain all nodes which has url attribute 
	  */
	  NodeList getUrls(String url,String univname) 
	  {
		  NodeList nl=null;
		  if(getXmlFile(url))
	      {
			  String srch="//*[@url] | //key[.='url']/following-sibling::string/text()";
	          try
	          {
	        	  Object res=xpath.evaluate(srch,new InputSource(XML_FILE_NAME),XPathConstants.NODESET);
	        	  nl=(NodeList)res;
	          }
	          catch(Exception e){
System.out.println(e);
	          }
	          /**Find if the page has tracks
				 * If there are tracks save the URL
				*/
				saveTracks st=new saveTracks();
				st.parseDocument(univname,XML_FILE_NAME);
	          return nl;
	      }
		  else
		  {
			  return null;
		  }
	  }
}

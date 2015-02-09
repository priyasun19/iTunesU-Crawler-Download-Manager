

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import java.net.*;
import java.io.*;
import java.util.*;

public class saveTracks {
	//static int cc=1; 
	int k1;
	int temp=0;
	List urllist;
	Document dom;
	
	public saveTracks()
	{
		urllist=new ArrayList();
	}
	
	 /**
     *Parse the xml document to get the contents of TrackList tag
     */
	void parseDocument(String univname,String XML_FILE_NAME)
	{ 
		String k[]=new String[100];	
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	    	//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(XML_FILE_NAME);
		}
	    catch(ParserConfigurationException pce) {
	    	pce.printStackTrace();
		}
	    catch(SAXException se) {
	    	se.printStackTrace();
		}
	    catch(IOException ioe) {
	    	ioe.printStackTrace();
		}
		//get the root elememt
		Element docEle = dom.getDocumentElement();
	    NodeList nl2=docEle.getElementsByTagName("TrackList");
	    if(nl2.getLength()!=0)
	    {
	    	Element docele1=(Element)nl2.item(0);
			NodeList nl1 = docele1.getElementsByTagName("array");
	        Element docele2=(Element)nl1.item(0);
	        NodeList nl = docele2.getElementsByTagName("dict");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					Element el = (Element)nl.item(i);
					try{
                                            System.out.println("calling geturl");
						k=geturl(el,univname);

	                }
					catch(Exception e){}
					for(int j=0;j<k1;j++)
						urllist.add(k[j]);
				}
			}
	    }
	}

	/**
	  *Retrieve the url and meta information of the audio and video files from the xml document
	*/
/*	private String[] geturl(Element ele,String univname)throws Exception
	{
		DAO dobj=new DAO();
		String sn=new String();
		String val=new String(" ");
	    String artist=new String();
	    String genre=new String();
	    String des=new String();
	    String play=new String();
	    String ext=new String();
	    String purl=new String();
	    String val1=new String(" ");
	    k1=0;
	    Node no;
	    String temp=new String();
	    String s[]=new String[100];
	    NodeList nl=ele.getElementsByTagName("string");  
	    if(nl != null && nl.getLength()>0)
	    {
	   	for(int i=0;i<nl.getLength();i++)
		{
	   		Element el=(Element)nl.item(i);
	    	Element el1=(Element)el.getPreviousSibling();
	     	no=el1.getFirstChild();
	     	if(no!=null)
			{
	     		temp=el1.getFirstChild().getNodeValue();
			}
	     	if(temp.equalsIgnoreCase("songName")||temp.equalsIgnoreCase("genre") || temp.equalsIgnoreCase("artistName") || temp.equalsIgnoreCase("playlistName") || temp.equalsIgnoreCase("description"))
			{
	     		no=el.getFirstChild();
	    		if(no!=null)
				{
	    			val=el.getFirstChild().getNodeValue();
	                if(val!=null)
				    {
	                	if(temp.equalsIgnoreCase("genre"))
	       				{ 
	                		val1=val1.concat("Genre:"+val+"\n");
	                		genre=val;
	       				}
	         			else if(temp.equalsIgnoreCase("artistName"))
	                    { 
	         				val1=val1.concat("Artist name:"+val+"\n ");
	         				artist=val;
	                    }
	                    else if(temp.equalsIgnoreCase("playlistName"))
	        		    {
	                    	val1=val1.concat("Playlist name:"+val+"\n ");
	                    	play=val;
				        }
	         			else if(temp.equalsIgnoreCase("description"))
	          		    {
	         				val1=val1.concat("Description:"+val+"\n ");
	         				des=val;
	          		    }
				    }
	           
	      		}
			}
	        else if(temp.equalsIgnoreCase("previewURL"))
			{
	        	no=el.getFirstChild();
	     		if(no!=null)
				{ 
	     			val=el.getFirstChild().getNodeValue();
					purl=val;
	        		try
				    {
	        			URL url=new URL(val);
	   				   	URLConnection uc=url.openConnection();
	   				   	uc.setRequestProperty("User-Agent","itunes/10");
	    				String s2=uc.getContentType();
	      				if(s2.startsWith("audio")||s2.startsWith("video"))
	           			{		
	      					s[k1++]=val1;
	      					s[k1++]=val;
	      					if(s2.startsWith("audio"))
      							ext="audio";
	      					else if(s2.startsWith("video"))
	      						ext="video";
	      					dobj.putDB(artist,genre,des,play,ext,purl,sn,univname);
	           			}
					}
					catch(Exception e){}
	  			}
			}
		}
	    } 
	    return s;
	}*/
        private String[] geturl(Element ele,String univname)throws Exception
	{
		DAO dobj=new DAO();
	Object val=null;
	    String artist=new String();
	    String genre=new String();
	    String des=new String();
	    String play=new String();
	    String ext=new String();
	    String purl=new String();
	    String val1=new String(" ");
	    String songName=new String();
	  String duration=new String(),rank=new String(),explicit=new String(),year=new String(),discno=new String(),trackno=new String();
            Date md;
            int c,pl;
	    String previewLength=new String();
	    String releaseDate=new String();
	    String dateModified=new String();
	    String longdes=new String();
	    String url1=new String();
	    String type=new String();
	    String sav=new String();
            String popularity=new String();
	    k1=0;
	    Node no;
	    String temp=new String();
	    String s[]=new String[100];
	 
System.out.println("in geturl()");
 NodeList nl=ele.getElementsByTagName("string");
	    if(nl != null && nl.getLength()>0)
	    {
	   	for(int i=0;i<nl.getLength();i++)
		{
	   		Element el=(Element)nl.item(i);
	    	Element el1=(Element)el.getPreviousSibling();
	     	no=el1.getFirstChild();
	     	if(no!=null)
			{
	     		temp=el1.getFirstChild().getNodeValue();
			}
	     	if(temp.equalsIgnoreCase("genre") || temp.equalsIgnoreCase("artistName") || temp.equalsIgnoreCase("playlistName") || temp.equalsIgnoreCase("description") || temp.equalsIgnoreCase("fileExtension")||temp.equalsIgnoreCase("songName") || temp.equalsIgnoreCase("releaseDate") ||temp.equalsIgnoreCase("url") || temp.equalsIgnoreCase("longDescripton")||temp.equalsIgnoreCase("popularity")|| temp.equalsIgnoreCase("previewURL"))
			{
	     		no=el.getFirstChild();
	    		if(no!=null)
				{
	    			val=el.getFirstChild().getNodeValue();
	                if(val!=null)
				    {
	                	if(temp.equalsIgnoreCase("genre"))
	       				{
	                		val1=val1.concat("Genre:"+val+"\n");
	                		genre=val.toString();
	       				}
	         			else if(temp.equalsIgnoreCase("artistName"))
	                    {
	         				val1=val1.concat("Artist name:"+val+"\n ");
	         				artist=val.toString();
	                    }
	                    else if(temp.equalsIgnoreCase("playlistName"))
	        		    {
	                    	val1=val1.concat("Playlist name:"+val+"\n ");
	                    	play=val.toString();
				        }
	         			else if(temp.equalsIgnoreCase("description"))
	          		    {
	         				val1=val1.concat("Description:"+val+"\n ");
	         				des=val.toString();
	          		    }
	         			else if(temp.equalsIgnoreCase("fileExtension"))
	           		    {
	         				val1=val1.concat("File Extension:"+val+"\n ");
	         				ext=val.toString();
	           		    }
	         			else if(temp.equalsIgnoreCase("songName"))
	       				{
	                		val1=val1.concat("Song Name:"+val+"\n");
	                		songName=val.toString();
	       				}
	         			else if(temp.equalsIgnoreCase("releaseDate"))
	          		    {
	         				val1=val1.concat("Release Date:"+val+"\n ");
	         				releaseDate=val.toString();
                                }
	         			else if(temp.equalsIgnoreCase("longDescription"))
	          		    {
	         				val1=val1.concat("Long Description:"+val+"\n ");
	         				longdes=val.toString();
	          		    }
	         			else if(temp.equalsIgnoreCase("previewURL"))
	           		    {
	         				val1=val1.concat("previewURL:"+val+"\n ");
	         				purl=val.toString();
	           		    }
	         			else if(temp.equalsIgnoreCase("url"))
	           		    {
	         				val1=val1.concat("URL:"+val+"\n ");
	         				url1=val.toString();
	           		    }
 else if(temp.equalsIgnoreCase("popularity"))
                                {
     val1=val1.concat("Popularity: "+val+"\n");
     popularity=val.toString();
 }
				    }

	      		}
			}
	        /*else if(temp.equalsIgnoreCase("previewURL"))
			{
	        	no=el.getFirstChild();
	     		if(no!=null)
				{
	     			val=el.getFirstChild().getNodeValue();
					purl=val;
	        		try
				    {
	        			URL url=new URL(val);
	   				   	URLConnection uc=url.openConnection();
	   				   	uc.setRequestProperty("User-Agent","itunes/10");
	    				String s2=uc.getContentType();
	      				if(s2.startsWith("audio")||s2.startsWith("video"))
	           			{
	      					s[k1++]=val1;
	      					s[k1++]=val;
	      					dobj.putDB(artist,genre,des,play,ext,purl,cc);

	           			}
					}
					catch(Exception e){}
	  			}
			}*/
		}//for loop
	   	try
	    {
			URL url=new URL(purl);
			   	URLConnection uc=url.openConnection();
			   	uc.setRequestProperty("User-Agent","itunes/10");
			String s2=uc.getContentType();
				if(s2.startsWith("audio")||s2.startsWith("video"))
   			{
					s[k1++]=val1;
					s[k1++]=val.toString();
					if(s2.startsWith("audio"))
						sav="audio";
					else
						sav="video";
				//	System.out.println(val1);
						//putDB(artist,genre,des,play,ext,purl,songName,duration,previewLength,releaseDate,dateModified,longdes,url,type,sav,cc);
				//	System.out.println("added");

   			}
		}
		catch(Exception e){}
	    }//if loop
            nl=ele.getElementsByTagName("integer");
    //        for(int i=0;i<nl.getLength();i++)
   //           System.out.println(nl.item(i).getNodeValue());
    //       System.out.println("end");
	    if(nl != null && nl.getLength()>0)
	    {
	   	for(int i=0;i<nl.getLength();i++)
		{
	   		Element el=(Element)nl.item(i);
         //             System.out.println(el);
	    	Element el1=(Element)el.getPreviousSibling();
           //       System.out.println(el1);
	     	no=el1.getFirstChild();
         //          System.out.println(no);
	     	if(no!=null)
			{
	     		temp=el1.getFirstChild().getNodeValue();
             //              System.out.println(temp);
			}
	     	if(temp.equalsIgnoreCase("rank") || temp.equalsIgnoreCase("duration") || temp.equalsIgnoreCase("explicit") || temp.equalsIgnoreCase("previewLength") || temp.equalsIgnoreCase("trackNumber")||temp.equalsIgnoreCase("discNumber") || temp.equalsIgnoreCase("year"))
			{
	     		no=el.getFirstChild();
               //              System.out.println(no);
	    		if(no!=null)
				{

	    			val=el.getFirstChild().getNodeValue();
                   //              System.out.println("value : "+val);
                            boolean x=(val!=null);
                      //      System.out.println(x);
                         //   x=(val==null);
                         //   System.out.println(x);
	                if(x)
		         {
                //            System.out.println("hello "+temp.equalsIgnoreCase("rank"));
                              boolean y=temp.equalsIgnoreCase("duration");
                    //          System.out.println("y : "+y);
	                	if(temp.equalsIgnoreCase("rank"))
	       				{
	                		val1=val1.concat("Rank:"+val+"\n");
	                		rank=val.toString();
                    //                           System.out.println("rank:"+val+"\n ");
	       				}
	         			else if(y)
	                    {
	         		val1=val1.concat("Duration:"+val+"\n ");
                                duration=val.toString();
	         			//	duration=((Integer)val).intValue();
                    //                            System.out.println("Duration:"+play+"\n ");
	                    }
	                    else if(temp.equalsIgnoreCase("explicit"))
	        		    {
	                    	val1=val1.concat("Explicit:"+val+"\n ");
	                    explicit=val.toString();
                     //            System.out.println("Explicit:"+val+"\n ");
				        }
	         			else if(temp.equalsIgnoreCase("previewLength"))
	          		    {
	         				val1=val1.concat("previewLength:"+val+"\n ");
	         		previewLength=val.toString();
                      //                                 System.out.println("previewlength:"+val+"\n ");
	          		    }
	         			else if(temp.equalsIgnoreCase("trackNumber"))
	           		    {
	         			val1=val1.concat("trackNumber:"+val+"\n ");
	         				trackno=val.toString();
                        //                                   System.out.println("trackno:"+val+"\n ");
	           		    }
	         			else if(temp.equalsIgnoreCase("discNumber"))
	       				{
	                		val1=val1.concat("discNumber:"+val+"\n");
	                		discno=val.toString();
                              //                         System.out.println("discno:"+val+"\n ");
	       				}
	         			else if(temp.equalsIgnoreCase("year"))
	                    {
	         				val1=val1.concat("Year:"+val+"\n ");
                                        //        System.out.println("Duration : "+val);
	         				//duration=val;
                                              year=val.toString();
                                  //                     System.out.println("year:"+val+"\n ");
                                               //   System.out.println("Duration : "+c);
	                    }
	                    
				    }

	      		}
			}

		}//for loop
	   	try
	    {
System.out.println(val1);
				//	dobj.putDB(artist, genre, des, play, ext, purl, songName, duration, previewLength, releaseDate, dateModified, longdes, url1, type, sav,univname);
					//putDB(artist,genre,des,play,ext,purl,songName,duration,previewLength,releaseDate,dateModified,longdes,url,type,sav,cc);
					System.out.println("added");


		}
		catch(Exception e){
                System.out.println(e);
                }
	    }

            nl=ele.getElementsByTagName("date");
        //    for(int i=0;i<nl.getLength();i++)
          //    System.out.println(nl.item(i).getNodeValue());
          // System.out.println("end");
	    if(nl != null && nl.getLength()>0)
	    {
	   	for(int i=0;i<nl.getLength();i++)
		{
	   		Element el=(Element)nl.item(i);
             //         System.out.println(el);
	    	Element el1=(Element)el.getPreviousSibling();
           //       System.out.println(el1);
	     	no=el1.getFirstChild();
            //       System.out.println(no);
	     	if(no!=null)
			{
	     		temp=el1.getFirstChild().getNodeValue();
              //             System.out.println(temp);
			}
	     	if(temp.equalsIgnoreCase("dateModified"))
			{
	     		no=el.getFirstChild();
              //               System.out.println(no);
	    		if(no!=null)
				{

	    			val=el.getFirstChild().getNodeValue();
                   //              System.out.println("value : "+val);
                            boolean x=(val!=null);
                   //         System.out.println(x);
                         //   x=(val==null);
                         //   System.out.println(x);
	                if(x)
		         {
                          //  System.out.println("hello "+temp.equalsIgnoreCase("rank"));
                          //    boolean y=temp.equalsIgnoreCase("duration");
                            //  System.out.println("y : "+y);
	                	if(temp.equalsIgnoreCase("dateModified"))
	       				{
	                		val1=val1.concat("dateModified:"+val+"\n");
	                		dateModified=val.toString();
                                //               System.out.println("dateModified:"+val+"\n ");
	       				}
	         	


				    }

	      		}
			}

		}//for loop
	    }
            try
            {
            dobj.putDB(artist, genre, des, play, ext, purl, songName, duration, previewLength, releaseDate, dateModified, longdes, url1, type, sav,univname,rank,popularity,trackno,discno,explicit,year);
            }
            catch(Exception e)
            {
                System.out.println(e);

            }
	    return s;
	}
}

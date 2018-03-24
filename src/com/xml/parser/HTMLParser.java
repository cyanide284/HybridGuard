package com.xml.parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* 
Parse HTML for scripts and iframes and store the 3rd party includes in a csv

Author: 
Keshav Iyengar
UNCC CCI PhD Student
Student ID# 800989578
kiyengar@uncc.edu

Example use:
HTMLParser parser = new HTMLParser();
parser.parseFile("/usr/local/bin/com.amoedo.calculadora_2/AndroidManifest.xml", 4);

*/

public class HTMLParser {
	
	public HTMLParser() {
		
	}
	
	/*
	Specify the app's full file path and where the app name is located
	within that path
	*/
	public void parseHTMLFile(String fileName, int appNameIndex) throws IOException {
		
		try {
		
			File input = new File(fileName);
			Document doc = Jsoup.parse(input, "UTF-8");
			
			//get all the script and iframe tags in the document
			Elements scripts = doc.getElementsByTag("script");
			Elements iframes = doc.getElementsByTag("iframe");			
			
			//lists to store each tag's content
			ArrayList<String> scriptList = new ArrayList<String>();
			ArrayList<String> iframeList = new ArrayList<String>();
						
			//parse each of the script tags and store content in list
			for (Element script : scripts ) {   
				
				//if the script tag is 3rd party
				if( is3rdParty( script.attr("src") ) ) {
					
					//write it to the file
					//appendToCSV( "3rdPartyJS.csv", ( "," + script.attr("src") + "\n" ) );
					scriptList.add(script.attr("src"));
				
				}     
				
			}
			
			for (Element iframe : iframes) {

				if( is3rdParty( iframe.attr("src") ) ) {
					
					iframeList.add(iframe.attr("src"));
				
				}     
					
			}

			//for appending the app name to the csv
			String path[] = fileName.split("/");
			String appName = path[appNameIndex];
			
			appendToCSV("3rdPartyIncludes.csv", appName, iframeList, scriptList);
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	//check if a script or iframe src is 3rd party or authored by developer
	public boolean is3rdParty(String str) {
		
		String s = str.toLowerCase();
		
		if(s.contains("http")) return true;
		else if(s.contains("https")) return true;
		else if(s.contains("://www")) return true;
		else if(s.contains("www.")) return true;
		else if(s.contains(".com")) return true;
		else return false;
		 
	}
	
	public void appendToCSV(String csvName, String apName, ArrayList<String> iList, ArrayList<String> sList) throws IOException {
        
        FileWriter fw = new FileWriter(csvName, true);
        StringBuilder sb = new StringBuilder();
        
        sb.append(apName);
        
        int listSize = 0;
        
        //choose which list is larger
        if(iList.size() > sList.size()) listSize = iList.size();
        else listSize = sList.size();
        
        //use larger list size as loop counter
        //add each iframe and script to the csv
        for(int i = 0; i < listSize; i++) {
        
        	try {
        		sb.append("," + sList.get(i));
        	} catch(Exception e) {
        		sb.append(",");
        	}     	
        	
        	try {
        		sb.append("," + iList.get(i) + "\n");
        	} catch(Exception e) {
        		sb.append(",\n");
        	}
        	
        }
        
        fw.append(sb.toString());
        fw.close(); 
        
    }

}

package com.xml.parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Statement;

/*
Author: 
Keshav Iyengar
UNCC CCI PhD Student
Student ID# 800989578
kiyengar@uncc.edu

Example use:
XMLParser parser = new XMLParser();
parser.parseFile("/usr/local/bin/com.amoedo.calculadora_2/AndroidManifest.xml", 4);
*/

public class XMLParser {

    public XMLParser() {}

    //specify XML file path in parameter filePath
    //specify where in the file path the app name is in parameter appNameIndex
    public void parseFile(String filePath, int appNameIndex) {
        
        try {
            
            //File fXmlFile = new File("/usr/local/bin/com.amoedo.calculadora_2/AndroidManifest.xml");
            File fXmlFile = new File(filePath);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            String path[] = filePath.split("/");
            
            //the array index corresponds to where in the path the app name is
            String appName = path[appNameIndex];
            appendToCSV("permissions.csv", (appName));
            
            NodeList nList = doc.getElementsByTagName("uses-permission");
            
            /*
            DbConnection db = new DbConnection();
            Connection connection = DbConnection.getConnection();
            Statement s = DbConnection.getNewStatement();
            String result = "";
            */
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;
                            //result = eElement.getAttribute("android:name");
                      
                            appendToCSV( "permissions.csv", ( "," + eElement.getAttribute("android:name") + "\n" ) );
                            
                            //s.execute("INSERT INTO Results (Permissions_2) VALUES ('" + result + "')");
                            
                            /*
                            System.out.println("First Name : " + eElement.getElementsByTagName("uses-permission").item(0).getTextContent());
                            System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                            System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                            System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
                            */
                    }

            }
            //connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
    
    public void appendToCSV(String csvName, String toAppend) throws IOException {
        
        FileWriter fw = new FileWriter(csvName, true);
        StringBuilder sb = new StringBuilder();
        
        sb.append(toAppend);
        fw.append(sb.toString());
        fw.close(); 
        
    }
  
}
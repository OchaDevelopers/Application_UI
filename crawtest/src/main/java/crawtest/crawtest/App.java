package crawtest.crawtest;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class App {
    public static void main( String[] args ) throws IOException {
    	BufferedReader br = null;
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	factory.setNamespaceAware(true);
    	DocumentBuilder builder;
    	org.w3c.dom.Document doc = null;
    	FileWriter fw = new FileWriter("D:\\Program file\\result\\derby.txt");
    	FileWriter fw2 = new FileWriter("D:\\Program file\\result\\title.txt");
    	String title[] = new String[50];
    	String author[] = new String[50];
    	String id[] = new String[50];
    	String time[] = new String[50];
    	String path[] = new String[150];
    	String line1, open, fix, temp;
    	Date dateo, datef;
    	SimpleDateFormat orgin = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		SimpleDateFormat newone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	int indexc[] = new int[50];
    	int index = 0, pindex = 0;
    	indexc[0] = 0;
    	fw.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
    	fw.write("<bugrepository name=\"AspectJ\">\n");
    	BufferedReader brg = new BufferedReader(new FileReader("D:\\Program file\\result\\gitlog.txt"));
    	line1 = brg.readLine();
    	while(index < 50 && pindex < 100) {
    		if (line1 == null) break;
    		if (line1.contains("title") && index < 50) {
    			title[index] = line1.substring(6);
    		}
    		line1 = brg.readLine();
    		if (line1.contains("CommitID") && index < 50) {
    			id[index] = line1.substring(9);
    		}
    		line1 = brg.readLine();
    		if (line1.contains("author") && index < 50) {
    			author[index] = line1.substring(7);
    		}
    		line1 = brg.readLine();
    		if (line1.contains("time") && index < 50) {
    			time[index] = line1.substring(5);
    		}
    		while ((line1 = brg.readLine()) != null && line1.contains("path") && pindex < 150) {
				path[pindex] = line1.substring(5);
				pindex++;
    		}
    		indexc[index+1] = pindex;
    		index++;
		}
    	brg.close();
    	try {
	        String urlstr = "https://issues.apache.org/jira/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+DERBY+AND+issuetype+%3D+Bug+AND+status+%3D+Closed+AND+resolution+%3D+Fixed+AND+created+%3E%3D+-150w+ORDER+BY+priority+DESC%2C+updated+DESC&tempMax=1000";
	        URL url = new URL(urlstr);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        
	        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine())!= null) {
	        	result += line.trim();
	        }
	        InputSource is = new InputSource(new StringReader(result));
	        builder = factory.newDocumentBuilder();
	        doc = builder.parse(is);
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        XPath xpath = xpathFactory.newXPath();
	        XPathExpression expr = xpath.compile("//item/summary");
	        XPathExpression expr2 = xpath.compile("//item/description");
	        XPathExpression expr4 = xpath.compile("//item/created");
	        XPathExpression expr5 = xpath.compile("//item/resolved");
	        XPathExpression expr6 = xpath.compile("//comment");
	        XPathExpression expr7 = xpath.compile("//item/key");
	        
	        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodeList2 = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodeList4 = (NodeList) expr4.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodeList5 = (NodeList) expr5.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodeList6 = (NodeList) expr6.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodeList7 = (NodeList) expr7.evaluate(doc, XPathConstants.NODESET);
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        	NodeList child = nodeList.item(i).getChildNodes();
	        	NodeList child2 = nodeList2.item(i).getChildNodes();
	        	NodeList child4 = nodeList4.item(i).getChildNodes();
	        	NodeList child5 = nodeList5.item(i).getChildNodes();
	        	NodeList child6 = nodeList6.item(i).getChildNodes();
	        	NodeList child7 = nodeList7.item(i).getChildNodes();
	        	for(int j = 0; j < child.getLength(); j++) {
	        		Node node = child.item(j);
	        		Node node2 = child2.item(j);
	        		Node node4 = child4.item(j);
	        		Node node5 = child5.item(j);
	        		Node node6 = child6.item(j);
	        		Node node7 = child7.item(j);
	        		open = node4.getTextContent();
	        		fix = node4.getTextContent();
	        		dateo = orgin.parse(open);
	        		datef = orgin.parse(fix);
	        		fw.write("<bug id=\""+i+"\" opendate=\""+newone.format(dateo)+"\""
	        				+ " fixdate=\""+newone.format(datef)+"\">\n");
	        		fw.write("<buginformation>\n");
	        		fw.write("<summary>"+node.getTextContent()+"</summary>\n");
	        		temp = node2.getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
	        		fw.write("<description>\n"+temp+"\n</description>\n");
	        		//length로 comment잡아내기
	        		/*temp = node6.getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
	        		fw.write("<comments>\n</comments>\n");
	        		//fw.write("<comments>\ncomments : "+temp+"\n</comments>\n");*/
	        		fw.write("</buginformation>\n");
	        		index = 0;
	        		while (title[index] != null && !title[index].equals(node7.getTextContent())) {
	        			index++;
	        			
	        		}
	        		if(index < 50) {
		        		fw.write("<fixedCommits>\n");
		        		fw.write("<commit id=\""+id[index]+"\" author=\""+author[index]+"\" date=\""+time[index]+"\">\n");
		        		for(int k = indexc[index]; k < indexc[index+1]; k++) {
		        			fw.write("<file name=\""+path[k]+"\">\n");
		        			fw.write("</file>\n");
		        		}
		        		
		        		fw.write("</commit>\n");
		        		fw.write("</fixedCommits>\n");
	        		}
	        		fw.write("</bug>\n");
	        		
	        	}
	        }
	        
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	// title crawer
    	/*try {
	        String urlstr = "https://issues.apache.org/jira/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+DERBY+AND+issuetype+%3D+Bug+AND+status+%3D+Closed+AND+resolution+%3D+Fixed+AND+created+%3E%3D+-150w+ORDER+BY+priority+DESC%2C+updated+DESC&tempMax=1000";
	        URL url = new URL(urlstr);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        
	        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine())!= null) {
	        	result += line.trim();
	        }
	        InputSource is = new InputSource(new StringReader(result));
	        builder = factory.newDocumentBuilder();
	        doc = builder.parse(is);
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        XPath xpath = xpathFactory.newXPath();
	        XPathExpression exprt = xpath.compile("//item/key");
	        NodeList nodeListt = (NodeList) exprt.evaluate(doc, XPathConstants.NODESET);
	        for (int i = 0; i < nodeListt.getLength(); i++) {
	        	NodeList childt = nodeListt.item(i).getChildNodes();
	        	for(int j = 0; j < childt.getLength(); j++) {
	        		Node nodet = childt.item(j);
	        		fw2.write(nodet.getTextContent()+"\n");
	        	}
	        }
	        
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}*/
    	fw.close();
    	fw2.close();
    	System.out.println("\nend");
    }
}

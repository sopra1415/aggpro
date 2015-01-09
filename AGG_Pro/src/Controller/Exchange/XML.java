package Controller.Exchange;

import Data.Database.DatabaseConnector;
import Data.LiveClasses.Tournament;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author Jonathan Göggel
 *
 */

public class XML {
	private DatabaseConnector dc;
	private String rootXMLtag = "AGGPRO";

	public XML(DatabaseConnector dc) {
		super();
		this.dc = dc;
	}
	public void xml2table(String tablename,String xmlstr) throws ParserConfigurationException, SAXException, IOException, SQLException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xmlstr));
		Document doc = dBuilder.parse(is);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		NodeList nodeListTable = doc.getElementsByTagName(tablename);
		for (int i = 0; i < nodeListTable.getLength(); i++) {
			Node nodeTable = nodeListTable.item(i);
			Element elementTable = (Element) nodeTable;
			NodeList nodeListRecord = elementTable.getElementsByTagName("RECORD");
			for (int j = 0; j < nodeListRecord.getLength(); j++) {
				Node nodeRecord = nodeListRecord.item(j);
				Element elementRecord = (Element) nodeRecord;;
				NodeList nodeListFields = elementRecord.getChildNodes();

				//dc.insert("insert into Tournament(id,NaMe) values(5,'satz3');");
				ArrayList<String> fields = new ArrayList<>();
				ArrayList<String> values = new ArrayList<>();
				for (int k = 0; k < nodeListFields.getLength(); k++) {
					Node nodeField = nodeListFields.item(k);
					String text = nodeField.getTextContent();
					text = text.replaceAll("'", "''");
					if(text !=""){
						values.add("'"+text+"'");
						fields.add(nodeField.getNodeName());
					}
				}
				//INSERT
				if(fields.size()>0){
					String insert = "INSERT INTO " + tablename;
					insert +="("+join(fields,",")+")";
					insert += "VALUES("+join(values,",")+");";
					dc.insert(insert);
				}
			}

		}
	}
	public HashMap<String, String> xmlfile2database(DatabaseConnector dc,String xmluri) throws IOException, ParserConfigurationException, SAXException, SQLException{
		BufferedReader br = new BufferedReader(new FileReader(xmluri));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine(); 
		while(line != null){
			sb.append(line);
			sb.append("\n");
			line=br.readLine();
		}
		br.close();

		String xmlstr = sb.toString();
		for (String tablename : dc.getAllTables()) {
			xml2table(tablename, xmlstr);
		}
		return xmlGetComments(xmlstr);
	}
	public HashMap<String, String> xmlGetComments (String xmlStr) throws ParserConfigurationException, SAXException, IOException{
		HashMap<String, String> comments=new HashMap<>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xmlStr));
		Document doc = dBuilder.parse(is);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		NodeList nodeListComment = doc.getElementsByTagName("XMLCOMMENT");

		for (int i = 0; i < nodeListComment.getLength(); i++) {
				Node nodeKey = nodeListComment.item(i);
				Element elementKey = (Element) nodeKey;;
				String key = elementKey.getTextContent();
				String value = elementKey.getNodeName();
				comments.put(key, value);
		}
		//TODO test
		return comments;
	}
	public void xmlString2xmlfile(String xmluri,String xmlStr) throws TransformerException, ParserConfigurationException, SQLException, IOException{
		Writer writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmluri),"utf-8")) ;
		writer.write(xmlStr);
		writer.close();
	}


	public void appendTable2xml(Document doc,String tablename) throws SQLException{
		Element element = (Element) doc.getElementsByTagName(rootXMLtag).item(0);
		ResultSet rs = dc.select("SELECT * FROM " + tablename );
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		Element tabelle = doc.createElement(tablename);
		element.appendChild(tabelle);
		while (rs.next()) {
			Element record = doc.createElement("RECORD");
			tabelle.appendChild(record);
			for (int i = 1; i <= colCount; i++) {
				Element field = doc.createElement(rsmd.getColumnName(i));
				String string = rs.getString(i);
				if(string == null){
					string = "";	
				}
				field.appendChild(doc.createTextNode(string));
				record.appendChild(field);
			}
		}

	}
	public void appendComment(Document doc,HashMap<String, String> comments) throws SQLException{//TODO mehrere einträge als parameter
		Element element = (Element) doc.getElementsByTagName(rootXMLtag).item(0);
		Element comment = doc.createElement("XMLCOMMENT");
		element.appendChild(comment);
		for (String key : comments.keySet()) {
			String value = comments.get(key);
			Element field = doc.createElement(key);
			comment.appendChild(field);
			field.appendChild(doc.createTextNode(value));
		}

	}

	public Document xmlWriteStrStart() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element element = doc.createElement(rootXMLtag);
		doc.appendChild(element);
		return doc;		
	}
	public String xmlWriteStrEnd(Document doc) throws TransformerException{
		DOMSource domSource = new DOMSource(doc);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		//transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		//transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);

		return sw.toString();
	}

	public String database2xml() throws TransformerException, ParserConfigurationException, SQLException{
		Document doc = xmlWriteStrStart();
		for (String tablename: dc.getAllTables()) {
			appendTable2xml(doc, tablename);
		}
		return xmlWriteStrEnd(doc);
	}

	public String join(ArrayList<String> list,String separator){
		if(list.size() <1){
			return "";
		}
		String result = list.get(0);
		list.remove(0);
		for (String string : list) {
			result += separator + string;
		}
		return result;
	}
	public String joinInt(ArrayList<Integer> list,String separator){
		if(list.size() <1){
			return "";
		}
		String result = ""+list.get(0);
		list.remove(0);
		for (int element : list) {
			result += separator + element;
		}
		return result;
	}
	public void tournaments2xmlFile(ArrayList<Integer> tournamentIds,String comment,String pathAndfile) throws ParserConfigurationException, SQLException, TransformerException, IOException{
		System.out.println("todo xml2file and comment2xml");
		HashMap<String, String> comments= new HashMap<>();
		comments.put("COMMENT", comment);
		comments.put("TOURNAMENTS", joinInt(tournamentIds, ","));
		Document doc = xmlWriteStrStart();
		appendComment(doc,comments);
		for (String tablename: dc.getAllTables()) {
			appendTable2xml(doc, tablename);
		}
		xmlString2xmlfile(pathAndfile, xmlWriteStrEnd(doc));

	}
	public void xmlTournaments2db(String pathAndfile) throws ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException{
		DatabaseConnector dcnew = new DatabaseConnector("tmp");
		dcnew.createAllTables();
		HashMap<String , String> comment = xmlfile2database(dcnew, pathAndfile);
		String tournamentIds = comment.get("TOURNAMENTS");
		System.out.print("IDs der Turniere:");
		for (String s : tournamentIds.split(",")) {
			int id = Integer.parseInt(s);
		System.out.print(id+",");	
		}
		System.out.println();
		System.out.println("TODO daten von XMLfile übernehmen");
		//participants alle?
		//tournaments where id stimmt
		//participantlist →id von participant übersetzen where tournament stimmt
		//round,  → tournamentid überseztzen
		//encounter → tournamentiid & round übersetzen
		//points → encounter & participant umsetzen
		//modullist
		//modul
		//swissSystem
		//KoSystem

	}

}

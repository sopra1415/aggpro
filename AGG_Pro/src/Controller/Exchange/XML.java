package Controller.Exchange;

import Data.Database.DatabaseConnector;
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

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


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
						fields.add(nodeField.getNodeName());
						String text = nodeField.getTextContent();
						text = text.replaceAll("'", "''");
						values.add("'"+text+"'");
					}
					//INSERT
					String insert = "INSERT INTO " + tablename;
					insert +="("+join(fields,",")+")";
					insert += "VALUES("+join(values,",")+");";
					dc.insert(insert);
				}

			}
	}
	public void xmlfile2database(String xmluri) throws IOException, ParserConfigurationException, SAXException, SQLException{
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
	}
	public void database2xmlfile(String xmluri) throws TransformerException, ParserConfigurationException, SQLException, IOException{
		Writer writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmluri),"utf-8")) ;
		writer.write(database2xml());
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
				field.appendChild(doc.createTextNode(rs.getString(i)));
				record.appendChild(field);
			}
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

}

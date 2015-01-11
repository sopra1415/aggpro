
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
import java.util.Arrays;
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
	private enum cpRecord {update,newId,create};


	public XML(DatabaseConnector dc) {
		super();
		this.dc = dc;
	}
	public void xml2table(String tablename,String xmlstr,DatabaseConnector dc) throws ParserConfigurationException, SAXException, IOException, SQLException{
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
                                        //System.out.println(insert);
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
			xml2table(tablename, xmlstr,dc);
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
		NodeList nodeListXMLComment = doc.getElementsByTagName("XMLCOMMENT");
		Element elementXMLComment = (Element) nodeListXMLComment.item(0);
		NodeList nodeListComments = elementXMLComment.getChildNodes();
		for (int i = 0; i < nodeListComments.getLength(); i++) {
			Node nodeKey = nodeListComments.item(i);
			Element elementKey = (Element) nodeKey;;
			String key = elementKey.getNodeName();
			String value = elementKey.getTextContent();
			comments.put(key, value);
		}
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
		//TODO create new dir if dir not exist
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
		DatabaseConnector dcTmpXML = new DatabaseConnector("tmp");
		dcTmpXML.clearDatabase();
		dcTmpXML.createAllTables();
		HashMap<String , String> comment = xmlfile2database(dcTmpXML, pathAndfile);
		String tournamentIds = comment.get("TOURNAMENTS");
		for (int  id : dcTmpXML.getIdsFrom("EventProperties")) {
			ResultSet rs = dcTmpXML.select("SELECT key,value FROM EventProperties");
			while(rs.next()){
				String key =rs.getString(1);
				String value = rs.getString(2);
                                ResultSet rsEvent = dc.select(String.format("SELECT id FROM EventProperties WHERE key = '%s'",key));
                                if(rsEvent.next()){
                                    dc.update(String.format("UPDATE EventProperties SET value = '%s' WHERE key = '%s'",value,key));	
			        }else{
                                    dc.insert(String.format("INSERT INTO EventProperties (key,value) VALUES ('%s','%s')",key,value));
                                }
			}
		}
		HashMap<String, HashMap<Integer, String>> map = new HashMap<>();
		for (String table : dcTmpXML.getAllTables()){
			HashMap<Integer, String> mapIds = new HashMap<>();
			map.put(table, mapIds);
		}
		for (int id : dcTmpXML.getIdsFrom("Participant")) {
			cpDatabaseRecord(dcTmpXML, "Participant", id, dc, map);
		}
		if(tournamentIds == null){
			return;	
		}
		
		//fürs turnier wird das modul benötigt
		for (String s : tournamentIds.split(",")) {
			int idTournament = Integer.parseInt(s);
			for (int  idModul : dcTmpXML.getIdsFrom("Modul","id = " + idTournament)) { 
				cpDatabaseRecord(dcTmpXML, "Modul", idModul, dc, map);
				ResultSet rsModulList = dc.select("SELECT id FROM ModulList WHERE ModulId = "+idModul);
				while(rsModulList.next()){
					int idModulList = rsModulList.getInt(1);
					cpDatabaseRecord(dcTmpXML, "ModulList", idModulList, dc, map);
					ResultSet rs = dcTmpXML.select("SELECT TournamensystemtId FROM ModulList WHERE id = " + idModulList);
					rs.next();
					int modulListTournamenSystemtId = rs.getInt(1);
					for (int  id : dcTmpXML.getIdsFrom("swissSystem","SwissSystem = 1 AND Id = "+modulListTournamenSystemtId)) {
						cpDatabaseRecord(dcTmpXML, "swissSystem", id, dc, map);
					}
					for (int  id : dcTmpXML.getIdsFrom("KoSystem","SwissSystem = 0 AND Id = "+modulListTournamenSystemtId)) { 
						cpDatabaseRecord(dcTmpXML, "KOSystem", id, dc, map);

					}
				}

			}
		}
		
		
		for (String s : tournamentIds.split(",")) {
			int idTournament = Integer.parseInt(s);
			cpDatabaseRecord(dcTmpXML, "Tournament", idTournament, dc, map);
			for (int  idRound : dcTmpXML.getIdsFrom("Round","TournamentId = '"+idTournament+"'")) { 
				cpDatabaseRecord(dcTmpXML, "Round", idRound, dc, map);
			}
			for (int  id : dcTmpXML.getIdsFrom("ParticipantList")) {
				cpDatabaseRecord(dcTmpXML, "ParticipantList", id, dc, map);
			}
			for (int  idEncounter : dcTmpXML.getIdsFrom("Encounter","TournamentId = "+idTournament)) { 
				cpDatabaseRecord(dcTmpXML, "Encounter", idEncounter, dc, map);
				for (int  idPoints : dcTmpXML.getIdsFrom("Points","EncounterId = "+idEncounter)) {
					cpDatabaseRecord(dcTmpXML, "Points", idPoints, dc, map);
				}
			}
		}

	}
	private void cpDatabaseRecord(DatabaseConnector dcFrom,String table,int id,DatabaseConnector dcTo,HashMap<String, HashMap<Integer, String>> map) throws SQLException{
		table = table.toUpperCase();
		HashMap<String, ArrayList<String>> foreignKeys = new HashMap<>();
		foreignKeys.put("Tournament",new ArrayList(Arrays.asList(new String[] {"ModulId"})));
		foreignKeys.put("swissSystem",new ArrayList(Arrays.asList(new String[] { "TournamentSystemId"})));
		foreignKeys.put("KoSystem",new ArrayList(Arrays.asList(new String[] { "TournamentSystemId"})));
		foreignKeys.put("ModulList",new ArrayList(Arrays.asList(new String[] { "ModulId", "TournamentsystemId"})));
		foreignKeys.put("ParticipantList",new ArrayList(Arrays.asList(new String[] { "ParticipantId", "TournamentId"})));
		foreignKeys.put("Round",new ArrayList(Arrays.asList(new String[] { "TournamentId"})));
		foreignKeys.put("Encounter",new ArrayList(Arrays.asList(new String[] { "TournamentId", "RoundId"})));
		foreignKeys.put("Points",new ArrayList(Arrays.asList(new String[] { "ParticipantId", "EncounterId"})));

		//Select all data from table and row
		ResultSet rsAllFromTable = dcFrom.select("SELECT * FROM " + table + " WHERE id = " + id);
		if(!rsAllFromTable.next()){
			System.out.println("fehler kein ergebniss gefunden für "+table +" and id "+id);	
		return;
		}
		ResultSetMetaData metaData = rsAllFromTable.getMetaData();
		ArrayList<String> columnames = new ArrayList<>();
		//1. get spalten
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			columnames.add(metaData.getColumnName(i+1));
		}
		//2. foreach foreignKeys schneide "id" ab und hole id aus hashmap

		//set key and value for foreignkeys
		ArrayList<String> tableForeignKeys = foreignKeys.get(table);
		ArrayList<String> keys = new ArrayList<>();
		ArrayList<String> values = new ArrayList<>();
		if(tableForeignKeys !=null){
			for (String foreignkey : tableForeignKeys) {
			//schneide endung id ab	
				String key = foreignkey.replace("id$", "");
				key = key.toUpperCase();
				keys.add(foreignkey);
				values.add(map.get(table).get(key));
			}
			
		}
	//add colums and values
		for (int i = 0; i <columnames.size() ; i++) {
			String name = columnames.get(i);
			boolean skip =false;
			if(name.equalsIgnoreCase("Id")){
				skip=true;	
			}
			if(tableForeignKeys != null){

				for (String foreignKey : tableForeignKeys) {//check if name is foreignkey or id
					if(name.equalsIgnoreCase(foreignKey)){ 
						skip=true;
						//wenn key = table →rm  aus spaltenliste
						//columnames.remove(name);
					}
				}
			}
			if(!skip){//add colum if not foreignkey or id
				String value = rsAllFromTable.getString(i+1);
				if(value != null){
					keys.add(name);
					values.add(value);
				}
			}
		}
		boolean insert =true;
		if(table.equalsIgnoreCase("Participant")){
			ResultSet rsParticipantOld = dcFrom.select("SELECT StartNumber FROM Participant WHERE id = "+id);
			rsParticipantOld.next();
			String startNumberOld = rsParticipantOld.getString(1);
			ResultSet rsParticipant = dcTo.select("SELECT id FROM Participant WHERE StartNumber =  '" + startNumberOld+"'");
			if(rsParticipant.next()){
				int participantIdNew = rsParticipant.getInt(1);
				insert=false;//update Participant
                                
				for (int i = 0; i < keys.size(); i++) {
                                   dcTo.update("Participant", keys.get(i), values.get(i),participantIdNew);
				}
				map.get(table).put(id, participantIdNew+"");
				
			}
		}
		if(insert){
			String insertKeyString = join(keys, ",") ;
			String insertValueString = join(values,"','");
			int returnid = dcTo.insert(String.format("INSERT INTO %s (%s) VALUES ('%s')", table,insertKeyString,insertValueString));
			map.get(table).put(id, returnid+"");
		}
	}

}

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
/**
 * Class to handle every xml operation
 *
 * @author jojo
 */
public class XML {

    private DatabaseConnector dc;
    private String rootXMLtag = "AGGPRO";

    public XML(DatabaseConnector dc) {
        this.dc = dc;
    }

    /**
     * parses a xml string and insert it into the dc the primary keys(ids) have
     * of be unic and shouldn't be in already in the dc
     *
     * @param tablename
     * @param xmlstr
     * @param dc
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws SQLException
     */
    public void xml2table(String tablename, String xmlstr, DatabaseConnector dc) throws ParserConfigurationException, SAXException, IOException, SQLException {
        //create new xmlparser objekts	
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlstr));
        Document doc = dBuilder.parse(is);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        //get the tablename tag
        NodeList nodeListTable = doc.getElementsByTagName(tablename);
        for (int i = 0; i < nodeListTable.getLength(); i++) {//for each record
            Node nodeTable = nodeListTable.item(i);
            Element elementTable = (Element) nodeTable;
            //get the record tag
            NodeList nodeListRecord = elementTable.getElementsByTagName("RECORD");
            for (int j = 0; j < nodeListRecord.getLength(); j++) {
                Node nodeRecord = nodeListRecord.item(j);
                Element elementRecord = (Element) nodeRecord;;
                NodeList nodeListFields = elementRecord.getChildNodes();

                ArrayList<String> fields = new ArrayList<>();
                ArrayList<String> values = new ArrayList<>();
                //for each entry in the record
                for (int k = 0; k < nodeListFields.getLength(); k++) {
                    Node nodeField = nodeListFields.item(k);
                    String text = nodeField.getTextContent();
                    text = text.replaceAll("'", "''");
                    //replace ' with '' to insert in the database
                    if (text != "") {
                        //add field and value if the value is not empty
                        values.add("'" + text + "'");
                        fields.add(nodeField.getNodeName());
                    }
                }
                //INSERT if fields exists
                if (fields.size() > 0) {
                    String insert = "INSERT INTO " + tablename;
                    insert += "(" + join(fields, ",") + ")";
                    insert += "VALUES(" + join(values, ",") + ");";
                    dc.insert(insert);
                }
            }

        }
    }

    /**
     * insert xmlfile into a database
     *
     * @param dc
     * @param xmluri
     * @return the comments block of the xmlfile
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws SQLException
     */
    public HashMap<String, String> xmlfile2database(DatabaseConnector dc, String xmluri) throws IOException, ParserConfigurationException, SAXException, SQLException {
        //read the file
        BufferedReader br = new BufferedReader(new FileReader(xmluri));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();

        String xmlstr = sb.toString();
        //load all tables in the database
        for (String tablename : dc.getAllTables()) {
            xml2table(tablename, xmlstr, dc);
        }
        //return the comments
        return xmlGetComments(xmlstr);
    }

    /**
     * get the comments from a xmlfile
     *
     * @param xmlStr
     * @return comment
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public HashMap<String, String> xmlGetComments(String xmlStr) throws ParserConfigurationException, SAXException, IOException {
        HashMap<String, String> comments = new HashMap<>();//return hash for the comments
        //create the xml parser
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlStr));
        Document doc = dBuilder.parse(is);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();
        //get the XMLCOMMENT block
        NodeList nodeListXMLComment = doc.getElementsByTagName("XMLCOMMENT");
        Element elementXMLComment = (Element) nodeListXMLComment.item(0);
        NodeList nodeListComments = elementXMLComment.getChildNodes();
        for (int i = 0; i < nodeListComments.getLength(); i++) {
            //put each comment entry in the hash
            Node nodeKey = nodeListComments.item(i);
            Element elementKey = (Element) nodeKey;;
            String key = elementKey.getNodeName();
            String value = elementKey.getTextContent();
            comments.put(key, value);
        }
        return comments;
    }

    /**
     * Write the xmlString into a file
     *
     * @param xmluri
     * @param xmlStr
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SQLException
     * @throws IOException
     */
    public void xmlString2xmlfile(String xmluri, String xmlStr) throws TransformerException, ParserConfigurationException, SQLException, IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmluri), "utf-8"));
        writer.write(xmlStr);
        writer.close();
    }

    /**
     * append a table from the database atribute to the document
     *
     * @param doc
     * @param tablename
     * @throws SQLException
     */
    public void appendTable2xml(Document doc, String tablename) throws SQLException {
        //get all data from the table
        ResultSet rs = dc.select("SELECT * FROM " + tablename);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Element tabelle = doc.createElement(tablename);
        //get the roottag of the xml document to append 
        Element element = (Element) doc.getElementsByTagName(rootXMLtag).item(0);
        element.appendChild(tabelle);
        while (rs.next()) {
            //add each record
            Element record = doc.createElement("RECORD");
            tabelle.appendChild(record);
            for (int i = 1; i <= colCount; i++) {
                Element field = doc.createElement(rsmd.getColumnName(i));
                String string = rs.getString(i);
                if (string == null) {//avoid null objects
                    string = "";
                }
                field.appendChild(doc.createTextNode(string));
                record.appendChild(field);
            }
        }

    }

    /**
     * append comments to the document
     *
     * @param doc
     * @param comments
     * @throws SQLException
     */
    public void appendComment(Document doc, HashMap<String, String> comments) throws SQLException {
        Element element = (Element) doc.getElementsByTagName(rootXMLtag).item(0);
        Element comment = doc.createElement("XMLCOMMENT");//get the xmlcommenttag
        element.appendChild(comment);
        //append each key value pair to the document
        for (String key : comments.keySet()) {
            String value = comments.get(key);
            Element field = doc.createElement(key);
            comment.appendChild(field);
            field.appendChild(doc.createTextNode(value));
        }

    }

    /**
     * Create a doxument for the xml file
     *
     * @return xml document
     * @throws ParserConfigurationException
     */
    public Document xmlWriteStrStart() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        //set the root tag
        Element element = doc.createElement(rootXMLtag);
        doc.appendChild(element);
        return doc;
    }

    /**
     * finish the xml document
     *
     * @param doc
     * @return xml
     * @throws TransformerException
     */
    public String xmlWriteStrEnd(Document doc) throws TransformerException {
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

    /**
     * write the whole database in a xml String
     *
     * @return xml String
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SQLException
     */
    public String database2xml() throws TransformerException, ParserConfigurationException, SQLException {
        Document doc = xmlWriteStrStart();
        for (String tablename : dc.getAllTables()) {
            appendTable2xml(doc, tablename);
        }
        return xmlWriteStrEnd(doc);
    }

    /**
     * join a arraylist of strings with the seperator to a string
     *
     * @param list
     * @param separator
     * @return
     */
    public String join(ArrayList<String> list, String separator) {
        if (list.size() < 1) {
            return "";
        }
        String result = list.remove(0);
        for (String string : list) {
            result += separator + string;
        }
        return result;
    }

    /**
     * join a arraylist of integers with the sperator to a string
     *
     * @param list
     * @param separator
     * @return
     */
    public String joinInt(ArrayList<Integer> list, String separator) {
        if (list.size() < 1) {
            return "";
        }
        String result = "" + list.get(0);
        list.remove(0);
        for (int element : list) {
            result += separator + element;
        }
        return result;
    }

    /**
     * write the database to a xmlfile and set comments which tournaments have
     * to be imported
     *
     * @param tournamentIds
     * @param comment
     * @param pathAndfile
     * @throws ParserConfigurationException
     * @throws SQLException
     * @throws TransformerException
     * @throws IOException
     */
    public void tournaments2xmlFile(ArrayList<Integer> tournamentIds, String comment, String pathAndfile) throws ParserConfigurationException, SQLException, TransformerException, IOException {

        HashMap<String, String> comments = new HashMap<>();
        comments.put("COMMENT", comment);
        comments.put("TOURNAMENTS", joinInt(tournamentIds, ","));
        Document doc = xmlWriteStrStart();
        appendComment(doc, comments);
        for (String tablename : dc.getAllTables()) {
            appendTable2xml(doc, tablename);
        }
        xmlString2xmlfile(pathAndfile, xmlWriteStrEnd(doc));

    }

    /**
     * merge all participants update the event and import all selected
     * tournaments(comment from the xmlfile) and their dependencies
     *
     * @param pathAndfile
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void xmlTournaments2db(String pathAndfile) throws ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException {
        //load all data from the xml file to a temp database
        DatabaseConnector dcTmpXML = new DatabaseConnector("tmp");
        dcTmpXML.clearDatabase();
        dcTmpXML.createAllTables();
        HashMap<String, String> comment = xmlfile2database(dcTmpXML, pathAndfile);//get xml comments 
        String tournamentIds = comment.get("TOURNAMENTS");
        //Update all Event Properties
        ResultSet rsEventPropertiesOld = dcTmpXML.select("SELECT key,value FROM EventProperties");
        while (rsEventPropertiesOld.next()) {
            String key = rsEventPropertiesOld.getString(1);
            String value = rsEventPropertiesOld.getString(2);
            ResultSet rsEventId = dc.select(String.format("SELECT id FROM EventProperties WHERE key = '%s'", key));
            if (rsEventId.next()) {
                dc.update(String.format("UPDATE EventProperties SET value = '%s' WHERE key = '%s'", value, key));
            } else {
                dc.insert(String.format("INSERT INTO EventProperties (key,value) VALUES ('%s','%s')", key, value));
            }
        }
        //Create a hashmap foreach table to map the foreignkeys to new ones
        HashMap<String, HashMap<Integer, String>> map = new HashMap<>();
        for (String table : dcTmpXML.getAllTables()) {
            HashMap<Integer, String> mapIds = new HashMap<>();
            map.put(table, mapIds);
        }
        //Update/Insert all Participants
        for (int id : dcTmpXML.getIdsFrom("Participant")) {
            cpDatabaseRecord(dcTmpXML, "Participant", id, dc, map);
        }
        //return if there is no tournament  to import
        if (tournamentIds == null) {
            return;
        }

        //insert the modul with dependencies (ModulList swissSystem and KOSystem)
        for (String s : tournamentIds.split(",")) {
            int idTournament = Integer.parseInt(s);
            for (int idModul : dcTmpXML.getIdsFrom("Modul", "id = " + idTournament)) {
                cpDatabaseRecord(dcTmpXML, "Modul", idModul, dc, map);

                ResultSet rsSwiss = dc.select(String.format("SELECT swissSystem.id FROM swissSystem,ModulList WHERE ModulList.SwissSystem=1 AND swissSystem.id = ModulList.TournamentsystemId AND ModulList.ModulId = %d", 1));
                while (rsSwiss.next()) {
                    int idSwiss = rsSwiss.getInt(1);
                    cpDatabaseRecord(dcTmpXML, "swissSystem", idSwiss, dc, map);
                }
                ResultSet rsKoSystem = dc.select(String.format("SELECT KoSystem.id FROM KoSystem,ModulList WHERE ModulList.SwissSystem=0 AND KoSystem.id = ModulList.TournamentsystemId AND ModulList.ModulId = %d", 1));
                while (rsKoSystem.next()) {
                    int idKoSystem = rsKoSystem.getInt(1);
                    cpDatabaseRecord(dcTmpXML, "KoSystem", idKoSystem, dc, map);
                }
                ResultSet rsModulList = dc.select("SELECT id FROM ModulList WHERE ModulId = " + idModul);
                while (rsModulList.next()) {
                    int idModulList = rsModulList.getInt(1);
                    cpDatabaseRecord(dcTmpXML, "ModulList", idModulList, dc, map);
                }

            }
        }

        //insert the tournaments with the leaving dependencies
        for (String s : tournamentIds.split(",")) {
            int idTournament = Integer.parseInt(s);
            cpDatabaseRecord(dcTmpXML, "Tournament", idTournament, dc, map);
            for (int idRound : dcTmpXML.getIdsFrom("Round", "TournamentId = '" + idTournament + "'")) {
                cpDatabaseRecord(dcTmpXML, "Round", idRound, dc, map);
            }
            for (int id : dcTmpXML.getIdsFrom("ParticipantList")) {
                cpDatabaseRecord(dcTmpXML, "ParticipantList", id, dc, map);
            }
            for (int idEncounter : dcTmpXML.getIdsFrom("Encounter", "TournamentId = " + idTournament)) {
                cpDatabaseRecord(dcTmpXML, "Encounter", idEncounter, dc, map);
                for (int idPoints : dcTmpXML.getIdsFrom("Points", "EncounterId = " + idEncounter)) {
                    cpDatabaseRecord(dcTmpXML, "Points", idPoints, dc, map);
                }
            }
        }

    }

    /**
     * write a database record in the given table and map the foreignkeys to the
     * new ones
     *
     * @param dcFrom
     * @param table
     * @param id
     * @param dcTo
     * @param map
     * @throws SQLException
     */
    private void cpDatabaseRecord(DatabaseConnector dcFrom, String table, int id, DatabaseConnector dcTo, HashMap<String, HashMap<Integer, String>> map) throws SQLException {
        table = table.toUpperCase();//turn the table to uppercase for accecing stable to the hashmap 

        //Select all data from table and row
        ResultSet rsAllFromTable = dcFrom.select("SELECT * FROM " + table + " WHERE id = " + id);
        if (!rsAllFromTable.next()) {
            return;
        }
        ResultSetMetaData metaData = rsAllFromTable.getMetaData();
        ArrayList<String> columnames = new ArrayList<>();
        //get spalten
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            columnames.add(metaData.getColumnName(i + 1));
        }
        //foreach foreignKeys remove  "id" at the end and get the id from the hashmap

        //set key and value for foreignkeys
        ArrayList<String> tableForeignKeys = dc.getForeignKeys().get(table);
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        //add colums and values
        for (int i = 0; i < columnames.size(); i++) {
            String value = null;
            String name = columnames.get(i);
            boolean skip = false;
            if (name.equalsIgnoreCase("Id")) {
                skip = true;
            }

            if (tableForeignKeys != null) {

                for (String foreignKey : tableForeignKeys) {//if name is foreignkey
                    if (name.equalsIgnoreCase(foreignKey)) {
                        if (table.equalsIgnoreCase("ModulList") && name.equalsIgnoreCase("TournamentsystemId") ) {
                            ResultSet rsModulList = dcFrom.select("SELECT SwissSystem,modulId FROM ModulList WHERE id =" + id);
                            if (rsModulList.next()) {
                                Boolean swiss = rsModulList.getBoolean(1);
                                int modulId = rsModulList.getInt(2);
                                if (swiss) {
                                    value = map.get("SWISSSYSTEM").get(modulId);//map the id to the new one
                                } else {
                                    value = map.get("KOSYSTEM").get(modulId);//map the id to the new one
                                }
                            }

                        } else {

                            foreignKey = foreignKey.toUpperCase();
                            String fKey = foreignKey.replaceAll("ID$", "");
                            
                            ResultSet rsOldForeignId = dcFrom.select(String.format("SELECT %s FROM %s WHERE id = %d",foreignKey,table,id));
                            rsOldForeignId.next();
                            int oldForeignId = rsOldForeignId.getInt(1);
                            value = map.get(fKey).get(oldForeignId);//map the id to the new one
                            //value = map.get(table).get(fKey);//map the id to the new one
                        }
                    }
                }
            }
            if (!skip) {//add colum if not foreignkey or id
                if (value == null) {
                    //set the value if it isnt already set(eg. foreignKey)
                    value = rsAllFromTable.getString(i + 1);
                }

                if (value != null) {
                    //add the key,values if there is a value
                    keys.add(name);
                    values.add(value);
                }
            }
        }
        boolean insert = true;
        //Participant is special because ,if StartNumber is equal => update
        if (table.equalsIgnoreCase("Participant")) {
            ResultSet rsParticipantOld = dcFrom.select("SELECT StartNumber FROM Participant WHERE id = " + id);
            rsParticipantOld.next();
            String startNumberOld = rsParticipantOld.getString(1);
            ResultSet rsParticipant = dcTo.select("SELECT id FROM Participant WHERE StartNumber =  '" + startNumberOld + "'");
            if (rsParticipant.next()) {
                int participantIdNew = rsParticipant.getInt(1);
                insert = false;//update Participant

                for (int i = 0; i < keys.size(); i++) {
                    dcTo.update("Participant", keys.get(i), values.get(i), participantIdNew);
                }
                map.get(table).put(id, participantIdNew + "");

            }
        }
        //insert the values
        if (insert) {
            String insertKeyString = join(keys, ",");
            String insertValueString = join(values, "','");
            int returnid = dcTo.insert(String.format("INSERT INTO %s (%s) VALUES ('%s')", table, insertKeyString, insertValueString));
            map.get(table).put(id, returnid + "");
        }
    }

}

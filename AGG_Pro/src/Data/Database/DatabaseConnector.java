package Data.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Jonathan Göggel
 *
 * The DatabaseConnector has a connection to a database(contains Event) It is
 * used for every connection to the database
 *
 */
public class DatabaseConnector {

    private Connection connection = null;
    private String databasedir = System.getProperty("user.home") + "/aggpro/";  //directory to save the databases

    public DatabaseConnector(String database) throws ClassNotFoundException, SQLException {
        createConnection(databasedir, database);
    }

    /**
     * create a connection to the given datbase on the given location
     *
     * @param path
     * @param file
     * @throws ClassNotFoundException
     * @throws SQLException
     *
     */
    private void createConnection(String path, String file) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:" + path + file, "", "");

    }
    public void changeDatabase(String databaseName) throws Throwable{
        this.finalized();
        this.createConnection(databasedir, databaseName);
    }

    /**
     * create all Tables used for AGGPro
     *
     * @throws SQLException
     */
    public void createAllTables() throws SQLException {
            //first line of all tables is a primarykey autoincrement id
        //columns with ...Id are foreignkeys
        createTable("CREATE TABLE IF NOT EXISTS Tournament(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " NAME VARCHAR(255), "
                + " ModulId INT) "
        );

        createTable("CREATE TABLE IF NOT EXISTS swissSystem(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " NumberOfPlayersAfterCut INT, "
                + " NumberOfRounds INT) "
        );

        createTable("CREATE TABLE IF NOT EXISTS KoSystem(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " DoubleKO BOOLEAN, "
                + " ParticipantCount INT, "
                + " NumberOfPlayers INT) "
        );

        createTable("CREATE TABLE IF NOT EXISTS Modul(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " Name VARCHAR(255), "
                + " PointsWin INT, "
                + " PointsLoose INT, "
                + " PointsDraw INT)"
        );

        createTable("CREATE TABLE IF NOT EXISTS ModulList(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " ModulId INT, "
                + " TournamentsystemId INT, "
                + " SwissSystem BOOLEAN, "
                + " SortOrder INT) "
        );

        createTable("CREATE TABLE IF NOT EXISTS Participant(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + "  StartNumber VARCHAR (255), "
                + "  Prename VARCHAR(255), "
                + "  Surname VARCHAR(255), "
                + "  Nickname VARCHAR(255), "
                + "  Email VARCHAR(255), "
                + " Paid BOOLEAN, "
                + " Presend BOOLEAN, "
                + "  Other VARCHAR(255), "
                + " Freepass BOOLEAN, "
                + " Superfreepass BOOLEAN) "
        );

        createTable("CREATE TABLE IF NOT EXISTS ParticipantList(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " ParticipantId INT, "
                + " TournamentId INT) "
        );

        createTable("CREATE TABLE IF NOT EXISTS Round(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " TournamentId INT, "
                + " Round INT) "
        );
        createTable("CREATE TABLE IF NOT EXISTS Encounter(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " TournamentId INT, "
                + " RoundId INT) "
        );
        createTable("CREATE TABLE IF NOT EXISTS Points(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + " ParticipantId INT, "
                + " EncounterId INT, "
                + " Points INT) "
        );
        //keys are StartDate EndDate name and so on
        createTable("CREATE TABLE IF NOT EXISTS EventProperties(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
                + "  Key VARCHAR(255), "
                + "  Value VARCHAR(255)) "
        );
    }

    /**
     * Create one table
     *
     * @param createstr
     * @throws SQLException
     */
    public void createTable(String createstr) throws SQLException {
        execute(createstr);
    }

    /**
     * check if a table exists
     *
     * @param tablename
     * @return if the table exists
     * @throws SQLException
     */
    public boolean checkTable(String tablename) throws SQLException {
        tablename = tablename.toUpperCase();
        ResultSet rs = select("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' and TABLE_NAME = '" + tablename + "'");
        while (rs.next()) {
            return true;
        }
        return false;
    }

    /**
     * destructor close the connection to the database
     *
     * @throws SQLException
     */
    public void finalized() throws SQLException {
        connection.close();
    }

    /**
     *
     * @param selectstr
     * @return resultset of the given statement
     * @throws SQLException
     */
    public ResultSet select(String selectstr) throws SQLException {
        ResultSet tableRS = null;
        Statement stmt = connection.createStatement();
        tableRS = stmt.executeQuery(selectstr);
        return tableRS;
    }

    /**
     * execute a SQL-INSERT-String into the database
     *
     * @param insertstr
     * @return the id(primarykey) of the inserted record
     * @throws SQLException
     */
    public int insert(String insertstr) throws SQLException {
        Statement stmt;
        stmt = connection.createStatement();
        stmt.executeUpdate(insertstr, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();//get the primarykey (Id)
        rs.next();
        int pk = rs.getInt(1);
        return pk;
    }

    /**
     * execute a SQL-UPDATE-String
     *
     * @param updatestr
     * @throws SQLException
     */
    public void update(String updatestr) throws SQLException {
        execute(updatestr);
    }

    /**
     * update set the value in the given field on the given table where id
     * maches
     *
     * @param table
     * @param field
     * @param value
     * @param id
     * @throws SQLException
     */
    public void update(String table, String field, String value, int id) throws SQLException {
        execute("UPDATE " + table + " SET " + field + " = '" + value + "' WHERE id = " + id);
    }

    /**
     * update set the value in the given field on the given table where id
     * maches
     *
     * @param table
     * @param field
     * @param value
     * @param id
     * @throws SQLException
     */
    public void update(String table, String field, int value, int id) throws SQLException {
        execute("UPDATE " + table + " SET " + field + " = '" + value + "' WHERE id = " + id);
    }

    /**
     * update set the value in the given field on the given table where id
     * maches
     *
     * @param table
     * @param field
     * @param value
     * @param id
     * @throws SQLException
     */
    public void update(String table, String field, Boolean value, int id) throws SQLException {
        execute("UPDATE " + table + " SET " + field + " = '" + value + "' WHERE id = " + id);
    }

    /**
     * execute a sql-string
     *
     * @param execute string
     * @throws SQLException
     */
    public void execute(String executestr) throws SQLException {
        
        Statement stmt;
        stmt = connection.createStatement();
        stmt.executeUpdate(executestr);
    }

    /**
     * delete the record with the id from the given table
     *
     * @param tablename
     * @param id
     * @throws SQLException
     */
    public void delete(String tablename, int id) throws SQLException {
        execute("DELETE FROM " + tablename + " WHERE Id = " + id);
    }

    /**
     * execute a SQL-DELETE-String
     *
     * @param deletestr
     * @throws SQLException
     */
    public void delete(String deletestr) throws SQLException {
        execute(deletestr);
    }

    /**
     * clear the the whole database(drop all tables)
     *
     * @throws SQLException
     */
    public void clearDatabase() throws SQLException {
        execute("DROP ALL OBJECTS");
    }

    /**
     * function for testing
     *
     * @param sql
     * @return the result of a sql-string as a string
     * @throws SQLException
     */
    public String test_selecttostr(String sql) throws SQLException {
        String result = "";

        Statement stmt = connection.createStatement();
        ResultSet selectRS = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = selectRS.getMetaData();
        int anzahl = rsmd.getColumnCount();
        while (selectRS.next()) {
            for (int i = 1; i <= anzahl; i++) {
                result += String.format("%s,", selectRS.getString(i));
            }
            result += "\n";
        }

        return result;
    }

    /**
     * function for testing
     *
     * @return all tablenames
     * @throws SQLException
     */
    public String test_tablelisttostring() throws SQLException {
        String result = "";
        for (String table : getAllTables()) {
            result += String.format("%s\n", table);
        }
        return result;
    }

    /**
     * get all tables
     *
     * @return tablenames
     * @throws SQLException
     */
    public ArrayList<String> getAllTables() throws SQLException {
        ArrayList<String> tableList = new ArrayList<>();
        String tables = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' ORDER BY TABLE_NAME";
        ResultSet tablesRS;

        Statement stmt = connection.createStatement();
        tablesRS = stmt.executeQuery(tables);
        while (tablesRS.next()) {
            tableList.add(tablesRS.getString(1));
        }
        return tableList;
    }

    /**
     * tests if the Participant exists
     *
     * @param startnumber
     * @return if the participant exist
     * @throws SQLException
     */
    public boolean existParticipantStartnumber(String startnumber) throws SQLException {
        ResultSet rs = select("SELECT FROM Participant WHERE Startnumber = '" + startnumber + "'");
        while (rs.next()) {
            return true;
        }
        return false;
    }

    /**
     * return all ids from a table
     *
     * @param table
     * @return ids
     * @throws SQLException
     */
    public ArrayList<Integer> getIdsFrom(String table) throws SQLException {
        return getIdsFrom(table, "");
    }

    /**
     * return ids from all matching records
     *
     * @param table
     * @param where
     * @return ids
     * @throws SQLException
     */
    public ArrayList<Integer> getIdsFrom(String table, String where) throws SQLException {
        String selectstr = "SELECT id From " + table;
        if (where != "") {//if no where is given ignore it 
            selectstr += " WHERE " + where;
        }
        ResultSet rs = select(selectstr);
        ArrayList<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getInt(1));
        }
        return ids;

    }

    /**
     * function for testing
     *
     * @return all data of the database as string
     * @throws SQLException
     */
    public String test_databaseToStr() throws SQLException {
        String s = "";
        for (String table : getAllTables()) {
            s += table + "\n";
            s += test_tabledDesc(table) + "\n";
            s += test_selecttostr("SELECT * FROM " + table) + "\n";
        }
        return s;

    }
    
    public String test_tabledDesc(String table) throws SQLException{
        String result="";
        ResultSet rs = select("SELECT * FROM " + table);
        ResultSetMetaData rsmd = rs.getMetaData();
        
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            result+= rsmd.getColumnName(i)+" - ";
        }
        return result;
        
    }
    
    public HashMap<String, ArrayList<String>> getForeignKeys(){
        HashMap<String, ArrayList<String>> foreignKeys = new HashMap<>();
        foreignKeys.put("TOURNAMENT", new ArrayList(Arrays.asList(new String[]{"ModulId"})));
        //foreignKeys.put("SWISSSYSTEM", new ArrayList(Arrays.asList(new String[]{"TournamentSystemId"})));
        //foreignKeys.put("KOSYSTEM", new ArrayList(Arrays.asList(new String[]{"TournamentSystemId"})));
        foreignKeys.put("MODULLIST", new ArrayList(Arrays.asList(new String[]{"ModulId", "TournamentsystemId"})));
        foreignKeys.put("PARTICIPANTLIST", new ArrayList(Arrays.asList(new String[]{"ParticipantId", "TournamentId"})));
        foreignKeys.put("ROUND", new ArrayList(Arrays.asList(new String[]{"TournamentId"})));
        foreignKeys.put("ENCOUNTER", new ArrayList(Arrays.asList(new String[]{"TournamentId", "RoundId"})));
        foreignKeys.put("POINTS", new ArrayList(Arrays.asList(new String[]{"ParticipantId", "EncounterId"})));
    return foreignKeys;
    }

}

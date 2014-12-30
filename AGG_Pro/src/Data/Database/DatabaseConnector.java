

package Data.Database;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.ArrayList;

/**
 * 
 * @author Jonathan Göggel
 *
 */
public class DatabaseConnector {
	private Connection connection = null ;
	private String databasedir = "./.aggpro/";//TODO
	public DatabaseConnector(String database) throws ClassNotFoundException, SQLException {
		createConnection(databasedir,database);
	}
	private void createConnection(String path,String file) throws ClassNotFoundException, SQLException{
		Class.forName("org.h2.Driver"); 
		connection = DriverManager.getConnection("jdbc:h2:"+path+file, "", "");

	}
	public void createAllTables() throws SQLException{
		createTable("CREATE TABLE IF NOT EXISTS Tournament(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " NAME VARCHAR(255))"
				); 

		createTable("CREATE TABLE IF NOT EXISTS swissSystem(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " TournamentSystemId INT, "
				+ " NumberOfPlayersAfterCut INT, "
				+ " NumberOfRounds INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS KoSystem(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " TournamentSystemId INT, "
				+ " DoubleKO BOOLEAN, "
				+ " NumberOfPlayers INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS TournamentSystem(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " cut INT, "
				+ " ParticipantCount INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS Modul(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " TournamentId INT, "
				+ " Tournamentsystemid INT, "
				+ "  Name VARCHAR(255), "
				+ " SortOrder INT, "
				+ " PrimaryPoints INT, "
				+ " SecondaryPoints INT, "
				+ " TertiaryPoints INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS Participant(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ "  Prename VARCHAR(255), "
				+ "  Surname VARCHAR(255), "
				+ "  Nickname VARCHAR(255), "
				+ "  Emal VARCHAR(255), "
				+ " Paied BOOLEAN, "
				+ " Presend BOOLEAN, "
				+ "  Other VARCHAR(255), "
				+ " Freepass INT, "
				+ " Superfreepass INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS ParticipantList(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " ParticipantId INT, "
				+ " TournamentId INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS ParticipantEncounter(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " ParticipantListId INT, "
				+ " Encounterid INT, "
				+ " Points INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS Encounter(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ " TournamentId INT, "
				+ " Round INT) "
				);

		createTable("CREATE TABLE IF NOT EXISTS EventProperties(Id INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL, "
				+ "  Key VARCHAR(255), "
				+ "  Value VARCHAR(255)) "
				);
		//Name Password StartDate EndDate
		//TODO
	}
	public void createTable(String createstr ) throws SQLException{
		execute(createstr); 
	}
	public boolean checkTable(String tablename) throws SQLException{
		tablename = tablename.toUpperCase();
		ResultSet rs = select("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' and TABLE_NAME = '"+ tablename+"'");
		while (rs.next()){
			return true;
		}
		return false;
	}
	public void finalized(){
		try { 
			connection.close(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 

	}



	public ResultSet select(String selectstr) throws SQLException{
		ResultSet tableRS=null;
		Statement stmt = connection.createStatement();
		tableRS = stmt.executeQuery(selectstr);
		return tableRS;
	}
	public void insert(String insertstr) throws SQLException{
		execute(insertstr);
	}
	public void update(String updatestr) throws SQLException{
		execute(updatestr);
	}
	public void execute(String executestr ) throws SQLException{
		Statement stmt;
		stmt = connection.createStatement();
		stmt.executeUpdate(executestr); 
	}
	public void delete(String tablename,int id) throws SQLException{
		execute("DELETE FROM "+ tablename + " WHERE Id = " + id );
	}
	public void clearDatabase() throws SQLException{
		execute("DROP ALL OBJECTS");
		//execute("DROP ALL OBJECTS DELETE FILES"); TODO
	}





	public String test_selecttostr(int anzahl,String sql){
		String result="";
		try {
			Statement stmt = connection.createStatement();
			ResultSet selectRS = stmt.executeQuery(sql); 
			while (selectRS.next()) { 
				for (int i = 1; i <= anzahl; i++) {
					result += String.format("%s,", selectRS.getString(i));
				}
				result +=  "\n";
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String test_tablelisttostring(){
		String result="";
		for (String table: getAllTables()) {
				result += String.format("%s\n", table);
		}
		return result;
	}
	public ArrayList<String> getAllTables(){
		ArrayList<String> tables = new ArrayList<>();
		String tablesQ = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' ORDER BY TABLE_NAME";
		ResultSet tablesRS;
		try {
			Statement stmt = connection.createStatement();
			tablesRS = stmt.executeQuery(tablesQ);
			while (tablesRS.next()) { 
				tables.add(tablesRS.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return tables;
	}

}

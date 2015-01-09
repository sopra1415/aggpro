package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Modul {
	private String name;
	private int id;
	private DatabaseConnector dc;
	private int pointsWin;
	private int pointsLoose;
	private int pointsDraw;
	private ArrayList<TournamentSystem> tournamentsystems = new ArrayList<TournamentSystem>();
	//TODO: Darüber müssen wir nochmal reden ...

	public Modul(DatabaseConnector dc,String name, int id, int pointsWin, int pointsLoose,
			int pointsDraw) throws SQLException {
		this.name = name;
		this.dc=dc;
		this.pointsWin = pointsWin;
		this.pointsLoose = pointsLoose;
		this.pointsDraw = pointsDraw;
		this.id = dc.insert(String.format("INSERT INTO Modul (Name,PointsWin,PointsLoose,PointsDraw) VALUES (%d,%d,%d) ",name,pointsWin,pointsLoose,pointsDraw));
	}
	
	public Modul(DatabaseConnector dc,Integer id) throws SQLException{
		this.id = id;
		ResultSet rs = dc.select("SELECT Name,PointsWin,PointsLoose,PointsDraw FROM Modul WHERE id = "+id);
		rs.next();
		this.name = rs.getString(1);
		this.pointsWin = rs.getInt(2);
		this.pointsLoose = rs.getInt(3);
		this.pointsDraw = rs.getInt(4);
		
		rs = dc.select("SELECT TournamentsystemId,SwissSystem,Cut,ParticipantCount FROM ModulList WHERE ModulId = "+ id +" ORDER BY SortOrder");
		while(rs.next()){
			int tournamentsystemId = rs.getInt(1);
			boolean swissSystem = rs.getBoolean(2);
			int cut = rs.getInt(3);
			int participantCount = rs.getInt(4);
			if(swissSystem){
				ResultSet rsSwiss = dc.select("SELECT NumberOfPlayersAfterCut, NumberOfRounds FROM swissSystem WHERE id = "+ tournamentsystemId);
				rsSwiss.next();
				int numberOfPlayersAfterCut = rsSwiss.getInt(1);
				int numberOfRounds = rsSwiss.getInt(2);
				this.tournamentsystems.add(new SwissSystem(name,numberOfPlayersAfterCut,numberOfRounds));
			}else{
			ResultSet rsKO = dc.select("SELECT DoubleKO,NumberOfPlayers FROM  KoSystem WHERE id = "+ tournamentsystemId);	
			rsKO.next();
				boolean doubleKO = rsKO.getBoolean(1);
				int numberOfPlayers = rsKO.getInt(2);
				this.tournamentsystems.add(new KoSystem(name, numberOfPlayers, doubleKO));
			}
				

				
		}
		
	}
	
	// getters and setters


	public String getName() {
		return name;
	}

	public void setName(String name) throws SQLException {
		this.name = name;
		dc.update("Modul", "Name", name, id);
	}

	public int getId() {
		return id;
	}

	public int getPointsWin() {
		return pointsWin;
	}

	public void setPointsWin(int pointsWin) throws SQLException {
		this.pointsWin = pointsWin;
		dc.update("Modul", "PointsWin", pointsWin, id);
	}

	public int getPointsLoose() {
		return pointsLoose;
	}

	public void setPointsLoose(int pointsLoose) throws SQLException {
		this.pointsLoose = pointsLoose;
		dc.update("Modul", "PointsLoose", pointsLoose, id);
	}

	public int getPointsDraw() {
		return pointsDraw;
	}

	public void setPointsDraw(int pointsDraw) throws SQLException {
		this.pointsDraw = pointsDraw;
		dc.update("Modul", "PointsDraw", pointsDraw, id);
	}


	
}

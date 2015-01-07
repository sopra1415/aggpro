package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Modul {
	private String name;
	private int Id;
	private int pointsWin;
	private int pointsLoose;
	private int pointsDraw;
	private ArrayList<TournamentSystem> tournamentsystems = new ArrayList<TournamentSystem>();
	//TODO: Darüber müssen wir nochmal reden ...

	public Modul(String name, int id, int primaryPoints, int secondaryPoints,
			int tertiaryPoints) {
		super();
		this.name = name;
		Id = id;
		this.pointsWin = primaryPoints;
		this.pointsLoose = secondaryPoints;
		this.pointsDraw = tertiaryPoints;
	}
	
	public Modul(DatabaseConnector dc,Integer id) throws SQLException{
		this.Id = id;
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
				rs.next();
				int numberOfPlayersAfterCut = rs.getInt(1);
				int numberOfRounds = rs.getInt(2);
				this.tournamentsystems.add(new SwissSystem(name,numberOfPlayersAfterCut,numberOfRounds));
			}else{
			ResultSet rsKO = dc.select("SELECT DoubleKO,NumberOfPlayers FROM  KoSystem WHERE id = "+ tournamentsystemId);	
			rs.next();
				boolean doubleKO = rs.getBoolean(1);
				int numberOfPlayers = rs.getInt(2);
				this.tournamentsystems.add(new KoSystem(name, numberOfPlayers, doubleKO));
			}
				

				
		}
		
	}
	
	// getters and setters


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getPrimaryPoints() {
		return pointsWin;
	}

	public void setPrimaryPoints(int primaryPoints) {
		this.pointsWin = primaryPoints;
	}

	public int getSecondaryPoints() {
		return pointsLoose;
	}

	public void setSecondaryPoints(int secondaryPoints) {
		this.pointsLoose = secondaryPoints;
	}

	public int getTertiaryPoints() {
		return pointsDraw;
	}

	public void setTertiaryPoints(int tertiaryPoints) {
		this.pointsDraw = tertiaryPoints;
	}
	
}

package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Round {
	private int id;
	private int round;
	private Tournament tournament;
	private ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private DatabaseConnector dc;
	
	public Round(DatabaseConnector dc,int id, Tournament tournament,int round) throws SQLException {
		this.tournament = tournament;
		this.dc = dc;
		this.round = round;
		this.id = dc.insert("INSERT INTO Round(TournamentId,Round) VALUES ('"+tournament.getId()+"', '"+round+"')");
	}
	public Round(DatabaseConnector dc,int id,Tournament tournament,ArrayList<Participant> participants) throws SQLException{
		this.id=id;
		this.tournament=tournament;
		ResultSet rs = dc.select("SELECT Round FROM Round WHERE Id = " + id );
		rs.next();
		this.round = rs.getInt(1);
		
		rs = dc.select("SELECT id FROM Encounter WHERE TournamentId = "+tournament.getId() +" AND Round = "+round);
		while (rs.next()) {
			int encounterId=rs.getInt(1);
			encounters.add(new Encounter(dc,encounterId,this,participants));
		}
	}
	
	
	// getters and setters
	public int getId() {
		return id;
	}


	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) throws SQLException {
		this.tournament = tournament;
		dc.update("Round", "TournamentId", tournament.getId(), id);
	}

	public Encounter getEncounter(int encounterId) throws Exception{
		for(Encounter encounter: encounters){
			if(encounter.getId() == encounterId){
				return encounter;
			}
		}
		return null;
	}
	
	public void addEncounter(Encounter encounter) throws SQLException{
		encounters.add(encounter);
		dc.update(String.format("INSERT INTO Encounter (TournamentId,RoundId  ) VALUES (%d,%d)",tournament.getId(),id));
	}
	
	public void deleteEncounter(Encounter encounter) throws SQLException{
		encounters.remove(encounter);
		dc.delete(String.format("DELETE FROM Encounter WHERE tournament = %d AND RoundId = %d",tournament.getId(),id));
	}
	public int getRound(){
		return round;
	}

}

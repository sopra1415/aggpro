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
	
	public Round(int id, Tournament tournament) {
		this.id = id;
		this.tournament = tournament;
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

	public void setId(int id) {
		this.id = id;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Encounter getEncounter(int encounterId) throws Exception{
		for(Encounter encounter: encounters){
			if(encounter.getId() == encounterId){
				return encounter;
			}
		}
		return null;
	}
	
	public void addEncounter(Encounter encounter){
		encounters.add(encounter);
	}
	
	public void deleteEncounter(Encounter encounter){
		encounters.remove(encounter);
	}

}

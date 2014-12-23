package liveclasses;

import java.util.ArrayList;

public class Round {
	private int id;
	private Tournament tournament;
	private ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	
	public Round(int id, Tournament tournament) {
		this.id = id;
		this.tournament = tournament;
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
		throw new Exception("There is now Encounter with the given id ");
	}
	
	public void addEncounter(Encounter encounter){
		encounters.add(encounter);
	}
	
	public void deleteEncounter(Encounter encounter){
		encounters.remove(encounter);
	}

}

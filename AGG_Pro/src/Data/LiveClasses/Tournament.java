package Data.LiveClasses;

import java.util.ArrayList;

public class Tournament {
	private String name;
	private int id;
	private Modul modul;
	private ArrayList<Participant> participants = new ArrayList<Participant>();
	private ArrayList<Round> rounds = new ArrayList<Round>();
	
	
	public Tournament(String name, int id, Modul modul) {
		super();
		this.name = name;
		this.id = id;
		this.modul = modul;
	}
	// getters and setters

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Modul getModul() {
		return modul;
	}
	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
	public Participant getParticipant(int participantId) throws Exception{
		for(Participant participant: participants){
			if(participant.getId() == participantId){
				return participant;
			}
		}
		return null;
	}
	
	public void addParticpant(Participant participant){
		participants.add(participant);
	}
	
	public void deleteParticipant(Participant participant){
		participants.remove(participant);
	}
	
	public Round getRound(int roundId) throws Exception{
		for(Round round: rounds){
			if(round.getId() == roundId){
				return round;
			}
		}
		return null;
	}
	
	public void addRound(Round round){
		rounds.add(round);
	}
	
	public void deleteRound(Round round){
		rounds.remove(round);
	}

}

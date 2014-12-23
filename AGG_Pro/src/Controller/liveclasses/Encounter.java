package liveclasses;

import java.util.ArrayList;

public class Encounter {
	private String name;
	private Round round;
	private int id;
	private ArrayList<Participant> participants = new ArrayList<Participant>();
	
	public Encounter(String name, Round round) {
		super();
		this.name = name;
		this.round = round;
	}
	
	// getters and setters

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
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
	

}

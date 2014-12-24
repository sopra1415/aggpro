package Data.LiveClasses;

import java.util.ArrayList;

public class Participant {
	private String name;
	private int id;
	private String prename;
	private String nickname;
	private boolean payed;
	private boolean present;
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private String other;
	private boolean freepass;
	private boolean superfreepass;
	
	public Participant(String name, String prename, String nickname,
			boolean payed, boolean present, String other, boolean freepass,
			boolean superfreepass) {
		super();
		this.name = name;
		this.prename = prename;
		this.nickname = nickname;
		this.payed = payed;
		this.present = present;
		this.other = other;
		this.freepass = freepass;
		this.superfreepass = superfreepass;
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

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public boolean isFreepass() {
		return freepass;
	}

	public void setFreepass(boolean freepass) {
		this.freepass = freepass;
	}

	public boolean isSuperfreepass() {
		return superfreepass;
	}

	public void setSuperfreepass(boolean superfreepass) {
		this.superfreepass = superfreepass;
	}
	
	public Tournament getTournament(int tournamentId) throws Exception{
		for(Tournament Tournament: tournaments){
			if(Tournament.getId() == tournamentId){
				return Tournament;
			}
		}
		return null;
	}
	
	public void addTournament(Tournament Tournament){
		tournaments.add(Tournament);
	}
	
	public void deleteTournament(Tournament Tournament){
		tournaments.remove(Tournament);
	}
	

}

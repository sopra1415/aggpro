package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Participant {
	private String name;
	private int id;
	private String prename;
	private String nickname;
	private boolean paid;
	private boolean present;
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private String other;
	private boolean freepass;
	private boolean superfreepass;
	private String email;
	
	public Participant(String name, String prename, String nickname,
			boolean payed, boolean present, String other, boolean freepass,
			boolean superfreepass) {
		super();
		this.name = name;
		this.prename = prename;
		this.nickname = nickname;
		this.paid = payed;
		this.present = present;
		this.other = other;
		this.freepass = freepass;
		this.superfreepass = superfreepass;
	}
	
	public Participant(DatabaseConnector dc, Integer id, ArrayList<Tournament> tournaments) throws SQLException{
		this.id=id;
		ResultSet rs = dc.select("SELECT Prename,Surname,Nickname,Email,Paid,Presend,Other,Freepass,Superfreepass FROM Participent WHERE id = " + id);
		rs.next();
		this.prename = rs.getString(1);
		this.name = rs.getString(2);
		this.nickname = rs.getString(3);
		this.email = rs.getString(4);
		this.paid = rs.getBoolean(5);
		this.present = rs.getBoolean(6);
		this.other = rs.getString(7);
		this.freepass = rs.getBoolean(8);
		this.superfreepass = rs.getBoolean(9);

		rs = dc.select("SELECT TournamentId FROM ParticipantList WHRER ParticipantId = " + id);
		while (rs.next()) {
			Integer tournamentid = rs.getInt(1);
			for (Tournament tournament : tournaments) {
				if(tournament.getId() == tournamentid){
					tournament.addParticipantInit(this);
					this.tournaments.add(tournament);
				}
			}
		}
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
		return paid;
	}

	public void setPayed(boolean payed) {
		this.paid = payed;
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

package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.util.Vector;
import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

public class Participant {
	private String name;
	private int id;
	private String startnumber;
	private String prename;
	private String nickname;
	private boolean paid;
	private boolean present;
	private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	private String other;
	private boolean freepass;
	private boolean superfreepass;
	private String email;
	private DatabaseConnector dc;

	public Participant(DatabaseConnector dc, String startnubmer, String name, String prename, String nickname, String email, 
			boolean payed, boolean present, String other, boolean freepass,
			boolean superfreepass) throws SQLException {
		this.dc = dc;
                this.startnumber = startnubmer;
		this.name = name;
		this.prename = prename;
		this.nickname = nickname;
                this.email = email;
		this.paid = payed;
		this.present = present;
		this.other = other;
		this.freepass = freepass;
		this.superfreepass = superfreepass;
		this.id = dc.insert(String.format("INSERT INTO Participant (Startnumber, Prename, Surname, Nickname, Email, Paid, Presend, Other, Freepass, Superfreepass) VALUES('%s','%s','%s','%s','%s',%d,%d,'%s',%d,%d)", startnubmer, prename, name, nickname, email, booleanToNumber(paid), booleanToNumber(present), other, booleanToNumber(freepass), booleanToNumber(superfreepass) ));
	}

	public Participant(DatabaseConnector dc, Integer id, ArrayList<Tournament> tournaments) throws SQLException{
		this.id=id;
                this.dc=dc;
		ResultSet rs = dc.select("SELECT Prename,Surname,Nickname,Email,Paid,Presend,Other,Freepass,Superfreepass,Startnumber FROM Participant WHERE id = " + id);
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
		this.startnumber = rs.getString(10);


		rs = dc.select("SELECT TournamentId FROM ParticipantList WHERE ParticipantId = " + id);
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

	public String getName() {
		return name;
	}

	public String getStartnumber() {
		return startnumber;
	}

	//public void setStartnumber(String startnumber) throws SQLException {
	//	this.startnumber = startnumber;
	//	dc.update("Participant", "StartNumber", startnumber, id);
	//}

	public void setName(String name) throws SQLException {
		this.name = name;
		dc.update("Participant", "Surname", name, id);
	}

	public String getPrename() {
		return prename;
	}
        
        public String getEmail(){
            return email;
        }
        
        public void setEmail(String email) throws SQLException {
            this.email = email;
            dc.update("Participant", "Email", email, id);
        }

	public void setPrename(String prename) throws SQLException {
		this.prename = prename;
		dc.update("Participant","Prename",prename,id);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) throws SQLException {
		this.nickname = nickname;
		dc.update("Participant", "Nickname", nickname, id);


	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) throws SQLException {
		this.paid = paid;
		dc.update("Participant", "Paid", paid, id);

	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) throws SQLException {
		this.present = present;
		dc.update("Participant", "Presend", present, id);

	}

	public String getOther() {
		return other;

	}

	public void setOther(String other) throws SQLException {
		this.other = other;
		dc.update("Participant", "Other", other, id);


	}

	public boolean isFreepass() {
		return freepass;

	}

	public void setFreepass(boolean freepass) throws SQLException {
		this.freepass = freepass;
		dc.update("Participant", "Freepass", freepass, id);

	}

	public boolean isSuperfreepass() {
		return superfreepass;
	}

	public void setSuperfreepass(boolean superfreepass) throws SQLException {
		this.superfreepass = superfreepass;
		dc.update("Participant", "Superfreepass", superfreepass, id);

	}

	public Tournament getTournament(int tournamentId) throws Exception{
		for(Tournament Tournament: tournaments){
			if(Tournament.getId() == tournamentId){
				return Tournament;
			}
		}
		return null;
	}

	public void addTournament(Tournament tournament) throws SQLException{
		tournaments.add(tournament);
		dc.insert(String.format("INSERT INTO ParticipantList(ParticipantId, TournamentId) VALUES (%d, %d)", this.id, tournament.getId()));
	}

	public void deleteTournament(Tournament tournament) throws SQLException{
		if(tournaments.contains(tournament)){
			tournaments.remove(tournament);
			dc.delete(String.format("DELETE FROM ParticipantList WHERE TournamentId = %d AND ParticipantId = &d", tournament.getId(), this.id));
		}
	}

	public ArrayList<Tournament> getTournaments(){
		return this.tournaments;
	}

	public Vector getData(){
		Vector data = new Vector();
		data.add(startnumber);
		data.add(this.name);
		data.add(this.prename);
		data.add(this.nickname);
		return data;
	}
        
        private int booleanToNumber(boolean in){
            if (in){
                return 1;
            }else {
                return 0;
            }
        }


}

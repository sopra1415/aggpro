package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;

/**
 * Live Class for the Event
 */
public class Event {

    private String name;
    private GregorianCalendar startDate = new GregorianCalendar();
    private GregorianCalendar endDate = new GregorianCalendar();
    private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
    private ArrayList<Participant> participants = new ArrayList<Participant>();
    private DatabaseConnector dc;

    /**
     * Creates a new Event and Insert it into the database
     *
     * @param name
     * @param startDate
     * @param endDate
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Event(String name, GregorianCalendar startDate, GregorianCalendar endDate) throws ClassNotFoundException, SQLException {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        dc = new DatabaseConnector(name);
        dc.createAllTables();
        dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('name','" + name + "')");
        dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('startDate','" + startDate.getTimeInMillis() + "')");
        dc.insert("INSERT INTO EventProperties(Key, Value ) VALUES('endDate','" + endDate.getTimeInMillis() + "')");

    }

    /**
     * Load Event from Database
     *
     * @param dc
     * @throws SQLException
     * @throws ParseException
     */
    public Event(DatabaseConnector dc) throws SQLException, ParseException {
        ResultSet rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'name'");
        if (rs.next()) {
            this.name = rs.getString(1);
        }
        rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'startDate'");
        if (rs.next()) {
            String startDateStr = rs.getString(1);
            // Convert the Date in Milliseconds to insert it into database
            startDate.setTimeInMillis(Long.parseLong(startDateStr));
        }
        rs = dc.select("SELECT Value FROM EventProperties WHERE Key = 'endDate'");
        if (rs.next()) {
            String endDateStr = rs.getString(1);
            endDate.setTimeInMillis(Long.parseLong(endDateStr));
        }
        ArrayList<Integer> tournamentIds = new ArrayList<>();
        rs = dc.select("SELECT Id FROM Tournament");
        while (rs.next()) {
            Integer id = rs.getInt(1);
            if (id != null) {
                tournamentIds.add(id);
            }
        }

        ArrayList<Integer> participantIds = new ArrayList<>();
        rs = dc.select("SELECT Id FROM Participant");
        while (rs.next()) {
            Integer id = rs.getInt(1);
            if (id != null) {
                participantIds.add(id);
            }
        }
        for (Integer id : participantIds) {
            Participant tempParticipant = new Participant(dc, id);
            if (!this.participants.contains(tempParticipant)) {
                participants.add(tempParticipant);
            }
            //this.participants.add(new Participant(dc, id));//ALT : this method also connectet participant ant tournament
        }
        for (Integer id : tournamentIds) {
            Tournament tempTournament = new Tournament(dc, id, participants);
            if (!this.tournaments.contains(tempTournament)) {
                this.tournaments.add(tempTournament);
            }
        }

        this.dc = dc;
    }
    // getters and setters

    public String getName() {
        return name;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     *
     * @param participantId
     * @return
     * @throws Exception
     */
    public Participant getParticipant(int participantId) throws Exception {
        for (Participant participant : participants) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }

    /**
     *
     * @param tournamentId
     * @return
     * @throws Exception
     */
    public Tournament getTournament(int tournamentId) throws Exception {
        for (Tournament Tournament : tournaments) {
            if (Tournament.getId() == tournamentId) {
                return Tournament;
            }
        }
        return null;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    /**
     *
     * @param tournamentName
     * @return
     */
    public Tournament getTournament(String tournamentName) {
        for (Tournament t : tournaments) {
            if (t.getName().equals(tournamentName)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public DatabaseConnector getDatabaseConnector() {
        return dc;
    }

    /**
     * searches an single Participant with the given Number
     *
     * @param participantNumber startNumber of the participant
     * @return
     */
    public Participant getParticipant(String participantNumber) {
        for (Participant p : participants) {
            if (p.getStartnumber().equals(participantNumber)) {
                return p;
            }
        }
        return null;
    }

    public void setName(String name) throws SQLException, IOException {
        File oldDatabase = new File(System.getProperty("user.home")+"/aggpro/"+this.name);
        this.name = name;
        dc.update("UPDATE EventProperties SET name ='" + name + "'");
        boolean res = oldDatabase.renameTo(new File(System.getProperty("user.home")+"/aggpro/"+this.name));
        System.out.println(res);
    }

    public void setStartDate(GregorianCalendar startDate) throws SQLException {
        this.startDate = startDate;
        dc.update("UPDATE EventProperties SET name ='" + startDate + "'");

    }

    public void setEndDate(GregorianCalendar endDate) throws SQLException {
        this.endDate = endDate;
        dc.update("UPDATE EventProperties SET name ='" + endDate + "'");

    }

    public void addParticpant(Participant participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

    public void deleteParticipant(Participant participant) throws SQLException {
        participants.remove(participant);
        dc.delete("Participant", participant.getId());
    }

    public void addTournament(Tournament tournament) {
        if (!tournaments.contains(tournament)) {
            tournaments.add(tournament);
        }
    }

    public void deleteTournament(Tournament tournament) throws SQLException {
        tournaments.remove(tournament);
        dc.delete("Tournament", tournament.getId());
        for (Participant participant : participants) {
            participant.deleteTournament(tournament);
        }

    }

}

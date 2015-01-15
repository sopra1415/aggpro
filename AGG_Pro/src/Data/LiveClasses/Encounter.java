package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

/**
 * LiveClass for a Encounter
 *
 */
public class Encounter {

    private Round round;

  
    private int id;
    private ArrayList<Participant> participants = new ArrayList<Participant>();
    private ArrayList<Integer> points = new ArrayList<>();
    private DatabaseConnector dc;

    /**
     * Creates new Encounter and insert it into the database
     *
     * @param dc
     * @param round
     * @throws SQLException
     */
    public Encounter(DatabaseConnector dc, Round round) throws SQLException {
        this.dc = dc;
        this.round = round;
        this.id = dc.insert(String.format("INSERT INTO Encounter (RoundId, TournamentId) VALUES (%d, %d)", round.getId(), round.getTournament().getId()));
    }

    /**
     * Load encounter from the Database
     *
     * @param dc
     * @param id
     * @param round
     * @param paticipants
     * @throws SQLException
     */
    public Encounter(DatabaseConnector dc, int id, Round round, ArrayList<Participant> paticipants) throws SQLException {
        this.id = id;
        this.round = round;
        ResultSet rs = dc.select("SELECT ParticipantId,Points FROM Points WHERE EncounterId = " + id);
        while (rs.next()) {
            int participantId = rs.getInt(1);
            int points = rs.getInt(2);
            for (Participant participant : paticipants) {
                if (participant.getId() == participantId) {
                    this.participants.add(participant);
                    this.points.add(points);
                }
            }

        }
    }

	// getters and setters
    public int getId() {
        return id;
    }

    public Round getRound() {
        return round;
    }

    public Participant getParticipant(int participantId) throws Exception {
        for (Participant participant : participants) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean isInvolved(Participant p) {
        if (participants.contains(p)) {
            return true;
        }
        return false;
    }

    /**
     * Set a Round and update it in the Database
     *
     * @param round
     * @throws SQLException
     */
    public void setRound(Round round) throws SQLException {
        this.round = round;
        dc.update("Encounter", "RoundId", round.getId(), id);
    }

    /**
     * set Points and update it in the Database
     *
     * @param points
     * @throws SQLException
     */
    public void setPoints(ArrayList<Integer> points) throws SQLException {
        this.points = points;
        dc.delete("DELETE FROM Points WHERE EncounterId =" + this.id);
        dc.insert(String.format("INSERT INTO Points(ParticipantId, EncounterId, Points) VALUES (%d, %d, %d)", participants.get(0).getId(), id, points.get(0)));
        dc.insert(String.format("INSERT INTO Points(ParticipantId, EncounterId, Points) VALUES (%d, %d, %d)", participants.get(1).getId(), id, points.get(1)));

    }

    /**
     * add a participant and insert it in the database
     *
     * @param participant
     * @throws SQLException
     */
    public void addParticpant(Participant participant) throws SQLException {
        participants.add(participant);
        dc.insert(String.format("INSERT INTO Points (ParticipantId,EncounterId,Points) VALUES (%d,%d,%d)", participant.getId(), id, 0));
    }

    /**
     * removes a Participant and set this in the database
     *
     * @param participant
     * @throws SQLException
     */
    public void deleteParticipant(Participant participant) throws SQLException {
        participants.remove(participant);
        dc.delete(String.format("DELETE FROM Points WHERE ParticipantId = %d AND EncounterId = %d", participant.getId(), id));
    }
    
    public boolean isFinished(){
        boolean finish = true;
        for (Integer i:points){
            if (i < 0){
                finish = false;
            }
        }
        return finish;        
    }

      @Override
    public String toString() {
        return "Encounter{" + "round=" + round.getId() + ", id=" + id + ", #participants=" + participants.size() + ", points=" + points + '}';
    }
    
}

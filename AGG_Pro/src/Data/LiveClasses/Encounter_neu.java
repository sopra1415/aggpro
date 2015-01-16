package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.util.HashMap;
import java.util.HashSet;

/**
 * LiveClass for a Encounter
 *
 */
public class Encounter_neu {

    private Round round;
    private int id;
    ArrayList<Participant> participants = new ArrayList<>();
    HashMap<Integer, Integer> participantPoints = new HashMap<>();//ParticipantId => Points
    private DatabaseConnector dc;

    /**
     * Creates new Encounter and insert it into the database
     *
     * @param dc
     * @param round
     * @throws SQLException
     */
    public Encounter_neu(DatabaseConnector dc, Round round) throws SQLException {
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
    public Encounter_neu(DatabaseConnector dc, int id, Round round, ArrayList<Participant> AllPaticipants) throws SQLException {
        this.id = id;
        this.round = round;
        ResultSet rs = dc.select("SELECT ParticipantId,Points FROM Points WHERE EncounterId = " + id);
        while (rs.next()) {
            int participantId = rs.getInt(1);
            int points = rs.getInt(2);
            for (Participant participant : participants) {
                if (participant.getId() == participantId) {
                    this.participants.add(participant);
                    participantPoints.put(participantId, points);
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

    public Participant getParticipant(int participantId) {
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

    public int getPoints(Participant participant) {
        return participantPoints.get(participant.getId());
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
    public void setPoints(Participant participant, Integer points) throws SQLException {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
        if (participantPoints.containsKey(participant)) {
            //Update
            dc.insert(String.format("INSERT INTO Points(ParticipantId, EncounterId, Points) VALUES (%d, %d, %d)", participant.getId(), id, points));
        } else {
            //insert
            dc.update(String.format("UPDATE Points SET Points = %s WHERE EncounterId = %s AND ParticipantId = %s", points, id, participant.getId()));
        }
        participantPoints.put(participant.getId(), points);
    }

    /**
     * add a participant and insert it in the database
     *
     * @param participant
     * @throws SQLException
     */
    public void addParticpant(Participant participant) throws SQLException {
        if (!participants.contains(participant)) {
            participants.add(participant);
            dc.insert(String.format("INSERT INTO Points (ParticipantId,EncounterId) VALUES (%d,%d)", participant.getId(), id));
        }
    }

    /**
     * removes a Participant and set this in the database
     *
     * @param participant
     * @throws SQLException
     */
    public void deleteParticipant(Participant participant) throws SQLException {
        if(participants.contains(participant)){
        participants.remove(participant);
        dc.delete(String.format("DELETE FROM Points WHERE ParticipantId = %d AND EncounterId = %d", participant.getId(), id));
        }
    }

    public boolean isFinished() {
        boolean finish = true;
        for (Integer i : participantPoints.keySet()) {
            if (i < 0) {
                finish = false;
            }
        }
        return finish;
    }

    @Override
    public String toString() {
        return "Encounter{" + "round=" + round.getId() + ", id=" + id + ", #participants=" + participants.size() + ", points=" + participantPoints.keySet().size() + '}';
    }

}

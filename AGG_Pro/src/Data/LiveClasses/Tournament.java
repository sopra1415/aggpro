package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Tournament {

    private String name;
    private int id;
    private Modul modul;
    private ArrayList<Participant> participants = new ArrayList<Participant>();
    private ArrayList<Round> rounds = new ArrayList<Round>();
    DatabaseConnector dc;

    /**
     * Creates a new Tournament and Insert it into the database
     *
     * @param dc
     * @param name
     * @param modul
     * @throws SQLException
     */
    public Tournament(DatabaseConnector dc, String name, Modul modul) throws SQLException {
        this.dc = dc;
        this.name = name;
        this.modul = modul;
        this.id = dc.insert("INSERT INTO Tournament(Name, ModulId) VALUES ('" + name + "', '" + modul.getId() + "')");
    }

    /**
     * Load Tournament from Database
     *
     * @param dc
     * @param id
     * @param participants
     * @throws SQLException
     */
    public Tournament(DatabaseConnector dc, Integer id, ArrayList<Participant> participants) throws SQLException {//von db erstellen
        this.id = id;
        ResultSet rs = dc.select("SELECT name,modulId FROM Tournament WHERE Id = " + id);
        rs.next();
        this.name = rs.getString(1);
        Integer modulId = rs.getInt(2);
        this.modul = new Modul(dc, modulId);

        rs = dc.select("SELECT Id FROM Round WHERE TournamentId = " + id);
        while (rs.next()) {
            int roundId = rs.getInt(1);
            rounds.add(new Round(dc, roundId, this, participants));
        }
        this.dc = dc;

        //add Participants
        rs = dc.select("SELECT ParticipantId FROM ParticipantList WHERE TournamentId = " + id);
        while (rs.next()) {
            Integer participantId = rs.getInt(1);
            for (Participant participant : participants) {
                if (participant.getId() == participantId) {
                    participant.addTournament(this);
                    //this.participants.add(participant);
                    addParticipant(participant);
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

    public Modul getModul() {
        return modul;
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

    public Round getRound(int roundId) throws Exception {
        for (Round round : rounds) {
            if (round.getId() == roundId) {
                return round;
            }
        }
        return null;
    }

    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }

    public int getRankOfParticipant(Participant player, final Round newRound) {
        ArrayList<Participant> ranking = new ArrayList<>();

        for (Participant p : participants) {
            ranking.add(p);
        }

        Collections.sort(ranking, new Comparator<Participant>() {

            @Override
            public int compare(Participant p1, Participant p2) {
                System.out.println(getScore(p2, newRound));
                System.out.println(getScore(p1, newRound));
                int compareValue = getScore(p2, newRound) - getScore(p1, newRound);
                if (compareValue != 0) {
                    return compareValue;
                } else {
                    return getSecondaryScore(p2, newRound) - getSecondaryScore(p1, newRound);
                }
            }
        });

        return ranking.indexOf(player) + 1;
    }

    public int getNumberOfPlayedGames(Participant p) {
        //TODO implement
        int counter = 0;
        for (Round r : rounds) {
            for (Encounter e : r.getEncounters()) {
                if (e.isInvolved(p)) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setName(String name) throws SQLException {
        this.name = name;
        dc.update("Tournament", "Name", name, id);

    }

    public void setModul(Modul modul) throws SQLException {
        this.modul = modul;
        dc.update("Tournament", "Modul", modul.getId() + "", id);

    }

    public void addParticipant(Participant participant) throws SQLException {
        if (!participants.contains(participant)) {
            participants.add(participant);
            participant.addTournament(this);
            //dc.insert("INSERT INTO ParticipantList(ParticipantId, TournamentId) VALUES(" + participant.getId() + ", " + id + ")");    
        }
        ResultSet rs = dc.select(String.format("SELECT Id FROM ParticipantList WHERE ParticipantId = %d AND TournamentId = %d", participant.getId(), id));
        if (!rs.next()) {
            dc.insert(String.format("INSERT INTO ParticipantList(ParticipantId, TournamentId) VALUES (%d, %d)", participant.getId(), id));
        }

    }

    /**
     * Add Participant and don't write it into the Database
     *
     * @param participant
     */
    public void addParticipantInit(Participant participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

    public void deleteParticipant(Participant participant) throws SQLException {
        participants.remove(participant);
        dc.delete(String.format("DELETE FROM ParticipantList WHERE ParticipantId = %d AND TournamentID = %d", participant.getId(), id));
    }

    public void addRound(Round round) throws SQLException {
        rounds.add(round);
        //TODO überprüfen (ich denke, dass es doppelt ist)
        //dc.insert(String.format("INSERT INTO Round (TournamentId,Round) VALUES (%d,%d) ", id, round.getRound()));
    }

    public void deleteRound(Round round) throws SQLException {
        rounds.remove(round);
        dc.delete(String.format("DELETE FROM Round WHERE TournamentId = %d AND Round = %d", id, round));
    }

    public boolean actualRoundOver() {
        boolean finished = true;
        Round lastRound = rounds.get(rounds.size() - 1);
        ArrayList<Encounter> allEncounters = lastRound.getEncounters();
        if (!allEncounters.isEmpty()) {
            for (Encounter e : allEncounters) {
                // check, if every encounter has finished properly
                if (!e.isFinished()) {
                    finished = false;
                }
            }
        }
        return finished;
    }

    public void generateNextRound() {
        System.out.println("generate next round");

        if (rounds.isEmpty()) {
            generateRandomEncounters();
        } else if (actualRoundOver()) {
            if (this.getModul().getTournamentSystem(rounds.size()) == null) {
                System.err.println("Turnier hat kein valides Turniersystem");
            } else if (this.getModul().getTournamentSystem(rounds.size()) == TournamentSystem.system.swissSystem) {
                generateSwissSystem();
            } else if (this.getModul().getTournamentSystem(rounds.size()) == TournamentSystem.system.koSystem) {
                generateKoSystem();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Bitte trage zunächst alle Punkte ein");
        }

        System.out.println("generation finished");
    }

    private void generateSwissSystem() {
        try {
            Round newRound = new Round(dc, this, rounds.size() + 1);
            this.addRound(newRound);
            Round lastRound = rounds.get(rounds.size() - 2);

            //Round lastRound = rounds.get(rounds.size() - 1);
            ArrayList<Participant> nextRoundPlayers = new ArrayList<Participant>();
            // get all Players of the next round in swiss System
            if (this.getRoundsOfSameTournamentSystem(newRound).contains(lastRound)) {
                // take players of the round before
                for (Encounter e : lastRound.getEncounters()) {
                    // check if there is a freelos player
                    nextRoundPlayers.add(e.getParticipants().get(0));
                    nextRoundPlayers.add(e.getParticipants().get(1));
                }
            } else {
                SwissSystem actualSystem = (SwissSystem) modul.getTouramentSystem(lastRound.getRound());
                for (int i = 1; i <= actualSystem.getNumberOfPlayersAfterCut(); i++) {
                    nextRoundPlayers.add(this.getPlayerWithRanking(i, lastRound));
                }
            }

            ArrayList<ArrayList<Participant>> swissSystem = nextRoundSwissSystemOpponents(nextRoundPlayers, newRound);
            Encounter nextEncounter;
            for (ArrayList<Participant> ap : swissSystem) {
                Participant playerBevore = null;
                for (Participant p : ap) {
                    if (playerBevore == null) {
                        playerBevore = p;
                    } else {
                        nextEncounter = new Encounter(dc, newRound);
                        nextEncounter.addParticpant(playerBevore);
                        nextEncounter.addParticpant(p);
                        newRound.addEncounter(nextEncounter);
                        playerBevore = null;
                    }
                }
            }
            // swiss System
        } catch (SQLException ex) {
            Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateKoSystem() {
        try {
            Round newRound = new Round(dc, this, rounds.size() + 1);
            this.addRound(newRound);

            Round lastRound = rounds.get(rounds.size() - 2);

            ArrayList<Participant> nextRoundPlayers = new ArrayList<Participant>();

            //iterate all participating Players, and check which one has won the 1vs1
            for (Encounter e : lastRound.getEncounters()) {
                if (e.getPoints().get(0) == getModul().getPointsWin()) {
                    nextRoundPlayers.add(e.getParticipants().get(0));
                } else {
                    nextRoundPlayers.add(e.getParticipants().get(1));
                }
            }
            int counter = 0;
            Participant playerBefore = null;

            // add always 2 players together in a new encounter
            Encounter nextEncounter;
            for (Participant p : nextRoundPlayers) {

                if (counter % 2 == 0) {
                    playerBefore = p;
                } else {

                    nextEncounter = new Encounter(dc, newRound);
                    nextEncounter.addParticpant(playerBefore);
                    nextEncounter.addParticpant(p);
                    newRound.addEncounter(nextEncounter);

                }
                counter++;
            }

            if (playerBefore != null) {
                // einem Spieler ein Freilos geben, !nicht zwingend dem letzten!
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateRandomEncounters() {

        try {
            Round newRound = new Round(dc, this, 1);
            this.addRound(newRound);
            //generate random Encounters for the Start
            Participant playerBevore = null;
            Encounter nextEncounter;
            for (Participant p : participants) {
                if (playerBevore == null) {
                    playerBevore = p;
                } else {
                    nextEncounter = new Encounter(dc, newRound);
                    nextEncounter.addParticpant(playerBevore);
                    nextEncounter.addParticpant(p);

                    // preinitialize the encounter-points with default values
                    ArrayList<Integer> points = new ArrayList<Integer>();
                    points.add(-1);
                    points.add(-1);
                    nextEncounter.setPoints(points);

                    newRound.addEncounter(nextEncounter);
                    playerBevore = null;
                }
            }

            if (playerBevore != null) {
                //letztem Spieler Freilos geben
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ArrayList<ArrayList<Participant>> nextRoundSwissSystemOpponents(ArrayList<Participant> players, final Round newRound) {
        //
        ArrayList<ArrayList<Participant>> ranking = new ArrayList<ArrayList<Participant>>();

        Collections.sort(players, new Comparator<Participant>() {

            @Override
            public int compare(Participant t, Participant t1) {
                int compareValue = getScore(t1, newRound) - getScore(t, newRound);
                if (compareValue != 0) {
                    return compareValue;
                } else {
                    return getSecondaryScore(t1, newRound) - getSecondaryScore(t, newRound);
                }
            }
        });

        //create the list with lists of 'skill'
        Participant playerBevore = null;
        Boolean start = false;
        for (Participant p : players) {
            if (start) {
                if (getScore(playerBevore, newRound) == getScore(p, newRound)) {

                } else {
                    ranking.add(new ArrayList<Participant>());
                }
            } else {
                start = true;
                ranking.add(new ArrayList<Participant>());
            }
            ranking.get(ranking.size() - 1).add(p);
            playerBevore = p;
        }

        //balance the lists within the list (so there are only even numbers of players)
        Participant movingPlayer = null;
        for (ArrayList<Participant> list : ranking) {
            if (movingPlayer != null) {
                list.add(0, movingPlayer);
                movingPlayer = null;
            }
            if (list.size() % 2 != 0) {
                movingPlayer = list.get(list.size() - 1);
                list.remove(movingPlayer);
            }
        }
        if (movingPlayer != null) {
            ranking.get(ranking.size() - 1).add(movingPlayer);

            // Freilos handlen
        }
        return ranking;
    }

    /**
     * calculates the primary score of a player until a given round
     *
     * @param player
     * @param r
     * @return
     */
    public int getScore(Participant player, Round r) {
        //check if this method works for a swiss System after an other swiss System
        ArrayList<Round> allRoundsOfTheSystem = getRoundsOfSameTournamentSystem(r);
        int sum = 0;
        for (Round iterateRound : allRoundsOfTheSystem) {
            // stopps at the given round
            if (iterateRound.getId() == r.getId()) {
            } else {
                for (Encounter iterateEncounter : iterateRound.getEncounters()) {
                    //search for the player
                    if (iterateEncounter.getParticipants().contains(player)) {

                        if (iterateEncounter.getParticipants().get(0) == player) {
                            sum += iterateEncounter.getPoints().get(0);
                            break;
                        } else if (iterateEncounter.getParticipants().get(1) == player) {
                            sum += iterateEncounter.getPoints().get(1);
                            break;
                        }
                    }
                }
            }
        }
        return sum;
    }

    /**
     * calculates the secondary score of an player until an given round
     *
     * @param player
     * @param r
     * @return
     */
    public int getSecondaryScore(Participant player, Round r) {
        ArrayList<Round> allRoundsOfTheSystem = getRoundsOfSameTournamentSystem(r);
        int sum = 0;
        for (Round iterateRound : allRoundsOfTheSystem) {
            // stopps at the given round
            if (iterateRound == r) {
                break;
            }
            for (Encounter iterateEncounter : iterateRound.getEncounters()) {
                if (iterateEncounter.getParticipants().contains(player)) {

                    if (iterateEncounter.getParticipants().get(0) == player) {
                        sum += getScore(iterateEncounter.getParticipants().get(1), r);
                        break;
                    } else if (iterateEncounter.getParticipants().get(1) == player) {
                        sum += getScore(iterateEncounter.getParticipants().get(0), r);
                        break;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * returns a list with all rounds, which are in the same tournamentsystem
     * like the given round
     *
     * @param r
     * @return
     */
    private ArrayList<Round> getRoundsOfSameTournamentSystem(Round r) {

        ArrayList<Round> roundsOfSameSystem = new ArrayList<Round>();
        //ArrayList<TournamentSystem> allSystems = getModul().getTournamentSystems();
        roundsOfSameSystem.add(r);

        for (int i = rounds.indexOf(r) - 1; i >= 0; i--) {
            if (modul.getTouramentSystem(r.getRound()) != modul.getTouramentSystem(i)) {
                break;
            } else {
                roundsOfSameSystem.add(rounds.get(i));
            }
        }
        return roundsOfSameSystem;
    }

    public int getProgressOfTournament() {
        try {
            int numberOfRounds = 0;
            for (TournamentSystem ts : modul.getTournamentSystems()) {
                try {
                    numberOfRounds += ((SwissSystem) ts).getNumberOfRounds();
                } catch (ClassCastException e) {
                    numberOfRounds += (int) Math.log(((KoSystem) ts).getNumberOfPlayers()) / Math.log(2.0);
                    break;
                }
            }
            return (100 / numberOfRounds) * (rounds.size() - 1);
        } catch (NullPointerException | ArithmeticException e) {
            return 0;
        }
    }

    /**
     * Returns the participant, who holds the rank in the given round
     *
     * @param rank
     * @param lastRound the last finished Round
     * @return
     */
    private Participant getPlayerWithRanking(int rank, Round lastRound) {
        for (Participant p : participants) {
            if (this.getRankOfParticipant(p, lastRound) == rank) {
                return p;
            }
        }
        return null;
    }

}

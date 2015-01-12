package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;
import java.util.Collection;
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

    public Tournament(DatabaseConnector dc, String name, Modul modul) throws SQLException {
        super();
        this.dc = dc;
        this.name = name;
        this.modul = modul;
        this.id = dc.insert("INSERT INTO Tournament(Name, ModulId) VALUES ('" + name + "', '" + modul.getId() + "')");
    }

    public Tournament(DatabaseConnector dc, Integer id) throws SQLException {//von db erstellen
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
		//TODO rounds
        //participants werden in Participants gesetzt

    }
    // getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name = name;
        dc.update("Tournament", "Name", name, id);

    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) throws SQLException {
        this.modul = modul;
        dc.update("Tournament", "Modul", modul.getId() + "", id);

    }

    public Participant getParticipant(int participantId) throws Exception {
        for (Participant participant : participants) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }

    public void addParticipant(Participant participant) throws SQLException {
        participants.add(participant);
        dc.insert("INSERT INTO ParticipantList(ParticipantId, TournamentId) VALUES(" + participant.getId() + ", " + id + ")");
    }

    public void addParticipantInit(Participant participant) {
        participants.add(participant);
    }

    public void deleteParticipant(Participant participant) throws SQLException {
        participants.remove(participant);
        dc.delete(String.format("DELETE FROM ParticipantList WHERE ParticipantId = %d AND TournamentID = %d", participant.getId(), id));
    }

    public Round getRound(int roundId) throws Exception {
        for (Round round : rounds) {
            if (round.getId() == roundId) {
                return round;
            }
        }
        return null;
    }

    public void addRound(Round round) throws SQLException {
        rounds.add(round);
        dc.insert(String.format("INSERT INTO Round (TournamentId,Round) VALUES (%d,%d) ", id, round));
    }

    public void deleteRound(Round round) throws SQLException {
        rounds.remove(round);
        dc.delete(String.format("DELETE FROM Round WHERE TournamentId = %d AND Round = %d", id, round));
    }

    public ArrayList<Participant> getParticipants() {
        return this.participants;
    }

    public int getRankOfParticipant(Participant p) {
        //TODO implement            
        return 0;
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

    public boolean actualRoundOver() {
        boolean finished = true;
        Round lastRound = rounds.get(rounds.size() - 1);
        ArrayList<Encounter> allEncounters = lastRound.getEncounters();
        if (!allEncounters.isEmpty()) {
            for (Encounter e : allEncounters) {
                // check, if encounter has finished properly
                if (e.getPoints().size() != 2) {
                    finished = false;
                }
            }
        }
        return finished;
    }

    public void generateNextRound() {
        Round lastRound = rounds.get(rounds.size() - 1);
        int iLastRound = rounds.size() - 1;
        if (actualRoundOver()) {
            try {
                Round newRound = new Round(dc, this, rounds.size());
                this.addRound(newRound);

                ArrayList<Participant> nextRoundPlayers = new ArrayList<Participant>();

                if (this.getModul().getTournamentSystem(rounds.size()) == null) {
                    //
                } else if (this.getModul().getTournamentSystem(rounds.size()) == TournamentSystem.system.swissSystem) {
                    // neues schweizer System
                    
                    //TODO nextRoundPlayers füllen
                    
                    ArrayList<ArrayList<Participant>> swissSystem = nextRoundSwissSystem(nextRoundPlayers, lastRound);
                    Encounter nextEncounter;
                    for (ArrayList<Participant> ap:swissSystem){
                        Participant playerBevore = null;
                        for (Participant p:ap){
                            if (playerBevore==null){
                                playerBevore = p;
                            } else {
                                nextEncounter = new Encounter(dc, newRound);
                                nextEncounter.addParticpant(playerBevore);
                                nextEncounter.addParticpant(p);
                                newRound.addEncounter(nextEncounter);
                                playerBevore=null;
                            }
                        }
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                } else if (this.getModul().getTournamentSystem(rounds.size()) == TournamentSystem.system.koSystem) {
                    


                    // neue Runde im KO-System
                    //iterate all participating Players, and check which one has won the 1vs1
                    for (Encounter e : lastRound.getEncounters()) {
                        if (e.getPoints().get(0) == getModul().getPointsWin()) {
                            nextRoundPlayers.add(e.getParticipants().get(0));
                        } else {
                            nextRoundPlayers.add(e.getParticipants().get(1));
                        }
                    }
                    int counter = 0;
                    Participant temp = null;

                    // add always 2 players together in a new encounter
                    Encounter nextEncounter;
                    for (Participant p : nextRoundPlayers) {

                        if (counter % 2 == 0) {
                            temp = p;
                        } else {

                            nextEncounter = new Encounter(dc, newRound);
                            nextEncounter.addParticpant(temp);
                            nextEncounter.addParticpant(p);
                            newRound.addEncounter(nextEncounter);

                        }
                        counter++;
                    }
                    
                    
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Bitte trage zunächst alle Punkte ein");
        }
    }
    
    private ArrayList<ArrayList<Participant>> nextRoundSwissSystem(ArrayList<Participant> players, final Round lastRound){
        //
        ArrayList<ArrayList<Participant>> ranking = new ArrayList<ArrayList<Participant>>();
        
        Collections.sort(players, new Comparator<Participant>() {

            @Override
            public int compare(Participant t, Participant t1) {
                int compareValue = getScore(t, lastRound) - getScore(t1, lastRound);
                if (compareValue!=0){
                    return  compareValue;
                } else {
                    return getSecondaryScore(t, lastRound) - getSecondaryScore(t1, lastRound);
                }
            }
        });
        
        //create the list with lists of 'skill'
        Participant playerBevore=null;
        Boolean start = false;
        for (Participant p:players){
            if (start){
                if (getScore(playerBevore, lastRound)==getScore(p, lastRound)){
                    
                } else {
                    ranking.add(new ArrayList<Participant>());
                }
            } else {
                start = true;
                ranking.add(new ArrayList<Participant>());
            }
            ranking.get(ranking.size()-1).add(p);
            playerBevore = p;
        }
        
        //balance the lists within the list (only even numbers of players)
        Participant movingPlayer = null;
        for (ArrayList<Participant> list:ranking){
            if (movingPlayer != null){
                list.add(0, movingPlayer);
                movingPlayer = null;
            }
            if (list.size()%2!=0){
                movingPlayer = list.get(list.size()-1);
            }
        }
        if (movingPlayer!=null){
            ranking.get(ranking.size()-1).add(movingPlayer);
        }
        return ranking;
    }
    
    public int getScore(Participant player, Round r){
        ArrayList<Round> allRoundsOfTheSystem = getRoundsOfSameTournamentSystem(r);
        int sum = 0;
        for (Round iterateRound:allRoundsOfTheSystem){
            for (Encounter iterateEncounter:iterateRound.getEncounters()){
                if (iterateEncounter.getParticipants().contains(player)){
                    
                    if (iterateEncounter.getParticipants().get(0)==player){
                        sum += iterateEncounter.getPoints().get(0);
                        break;
                    }else  if (iterateEncounter.getParticipants().get(1) == player){
                        sum += iterateEncounter.getPoints().get(1);
                        break;
                    }
                }
            }
        }
        return sum;
    }
    
    /*
    public int compare(Participant t, Participant t1, Round lastRound) {
            return getScore(t, lastRound) - getScore(t1, lastRound);
        }
    */
    public int getSecondaryScore(Participant player, Round r){
        ArrayList<Round> allRoundsOfTheSystem = getRoundsOfSameTournamentSystem(r);
        int sum = 0;
        for (Round iterateRound:allRoundsOfTheSystem){
            for (Encounter iterateEncounter:iterateRound.getEncounters()){
                if (iterateEncounter.getParticipants().contains(player)){
                    
                    if (iterateEncounter.getParticipants().get(0)==player){
                        sum +=  getScore(iterateEncounter.getParticipants().get(1), r);
                        break;
                    }else  if (iterateEncounter.getParticipants().get(1) == player){
                        sum +=  getScore(iterateEncounter.getParticipants().get(0), r);
                        break;
                    }
                }
            }
        }
        return sum;
    }

    
    private ArrayList<Round> getRoundsOfSameTournamentSystem(Round r){
        
        ArrayList<Round> roundsOfSameSystem = new ArrayList<Round>();
        //ArrayList<TournamentSystem> allSystems = getModul().getTournamentSystems();
        roundsOfSameSystem.add(r);
        
        for (int i= rounds.indexOf(r); i>=0; i--){
            if (r.getEncounters().size()!=rounds.get(i).getEncounters().size()){
                break;
            } else {
                roundsOfSameSystem.add(rounds.get(i));
            }
        }
        return roundsOfSameSystem;
    }
    

}

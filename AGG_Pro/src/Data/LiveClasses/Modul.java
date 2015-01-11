package Data.LiveClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Database.DatabaseConnector;

public class Modul {

    private String name;
    private int id;
    private DatabaseConnector dc;
    private int pointsWin;
    private int pointsLoose;
    private int pointsDraw;
    private ArrayList<TournamentSystem> tournamentsystems = new ArrayList<TournamentSystem>();
    private SwissSystem castedSwissSystem;
    private KoSystem casKoSystem;

    public Modul(DatabaseConnector dc, String name, int pointsWin, int pointsLoose,
            int pointsDraw, ArrayList<TournamentSystem> tournamentSystems) throws SQLException {

        this.tournamentsystems = tournamentSystems;

        this.name = name;
        this.dc = dc;
        this.pointsWin = pointsWin;
        this.pointsLoose = pointsLoose;
        this.pointsDraw = pointsDraw;
        this.id = dc.insert(String.format("INSERT INTO Modul (Name,PointsWin,PointsLoose,PointsDraw) VALUES ('%s',%d,%d,%d) ", name, pointsWin, pointsLoose, pointsDraw));
    }

    public Modul(DatabaseConnector dc, Integer id) throws SQLException {
        this.id = id;
        ResultSet rs = dc.select("SELECT Name,PointsWin,PointsLoose,PointsDraw FROM Modul WHERE id = " + id);
        rs.next();
        this.name = rs.getString(1);
        this.pointsWin = rs.getInt(2);
        this.pointsLoose = rs.getInt(3);
        this.pointsDraw = rs.getInt(4);

        rs = dc.select("SELECT TournamentsystemId,SwissSystem FROM ModulList WHERE ModulId = " + id + " ORDER BY SortOrder");
        while (rs.next()) {
            int tournamentsystemId = rs.getInt(1);
            boolean swissSystem = rs.getBoolean(2);
            if (swissSystem) {
                ResultSet rsSwiss = dc.select("SELECT NumberOfPlayersAfterCut, NumberOfRounds, CUT FROM swissSystem WHERE id = " + tournamentsystemId);
                rsSwiss.next();
                int numberOfPlayersAfterCut = rsSwiss.getInt(1);
                int numberOfRounds = rsSwiss.getInt(2);
                int cut = rsSwiss.getInt(3);
                this.tournamentsystems.add(new SwissSystem(name, numberOfPlayersAfterCut, numberOfRounds, tournamentsystemId, cut));
            } else {
                ResultSet rsKO = dc.select("SELECT DoubleKO,NumberOfPlayers FROM  KoSystem WHERE id = " + tournamentsystemId);
                rsKO.next();
                boolean doubleKO = rsKO.getBoolean(1);
                int numberOfPlayers = rsKO.getInt(2);
                this.tournamentsystems.add(new KoSystem(name, numberOfPlayers, doubleKO, tournamentsystemId));
            }

        }

    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name = name;
        dc.update("Modul", "Name", name, id);
    }

    public int getId() {
        return id;
    }

    public int getPointsWin() {
        return pointsWin;
    }

    public void setPointsWin(int pointsWin) throws SQLException {
        this.pointsWin = pointsWin;
        dc.update("Modul", "PointsWin", pointsWin, id);
    }

    public int getPointsLoose() {
        return pointsLoose;
    }

    public void setPointsLoose(int pointsLoose) throws SQLException {
        this.pointsLoose = pointsLoose;
        dc.update("Modul", "PointsLoose", pointsLoose, id);
    }

    public int getPointsDraw() {
        return pointsDraw;
    }

    public void setPointsDraw(int pointsDraw) throws SQLException {
        this.pointsDraw = pointsDraw;
        dc.update("Modul", "PointsDraw", pointsDraw, id);
    }
    // Die komplette Kombination der TournamentSystems wird überschrieben
    public void setTournamentSystems(ArrayList<TournamentSystem> tournamentSystems) throws SQLException {
        this.tournamentsystems = tournamentSystems;
        //Alle Einträge der zugehörigen Tuniersysteme werden gelöscht
        dc.delete("DELETE * FROM Modullist WHERE Modulid ="+this.id);
        boolean isSwissSystem = false;
        for (int i = 1; i <= tournamentsystems.size(); i++) {
            TournamentSystem tournamentSystem = tournamentsystems.get(i);
            // Dirty switch case, ob es sich um ein Swiss System oder ein ḰO System handelt
            try {
                SwissSystem castedSwissSystem = (SwissSystem) tournamentSystem;
                dc.insert(String.format("INSERT INTO swissSystem(NumberOfPlayersAfterCut, NumberOfRounds, Cut) VALUES(%d, %d)", castedSwissSystem.getNumberOfPlayersAfterCut(), castedSwissSystem.getNumberOfRounds(), castedSwissSystem.getCut()));
                isSwissSystem = true;
            } catch (Exception e) {
                isSwissSystem = false;
                KoSystem castedKoSystem = (KoSystem) tournamentSystem;
                dc.insert(String.format("INSERT INTO KoSystem(DoubleKO, NumberOfPlayers, ParticipantCount) VALUES (%s, %d)", castedKoSystem.isDoubleKO() + "", castedKoSystem.getNumberOfPlayers(), castedKoSystem.getNumberOfPlayers()));

            } finally {
                if (isSwissSystem) {
                    dc.insert(String.format("INSERT INTO ModulList(ModulId, TournamentSystemId, SwissSystem, SortOrder) "
                            + "VALUES (%d, %d, %d, %d, %d)", this.getId(), tournamentSystem.getId(), isSwissSystem, i));
                }
            }
        }
    }

}

package Data.LiveClasses;

public abstract class TournamentSystem {
    public enum system {
        swissSystem, koSystem
    }
    private String name;
    protected int id;
/**
 * Constructor without Databaseconnection
 * @param name 
 */
    public TournamentSystem(String name) {
        this.name = name;
    }

	// getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

}

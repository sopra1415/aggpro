package Data.LiveClasses;

import java.util.ArrayList;

public class Modul {
	private String name;
	private int Id;
	private int primaryPoints;
	private int secondaryPoints;
	private int tertiaryPoints;
	//TODO: Darüber müssen wir nochmal reden ...
	private SwissSystem swissSystem;
	private KoSystem koSystem; 

	// getters and setters

	public SwissSystem getSwissSystem() {
		return swissSystem;
	}

	public void setSwissSystem(SwissSystem swissSystem) {
		this.swissSystem = swissSystem;
	}

	public KoSystem getKoSystem() {
		return koSystem;
	}

	public void setKoSystem(KoSystem koSystem) {
		this.koSystem = koSystem;
	}

	public Modul(String name, int id, int primaryPoints, int secondaryPoints,
			int tertiaryPoints) {
		super();
		this.name = name;
		Id = id;
		this.primaryPoints = primaryPoints;
		this.secondaryPoints = secondaryPoints;
		this.tertiaryPoints = tertiaryPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getPrimaryPoints() {
		return primaryPoints;
	}

	public void setPrimaryPoints(int primaryPoints) {
		this.primaryPoints = primaryPoints;
	}

	public int getSecondaryPoints() {
		return secondaryPoints;
	}

	public void setSecondaryPoints(int secondaryPoints) {
		this.secondaryPoints = secondaryPoints;
	}

	public int getTertiaryPoints() {
		return tertiaryPoints;
	}

	public void setTertiaryPoints(int tertiaryPoints) {
		this.tertiaryPoints = tertiaryPoints;
	}
	
}

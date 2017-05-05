package be.vdab.entities;


public class Genres {
	private long id;
	private String naam;
	
	public Genres(long id, String naam) {
		setId(id);
		setNaam(naam);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	
	
}

package be.vdab.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Voorstellingen {
	private long id;
	private String titel;
	private String uitvoerders;
	private Timestamp datum;
	private long genreid;
	private BigDecimal prijs;
	private long vrijeplaatsen;
	
	
	public Voorstellingen(long id, String titel, String uitvoerders, Timestamp datum, long genreid, BigDecimal prijs, long vrijeplaatsen) {
		setId(id);
		setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(datum);
		setGenreid(genreid);
		setPrijs(prijs);
		setVrijeplaatsen(vrijeplaatsen);
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


	public String getUitvoerders() {
		return uitvoerders;
	}


	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}


	public Timestamp getDatum() {
		return datum;
	}


	public void setDatum(Timestamp datum) {
		this.datum = datum;
	}


	public long getGenreid() {
		return genreid;
	}


	public void setGenreid(long genreid) {
		this.genreid = genreid;
	}


	public BigDecimal getPrijs() {
		return prijs;
	}


	public void setPrijs(BigDecimal prijs) {
		if ( ! isPrijsValid(prijs)) {
			throw new IllegalArgumentException();
		}
		this.prijs = prijs;
	}
	
	public static boolean isPrijsValid(BigDecimal prijs) {
		return prijs != null && prijs.compareTo(BigDecimal.ZERO) >= 0;
	}

	public long getVrijeplaatsen() {
		return vrijeplaatsen;
	}


	public void setVrijeplaatsen(long vrijeplaatsen) {
		this.vrijeplaatsen = vrijeplaatsen;
	}
	
	

	
	
}

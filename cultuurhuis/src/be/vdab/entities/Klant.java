package be.vdab.entities;


public class Klant {
	private long id;
	private String voornaam;
	private String familienaam;
	private String straat;
	private int huisnr;
	private int postcode;
	private String gemeente;
	private String gebruikersnaam;
	private String paswoord;
	
	
	
	public Klant(long id, String voornaam, String familienaam, String straat, int huisnr, int postcode, String gemeente,
			String gebruikersnaam, String paswoord) {
		super();
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.straat = straat;
		this.huisnr = huisnr;
		this.postcode = postcode;
		this.gemeente = gemeente;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public int getHuisnr() {
		return huisnr;
	}
	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getPaswoord() {
		return paswoord;
	}
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}
	
	public static boolean isGebruikersnaamValid(String gebruikersnaam) {
		return gebruikersnaam != null && !gebruikersnaam.isEmpty();
	}
	
	public static boolean isPaswoordValid(String paswoord) {
		return paswoord != null && !paswoord.isEmpty();
	}	
}

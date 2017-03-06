package Luokat;

public class Kayttaja {
	/**
	 * 
	 */
	public Kayttaja() {
		
	}

	private int kayttaja_id;
	private String tunnus;
	private String salasana;
	
	
	public Kayttaja(int kayttaja_id, String tunnus, String salasana) {
		super();
		this.kayttaja_id = kayttaja_id;
		this.tunnus = tunnus;
		this.salasana = salasana;
	}
	public int getKayttaja_id() {
		return kayttaja_id;
	}
	public void setKayttaja_id(int kayttaja_id) {
		this.kayttaja_id = kayttaja_id;
	}
	public String getTunnus() {
		return tunnus;
	}
	
	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}
	public String getSalasana() {
		return salasana;
	}
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	
	@Override
	public String toString() {
		return "kayttaja [kayttaja_id=" + kayttaja_id + ", tunnus=" + tunnus
				+ ", salasana=" + salasana + "]";
	}

}

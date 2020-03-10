package Luokat;

public class Asiakas {
	private int asiakas_id;
	private String nimi;
	private int puhelinnumero;
	private String spostiosoite;
	/**
	 * 
	 */
	public Asiakas() {
		asiakas_id = 0;
		nimi= null;
		puhelinnumero =0;
		spostiosoite = null;
	}
	/**
	 * @param asiakas_id
	 * @param nimi
	 * @param puhelinnumero
	 * @param spostiosoite
	 */
	public Asiakas(int asiakas_id, String nimi, int puhelinnumero,
			String spostiosoite) {
		super();
		this.asiakas_id = asiakas_id;
		this.nimi = nimi;
		this.puhelinnumero = puhelinnumero;
		this.spostiosoite = spostiosoite;
	}
	public int getAsiakas_id() {
		return asiakas_id;
	}
	public void setAsiakas_id(int asiakas_id) {
		this.asiakas_id = asiakas_id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public int getPuhelinnumero() {
		return puhelinnumero;
	}
	public void setPuhelinnumero(int puhelinnumero) {
		this.puhelinnumero = puhelinnumero;
	}
	public String getSpostiosoite() {
		return spostiosoite;
	}
	public void setSpostiosoite(String spostiosoite) {
		this.spostiosoite = spostiosoite;
	}
	
	public String toString() {
		return "Asiakas [asiakas_id=" + asiakas_id + ", nimi=" + nimi
				+ ", puhelinnumero=" + puhelinnumero + ", spostiosoite="
				+ spostiosoite + "]";
	}
	

}

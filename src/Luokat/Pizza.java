package Luokat;


import java.util.Date;

public class Pizza {
	private int pizza_id;
	private String pizzan_nimi;
	private String taytteet;
	private double hinta;
	private Date poistomerkitty;
	
	/**
	 * 
	 */
	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param pizza_id
	 * @param pizzan_nimi
	 * @param taytteet
	 * @param hinta
	 * @param dateStr
	 */
	public Pizza(int pizza_id, String pizzan_nimi, String taytteet,
			double hinta, Date poistomerkitty) {
		super();
		this.pizza_id = pizza_id;
		this.pizzan_nimi = pizzan_nimi;
		this.taytteet = taytteet;
		this.hinta = hinta;
		this.poistomerkitty = (java.sql.Date) poistomerkitty;
	}
	public int getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}
	public String getPizzan_nimi() {
		return pizzan_nimi;
	}
	public void setPizzan_nimi(String pizzan_nimi) {
		this.pizzan_nimi = pizzan_nimi;
	}
	public String getTaytteet() {
		return taytteet;
	}
	public void setTaytteet(String taytteet) {
		this.taytteet = taytteet;
	}
	public double getHinta() {
		return hinta;
	}
	public void setHinta(double hinta) {
		this.hinta = hinta;
	}
	public Date getPoistomerkitty() {
		return poistomerkitty;
	}
	public void setPoistomerkitty(Date poistomerkitty) {
		this.poistomerkitty = (java.sql.Date) poistomerkitty;
	}
	@Override
	public String toString() {
		return  pizzan_nimi+" "+taytteet +" "+ hinta+"\n";
				
	}
	
	
}

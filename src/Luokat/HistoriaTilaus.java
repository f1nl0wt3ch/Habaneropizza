package Luokat;

import java.util.Date;

public class HistoriaTilaus {
      private int tilausNo;
      private int maara;
      private Double maksut;
      private String maksutapa;
      private String spostiosoite;
      private Date tilauspaiva;
	/**
	 * 
	 */
	public HistoriaTilaus() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param tilausNo
	 * @param maara
	 * @param maksut
	 * @param maksutapa
	 * @param spostiosoite
	 * @param tilauspaiva
	 */
	public HistoriaTilaus(int tilausNo, int maara, Double maksut,
			String maksutapa, String spostiosoite, Date tilauspaiva) {
		super();
		this.tilausNo = tilausNo;
		this.maara = maara;
		this.maksut = maksut;
		this.maksutapa = maksutapa;
		this.spostiosoite = spostiosoite;
		this.tilauspaiva = tilauspaiva;
	}
	public int getTilausNo() {
		return tilausNo;
	}
	public void setTilausNo(int tilausNo) {
		this.tilausNo = tilausNo;
	}
	public int getMaara() {
		return maara;
	}
	public void setMaara(int maara) {
		this.maara = maara;
	}
	public Double getMaksut() {
		return maksut;
	}
	public void setMaksut(Double maksut) {
		this.maksut = maksut;
	}
	public String getMaksutapa() {
		return maksutapa;
	}
	public void setMaksutapa(String maksutapa) {
		this.maksutapa = maksutapa;
	}
	public String getSpostiosoite() {
		return spostiosoite;
	}
	public void setSpostiosoite(String spostiosoite) {
		this.spostiosoite = spostiosoite;
	}
	public Date getTilauspaiva() {
		return tilauspaiva;
	}
	public void setTilauspaiva(Date tilauspaiva) {
		this.tilauspaiva = tilauspaiva;
	}
	@Override
	public String toString() {
		return "HistoraTilaus [tilausNo=" + tilausNo + ", maara=" + maara
				+ ", maksut=" + maksut + ", maksutapa=" + maksutapa
				+ ", spostiosoite=" + spostiosoite + ", tilauspaiva="
				+ tilauspaiva + "]";
	}
	
	
      
}

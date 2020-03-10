package DBHoitaja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Luokat.Asiakas;
import Luokat.HistoriaTilaus;
import Luokat.Pizza;

public class DBHoitaja {

	/**
	 * Habanero pizzarien.
	 *
	 * @author Dinh, Kaisa, Laura, Päivi
	 * @since 14.10.2014
	 * @version 1.0
	 */
	private Statement sta;
	private ResultSet rs;

	/*String driver = "com.mysql.jdbc.Driver";
	String user = "projekti";
	String password = "123456";
	String url = "jdbc:mysql://localhost:3306/projekti";*/
	
	/*Tuotanto Database*/
	String driver = "org.postgresql.Driver";
	String user = "sqrziclkcgwjkr";
	String password = "24c3cb733ba278f974bcde052b79b0e9a484ae8cbfa439eed63b9046ee245e27";
	String url = "jdbc:postgres://ec2-54-225-230-243.compute-1.amazonaws.com:5432/d6uvjq8retv19h";
	
	
	 /**
	 * Yhdistä tietokantaan, jos onnistuu palauttaa oikein, entä ei palauttaa
	 * väärin.
	 *
	 * @see connectDatabase
	 * @return oikein tai väärin
	 */
	public Connection connectDatabase() {
		Connection con;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
				sta = con.createStatement();
				return con;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		
	}

	/**
	 * Lopetta tietokantaan yhditys.
	 *
	 * @see Sulje
	 * @return oikein tai väärin
	 */
	public boolean Sulje(Connection con) {
		boolean paluu = false;
		try {
			con.close();
			paluu = true;

		} catch (SQLException SQLe) {
			paluu = false;
		}
		return paluu;
	}

	/**
	 * Ota kaikki aisakaslista tietokannasta.
	 * 
	 * @see getAsiakkaat
	 * @param table
	 *            , asiakastaulukon nimi tietokannassa.
	 * @return ArrayList, palauttaa asiakaslista.
	 */
	public ArrayList<Asiakas> getAsiakkaat(String table) {
		List<Asiakas> lista = new ArrayList<Asiakas>();
		int asiakas_id, puhelinnumero;
		String nimi, spostiosoite;
		try {

			String query = "select * from " + table;
			rs = sta.executeQuery(query);

			while (rs.next()) {
				asiakas_id = rs.getInt("asiakas_id");
				nimi = rs.getString("nimi");
				puhelinnumero = rs.getInt("puhelinnumero");
				spostiosoite = rs.getString("spostiosoite");
				lista.add(new Asiakas(asiakas_id, nimi, puhelinnumero,
						spostiosoite));
			}
		} catch (Exception e) {
			System.out.print("error " + e);
		} finally {
			Sulje(connectDatabase());
		}
		return (ArrayList<Asiakas>) lista;
	}

	/**
	 * Ota viisi aisakaslista tietokannasta.
	 *
	 * @see getLimitAsiakkaat
	 * @param table
	 *            , asiakastaulukon nimi tietokannassa.
	 * @param limit
	 *            , tarkoittaa määrä joita omistaja haluaa ottaa
	 *            asiakaslistasta.
	 * @return ArrayList, palauttaa asiakaslista.
	 */
	public ArrayList<Asiakas> getLimitAsiakkaat(String table, int limit) {
		List<Asiakas> lista = new ArrayList<Asiakas>();
		int asiakas_id, puhelinnumero;
		String nimi, spostiosoite;
		try {

			String query = "select * from " + table + " limit " + limit;
			rs = sta.executeQuery(query);

			while (rs.next()) {
				asiakas_id = rs.getInt("asiakas_id");
				nimi = rs.getString("nimi");
				puhelinnumero = rs.getInt("puhelinnumero");
				spostiosoite = rs.getString("spostiosoite");
				lista.add(new Asiakas(asiakas_id, nimi, puhelinnumero,
						spostiosoite));
			}

		} catch (Exception e) {
			System.out.print("error " + e);
		} finally {
			Sulje(connectDatabase());
		}

		return (ArrayList<Asiakas>) lista;

	}

	/**
	 * Ota pizzalista tietokannasta.
	 *
	 * @see getPizzat
	 * @param table
	 *            , pizzataulukon nimi tietokannassa.
	 * @return ArrayList, palauttaa pizzalista.
	 */
	public ArrayList<Pizza> getPizzat(String table) {
		List<Pizza> lista = new ArrayList<Pizza>();
		int pizza_id;
		double hinta;
		Date poistomerkitty = null;
		String pizzan_nimi, taytteet;
		try {

			String query = "select * from " + table;
			rs = sta.executeQuery(query);

			while (rs.next()) {
				pizza_id = rs.getInt("pizza_id");
				pizzan_nimi = rs.getString("pizzan_nimi");
				taytteet = rs.getString("taytteet");
				hinta = rs.getDouble("hinta");
				poistomerkitty = rs.getDate("poistomerkitty");
				lista.add(new Pizza(pizza_id, pizzan_nimi, taytteet, hinta,
						poistomerkitty));
			}
		} catch (Exception e) {
			System.out.print("error " + e);
		} finally {
			Sulje(connectDatabase());
		}

		return (ArrayList<Pizza>) lista;

	}

	/**
	 * Ota tilauslista tietokannasta.
	 *
	 * @see getTilaukset
	 * @param table
	 *            , tilaustaulukon nimi tietokannassa.
	 * @return List, palauttaa tilauslista.
	 */
	public List<HistoriaTilaus> getTilaukset(String table) {
		List<HistoriaTilaus> list = new ArrayList<HistoriaTilaus>();
		int tilausNo, maara;
		Double maksut;
		String spostiosoite, maksutapa;
		Date tilauspaiva;
		String query = "select * from " + table;
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				tilausNo = rs.getInt("tilausNo");
				maara = rs.getInt("maara");
				maksut = rs.getDouble("maksut");
				maksutapa = rs.getString("maksutapa");
				spostiosoite = rs.getString("spostiosoite");
				tilauspaiva = rs.getDate("tilauspaiva");
				list.add(new HistoriaTilaus(tilausNo, maara, maksut, maksutapa,
						spostiosoite, tilauspaiva));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
		return list;

	}

	/**
	 * Ota rajoitus tilauslista tietokannasta.
	 *
	 * @see getLimitTilaukset
	 * @param table
	 *            , tilaustaulukon nimi tietokannassa.
	 * @param limit
	 *            , määrä jota omistaja haluaa ottaa.
	 * @return List, palauttaa tilauslista.
	 */
	public List<HistoriaTilaus> getLimitTilaukset(String table, int limit) {
		List<HistoriaTilaus> list = new ArrayList<HistoriaTilaus>();
		int tilausNo, maara;
		Double maksut;
		String spostiosoite, maksutapa;
		Date tilauspaiva;
		String query = "select * from " + table + " limit " + limit;
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				tilausNo = rs.getInt("tilausNo");
				maara = rs.getInt("maara");
				maksut = rs.getDouble("maksut");
				maksutapa = rs.getString("maksutapa");
				spostiosoite = rs.getString("spostiosoite");
				tilauspaiva = rs.getDate("tilauspaiva");
				list.add(new HistoriaTilaus(tilausNo, maara, maksut, maksutapa,
						spostiosoite, tilauspaiva));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
		return list;

	}

	/**
	 * Lisää uusi asiakas tietokantaan.
	 *
	 * @see lisaaAsiakkaat
	 * @param asiakas
	 *            , tarkoittaa asiakkaan olio.
	 * @return boolean, oikein tai väärin arvo.
	 */
	public boolean lisaaAsiakkaat(Asiakas asiakas) {
		String query;
		String nimi = asiakas.getNimi();
		String email = asiakas.getSpostiosoite();
		int puhelin = asiakas.getPuhelinnumero();
		try {
			query = "insert into asiakkaat (nimi,spostiosoite,puhelinnumero) values ('"
					+ nimi + "','" + email + "'," + puhelin + ")";
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return true;

	}

	/**
	 * Lisää uusi pizza tietokantaan.
	 *
	 * @see lisaaPizzat
	 * @param pizza
	 *            , tarkoittaa pizzan olio.
	 * @return boolean, oikein tai väärin arvo.
	 */
	public boolean lisaaPizzat(Pizza pizza) {
		String nimi = pizza.getPizzan_nimi();
		String taytteet = pizza.getTaytteet();
		double hinta = pizza.getHinta();
		Date poistomerkitty = pizza.getPoistomerkitty();
		String query = "insert into pizzat(pizzan_nimi,hinta, taytteet,poistomerkitty) values('"
				+ nimi
				+ "',"
				+ hinta
				+ ",'"
				+ taytteet
				+ "','"
				+ poistomerkitty + "')";
		try {
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

		return true;
	}

	/**
	 * Poista asiakkaan tiedot asiakastaulukosta.
	 *
	 * @see poistaaAsiakas
	 * @param asiakas_id
	 *            , poista asiakas perustuu tähän tietoon.
	 * @return ei mitään
	 */
	public void poistaaAsiakas(int asiakas_id) {

		String query = "delete from asiakkaat where asiakas_id=" + asiakas_id;
		try {
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

	}

	/** Delete row from table pizzat */

	/**
	 * Poista pizzan asiakastaulukosta.
	 *
	 * @see poistaaPizza
	 * @param pizza_id
	 *            , poista pizza perustuu tähän tietoon.
	 * @return ei mitään
	 */
	public void poistaaPizza(int pizza_id) {
		String query = "delete from pizzat where pizza_id=" + pizza_id;
		try {
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
	}

	/**
	 * Tarkistaa omistajan salasana ja käyttäjätunnus ok tai ei.
	 *
	 * @see tarkistaTiedot
	 * @param tunnus
	 *            , syätetty tieto käyttäjältä.
	 * @param table
	 *            , taulukko joisaa omistajan tunnus ja salasana
	 * @return palautta merkkijono
	 */
	public int tarkistaTiedot(String tunnus, String salasana) {
		int lkm = 0;
		String query = "select * from kayttajat where tunnus ='"+ tunnus + "' and salasana='"+salasana+"'";
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				String pass = rs.getString("salasana");
				String user = rs.getString("tunnus");
				if(!pass.equals(null) || !user.equals(null))
					lkm++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

		return lkm;
	}

	/**
	 * Ota salasana kun tiedä tunnus.
	 *
	 * @see selectData
	 * @param username
	 *            , annettu tunnus.
	 * @param table
	 *            , taulukko joisaa omistajan tunnus ja salasana
	 * @return palautta merkkijono
	 */
	public String selectData(String username, String table) {
		String salasana = null;
		String query = "select salasana from " + table + " where tunnus='"
				+ username + "'";

		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				salasana = rs.getString("salasana");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

		return salasana;

	}

	/**
	 * Omistaja voi päivittää mikä tahansa pizzan tieto niin kuin hinta,
	 * taytteet, nimi tai poistomerkitty.
	 *
	 * @see paivitaTiedot
	 * @param pizza
	 *            , pizzaluokan olio.
	 * @param valinta
	 *            , perustuu tähän määritellä kumpi tieto omistaja haluaa
	 *            päivittää.
	 * @return ei mitään
	 */
	public void paivitaTiedot(Pizza pizza, int valinta) {
		String query = null;
		int id = pizza.getPizza_id();

		switch (valinta) {
		case 1:
			String nimi = pizza.getPizzan_nimi();
			query = "update pizzat set pizzan_nimi = '" + nimi
					+ "' where pizza_id= " + id;
			try {
				sta.executeUpdate(query);
			} catch (SQLException sql) {
				sql.printStackTrace();
			} finally {
				Sulje(connectDatabase());
			}
			break;
		case 2:
			String taytteet = pizza.getTaytteet();
			query = "update pizzat set taytteet = '" + taytteet
					+ "' where pizza_id= " + id;
			try {
				sta.executeUpdate(query);
			} catch (SQLException sql) {
				sql.printStackTrace();
			} finally {
				Sulje(connectDatabase());
			}
			break;
		case 3:
			Double hinta = pizza.getHinta();
			query = "update pizzat set hinta = '" + hinta
					+ "' where pizza_id= " + id;
			try {
				sta.executeUpdate(query);
			} catch (SQLException sql) {
				sql.printStackTrace();
			} finally {
				Sulje(connectDatabase());
			}
			break;
		case 4:
			Date date = pizza.getPoistomerkitty();
			query = "update pizzat set poistomerkitty = '" + date
					+ "' where pizza_id= " + id;
			try {
				sta.executeUpdate(query);
			} catch (SQLException sql) {
				sql.printStackTrace();
			} finally {
				Sulje(connectDatabase());
			}
			break;
		default:
			break;

		}

	}

	/**
	 * Ota kaikki tiedot sarakkeesta jossa talukossa.
	 *
	 * @see otaTiedotSarake
	 * @param sarake
	 *            , sarakkeen nimi, joista haluaa ottaa tiedot.
	 * @param taulukko
	 *            , joihin sarake kuuluu
	 * @return List, palautta merkkijonolista
	 */
	public List<String> otaTiedotSarake(String sarake, String taulukko) {
		String edusta;
		List<String> lista = new ArrayList<String>();
		String query = "select " + sarake + " from " + taulukko;
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				edusta = rs.getString(sarake);
				lista.add(edusta);
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

		return lista;
	}

	/**
	 * Ota pizzan hinta perustuu pizzan nimeihin. Voidaan laskea paljonko rahaa
	 * tulee kun asiakas tekee tilaus.
	 *
	 * @see otaPizzanHinta
	 * @param pizza_nimi
	 *            , annettu nimi.
	 * @return Double, paulauttaa pizzan hinta decimaliluku arvo.
	 */
	public Double otaPizzanHinta(String pizza_nimi) {
		Double hinta = 0.0;
		String query = "select hinta from pizzat where pizzan_nimi= '"
				+ pizza_nimi + "'";
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				hinta = Double.parseDouble(rs.getString("hinta"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}

		return hinta;
	}

	/**
	 * Talenta uusi tilaus tietokantaan.
	 *
	 * @see tallentaTilausTietokantaan
	 * @param historia
	 *            , HistoriaTilaus luokan olio.
	 * @return boolean, paulauttaa oikein tai väärin arvo.
	 */
	public boolean tallentaTilausTietokantaan(HistoriaTilaus historia) {
		int tilausNo = historia.getTilausNo();
		int maara = historia.getMaara();
		Double maksut = historia.getMaksut();
		String maksutapa = historia.getMaksutapa();
		String spostiosoite = historia.getSpostiosoite();
		Date tilauspaiva = historia.getTilauspaiva();
		String query = "insert into tilaukset(tilausNo, maara, maksut, maksutapa, spostiosoite, tilauspaiva) value ("
				+ tilausNo
				+ ","
				+ maara
				+ ","
				+ maksut
				+ ",'"
				+ maksutapa
				+ "','" + spostiosoite + "','" + tilauspaiva + "')";
		try {
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return true;
	}

	/**
	 * Tarkistaa asiakas on uusi tai vanha sähkäpostiosoitteella. Perustuu tähän
	 * määritellään kannattaako talentaa asiakkaan tiedot tai ei.
	 *
	 * @see tarkistaAsiakas
	 * @param input_email
	 *            , annettu sähkäposti.
	 * @return Asiakas, paulauttaa Asiakas luokan olio.
	 */
	public Asiakas tarkistaAsiakas(String input_email) {
		String nimi, spostiosoite;
		int asiakas_id, puhelinnumero;
		Asiakas asiakas = null;
		String query = "select * from asiakkaat where spostiosoite='"
				+ input_email + "'";
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				nimi = rs.getString("nimi");
				asiakas_id = rs.getInt("asiakas_id");
				spostiosoite = rs.getString("spostiosoite");
				puhelinnumero = rs.getInt("puhelinnumero");
				asiakas = new Asiakas(asiakas_id, nimi, puhelinnumero,
						spostiosoite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return asiakas;
	}

	/**
	 * Ota tilaustiedot perustuu tilausnumeroon. Perustuu tähän asiakas voi
	 * tarkistaa kaikki historiatilauksensa.
	 *
	 * @see tarkistaTilausTilanne
	 * @param syotetty_numero
	 *            , annettu tilausnumero.
	 * @return HistoriaTilaus, paulauttaa HistoriaTilaus luokan olio.
	 */
	public HistoriaTilaus tarkistaTilausTilanne(int syotetty_numero) {
		String maksutapa, spostiosoite;
		int maara;
		Double maksut;
		Date tilauspaiva;
		HistoriaTilaus historia = new HistoriaTilaus();
		String query = "select * from tilaukset where tilausNo = "
				+ syotetty_numero;
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				maara = rs.getInt("maara");
				maksut = rs.getDouble("maksut");
				maksutapa = rs.getString("maksutapa");
				spostiosoite = rs.getString("spostiosoite");
				tilauspaiva = rs.getDate("tilauspaiva");
				historia = (new HistoriaTilaus(syotetty_numero, maara, maksut,
						maksutapa, spostiosoite, tilauspaiva));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
		return historia;
	}

	/**
	 * Ota asiakkaan tilaus perustuu sähkäpostiin.
	 *
	 * @see tarkistaTilausTilanne
	 * @param syotetty_sposti
	 *            , annettu sähkäposti.
	 * @return List, paulauttaa HistoriaTilaus luokan olio lista.
	 */
	public List<HistoriaTilaus> tarkistaTilausTilanne(String syotetty_sposti) {
		List<HistoriaTilaus> lista = new ArrayList<HistoriaTilaus>();
		String maksutapa;
		int maara, tilausNo;
		Double maksut;
		Date tilauspaiva;
		HistoriaTilaus historia = new HistoriaTilaus();
		String query = "select * from tilaukset where spostiosoite = '"
				+ syotetty_sposti + "'";
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				maara = rs.getInt("maara");
				maksut = rs.getDouble("maksut");
				maksutapa = rs.getString("maksutapa");
				tilausNo = rs.getInt("tilausNo");
				tilauspaiva = rs.getDate("tilauspaiva");
				historia = (new HistoriaTilaus(tilausNo, maara, maksut,
						maksutapa, syotetty_sposti, tilauspaiva));
				lista.add(historia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
		return lista;
	}

	/**
	 * Järjestää tilaustaulukko perustuu tilauspäivään uudesta vanhaan.
	 *
	 * @see jarjestaTaulukko
	 * @param taulukko
	 *            , taulukko jota tarvitaan järjesttää.
	 * @param sarake
	 *            , taulukon sarake tarvitaan järjesttää.
	 * @return List, paulauttaa HistoriaTilaus luokan olio lista.
	 */
	public List<HistoriaTilaus> jarjestaTaulukko(String taulukko, String sarake) {
		List<HistoriaTilaus> list = new ArrayList<HistoriaTilaus>();

		String maksutapa, spostiosoite;
		int tilausNo, maara;
		Double maksut;
		Date tilauspaiva;
		String query = "select * from " + taulukko + " order by " + sarake
				+ " desc";
		try {
			rs = sta.executeQuery(query);
			while (rs.next()) {
				tilausNo = rs.getInt("tilausNo");
				maara = rs.getInt("maara");
				maksut = rs.getDouble("maksut");
				maksutapa = rs.getString("maksutapa");
				spostiosoite = rs.getString("spostiosoite");
				tilauspaiva = rs.getDate("tilauspaiva");
				list.add(new HistoriaTilaus(tilausNo, maara, maksut, maksutapa,
						spostiosoite, tilauspaiva));
			}
			
		} catch (SQLException e) {
			System.out.print("error SQL " + e);
			e.printStackTrace();
		} finally {
			Sulje(connectDatabase());
		}
		return list;
	}

	/**
	 * Päivitä asiakkaan puhelinnumero jos asiakas on vanha mutta sen numero on
	 * vaihdettu.
	 *
	 * @see paivitaPuhelinnumero
	 * @param sarake_paivitetty
	 *            , sarake koostuu tieto jota tarvitaan päivittää.
	 * @param sarake_perustuu
	 *            , perustuu tähän, läydetään tieto tietokannasta.
	 * @param taulukko
	 *            , joissa on kaikki ylevat tiedot.
	 * @param uusiarvo
	 *            , uusi puhelinnumero
	 * @param vanhaarvo
	 *            , vanha puhelinnumero
	 * @return ei mitään.
	 */
	public void paivitaPuhelinnumero(String sarake_paivitetty,
			String sarake_perustuu, String taulukko, String uusiarvo,
			String vanhaarvo) {
		String query = "update " + taulukko + " set " + sarake_paivitetty
				+ "='" + uusiarvo + "' where " + sarake_perustuu + "='"
				+ vanhaarvo + "'";
		try {
			sta.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}

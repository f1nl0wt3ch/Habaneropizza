package Luokat;

import java.util.List;

public class Tilaukset {
	private List<String> pizzoja;
	private List<Integer> maaria;
	
	
	/**
	 * 
	 */
	public Tilaukset() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param pizzoja
	 * @param maaria
	 */
	public Tilaukset(List<String> pizzoja, List<Integer> maaria) {
		super();
		this.pizzoja = pizzoja;
		this.maaria = maaria;
	}

	public List<String> getPizzoja() {
		return pizzoja;
	}
	public void setPizzoja(List<String> pizzoja) {
		this.pizzoja = pizzoja;
	}
	public List<Integer> getMaaria() {
		return maaria;
	}
	public void setMaaria(List<Integer> maaria) {
		this.maaria = maaria;
	}

	@Override
	public String toString() {
		return "Tilaus pizzoja=" + pizzoja + ", maaria=" + maaria;
	}
	
	

}

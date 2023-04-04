
package org.sirketimiz027.overriding;


public class Leylek extends Kuslar{
	
	private String cinsi;

	public Leylek() {
	}

	public Leylek(String cinsi, String rengi, boolean gocDurumu, double yasamUzunluguOrtalaması, int yumutlamaMevsimi) {
		super(rengi, gocDurumu, yasamUzunluguOrtalaması, yumutlamaMevsimi);
		this.cinsi = cinsi;
	}

	public String getCinsi() {
		return cinsi;
	}

	public void setCinsi(String cinsi) {
		this.cinsi = cinsi;
	}
	
	
	
	@Override
	protected String kusunBilgisiniYaz(){
	
			return super.kusunBilgisiniYaz()+"Leylek cinsi"+this.cinsi+"\n";
	}
}

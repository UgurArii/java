
package org.sirketimiz027.overriding;

public class Kuslar {
	private String rengi;
	private boolean gocDurumu;
	private double yasamUzunluguOrtalaması;
	private int yumutlamaMevsimi;

	public Kuslar() {
	}

	public Kuslar(String rengi, boolean gocDurumu, double yasamUzunluguOrtalaması, int yumutlamaMevsimi) {
		this.rengi = rengi;
		this.gocDurumu = gocDurumu;
		this.yasamUzunluguOrtalaması = yasamUzunluguOrtalaması;
		this.yumutlamaMevsimi = yumutlamaMevsimi;
	}

	public String getRengi() {
		return rengi;
	}

	public void setRengi(String rengi) {
		this.rengi = rengi;
	}

	public boolean isGocDurumu() {
		return gocDurumu;
	}

	public void setGocDurumu(boolean gocDurumu) {
		this.gocDurumu = gocDurumu;
	}

	public double getYasamUzunluguOrtalaması() {
		return yasamUzunluguOrtalaması;
	}

	public void setYasamUzunluguOrtalaması(double yasamUzunluguOrtalaması) {
		this.yasamUzunluguOrtalaması = yasamUzunluguOrtalaması;
	}

	public int getYumutlamaMevsimi() {
		return yumutlamaMevsimi;
	}

	public void setYumutlamaMevsimi(int yumutlamaMevsimi) {
		this.yumutlamaMevsimi = yumutlamaMevsimi;
	}
	
	protected String kusunBilgisiniYaz(){
		String gocDurumuDegiskeni,mevsim;
		
		if(this.gocDurumu == true){
			gocDurumuDegiskeni = "Göçmen Kuş";
		}else{
			gocDurumuDegiskeni = "Göçmen Kuş Değil";
		}
			
		switch(this.yumutlamaMevsimi){
			case 1:
				mevsim = "İLKBAHAR";
				break;
			case 2:
				mevsim = "YAZ";
				break;
			case 3:
				mevsim = "SONBAHAR";
				break;
			case 4:
				mevsim = "KIŞ";
				break;	
			
				
			default : mevsim = "YAZ";	
		}
			return "Rengi: "+this.rengi+
				"Göç Durumu : "+gocDurumuDegiskeni+
				"Yaşam uzunluğu ortalaması"+this.yasamUzunluguOrtalaması+
				"Yumutlama Mevsimi"+mevsim+"\n";
	}
}


package org.sirketimiz025cokduzeykalitim;

public class BagajKutuUcreti extends BagajKutuAgirligi {
   
	private double ucret;

	public BagajKutuUcreti() {
		super();
		this.ucret=0;
	}

	public BagajKutuUcreti(double ucret, double hacim, double en, double boy, double yukseklik) {
		super(hacim, en, boy, yukseklik);
		this.ucret = ucret;
	}

	public BagajKutuUcreti(double ucret, double hacim, double BirKupunKenari) {
		super(hacim, BirKupunKenari);
		this.ucret = ucret;
	}
	
	public BagajKutuUcreti(BagajKutuUcreti nesne) {
		super(nesne);
		this.ucret = nesne.ucret;
	}
	
	double ucretHesapla(){
		return (hacimHesapla()*0.10)+25;
	}
   
    	
}

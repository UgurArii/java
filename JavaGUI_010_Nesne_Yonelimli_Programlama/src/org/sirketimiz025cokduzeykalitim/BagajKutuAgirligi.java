
package org.sirketimiz025cokduzeykalitim;

public class BagajKutuAgirligi extends BagajKutu{

   private double hacim;

	public BagajKutuAgirligi() {
		super();
		this.hacim=0;
	}

	public BagajKutuAgirligi(double hacim, double en, double boy, double yukseklik) {
		super(en, boy, yukseklik);
		this.hacim = hacim;
	}

	public BagajKutuAgirligi(double hacim, double BirKupunKenari) {
		super(BirKupunKenari);
		this.hacim = hacim;
	}
	
	public BagajKutuAgirligi(BagajKutuAgirligi nesne) {
		super(nesne);
		this.hacim = nesne.hacim;
	}
   
   
}

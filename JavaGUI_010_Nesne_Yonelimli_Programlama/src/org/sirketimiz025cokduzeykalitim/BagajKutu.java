
package org.sirketimiz025cokduzeykalitim;

public class BagajKutu {

  private double en,boy,yukseklik;

	public BagajKutu() {
	this.en = this.boy = this.yukseklik = 0;
	
	}

	public BagajKutu(double en, double boy, double yukseklik) {
		this.en = en;
		this.boy = boy;
		this.yukseklik = yukseklik;
	}
	
  public BagajKutu(double BirKupunKenari) {
	this.en = this.boy = this.yukseklik = 0;
	
	}
  
  public BagajKutu(BagajKutu nesne) {
		this.en = nesne.en;
		this.boy = nesne.boy;
		this.yukseklik = nesne.yukseklik;
	}
  
      double hacimHesapla(){
	      return (this.en*this.boy*this.yukseklik);
      }  
}

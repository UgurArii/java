
package org.sirketimiz009.metotlaritaniyalim;

import org.sirketimiz008.siniflarkavrami.*;


public class Kutu {
 
    double en;
    double boy;
    double yukseklik;
    
    void hacimHesaplaKonsolaYaz(){
	    System.out.println("Hacim: " + en * boy * yukseklik);
    }
    
    double hacmiHesap(){
	    return en*boy*yukseklik;
    }
    
    void HazirlikYap(double enIlkDegeri, double boyIlkDegeri, double yukseklikIlkDeger){
	    this.en = enIlkDegeri;
	    this.boy = boyIlkDegeri;
	    this.yukseklik = yukseklikIlkDeger;
    }
    
    public Kutu(){
	    System.out.println("Kutu Sınıfının Hazırlayıcı Parametresiz");
    }

	public Kutu(double en, double boy, double yukseklik) {
		this.en = en;
		this.boy = boy;
		this.yukseklik = yukseklik;
		 System.out.println("Kutu Sınıfının Hazırlayıcı Parametreli");
		 System.out.println("En: " +en);
		 System.out.println("Boy: " +boy);
		  System.out.println("Yükseklik: " +yukseklik);
		 
	}
    
    
}


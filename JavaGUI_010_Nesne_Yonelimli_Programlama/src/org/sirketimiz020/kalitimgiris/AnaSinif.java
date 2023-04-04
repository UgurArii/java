/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sirketimiz020.kalitimgiris;

/**
 *
 * @author uğur
 */
public class AnaSinif {
	int sayi1,sayi2;

	public AnaSinif(int sayi1, int sayi2) {
		this.sayi1 = sayi1;
		this.sayi2 = sayi2;
	}

	public int getSayi1() {
		return sayi1;
	}

	public void setSayi1(int sayi1) {
		this.sayi1 = sayi1;
	}

	public int getSayi2() {
		return sayi2;
	}

	public void setSayi2(int sayi2) {
		this.sayi2 = sayi2;
	}
	
	
	public String sayilariGosterMetodu_AnaSinif(){
		
		
		return "Sayilari Goster Metodu AnaSinif" + this.sayi1 + 
			"Sayi2 : "+this.sayi2;
	}
}

class CocukSinif extends AnaSinif{
	int sayi3;
	
	public CocukSinif(int sayi3,int sayi1, int sayi2){
		super(sayi1,sayi2);
		this.sayi3 = sayi3;// üst sınıfın hazırlayıcı
	}

	public int getSayi3() {
		return sayi3;
	}

	public void setSayi3(int sayi3) {
		this.sayi3 = sayi3;
	}
	
	public String sayilariGosterMetodu_CocukSinif(){
		int toplam = sayi1 + sayi2 + this.sayi3;
		
		return "Sayilari Toplam Metodu Çocuk Sinif Toplam " + this.sayi3;
	}
}

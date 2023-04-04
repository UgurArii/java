/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sirketimiz032.finalanahtarsozcugu.kalitim;

/**
 *
 * @author uğur
 */
public class Sinif2 extends Sinif1{
	
	private int sayi3,sayi4,sayi5,sayi6;

	public Sinif2() {
	}

	public Sinif2(int sayi3, int sayi4, int sayi5, int sayi6, int sayi1, int sayi2) {
		super(sayi1, sayi2);
		this.sayi3 = sayi3;
		this.sayi4 = sayi4;
		this.sayi5 = sayi5;
		this.sayi6 = sayi6;
	}

	public int getSayi3() {
		return sayi3;
	}

	public void setSayi3(int sayi3) {
		this.sayi3 = sayi3;
	}

	public int getSayi4() {
		return sayi4;
	}

	public void setSayi4(int sayi4) {
		this.sayi4 = sayi4;
	}

	public int getSayi5() {
		return sayi5;
	}

	public void setSayi5(int sayi5) {
		this.sayi5 = sayi5;
	}

	public int getSayi6() {
		return sayi6;
	}

	public void setSayi6(int sayi6) {
		this.sayi6 = sayi6;
	}

	
	
	@Override
	public int abstractTipindekiMetot_SayilariTopla() {
		
		
		return this.sayi3+this.sayi4+this.sayi5+this.sayi6;
	}

	
	@Override
	public int abstractTipindekiMetot_SayilariTopla(int parametreSayi2) {
		return (int) (float) (this.sayi3+this.sayi4+this.sayi5+this.sayi6+parametreSayi2);
	}
	
	
	
	@Override
	public int normalTipindekiMetot_SayilariTopla(){
		return super.getSayi1()+super.sayi2+this.sayi3+this.sayi4+this.sayi5+this.sayi6;
	}
	/*
                @Override	
		public String normalTipindekiMetot_SayilariTopla(double parametreSayi2){
		return "Toplam: "+normalTipindekiMetot_SayilariTopla()+parametreSayi2;
	}
		
		//aşırı yükleme
		public final int normalTipindekiMetot_SayilariTopla(double parametreSayi2){
		return this.getSayi1()+this.sayi2;
	}
	*/
	
}


package org.sirketimiz032.finalanahtarsozcugu.kalitim;

public abstract class Sinif1 {
	private int sayi1;
	protected int sayi2;

	public Sinif1() {
	}

	public Sinif1(int sayi1, int sayi2) {
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
	
	
	
	public int normalTipindekiMetot_SayilariTopla(){
		return this.sayi1+sayi2;
	}
	
		public int normalTipindekiMetot_SayilariTopla(int parametreSayi2){
		return this.sayi1+sayi2;
	}
	
	public abstract int abstractTipindekiMetot_SayilariTopla();
	public abstract int abstractTipindekiMetot_SayilariTopla(int parametreSayi2);
	
	public final int finalTipindekiMetot_SayilariTopla(){
		return this.sayi1+sayi2;
	}
	
	public final int finalTipindekiMetot_SayilariTopla(int parametreSayi2){
		return this.sayi1+parametreSayi2;
	}
	
	
}

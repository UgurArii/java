
package com.firmaadi001.genericsKullanimi;

public class _001_GenericsSiniftakiKullanimi<T> {
	private T t;//t de değişken tipi belirtilmez
	//--------------------------------------
	 public _001_GenericsSiniftakiKullanimi(){
	
}

	public _001_GenericsSiniftakiKullanimi(T t) {
		this.t = t;
	}
//--------------------------------------
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	//-------------------------------------- 
	
	public static void main(String[] args) {
		_001_GenericsSiniftakiKullanimi <Integer> integerTipindekiNesne = new _001_GenericsSiniftakiKullanimi<Integer>();
		_001_GenericsSiniftakiKullanimi <String> stringTipindekiNesne = new _001_GenericsSiniftakiKullanimi<String>();
		
		integerTipindekiNesne.setT(new Integer(105));
		System.out.println("Sonuc : "+integerTipindekiNesne.getT());
		
		
		stringTipindekiNesne.setT("Sonsuza Kadar");
		System.out.println("Sonuc : "+stringTipindekiNesne.getT());
		
		integerTipindekiNesne.yazdir("MUSTAFA KEMAL ATATÜRK");
		integerTipindekiNesne.yazdir(1881);
		
	}

	/*private void yazdir(String gelenParametre) {
		System.out.println("Gelen : "+gelenParametre);
	}

	private void yazdir(int gelenParametre) {
		System.out.println("Gelen : "+gelenParametre);
	}
	*/
	private <U> void yazdir(U u) {
		System.out.println("T : "+t.getClass().getName()+"............."+t);
		System.out.println("U : "+u.getClass().getName()+"............."+u);
	}
	
	private <U extends Number > void yazdir(U u) {
		String mesaj  ="Yazı Kısıtlama"+"T : "+t.getClass().getName()+"............."+t+
			"U : "+u.getClass().getName()+"............."+u;
		System.out.println(mesaj);
	}
	private <U extends String > void yazdir(U u) {
		String mesaj  ="Yazı Kısıtlama"+"T : "+t.getClass().getName()+"............."+t+
			"U : "+u.getClass().getName()+"............."+u;
		System.out.println(mesaj);
	}
}

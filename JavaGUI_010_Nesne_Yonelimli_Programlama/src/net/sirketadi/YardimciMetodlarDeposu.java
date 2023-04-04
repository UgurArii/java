/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sirketadi;

/**
 *
 * @author uğur
 */
public class YardimciMetodlarDeposu {
	
	public int menuMetodu(){
		return 10;
	}
	public String selamVer(){
		
		return "Selamlar Hoşgeldiniz.";
	}
	
		public String selamVer(String gelenAd){
		
		String mesaj = "Selamlar Hoşgeldiniz."+gelenAd+"Menu Sayısı:"+menuMetodu();
		return mesaj;
	}
		
		public String selamVer(String gelenAd, String gelenSoyad){
		
		String mesaj = "Selamlar "+gelenAd+ " "+ gelenSoyad+ " Hoş Geldiniz.";
		return mesaj;
	}
	
	public String ugurlamaYap(){
		return "Güle Güle Gene Bekleriz";
	}
	
	public String ugurlamaYap(String gelenAd){
		return "Güle Güle Gene Bekleriz."+gelenAd;
	}
}

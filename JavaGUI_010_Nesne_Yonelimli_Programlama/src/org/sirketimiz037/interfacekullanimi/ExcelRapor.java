/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sirketimiz037.interfacekullanimi;

/**
 *
 * @author uğur
 */
public class ExcelRapor implements IRapor{

	@Override
	public void hazirla() {
		System.out.println("Word Hazırlanıyor");
		String durum = "Word Hazırlanıyor";
	}

	@Override
	public void hazirla(int basilacakAdet) {
		System.out.println("Basılacak Sayfa Adedi : "+basilacakAdet);
		String durum = "Basılacak Sayfa Adedi : "+basilacakAdet;
	}

	@Override
	public String hazirla(String belgeAdi) {
		System.out.println("Basılacak Belge Adı : "+belgeAdi);
		String durum = "Basılacak Belge Adı : "+belgeAdi;
		return durum;
	}

	@Override
	public String hazirla(int basilacakAdet, String belgeAdi) {
		System.out.println("Basılacak Sayfa Adedi : "+basilacakAdet + "Belge Adı:  " + belgeAdi);
		String durum = "Basılacak Belge Adı : "+basilacakAdet + "Belge Adı:  " + belgeAdi;
		return durum;
	}

	@Override
	public void hazirla(Fatura fatura) {
		System.out.println("Fatura Hazırlanıyor");
		String durum = "Fatura Hazırlanıyor\n";
		
	}
	
}



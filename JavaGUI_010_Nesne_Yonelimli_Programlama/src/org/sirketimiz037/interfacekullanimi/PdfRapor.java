
package org.sirketimiz037.interfacekullanimi;


public class PdfRapor implements IRapor{

	@Override
	public void hazirla() {
		System.out.println("Pdf Hazırlanıyor");
		String durum = "Pdf Hazırlanıyor";
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


package com.kurumunadi;


public class Personel {
    private String adi;
    private String soyadi;
    private String bolumu;
    private int numarasi;

       public Personel(){
	       
       }
       public Personel(String adi){
	       this.adi = adi;
       }
       
       public Personel(String adi, String soyadi){
	       this.adi = adi;
	       this.soyadi = soyadi;
       }
       
         public Personel(String adi, String soyadi, String bolumu){
	       this.adi = adi;
	       this.soyadi = soyadi;
	       this.bolumu = bolumu;
       }
	public Personel(String adi, String soyadi, String bolumu, int numarasi) {
		this.adi = adi;
		this.soyadi = soyadi;
		this.bolumu = bolumu;
		this.numarasi = numarasi;
	}

	 public Personel(int numarasi){
		 this.numarasi = numarasi;
	 }
	 
	  public Personel(int numarasi, String adi){
		 this.numarasi = numarasi;
		 this.adi = adi;
	 }
	 	public Personel(String soyadi, String adi,  int numarasi,String bolumu) {
		this.adi = adi;
		this.soyadi = soyadi;
		this.bolumu = bolumu;
		this.numarasi = numarasi;
	}
		public Personel(String soyadi, String adi,  int numarasi) {
		this.adi = adi;
		this.soyadi = soyadi;
		this.bolumu = bolumu;
		
	}
	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getSoyadi() {
		return soyadi;
	}

	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}

	public String getBolumu() {
		return bolumu;
	}

	public void setBolumu(String bolumu) {
		this.bolumu = bolumu;
	}

	public int getNumarasi() {
		return numarasi;
	}

	public void setNumarasi(int numarasi) {
		this.numarasi = numarasi;
	}

	public int getirNo(){
		return numarasi;
	}
	
	public void hazirlnaNo(int numarasi){
		this.numarasi = numarasi+10;		
	}
	
        public String personelinAdiSoyadiNoGoster(){
		return this.getirNo() + " " + this.getAdi() + " " +this.getSoyadi();
	}
	
	public String personelinAdiSoyadiBolumuGoster(){
		return this.getAdi() +  " " + this.getSoyadi() + " " + this.getBolumu();
	}
}

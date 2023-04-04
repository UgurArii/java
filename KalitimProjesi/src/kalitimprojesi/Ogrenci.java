package kalitimprojesi;


public class Ogrenci {
    
    
    private String ad;
    private String soyad;
    private int yas;
    private int ogrenciNumarasi;
    
    public Ogrenci(){
        ad = "yok";
        soyad = "yok";
        yas = 0;
        ogrenciNumarasi = 0;
    }
    
    public Ogrenci(String ad, String soyad, int yas, int ogrenciNumarasi){
        this.ad = ad;
        this.soyad = soyad;
        setYas(yas);
        setOgrenciNumarasi(ogrenciNumarasi);
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
       if(this.yas < 0){
           this.yas = 0;
       }else{
           this.yas = yas;
       }
    }

    public int getOgrenciNumarasi() {
        return ogrenciNumarasi;
    }

    public void setOgrenciNumarasi(int ogrenciNumarasi) {
        if(this.ogrenciNumarasi < 0){
            this.ogrenciNumarasi = 0;
        }else{
            this.ogrenciNumarasi = ogrenciNumarasi;        
        }
    }
    
    public String kisiBilgileriniVer(){
        return "Kisi Bilgileri: ad " + ad + " soyad " + soyad + " yas " + yas + " öğrenci no: " +ogrenciNumarasi;
    }
    
}

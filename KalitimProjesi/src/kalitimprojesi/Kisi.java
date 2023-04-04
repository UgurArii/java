package kalitimprojesi;



public class Kisi extends Object{
    
    
    private String ad;
    private String soyad;
    private int yas;
    
    public Kisi(){
        ad = "yok";
        soyad = "yok";
        yas = 0;
    }
    
    public Kisi(String ad, String soyad, int yas){
        this.ad = ad;
        this.soyad = soyad;
        setYas(yas);// yapıcı olarak çağrılır.
        
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
    
    public String kisiBilgileriVer(){
        return "Kisi Bilgileri: ad " + ad + " soyad " + soyad + " yas " + yas;
    }

    @Override
    public String toString() {
        return "Kişi bilgileri ad soyad " + ad + " " + soyad; 
    }

    String kisiBilgileriniVer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}

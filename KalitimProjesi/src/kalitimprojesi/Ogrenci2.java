package kalitimprojesi;

//extends ile kisi sınıfında kalıtım sağlatık
public class Ogrenci2 extends Kisi{

    private int ogrenciNumarasi;
    
    public Ogrenci2(){
        super();
        setOgrenciNumarasi(ogrenciNumarasi);
        
    }
    
    public Ogrenci2(String ad, String soyad, int yas, int ogrenciNumarasi){
        super(ad,soyad,yas);
        ogrenciNumarasi = 0;
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

    @Override
       public String kisiBilgileriniVer(){
       return super.kisiBilgileriniVer()+ " ogrenci numarası: " + ogrenciNumarasi;
   }

    
    
    
}

package kalitimprojesi;


public class Main {

    public static void main(String[] args) {
        Kisi kisi = new Kisi("Uğur ", "Arı",22);
        System.out.println(kisi.kisiBilgileriniVer());
        System.out.println(kisi.toString());
        
        Ogrenci ogrenci = new Ogrenci("Uğur","Arı",22,12541520);
        System.out.println(ogrenci.kisiBilgileriniVer());
        
        Ogrenci2 ogrenci2 = new Ogrenci2("Uğur", "Arı" , 22, 12541520);
        System.out.println(ogrenci2.kisiBilgileriniVer());
        System.out.println(ogrenci2.toString());
    }
    
}

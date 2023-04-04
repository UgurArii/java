package dosya_islemleri;
import java.io.*;
import java.util.*;
import ekpaket.*;
public class Dosya_islemleri {
//ana paket kısmında aynı isim olsaydı diğer dosyaların ismi.classismi şeklinde yazılmalı
    public static void main(String[] args) {
       int sayi =10;
       int sayi2=20;
       int sonuc;
        ekpaketSinifi paketsinif = new ekpaketSinifi();
      sonuc =  paketsinif.topla(sayi,sayi2);
      System.out.println("Toplam = "+sonuc);
    }
    
}

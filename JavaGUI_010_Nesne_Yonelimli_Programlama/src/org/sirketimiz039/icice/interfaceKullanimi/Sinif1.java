
package org.sirketimiz039.icice.interfaceKullanimi;


public class Sinif1 {

   public interface IBizimIcArayuzumuz{
	   boolean sayiIsareti(int sayi);
	   int sayiMutalkDeger(int sayi);
	   
	   
   }	
   
   public class Sinif2 extends Sinif1{
	   public String sayiyiGoster(int Sayi){
		   return "\nİç Gömülü Inner Sınıf2: "+Sayi;
	   }
   }
   
   public static class Sinif3 implements IBizimIcArayuzumuz{

		@Override
		public boolean sayiIsareti(int sayi) {
			return sayi>0 ? true : false;
		}

		@Override
		public int sayiMutalkDeger(int sayi) {
			boolean isaret = sayiIsareti(sayi);
			return isaret==true ? sayi : -1*sayi;
		}
		
		public String sayiyiGoster(int sayi){
			return "\nİç-Gömülü-Inner Sinif3 Sonuç: "+sayiMutalkDeger(sayi);
		}
	   
   }
   
 
   
}

  abstract class Sinif3 implements Sinif1.IBizimIcArayuzumuz{

	@Override
	public int sayiMutalkDeger(int sayi) {
		if(sayi>0){
			return sayi;
		}else{
			return sayi * -1;
		}
	}
	  
	  
  }

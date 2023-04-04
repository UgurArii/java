
package org.sirketimiz037.interfacekullanimi;


public interface IRapor {
   public void hazirla();
   public void hazirla(int basilacakAdet);
   public String hazirla(String belgeAdi);
   public String hazirla(int basilacakAdet,String belgeAdi);
   public void hazirla(Fatura fatura);
 	
}

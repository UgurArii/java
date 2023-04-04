
package org.sirketimiz013.recursivemetotlari;

public class IslemDeposu {
	int faktoriye(int sayi){
		if(sayi<0){
			return 0;
		}
		if(sayi == 1 || sayi==0){
			return 1;
		}else{
	int sonuc = sayi * faktoriye(sayi-1);
	return sonuc;
		}
	}
	
	
	
}

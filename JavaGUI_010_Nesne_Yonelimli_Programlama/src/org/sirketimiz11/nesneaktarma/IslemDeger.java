
package org.sirketimiz11.nesneaktarma;

public class IslemDeger {
	
	double sayi1,sayi2;

	public IslemDeger(double sayi1, double sayi2) {
		this.sayi1 = sayi1;
		this.sayi2 = sayi2;
	}
	
	boolean nesnesEsitmi(IslemDeger parametreyleGelenNesnemiz){
		if(parametreyleGelenNesnemiz.sayi1 == this.sayi1 && parametreyleGelenNesnemiz.sayi2 == this.sayi2){
	return true;		
		}else{
			return false;
		}
		
	}
}

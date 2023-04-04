
package org.sirketimiz10.overloading;


public class TemelMatematik {
	
	int mutlakDegerAl(int sayi){
		if(sayi>0){
		return sayi;
		}else{
			return -sayi;
		}
	}
	
	float mutlakDegerAl(float sayi){
		if(sayi>0){
		return sayi;
		}else{
			return -sayi;
		}
	}
	
	double mutlakDegerAl(double sayi){
		if(sayi>0){
		return sayi;
		}else{
			return -sayi;
		}
	}
	
	String toplamaIslemi(){
		System.out.println("Parametresiz Metod");
		return "Paremetresiz Metod";
	}
	
	double toplamaIslemi(double sayi1){
		return sayi1 + sayi1;
	}
	
	double toplamaIslemi(double sayi1, double sayi2){
		return sayi1 + sayi2;
	}
	
	int toplamaIslemi(int sayi1, double sayi2){
		return sayi1 + (int)sayi2;
	}
	
	
}

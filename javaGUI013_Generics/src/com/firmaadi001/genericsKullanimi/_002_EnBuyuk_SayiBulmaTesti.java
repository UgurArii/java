package com.firmaadi001.genericsKullanimi;


public class _002_EnBuyuk_SayiBulmaTesti {
	public static void main(String parametreler[]) {
		System.out.printf("En Büyük int %d, %d, %d Sonuç = %d\n\n\n",3,4,5, enBuyugunuBul(3,4,5));
		System.out.printf("En Büyük double %.1f, %.1f, %.1f Sonuç = %.1f\n\n\n",3.4,0.4,1.5, enBuyugunuBul(3.4,0.4,1.5));
		
	}

//	private static int enBuyugunuBul(int a, int b, int c) {
//		int enBuyuk = a;
//		if(b>enBuyuk){
//			b = enBuyuk;
//		}
//		if(c>enBuyuk){
//			c=enBuyuk;
//		}
//		
//		return enBuyuk;
//	}
//	
	private static <T extends Comparable<T>> T enBuyugunuBul(T a, T b, T c) {
		T enBuyuk = a;
		if(b.compareTo(enBuyuk)>0){
			enBuyuk = b;
		}
		if(c.compareTo(enBuyuk)>0){
			enBuyuk = c;
		}
		
		return enBuyuk;
	}
}

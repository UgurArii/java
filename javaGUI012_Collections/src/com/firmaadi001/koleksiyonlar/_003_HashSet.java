
package com.firmaadi001.koleksiyonlar;

import java.util.HashSet;

public class _003_HashSet {
	public static void main(String args[]){
	HashSet hs = new HashSet();
	System.out.println("Başlangıç Boyutu: "+hs.size());
	hs.add("J");
	hs.add("A");
	hs.add("V");
	hs.add("A");
	hs.add("G");
	hs.add("U");
	hs.add("I");
	
	
	
	
	System.out.println("Eklemeden sonra Dizi Boyutu : "+ hs.size());
	
	System.out.println("İçerik " + hs);
	
	hs.remove("s");
	
	hs.remove(7);
		System.out.println("Sonraki Hali " + hs);
	}
}

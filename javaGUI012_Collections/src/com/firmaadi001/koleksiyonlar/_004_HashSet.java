
package com.firmaadi001.koleksiyonlar;

import java.util.LinkedHashSet;




public class _004_HashSet {
	public static void main(String args[]){
	LinkedHashSet lhs = new LinkedHashSet();
	System.out.println("Başlangıç Boyutu: "+lhs.size());
	lhs.add("J");
	lhs.add("A");
	lhs.add("V");
	lhs.add("A");
	lhs.add("G");
	lhs.add("U");
	lhs.add("I");
	
	
	
	
	System.out.println("Eklemeden sonra Dizi Boyutu : "+ lhs.size());
	
	System.out.println("İçerik " + lhs);
	
	lhs.remove("s");
	
	lhs.remove(7);
		System.out.println("Sonraki Hali " + lhs);
	}

}

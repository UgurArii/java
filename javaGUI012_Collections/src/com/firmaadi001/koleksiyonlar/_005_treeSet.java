
package com.firmaadi001.koleksiyonlar;

import java.util.TreeSet;

public class _005_treeSet {
		public static void main(String args[]){
	TreeSet ts = new TreeSet();
	System.out.println("Başlangıç Boyutu: \n"+ts.size());
	ts.add("J");
	ts.add("A");
	ts.add("V");
	ts.add("A");
	ts.add("G");
	ts.add("U");
	ts.add("I");
	//treset sayılara izin yok.
	
	
	
	System.out.println("Eklemeden sonra Dizi Boyutu : \n"+ ts.size());
	
	System.out.println("İçerik \n" + ts);
	
	ts.remove("s");
	
	ts.remove(7);
		System.out.println("Sonraki Hali \n" + ts);
	}
}

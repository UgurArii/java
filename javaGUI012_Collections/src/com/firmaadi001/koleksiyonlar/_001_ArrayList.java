
package com.firmaadi001.koleksiyonlar;

import java.util.ArrayList;


public class _001_ArrayList {

public static void main(String args[]){
	ArrayList al = new ArrayList();
	System.out.println("Başlangıç Boyutu: "+al.size());
	al.add("J");
	al.add("A");
	al.add("V");
	al.add("A");
	al.add("G");
	al.add("U");
	al.add("I");
	
	al.add(0,"U");
	
	System.out.println("Eklemeden sonra Dizi Boyutu : "+ al.size());
	
	System.out.println("İçerik " + al);
	
	al.remove("s");
	
}	
}

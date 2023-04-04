
package com.firmaadi001.koleksiyonlar;

import java.util.LinkedList;


public class _002_linkedList {
	public static void main(String args[]){
	LinkedList ll = new LinkedList();
	System.out.println("Başlangıç Boyutu: "+ll.size());
	ll.add("J");
	ll.add("A");
	ll.add("V");
	ll.add("A");
	ll.add("G");
	ll.add("U");
	ll.add("I");
	ll.add(0,"U");
	ll.addFirst("Uğur");
	ll.addLast("Arı");
	
	
	
	System.out.println("Eklemeden sonra Dizi Boyutu : "+ ll.size());
	
	System.out.println("İçerik " + ll);
	
	ll.remove("s");
	
	ll.remove(7);
		System.out.println("Sonraki Hali " + ll);
		
}
}

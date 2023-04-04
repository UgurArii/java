
package com.firmaadi001.koleksiyonlar;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class _013_HashTable {
	public static void main(String args[]){
	Hashtable ht = new Hashtable();
	Enumeration isimler;
	String eleman;
	System.out.println("Başlangıç Boyutu: \n"+ht.size());
	ht.put("J",new Double(100.05));
	ht.put("A",new Double(100.565));
	ht.put("V",new Double(2040.740));
	ht.put("A", new Double(10.55));
	ht.put("G",new Double(40005.50));
	ht.put("U",new Double(004.100));
	ht.put("I",new Double(5500.40));
	//treset sayılara izin yok.
	
	Set set = ht.entrySet();
	Iterator i = set.iterator();
	while(i.hasNext()){
		Map.Entry me = (Map.Entry) i.next();
		System.out.println(me.getKey()+" : "+me.getValue());
	}
	
	isimler = ht.keys();
	while(isimler.hasMoreElements()){
		Object object = isimler.nextElement();
		System.out.println(object+" : "+ht.get(object));
	}
	System.out.println("Eklemeden sonra Dizi Boyutu : \n"+ ht.size());
	
	System.out.println("İçerik \n" + ht);
	
	ht.remove("s");
	
	ht.remove(7);
		System.out.println("Sonraki Hali \n" + ht);
	}
}

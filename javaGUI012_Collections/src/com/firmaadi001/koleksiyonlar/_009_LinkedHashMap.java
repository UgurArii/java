/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firmaadi001.koleksiyonlar;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author uğur
 */
public class _009_LinkedHashMap {
	public static void main(String args[]){
	LinkedHashMap lhm = new LinkedHashMap();
	System.out.println("Başlangıç Boyutu: \n"+lhm.size());
	lhm.put("J",new Double(100.05));
	lhm.put("A",new Double(100.565));
	lhm.put("V",new Double(2040.740));
	lhm.put("A", new Double(10.55));
	lhm.put("G",new Double(40005.50));
	lhm.put("U",new Double(004.100));
	lhm.put("I",new Double(5500.40));
	//treset sayılara izin yok.
	
	Set set = lhm.entrySet();
	Iterator i = set.iterator();
	while(i.hasNext()){
		Map.Entry me = (Map.Entry) i.next();
		System.out.println(me.getKey()+" : "+me.getValue());
	}
	System.out.println("Eklemeden sonra Dizi Boyutu : \n"+ lhm.size());
	
	System.out.println("İçerik \n" + lhm);
	
	lhm.remove("s");
	
	lhm.remove(7);
		System.out.println("Sonraki Hali \n" + lhm);
}
}



package com.firmaadi001.koleksiyonlar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class _006_HashMap {
	public static void main(String args[]){
	HashMap hm = new HashMap();
	System.out.println("Başlangıç Boyutu: \n"+hm.size());
	hm.put("J",new Double(100.05));
	hm.put("A",new Double(100.565));
	hm.put("V",new Double(2040.740));
	hm.put("A", new Double(10.55));
	hm.put("G",new Double(40005.50));
	hm.put("U",new Double(004.100));
	hm.put("I",new Double(5500.40));
	//treset sayılara izin yok.
	
	Set set = hm.entrySet();
	Iterator i = set.iterator();
	while(i.hasNext()){
		Map.Entry me = (Map.Entry) i.next();
		System.out.println(me.getKey()+" : "+me.getValue());
	}
	System.out.println("Eklemeden sonra Dizi Boyutu : \n"+ hm.size());
	
	System.out.println("İçerik \n" + hm);
	
	hm.remove("s");
	
	hm.remove(7);
		System.out.println("Sonraki Hali \n" + hm);
	}
}

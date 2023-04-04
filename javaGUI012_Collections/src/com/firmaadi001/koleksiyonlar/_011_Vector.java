
package com.firmaadi001.koleksiyonlar;

import java.util.Enumeration;
import java.util.Vector;

public class _011_Vector {
	public static void main(String args[]){
		Vector v = new Vector(3, 2);
		System.out.println("Başlangıç Boyutu: "+v.size()+" Kapasitesi : "+v.capacity());
		v.addElement(new Integer(1));
		v.addElement(new Integer(2));
		v.addElement(new Integer(3));
		v.addElement(new Integer(4));
		System.out.println("Sonraki Boyutu: "+v.size()+" Kapasitesi : "+v.capacity());
		
		if(v.contains(new Integer(3))){
			System.out.println("3. vektörün varlığı onaylandı"+v.contains(new Integer(3)));
		}
		
		if(v.contains(new Integer(24))){
			System.out.println("24. vektörün varlığı onaylandı"+v.contains(new Integer(24)));
		}else{
			System.out.println("24. vektörün varlığı onaylanmadı!!!"+v.contains(new Integer(24)));
		}
		
		Enumeration vEnum = v.elements();
		System.out.println("\nVektör Elemanları");
		while(vEnum.hasMoreElements()){
			System.out.println(vEnum.nextElement()+" \n"); 
		}
	}
}

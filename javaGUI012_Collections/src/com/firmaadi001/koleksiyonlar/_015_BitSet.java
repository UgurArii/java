
package com.firmaadi001.koleksiyonlar;

import java.util.BitSet;

public class _015_BitSet {
	public static void main(String[] args) {
		BitSet bs1 = new BitSet(16);
		BitSet bs2 = new BitSet(16);
		
		for(int i = 0; i < 16;i++){
			if(i%2==0) bs1.set(i);
			if(i%5==0) bs2.set(i);
		}
		
		System.out.println("bit1 için ilk durum : "+bs1+"\n");
		System.out.println("bit2 için ilk durum : "+bs2+"\n");
		
		//AND
		bs2.and(bs1);
		
		System.out.println("bit2 AND bs1 : "+bs2+"\n");
		
		//OR
		bs2.or(bs1);
		
		System.out.println("bit2 OR bs1 : "+bs2+"\n");
		
		//XOR
		bs2.xor(bs1);
		
		System.out.println("bit2 XOR bs1 : "+bs2+"\n");
		
	}
}

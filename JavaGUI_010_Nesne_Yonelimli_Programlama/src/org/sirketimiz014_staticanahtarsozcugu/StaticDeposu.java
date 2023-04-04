/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sirketimiz014_staticanahtarsozcugu;

/**
 *
 * @author uğur
 */
public class StaticDeposu {
	
        static protected int int_tipindeki_static_degiskeni1=100;
	public static int int_tipindeki_static_degiskeni2;
	
	
	static{
		System.out.println("Static kapsam alanı çalıştı");
		int_tipindeki_static_degiskeni2 = int_tipindeki_static_degiskeni1 + 500; 
	}
	
}

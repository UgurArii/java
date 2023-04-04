/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sirketimiz031.abstractanahtarsozcugu;

/**
 *
 * @author uğur
 */
public abstract class AbstractSoyutOzet {
	
	abstract String govdesizIsYapmayanMetot();//soyut metotlar gövdesiz olacak
	 protected String govdeliIsYapanMetot(){
		 
		 System.out.println("Gövdeli İş Yapan Metot");
		 return "Gövdeli İş Yapan Metot";
	 }
}

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
public class MirasAlanSinif extends AbstractSoyutOzet{

	@Override
	String govdesizIsYapmayanMetot() {
		System.out.println("MirasAlanSinifi Gövdesiz İş Yapmayan Metot");
		return "MirasAlanSinifi: Gövdesiz İş Yapmayan Metot";
	}
	
}

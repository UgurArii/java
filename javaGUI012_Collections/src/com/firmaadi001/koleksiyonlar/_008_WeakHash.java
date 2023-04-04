
package com.firmaadi001.koleksiyonlar;

import java.util.Map;
import java.util.WeakHashMap;

public class _008_WeakHash {
	public static Map map;
	public static void main(String args[]){
		map = new WeakHashMap();
		map.put(new String("Java"),"GUI");
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				while(map.containsKey("JAVA")){
					try{
						System.out.println("---------------------------");
						Thread.sleep(3000);
					}catch(InterruptedException e){
						System.out.println("Hata : "+e);
					}catch(Exception e){
						System.out.println("Hata : "+e);
					}
					
					System.out.println("Thread(iş parçacığı) bekletildi.Şimdi devam ediliyor. ");
					System.gc();
				}
			}
			
		};
		Thread t = new Thread(run);
		t.start();
		System.out.println("Vazifesi İşlendi");
		
		try{
			t.join();
		}catch(InterruptedException e){
						System.out.println("Hata : "+e);
					}catch(Exception e){
						System.out.println("Hata : "+e);
					}
		
		
		
	}
}

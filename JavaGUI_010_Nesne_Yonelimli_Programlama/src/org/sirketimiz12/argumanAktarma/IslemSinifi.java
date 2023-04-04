
package org.sirketimiz12.argumanAktarma;


public class IslemSinifi {

    int sayimiz;

	public IslemSinifi(int sayimiz) {
		this.sayimiz = sayimiz;
	}
    
    //primitive tip
	int ilkelTipiAktar(int sayimiz){
		
		
		return sayimiz+1000;
	}
	
	//referans tipleri aktarılıyor
	int nesneTipiAktar(IslemSinifi parametreGelenNesne){
		
		
		return parametreGelenNesne.sayimiz = parametreGelenNesne.sayimiz+1000;
	}

	IslemSinifi nesneDonder() {
		IslemSinifi yeniNesne = new IslemSinifi(this.sayimiz+500);
		
		return yeniNesne;
	}
	
	
}

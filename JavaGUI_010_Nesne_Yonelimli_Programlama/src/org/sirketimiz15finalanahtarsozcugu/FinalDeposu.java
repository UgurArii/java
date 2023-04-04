
package org.sirketimiz15finalanahtarsozcugu;


public class FinalDeposu {
   final static int FINAL_DEGISKENIMIZ = 571;
   	        private int siradanDegiskenimiz;

	public FinalDeposu(int siradanDegiskenimiz) {
		this.siradanDegiskenimiz = siradanDegiskenimiz;
	}

	public FinalDeposu() {
		this.siradanDegiskenimiz = 621;
	}
	 
	 String yazdirmaMetodu(){
		 return "final değişkenimiz :   " + FINAL_DEGISKENIMIZ +
			 "\n sıradan değişkenimiz " + siradanDegiskenimiz;
	 } 

	public int getSiradanDegiskenimiz() {
		return siradanDegiskenimiz;
	}

	public void setSiradanDegiskenimiz(int siradanDegiskenimiz) {
		this.siradanDegiskenimiz = siradanDegiskenimiz;
	}
	 
	 
}

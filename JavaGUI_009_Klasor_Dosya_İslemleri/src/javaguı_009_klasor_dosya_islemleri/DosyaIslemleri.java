
package javaguı_009_klasor_dosya_islemleri;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author uğur
 */
public class DosyaIslemleri {
	
	public static String dosyaSecMetodu(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(".")); //dosyanın başlıyacağı yer
		chooser.setDialogTitle("Lütfen Bir Dosya Seçiniz...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int geriDonusDegeri = chooser.showOpenDialog(null);
		
		if(geriDonusDegeri == JFileChooser.APPROVE_OPTION){
			
			return chooser.getSelectedFile().getAbsolutePath();
		}else{
			return null;
		}
	}
	
	public static String klasorSecMetodu(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(".")); //dosyanın başlıyacağı yer
		chooser.setDialogTitle("Klasör Bir Dosya Seçiniz...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int geriDonusDegeri = chooser.showOpenDialog(null);
		
		if(geriDonusDegeri == JFileChooser.APPROVE_OPTION){
			
			return chooser.getSelectedFile().getAbsolutePath();
		}else{
			return null;
		}
	}
	
	
	public static String dosyaOlusturmaMetodu(File file){
		if(file.exists()){
			return "Bu Dosya ile Aynı İsimde Bir Dosya Var";
		}else{
			try{
				if(file.createNewFile()==true){
					
					return "ONAYLANDI";
				}else{
					return "Hata Oluştur";
				}
			}catch(Exception e){
				return "Hata Oluştur: " +e;
			}
		}
		
		
	}
	
	
	
	public static String klasorOlusturmaMetodu(File file){
		if(file.exists()){
			return "Bu Dosya ile Aynı İsimde Bir Klasör Var";
		}else{
			try{
				if(file.mkdirs()==true){
					
					return "ONAYLANDI";
				}else{
					return "Hata Oluştur";
				}
			}catch(Exception e){
				return "Hata Oluştur: " +e;
			}
		}
		
		
	}
	
	public static void kopyalamaMetodu(File kaynak, File hedef) throws IOException{
		
		if(kaynak.isDirectory()){
		    if(!hedef.exists()){
			    hedef.mkdirs();
		    }	
		    String[] ictekiler = kaynak.list();
		    
		    for(int i = 0; i<ictekiler.length;i++){
			    kopyalamaMetodu(new File(kaynak, ictekiler[i]),new File(hedef, ictekiler[i]));
		    }
		}else{
			dosyaInputStreamileKopyalama(kaynak,hedef);
		//	dosyaFileChannelIleKopyalam(Kaynak,Hedef);
		}
		
	
}
	
public static void dosyaInputStreamileKopyalama(File kaynakDosya,File hedefDosya)throws IOException{
		
                  if(hedefDosya.isDirectory()){
			  hedefDosya = new File(hedefDosya, kaynakDosya.getName());
		  }	
				  
			if(!hedefDosya.exists()){
				hedefDosya.createNewFile();
			}

InputStream inputStream = new FileInputStream(kaynakDosya);
OutputStream outputStream = new FileOutputStream(hedefDosya);

byte[] buffer = new byte[1024];
int uzunluk;

while((uzunluk = inputStream.read(buffer))>0){
	outputStream.write(buffer,0,uzunluk);
}
inputStream.close();
outputStream.close();
		}

public static void dosyaChanneltreamileKopyalama(File kaynakDosya,File hedefDosya)throws IOException{
		
                  if(hedefDosya.isDirectory()){
			  hedefDosya = new File(hedefDosya, kaynakDosya.getName());
		  }	
				  
			if(!hedefDosya.exists()){
				hedefDosya.createNewFile();
			}

	FileChannel kaynak=null,hedef = null; 

	

	try{
		kaynak = new FileInputStream(kaynakDosya).getChannel();
		hedef = new FileOutputStream(hedefDosya).getChannel();
		hedef.transferFrom(kaynak, 0, kaynak.size());
	}catch(Exception e){
		
	}finally{
		if(kaynak!=null){
			kaynak.close();
		}
	}
		}
      
     public static String tasimaMetodu(File kaynak, File hedef){
	     
	     if(!hedef.exists()){
		     hedef.mkdirs();
	     }
	     
	     boolean sonuc = kaynak.renameTo(new File(hedef, kaynak.getName()));
	     
	     if(sonuc){
		     return "Onaylandı";
	     }else{
		     return "Başarısız";
	     }
     }
     
     public static String silmeMetodu(File secilenDosyaveDizin){
	     if(secilenDosyaveDizin.exists()){
		     if(secilenDosyaveDizin.isFile()){
			     if(secilenDosyaveDizin.delete() ==true){
				     return "Onaylandı";
			     }else{
				     return "Hata Oluştu";
			     }
		     }else if(secilenDosyaveDizin.isDirectory()){
			     if(ustKlasorVeIcindekiAltKlasorleriSilmeMetodu(secilenDosyaveDizin)==true){
				     return "Onaylandı";
				     
			     }else{
				     return "Hata Oluştu";
			     }
		     }else{
			     return "Verilen Klasör ya da Dosyaya ait değildir";
		     }
	     }else{
		     return "Silinecek Dosya veya Klasör Bulunamadı";
	     }
     }
     
     public static boolean ustKlasorVeIcindekiAltKlasorleriSilmeMetodu(File dizin){
	     if(dizin.isDirectory()){
		     String[] altKlasorler = dizin.list();
		     for(int i = 0; i < altKlasorler.length;i++){
		     boolean sonuc = ustKlasorVeIcindekiAltKlasorleriSilmeMetodu(new File(dizin, altKlasorler[i]));
		     if(false == sonuc){
			     return false;
		     }
		    
	            }
		    
	     }
	       return dizin.delete();
     }

     
        public static List<String> dosyaOkumaMetodu(File dosya) throws IOException{
		Scanner tarayici = new Scanner(dosya);
		List<String> sonuc = new ArrayList<String>();
		
		while(tarayici.hasNextLine()){
			sonuc.add(tarayici.nextLine());
			
		}
		
		tarayici.close();
		return sonuc;
	}
     
	
	public static boolean dosyaYazmaMetodu(List<String> satirlar, File dosya, boolean ekle)throws IOException{
		
		BufferedWriter cikis = new BufferedWriter(new FileWriter(dosya,ekle));
		
	        	for(String s: satirlar){
				cikis.append(s);
				cikis.newLine();
			}
			
			cikis.close();
			return true;
	}
 
	}

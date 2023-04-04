
package tr.com.siteadi002.sqlserver.baglantisi;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class _001_JDBS_ile_Sq_Serverl_Baglantisi_Statement {
	public static void main(String[] args) {
		
	//1.Adım Değişkenlerini tanımlayalım
	String url = "jdbc:sqlserver://localhost:1433/";
        String veritabaniAdi = "databaseName=PastaneVeritabani;";
	String surucu = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String kullaniciAdi="user=ugur;";
	String kullaniciParolasi = "password=84878487";
	
	Connection con = null;
	Statement stm = null;
	ResultSet rs = null;
	
		try {
			Class.forName(surucu);
			con = DriverManager.getConnection(url+veritabaniAdi,kullaniciAdi,kullaniciParolasi);
			String SQL_Ara = "SELECT * FROM kisiler";
			stm = con.createStatement();
//			String SQL_ARA = "UPDATE kisiler SET"
//				+"adi = '',"
//				+"meslegi = 'Yazılımcı',"
//				+"ehliyet = '1',"
//				+"uzmanlikAlani = 'Mysql',"
//				+"WHERE kisilerId = 5";
//			
//			int guncellenenKayit = stmt.executeUpdate(SQL_ARA);
//			
			String SQL_ARA = "SELECT * FROM tblMusteri";
			stm = con.createStatement();
			rs = stm.executeQuery(SQL_Ara);
			boolean kayitlar = rs.next();
			
			if(!kayitlar){
				System.out.println("Kayıt Yok");
				return;
			}else{
				do{
					String ADISOYADI = null;
					ADISOYADI = rs.getString("musteriAdiSoyadi");
					
					System.out.println(rs.getString(1)+" " + ADISOYADI);
				}while(rs.next());
			}
			
//			if(!kayitlar){
//				System.out.println("Kayıt Yok");
//				return;
//			}else{
//				do{
//					String ADI = null;
//					ADI = rs.getString("adi");
//					String SOYADI = null;
//					SOYADI = rs.getString("soyadi");
//					System.out.println(rs.getString(1)+" " + ADI +" " +SOYADI);
//				}while(rs.next());
//			}
			
		} catch (Exception e) {
			System.out.println("Hata : "+e);
		}finally{
			if(rs != null){
		   try{
			rs.close();   
		   }catch(Exception e){
			   
		   }
			}
			
			if(stm != null){
		   try{
			stm.close();   
		   }catch(Exception e){
			   
		   }
			} 
			
			if(con != null){
		   try{
			con.close();   
		   }catch(Exception e){
			   
		   }
			}
		}
		
		
    }
}

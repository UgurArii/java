
package tr.com.siteadi001.mysqlbaglantisi;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class _001_JDBS_ile_Mysql_Baglantisi_Statement {
	public static void main(String[] args) {
		
	//1.Adım Değişkenlerini tanımlayalım
	String url = "jdbc:mysql://localhost:3306/";
        String veritabaniAdi = "sirket";
	String surucu = "com.mysql.jdbc.Driver";
	String kullaniciAdi="root";
	String kullaniciParolasi = "";
	
	Connection con = null;
	Statement stm = null;
	ResultSet rs = null;
	
		try {
			Class.forName(surucu);
			con = DriverManager.getConnection(url+veritabaniAdi,kullaniciAdi,kullaniciParolasi);
//			
//			String SQL_ARA = "UPDATE kisiler SET"
//				+"adi = 'Uğur',"
//				+"meslegi = 'Bilgisayar',"
//				+"ehliyet = '0',"
//				+"uzmanlikAlani = 'PHP',"
//				+"WHERE kisiler = '3'";
//			
//			int guncellenenKayit = stm.executeUpdate(SQL_ARA);
//			if(guncellenenKayit != 0){
//				System.out.println(guncellenenKayit+"Kayıt Güncellendi");
//			}else{
//				System.out.println("Kayıt Güncellenmedi");
//			}
			
			String SQL_Ara = "SELECT * FROM kisiler";
			stm = con.createStatement();
			rs = stm.executeQuery(SQL_Ara);
			boolean kayitlar = rs.next();
			
			if(!kayitlar){
				System.out.println("Kayıt Yok");
				return;
			}else{
				do{
					String ADI = null;
					ADI = rs.getString("adi");
					String SOYADI = null;
					SOYADI = rs.getString("soyadi");
					System.out.println(rs.getString(1)+" " + ADI +" " +SOYADI);
				}while(rs.next());
			}
			
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

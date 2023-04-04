
package tr.com.siteadi003.access.baglantisi;
import tr.com.siteadi001.mysqlbaglantisi.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class _001_JDBS_ile_Accessi_Statement {
	public static void main(String[] args) {
		
	//1.Adım Değişkenlerini tanımlayalım
	String baglantiURL = "jdbc:odbc:Driver={Microsoft Access Driver  (*.mdb,*.accdb)};"
		+ "DBQ=C:\\Users\\uğur\\Documents\\NetBeansProjects\\JavaGUI_0014_Veritabani\\Veritabani20181.accdb";
	        Connection con = null;
	        Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(baglantiURL);
			
			String SQL_ARA = " UPDATE YAZAR SET "
				+" ADI = 'Uğur', "
				+" SOYADI = 'Arı', "
				+" WHERE YAZARID = '3' ";
			stm = con.createStatement();
			int guncellenenKayit = stm.executeUpdate(SQL_ARA);
			if(guncellenenKayit != 0){
				System.out.println(guncellenenKayit+"Kayıt Güncellendi");
			}else{
				System.out.println("Kayıt Güncellenmedi");
			}
			
			String SQL_Ara = "SELECT * FROM YAZAR";
			Statement stm2 = con.createStatement();
			rs = stm2.executeQuery(SQL_Ara);
			boolean kayitlar = rs.next();
			
			if(!kayitlar){
				System.out.println("Kayıt Yok");
				return;
			}else{
				do{
					String ADI = null;
					ADI = rs.getString("ADI");
					String SOYADI = null;
					SOYADI = rs.getString("SOYADI");
					System.out.println(rs.getString(1)+" " + ADI +" " +SOYADI+ " " + rs.getString("EPOSTA"));
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

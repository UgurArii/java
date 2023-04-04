
package tr.com.siteadi001.mysqlbaglantisi;
import java.sql.*;

public class _003_JDBS_ile_Mysql_Baglantisi_PreparedStatement {
	public static void main(String[] args) {
		
	//1.Adım Değişkenlerini tanımlayalım
	
	Connection con = null;
	Statement stm = null;
	ResultSet rs = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sirket","root","");
			
			Statement s = con.createStatement();
			int eklenenSayi;
			
			//bolum tablosu varsa silinecek
			s.executeUpdate("DROP TABLE IF EXISTS bolum");
			
			//bolum tablosu oluşturulacak
			s.executeUpdate("CREATE TABLE 'bolum' ("
				+ "'bolumid' int(11) NOT NULL AUTO_INCREMENT,"
				+ "'bolumadi' varchar(45) NOT NULL,"
				+ "'bolundugukat' varchar(45) NOT NULL,"
				+ "  PRIMARY KEY (`bolumid`)"
				+ ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8$$");


			s.close();
			
			
			
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

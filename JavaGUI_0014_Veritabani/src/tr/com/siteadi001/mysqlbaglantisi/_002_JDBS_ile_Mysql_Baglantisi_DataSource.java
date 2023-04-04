
package tr.com.siteadi001.mysqlbaglantisi;
import java.sql.*;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;
public class _002_JDBS_ile_Mysql_Baglantisi_DataSource {
        static Connection con = null;
	static Statement stm = null;
        static ResultSet rs = null;
	BasicDataSource bdSource = new BasicDataSource();
	public _002_JDBS_ile_Mysql_Baglantisi_DataSource() {
		//1.Adım Değişkenlerini tanımlayalım
		bdSource.setUrl("jdbc:mysql://localhost:3306/");
		bdSource.setDriverClassName("com.mysql.jdbc.Driver");
		bdSource.setUsername("root");
		bdSource.setPassword("");
	}
	
	public Connection baglantiOlustur(){
		try{
			if(con != null){
				System.err.println("Bağlantı Oluşturulamadı");
			}else{
				con = bdSource.getConnection();
				System.out.println("Bağlantı Başarılı");
				JOptionPane.showMessageDialog(null, "Başarılı Bağlantı");
			}
		}catch(Exception e){
			System.out.println("Hata " + e);
		}
		return con;
	}
	
	public static void main(String[] args) {
	
		try {
			
			_002_JDBS_ile_Mysql_Baglantisi_DataSource nesne = new _002_JDBS_ile_Mysql_Baglantisi_DataSource();
			con = nesne.baglantiOlustur();
	
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

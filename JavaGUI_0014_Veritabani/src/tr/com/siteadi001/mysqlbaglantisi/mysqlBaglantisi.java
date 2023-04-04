
package tr.com.siteadi001.mysqlbaglantisi;

import java.sql.*;
import javax.swing.*;
public class mysqlBaglantisi {
	
	public static Connection baglanti(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection baglanti = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/sirket","root","");
		JOptionPane.showMessageDialog(null,"Bağlantı Başarılı : ", "Mesaj",JOptionPane.INFORMATION_MESSAGE);
			return baglanti;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"HATA : "+e, "Mesaj",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}


package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class config {
    
   public static Connection getConnection(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ska_db", "root", "");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    
}

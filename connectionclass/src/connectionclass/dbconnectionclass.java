
package connectionclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbconnectionclass {

  private static String url="jdbc:mysql://localhost:3306/cci";
private static String user="root";
private static String password="";
private static String driver="com.mysql.jdbc.Driver";
 Connection connMethod() throws ClassNotFoundException, SQLException{
   Class.forName(driver);
   Connection con=null;
  
   try{
        con=DriverManager.getConnection(url,user,password); 
      // String insertquery = "INSERT INTO 'dept_tb3'('SID','studId','Firstname','Lastname','Section','Department')values('4','NSR/004/14','Adane','Girma','SecA','CS')";
   

   }catch(SQLException e){
       
     JOptionPane.showMessageDialog(null, "Error :" +e); 
   }
 
   
   return con;



    }

 
 
 
 
 
    
}

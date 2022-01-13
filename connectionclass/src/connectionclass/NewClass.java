

package connectionclass;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import javax.management.Query;
import javax.swing.JOptionPane;
/**
 *
 * @author tsede
 */
public class NewClass {
private static String url="jdbc:mysql://localhost:3306/cci";
private static String user="root";
private static String password="";
private static String driver="com.mysql.jdbc.Driver";
 
 Connection connMethod() throws ClassNotFoundException, SQLException{
   Class.forName(driver);
   Connection con=null;
  
   try{
        con=DriverManager.getConnection(url,user,password); 
       
   }catch(SQLException e){
       
     JOptionPane.showMessageDialog(null, "Error :" +e); 
   }
 
  
   try{ 
      Statement  stmt = con.createStatement();
      // String executeQuery = null;
       ResultSet res=stmt.executeQuery("select * from dept_tb3");
       //res=stmt.executeQuery("insert into dept_tb3(SID,studId,Firstname,Lastname,Sec,Department) values ('3','NSR/004/14','Adane','Girma','SecA','CS'");
        while(res.next()){
            
           System.out.println(res.getString(1)+" \t"+res.getString(2)+" \t "+res.getString(3)+" \t"+res.getString(4)+" \t"+res.getString(5)+" \t"+res.getString(6));
            //JOptionPane.showMessageDialog(null, "succesfuly"); 
            
       }
     
   }catch(Exception e){
   JOptionPane.showMessageDialog(null, "Error :" +e); 
       
   }
   
      
   
   
   
   
   return con;



    }
}
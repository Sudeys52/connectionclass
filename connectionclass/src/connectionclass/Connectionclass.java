
package connectionclass;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;


public class Connectionclass extends Application {
    //String data;
     private TableView table = new TableView<Table1>();
 private ObservableList<ObservableList> data;
public Text msg =new Text("WELL COME"); 
public Text ut =new Text("SID");
public Text pt =new Text("studID");
public Text fn =new Text("Firstname");
public Text ln =new Text("Lasatname");
public Text sec =new Text("Section");
public Text dep =new Text("Department");




public TextField uf= new TextField();
public TextField pf= new TextField();
public TextField fnf= new TextField();
public TextField lnf= new TextField();
public TextField secf= new TextField();
public TextField depf= new TextField();

Button b1 = new Button("insert");
Button b2 = new Button("update");
Button b3 = new Button("view");
Button b4 = new Button("Distnict");
   // private Paint table;
    public void start(Stage primaryStage) {
         b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              dbconnectionclass ne = new dbconnectionclass();
                String sql = "Insert into dept_tb3 (SID, studId, Firstname, Lastname,Section, Department ) Values (?,?,?,?,?,?)";
                String ut = uf.getText();
                String pt = pf.getText();
                String fn = fnf.getText();
                String ln = lnf.getText();
                String sec = secf.getText();
                String dep = depf.getText();
                try {
                    Connection con = ne.connMethod();

                    PreparedStatement pre;
                    try {
                        pre = con.prepareStatement(sql);
                        pre.setString(1, ut);
                        pre.setString(2, pt);
                        pre.setString(3, fn);
                        pre.setString(4, ln);
                        pre.setString(5, sec);
                        pre.setString(6, dep);
                        int i = pre.executeUpdate();
                        if (i == 1) {

                            //AlertDialog.("info","Data Inserted succsecfully");
                            System.out.println("Data Inserted succsecfully");
                        }

                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(Connectionclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Connectionclass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Connectionclass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
  
b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            { dbconnectionclass ne = new dbconnectionclass();
                Connection con = null;
     
                try
                {
               
                    con=ne.connMethod();
                    String Aman="Aman";
                    String txx="Teshome";
           String sql = "UPDATE dept_tb3 SET Firstname='"+txx+"' WHERE Firstname='"+Aman+"'";
 
PreparedStatement statement = con.prepareStatement(sql);

             statement.executeUpdate();
              JOptionPane.showMessageDialog(null,"The Data Is Updated successfully!");
            }
                catch(Exception ex)
                {
                  System.out.println(ex.getMessage());  
                }
        }
        });

b3.setOnAction(new EventHandler<ActionEvent>() {
 private ObservableList<ObservableList> data;

            @Override
            public void handle(ActionEvent event) 
            {
             
        
        Connection con;
        ResultSet rs;
        data = FXCollections.observableArrayList();
        try {

            table.setStyle("-fx-background-color:red; -fx-font-color:green ");
         dbconnectionclass ne = new dbconnectionclass();
            con = ne.connMethod();
                String SQL = "SELECT * from dept_tb3";
                rs = con.createStatement().executeQuery(SQL);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                table.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }


            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
                }
                System.out.println("Row[1]added " + row);
                data.add(row);

            }


            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }
            }
});

   
        
       GridPane root = new GridPane();
   VBox vbox = new VBox();
            root.add(msg,0,0);
       
            root.add(ut,0,1);
       
    root.add(uf,1,1);
    root.add(pt,0,2);
     root.add(pf,1,2);
      root.add(fn,0,3);
      root.add(fnf,1,3);
      root.add(ln,0,4);
      root.add(lnf,1,4);
      root.add(sec,0,5);
      root.add(secf,1,5);
      root.add(dep,0,6);
       root.add(depf,1,6);
       root.add(b1,0,7);
      root.add(b2,1,7);
     root.add(b3,2,7);
       root.add(b4,3,7);
      root.add(table,0,8);
    
       
       
b4.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;

            //private TableView tbl;
            @Override
            public void handle(ActionEvent event) {

              dbconnectionclass ne = new dbconnectionclass();
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                     table.setStyle("-fx-background-color:red; -fx-font-color:green ");
                    c = ne.connMethod();
                    String SQL = "SELECT distinct Section from dept_tb3";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        col.setMinWidth(100);
                        table.getColumns().addAll(col);
                       // System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                       // System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }

            }
        });
     
      Scene scene = new Scene(root, 800, 550);
        
        primaryStage.setTitle("___JAVA ASSIGNMENT TWO__");
        primaryStage.setScene(scene);
        primaryStage.show();
         


  
        
    } 
     
        
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(args);
        dbconnectionclass ne = new dbconnectionclass();
          NewClass nn=new NewClass();
      Connection conn=nn.connMethod();
       //if(conn!=null)
       
        // JOptionPane.showMessageDialog(null, "connected");
     // else
      //   JOptionPane.showMessageDialog(null, "not connected"); 
        
    
}
}
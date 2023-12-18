

package Dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class Main{
    
    private Connection conectar = null;
    private final String usuario = "root";
    private final String contraseña ="123456789";
    private final String db ="sigloxxi";
    private final String ip ="127.0.0.1";
    private final String puerto = "3306";        
    private final String cadena = "jdbc:mysql://"+ ip +":"+ puerto + "/" + db;
    
    public Connection establecerConeccion(){
    try{
        conectar = DriverManager.getConnection(cadena,usuario,contraseña);
       // JOptionPane.showMessageDialog(null,"Se genero la conexion");
                }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"Se genero la conexion" + e);
    }
    return conectar;
    }
        
        
               
    public static void main(String[] args){
Main app = new Main();
app.establecerConeccion();

}
}
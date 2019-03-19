package laboratorio3;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**Clase para conectar la base de datos
 * phpmyadmin con netbeans
 * 
 */
public class Conectar {
    //variables que contendran las especificaciones para la coneccion.
    private String sDriver = "com.mysql.jdbc.Driver";
    private String sCadenaConeccion = "jdbc:mysql://127.0.0.1/planilladb";
    private String sUsuario = "root";
    private String sContraseña = "";
    
    public Connection conect;
    public Conectar(){
        try {
            Class.forName(sDriver);
            conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
            JOptionPane.showMessageDialog(null, "Coneccion Exitosa");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error\n"+ex);
        }    
    }
}

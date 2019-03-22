package laboratorio3;
import java.sql.*;
import javax.swing.*;
/**Clase para conectar la base de datos
 * phpmyadmin con netbeans
 * servidor de uso local
 */
public class Conectar {
    //variables que contendran las especificaciones para la coneccion.
    private String sDriver = "com.mysql.jdbc.Driver";
    private String sCadenaConeccion = "jdbc:mysql://localhost:3306/dbplanilla";
    private String sUsuario = "root";
    private String sContraseña = "1234"; 
    public Connection conect;
    
    public Conectar(){
        try {
            Class.forName(sDriver);
            conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error\n"+ex);
        }    
    }
    
}

package laboratorio3;
import java.sql.*;
import javax.swing.*;
/**Clase para conectar la base de datos
 * phpmyadmin con netbeans
 * servidor de uso local
 */
public class Conectar {
    //variables que contendran las especificaciones para la coneccion.
    public static Connection conect;
    public static void Conectar(){
        String sDriver = "com.mysql.jdbc.Driver";
        String sCadenaConeccion = "jdbc:mysql://localhost:3306/dbplanilla";
        String sUsuario = "root";
        String sContraseña = ""; 
        try {
            Class.forName(sDriver);
            conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error\n"+ex);
        }    
    }
    
}

package laboratorio3;
import java.sql.*;
/**
 *
 * @author Billy Sican
 */
public class SubiraSQL {
    //Registrar en la tabla Empleados
    public static void SubirEmpleados(int intInfo[][], String sNombre[][], double[][] dblSueldo, int intPos){
        String sCadenaConeccion = "jdbc:mysql://localhost:3306/dbplanilla";
        String sUsuario = "root";
        String sContraseña = "1234"; 
        
        String [][] sSubirSQL = new String[1][5];
        sSubirSQL[0][0] = ConsultasSQL.CodigoEmpleado();
        sSubirSQL[0][1] = sNombre[intPos][0];
        sSubirSQL[0][2] = Double.toString(dblSueldo[intPos][0]);
        sSubirSQL[0][3] = Integer.toString(intInfo[intPos][2]);
        sSubirSQL[0][4] = Integer.toString(intInfo[intPos][3]);
        try{

            Connection conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
            PreparedStatement pst = conect.prepareStatement("INSERT INTO empleado VALUES(?,?,?,?,?)");
            pst.setString(1, sSubirSQL[0][0]);
            pst.setString(2, sSubirSQL[0][1]);
            pst.setString(3, sSubirSQL[0][2]);
            pst.setString(4, sSubirSQL[0][3]);
            pst.setString(5, sSubirSQL[0][4]);
            pst.executeUpdate();
            System.out.println("Registro Empleados Exitoso");
        }catch(Exception e){
            System.out.println(e);
        } 
    }
    
    
    public static void SubirNominaEncabezado(String sFechaI, String sFechaF, String sTotal){
        String sCadenaConeccion = "jdbc:mysql://localhost:3306/dbplanilla";
        String sUsuario = "root";
        String sContraseña = "1234"; 
        
        String [][] sSubirSQL = new String[1][4];
        sSubirSQL[0][0] = ConsultasSQL.NumeroNomina();
        sSubirSQL[0][1] = sFechaI;
        sSubirSQL[0][2] = sFechaF;
        sSubirSQL[0][3] = sTotal;
        try{
           Connection conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
           PreparedStatement pst = conect.prepareStatement("INSERT INTO encabezado_nomina VALUES(?,?,?,?)");
           
           pst.setString(1, sSubirSQL[0][0]);
           pst.setString(2, sSubirSQL[0][1]);
           pst.setString(3, sSubirSQL[0][2]);
           pst.setString(4, sSubirSQL[0][3]);
           pst.executeUpdate();
           System.out.println("Registro Nomina Encabezado Exitoso");
        }catch(Exception e){
           System.out.println(e);
        }
    }
    
    public static void SubirConcepto(String sCodigoC, double[][] dblPlani, int iFila, int iColumna){
        String sCadenaConeccion = "jdbc:mysql://localhost:3306/dbplanilla";
        String sUsuario = "root";
        String sContraseña = "1234";
        
        String [][] sSubirSQL = new String[1][4];
        sSubirSQL[0][0] = ConsultasSQL.NumeroNomina();
        sSubirSQL[0][1] = ConsultasSQL.CodigoEmpleado();
        sSubirSQL[0][2] = sCodigoC;
        sSubirSQL[0][3] = Double.toString(dblPlani[iFila][iColumna]);
        
        try{
           Connection conect = DriverManager.getConnection(sCadenaConeccion, sUsuario, sContraseña);
           PreparedStatement pst = conect.prepareStatement("INSERT INTO detalle_nomina VALUES(?,?,?,?)");
           
           pst.setString(1, sSubirSQL[0][0]);
           pst.setString(2, sSubirSQL[0][1]);
           pst.setString(3, sSubirSQL[0][2]);
           pst.setString(4, sSubirSQL[0][3]);
           pst.executeUpdate();
           System.out.println("Registro Concepto Exitoso");
        }catch(Exception e){
           System.out.println(e);
        }
    }
    
}

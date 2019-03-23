package laboratorio3;
import java.sql.*;

/**Clase para hacer consultas de la informacion guardada en la BD
 * 
 * @author Billy Sican
 */
public class ConsultasSQL {
    //Datos guradados en una matriz
    public static void ConsultaDepartamento(String [][] sDepartamento){
        Conectar con = new Conectar();
        int intContador=0;
        String sql = "SELECT * FROM departamento";
        try {
            Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                sDepartamento[intContador][0] = rs.getString("codigo_departamento");
                sDepartamento[intContador][1] = rs.getString("nombre_departamento");
                intContador+=1;
            }
        } catch (Exception ex) {
            System.out.println("Error al imprimir"+ex);
        }
    }
    
    public static void ConsultaPuesto(String [][] sPuesto){
        Conectar con = new Conectar();
        int intContador=0;
        String sql = "SELECT * FROM puesto";
        try {
            Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                sPuesto[intContador][0] = rs.getString("codigo_puesto");
                sPuesto[intContador][1] = rs.getString("nombre_puesto");
                intContador+=1;
            }
        } catch (Exception ex) {
            System.out.println("Error al imprimir"+ex);
        }
    }
    
    public static void ConsultaConcepto(String[][] sConcepto){
        
        int intContador=0;
        
        String sql = "SELECT * FROM concepto";
        try {
            Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                sConcepto[intContador][0] = rs.getString("codigo_concepto");
                sConcepto[intContador][1] = rs.getString("nombre_concepto");
                sConcepto[intContador][2] = rs.getString("efecto_concepto");
                intContador+=1;
            }
        } catch (Exception ex) {
            System.out.println("Error al imprimir"+ex);
        }
    }
    
    //obtencion del ultimo valor del registro
    public static String NumeroNomina(){
        Conectar con = new Conectar();
        String sNumeroNomina = "";
        int intNumNomina = 0;
        String sql = "SELECT MAX(codigo_nomina) AS codigo_nomina FROM encabezado_nomina";
        try{
            Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                intNumNomina = (rs.getInt(1)+1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        sNumeroNomina = Integer.toString(intNumNomina);
        return sNumeroNomina;
    }
    
    public static String CodigoEmpleado(){
        String sCodigo = "";
        int intCodigo = 0;
        String sql = "SELECT MAX(codigo_empleado) AS codigo_empleado FROM empleado";
        try{
           Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                intCodigo = (rs.getInt(1)+1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        sCodigo = Integer.toString(intCodigo);
        return sCodigo;
    }
}

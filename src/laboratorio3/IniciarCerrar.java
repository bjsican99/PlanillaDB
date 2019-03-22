package laboratorio3;
import java.util.*;
import java.sql.*;
/**Clase para:
 * Iniciar Sesion: con usuario y contraseña almacenado en una BD.
 * y asi tambien la opcion de cerrar sesion.
 * 
 * @author Billy Sican
 */
public class IniciarCerrar {
        //Metodo Para ingreso con pedida de usuario y contraseña
    //ya conecatado a la BD
    public static int Login(){
        Conectar con = new Conectar();
        Scanner scngua = new Scanner(System.in);
        //Ingreso
        String sUser, sContra;     
        System.out.println("\nIngreso Usuario: ");
        sUser = scngua.nextLine();
        System.out.println("Ingreso Contraseña: ");
        sContra = scngua.nextLine();
        //busqueda
        try{
            con.conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbplanilla","root","1234");
            PreparedStatement pst = con.conect.prepareStatement("select * from dbplanilla.usuarios where nombre_usuario = ?");
            pst.setString(1, sUser.trim());
            ResultSet rs = pst.executeQuery();

            if(rs.next() && sUser.equals(rs.getString("nombre_usuario")) && sContra.equals(rs.getString("clave_usuario"))){
                System.out.println("Bienvenido\n");
                return 1;
            }else{
                System.out.println("Usuario no existente\n");
                return 4;
            }
        }catch(SQLException e){
            System.out.println(e);
            return 0;
        }    
    }
    
    //Metodo al momento de iniciar sesion
    //Llamada de todas las fucniones para llenar la matriz de forma estatica.   
    public static void SesionIniciada(){
        double [][] dblPlanilla = new double[5][8];
        int [] intDepartamento = new int[5];
        String [][] sNombresYDerechoPrestaciones = new String[5][2];   
        //llamado de metos de la clase LlenadoyCalculoPlanilla
        LlenadoyCalculoPlanilla.LlenadoDePlanilla(dblPlanilla, sNombresYDerechoPrestaciones);
        LlenadoyCalculoPlanilla.SumaSueldoDepartamentos(dblPlanilla, intDepartamento);
        Laboratorio3.MostrarPlanillaYVector(dblPlanilla, intDepartamento, sNombresYDerechoPrestaciones);  
    }
    
    //Funcion para Cerrar Sesión
    //con metodo si hay alguna confuncion al momento de ingresar alguna letra erronea
    public static int CerrarSesion(){
        Scanner scngua = new Scanner(System.in);
        String sCerrarSesion;
            System.out.println("Decea Cerrar Sesión?\nS/N:\t");
            sCerrarSesion = scngua.nextLine();
            if(sCerrarSesion.equals("s") || sCerrarSesion.equals("S")){
                return 4;
            }else if(sCerrarSesion.equals("n") || sCerrarSesion.equals("N")){            
                return 0;
            }else{
                System.out.println("Error al escribir la respuesta");
                return 0;
            }          
    }
}

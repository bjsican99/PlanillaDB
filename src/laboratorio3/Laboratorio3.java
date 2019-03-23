package laboratorio3;
import java.text.*;
import java.util.Scanner;
/**
 *Software tipo planilla, para calculo de sueldo liquido, con uso de vectores y matrices.
 * Adicion con calculo de IGSS e ISR dependiendo de un rango de salario.
 * Presentacion
 * @author BillyS
 * 0901-17-16250
 * Derechos de Autor Recervados
 */
public class Laboratorio3 {  
    public static void main(String[] args) { 
        Scanner scngua = new Scanner(System.in);
        int intOpcMenu; 
        //INICIO DE SESION   
        System.out.println("Bienvenido");
        intOpcMenu=IniciarCerrar.Login();
        while(intOpcMenu !=4){
            System.out.println("\n-----Menu-----");
            System.out.println("1-Ingreso De Planilla\n2-Modificaf\n3-Eliminar\n4-Salir/Cerrar Sesion\nEscriba el numero de la opción:");
            intOpcMenu = scngua.nextInt();        
            switch(intOpcMenu){
                case 1:{//llenado
                    IniciarCerrar.SesionIniciada();
                break;}
                case 2:{//Modificacion
                    
                break;}//Eliminar
                case 3:{
                    
                break;}
                case 4:{//Cerrar
                    intOpcMenu = IniciarCerrar.CerrarSesion();
                break;}
            }
            System.out.println("-----------------------------------------------------");
        }
        System.out.println("Sesion Cerrada");
    }
    
    //Imprime en pantalla todos los resultados de la matriz y del vector
    public static void MostrarPlanillaYVector(double[][] dblPlani, double[] dblDepar, String[][] sNombresVec){
        int iconteoaux=1;
        DecimalFormat dfDosDeci = new DecimalFormat("#00000.00");
        DecimalFormat dfUnDeci = new DecimalFormat("#");
        System.out.println("-------------------------------------------"
                        + "----------------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
        int intDepar=1;
        for(int iFila=0; iFila <5; iFila++){   
                
                System.out.println("Nombre: "+sNombresVec[iFila][0]); 
                System.out.println("Departamento: "+intDepar+
                        "\t\t--Sueldo Base: "+dblPlani[iFila][0]+"\t\t--Deducciones: "+dblPlani[iFila][1]+
                        "\t--Prestaciones: "+dblPlani[iFila][2]+" \t--IGSS: "+dfDosDeci.format(dblPlani[iFila][3])+
                        "\t--ISR: "+dfDosDeci.format(dblPlani[iFila][4])+
                        "\t\t--Sueldo Liquido: "+dfDosDeci.format(dblPlani[iFila][5]));
                System.out.println("-------------------------------------------"
                        + "----------------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
                intDepar++;
        }
        System.out.println("---------------------------------------------------"
                        + "--------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
             
    }
}
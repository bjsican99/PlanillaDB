package laboratorio3;
import java.util.*;
import java.text.*;
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
        //Vectores y Matrices
        double [][] dblPlanilla = new double[10][8];
        int [] intDepartamento = new int[5];
        String [][] sNombresYDerechoPrestaciones = new String[10][2];       
        //Principal Llamado de funcione
        System.out.println("Bienvenido");
        LlenadoDePlanilla(dblPlanilla, sNombresYDerechoPrestaciones);
        SumaSueldoDepartamentos(dblPlanilla, intDepartamento);
        MostrarPlanillaYVector(dblPlanilla, intDepartamento, sNombresYDerechoPrestaciones);
    }
    
    public static void LlenadoDePlanilla(double[][] dblPlani, String[][] sNombresDerecho){
        Scanner scngua = new Scanner(System.in);
        int iConteo=1;
        for(int ifila = 0; ifila <10; ifila++){
            System.out.println("Ingrese el Nombre del Empleado #"+iConteo);
            sNombresDerecho[ifila][0] = scngua.nextLine();//Nombres En la matriz
            System.out.println("Calculo a "+sNombresDerecho[ifila][0]+" algun impuesto:\nSI/NO");
            sNombresDerecho[ifila][1] = scngua.nextLine();//Derecho a Prestaciones y Deducciones
            //Llenado de Salarios, Deducciones y Prestaciones
            dblPlani[ifila][0] = ifila;   
            SalariosDeduccionesPrestaciones(dblPlani, ifila, sNombresDerecho); 
            SumaSueldoLiquido(dblPlani, ifila);
            iConteo++;
        }   
    }
    //Llenado de matriz con variables random
    public static void SalariosDeduccionesPrestaciones(double [][] dblPlani, int ifila, String [][] sDerecho){
        String strDerecho = sDerecho[ifila][1].toUpperCase();
        Random rSueldoBase = new Random();
        Random rDeducciones = new Random();
        Random rPersepciones = new Random();
        Random rDepartamento = new Random();
        //Llenado
        dblPlani[ifila][1] = rSueldoBase.nextInt(97500)+2501;
        dblPlani[ifila][7] = rDepartamento.nextInt(5)+1;     
        dblPlani[ifila][2] = rDeducciones.nextInt(500)+100;
        dblPlani[ifila][3] = rPersepciones.nextInt(500)+250;
        //Verificacion de se le calcula IGSS E ISR
        if(strDerecho.equals("SI") || strDerecho.equals("S")){            
            dblPlani[ifila][4] = CalculoIGSS(dblPlani, ifila);
            dblPlani[ifila][5] = CalculoISR(dblPlani, ifila);
        }else{
            dblPlani[ifila][2] = dblPlani[ifila][4] = dblPlani[ifila][5] = 0;           
        }                    
    }
    //Funcion del calculo IGSS
    public static double CalculoIGSS(double [][] dblPlanilla, int ifila){                
        return dblPlanilla[ifila][4] = dblPlanilla[ifila][1] * 0.1067;//IGSS   
    }
    //Funcion del calculo ISR
    public static double CalculoISR(double [][] dblPlanilla, int ifila){
        double [][] dblISR = {{2500,5000,3},{5001,10000,5},{10001,100001,10}};//Matriz del porcentaje al ISR
        for(int iPosISRf=0; iPosISRf <3; iPosISRf++){  //IRS             
            if(dblPlanilla[ifila][1]>=dblISR[iPosISRf][0] && dblPlanilla[ifila][1]<=dblISR[iPosISRf][1]){
                dblPlanilla[ifila][5] = dblPlanilla[ifila][1]*(dblISR[iPosISRf][2]/100);//ISR               
            }             
        }
        return dblPlanilla[ifila][5];
    }
    //Funcion suma y resta los todas las bonificacion y deducciones
    public static double SumaSueldoLiquido(double [][] dblPlanilla, int ifila){
        return dblPlanilla[ifila][6] = dblPlanilla[ifila][1]-dblPlanilla[ifila][2]+dblPlanilla[ifila][3]-dblPlanilla[ifila][4]-dblPlanilla[ifila][5];//Sueldo Liquido 
    }
    //Guarda todos los elementos en el vector dependiendo del departamento al que pertenece
    public static void SumaSueldoDepartamentos(double[][] dblPlani, int[] intDepar){
        int iAuxConteo=1;
        for(int iPosicion = 0; iPosicion < 5; iPosicion++){
            for(int iFila=0; iFila < 10; iFila++){
                if(iAuxConteo == dblPlani[iFila][7]){
                    intDepar[iPosicion] += dblPlani[iFila][6];                   
                }
            }
            iAuxConteo++;
        }
    }
    //Imprime en pantalla todos los resultados de la matriz y del vector
    public static void MostrarPlanillaYVector(double[][] dblPlani, int[] intDepar, String[][] sNombresVec){
        int iconteoaux=1;
        DecimalFormat dfDosDeci = new DecimalFormat("#00000.00");
        DecimalFormat dfUnDeci = new DecimalFormat("#");
        System.out.println("-------------------------------------------"
                        + "----------------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
        
        for(int iFila=0; iFila <10; iFila++){                
                System.out.println("Nombre: "+sNombresVec[iFila][0]); 
                System.out.println("Departamento: "+dfUnDeci.format(dblPlani[iFila][7])+
                        "\t\t--Sueldo Base: "+dblPlani[iFila][1]+"\t\t--Deducciones: "+dblPlani[iFila][2]+
                        "\t--Prestaciones: "+dblPlani[iFila][3]+" \t--IGSS: "+dfDosDeci.format(dblPlani[iFila][4])+
                        "\t--ISR: "+dfDosDeci.format(dblPlani[iFila][5])+
                        "\t\t--Sueldo Liquido: "+dfDosDeci.format(dblPlani[iFila][6]));
                System.out.println("-------------------------------------------"
                        + "----------------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
        }
        System.out.println("---------------------------------------------------"
                        + "--------------------------------------------"
                        + "----------------------------------------------------"
                        + "------------");
        System.out.println("Total Por Departamento\n");
        for(int iPosicion = 0; iPosicion <5;iPosicion++){
            System.out.println("Numero De Departamento "+iconteoaux+": "+intDepar[iPosicion]);
            iconteoaux++;
        }       
    }
}
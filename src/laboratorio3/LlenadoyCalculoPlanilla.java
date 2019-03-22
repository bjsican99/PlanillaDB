package laboratorio3;
import java.util.*;
/**Clase para tener mejor control sobre el codigo de llenado de planilla y su almacenamiento de forma local
 * Para tener un mejor mantenimiento del mismo
 * @author Billy Sican
 */
public class LlenadoyCalculoPlanilla {
    public static double igss = 0.0483;
    public static double [][] dblISR = {{2500,5000,3},{5001,10000,5},{10001,100001,10}};//Matriz del porcentaje al ISR
    //Metodo para el llenado de la planilla
    //almacenamiento temporal para subir todo o nada a la BD
    public static void LlenadoDePlanilla(double[][] dblPlani, String[][] sNombresDerecho){
        Scanner scngua = new Scanner(System.in);
        int iConteo=1;
        for(int ifila = 0; ifila <5; ifila++){
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
        dblPlani[ifila][2] = rDeducciones.nextInt(500)+100;
        dblPlani[ifila][3] = rPersepciones.nextInt(500)+250;
        dblPlani[ifila][7] = rDepartamento.nextInt(5)+1;             
        //Verificacion de se le calcula IGSS E ISR
        if(strDerecho.equals("SI") || strDerecho.equals("S")){            
            dblPlani[ifila][4] = CalculoIGSS(dblPlani, ifila);
            dblPlani[ifila][5] = CalculoISR(dblPlani, ifila);
        }else{
            dblPlani[ifila][4] = dblPlani[ifila][5] = 0;           
        }                    
    }
    
    //Funcion del calculo IGSS
    public static double CalculoIGSS(double [][] dblPlanilla, int ifila){                
        return dblPlanilla[ifila][4] = dblPlanilla[ifila][1] * igss;//IGSS   
    }
    
    //Funcion del calculo ISR
    public static double CalculoISR(double [][] dblPlanilla, int ifila){       
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
}

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
    public static void LlenadoDePlanilla(int[][] intInfo,double[][] dblPlani, String[][] sNombres, double[] dblSumaDepart){
        String sNumNomina; String sTotal; String sFechaInicio; String sFechaFinal;
        Scanner scngua = new Scanner(System.in);
        int iConteo=1;       
        String sCodConcepto;
        sNumNomina = ConsultasSQL.NumeroNomina();

        System.out.println("Nomina Numero: "+sNumNomina);
        System.out.println("Ingrese La Fecha De Inicio De La Nomina:");
        sFechaInicio = scngua.nextLine();
        System.out.println("Ingrese La Fecha De Final De La Nomina:");
        sFechaFinal = scngua.nextLine();
        
        //Ciclo para guardar los registros en nomina y nomina detalle
        for(int ifila = 0; ifila <5; ifila++){
            //Datos Basico: Codigo, Nombre, Puesto, Departamento.
            System.out.println("Ingrese el Nombre del Empleado #"+iConteo);
            sNombres[ifila][0] = scngua.nextLine();//Nombres En la matriz tipo string
            dblPlani[ifila][0] = ifila;  //codigo 
            DepartamentoPuesto(intInfo, dblPlani, ifila);
            //subir a empleados
            SubiraSQL.SubirEmpleados(intInfo, sNombres, dblPlani, ifila);
            //Salario
            for(int iConcep=1; iConcep<=5;iConcep++){
                sCodConcepto = CodigoConcepto(iConcep);
                if(iConcep == 5){
                    SumaSueldoLiquido(dblPlani, ifila);
                    SubiraSQL.SubirConcepto(sCodConcepto, dblPlani, ifila, iConcep);
                }else{
                    dblPlani[ifila][iConcep] = SalariosDeduccionesPrestaciones(dblPlani, ifila, iConcep);                
                    SubiraSQL.SubirConcepto(sCodConcepto, dblPlani, ifila, iConcep);
                }               
            }           
            iConteo++;
        } 
        sTotal = TotalNomina(dblSumaDepart);
        SubiraSQL.SubirNominaEncabezado(sFechaInicio, sFechaFinal, sTotal);
    }
    
    public static void DepartamentoPuesto(int intInfo[][], double[][] dblPlani, int ifila){
        Random rSueldoBase = new Random();
        Random rDepartamento = new Random();
        Random rPuesto = new Random();
        
        intInfo[ifila][1] = rDepartamento.nextInt(4)+1;  
        intInfo[ifila][2] = rPuesto.nextInt(4)+1;
        dblPlani[ifila][0] = rSueldoBase.nextInt(97500)+2501;
    }
    
    //Llenado de matriz con variables random
    public static double SalariosDeduccionesPrestaciones(double [][] dblPlani, int ifila, int iColumna){  
        Random rDeducciones = new Random();
        Random rPersepciones = new Random();
        if(iColumna == 1){
            return dblPlani[ifila][1] = rDeducciones.nextInt(500)+100;
        }else if(iColumna == 2){
            return dblPlani[ifila][2] = rPersepciones.nextInt(500)+250;   
        }else if(iColumna == 3){
            return dblPlani[ifila][3] = CalculoIGSS(dblPlani, ifila);
        }else{
            return dblPlani[ifila][4] = CalculoISR(dblPlani, ifila); 
        }
    }
    
    public static String CodigoConcepto(int iConcep){
        if(iConcep == 1){
            return "1";
        }else if(iConcep == 2){
            return "2";  
        }else if(iConcep == 3){
            return "3";
        }else if(iConcep==4){
            return "4"; 
        }else{
            return "5";
        }
    }
    
    
    //Funcion del calculo IGSS
    public static double CalculoIGSS(double [][] dblPlanilla, int ifila){                
        return dblPlanilla[ifila][3] = dblPlanilla[ifila][0] * igss;//IGSS   
    }
    
    //Funcion del calculo ISR
    public static double CalculoISR(double [][] dblPlanilla, int ifila){       
        for(int iPosISRf=0; iPosISRf <3; iPosISRf++){  //IRS             
            if(dblPlanilla[ifila][0]>=dblISR[iPosISRf][0] && dblPlanilla[ifila][0]<=dblISR[iPosISRf][1]){
                dblPlanilla[ifila][4] = dblPlanilla[ifila][0]*(dblISR[iPosISRf][2]/100);//ISR               
            }             
        }
        return dblPlanilla[ifila][4];
    }
    
    //Funcion suma y resta los todas las bonificacion y deducciones
    public static double SumaSueldoLiquido(double [][] dblPlanilla, int ifila){
        String [][] sConcepto = new String [5][3];
        ConsultasSQL.ConsultaConcepto(sConcepto);
        for(int iFila = 0; iFila<5; iFila++){
            if(sConcepto[iFila][2].equals("+")){
                dblPlanilla[ifila][5] += dblPlanilla[ifila][iFila];
            }else{
                dblPlanilla[ifila][5] += (dblPlanilla[ifila][iFila])*(-1);
            }
        }
        return dblPlanilla[ifila][5];
    }
    
    //Guarda todos los elementos en el vector dependiendo del departamento al que pertenece
    public static void SumaSueldoDepartamentos(int intInfo[][], double[][] dblPlani, double[] dblDepar){
        int iAuxConteo=1;
        for(int iPosicion = 0; iPosicion < 5; iPosicion++){
            for(int iFila=0; iFila < 5; iFila++){
                if(iAuxConteo == intInfo[iFila][1]){
                    dblDepar[iPosicion] += dblPlani[iFila][5];                   
                }
            }
            iAuxConteo++;
        }
    }
    
    public static String TotalNomina(double[] dblDepar){
        double dblTotal=0.0;
        String sTotal;
        for(int iPos =0; iPos<5;iPos++){
            dblTotal += dblDepar[iPos];
        }
        sTotal = Double.toString(dblTotal);
        return sTotal;
    }
}

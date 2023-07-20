
package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class optimizador {
    int etiqueta = 0;
    boolean reroll = false;
    double x,y,z;
    List<simbolo> tabla;
    List<String> operaciones;
    List<String> d;
    List<String> j;
    /*List<String> buffer;
    List<String> buffer2;
    List<String> bufferOP;*/
    
    optimizador(List<String> d,List<simbolo> tabla, List<String> operaciones){
        this.d = d;
        this.tabla = tabla;
        this.operaciones = operaciones;
        j = new ArrayList<>();
        System.out.println("\n...GENERADOR DE CODIGO INTERMEDIO OPTIMIZADO...\n");
        pruebas();
    }
    
    private void pruebas(){ 
        for(int i = 0; i<d.size();i++){
            if(d.get(i).charAt(0) == 't'){
                if(d.get(i+2).charAt(0)=='0' || d.get(i+2).charAt(0)=='1' || d.get(i+2).charAt(0)=='2'
                        || d.get(i+2).charAt(0)=='3'|| d.get(i+2).charAt(0)=='4'|| d.get(i+2).charAt(0)=='5'
                        || d.get(i+2).charAt(0)=='6'|| d.get(i+2).charAt(0)=='7'|| d.get(i+2).charAt(0)=='8'
                        || d.get(i+2).charAt(0)=='9'){
                    if(d.get(i+4).charAt(0)=='0' || d.get(i+4).charAt(0)=='1' || d.get(i+4).charAt(0)=='2'
                        || d.get(i+4).charAt(0)=='3'|| d.get(i+4).charAt(0)=='4'|| d.get(i+4).charAt(0)=='5'
                        || d.get(i+4).charAt(0)=='6'|| d.get(i+4).charAt(0)=='7'|| d.get(i+4).charAt(0)=='8'
                        || d.get(i+4).charAt(0)=='9'){
                        System.out.println("Numero: "+d.get(i+2));
                        System.out.println("Numero: "+d.get(i+4));
                        
                        x = Double.parseDouble(d.get(i+2));
                        y = Double.parseDouble(d.get(i+4));
                        if(d.get(i+3).equals("+"))
                            z=x+y;
                        if(d.get(i+3).equals("-"))
                            z=x-y;
                        if(d.get(i+3).equals("*"))
                            z=x*y;
                        if(d.get(i+3).equals("/"))
                            z=x/y;
                        System.out.println("Resultado: "+z);
                        
                        reroll = true;
                    }
                }
            }
            System.out.println(d.get(i));
        }
    }
}

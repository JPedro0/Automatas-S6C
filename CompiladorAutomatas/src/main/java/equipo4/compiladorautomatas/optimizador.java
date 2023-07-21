
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
        imprimir();
    }
    
    private void pruebas(){ 
        for(int i = 0; i<d.size();i++){
            if(d.get(i).charAt(0) == 't'){
                if(d.get(i+2).charAt(0)=='0' || d.get(i+2).charAt(0)=='1' || d.get(i+2).charAt(0)=='2'
                        || d.get(i+2).charAt(0)=='3'|| d.get(i+2).charAt(0)=='4'|| d.get(i+2).charAt(0)=='5'
                        || d.get(i+2).charAt(0)=='6'|| d.get(i+2).charAt(0)=='7'|| d.get(i+2).charAt(0)=='8'
                        || d.get(i+2).charAt(0)=='9' || d.get(i+2).charAt(0)=='-'){
                    if(d.get(i+4).charAt(0)=='0' || d.get(i+4).charAt(0)=='1' || d.get(i+4).charAt(0)=='2'
                        || d.get(i+4).charAt(0)=='3'|| d.get(i+4).charAt(0)=='4'|| d.get(i+4).charAt(0)=='5'
                        || d.get(i+4).charAt(0)=='6'|| d.get(i+4).charAt(0)=='7'|| d.get(i+4).charAt(0)=='8'
                        || d.get(i+4).charAt(0)=='9' || d.get(i+4).charAt(0)=='-'){
                        //System.out.println("Numero: "+d.get(i+2));
                        //System.out.println("Numero: "+d.get(i+4));
                        
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
                        if(d.get(i+3).equals("^"))
                            z=Math.pow(x, y);
                        //System.out.println("Resultado: "+z);
                        
                        reroll = true;
                        
                        for(int l=(i+1);l<=(i+10);l++){
                            if(d.get(l).equals(d.get(i))){
                                d.set(l, String.valueOf(z));
                            }
                        }
                               
                        int a=0;
                        for(int l=i;l<(i+6);){
                            //System.out.println("-----Valor: "+l+" . Lexema: "+d.get(l));
                            d.remove(l);
                            a=a+1;
                            if(a==6)
                                break;
                        }
                    }
                }
            }
            //System.out.println("Valor: "+i+" . Lexema: "+d.get(i));
        }
        
        if(reroll){
            reroll=false;
            pruebas();
        }
    }
    
    private void imprimir(){
        for(int i=0;i<d.size();i++){
            System.out.println("Valor: "+i+" . Lexema: "+d.get(i));
        }
    }
}


package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class ensamblador {
    
    List<simbolo> tabla;
    List<String> origen;
    List<String> codigo;
    
    ensamblador(List<String> d,List<simbolo> tabla){
        this.tabla = tabla;
        this.origen = d;
        this.codigo = new ArrayList<>();
        System.out.println("\n...GENERADOR DE CODIGO OBJETO...\n");
        variables();
        recorredor();
        imprimir();
    }
    
    private void variables(){
        codigo.add(".DATA");
        for(int i=1;i<tabla.size();i++){
            codigo.add("\t"+tabla.get(i).ide+" DB '0'");
        }
        codigo.add("\tSaltodeLinea db 10,13,'$'");
        for(int i=0;i<10;i++){
            codigo.add("\tt"+i+" DB '0'");
        }
    }
    
    private void recorredor(){
        codigo.add(".CODE");
        codigo.add(".STARTUP");
        //Codigo para iniciar
        codigo.add("\tmov ax, @data");
        codigo.add("\tmov ds, ax\n");
        //Codigo que se generara con el PF
        for(int i=0;i<origen.size();i++){
            //Saltos de linea
            if (origen.get(i).charAt(0)== 'L' && origen.get(i).charAt(origen.get(i).length()-1) == ':'){
                codigo.add("\t"+origen.get(i));
            }
            for(int j=1;j<tabla.size();j++){
                if(origen.get(i).equals(tabla.get(j).ide) && origen.get(i+1).equals("=")){
                    codigo.add(origen.get(i));
                }
            }
            if(origen.get(i).equals("if")){
                codigo.add(origen.get(i));
            }
            if(origen.get(i).equals("else")){
                codigo.add(origen.get(i));
            }
            if(origen.get(i).equals("while")){
                codigo.add(origen.get(i));
            }
            if(origen.get(i).equals("read")){
                codigo.add(origen.get(i));
            }
            if(origen.get(i).equals("print")){
                codigo.add(origen.get(i));
            }
            
            
        }
        
        //Codigo para finalizar
        codigo.add("\n\tmov ax, 4c00h");
        codigo.add("\tint 21h");
    }
    
    private void imprimir(){
        for(int i=0;i<codigo.size();i++){
            System.out.println(codigo.get(i));
        }
    }
}


package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class genIntermedio {
    nodo p;
    List<simbolo> tabla;
    List<String> operaciones;
    List<String> d;
            
    genIntermedio(nodo sintaxis, List<simbolo> tabla, List<String> operaciones){
        p = sintaxis;
        this.tabla = tabla;
        this.operaciones = operaciones;
        d = new ArrayList<>();
        pruebas();
        generador();
    }
    
    private void pruebas(){
        System.out.println("\n...GENERADOR DE CODIGO INTERMEDIO...");
        //System.out.println(p.lexema);
        System.out.println("\n");
        for(int q = 1;q<tabla.size();q++){
            System.out.println(tabla.get(q).ide +" "+ tabla.get(q).tipo +" "+ tabla.get(q).num_linea + " " + tabla.get(q).tamaño);
        }
        System.out.println("\n");
        for(int l = 0; l<operaciones.size();l++){
            System.out.println(operaciones.get(l));
        }
        System.out.println("\n");
        
    }
    
    private void generador(){
        while(p.sig != null){
            if(p.lexema.equals("class") || p.lexema.equals("{") ||p.lexema.equals("}") || p.lexema.equals(tabla.get(0).ide)  ){
                d.add(p.lexema);
                p = p.sig;
            }
            else{
                //d.add(p.lexema);
                p = p.sig;
            }
        }
        for(int i = 0; i<d.size();i++){
            System.out.println(d.get(i));
        }
    }
    
    private void notacionP(){
        
    }
}

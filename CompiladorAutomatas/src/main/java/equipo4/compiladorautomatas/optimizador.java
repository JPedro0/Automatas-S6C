
package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class optimizador {
    int etiqueta = 0;
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
        System.out.println("IDE - TIPO - RENGLON - TAMAÑO EN MEMORIA");
        for(int q = 1;q<tabla.size();q++){
            System.out.println(tabla.get(q).ide +" "+ tabla.get(q).tipo +" "+ tabla.get(q).num_linea + " " + tabla.get(q).tamaño);
        }
        System.out.println("\nOperaciones del programa fuente");
        for(int l = 0; l<operaciones.size();l++){
            System.out.println(operaciones.get(l));
        }
        System.out.println("\n");
        
    }
}


package equipo4.compiladorautomatas;

public class nodo {
    String lexema;
    int token, renglon;
    nodo sig = null;
    
    nodo(String lexema, int token, int renglon){
        this.lexema = lexema;
        this.token = token;
        this.renglon = renglon;
    };
}

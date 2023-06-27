
package equipo4.compiladorautomatas;

public class simbolo {
    String ide, tipo, valor;
    int num_linea;
    boolean ambito;
    
    simbolo(String ide, String tipo, String valor, int num_linea, boolean ambito){
        this.ide = ide;
        this.tipo = tipo;
        this.valor = valor;
        this.num_linea = num_linea;
        this.ambito = ambito;
    };
}

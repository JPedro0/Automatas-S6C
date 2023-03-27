package equipo4.compiladorautomatas;

import java.io.IOException;

public class CompiladorAutomatas {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        lexico lexico = new lexico();
            if(!lexico.errorEncontrado){
                System.out.println("Analisis lexico terminado");
            }
    }
}

package equipo4.compiladorautomatas;

public class CompiladorAutomatas {

    public static void main(String[] args) {
        compi lexico = new compi();
            if(!lexico.errorEncontrado){
                System.out.println("Analisis lexico terminado");
                
                lexico.sintaxis();
                
                if(!lexico.errorEncontrado){
                    System.out.println("Analisis Sintactico y Semantico terminado");
                    
                    lexico.siguientePaso();
                }
            }
    }
}

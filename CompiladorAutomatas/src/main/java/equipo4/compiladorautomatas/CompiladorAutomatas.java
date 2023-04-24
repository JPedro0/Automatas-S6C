package equipo4.compiladorautomatas;

public class CompiladorAutomatas {

    public static void main(String[] args) {
        lexico lexico = new lexico();
            if(!lexico.errorEncontrado){
                System.out.println("Analisis lexico terminado");
            }
        
        prueba_Doc imprimir = new prueba_Doc();
        //imprimir.prueba_Doc();
    }
}

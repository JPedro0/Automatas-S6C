package equipo4.compiladorautomatas;

public class CompiladorAutomatas {

    public static void main(String[] args) {
        lexico lexico = new lexico();
            if(!lexico.errorEncontrado){
                System.out.println("Analisis lexico terminado");
            }
    }
}


package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class genIntermedio {
    nodo p;
    List<simbolo> tabla;
    List<String> operaciones;
    List<String> d;
    List<String> buffer;
    List<String> buffer2;
    List<String> bufferOP;
            
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
            if(p.lexema.equals("class") || p.lexema.equals("{") ||p.lexema.equals("}") 
                    || p.lexema.equals(tabla.get(0).ide) || p.lexema.equals(";") || p.lexema.equals("else") ){
                d.add(p.lexema);
                p = p.sig;
            }
            else if(p.lexema.equals("var")){
                d.add(p.lexema);
                p = p.sig;
                d.add(p.lexema);
                p = p.sig;
                if(p.lexema.equals(":")){
                    d.add(p.lexema);
                    p = p.sig;
                    d.add(p.lexema);
                    p = p.sig;
                }
                if(p.lexema.equals(",")){
                    declararVariables();
                }
            }
            else if(p.lexema.equals("while") || p.lexema.equals("if")){
                d.add(p.lexema);
                p = p.sig;
                d.add(p.lexema);
                p = p.sig;
                condicionales();
                /*p = p.sig;
                d.add(p.lexema);
                p = p.sig;*/
            }
            else if(p.token == 210 || p.token == 201){
                while (!p.lexema.equals(";")){
                    d.add(p.lexema);
                    p = p.sig;
                }
            }
            else if(p.token == 100){    //Falta
                notacionP();
            }
            else{
                //d.add(p.lexema);
                p = p.sig;
            }
        }
        d.add("}");
        /*for(int i = 0; i<d.size();i++){
            System.out.println(d.get(i));
        }*/
    }
    
    private void notacionP(){
        buffer = new ArrayList<String>();
        buffer2 = new ArrayList<String>();
        bufferOP = new ArrayList<String>();
        boolean parentesis = false;
        
        while (!p.lexema.equals(";")){
            if(p.token >=100 && p.token <= 102){
                buffer.add(p.lexema);
            }
            else if(p.token == 124 || p.token == 202 || p.token == 203){
                buffer.add(p.lexema);
            }
            else if(p.token == 103 || p.token == 104){ //Orden 5 + -
                if(bufferOP.isEmpty()){
                    bufferOP.add(p.lexema);
                }else{
                    if(bufferOP.get(bufferOP.size()-1).equals("+")||bufferOP.get(bufferOP.size()-1).equals("-")){
                        buffer.add(bufferOP.get(bufferOP.size()-1));
                        bufferOP.remove(bufferOP.size()-1);
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("*")||bufferOP.get(bufferOP.size()-1).equals("/")){
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("^")){
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("(")){
                        bufferOP.add(p.lexema);
                    }
                }
            }
            else if(p.token == 105 || p.token == 106){  //Orden 4 * /
                if(bufferOP.isEmpty()){
                    bufferOP.add(p.lexema);
                }else{
                    if(bufferOP.get(bufferOP.size()-1).equals("+")||bufferOP.get(bufferOP.size()-1).equals("-")){
                        for(int i = (bufferOP.size()-1); i > 0;i--){
                            if(bufferOP.get(i).equals("(") || bufferOP.get(i).equals("=")){
                                break;
                            }
                            else{
                                buffer.add(bufferOP.get(bufferOP.size()-1));
                                bufferOP.remove(bufferOP.size()-1);
                            }
                        }
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("*")||bufferOP.get(bufferOP.size()-1).equals("/")){
                        buffer.add(bufferOP.get(bufferOP.size()-1));
                        bufferOP.remove(bufferOP.size()-1);
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("^")){
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(p.lexema);
                    }
                }
            }
            else if(p.token == 107){ // Orden 3 ^
                if(bufferOP.isEmpty()){
                    bufferOP.add(p.lexema);
                }
                else{
                    if(bufferOP.get(bufferOP.size()-1).equals("+")||bufferOP.get(bufferOP.size()-1).equals("-")){
                        for(int i = (bufferOP.size()-1); i > 0;i--){
                            if(bufferOP.get(i).equals("(") || bufferOP.get(i).equals("=")){
                                break;
                            }
                            else{
                                buffer.add(bufferOP.get(bufferOP.size()-1));
                                bufferOP.remove(bufferOP.size()-1);
                            }
                        }
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("*")||bufferOP.get(bufferOP.size()-1).equals("/")){
                        for(int i = (bufferOP.size()-1); i > 0;i--){
                            if(bufferOP.get(i).equals("(") || bufferOP.get(i).equals("=")){
                                break;
                            }
                            else{
                                buffer.add(bufferOP.get(bufferOP.size()-1));
                                bufferOP.remove(bufferOP.size()-1);
                            }
                        }
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("^")){
                        buffer.add(bufferOP.get(bufferOP.size()-1));
                        bufferOP.remove(bufferOP.size()-1);
                        bufferOP.add(p.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(p.lexema);
                    }
                }
            }
            else if(p.token == 120){ // Orden 2 (
                bufferOP.add(p.lexema);
            }
            else if(p.token == 121){ // Orden 2 )
                for(int i = (bufferOP.size()-1); i > 0;i--){
                    //System.out.println("a"+bufferOP.get(i));
                    if(bufferOP.get(i).equals("(")){
                        bufferOP.remove(i);
                        break;
                    }
                    else{
                        buffer.add(bufferOP.get(i));
                        bufferOP.remove(i);
                    }
                }
            }
            else if(p.token == 125){    //Orden 1 =
                bufferOP.add(p.lexema);
            }
            
            p = p.sig;
        }
        
        //Vaciar pila al final de todo
        if(bufferOP.size() >= 1){
            //System.out.println("a");
            for(int i = (bufferOP.size()-1); i >= 0;i--){
                buffer.add(bufferOP.get(i));
            }
        }
        
        
        if(buffer.size() == 3){
            String guardo = null;
            
            for(int i = 0; i < buffer.size();i++){
                if(buffer.get(i).equals("+")||buffer.get(i).equals("-")||buffer.get(i).equals("*")||
                        buffer.get(i).equals("/")||buffer.get(i).equals("^")||buffer.get(i).equals("=")){
                    buffer2.add(buffer.get(i));
                    buffer2.add(guardo);
                }
                else{
                    if(buffer2.isEmpty()){
                        buffer2.add(buffer.get(i));
                    }else{
                        guardo = buffer.get(i);
                        //buffer2.add(buffer.get(i));
                    }
                }
            }
        }
        else{
            String texto = "";
            String operaciones = "";
            for(int i = 0; i < buffer.size();i++){
                texto = texto+buffer.get(i);
            }
            for(int i = 0; i < bufferOP.size();i++){
                operaciones = operaciones+bufferOP.get(i);
            }
            System.out.println("Valores: "+texto+". Operadores: "+operaciones);
        }
        
        String texto = "";
        for(int i = 0; i < buffer2.size();i++){
            texto = texto+buffer2.get(i);
        }
        System.out.println("Operacion: "+texto);
    }
    
    private void declararVariables(){
        if(p.lexema.equals(",")){
            p = p.sig;
            d.add(":");
            for(int i = 0;i<tabla.size();){
                if(tabla.get(i).ide.equals(p.lexema)){
                    d.add(tabla.get(i).tipo);
                    break;
                }
                else{
                i = i+1;
                }
            }
            d.add(";");
            d.add("var");
            d.add(p.lexema);
            p = p.sig;
            if(p.lexema.equals(",")){
                declararVariables();
            }
            if(p.lexema.equals(":")){
                d.add(p.lexema);
                p = p.sig;
                d.add(p.lexema);
                p = p.sig;
            }
        }
    }
    
    private void condicionales(){
        if(p.lexema.equals("(")){   //Falta
            d.add(p.lexema);
            p = p.sig;
        }
        else{
            while(!p.lexema.equals("{")){
                d.add(p.lexema);
                p = p.sig;
            }
        }
    }
}

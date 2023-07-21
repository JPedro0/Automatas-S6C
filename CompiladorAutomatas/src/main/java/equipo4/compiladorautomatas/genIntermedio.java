
package equipo4.compiladorautomatas;

import java.util.ArrayList;
import java.util.List;

public class genIntermedio {
    nodo p,w;
    int etiqueta = 0;
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
        System.out.println("\n...GENERADOR DE CODIGO INTERMEDIO SIN OPTIMIZAR...\n");
        //pruebas();
        generador();
        Optimizar();
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
    
    private void generador(){
        while(p.sig != null){
            if(p.lexema.equals("class") || p.lexema.equals(tabla.get(0).ide) || p.lexema.equals(";")){
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
            else if(p.lexema.equals("{")){
                if(p.sig.token >= 100 && p.sig.token <=102){
                    d.add(p.lexema);
                    p = p.sig;
                    d.add("L"+etiqueta+":");
                    etiqueta = etiqueta+1;
                }
                else if(p.sig.token >= 200 && p.sig.token <300){
                    d.add(p.lexema);
                    p = p.sig;
                    d.add("L"+etiqueta+":");
                    etiqueta = etiqueta+1;
                }
                else{
                    d.add(p.lexema);
                    p = p.sig;
                }
            }
            else if(p.lexema.equals("}") ){
                if(d.get(d.size()-1).equals("}") || d.get(d.size()-1).equals(";")){
                    d.add(p.lexema);
                    p = p.sig;
                    d.add("L"+etiqueta+":");
                    etiqueta = etiqueta+1;
                }
                else{
                    d.add(p.lexema);
                    p = p.sig;
                }
            }
            else if(p.lexema.equals("while") || p.lexema.equals("if")){
                if(p.lexema.equals("while")&& d.get(d.size()-1).charAt(0) != 'L'){
                    d.add("L"+etiqueta+":");
                    etiqueta = etiqueta+1;
                }
                w = p;
                //p se queda en el if/while
                w = w.sig.sig;
                condicionales();
                /*p = p.sig;
                d.add(p.lexema);
                p = p.sig;*/
            }
            else if (p.lexema.equals("else")){
                d.add(p.lexema);
                p = p.sig;
                d.add(p.lexema);
                p = p.sig;
                d.add("L"+etiqueta+":");
                etiqueta = etiqueta+1;
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
        
        //Imprimir nodos del Intermedio
        for(int i = 0; i<d.size();i++){
            System.out.println("Valor: "+i+" . Lexema: "+d.get(i));
        }
    }
    
    private void notacionP(){
        buffer = new ArrayList<>();
        buffer2 = new ArrayList<>();
        bufferOP = new ArrayList<>();
        
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
            for(int j = 0; j < buffer2.size();j++){
                d.add(buffer2.get(j));
            }
                    
        }
        else{
            bufferOP = new ArrayList<>();
            int puntero = 0;
            
            for(int i = 0; i < buffer.size();i++){
                bufferOP = new ArrayList<>();
                if(buffer.size() == 3){
                    String guardo = null;
            
                    for(int k = 0; k < buffer.size();k++){
                        if(buffer.get(k).equals("+")||buffer.get(k).equals("-")||buffer.get(k).equals("*")||
                                buffer.get(k).equals("/")||buffer.get(k).equals("^")||buffer.get(k).equals("=")){
                            bufferOP.add(buffer.get(k));
                            bufferOP.add(guardo);
                        }
                        else{
                            if(bufferOP.isEmpty()){
                                bufferOP.add(buffer.get(k));
                        }else{
                                guardo = buffer.get(k);
                                //buffer2.add(buffer.get(i));
                            }
                        }
                    }
                    
                    for(int j = 0; j < bufferOP.size();j++){
                        d.add(bufferOP.get(j));
                    }
                    
                    //d.add(";");
                    
                    break;
                }
                if(buffer.get(i).equals("+")||buffer.get(i).equals("-")||buffer.get(i).equals("*")||
                        buffer.get(i).equals("/")||buffer.get(i).equals("^")||buffer.get(i).equals("=")){
                    //Si encuentra

                    bufferOP.add("t"+puntero);
                    bufferOP.add("=");
                    
                    //System.out.println(buffer.get(i) + buffer.get(i-2) + buffer.get(i-1));
                    bufferOP.add(buffer.get(i-2));
                    bufferOP.add(buffer.get(i));
                    bufferOP.add(buffer.get(i-1));
                    
                    buffer.add(i,"t"+puntero);
                    
                    buffer.remove(i-1);
                    buffer.remove(i-2);
                    buffer.remove(i-1);
                    
                    //String algo = "";
            
                    for(int j = 0; j < bufferOP.size();j++){
                        d.add(bufferOP.get(j));
                    }
                    
                    d.add(";");
            
                    //System.out.println("Operacion: "+algo);
                    
                    i=0;
                    puntero = puntero +1;
                }
            }
        }
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
        buffer = new ArrayList<>();
        buffer2 = new ArrayList<>();
        bufferOP = new ArrayList<>();
        
        while(!w.sig.lexema.equals("{")){
            if(w.token >=100 && w.token <= 102){
                buffer.add(w.lexema);
            }
            else if(w.token == 124 || w.token == 202 || w.token == 203){
                buffer.add(w.lexema);
            }
            else if(w.token == 103 || w.token == 104){ //Orden 5 + -
                if(bufferOP.isEmpty()){
                    bufferOP.add(w.lexema);
                }else{
                    if(bufferOP.get(bufferOP.size()-1).equals("+")||bufferOP.get(bufferOP.size()-1).equals("-")){
                        buffer.add(bufferOP.get(bufferOP.size()-1));
                        bufferOP.remove(bufferOP.size()-1);
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("*")||bufferOP.get(bufferOP.size()-1).equals("/")){
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("^")){
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("(")){
                        bufferOP.add(w.lexema);
                    }
                }
            }
            else if(w.token == 105 || w.token == 106){  //Orden 4 * /
                if(bufferOP.isEmpty()){
                    bufferOP.add(w.lexema);
                }else{
                    if(bufferOP.get(bufferOP.size()-1).equals("+")||bufferOP.get(bufferOP.size()-1).equals("-")){
                        //System.out.println("a");
                        for(int i = (bufferOP.size()-1); i >= 0;i--){
                            if(bufferOP.get(i).equals("(") || bufferOP.get(i).equals("=")){
                                break;
                            }
                            else{
                                bufferOP.add(w.lexema);
                                buffer.add(bufferOP.get(bufferOP.size()-1));
                                bufferOP.remove(bufferOP.size()-1);
                            }
                        }
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("*")||bufferOP.get(bufferOP.size()-1).equals("/")){
                        buffer.add(bufferOP.get(bufferOP.size()-1));
                        bufferOP.remove(bufferOP.size()-1);
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("^")){
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(w.lexema);
                    }
                }
            }
            else if(w.token == 107){ // Orden 3 ^
                if(bufferOP.isEmpty()){
                    bufferOP.add(w.lexema);
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
                        bufferOP.add(w.lexema);
                    }
                    else if(bufferOP.get(bufferOP.size()-1).equals("=")){
                        bufferOP.add(w.lexema);
                    }
                }
            }
            else if(w.token == 120){ // Orden 2 (
                bufferOP.add(w.lexema);
            }
            else if(w.token == 121){ // Orden 2 )
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
            else if(w.token >= 108 && w.token <= 113){    //Orden 1 CONDICIONALES
                for(int i = (bufferOP.size()-1); i >= 0;i--){
                    buffer.add(bufferOP.get(i));
                    bufferOP.remove(i);
                }
                bufferOP.add(w.lexema);
            }
            
            w = w.sig;
        }
        
        //Vaciar pila al final de todo
        if(bufferOP.size() >= 1){
            //System.out.println("a");
            for(int i = (bufferOP.size()-1); i >= 0;i--){
                buffer.add(bufferOP.get(i));
            }
        }
        
        /*String texto = "";
        String ope = "";
        for(int n=0;n<buffer.size();n++){
            texto=texto+buffer.get(n);
        }
        System.out.println("Operacion: "+texto);*/
        
        if(buffer.size() == 3){
            String guardo = null;
            
            for(int i = 0; i < buffer.size();i++){
                if(buffer.get(i).equals("+")||buffer.get(i).equals("-")||buffer.get(i).equals("*")||
                        buffer.get(i).equals("/")||buffer.get(i).equals("^")||buffer.get(i).equals("=")||
                        buffer.get(i).equals(">")||buffer.get(i).equals("<")||buffer.get(i).equals("==")||
                        buffer.get(i).equals(">=")||buffer.get(i).equals("<=")||buffer.get(i).equals("!=")){
                        
                    buffer2.add(buffer.get(i));
                    buffer2.add(guardo);
                    
                }
                else{
                    if(buffer2.isEmpty()){
                        buffer2.add("t0");
                        buffer2.add("=");
                        buffer2.add(buffer.get(i));
                    }else{
                        guardo = buffer.get(i);
                        //buffer2.add(buffer.get(i));
                    }
                }
            }
            buffer2.add(";");
            
            /*String texto = "";
            for(int n=0;n<buffer2.size();n++){
                texto=texto+buffer2.get(n);
            }
            System.out.println("Operacion2: "+texto);*/
            
            for(int j = 0; j < buffer2.size();j++){
                d.add(buffer2.get(j));
            }
            
            
            d.add(p.lexema);
            //System.out.println("1"+p.lexema);
            p=p.sig;
            d.add(p.lexema);
            p=p.sig;
            d.add("t0");
            d.add(")");
            d.add("go to");
            d.add(":");
            d.add("L"+(etiqueta));
            d.add(";");
            d.add("go to");
            d.add(":");
            //System.out.println("2 "+w.sig.sig.lexema);
            if(w.sig.sig.lexema.equals("if") || w.sig.sig.lexema.equals("while")){
                d.add("L"+(etiqueta+2));
            }
            else{
                d.add("L"+(etiqueta+1));
            }
            d.add(";");
            
                    
        }
        else{
            bufferOP = new ArrayList<>();
            int puntero = 0;
            
            for(int i = 0; i < buffer.size();i++){
                bufferOP = new ArrayList<>();
                if(buffer.size() == 3){
                    String guardo = null;
                    
                    bufferOP.add("t"+puntero);
                    bufferOP.add("=");
            
                    for(int k = 0; k < buffer.size();k++){
                        //System.out.println(buffer.get(k));
                        if(buffer.get(k).equals(">")||buffer.get(k).equals("<")||buffer.get(k).equals("==")||
                                buffer.get(k).equals(">=")||buffer.get(k).equals("<=")||buffer.get(k).equals("!=")){

                            bufferOP.add(buffer.get(k));
                            bufferOP.add(guardo);
                        }
                        else{
                            if(bufferOP.size()==2){
                                bufferOP.add(buffer.get(k));
                            }else{
                                guardo = buffer.get(k);
                                //buffer2.add(buffer.get(i));
                            }
                        }
                    }
                    
                    bufferOP.add(";");
                    
                    /*String texto = "";
                    for(int n=0;n<bufferOP.size();n++){
                        texto=texto+bufferOP.get(n);
                    }
                    System.out.println("Operacion4: "+texto);*/
                    
                    for(int j = 0; j < bufferOP.size();j++){
                        d.add(bufferOP.get(j));
                    }
                    
                    d.add(p.lexema);
                    p=p.sig;
                    d.add(p.lexema);
                    p=p.sig;
                    d.add("t"+puntero);
                    d.add(")");
                    d.add("go to");
                    d.add(":");
                    d.add("L"+(etiqueta));
                    d.add(";");
                    d.add("go to");
                    d.add(":");
                    if(w.sig.sig.lexema.equals("if") || w.sig.sig.lexema.equals("while")){
                        d.add("L"+(etiqueta+2));
                    }
                    else{
                        d.add("L"+(etiqueta+1));
                    }
                    d.add(";");
                    
                    //d.add(";");
                    
                    break;
                }
                if(buffer.get(i).equals("+")||buffer.get(i).equals("-")||buffer.get(i).equals("*")||
                        buffer.get(i).equals("/")||buffer.get(i).equals("^")||buffer.get(i).equals("=")||
                        buffer.get(i).equals(">")||buffer.get(i).equals("<")||buffer.get(i).equals("==")||
                        buffer.get(i).equals(">=")||buffer.get(i).equals("<=")||buffer.get(i).equals("!=")){
                    //Si encuentra

                    bufferOP.add("t"+puntero);
                    bufferOP.add("=");
                    
                    //System.out.println(buffer.get(i) + buffer.get(i-2) + buffer.get(i-1));
                    bufferOP.add(buffer.get(i-2));
                    bufferOP.add(buffer.get(i));
                    bufferOP.add(buffer.get(i-1));
                    
                    buffer.add(i,"t"+puntero);
                    
                    buffer.remove(i-1);
                    buffer.remove(i-2);
                    buffer.remove(i-1);
                    
                    bufferOP.add(";");
                    
                    /*String texto = "";
                    for(int n=0;n<bufferOP.size();n++){
                        texto=texto+bufferOP.get(n);
                    }
                    System.out.println("Operacion3: "+texto);*/
            
                    for(int j = 0; j < bufferOP.size();j++){
                        d.add(bufferOP.get(j));
                    }
                    
                    //d.add(";");
            
                    //System.out.println("Operacion: "+algo);
                    
                    i=0;
                    puntero = puntero +1;
                }
            }
            
        }
        
        p=w;
    }
    
    private void Optimizar(){
        optimizador optimizar = new optimizador(d,tabla,operaciones);
    }
}

package equipo4.compiladorautomatas;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class compi {

    nodo cabeza = null, p;
    int estado = 0, columna, valorMT, numRenglon = 1, caracter = 0;
    String lexema = "";
    boolean errorEncontrado = false, endfile = false;
    List<simbolo> tabla = new ArrayList<>();
    List<String> operacion = new ArrayList<>();
    List<String> operaciones = new ArrayList<>();

    String archivo = "C:\\Users\\nuevo\\Desktop\\CompiladorAutomatas\\src\\main\\java\\pruebas\\prueba.txt";

    int matriz[][] = {
        /*      l   d   .   +   -       *   /   ^   >   <   =   !   &   |   ,   :   ;   (   )   {   }   "   ed  tab nl  oc  eof*/
        /*0*/   {1, 2, 505, 103, 104, 105, 5, 107, 8, 9,    10, 11, 12, 13, 117, 118, 119, 120, 121, 122, 123, 14, 0, 0, 0, 505, 0},
        /*1*/   {1, 1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100},
        /*2*/   {101, 2, 3, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101},
        /*3*/   {500, 4, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500},
        /*4*/   {102, 4, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102},
        /*5*/   {106, 106, 106, 106, 106, 6, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106},
        /*6*/   {6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 501, 6, 501},
        /*7*/   {6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 501, 6, 501},
        /*8*/   {108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 110, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108},
        /*9*/   {109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 111, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109},
        /*10*/  {125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 112, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125},
        /*11*/  {116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 113, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116},
        /*12*/  {502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 114, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502},
        /*13*/  {503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 115, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503},
        /*14*/  {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 124, 14, 14, 504, 14, 504}
    };

    String palabrasRes[][] = {
        {"200", "var"},
        {"201", "print"},
        {"202", "true"},
        {"203", "false"},
        {"204", "if"},
        {"205", "else"},
        {"206", "while"},
        {"207", "string"},
        {"208", "int"},
        {"209", "double"},
        {"210", "read"},
        {"211", "class"},
        {"212", "bool"}
    };

    String codErrores[][] = {
        {"500", "Se espera un dígito"},
        {"501", "Se espera cerrar comentario"},
        {"502", "Se espera un & "},
        {"503", "Se espera un | "},
        {"504", "Se espera cerrar cadena"},
        {"505", "Símbolo no valido"}
    };

    RandomAccessFile file = null;

    public compi() {
        try {
            file = new RandomAccessFile(archivo, "r");

            //System.out.println(file.length());
            while (!endfile) {
                //System.out.println(caracter);
                caracter = file.read();

                if (caracter == -1) {
                    columna = 25;
                    endfile = true;
                    //System.out.println("Final del archivo");
                } else if (Character.isLetter((char) caracter)) {
                    columna = 0;
                } else if (Character.isDigit((char) caracter)) {
                    columna = 1;
                } else {
                    switch ((char) caracter) {
                        case '.':
                            columna = 2;
                            break;
                        case '+':
                            columna = 3;
                            break;
                        case '-':
                            columna = 4;
                            break;
                        case '*':
                            columna = 5;
                            break;
                        case '/':
                            columna = 6;
                            break;
                        case '^':
                            columna = 7;
                            break;
                        case '>':
                            columna = 9;
                            break;
                        case '<':
                            columna = 8;
                            break;
                        case '=':
                            columna = 10;
                            break;
                        case '!':
                            columna = 11;
                            break;
                        case '&':
                            columna = 12;
                            break;
                        case '|':
                            columna = 13;
                            break;
                        case ',':
                            columna = 14;
                            break;
                        case ':':
                            columna = 15;
                            break;
                        case ';':
                            columna = 16;
                            break;
                        case '(':
                            columna = 17;
                            break;
                        case ')':
                            columna = 18;
                            break;
                        case '{':
                            columna = 19;
                            break;
                        case '}':
                            columna = 20;
                            break;
                        case '"':
                            columna = 21;
                            break;
                        case ' ':
                            columna = 22; //Espacio en blanco
                            break;
                        case 9:
                            columna = 23;    //Tabulador
                            break;
                        case 13: {
                            columna = 24;  //Nueva linea
                            numRenglon = numRenglon + 1;
                        }
                        break;
                        default:
                            break;
                    }
                }

                valorMT = matriz[estado][columna];

                if (valorMT < 100) {
                    estado = valorMT;

                    if (estado == 0) {
                        lexema = "";
                    } else {
                        lexema = lexema + (char) caracter;
                    }
                } else if (valorMT >= 100 && valorMT < 500) {
                    if (valorMT == 100) {
                        validarPalabraReservada();//if 
                    }

                    if (valorMT == 100 || valorMT == 101 || valorMT == 102 || valorMT == 106
                            || valorMT == 108 || valorMT == 109 || valorMT == 108 || valorMT == 125
                            || valorMT == 116 || valorMT >= 200) {
                        file.seek(file.getFilePointer() - 1);
                    } else {
                        lexema = lexema + (char) caracter;
                    }

                    insertarNodo();
                    estado = 0;
                    lexema = "";
                } else {
                    imprimirError();
                    break;
                }
            }
            imprimirNodo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void imprimirError() {
        if (caracter != -1 && valorMT >= 500) {
            System.exit(0);
            for (String[] errore : codErrores) {
                if (valorMT == Integer.valueOf(errore[0])) {
                    if (valorMT == 505) {
                        System.out.println("El error encontrado es: " + errore[1] + ", error " + valorMT + ", caracter " + caracter + ", en el renglon " + numRenglon);
                    } else {
                        System.out.println("El error encontrado es: " + errore[1] + ", error " + valorMT + ", caracter " + caracter + ", en el renglon " + (numRenglon - 1));
                    }
                }
            }
        }
    }

    private void validarPalabraReservada() {
        for (String palabrasRes[] : palabrasRes) {
            if (lexema.equals(palabrasRes[1])) {
                valorMT = Integer.valueOf(palabrasRes[0]);
            }
        }
    }

    private void imprimirNodo() {
        p = cabeza;
        while (p != null) {
            if (!p.lexema.isBlank()) {
                //System.out.println(p.lexema);
                System.out.println(p.lexema + " " + p.token + " " + p.renglon);
            }
            p = p.sig;
        }
    }

    private void insertarNodo() {
        nodo nodo = new nodo(lexema, valorMT, numRenglon);

        if (cabeza == null) {
            cabeza = nodo;
            p = cabeza;
        } else {
            p.sig = nodo;
            p = nodo;
        }
    }

    //SINTAXIS
    public void sintaxis() {
        System.out.println("\n...SINTACTICO Y SEMANTICO...\n");
        p = cabeza;
        while (p != null) {

            //Inicio sintactico
            if (p.token == 211) {
                p = p.sig;
                if (p.token == 100) {
                    simbolo variable = new simbolo(p.lexema, "class", p.lexema, p.renglon, true, 1);
                    tabla.add(variable);
                    p = p.sig;
                    if (p.token == 122) {
                        declarar_var();
                        
                        //System.out.println(tabla.get(0).ide + " " + tabla.get(0).tipo);
                        
                        /*for(int i = 0;i<tabla.size();i++){
                            System.out.println(tabla.get(i).ide + " " + tabla.get(i).tipo);
                        }*/
                        
                        if (p.sig == null) {

                        } else {
                            p = p.sig;
                        }
                        while (p.token != 123) { //Busca un }

                            statement();
                            //System.out.println("\n");
                            /*for(int i = 0;i<operacion.size();i++){
                            System.out.println(operacion.get(i));
                            }*/
                            
                            comprobarSemantico();
                            
                            operacion = new ArrayList<>();
                            
                            if (p.sig == null) {
                                break;
                            } else {
                                p = p.sig;
                                //System.out.println(p.lexema + " " + p.renglon);
                                if (p.token == 123 && p.token <= 200){

                                }
                                else{
                                    //System.out.println(p.lexema + " " + p.renglon);
                                    //System.out.println("B");
                                }
                            }
                        }
                        if (p.token == 123) {
                            //System.out.println(p.lexema + "--" + p.renglon);
                            /*for(int i = 0;i<tabla.size();i++){
                            System.out.println(tabla.get(i).ide + " " + tabla.get(i).tipo + " " + tabla.get(i).ambito);
                            }*/
                            break;
                        } else {
                            System.out.println("Error: 509: Se espera un }");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Error 508: Se espera {");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error 507: Se espera ID");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 506: Se espera class");
                System.exit(0);
            }

            p = p.sig;
        }
    }

    //revisión sintáctica sobre la declaración de variables
    private void declarar_var() {
        while (p.sig.token == 200){
            p = p.sig;
            if (p.token == 200) {
            p = p.sig;
            while (p.token != 118) { // Busca un :
                if (p.token == 100) {
                    //Ingreso de ID's
                    //System.out.println(p.lexema);
                    simbolo variable = new simbolo(p.lexema, null, null, p.renglon, false, 1);
                     
                    for(int i = 0;i<tabla.size();){
                        //System.out.println(tabla.get(i).ide);
                        if(tabla.get(0).ide.equals(p.lexema)){
                            System.out.println("Error 528: No se puede usar nombre de clase. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                            System.exit(0);
                        }
                        if(tabla.get(i).ide.equals(p.lexema)){
                            System.out.println("Error 525: Variable ya declarada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                            System.exit(0);
                        }
                        else{
                            if(i==(tabla.size() - 1)){
                                tabla.add(variable);
                                break;
                            }
                            else{
                                i = i+1;
                            }
                        }
                    }

                    p = p.sig;
                    if (p.token == 117) {
                        p = p.sig;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Error 507: Se espera ID");
                    System.exit(0);
                    break;
                }
            }
            if (p.token == 118) {
                tipo();
                //ASIGNACION DE TIPOS A LA TABLA
                //System.out.println(p.lexema);
                for(int i = 0;i<tabla.size();i++){
                    if(tabla.get(i).tipo == null){
                        //System.out.println(tabla.get(i).ide + " " + tabla.get(i).tipo);
                        simbolo variable = new simbolo(tabla.get(i).ide, p.lexema, "a", tabla.get(i).num_linea, false,0);
                        if(p.lexema.equals("int")){
                            variable = new simbolo(tabla.get(i).ide, p.lexema, null, tabla.get(i).num_linea, false,4);
                        }
                        if(p.lexema.equals("double")){
                            variable = new simbolo(tabla.get(i).ide, p.lexema, null, tabla.get(i).num_linea, false,17);
                        }
                        if(p.lexema.equals("string")){
                            variable = new simbolo(tabla.get(i).ide, p.lexema, null, tabla.get(i).num_linea, false,16);
                        }
                        if(p.lexema.equals("bool")){
                            variable = new simbolo(tabla.get(i).ide, p.lexema, null, tabla.get(i).num_linea, false,1);
                        }
                        
                        tabla.set(i, variable);
                    }
                }
                
                p = p.sig;
                if (p.token == 119) {
                    //vacio
                } else {
                    System.out.println("Error 512: Se espera ;");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 511: Se espera :");
                System.exit(0);
            }
        } else {
            System.out.println("Error 510: Se espera var");
            System.exit(0);
        }
        }
    }

    private void tipo() {
        p = p.sig;
        if (p.token == 207 || p.token == 208 || p.token == 209 || p.token == 212) {
            //vacio
        } else {
            System.out.println("Error 513: Se espera un TIPO");
            System.exit(0);
        }
    }

    private void statement() {
        if (p.token == 121) {
            System.out.println("Error 514: Se espera un ESTATUTO");
            System.exit(0);
        }
        //System.out.println(p.lexema);
        if (p.token == 201) { //print (a);
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                if (p.token == 100) {
                    //System.out.println(p.lexema);
                    
                    /*if(tabla.contains(p.lexema)){
                        System.out.println(p.lexema + ": true");
                    }
                    else{
                        System.out.println(p.lexema + ": false");
                    }*/
                    
                    for(int i = 0;i<tabla.size();){
                        //System.out.println(tabla.get(i).ide.equals(p.lexema));
                        if(tabla.get(i).ide.equals(p.lexema)){
                            if(tabla.get(i).ide == tabla.get(0).ide){
                                System.out.println("Error 524: Varible no puede ser utilizada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                            else{
                                //System.out.println("TRUE print");
                                simbolo variable = new simbolo(tabla.get(i).ide, tabla.get(i).tipo, tabla.get(i).valor, tabla.get(i).num_linea, true, tabla.get(i).tamaño);
                                tabla.set(i, variable);
                                break;
                            }
                        }
                        else{
                            if(i!=(tabla.size() - 1)){
                                i = i+1;
                            }
                            else{
                                System.out.println("Error 523: Variable no encontrada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                        }
                    }
                    
                    
                    
                    p = p.sig;
                    if (p.token == 121) {
                        p = p.sig;
                        //System.out.println(p.lexema);
                        if (p.token != 119) {
                            System.out.println("Error 512: Se espera un ;");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Error 516: Se espera un )");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error 507: Se espera un ID");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 515: Se espera un (");
                System.exit(0);
            }
        }
        if (p.token == 210) { //read (a);
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                if (p.token == 100) {
                    
                    for(int i = 0;i<tabla.size();){
                        //System.out.println(tabla.get(i).ide.equals(p.lexema));
                        if(tabla.get(i).ide.equals(p.lexema)){
                            if(tabla.get(i).ide == tabla.get(0).ide){
                                System.out.println("Error 524: Varible no puede ser utilizada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                            else{
                                //System.out.println("TRUE print");
                                simbolo variable = new simbolo(tabla.get(i).ide, tabla.get(i).tipo, tabla.get(i).valor, tabla.get(i).num_linea, true, tabla.get(i).tamaño);
                                tabla.set(i, variable);
                                break;
                            }
                        }
                        else{
                            if(i!=(tabla.size() - 1)){
                                i = i+1;
                            }
                            else{
                                System.out.println("Error 523: Variable no encontrada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                        }
                    }
                    
                    p = p.sig;
                    if (p.token == 121) {
                        p = p.sig;
                        if (p.token != 119) {
                            System.out.println("Error 512: Se espera un ;");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Error 516: Se espera un )");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error 507: Se espera un ID");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 515: Se espera un (");
                System.exit(0);
            }
        }
        if (p.token == 100) { //ID = a;
            
            for(int i = 0;i<tabla.size();){
                        //System.out.println(tabla.get(i).ide.equals(p.lexema));
                        if(tabla.get(i).ide.equals(p.lexema)){
                            if(tabla.get(i).ide == tabla.get(0).ide){
                                System.out.println("Error 524: Varible no puede ser utilizada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                            else{
                                //System.out.println("TRUE id");
                                simbolo variable = new simbolo(tabla.get(i).ide, tabla.get(i).tipo, tabla.get(i).valor, tabla.get(i).num_linea, true, tabla.get(i).tamaño);
                                tabla.set(i, variable);
                                break;
                            }
                        }
                        else{
                            if(i!=(tabla.size() - 1)){
                                i = i+1;
                            }
                            else{
                                System.out.println("Error 523: Variable no encontrada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                        }
                    }
            operacion.add(p.lexema);
            p = p.sig;
            if (p.token == 125) {
                operacion.add(p.lexema);
                p = p.sig;
                expresion_simple();
                
                //System.out.println(p.lexema);
                if (p.token != 119) {
                    System.out.println("Error 512: Se espera un ;");
                    System.exit(0);
                }
                else{
                    operacion.add(p.lexema);
                    armarOperacion();
                }
            } else {
                System.out.println("Error 517: Se espera un =");
                System.exit(0);
            }
        }
        if (p.token == 206) { //while(1>2)
            p = p.sig;
            if (p.token == 120) {
                operacion.add(p.lexema);
                p = p.sig;
                expresion_condicional();
                comprobarSemantico();
                if (p.token == 121) {
                    operacion.add(p.lexema);
                    armarOperacion();
                    p = p.sig;
                    if (p.token == 122) {
                        p = p.sig;
                        while (p.token != 123) { //Busca un }
                            
                            operacion = new ArrayList<>();
                            
                            statement();
                            
                            comprobarSemantico();
                            
                            operacion = new ArrayList<>();
                            
                            if (p.sig == null) {
                                System.out.println("Error 509: Se espera un }");
                                System.exit(0);
                                break;
                            } else {
                                p = p.sig;
                                if (p.token == 123) {
                                    //vacio
                                } else {
                                    System.out.println("Error 509: Se espera un }");
                                    System.exit(0);
                                }
                            }
                        }

                    } else {
                        System.out.println("Error 508: Se espera un {");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error 516: Se espera un )");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 515: Se espera un (");
                System.exit(0);
            }
        }
        if (p.token == 204) { // IF{}
            p = p.sig;
            if (p.token == 120) {
                operacion.add(p.lexema);
                p = p.sig;
                expresion_condicional();
                comprobarSemantico();
                if (p.token == 121) {
                    operacion.add(p.lexema);
                    armarOperacion();
                    p = p.sig;
                    if (p.token == 122) {
                        p = p.sig;
                        while (p.token != 123) { //Busca un }
                            
                            operacion = new ArrayList<>();
                            
                            statement();
                            
                            comprobarSemantico();
                            
                            operacion = new ArrayList<>();
                            
                            if (p.sig == null) {
                                System.out.println("Error 509: Se espera un }");
                                System.exit(0);
                                break;
                            } else {
                                p = p.sig;
                                if (p.token == 123) {
                                    //System.out.println(p.lexema);
                                    if (p.sig.token == 205) { // ELSE{}
                                        p = p.sig;
                                        p = p.sig;
                                        if (p.token == 122) {
                                            p = p.sig;
                                            while (p.token != 123) { //Busca un }
                                                //System.out.println(p.lexema);
                                                
                                                operacion = new ArrayList<>();
                            
                                                statement();
                            
                                                comprobarSemantico();
                                                
                                                operacion = new ArrayList<>();
                            
                                                if (p.sig == null) {
                                                    //System.out.println("Se espera un }");
                                                    System.exit(0);
                                                    break;
                                                } else {
                                                    p = p.sig;
                                                    if (p.token == 123) {
                                                        //vacio
                                                    } else {
                                                        System.out.println("Error 509: Se espera un }");
                                                        System.exit(0);
                                                    }
                                                }
                                            }

                                        } else {
                                            System.out.println("Error 518: Se espera un {");
                                            System.exit(0);
                                        }
                                    }
                                } else {
                                    
                                }
                            }
                        }

                    } else {
                        System.out.println("Error 508: Se espera un {");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error 516: Se espera un )");
                    System.exit(0);
                }
            } else {
                System.out.println("Error 515: Se espera un (");
                System.exit(0);
            }
        }
    }

    private void expresion_simple() {
        //p = p.sig;
        //System.out.println(p.lexema);
        if(p.token == 103 || p.token == 104){
            signo();
            termino();
        } else if(p.sig.token == 103 || p.sig.token == 104 || p.sig.token == 105 || p.sig.token == 106 || p.sig.token == 114 || p.sig.token == 115){
            factor();
            if(p.token == 103 || p.token == 104 || p.token == 115){
                operador_aditivo();
            }
            if(p.token == 105 || p.token == 106 || p.token == 114){
                operador_mult();
            }
            //System.out.println(p.lexema);
            //p = p.sig;
            expresion_simple();
        } else if(p.token == 120){
            operacion.add(p.lexema);
            p = p.sig;
            expresion_simple();
            if(p.token == 121){
                operacion.add(p.lexema);
                p = p.sig;
            } else {
                System.out.println("Error 516: Se espera un )");
                System.exit(0);
            }
        } else {
            termino();
            //System.out.println(p.lexema);
        }
    }

    private void expresion_condicional() {
        
        if(p.token == 202 || p.token == 203){
            p=p.sig;
        }
        else{
        //System.out.println("1"+p.lexema);
        expresion_simple();
        //System.out.println("2"+p.lexema);
        operador_relacional();
        //System.out.println("3"+p.lexema);
        expresion_simple();
        //System.out.println(p.token);                            
        }
    }

    private void signo() {
        if (p.token == 103 || p.token == 104) {
            p = p.sig;
        } else {
            System.out.println("Error 518: Se espera un SIGNO");
            System.exit(0);
        }
    }

    private void operador_aditivo() {
        if (p.token == 103 || p.token == 104 || p.token == 115) {
            operacion.add(p.lexema);
            p = p.sig;
        } else {
            System.out.println("Error 519: Se espera un OPERADOR ADITIVO");
            System.exit(0);
        }
    }

    private void operador_mult() {
        if (p.token == 105 || p.token == 106 || p.token == 114) {
            operacion.add(p.lexema);
            p = p.sig;
        } else {
            System.out.println("Error 520: Se espera un OPERADOR MULTIPLICATIVO");
            System.exit(0);
        }
    }

    private void operador_relacional() {
       
        if (p.token == 108 || p.token == 109 || p.token == 110 || p.token == 111 || p.token == 112 || p.token == 113) {
            operacion.add(p.lexema);
            p = p.sig;
        } else {
            //System.out.println(p.lexema);
            System.out.println("Error 521: Se espera un OPERADOR RELACIONAL");
            System.exit(0);
        }
    }

    private void factor() {
        //System.out.println(p.lexema);
        switch (p.token) {
            case 100:   //ID
                for(int i = 0;i<tabla.size();){
                        //System.out.println(tabla.get(i).ide.equals(p.lexema));
                        if(tabla.get(i).ide.equals(p.lexema)){
                            if(tabla.get(i).ide == tabla.get(0).ide){
                                System.out.println("Error 524: Varible no puede ser utilizada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                            else{
                                //System.out.println("TRUE factor");
                                simbolo variable = new simbolo(tabla.get(i).ide, tabla.get(i).tipo, tabla.get(i).valor, tabla.get(i).num_linea, true, tabla.get(i).tamaño);
                                tabla.set(i, variable);
                                break;
                            }
                        }
                        else{
                            if(i!=(tabla.size() - 1)){
                                i = i+1;
                            }
                            else{
                                System.out.println("Error 523: Variable no encontrada. Variable \"" + p.lexema + "\" en renglon: " + p.renglon);
                                System.exit(0);
                            }
                        }
                    }
                operacion.add(p.lexema);
                p = p.sig;
                break;
            case 101:   //Entero    
                operacion.add(p.lexema);
                p = p.sig;
                break;
            case 102:   //Decimal
                operacion.add(p.lexema);
                p = p.sig;
                break;
            case 124:   //Cadena
                operacion.add(p.lexema);
                p = p.sig;
                break;
            case 116:
                p = p.sig;
                factor();
                break;
            case 202:   //true
                operacion.add(p.lexema);
                p = p.sig;
                break;
            case 203:   //false
                operacion.add(p.lexema);
                p = p.sig;
                break;
            default:
                System.out.println("Error 522: Se espera un DATO");
                System.exit(0);
                break;
        }
    }

    private void termino() {
        //System.out.println("si");
        if(p.sig.token == 105 || p.sig.token == 106 || p.sig.token == 114){
            factor();
            operador_mult();
            factor();
        }
        else{
            //System.out.println("si");
            factor();
        }
    }
    
    private void comprobarSemantico(){
        List<String> operacionTipos = new ArrayList<>();
        String id = null,OP=null;
        String parentesis = "(";
        Boolean puntero = false;
        if(operacion.isEmpty()){
            
        }
        else{
            id=operacion.get(0);
            //System.out.println("\n");
            if(id.startsWith("(")){
                for(int j=1;j<(operacion.size());j++){
                    //System.out.println(operacion.get(j));
                    
                    if(operacion.get(j).charAt(0)=='0'||operacion.get(j).charAt(0)=='1'||operacion.get(j).equals("2")
                            ||operacion.get(j).charAt(0)=='3'||operacion.get(j).charAt(0)=='4'||operacion.get(j).charAt(0)=='5'
                            ||operacion.get(j).charAt(0)=='6'||operacion.get(j).charAt(0)=='7'||operacion.get(j).charAt(0)=='8'
                            ||operacion.get(j).charAt(0)=='9'){
                        //System.out.println("test");
                        if(operacion.get(j).length()>1){
                            for(int p=1;p<operacion.get(j).length();p++){
                                if(operacion.get(j).charAt(1)=='.'){
                                    operacionTipos.remove(operacionTipos.size()-1);
                                    operacionTipos.add("double");
                                    puntero=true;
                                    //System.out.println("ño");
                                    OP="double";
                                }
                                else{
                                    if(p==(operacion.get(j).length()-1)){
                                        if(puntero==false){
                                            //System.out.println("ñeee");
                                            OP="int";
                                            operacionTipos.add("int");
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            operacionTipos.add("int");
                            if(puntero==false){
                            //System.out.println(puntero);
                            OP="int";
                            }
                        }
                    }
                    else if(operacion.get(j).equals("+")||operacion.get(j).equals("-")||operacion.get(j).equals("*")
                            ||operacion.get(j).equals("/")||operacion.get(j).equals("^")){
                        operacionTipos.add(operacion.get(j));
                        if(operacion.get(j).equals("/")){
                            operacionTipos.add("double");
                            //System.out.println("double");
                            puntero=true;
                            OP="double";
                        }
                    }
                    else if(operacion.get(j).equals("<")||operacion.get(j).equals(">")
                            ||operacion.get(j).equals("<=")||operacion.get(j).equals(">=")
                            ||operacion.get(j).equals("==")||operacion.get(j).equals("!=")){
                        operacionTipos.add(operacion.get(j));
                    }
                    else if(operacion.get(j).equals("(")||operacion.get(j).equals(")")){
                        operacionTipos.add(operacion.get(j));
                    }
                    else{ //ID's
                        //operacionTipos.add(operacion.get(j));
                        
                        for(int k = 0;k<tabla.size();){
                            //System.out.println(operacion.get(j).equals(tabla.get(k).ide));
                            if(operacion.get(j).equals(tabla.get(k).ide)){
                                operacionTipos.add(tabla.get(k).tipo);
                                if(tabla.get(k).tipo.equals("int")){
                                    if(puntero==false){
                                        //System.out.println(puntero);
                                        OP="int";
                                    }
                                }
                                if(tabla.get(k).tipo.equals("double")){
                                    OP = "double";
                                }
                                if(tabla.get(k).tipo.equals("string")){
                                    OP = "string";
                                }
                                if(tabla.get(k).tipo.equals("bool")){
                                    //System.out.println("yes");
                                    OP = "bool";
                                }
                                break;
                            }
                            else{
                                if(k!=tabla.size()){
                                    k = k+1;
                                }
                                else{
                                    System.out.println("Error 526: .");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
                
                /*for(int i = 0;i<operacionTipos.size();i++){
                    System.out.println(operacionTipos.get(i));
                }
                System.out.println("Operador: "+OP);*/
            }
            else{
                for(int i = 0;i<tabla.size();){ //AGREGAR ID
                //System.out.println(operacion.get(i));
                    if(tabla.get(i).ide.equals(id)){
                        operacionTipos.add(tabla.get(i).tipo);
                        break;
                    }
                    else{
                        if(i!=(tabla.size() - 1)){
                            i = i+1;
                        }
                        else{
                            System.out.println("Error 526: .");
                            System.exit(0);
                        }
                    }
                }
                operacionTipos.add(operacion.get(1));   //AGREGAR =
                
                for(int j = 2;j<operacion.size();j++){
                    //operacionTipos.add(operacion.get(j));
                    
                    if(operacion.get(j).charAt(0)=='0'||operacion.get(j).charAt(0)=='1'||operacion.get(j).equals("2")
                            ||operacion.get(j).charAt(0)=='3'||operacion.get(j).charAt(0)=='4'||operacion.get(j).charAt(0)=='5'
                            ||operacion.get(j).charAt(0)=='6'||operacion.get(j).charAt(0)=='7'||operacion.get(j).charAt(0)=='8'
                            ||operacion.get(j).charAt(0)=='9'){
                        //System.out.println("test");
                        if(operacion.get(j).length()>1){
                            for(int p=1;p<operacion.get(j).length();p++){
                                if(operacion.get(j).charAt(1)=='.'){
                                    if(j>2){
                                        operacionTipos.remove(operacionTipos.size()-1);
                                    }
                                    operacionTipos.add("double");
                                    puntero=true;
                                    //System.out.println("ño");
                                    OP="double";
                                }
                                else{
                                    if(p==(operacion.get(j).length()-1)){
                                        if(puntero==false){
                                            //System.out.println("ñeee");
                                            OP="int";
                                            operacionTipos.add("int");
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            operacionTipos.add("int");
                            if(puntero==false){
                            //System.out.println(puntero);
                            OP="int";
                            }
                        }
                    }
                    else if(operacion.get(j).equals("+")||operacion.get(j).equals("-")||operacion.get(j).equals("*")
                            ||operacion.get(j).equals("/")||operacion.get(j).equals("^")){
                        operacionTipos.add(operacion.get(j));
                        if(operacion.get(j).equals("/")){
                            //operacionTipos.add("double");
                            //System.out.println("double");
                            puntero=true;
                            OP="double";
                        }
                    }
                    else if(operacion.get(j).charAt(0)=='"'){
                        OP = "string";
                        operacionTipos.add("string");
                    }
                    else if(operacion.get(j).equals("true")||operacion.get(j).equals("false")){
                        OP = "bool";
                        operacionTipos.add("bool");
                    }
                    else if(operacion.get(j).equals("(")||operacion.get(j).equals(")")||operacion.get(j).equals(";")){
                        operacionTipos.add(operacion.get(j));
                    }
                    else{ //ID's
                        //operacionTipos.add(operacion.get(j));
                        
                        for(int k = 0;k<tabla.size();){
                            //System.out.println(operacion.get(j).equals(tabla.get(k).ide));
                            if(operacion.get(j).equals(tabla.get(k).ide)){
                                operacionTipos.add(tabla.get(k).tipo);
                                if(tabla.get(k).tipo.equals("int")){
                                    if(puntero==false){
                                        //System.out.println(puntero);
                                        OP="int";
                                    }
                                }
                                if(tabla.get(k).tipo.equals("double")){
                                    OP = "double";
                                }
                                if(tabla.get(k).tipo.equals("string")){
                                    OP = "string";
                                }
                                if(tabla.get(k).tipo.equals("bool")){                                    
                                    OP = "bool";
                                }
                                break;
                            }
                            else{
                                if(k!=tabla.size()){
                                    k = k+1;
                                }
                                else{
                                    System.out.println("Error 526: Algo. Renglon: " + p.renglon);
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    
                }
                
                /*for(int i = 0;i<operacionTipos.size();i++){
                    System.out.println(operacionTipos.get(i));
                }
                System.out.println("Operador: "+OP);*/
            }

            //COMPATIBILIDAD DE TIPOS
            Boolean buffer = null;
            
            for(int i = 0;i<operacionTipos.size();){
                if(operacionTipos.get(i).equals(OP)){
                    buffer = true;
                    //System.out.println("Todo bien");
                    break;
                }
                else{
                    if(operacionTipos.get(i).equals("("))
                        i=i+1;
                    else if(i==(operacionTipos.size()-1) || i <= 1){
                        buffer = false;
                        //System.out.println("Todo mal");
                        break;
                        
                    }
                }
            }
            if(!buffer){
                System.out.println("Error 527: Conjunto de tipos no coinciden. Renglon: " + p.renglon);
                System.exit(0);
            }
        }
    }
    
    public void siguientePaso(){
        genIntermedio intermedio = new genIntermedio(cabeza,tabla,operaciones);
    }
    
    private void armarOperacion(){
        String texto = "";
        for(int i = 0; i < operacion.size();i++){
            texto = texto+operacion.get(i);
        }
        operaciones.add(texto);
    }
}

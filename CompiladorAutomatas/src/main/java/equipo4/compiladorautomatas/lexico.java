package equipo4.compiladorautomatas;

import java.io.RandomAccessFile;

public class lexico {
    nodo cabeza = null, p;
    int estado = 0, columna, valorMT, numRenglon = 1, caracter = 0;
    String lexema = "";
    boolean errorEncontrado = false, endfile = false;
    
    String archivo = "E:\\Users\\jpedr\\Escritorio\\CompiladorAutomatas\\src\\main\\java\\pruebas\\prueba.txt";
    
    int matriz[][] = {
            /*  l   d   .   +   -   *   /   ^   >   <   =   !   &   |   ,   :   ;   (   )   {   }   "   ed  tab nl  oc  eof*/
        /*0*/{  1,  2, 505,103,104,105,  5,107,  9,  8, 10, 11, 12, 13,117,118,119,120,121,122,123, 14, 0,   0,  0,505,  0},
        /*1*/{  1,  1, 100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100},
        /*2*/{101,  2,   3,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101},
        /*3*/{500,  4, 500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500},
        /*4*/{102,  4, 102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102},
        /*5*/{106,106, 106,106,106,  6,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106},
        /*6*/{  6,  6,   6, 6,   6,  7,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,501,  6,501},
        /*7*/{  6,  6,   6, 6,   6,  6,  0,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6,501,  6,501},
        /*8*/{108,108, 108,108,108,108,108,108,108,108,110,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108},
        /*9*/{109,109, 109,109,109,109,109,109,109,109,111,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109},
       /*10*/{125,125, 125,125,125,125,125,125,125,125,112,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125},
       /*11*/{116,116, 116,116,116,116,116,116,116,116,113,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116},
       /*12*/{502,502, 502,502,502,502,502,502,502,502,502,502,114,502,502,502,502,502,502,502,502,502,502,502,502,502,502},
       /*13*/{503,503, 503,503,503,503,503,503,503,503,503,503,503,115,503,503,503,503,503,503,503,503,503,503,503,503,503},
       /*14*/{ 14, 14,  14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,124, 14, 14,504, 14,504}
    };
    
    String palabrasRes[][] = {
        {"200","var"},
        {"201","print"},
        {"202","true"},
        {"203","false"},
        {"204","if"},
        {"205","else"},
        {"206","while"},
        {"207","string"},
        {"208","int"},
        {"209","double"},
        {"210","read"},
        {"211","class"}
    };
    
    String codErrores[][] = {
        {"500","Se espera un dígito"},
        {"501","Se espera cerrar comentario"},
        {"502","Se espera un & "},
        {"503","Se espera un | "},
        {"504","Se espera cerrar cadena"},
        {"505","Símbolo no valido"}
    };
    
    RandomAccessFile file = null;
    
    public lexico (){
        try{
            file = new RandomAccessFile(archivo,"r");
            
            //System.out.println(file.length());
            while (!endfile){
                //System.out.println(caracter);
                caracter = file.read();
                
                if(caracter == -1){
                    columna = 25;
                    endfile = true;
                    //System.out.println("Final del archivo");
                }
                else if(Character.isLetter((char)caracter)){
                    columna = 0;
                } else if(Character.isDigit((char)caracter)){
                    columna = 1;
                }
                else{
                    switch ((char) caracter){
                        case '.' : columna = 2;
                        break;
                        case '+' : columna = 3;
                        break;
                        case '-' : columna = 4;
                        break;
                        case '*' : columna = 5;
                        break;
                        case '/' : columna = 6;
                        break;
                        case '^' : columna = 7;
                        break;
                        case '>' : columna = 8;
                        break;
                        case '<' : columna = 9;
                        break;
                        case '=' : columna = 10;
                        break;
                        case '!' : columna = 11;
                        break;
                        case '&' : columna = 12;
                        break;
                        case '|' : columna = 13;
                        break;
                        case ',' : columna = 14;
                        break;
                        case ':' : columna = 15;
                        break;
                        case ';' : columna = 16;
                        break;
                        case '(' : columna = 17;
                        break;
                        case ')' : columna = 18;
                        break;
                        case '{' : columna = 19;
                        break;
                        case '}' : columna = 20;
                        break;
                        case '"' : columna = 21;
                        break;
                        case ' ' : columna = 22; //Espacio en blanco
                        break;
                        case 9 : columna = 23;    //Tabulador
                        break;
                        case 13 : {
                            columna = 24;  //Nueva linea
                            numRenglon = numRenglon+1;
                        }
                        break;
                        default: 
                        break;
                    }
                }
                
                valorMT = matriz[estado][columna];
                
                if(valorMT < 100){
                    estado = valorMT;
                    
                    if(estado == 0){
                        lexema = "";
                    }
                    else{
                        lexema = lexema + (char) caracter;
                    }
                }
                else if (valorMT >= 100 && valorMT < 500){
                    if (valorMT == 100){
                        validarPalabraReservada();//if 
                    }
                    
                    if (valorMT == 100 || valorMT == 101 || valorMT == 102 || valorMT == 106 
                            || valorMT == 108 || valorMT == 110 || valorMT == 125 
                            || valorMT == 116 || valorMT >= 200){
                        file.seek(file.getFilePointer()-1);
                    }
                    else{
                        lexema = lexema + (char) caracter;
                    }
                    
                    insertarNodo();
                    estado = 0;
                    lexema = "";
                }
                else{
                    imprimirError();
                    break;
                }
            }
            imprimirNodo();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private void imprimirError(){
        if (caracter != -1 && valorMT >= 500){
            errorEncontrado = true;
            for(String[] errore : codErrores){
                if (valorMT == Integer.valueOf(errore[0])){
                    if(valorMT == 505)
                        System.out.println("El error encontrado es: " + errore[1] + ", error " + valorMT + ", caracter " + caracter + ", en el renglon " + numRenglon);
                    else
                        System.out.println("El error encontrado es: " + errore[1] + ", error " + valorMT + ", caracter " + caracter + ", en el renglon " + (numRenglon -1));
                }
            }
        }
    }
    
    private void validarPalabraReservada(){
        for(String palabrasRes[] : palabrasRes){
            if(lexema.equals(palabrasRes[1])){
                valorMT = Integer.valueOf(palabrasRes[0]);
            }
        }
    }
    
    private void imprimirNodo(){
        p = cabeza;
        while (p!=null){
            if(!p.lexema.isBlank()){
                //System.out.println(p.lexema);
                System.out.println(p.lexema + " " + p.token + " " + p.renglon);
            }
            p = p.sig;
        }
    }
    
    private void insertarNodo(){
        nodo nodo = new nodo(lexema, valorMT, numRenglon);
        
        if(cabeza == null){
            cabeza = nodo;
            p = cabeza;
        }
        else{
            p.sig = nodo;
            p = nodo;
        }
    }

    public void sintaxis() {
        System.out.println("\nSINTACTICO\n");
        p = cabeza;
        while (p!=null){
           
            //Inicio sintactico
            if(p.token == 211){
                p = p.sig;
                if(p.token == 100){
                    p = p.sig;
                    if(p.token == 122){
                        declarar_var();
                        p = p.sig;
                        while(p.token != 123){
                            
                        }
                        if(p.token == 123){
                            break;
                        } else {
                            System.out.println("Se espera un }");
                        }
                    }
                    else{
                        System.out.println("Se espera {");
                    }
                }
                else{
                    System.out.println("Se espera ID");
                }
            }
            else{
                System.out.println("Se espera class");
            }
            
            p = p.sig;
        }
    }

    //revisión sintáctica sobre la declaración de variables
    private void declarar_var() {
        p = p.sig;
        if(p.token == 200){
            p = p.sig;
            while (p.token != 118){
                if(p.token == 100){
                    p = p.sig;
                    if(p.token == 117){
                        p = p.sig;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Se espera ID");
                    break;
                }
            }
            if(p.token == 118){
                tipo();
                p = p.sig;
                if(p.token == 119){
                    
                } else {
                    System.out.println("Se espera ;");
                }
            } else {
                System.out.println("Se espera :");
            }
        }
        else{
            System.out.println("Se espera var");
        }
    }

    private void tipo() {
        p = p.sig;
        if(p.token == 207 || p.token == 208 || p.token == 209){
            
        } else {
            System.out.println("Se espera un TIPO");
        }
    }
}

package equipo4.compiladorautomatas;

import java.io.RandomAccessFile;

public class lexico {
    nodo cabeza = null, p;
    int estado = 0, columna, valorMT, numRenglon = 1, caracter = 0;
    String lexema = "";
    boolean errorEncontrado = false;
    
    String archivo = "C:\\Users\\nuevo\\Desktop\\Compilador\\CompiladorAutomatas\\src\\main\\java\\pruebas\\prueba.txt";
    
    int matriz[][] = {
        /*0*/{1,2,505,103,104,105,5,107,9,8,10,11,12,13,117,118,119,120,121,122,123,14,0,0,0,505,0},
        /*1*/{1,1,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100},
        /*2*/{101,2,3,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101},
        /*3*/{500,4,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500,500},
        /*4*/{102,4,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102},
        /*5*/{106,106,106,106,106,106,6,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106},
        /*6*/{6,6,6,6,6,6,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,507},
        /*7*/{6,6,6,6,6,6,0,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
        /*8*/{108,108,108,108,108,108,108,108,108,108,110,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108},
        /*9*/{109,109,109,109,109,109,109,109,109,109,111,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109},
        /*10*/{125,125,125,125,125,125,125,125,125,125,112,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125},
        /*11*/{116,116,116,116,116,116,116,116,116,116,113,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116},
        /*12*/{502,502,502,502,502,502,502,502,502,502,502,502,114,502,502,502,502,502,502,502,502,502,502,502,502,502,502},
        /*13*/{503,503,503,503,503,503,503,503,503,503,503,503,503,115,503,503,503,503,503,503,503,503,503,503,503,503,503},
        /*14*/{14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,124,14,14,504,14,14}
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
        {"209","double"}
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
            while (caracter != -1){
                caracter = file.read();
                
                if(Character.isLetter((char)caracter)){
                    columna = 0;
                } else if(Character.isDigit((char)caracter)){
                    columna = 1;
                }
                else{
                    switch ((char) caracter){
                        case '+' : columna = ;
                        break;
                        case '-' : columna = ;
                        break;
                        case '*' : columna = ;
                        break;
                        case '/' : columna = ;
                        break;
                        case '^' : columna = ;
                        break;
                        case '<' : columna = ;
                        break;
                        case '>' : columna = ;
                        break;
                        case '=' : columna = ;
                        break;
                        case '!' : columna = ;
                        break;
                        case '&' : columna = ;
                        break;
                        case '|' : columna = ;
                        break;
                        case ',' : columna = ;
                        break;
                        case ':' : columna = ;
                        break;
                        case ';' : columna = ;
                        break;
                        case '(' : columna = ;
                        break;
                        case ')' : columna = ;
                        break;
                        case '{' : columna = ;
                        break;
                        case '}' : columna = ;
                        break;
                        case '"' : columna = ;
                        break;
                        case ' ' : columna = ; //Espacio en blanco
                        break;
                        case 10 : {columna = ;  //Nueva linea
                            numRenglon = numRenglon+1;
                        }
                        break;
                        case 9 : columna = ;    //Tabulador
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
                else if (valorMT >= 100 && valorMT <= 500){
                    if (valorMT == 100){
                        validarPalabraReservada();
                    }
                    
                    if (valorMT == 100 || valorMT == 101 || valorMT == 102){    //Terminarlo
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
            for(String[] errore : codErrores){
                if (valorMT == Integer.valueOf(errore[0])){
                    System.out.println("El error encontrado es: " + errore[1] + " error " + valorMT + " caracter " + caracter + " en el renglon " + numRenglon);
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
            System.out.println(p.lexema + " " + p.token + " " + p.renglon);
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
}

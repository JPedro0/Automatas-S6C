package equipo4.compiladorautomatas;

import java.io.RandomAccessFile;

public class compi {

    nodo cabeza = null, p;
    int estado = 0, columna, valorMT, numRenglon = 1, caracter = 0;
    String lexema = "";
    boolean errorEncontrado = false, endfile = false;

    String archivo = "E:\\Users\\jpedr\\Escritorio\\CompiladorAutomatas\\src\\main\\java\\pruebas\\prueba.txt";

    int matriz[][] = {
        /*  l   d   .   +   -   *   /   ^   >   <   =   !   &   |   ,   :   ;   (   )   {   }   "   ed  tab nl  oc  eof*/
        /*0*/{1, 2, 505, 103, 104, 105, 5, 107, 9, 8, 10, 11, 12, 13, 117, 118, 119, 120, 121, 122, 123, 14, 0, 0, 0, 505, 0},
        /*1*/ {1, 1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100},
        /*2*/ {101, 2, 3, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101},
        /*3*/ {500, 4, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500},
        /*4*/ {102, 4, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102},
        /*5*/ {106, 106, 106, 106, 106, 6, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106},
        /*6*/ {6, 6, 6, 6, 6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 501, 6, 501},
        /*7*/ {6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 501, 6, 501},
        /*8*/ {108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 110, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108},
        /*9*/ {109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 111, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109},
        /*10*/ {125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 112, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125},
        /*11*/ {116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 113, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116},
        /*12*/ {502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 114, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502},
        /*13*/ {503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 115, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503},
        /*14*/ {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 124, 14, 14, 504, 14, 504}
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
        {"211", "class"}
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
                            columna = 8;
                            break;
                        case '<':
                            columna = 9;
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
                            || valorMT == 108 || valorMT == 110 || valorMT == 125
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
            errorEncontrado = true;
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
        System.out.println("\nSINTACTICO\n");
        p = cabeza;
        while (p != null) {

            //Inicio sintactico
            if (p.token == 211) {
                p = p.sig;
                if (p.token == 100) {
                    p = p.sig;
                    if (p.token == 122) {
                        declarar_var();
                        p = p.sig;
                        while (p.token != 123) { //Busca un }
                            statement();
                            if (p.sig == null) {
                                break;
                            } else {
                                p = p.sig;
                            }
                        }
                        if (p.token == 123) {
                            break;
                        } else {
                            System.out.println("Se espera un }");
                            errorEncontrado = true;
                        }
                    } else {
                        System.out.println("Se espera {");
                        errorEncontrado = true;
                    }
                } else {
                    System.out.println("Se espera ID");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera class");
                errorEncontrado = true;
            }

            p = p.sig;
        }
    }

    //revisión sintáctica sobre la declaración de variables
    private void declarar_var() {
        p = p.sig;
        if (p.token == 200) {
            p = p.sig;
            while (p.token != 118) { // Busca un :
                if (p.token == 100) {
                    p = p.sig;
                    if (p.token == 117) {
                        p = p.sig;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Se espera ID");
                    errorEncontrado = true;
                    break;
                }
            }
            if (p.token == 118) {
                tipo();
                p = p.sig;
                if (p.token == 119) {
                    //vacio
                } else {
                    System.out.println("Se espera ;");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera :");
                errorEncontrado = true;
            }
        } else {
            System.out.println("Se espera var");
            errorEncontrado = true;
        }
    }

    private void tipo() {
        p = p.sig;
        if (p.token == 207 || p.token == 208 || p.token == 209) {
            //vacio
        } else {
            System.out.println("Se espera un TIPO");
            errorEncontrado = true;
        }
    }

    private void statement() {
        if (p.token == 121) {
            System.out.println("Se espera un ESTATUTO");
            errorEncontrado = true;
        }
        //System.out.println(p.lexema);
        if (p.token == 201) { //print (a);
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                if (p.token == 100) {
                    p = p.sig;
                    if (p.token == 121) {
                        p = p.sig;
                        if (p.token != 119) {
                            System.out.println("Se espera un ;");
                            errorEncontrado = true;
                        }
                    } else {
                        System.out.println("Se espera un )");
                        errorEncontrado = true;
                    }
                } else {
                    System.out.println("Se espera un ID");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera un (");
                errorEncontrado = true;
            }
        }
        if (p.token == 210) { //read (a);
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                if (p.token == 100) {
                    p = p.sig;
                    if (p.token == 121) {
                        p = p.sig;
                        if (p.token != 119) {
                            System.out.println("Se espera un ;");
                            errorEncontrado = true;
                        }
                    } else {
                        System.out.println("Se espera un )");
                        errorEncontrado = true;
                    }
                } else {
                    System.out.println("Se espera un ID");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera un (");
                errorEncontrado = true;
            }
        }
        if (p.token == 100) { //ID = a;
            p = p.sig;
            if (p.token == 125) {
                expresion_simple();
                p = p.sig;
                if (p.token != 119) {
                    System.out.println("Se espera un ;");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera un =");
                errorEncontrado = true;
            }
        }
        if (p.token == 206) { //while(1>2)
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                expresion_condicional();
                if (p.token == 121) {
                    p = p.sig;
                    if (p.token == 122) {
                        p = p.sig;
                        while (p.token != 123) { //Busca un }
                            statement();
                            if (p.sig == null) {
                                System.out.println("Se espera un }");
                                errorEncontrado = true;
                                break;
                            } else {
                                p = p.sig;
                            }
                        }
                        if (p.token == 123) {
                            //vacio
                        } else {
                            System.out.println("Se espera un }");
                            errorEncontrado = true;
                        }
                    } else {
                        System.out.println("Se espera un {");
                        errorEncontrado = true;
                    }
                } else {
                    System.out.println("Se espera un )");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera un (");
                errorEncontrado = true;
            }
        }
        if (p.token == 204) { // IF{}
            p = p.sig;
            if (p.token == 120) {
                p = p.sig;
                expresion_condicional();
                if (p.token == 121) {
                    p = p.sig;
                    if (p.token == 122) {
                        p = p.sig;
                        while (p.token != 123) { //Busca un }
                            statement();
                            if (p.sig == null) {
                                System.out.println("Se espera un }");
                                errorEncontrado = true;
                                break;
                            } else {
                                p = p.sig;
                            }
                        }
                        if (p.token == 123) {
                            p = p.sig;
                            if (p.token == 205) { // ELSE{}
                                p = p.sig;
                                if (p.token == 122) {
                                    p = p.sig;
                                    while (p.token != 123) { //Busca un }
                                        statement();
                                        if (p.sig == null) {
                                            System.out.println("Se espera un }");
                                            errorEncontrado = true;
                                            break;
                                        } else {
                                            p = p.sig;
                                        }
                                    }
                                    if (p.token == 123) {
                                        //vacio
                                    } else {
                                        System.out.println("Se espera un }");
                                        errorEncontrado = true;
                                    }
                                } else {
                                    System.out.println("Se espera un {");
                                    errorEncontrado = true;
                                }
                            }
                        } else {
                            System.out.println("Se espera un }");
                            errorEncontrado = true;
                        }
                    } else {
                        System.out.println("Se espera un {");
                        errorEncontrado = true;
                    }
                } else {
                    System.out.println("Se espera un )");
                    errorEncontrado = true;
                }
            } else {
                System.out.println("Se espera un (");
                errorEncontrado = true;
            }
        }
    }

    private void expresion_simple() {
        //System.out.println(p.lexema);
        p = p.sig;

    }

    private void expresion_condicional() {
        //System.out.println("1"+p.lexema);
        expresion_simple();
        //System.out.println("2"+p.lexema);
        operador_relacional();
        //System.out.println("3"+p.lexema);
        expresion_simple();
        //System.out.println(p.token);
    }
    
    private void signo() {
        if(p.token == 103 || p.token == 104){
            //vacio
        } else {
            System.out.println("Se espera un SIGNO");
            errorEncontrado = true;
        }
    }
    
    private void operador_aditivo() {
        if(p.token == 103 || p.token == 104 || p.token == 115){
            //vacio
        } else {
            System.out.println("Se espera un OPERADOR ADITIVO");
            errorEncontrado = true;
        }
    }
    
    private void operador_mult() {
        if(p.token == 105 || p.token == 106 || p.token == 114){
            //vacio
        } else {
            System.out.println("Se espera un OPERADOR MULTIPLICATIVO");
            errorEncontrado = true;
        }
    }
    
    private void operador_relacional() {
        if(p.token == 110 || p.token == 111 || p.token == 108 || p.token == 112 || p.token == 113 || p.token == 113){
            p = p.sig;
        } else {
            System.out.println("Se espera un OPERADOR RELACIONAL");
            errorEncontrado = true;
        }
    }
    
    private void factor() {
        switch (p.token) {
            case 100:
                //vacio
                break;
            case 124:
                //vacio
                break;
            case 116:
                p = p.sig;
                factor();
                break;
            default:
                expresion_simple();
                break;
        }
    }
    
    private void termino() {
        System.out.println("termino");
    }
}

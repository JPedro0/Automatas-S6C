/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipo4.compiladorautomatas;

import java.io.RandomAccessFile;

/**
 *
 * @author nuevo
 */
public class prueba_Doc {
    String archivo = "C:\\Users\\nuevo\\Desktop\\Compilador\\CompiladorAutomatas\\src\\main\\java\\pruebas\\prueba.txt";
    RandomAccessFile file = null;
    int caracter = 0;
    
    public void prueba_Doc(){
        try{
            file = new RandomAccessFile(archivo,"r");
            
            while (caracter != -1){
                caracter = file.read();
                if((char) caracter == '\n'){
                    System.out.println("Salto de linea");
                }
                else{
                    System.out.println((char) caracter);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

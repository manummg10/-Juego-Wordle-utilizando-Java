/**
 * Clase WordleFileManager que maneja la lectura y escritura de archivos.
 * 
 * - Permite leer palabras desde un archivo externo y cargarlas en el juego.
 * - Guarda el historial de las partidas en un archivo de texto.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordleFileManager {

    /**
     * Carga palabras desde un archivo de texto y las almacena en un array de Strings.
     * 
     * - Lee el archivo línea por línea.
     * - Convierte cada palabra a mayúsculas para mantener consistencia.
     * - Filtra palabras que no tengan exactamente 5 letras.
     * - Devuelve un array con todas las palabras cargadas.
     * 
     * fileName  Ruta del archivo donde están almacenadas las palabras.
     * Un array de Strings con todas las palabras cargadas desde el archivo.
     * FileNotFoundException Si el archivo no existe o no se puede leer.
     */
    public String[] loadWordsFromFile(String fileName) throws FileNotFoundException {
        Set<String> words = new HashSet<>(); // Usamos un Set para evitar palabras repetidas.

        // Abrimos el archivo utilizando la ruta proporcionada.
        Scanner scanner = new Scanner(new File("C:\\Users\\manu_\\eclipse-workspace\\Trabajo de enfoque Programacion\\src\\palabras.txt"));

        // Leemos el archivo línea por línea.
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().toUpperCase(); // Convertimos la palabra a mayúsculas.

            // Verificamos que la palabra tenga exactamente 5 letras antes de añadirla.
            if (word.length() == 5) {
                words.add(word);
            }
        }

        scanner.close(); // Cerramos el scanner para liberar recursos.

        // Convertimos el Set en un array de Strings y lo retornamos.
        return words.toArray(new String[0]);
    }
    
/**
 * Guarda el historial de la partida en un archivo de texto.
 * 
 * - Recibe un `String` con el historial de la partida y lo guarda en un archivo.
 * - Si el archivo no existe, se crea automáticamente.
 * - Si el archivo ya existe, se sobrescribe.
 * 
 * gameData  Contenido de la partida (historial de intentos y resultado).
 * fileName  Nombre del archivo donde se guardará la información.
 * FileNotFoundException Si ocurre un error al intentar escribir en el archivo.
 */

public void saveGame(String gameData, String fileName) throws FileNotFoundException {
    // Utilizamos PrintWriter para escribir en el archivo.
    try (PrintWriter writer = new PrintWriter(fileName)) {
        writer.print(gameData); // Guardamos el contenido de la partida en el archivo.
    }
  }
}


  

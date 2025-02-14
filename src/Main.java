import java.io.FileNotFoundException;

/**
 * Clase principal que inicia el programa Wordle.
 * 
 * - Carga las palabras desde un archivo externo.
 * - Crea una instancia del juego `WordleGame` con las palabras cargadas.
 * - Ejecuta el juego llamando al método `start()`.
 */
public class Main {
	
	// Crear un administrador de archivos para leer la lista de palabras
	public static void main(String[] args) throws FileNotFoundException {

	// Cargar palabras desde el archivo palabras.txt
	WordleFileManager fileManager = new WordleFileManager();
	String[] words = fileManager.loadWordsFromFile("C:\\Users\\manu_\\eclipse-workspace\\Trabajo de enfoque Programacion\\src\\palabras.txt");

	// Verificar si hay palabras disponibles en el archivo
	if (words.length == 0) {
	System.out.println("No se encontraron palabras en el archivo.");
	return;
	}

	// Iniciar el juego con las palabras cargadas
	WordleGame game = new WordleGame(words);
	game.start();
	}
}



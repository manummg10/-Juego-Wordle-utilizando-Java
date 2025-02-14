
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase WordleGame que contiene la lógica principal del juego.
 * 
 * - Gestiona los intentos del jugador.
 * - Verifica si la palabra ingresada es correcta.
 * - Guarda el historial de intentos.
 * - Proporciona retroalimentación con colores.
 * - Guarda el resultado de la partida en un archivo.
 */
public class WordleGame {
	/***
	 * Atributos de la clase WordleGame
	 */
    private static final int MAX_TRIES = 6; // Número máximo de intentos
    private static final int WORD_LENGTH = 5; // Longitud de las palabras del juego

    private String[] fileWords; // Lista de palabras cargadas desde el archivo
    private String secretWord; // Palabra secreta que el jugador debe adivinar
    private int remainingAttempts; // Número de intentos restantes
    private String[] triesHistory; // Historial de palabras ingresadas por el jugador

    
    /**
     * Constructor de la clase WordleGame.
     * 
     * - Inicializa la lista de palabras.
     * - Selecciona una palabra secreta aleatoria.
     * - Configura el número máximo de intentos.
     */
    public WordleGame(String[] fileWords) {
        this.fileWords = fileWords;
        this.secretWord = selectRandomWord(fileWords); // Seleccionar palabra secreta aleatoria
        this.remainingAttempts = MAX_TRIES;
        this.triesHistory = new String[MAX_TRIES]; // Inicializar historial de intentos
    }
    

    /**
     * Inicia el juego y gestiona el flujo principal de la partida.
     * 
     * - Pide al usuario ingresar palabras.
     * - Verifica si la palabra es correcta o incorrecta.
     * - Muestra el historial de intentos y retroalimentación en colores.
     * - Finaliza cuando el usuario adivina la palabra o se queda sin intentos.
     */   
    public void start() {
    	System.out.println("\n¡Bienvenido al Wordle de Manu Moraira Garcia!\n");
        Scanner scanner = new Scanner(System.in);
        boolean hasWon = false; // Indica si el jugador ha ganado
      

        while (remainingAttempts > 0) {
            System.out.println("Tienes " + remainingAttempts + " intentos restantes.");
            showTriesHistory(); // Mostrar el historial de intentos
            String guess = getUserInput(scanner); // Obtener la palabra ingresada por el jugador

            if (guess.equals(secretWord)) {
            	System.out.println("\n=============================="); // Línea separadora
            	System.out.println(WordleFeedback.ANSI_GREEN + "¡Felicidades! Has adivinado la palabra correcta: " + secretWord + WordleFeedback.ANSI_RESET);
                System.out.println("==============================\n"); // Línea separadora
                hasWon = true;
                break; 
            } else {
            	String feedback = WordleFeedback.feedBackString(guess, secretWord);
                triesHistory[MAX_TRIES - remainingAttempts] = feedback;  // Guardar feedback con colores
                System.out.println(feedback);
                remainingAttempts--;
            }
        }

        if (!hasWon) {
            System.out.println("\n==============================");
            System.out.println(WordleFeedback.ANSI_RED + "¡Has perdido! La palabra correcta era: " + secretWord + WordleFeedback.ANSI_RESET);
            System.out.println("==============================\n");
        }

        // Guardar el resultado de la partida en un archivo
        try {
            saveGameResult(hasWon);
            System.out.println("El historial de la partida se ha guardado en 'partida.txt'.");
        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar el historial de la partida.");
        }

        scanner.close(); // Cerrar el escáner
    }
    
    
    /**
     * Muestra el historial de palabras ingresadas por el usuario.
     */
    private void showTriesHistory() {
        System.out.println("\nHistorial de intentos:");
       

        boolean hasAttempts = false; // Para verificar si hay intentos registrados

        for (String word : triesHistory) {
            if (word != null) {
                System.out.println(word); // Imprime cada palabra en una línea separada
                hasAttempts = true;
            }
        }

        if (!hasAttempts) {
            System.out.println("Aún no has ingresado ninguna palabra.");
        }  
    }
    
    
    /**
     * Selecciona aleatoriamente una palabra de la lista de palabras disponibles.
     */
    private String selectRandomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    
    /**
     * Solicita al usuario ingresar una palabra y valida su formato.
     */
    private String getUserInput(Scanner scanner) {
        while (true) {
        	System.out.println("\n=============================="); // Línea separadora
            System.out.print("Introduce una palabra de 5 letras: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != WORD_LENGTH) {
                System.out.println(WordleFeedback.ANSI_RED + "La palabra debe tener 5 letras." + WordleFeedback.ANSI_RESET);
            } else if (!isWordValid(input)) {
                System.out.println(WordleFeedback.ANSI_RED + "La palabra no está en la lista." + WordleFeedback.ANSI_RESET);
            } else {
                return input;
            }
        }
    }
    
    
    /**
     * Verifica si la palabra ingresada está en la lista de palabras disponibles.
     */
    private boolean isWordValid(String word) {
        for (String validWord : fileWords) {
            if (validWord.equals(word)) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Guarda el historial de la partida en un archivo.
     */
    private void saveGameResult(boolean hasWon) throws FileNotFoundException {
        StringBuilder gameResult = new StringBuilder();
        
         // Agregar una línea separadora para diferenciar las partidas
        gameResult.append("\n==============================\n");
        
        // Agregar la fecha y hora de la partida
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        gameResult.append("Fecha de la partida: ").append(timestamp).append("\n");
        // Agregar historial de intentos
        gameResult.append("Historial de la partida:\n");
        
        for (String attempt : triesHistory) {
            if (attempt != null) {
            	gameResult.append(removeAnsiCodes(attempt)).append("\n");
            }
        }
        
        // Agregar resultado y palabra secreta
        gameResult.append("Resultado: ").append(hasWon ? "¡Ganaste!" : "Perdiste.").append("\n");
        gameResult.append("Palabra secreta: ").append(secretWord).append("\n");

     // Ruta del archivo donde se guardarán todas las partidas
        String filePath = "C:\\Users\\manu_\\eclipse-workspace\\Trabajo de enfoque Programacion\\src\\partida.txt";

   
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(gameResult.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de la partida.");
        }
    }

    // método para eliminar los códigos ANSI
    private String removeAnsiCodes(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", ""); // Elimina códigos ANSI
    }
}

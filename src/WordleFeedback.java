import java.util.ArrayList;
import java.util.List;

/**
 * Clase WordleFeedback que genera retroalimentación visual con colores ANSI.
 * 
 * - Utiliza códigos de color ANSI para resaltar letras en la terminal.
 * - Compara la palabra ingresada por el jugador con la palabra secreta.
 * - Devuelve un `String` con cada letra coloreada según su acierto o error.
 */
public class WordleFeedback {

    // Código ANSI para resetear el color al valor predeterminado de la consola.
    public static final String ANSI_RESET = "\u001B[0m";

    // Código ANSI para el color VERDE (letra correcta y en la posición correcta).
    public static final String ANSI_GREEN = "\u001B[32m";

    // Código ANSI para el color AMARILLO (letra correcta pero en posición incorrecta).
    public static final String ANSI_YELLOW = "\u001B[33m";

    // Código ANSI para el color BLANCO (letra incorrecta, no está en la palabra secreta).
    public static final String ANSI_WHITE = "\u001B[37m";
    
    // Rojo para errores
    public static final String ANSI_RED = "\u001B[31m"; 
    
    // Lista para almacenar el historial de intentos con colores
    private static final List<String> triesHistory = new ArrayList<>();

    /**
     * Genera la retroalimentación visual para la palabra ingresada.
     * 
     * - Compara cada letra de la palabra ingresada (`guess`) con la palabra secreta (`secretWord`).
     * - Si la letra está en la posición correcta, se muestra en verde.
     * - Si la letra está en la palabra pero en la posición incorrecta, se muestra en amarillo.
     * - Si la letra no está en la palabra secreta, se muestra en blanco.
     * 
     * guess       La palabra ingresada por el usuario.
     * secretWord  La palabra secreta que se debe adivinar.
     * Un `String` con la palabra coloreada según las reglas del juego.
     */
    public static String feedBackString(String guess, String secretWord) {
        // StringBuilder para construir la palabra coloreada.
        StringBuilder feedback = new StringBuilder();

        // Recorre cada letra de la palabra ingresada por el jugador.
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i); // Obtiene la letra en la posición actual.

            // Si la letra está en la misma posición en ambas palabras, es correcta (verde).
            if (letter == secretWord.charAt(i)) {
                feedback.append(ANSI_GREEN).append(letter).append(ANSI_RESET);
            } 
            // Si la letra está en la palabra secreta pero en otra posición, es casi correcta (amarillo).
            else if (secretWord.indexOf(letter) >= 0) {
                feedback.append(ANSI_YELLOW).append(letter).append(ANSI_RESET);
            } 
            // Si la letra no está en la palabra secreta, es incorrecta (blanco).
            else {
                feedback.append(ANSI_WHITE).append(letter).append(ANSI_RESET);
            }
        }
        String result = feedback.toString();
        triesHistory.add(result);  // Agregar intento al historial
        return result;
    }
}
    
  

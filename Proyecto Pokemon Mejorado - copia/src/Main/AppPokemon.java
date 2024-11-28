package Main;

import Interfaz.InterfazPokemon;

/**
 * Clase principal que inicia la aplicación del juego de Pokémon.
 * Se encarga de crear una instancia de la interfaz y ejecutar el flujo del juego.
 */
public class AppPokemon {

    /**
     * Método principal que ejecuta la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        // Crea una instancia de la interfaz del juego y comienza el juego.
        InterfazPokemon juegoPokemon = new InterfazPokemon();
        juegoPokemon.Juego();
    }
}

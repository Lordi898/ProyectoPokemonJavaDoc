package combate;

import objeto.Pokemon;

/**
 * Clase que representa un combate entre dos Pokémon.
 * Se encarga de gestionar las rondas y determinar el ganador del combate.
 */
public class Combate {

    private Pokemon pokemonJugador;
    private Pokemon pokemonRival;

    /**
     * Constructor que inicializa el combate con los Pokémon del jugador y del rival.
     *
     * @param pokemonJugador El Pokémon del jugador.
     * @param pokemonRival   El Pokémon rival.
     */
    public Combate(Pokemon pokemonJugador, Pokemon pokemonRival) {
        this.pokemonJugador = pokemonJugador;
        this.pokemonRival = pokemonRival;
    }

    /**
     * Ejecuta una ronda del combate en la que ambos Pokémon usan sus habilidades especiales
     * y se comparan sus poderes para determinar el ganador de la ronda.
     *
     * @return El Pokémon que gana la ronda, o {@code null} si hay un empate.
     */
    public Pokemon Ronda() {
        System.out.println("Iniciando ronda...");

        // Pokémon jugador usa habilidad especial.
        pokemonJugador.usarHabilidadEspecial();

        // Pokémon rival usa habilidad especial.
        pokemonRival.usarHabilidadEspecial();

        int poderJugador = pokemonJugador.calcularPoder(pokemonRival);
        int poderRival = pokemonRival.calcularPoder(pokemonJugador);

        System.out.println("Poder de " + pokemonJugador.getNombre() + ": " + poderJugador);
        System.out.println("Aguante de " + pokemonJugador.getNombre() + ": " + pokemonJugador.getAguante());
        System.out.println("Poder de " + pokemonRival.getNombre() + ": " + poderRival);
        System.out.println("Aguante de " + pokemonRival.getNombre() + ": " + pokemonRival.getAguante());

        if (poderJugador > poderRival) {
            pokemonRival.disminuirAguante();
            System.out.println("Gana la ronda: " + pokemonJugador.getNombre());
            return pokemonJugador;
        } else if (poderJugador < poderRival) {
            pokemonJugador.disminuirAguante();
            System.out.println("Gana la ronda: " + pokemonRival.getNombre());
            return pokemonRival;
        } else {
            System.out.println("Empate en esta ronda");
            return null;
        }
    }

    /**
     * Determina si hay un ganador del combate basado en el aguante de los Pokémon.
     *
     * @return El Pokémon ganador si uno de los Pokémon tiene 0 puntos de aguante, o {@code null} si el combate continúa.
     */
    public Pokemon Ganador() {
        if (pokemonJugador.getAguante() > 0 && pokemonRival.getAguante() == 0) {
            return pokemonJugador;
        } else if (pokemonRival.getAguante() > 0 && pokemonJugador.getAguante() == 0) {
            return pokemonRival;
        } else {
            return null; // El combate aún no ha terminado
        }
    }
}

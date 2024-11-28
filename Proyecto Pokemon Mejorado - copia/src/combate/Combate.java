package combate;

import objeto.Pokemon;

public class Combate {

    // Añada los atributos y el constructor *************

    private Pokemon pokemonJugador;
    private Pokemon pokemonRival;

    //***************************************************

    public Combate (Pokemon pokemonJugador, Pokemon pokemonRival) {
        this.pokemonJugador = pokemonJugador;
        this.pokemonRival = pokemonRival;
    }

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

    public Pokemon Ganador() {
        if (pokemonJugador.getAguante() > 0 && pokemonRival.getAguante() == 0) {
            return pokemonJugador;
        }

        else if (pokemonRival.getAguante() > 0 && pokemonJugador.getAguante() == 0) {
            return pokemonRival;
        }

        else {
            return null; //El combate aun no ha terminado
        }
    }
}


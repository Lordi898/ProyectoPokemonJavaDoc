package Interfaz;

import combate.Combate;
import objeto.Pokemon;

import java.util.Scanner;

/**
 * Clase que representa la interfaz principal para gestionar el juego de Pokémon.
 * Se encarga de la interacción con el usuario y el flujo general del juego.
 */
public class InterfazPokemon {
    private Scanner teclado;

    /**
     * Constructor que inicializa la interfaz con un escáner para entrada de datos.
     */
    public InterfazPokemon() {
        this.teclado = new Scanner(System.in);
    }

    /**
     * Método principal que gestiona el flujo completo del juego.
     * Incluye la creación del Pokémon del jugador y las batallas contra tres rivales.
     */
    public void Juego() {
        System.out.println("¡Bienvenido al Juego de Pokémon!");

        // Crear Pokémon del jugador
        Pokemon pokemonJugador = menuCreacionPokemonJugador();

        // Inicia las partidas contra 3 rivales
        for (int i = 1; i <= 3; i++) {
            System.out.println("PULSE ENTER PARA CONTINUAR...");
            teclado.nextLine();
            System.out.println("Te enfrentarás al rival número " + i);
            System.out.println("Presentación del pokemon oponente: ");
            Pokemon pokemonRival = siguientePokemonRival(i);
            System.out.println(pokemonRival);

            System.out.println("PULSE ENTER PARA CONTINUAR...");
            teclado.nextLine();

            // Crear y ejecutar una partida
            Pokemon ganador = Partida(pokemonJugador, pokemonRival);

            if (ganador == pokemonJugador) {
                System.out.println("¡Has ganado el combate contra " + pokemonRival.getNombre() + "!");
                if (i < 3) {
                    pokemonJugador.subirNivel();
                    System.out.println("¡Tu Pokémon ha subido de nivel! Nivel actual: " + pokemonJugador.getNivel());
                } else {
                    mostrarJuegoSuperado();
                    return;
                }
            } else {
                mostrarFinPartida();
                return;
            }
        }
    }

    /**
     * Gestiona un combate completo entre el Pokémon del jugador y el Pokémon rival.
     *
     * @param pokemonJugador El Pokémon del jugador.
     * @param pokemonRival   El Pokémon rival.
     * @return El Pokémon ganador del combate.
     */
    public Pokemon Partida(Pokemon pokemonJugador, Pokemon pokemonRival) {
        Combate combate = new Combate(pokemonJugador, pokemonRival);

        System.out.println("¡Que comience el combate!");
        while (combate.Ganador() == null) {
            combate.Ronda();
        }

        return combate.Ganador();
    }

    /**
     * Muestra un menú para la creación del Pokémon del jugador, permitiendo elegir su nombre y tipo.
     *
     * @return El Pokémon creado por el jugador.
     */
    public Pokemon menuCreacionPokemonJugador() {
        System.out.println("........................");
        System.out.println("Crea tu Pokémon......");
        System.out.println("........................");

        System.out.print("Introduce un nombre: ");
        String nombre = teclado.nextLine();

        String tipo = "";
        while (true) {
            System.out.println("Elige su tipo: ");
            System.out.println("1.- Agua");
            System.out.println("2.- Tierra");
            System.out.println("3.- Fuego");
            System.out.println("4.- Eléctrico");
            int opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar buffer
            if (opcion == 1) {
                tipo = "agua";
                break;
            } else if (opcion == 2) {
                tipo = "tierra";
                break;
            } else if (opcion == 3) {
                tipo = "fuego";
                break;
            } else if (opcion == 4) {
                tipo = "eléctrico";
                break;
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        return new Pokemon(nombre, tipo);
    }

    /**
     * Selecciona el Pokémon rival según el número de combate.
     *
     * @param numero El número del combate (1, 2 o 3).
     * @return El Pokémon rival correspondiente.
     */
    public Pokemon siguientePokemonRival(int numero) {
        return switch (numero) {
            case 1 -> new Pokemon("Caterpie", "tierra", 1);
            case 2 -> new Pokemon("Bulbasaur", "agua", 2);
            case 3 -> new Pokemon("Charmander", "fuego", 3);
            default -> null;
        };
    }

    /**
     * Muestra un mensaje de victoria cuando el jugador gana el juego.
     */
    public void mostrarJuegoSuperado() {
        System.out.println("PULSE ENTER PARA CONTINUAR...");
        teclado.nextLine();
        System.out.println("++++++++++ ENHORABUENA +++++++++++");
        System.out.println("+++++ HAS SUPERADO EL JUEGO ++++++");
        System.out.println("++++ ERES UN MAESTRO POKEMON +++++");
    }

    /**
     * Muestra un mensaje cuando el jugador pierde el juego.
     */
    public void mostrarFinPartida() {
        System.out.println("++++++++++ LO SIENTO +++++++++++");
        System.out.println("+++++ HAS SIDO ELIMINADO ++++++");
        System.out.println("+++++ VUELVE A INTENTARLO ++++++");
    }
}

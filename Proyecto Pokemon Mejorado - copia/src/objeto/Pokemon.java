package objeto;

import java.util.Random;

/**
 * Clase que representa un Pokémon con nombre, tipo, nivel y aguante.
 */
public class Pokemon {
    private String nombre;
    private String tipo;
    private int nivel;
    private int aguante;

    /**
     * Constructor que inicializa un Pokémon con nombre y tipo. 
     * El nivel se establece en 1 por defecto.
     *
     * @param nombre El nombre del Pokémon.
     * @param tipo   El tipo del Pokémon (por ejemplo, agua, fuego).
     */
    public Pokemon(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = 1;
        this.aguante = calcularAguante(this.nivel);
    }

    /**
     * Constructor que inicializa un Pokémon con nombre, tipo y nivel específico.
     *
     * @param nombre El nombre del Pokémon.
     * @param tipo   El tipo del Pokémon.
     * @param nivel  El nivel inicial del Pokémon.
     */
    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.aguante = calcularAguante(nivel);
    }

    /**
     * Calcula el aguante del Pokémon en función de su nivel.
     *
     * @param nivel El nivel del Pokémon.
     * @return El aguante calculado basado en el nivel.
     */
    private int calcularAguante(int nivel) {
        return nivel * 3;
    }

    /**
     * Obtiene el aguante actual del Pokémon.
     *
     * @return El aguante del Pokémon.
     */
    public int getAguante() {
        return aguante;
    }

    /**
     * Establece el aguante del Pokémon.
     *
     * @param aguante El nuevo valor del aguante.
     */
    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    /**
     * Obtiene el nombre del Pokémon.
     *
     * @return El nombre del Pokémon.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tipo del Pokémon.
     *
     * @return El tipo del Pokémon.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el nivel actual del Pokémon.
     *
     * @return El nivel del Pokémon.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Establece un nuevo nivel para el Pokémon y actualiza su aguante.
     *
     * @param nivel El nuevo nivel del Pokémon.
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.aguante = calcularAguante(nivel);
    }

    /**
     * Usa la habilidad especial del Pokémon basada en su tipo, 
     * lo que modifica el aguante o el nivel temporalmente.
     */
    public void usarHabilidadEspecial() {
        switch (this.tipo) {
            case "agua" -> {
                this.aguante += 2;
                System.out.println(this.nombre + " recupera aguante gracias a su habilidad especial.");
            }
            case "fuego" -> {
                this.nivel++;
                System.out.println(this.nombre + " aumenta su nivel gracias a su habilidad especial.");
            }
            case "tierra" -> {
                this.aguante += 1;
                System.out.println(this.nombre + " gana aguante por su resistencia al terreno.");
            }
            case "eléctrico" -> {
                this.aguante -= 1;
                System.out.println(this.nombre + " sacrifica aguante para potenciar su ataque.");
            }
            default -> System.out.println(this.nombre + " no tiene una habilidad especial activa.");
        }
    }

    /**
     * Calcula el poder del Pokémon contra un contrincante considerando ventajas de tipo.
     * Si el Pokémon es de tipo "eléctrico", reduce el aguante del contrincante.
     *
     * @param contrincante El Pokémon oponente.
     * @return El poder calculado del Pokémon contra el oponente.
     */
    public int calcularPoder(Pokemon contrincante) {
        Random random = new Random();
        int poderBase = switch (this.nivel) {
            case 1 -> random.nextInt(8) + 3;
            case 2 -> random.nextInt(15) + 6;
            case 3 -> random.nextInt(22) + 9;
            default -> random.nextInt(29) + 12;
        };

        int modificador = switch (this.tipo) {
            case "agua" -> contrincante.tipo.equals("fuego") ? 2 * this.nivel :
                    contrincante.tipo.equals("tierra") ? -2 * contrincante.nivel : 0;
            case "fuego" -> contrincante.tipo.equals("tierra") ? 2 * this.nivel :
                    contrincante.tipo.equals("agua") ? -2 * contrincante.nivel : 0;
            case "tierra" -> contrincante.tipo.equals("agua") ? 2 * this.nivel :
                    contrincante.tipo.equals("fuego") ? -2 * contrincante.nivel : 0;
            case "eléctrico" -> contrincante.tipo.equals("agua") ? 3 * this.nivel : 0;
            default -> 0;
        };

        if (this.tipo.equals("eléctrico")) {
            contrincante.setAguante((int) (contrincante.getAguante() * 0.9));
            System.out.println(contrincante.getNombre() + " pierde un 10% de su aguante por la descarga eléctrica.");
        }

        return Math.max(poderBase + modificador, 0);
    }

    /**
     * Incrementa el nivel del Pokémon y actualiza sus estadísticas.
     */
    public void subirNivel() {
        this.nivel++;
        this.actualizarStats();
    }

    /**
     * Recalcula las estadísticas del Pokémon en función de su nivel.
     */
    public void actualizarStats() {
        this.aguante = (int) Math.round(nivel * 2.5);
    }

    /**
     * Reduce el aguante del Pokémon en 1.
     */
    public void disminuirAguante() {
        this.aguante--;
    }

    /**
     * Devuelve una representación en cadena del estado actual del Pokémon.
     *
     * @return Una cadena que representa al Pokémon.
     */
    @Override
    public String toString() {
        return "Nombre: " + this.nombre +
                "\nTipo: " + this.tipo +
                "\nNivel: " + this.nivel +
                "\nAguante: " + this.aguante;
    }
}

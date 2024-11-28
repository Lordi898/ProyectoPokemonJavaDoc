package objeto;

import java.util.Random;

public class Pokemon {
    private String nombre;
    private String tipo;
    private int nivel;
    private int aguante;

    // Constructor: Inicializa con nombre y tipo, nivel predeterminado es 1.
    public Pokemon(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = 1;
        this.aguante = calcularAguante(this.nivel);
    }

    // Constructor: Inicializa con nombre, tipo y nivel específico.
    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.aguante = calcularAguante(nivel);
    }

    // Calcula el aguante del Pokémon basado en su nivel.
    private int calcularAguante(int nivel) {
        return nivel * 3;
    }

    // Métodos Getters y Setters
    public int getAguante() {
        return aguante;
    }

    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.aguante = calcularAguante(nivel);
    }

    // Usa la habilidad especial del Pokémon, modificando aguante o nivel.
    public void usarHabilidadEspecial() {
        switch (this.tipo) {
            case "agua" -> {
                this.aguante += 2; // Recupera 2 puntos de aguante.
                System.out.println(this.nombre + " recupera aguante gracias a su habilidad especial.");
            }
            case "fuego" -> {
                this.nivel++; // Sube un nivel temporalmente para esta ronda.
                System.out.println(this.nombre + " aumenta su nivel gracias a su habilidad especial.");
            }
            case "tierra" -> {
                this.aguante += 1; // Incrementa el aguante por resistencia al terreno.
                System.out.println(this.nombre + " gana aguante por su resistencia al terreno.");
            }
            case "eléctrico" -> {
                this.aguante -= 1; // Reduce su aguante para atacar con más poder.
                System.out.println(this.nombre + " sacrifica aguante para potenciar su ataque.");
            }
            default -> System.out.println(this.nombre + " no tiene una habilidad especial activa.");
        }
    }

    // Calcula el poder del Pokémon contra un contrincante, considerando ventajas.
    public int calcularPoder(Pokemon contrincante) {
        Random random = new Random();
        int poderBase = switch (this.nivel) {
            case 1 -> random.nextInt(8) + 3;  // Rango [3-10]
            case 2 -> random.nextInt(15) + 6; // Rango [6-20]
            case 3 -> random.nextInt(22) + 9; // Rango [9-30]
            default -> random.nextInt(29) + 12; // Rango [12-40]
        };

        // Ventajas de tipo
        int modificador = switch (this.tipo) {
            case "agua" -> contrincante.tipo.equals("fuego") ? 2 * this.nivel :
                    contrincante.tipo.equals("tierra") ? -2 * contrincante.nivel : 0;
            case "fuego" -> contrincante.tipo.equals("tierra") ? 2 * this.nivel :
                    contrincante.tipo.equals("agua") ? -2 * contrincante.nivel : 0;
            case "tierra" -> contrincante.tipo.equals("agua") ? 2 * this.nivel :
                    contrincante.tipo.equals("fuego") ? -2 * contrincante.nivel : 0;
            case "eléctrico" -> contrincante.tipo.equals("agua") ? 3 * this.nivel : 0; // Fuerte contra agua.
            default -> 0;
        };

        // Efecto secundario: Pokémon eléctrico reduce el aguante del rival un 10%.
        if (this.tipo.equals("eléctrico")) {
            contrincante.setAguante((int) (contrincante.getAguante() * 0.9));
            System.out.println(contrincante.getNombre() + " pierde un 10% de su aguante por la descarga eléctrica.");
        }

        return Math.max(poderBase + modificador, 0); // Asegura que el poder no sea negativo.
    }

    // Incrementa el nivel del Pokémon y recalcula sus estadísticas.
    public void subirNivel() {
        this.nivel++;
        this.actualizarStats();
    }

    // Recalcula las estadísticas del Pokémon basado en su nivel.
    public void actualizarStats() {
        this.aguante = (int) Math.round(nivel * 2.5);
    }

    // Reduce el aguante del Pokémon en 1.
    public void disminuirAguante() {
        this.aguante--;
    }

    // Representación en cadena del estado del Pokémon.
    public String toString() {
        return "Nombre: " + this.nombre +
                "\nTipo: " + this.tipo +
                "\nNivel: " + this.nivel +
                "\nAguante: " + this.aguante;
    }
}

package pwr.sim.tile;

/**
 * Komórka siatki świata symulacji. Zawiera rośliny oraz padlinę, mogą na niej przebywać zwierzęta.
 */
public abstract class Tile {
    /**
     * Rysuje komórkę na aktualnej pozycji kursora.
     */
    public abstract void draw();

    /**
     * Zwraca kolor komórki w formacie 8-bitowego kodu koloru ANSI. Różne typy komórek mają różne kolory.
     * @return kolor komórki w formacie 8-bitowego kodu koloru ANSI
     */
    public abstract int getColor();

    public int getFlora() {
        return this.flora;
    }

    public void changeFlora(int shift) {
        this.flora += shift;
    }

    public int getFlesh() {
        return this.flesh;
    }

    public void changeFlesh(int shift) {
        this.flesh += shift;
    }

    private int flora = 20;
    private int flesh = 0;
}

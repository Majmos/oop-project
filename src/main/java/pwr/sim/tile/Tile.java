package pwr.sim.tile;

public abstract class Tile {
    public void draw() {
        System.out.print('-');
    }

    public abstract int getColor();

    public int getFlora() {
        return this.flora;
    }

    public void changeFlora(int shift) {
        this.flora += shift;
    }

    private int flora;
}

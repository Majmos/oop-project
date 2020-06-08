package pwr.sim;

public class Vector2D {
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int length_squared() {
        return x * x + y * y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public final int x;
    public final int y;
}

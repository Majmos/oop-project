package pwr.sim;

/**
 * Dwuwymiarowy wektor.
 */
public class Vector2D {
    /**
     * Tworzy nowy wektor.
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Kwadrat długości wektora.
     * @return kwadrat długości wektora
     */
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

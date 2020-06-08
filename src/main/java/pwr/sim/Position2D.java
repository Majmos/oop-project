package pwr.sim;

import java.util.Objects;

/**
 * Reprezentuje pozycję w świecie. Gwarantuje że pozycja zawsze jest w granicach świata gry.
 */
public class Position2D {
    /**
     * Tworzy nową pozycję w świecie.
     * @param x
     * @param y
     * @param world
     */
    public Position2D(int x, int y, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }

    /**
     * Kopiuje pozycję.
     * @param other
     */
    public Position2D(Position2D other) {
        this.x = other.x;
        this.y = other.y;
        this.world = other.world;
    }

    /**
     * Zwraca nową pozycję zmienioną o offset zdefiniowany przez parametry.
     * @param x przesunięcie pozycji w osi x
     * @param y przesunięcie pozycji w osi y
     */
    public void move(int x, int y) {
        int newx = this.x + x;
        int newy = this.y + y;

        if(world.getTile(newx, newy) != null) {
            this.x = newx;
            this.y = newy;
        }
    }

    /**
     * Zmienia pozycję w obrębie tego samego świata.
     * @param x nowa pozycja w osi x
     * @param y nowa pozycja w osi y
     */
    public void setPosition(int x, int y) {
        if(world.getTile(x, y) == null) {
            return;
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Zwraca kwadrat dystansu do pozycji przekazanej w argumencie.
     * @param other pozycja do której chcemy uzyskać dystans
     * @return kwadrat dystansu do pozycji
     */
    public int distanceSquared(Position2D other) {
        Vector2D delta = delta(other);
        return delta.x * delta.x + delta.y * delta.y;
    }

    /**
     * Zwraca wektor różnicy między pozycjami. Np. delta między pozycjami {1, 2}, {3, 1} to (2, -1)
     * @param other pozycja do której różnicę chcemy uzyskać
     * @return wektor różnicy do określonej pozycji
     */
    public Vector2D delta(Position2D other) {
        int deltaX = other.getX() - x;
        int deltaY = other.getY() - y;

        return new Vector2D(deltaX, deltaY);
    }

    @Override
    public String toString() {
        return String.format("{x: %d, y: %d}", x, y);
    }

    // why doesnt it just recursively test fields for equality, why the fuck do i have to do this
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Position2D)) {
            return false;
        }

        Position2D o = (Position2D) obj;
        return x == o.x && y == o.y && world == o.world;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, world);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private final World world;
    private int x;
    private int y;
}

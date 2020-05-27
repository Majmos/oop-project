package pwr.sim;

public class Position2D implements Cloneable {
    public Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

   public Position2D(Position2D other) {
        this.x = other.x;
        this.y = other.y;
   }

    public int x;
    public int y;
}

package pwr.sim;

public class Node {
    public Node(Position2D pos, Node prev) {
        position = pos;
        this.prev = prev;
        this.finalCost = 999999;
        this.gCost = 999999;
    }

    public Position2D getPosition() {
        return position;
    }

    public Node prev;
    public final Position2D position;
    public int gCost;
    public int finalCost;
}

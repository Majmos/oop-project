package pwr.sim;

public class App {
    public static void main(String[] args) {
        World world = new World(50, 10);
        world.populate(10);
        world.draw();
        world.update();
    }
}

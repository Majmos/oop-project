package pwr.sim;

import java.io.IOException;
import pwr.sim.renderer.Renderer;

public class App {
    public static void main(String[] args) throws IOException {
        Renderer.enableAlternateScreenBuffer();

        World world;
        try {
            world = new World("assets/map50x50.txt");
            world.populate(10);
            world.draw();
            world.update();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.in.read();
        Renderer.disableAlternateScreenBuffer();
    }
}

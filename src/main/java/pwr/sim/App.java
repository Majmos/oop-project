package pwr.sim;

import java.io.IOException;
import pwr.sim.renderer.Renderer;

public class App {
    public static void main(String[] args) {
        Renderer.enableAlternateScreenBuffer();

        World world;
        try {
            world = new World("assets/map50x50.txt");
            world.populate(10);

            while(true) {
                world.draw();
                world.update();
                System.in.read();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        Renderer.disableAlternateScreenBuffer();
    }
}

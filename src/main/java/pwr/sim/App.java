package pwr.sim;

import org.ietf.jgss.GSSName;
import pwr.sim.animal.AnimalType;
import pwr.sim.renderer.Renderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(Renderer::disableAlternateScreenBuffer));

        Scanner scanner = new Scanner(System.in);

        Renderer.enableAlternateScreenBuffer();
        World world;

        try {
            world = World.loadFromFile("assets/map50x50.txt");
            world.populate(3, AnimalType.WOLF);
            world.populate(3, AnimalType.LION);
            world.populate(3, AnimalType.CROCODILE);
            world.populate(6, AnimalType.ANTELOPE);
            world.populate(6, AnimalType.HIPPO);

            String input;
            do {
                world.draw();
                input = scanner.nextLine();
                world.update();
            } while(!input.equalsIgnoreCase("q"));

        } catch (Exception e) {
            Renderer.disableAlternateScreenBuffer();
            e.printStackTrace();
        }
    }
}

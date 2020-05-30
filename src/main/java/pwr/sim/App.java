package pwr.sim;

import pwr.sim.renderer.Renderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(Renderer::disableAlternateScreenBuffer));

        Scanner scanner = new Scanner(System.in);

        Renderer.enableAlternateScreenBuffer();
        World world;

        try {
            world = new World("assets/map50x50.txt");
            world.populate(10);

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

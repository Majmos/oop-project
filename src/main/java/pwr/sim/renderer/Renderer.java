package pwr.sim.renderer;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;

public class Renderer {
    static final int cellWidth = 3;

    public static void drawUi(World world) {
        // draw animal list UI
        Renderer.setCursorPosition(52 * cellWidth, 1);
        System.out.print("Animals:");

        int i = 1;
        for (Animal animal: world.getAnimals()) {
            Renderer.setCursorPosition(52 * cellWidth, i + 1);
            String animalInfo = animal.getStringInfo();
            System.out.print(i + ": ");
            System.out.print(animalInfo.substring(0, Math.min(animalInfo.length(), 100)));
            i++;
        }
    }

    public static void setCursorPosition(int x, int y) {
        System.out.print(String.format("\u001B[%d;%dH", y, x));
    }

    public static void setCursorToCell(Position2D position) {
        setCursorPosition(position.getX() * cellWidth + 2, position.getY() + 1);
    }

    public static void drawColouredText(String text, int fgColour, int bgColour) {
        System.out.print(String.format("\u001B[38;5;%dm\u001B[48;5;%dm%s\u001B[0m", fgColour, bgColour, text));
    }

    public static void drawColouredChar(char c, int fgColour, int bgColour) {
        System.out.print(String.format("\u001B[38;5;%dm\u001B[48;5;%dm%c\u001B[0m", fgColour, bgColour, c));
    }

    public static void enableAlternateScreenBuffer() {
        System.out.print("\u001B[?1049h");
    }

    public static void disableAlternateScreenBuffer() {
        System.out.print("\u001B[?1049l");
    }

    public static void clearScreen() {
        System.out.print("\u001B[2J");
    }
}

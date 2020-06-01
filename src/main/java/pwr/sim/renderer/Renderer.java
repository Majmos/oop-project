package pwr.sim.renderer;

import pwr.sim.Position2D;

public class Renderer {
    static final int cellWidth = 3;

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

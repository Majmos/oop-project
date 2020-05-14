package pwr.sim.renderer;

public class Renderer {
    public void setCursorPosition(int y, int x) {
        System.out.print(String.format("\u001B[%d;%dH", y, x));
    }

    public void drawColouredText(String text, int fgColour, int bgColour) {
        System.out.print(String.format("\u001B[38;5;%dm\u001B[48;5;%dm%s\u001B[0m", fgColour, bgColour, text));
    }

    //Positive value of y/x will move cursor down/right and negative value will move cursor up/left
    public void moveCursorBy(int y, int x) {
        System.out.print(String.format("\u001B[%dB\u001B[%dC", y, x));
    }
}

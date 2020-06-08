package pwr.sim.renderer;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;

/**
 * Klasa zajmująca się rysowaniem symulacji na ekranie terminala. Posiada metody do poruszania kursorem i wypisywania
 * tekstu w różnych kolorach.
 */
public class Renderer {
    /**
     * Ilość znaków jaki zajmuje komórka w kierunku poziomym. W zależności od używanego fontu należy dopasować by
     * komórka miała możliwie najbliżej kształt kwadratu.
     */
    static final int cellWidth = 3;

    /**
     * Rysuje "UI" które składa się z listy aktualnie żyjących zwierząt i ich statusów.
     * @param world świat dla którego chcemy narysować UI
     */
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

    /**
     * Ustawia pozycję kursora w terminalu na kolumnę `x` i wiersz `y`.
     * Komórki i wiersze terminala adresowane są od 1.
     *
     * Jeżeli `x` > szerokości terminala lub `y` > wysokości terminala, kursor ustawiany jest odpowiednio na ostatnią
     * kolumnę/wiersz.
     * @param x kolumna terminala
     * @param y wiersz terminala
     */
    public static void setCursorPosition(int x, int y) {
        System.out.print(String.format("\u001B[%d;%dH", y, x));
    }

    /**
     * Ustawia kursor terminala na pozycję którą zajmuje wybrana komórka świata.
     * @param position pozycja w świecie na którą chcemy ustawić kursor.
     */
    public static void setCursorToCell(Position2D position) {
        setCursorPosition(position.getX() * cellWidth + 2, position.getY() + 1);
    }

    /**
     * Wypisuje tekst z określonym kolorem czcionki i tła.
     * @param text tekst który chcemy wypisać
     * @param fgColour kolor czcionki
     * @param bgColour kolor tła
     */
    public static void drawColouredText(String text, int fgColour, int bgColour) {
        System.out.print(String.format("\u001B[38;5;%dm\u001B[48;5;%dm%s\u001B[0m", fgColour, bgColour, text));
    }

    /**
     * Wypisuje znak z określonym kolorem czcionki i tła.
     * @param c znak który chcemy wypisać
     * @param fgColour kolor czcionki
     * @param bgColour kolor tła
     */
    public static void drawColouredChar(char c, int fgColour, int bgColour) {
        System.out.print(String.format("\u001B[38;5;%dm\u001B[48;5;%dm%c\u001B[0m", fgColour, bgColour, c));
    }

    /**
     * Uruchamia alternatywny bufor terminala.
     */
    public static void enableAlternateScreenBuffer() {
        System.out.print("\u001B[?1049h");
    }

    /**
     * Wyłącza alternatywny bufor terminala.
     */
    public static void disableAlternateScreenBuffer() {
        System.out.print("\u001B[?1049l");
    }

    /**
     * Czyści ekran
     */
    public static void clearScreen() {
        System.out.print("\u001B[2J");
    }
}

package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviour;

public abstract class Animal {
    private Animal() {}
    public Animal(AiBehaviour aiBehaviour, int y, int x) {
        this.aiBehaviour = aiBehaviour;
        this.y = y;
        this.x = x;
    }

    public void update() {
        aiBehaviour.update();
    }

    // Chcemy zadeklarować w klasie rodzic że każda klasa pochodna będzie posiadać uchwyt do obiektu pochodzacego od
    // klasy `AiBehaviour` (specyficzny dla każdego obiektu). Jednak w tej klasie nie wiemy jaki konkretny typ
    // obiektu `aiBehaviour` będzie zawierała klasa pochodna, toteż domyślny konstruktor klasy `Animal` nie może go
    // zainicjalizować.
    // Aby uniemożliwić skonstruowanie niezainicjalizowanego w pełni obiektu, domyślny konstruktor ustawiony został jako
    // prywatny.
    // Czy można zrobić to lepiej?
    AiBehaviour aiBehaviour;

    public void draw(char c) {
        System.out.print(String.format("\u001B[%dB\u001B[%dC\u001B[37m%c\u001B[%dA\u001B[%dD\u001B[0m", y, 3*x+1, c, y, 3*x+1));
    }

    private int health;
    private int x;
    private int y;
}

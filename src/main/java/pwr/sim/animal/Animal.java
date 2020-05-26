package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviour;

public abstract class Animal {
    private Animal() {}
    public Animal(AiBehaviour aiBehaviour, int y, int x) {
        this.aiBehaviour = aiBehaviour;
        this.position = new Position2D(x, y);
        this.aiBehaviour.position = position;
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
        System.out.print(String.format("\u001B[%dB\u001B[%dC\u001B[37m%c\u001B[%dA\u001B[%dD\u001B[0m", position.y, 3*position.x+1, c, position.y, 3*position.x+1));
    }

    private int health;
    private Position2D position;
}

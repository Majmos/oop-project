package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviour;
import pwr.sim.renderer.Renderer;

public abstract class Animal {
    private Animal() {}
    public Animal(AiBehaviour aiBehaviour, int x, int y) {
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

    public void draw() {
        Renderer.setCursorToCell(position.x, position.y);
        char c = getAnimalChar();
        Renderer.drawColouredText(Character.toString(c), 231, 16);
    }

    abstract char getAnimalChar();

    private int health;
    private Position2D position;
}

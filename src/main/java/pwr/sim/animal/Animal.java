package pwr.sim.animal;

import pwr.sim.animal.ai.IAiBehaviour;

public abstract class Animal {
    private Animal() {}
    public Animal(IAiBehaviour aiBehaviour, int y, int x) {
        this.aiBehaviour = aiBehaviour;
        this.y = y;
        this.x = x;
    }

    public void update() {
        aiBehaviour.update();
    }

    // Chcemy zadeklarować w klasie rodzic że każda klasa pochodna będzie posiadać uchwyt do obiektu implementującego
    // interfejs `IAiBehaviour` (specyficzny dla każdego obiektu). Jednak w tej klasie nie wiemy jaki konkretny typ
    // obiektu `aiBehaviour` będzie zawierała klasa pochodna, toteż domyślny konstruktor klasy `Animal` nie może go
    // zainicjalizować.
    // Aby uniemożliwić skonstruowanie niezainicjalizowanego w pełni obiektu, domyślny konstruktor ustawiony został jako
    // prywatny.
    // Czy można zrobić to lepiej?
    IAiBehaviour aiBehaviour;

    public void draw(char c) {
        System.out.print(String.format("\u001B[%d;%dH\u001B[37m%c\u001B[0m", y, 3*x+1, c));
    }

    private int health;
    private int x;
    private int y;
}

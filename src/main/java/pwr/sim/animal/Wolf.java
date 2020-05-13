package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourWolf;

public class Wolf extends Animal {
    public Wolf(int y, int x) {
        super(new AiBehaviourWolf(), y, x);
    }
    public void draw() {
        System.out.print("\u00B1[y;xH" + "\u00B1[37mW");
    }
}

package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourAntelope;

public class Antelope extends Animal {
    public Antelope(int y, int x) {
        super(new AiBehaviourAntelope(), y, x);
    }
    public void draw() {

    }
}

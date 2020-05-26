package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourAntelope;

public class Antelope extends Animal {
    public Antelope(int x, int y) {
        super(new AiBehaviourAntelope(), x, y);
    }
    public void draw() {

    }
}

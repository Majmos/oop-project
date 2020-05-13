package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourLion;

public class Lion extends Animal {
    public Lion(int y, int x) {
        super(new AiBehaviourLion(), y, x);
    }
}

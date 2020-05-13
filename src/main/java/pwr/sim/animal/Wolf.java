package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourWolf;

public class Wolf extends Animal {
    public Wolf(int x, int y) {
        super(new AiBehaviourWolf(), x, y);
    }

}

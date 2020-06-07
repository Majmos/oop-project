package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateHunt;

public class AiBehaviourPredator extends AiBehaviour {
    public AiBehaviourPredator(Animal animal) {
        super(animal);
        currentState = new AiStateHunt(this.animal);
    }
}

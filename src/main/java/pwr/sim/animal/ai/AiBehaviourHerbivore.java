package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.AiStateRoam;
import pwr.sim.animal.ai.state.IAiState;

import java.util.Stack;

public class AiBehaviourHerbivore extends AiBehaviour {
    public AiBehaviourHerbivore(Animal animal) {
        super(animal);
        currentState.pop();
        currentState.push(new AiStateRoam(this.animal));
    }
}

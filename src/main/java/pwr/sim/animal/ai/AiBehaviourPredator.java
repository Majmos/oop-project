package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateHunt;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.AiStateRoamPredator;
import pwr.sim.animal.ai.state.IAiState;

import java.util.Stack;

public class AiBehaviourPredator extends AiBehaviour {
    public AiBehaviourPredator(Animal animal) {
        super(animal);
        currentState.pop();
        currentState.push(new AiStateRoamPredator(this.animal));
    }
}

package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateHunt;
import pwr.sim.animal.ai.state.IAiState;

import java.util.Stack;

public class AiBehaviourPredator extends AiBehaviour {
    public AiBehaviourPredator(Animal animal) {
        this.animal = animal;
        currentState = new Stack<>();
        currentState.push(new AiStateHunt(this.animal));
    }

    @Override
    public void update() {
        super.update();
    }

    protected final Stack<IAiState> currentState;
    public Animal animal;
}

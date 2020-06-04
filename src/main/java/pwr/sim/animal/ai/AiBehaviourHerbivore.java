package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.IAiState;

import java.util.Stack;

public class AiBehaviourHerbivore extends AiBehaviour {
    public AiBehaviourHerbivore(Animal animal) {
        this.animal = animal;
        currentState = new Stack<>();
        currentState.push(new AiStateLookForFood(this.animal));
    }

    @Override
    public void update() {
        super.update();
    }

    protected final Stack<IAiState> currentState;
    public Animal animal;
}

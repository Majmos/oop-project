package pwr.sim.animal.ai;

import pwr.sim.animal.Animal;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.IAiState;

public class AiBehaviour {
    public AiBehaviour(Animal animal) {
        this.animal = animal;
        currentState = new AiStateLookForFood(this.animal);
    }

    public void update() {
    }

    public String debugInfo() {
        try {
            return currentState.debugInfo();
        } catch(Exception e) {
            return "Exception thrown";
        }
    }

    protected IAiState currentState;
    public Animal animal;
}

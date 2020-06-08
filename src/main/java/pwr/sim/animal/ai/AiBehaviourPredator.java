package pwr.sim.animal.ai;

import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.Crocodile;
import pwr.sim.animal.Lion;
import pwr.sim.animal.Wolf;
import pwr.sim.animal.ai.state.*;

public class AiBehaviourPredator extends AiBehaviour {
    public AiBehaviourPredator(Animal animal) {
        super(animal);
        currentState = new AiStateHunt(this.animal);
    }

    @Override
    public void update() {
        if(animal.wantToMate) {
            currentState = new AiStateCopulate(animal);
        } else if(animal.isHungry) {
            currentState = new AiStateHunt(animal);
        } else if(animal.isTired) {
            currentState = new AiStateSleep(animal);
        }
        IAiState newState = currentState.update();
        if (newState != null) {
            currentState = newState;
        }
    }
}

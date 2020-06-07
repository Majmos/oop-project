package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.*;

public class AiStateEvade implements IAiState {
    public AiStateEvade(Animal animal, Animal predator) {
        this.animal = animal;
        this.predator = predator;
    }

    @Override
    public IAiState update() {
        animal.evade(predator.getPosition());
        int currentDistance = animal.getPosition().distanceSquared(predator.getPosition());
        if(currentDistance > 35) {
            if (animal.wantToMate) {
                return new AiStateCopulate(animal);
            } else if (animal.isHungry) {
                return new AiStateLookForFood(animal);
            }
            return new AiStateSleep(animal);
        }
        return null;
    }

    private final Animal animal;
    private final Animal predator;
}

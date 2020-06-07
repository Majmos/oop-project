package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;

public class AiStateSleepPredator implements IAiState {
    public AiStateSleepPredator(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        animal.changeEnergy(20);
        if(animal.wantToMate) {
            return new AiStateCopulatePredator(animal);
        } else if(animal.isHungry) {
            return new AiStateHunt(animal);
        }
        if(animal.getEnergy() >= 100) {
            return new AiStateHunt(animal);
        }
        return null;
    }

    private final Animal animal;
}

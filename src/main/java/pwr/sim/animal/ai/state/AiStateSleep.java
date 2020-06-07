package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;

public class AiStateSleep implements IAiState {
    public AiStateSleep(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        animal.changeEnergy(20);
        if(animal.wantToMate) {
            return new AiStateCopulate(animal);
        } else if(animal.isHungry) {
            return new AiStateLookForFood(animal);
        }
        if(animal.getEnergy() >= 100) {
            return new AiStateLookForFood(animal);
        }
        return null;
    }

    private final Animal animal;
}

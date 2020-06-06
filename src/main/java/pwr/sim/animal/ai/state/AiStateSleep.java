package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;

public class AiStateSleep implements IAiState {
    public AiStateSleep(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        animal.changeEnergy(20);
        if(animal.getEnergy() >= 99) {
            return new AiStatePop();
        }
        if(animal.wantToMate) {
            animal.isTired = false;
            animal.isHungry = false;
            return new AiStateCopulate(animal);
        } else if(animal.isHungry) {
            animal.isHungry = false;
            return new AiStateLookForFood(animal);
        }
        return null;
    }

    private final Animal animal;
}

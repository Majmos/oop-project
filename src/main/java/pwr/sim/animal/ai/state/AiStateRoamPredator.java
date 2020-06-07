package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;

public class AiStateRoamPredator implements IAiState {
    public AiStateRoamPredator(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        if(phase == 0) {
            animal.move(1, 0);
        } else if(phase == 1) {
            animal.move(0, 1);
        } else if(phase == 2) {
            animal.move(-1, 0);
        } else if(phase == 3) {
            animal.move(0, -1);
        }
        phase = (phase + 1)%4;
        numTicks++;
        if(animal.wantToMate) {
            return new AiStateCopulatePredator(animal);
        } else if(animal.isHungry) {
            return new AiStateHunt(animal);
        } else if(animal.isTired) {
            return new AiStateSleepPredator(animal);
        }

        if(numTicks >= 6) {
            numTicks = 0;
            return new AiStateHunt(animal);
        }

        return null;
    }

    private int phase = 0;
    private int numTicks = 0;
    private final Animal animal;
}
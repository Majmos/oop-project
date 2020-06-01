package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;

public class AiStateRoam implements IAiState {
    public AiStateRoam(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        IAiState other = checkHungerAndEnergy();
        if(other != null) {
            return other;
        }
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

        if(numTicks >= 6) {
            numTicks = 0;
            return new AiStateCopulate(animal);
        }

        return null;
    }

    private IAiState checkHungerAndEnergy() {
        if(animal.getHunger() < 35 && animal.getHunger() < animal.getEnergy()) {
            return new AiStateLookForFood(animal);
        }
        if(animal.getEnergy() < 35) {
            return new AiStateSleep(animal);
        }
        return null;
    }

    private int phase = 0;
    private int numTicks = 0;
    private final Animal animal;
}

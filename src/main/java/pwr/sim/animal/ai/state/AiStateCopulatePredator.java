package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.AnimalFactory;

public class AiStateCopulatePredator implements IAiState {
    public AiStateCopulatePredator(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        World world = animal.getWorld();
        if(mate == null) {
            int minimum = 100000;
            for (Animal mate: world.getAnimals()) {
                if (mate.getClass().equals(animal.getClass()) && mate != animal) {
                    int currentDistance = animal.getPosition().distanceSquared(mate.getPosition());
                    if (currentDistance < minimum) {
                        minimum = currentDistance;
                        this.mate = mate;
                    }
                }
            }
        }
        animal.approach(mate.getPosition());
        if(animal.isHungry) {
            return new AiStateHunt(animal);
        } else if(animal.isTired) {
            return new AiStateSleepPredator(animal);
        }
        if(animal.getPosition().distanceSquared(mate.getPosition()) <= 2) {
            mate = null;
            world.toSpawn(animal, animal.getPosition());
            return new AiStatePop();
        }
        return null;
    }

    private final Animal animal;
    private Animal mate;
}

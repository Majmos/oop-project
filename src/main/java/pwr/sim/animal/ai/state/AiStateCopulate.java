package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.AnimalFactory;

public class AiStateCopulate implements IAiState {
    public AiStateCopulate(Animal animal) {
        this.world = animal.getWorld();
        this.animal = animal;
    }

    @Override
    public IAiState update() {
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
            animal.isHungry = false;
            return new AiStateLookForFood(animal);
        } else if(animal.isTired) {
            animal.isTired = false;
            return new AiStateSleep(animal);
        }
        if(animal.getPosition().distanceSquared(mate.getPosition()) <= 2) {
            mate = null;
            world.toSpawn(animal, animal.getPosition());
            return new AiStatePop();
        }
        return null;
    }

    private final World world;
    private final Animal animal;
    private Animal mate;
}

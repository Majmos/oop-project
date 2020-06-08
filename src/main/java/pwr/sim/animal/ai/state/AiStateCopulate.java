package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.AnimalFactory;

/**
 * Stan kopulacji.
 */
public class AiStateCopulate implements IAiState {
    public AiStateCopulate(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda odpowiada za znaleźenie partnera tego samego gatunku oraz kierowanie się ku niemu.
     * Gdy zwierze dojdzie do partnera to utworzy się nowy osobnik tego samego gatunku i zwierze
     * zmieni stan.
     * @return IAiState Zwraca nowy stan lub null aby pozostać w obecnym stanie.
     */
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
        if(mate != null) {
            animal.approach(mate.getPosition());
            if (animal.getPosition().distanceSquared(mate.getPosition()) <= 2) {
                mate = null;
                world.toSpawn(animal, animal.getPosition());
                return new AiStateSleep(animal);
            }
        }
        return null;
    }
    
    private final Animal animal;
    private Animal mate;
}

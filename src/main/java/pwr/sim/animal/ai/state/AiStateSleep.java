package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.animal.Antelope;
import pwr.sim.animal.Hippo;

/**
 * Stan snu zwierzęcia.
 */
public class AiStateSleep implements IAiState {
    public AiStateSleep(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda odpowiada za zwiększenie energii zwierzęcia jak i odrodzenie punktów życia.
     * Gdy zwierze jest wypoczęte to zmienia stan.
     * @return IAiState Zwraca nowy stan lub null aby pozostać w obecnym stanie.
     */
    @Override
    public IAiState update() {
        animal.changeEnergy(20);
        if(animal.getHealth() < 100) {
            animal.changeHealth(10);
        }
        if(animal.getEnergy() >= 100) {
            if(animal instanceof Antelope || animal instanceof Hippo) {
                return new AiStateLookForFood(animal);
            } else {
                return new AiStateHunt(animal);
            }
        }
        return null;
    }

    private final Animal animal;
}

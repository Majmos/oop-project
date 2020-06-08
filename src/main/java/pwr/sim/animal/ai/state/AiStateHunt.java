package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.Antelope;
import pwr.sim.animal.Hippo;

import java.util.List;

/**
 * Stan w którym zwierze poluje.
 */
public class AiStateHunt implements IAiState {
    public AiStateHunt(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda jest odpowiedzialna za znaleźenie najbliższego roślinożercy oraz kierowanie się ku niemu.
     * Gdy zwierze dojdzie do ofiary to atakuje, jeśli zabije zwierze to przechodzi w stan jedzenia i gdy
     * jest najedzone to zmienia stan.
     * @return IAiState Zwraca nowy stan lub null aby pozostać w obecnym stanie.
     */
    @Override
    public IAiState update() {
        World world = animal.getWorld();
        if(prey == null) {
            int minimum = 100000;
            for (Animal prey : world.getAnimals()) {
                if ((prey instanceof Antelope || prey instanceof Hippo) && prey.getHealth() > 0) {
                    int currentDistance = animal.getPosition().distanceSquared(prey.getPosition());
                    if (currentDistance < minimum) {
                        minimum = currentDistance;
                        this.prey = prey;
                    }
                }
            }
        }
        if(prey != null) {
            animal.approach(prey.getPosition());
            if (animal.getHunger() >= 100) {
                return new AiStateSleep(animal);
            }
            if (animal.getPosition().distanceSquared(prey.getPosition()) <= 2) {
                prey.changeHealth(-animal.getStrength());
                if (prey.getHealth() <= 0) {
                    prey = null;
                    return new AiStateEatCorpse(animal);
                }
            }
        }
        return null;
    }

    private final Animal animal;
    private Animal prey = null;
}

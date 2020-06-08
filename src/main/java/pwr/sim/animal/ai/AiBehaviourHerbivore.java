package pwr.sim.animal.ai;

import pwr.sim.World;
import pwr.sim.animal.*;
import pwr.sim.animal.ai.state.*;
/**
 * Klasa odpowiedzialna za zmiane stanów zwierząt roślinożernych.
 */
public class AiBehaviourHerbivore extends AiBehaviour {
    public AiBehaviourHerbivore(Animal animal) {
        super(animal);
    }

    /**
     * Metoda zmienia stan zwierzęcia w zależności od aktualnych statystyk i otoczenia.
     */
    @Override
    public void update() {
        if(animal.wantToMate) {
            currentState = new AiStateCopulate(animal);
        } else if(animal.isHungry) {
            currentState = new AiStateLookForFood(animal);
        } else if(animal.isTired) {
            currentState = new AiStateSleep(animal);
        }
        World world = animal.getWorld();
        int minimum = 35;
        boolean isInDanger = false;
        for (Animal predator: world.getAnimals()) {
            if ((predator instanceof Wolf || predator instanceof Crocodile || predator instanceof Lion) && predator.getHealth() > 0) {
                int currentDistance = animal.getPosition().distanceSquared(predator.getPosition());
                if(currentDistance < minimum) {
                    minimum = currentDistance;
                    isInDanger = true;
                    this.predator = predator;
                }
            }
        }
        if(isInDanger) {
            currentState = new AiStateEvade(animal, predator);
        }
        IAiState newState = currentState.update();
        if (newState != null) {
            currentState = newState;
        }
    }

    private Animal predator;
}

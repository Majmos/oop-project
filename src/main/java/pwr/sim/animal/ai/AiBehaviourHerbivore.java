package pwr.sim.animal.ai;

import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.Crocodile;
import pwr.sim.animal.Lion;
import pwr.sim.animal.Wolf;
import pwr.sim.animal.ai.state.AiStateEvade;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.IAiState;

public class AiBehaviourHerbivore extends AiBehaviour {
    public AiBehaviourHerbivore(Animal animal) {
        super(animal);
    }

    @Override
    public void update() {
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

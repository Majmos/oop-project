package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;

import java.util.List;

public class AiStateCopulate implements IAiState {
    public AiStateCopulate(Animal animal) {
        this.position = animal.getPosition();
        World world = animal.getWorld();
        this.animal = animal;
        this.animals = world.getAnimals();
    }

    @Override
    public IAiState update() {
        IAiState other = checkHungerAndEnergy();
        if(other != null) {
            return other;
        }
        if(mate == null) {
            for (Animal mate: animals) {
                if (mate.getClass().equals(animal.getClass())) {
                    Position2D matePosition = mate.getPosition();
                    int distanceX = matePosition.getX() - position.getX();
                    int distanceY = matePosition.getY() - position.getY();
                    int currentDistance = Math.abs(distanceX) + Math.abs(distanceY);
                    if (currentDistance < minimum) {
                        minimum = currentDistance;
                        minX = distanceX;
                        minY = distanceY;
                        this.mate = mate;
                    }
                }
            }
        }
        if(minX < 0) {
            position.move(-1,0);
            minX++;
        } else if(minX > 0) {
            position.move(1,0);
            minX--;
        }
        if(minY < 0) {
            position.move(0,-1);
            minY++;
        } else if(minY > 0) {
            position.move(0,1);
            minY--;
        }
        if(minX == 0 && minY == 0) {
//            world.breed(this.animal);
            mate = null;
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

    private final List<Animal> animals;
    private final Animal animal;
    private final Position2D position;
    private int minimum = 100000;
    private int minX = 0;
    private int minY = 0;
    private Animal mate = null;
}

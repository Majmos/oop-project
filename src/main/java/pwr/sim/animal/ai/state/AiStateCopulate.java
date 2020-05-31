package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.Antelope;
import pwr.sim.animal.Hippo;

import java.util.List;

public class AiStateCopulate implements IAiState {
    public AiStateCopulate(Animal animal) {
        this.position = animal.getPosition();
        this.world = animal.getWorld();
        this.animal = animal;
        this.animals = world.getAnimals();
    }

    @Override
    public IAiState update() {
        if(mate == null) {
            for (Animal mate: animals) {
                if (mate.getClass() == animal.getClass()) {
                    matePosition = mate.getPosition();
                    distanceX = matePosition.getX() - position.getX();
                    distanceY = matePosition.getY() - position.getY();
                    if (Math.abs(distanceX) + Math.abs(distanceY) < minimum) {
                        minimum = Math.abs(distanceX) + Math.abs(distanceY);
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
            mate = null;
            //Spawn new animal
        }
        return null;
    }

    private List<Animal> animals;
    private Animal animal;
    private World world;
    private Position2D position;
    private Position2D matePosition;
    private int distanceX = 0;
    private int distanceY = 0;
    private int minimum = 100000;
    private int minX = 0;
    private int minY = 0;
    private Animal mate = null;
}

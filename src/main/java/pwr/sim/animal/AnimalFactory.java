package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;

public class AnimalFactory {
    public AnimalFactory(World world) {
        this.world = world;
    }

    public Animal createAnimal(AnimalType animalType, Position2D position) {
        Animal animal;
        switch (animalType) {
            case ANTELOPE:
                animal = new Antelope();
                break;
            case CROCODILE:
                animal = new Crocodile();
                break;
            case HIPPO:
                animal = new Hippo();
                break;
            case LION:
                animal = new Lion();
                break;
            case WOLF:
                animal = new Wolf();
                break;
            default:
                return null;
        }
        animal.setWorld(this.world);
        animal.setPosition(position);
        animal.setAiBehaviour(new AiBehaviour(animal));
        return animal;
    }

    private final World world;
}
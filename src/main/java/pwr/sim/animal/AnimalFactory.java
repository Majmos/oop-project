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
        try {
            animal.setPosition(position);
        } catch (Exception e) {
            return null;
        }
        animal.setAiBehaviour(new AiBehaviour(animal));

        return animal;
    }

    public Animal createAnimal(Animal animal, Position2D position) {
        try {
            Animal other = animal.getClass().getConstructor().newInstance();

            other.setWorld(this.world);
            try {
                other.setPosition(position);
            } catch (Exception e) {
                return null;
            }
            other.setAiBehaviour(new AiBehaviour(other));
            return other;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    private final World world;
}
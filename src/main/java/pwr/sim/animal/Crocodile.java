package pwr.sim.animal;

public class Crocodile extends Animal {
    @Override
    public char getAnimalChar() {
        return 'C';
    }

    @Override
    public void move(int x, int y) {
        // crocodiles can move over water, no need to check for tile type
        super.position.move(x, y);
    }
}

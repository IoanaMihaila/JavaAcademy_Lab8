package exersare.packageSpecii;

import exersare.packageAnimal.Animal;

public class Pisica extends Animal {
    public Pisica() {
        this.nrPicioare = 4;
        Animal a = new Pisica();
        //a.nrPicioare=4; atributele protected pot fi accesate dintr-o clasa derivata dintr-un alt pachet, doar pe instanta this
    }
}

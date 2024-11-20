package exersare.packageSpecii;

import exersare.packageAnimal.Animal;

public class Caine extends Animal {
    public Caine() {
        Animal a = new Animal();
        a.culoare = "maro";
        //a.viteza //e disponibil doar in acelasi pachet
    }
}

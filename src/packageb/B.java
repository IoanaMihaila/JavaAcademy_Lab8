package packageb;
import packagea.A;

public class B extends A{
    public B() {
        //protected: poate fi accesata de oriunde din acelasi pachet,
        // si dintr-un alt pachet, daca mostenim clasa, dar doar pe instanta "this"
        this.prot = 20;
        A a = new A();
        a.pub = 10;
//        a.prot = 20;
//        a.pack = 30;
//        a.priv = 40;
    }
}

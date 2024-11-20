package packagea;

public class A2 {
    A a = new A();
    //se poate de oriunde (este public)
    //a.pub = 10;
    //se poate, pentru ca este acelasi pachet. daca erau pachete diferte, doar in subclasa se putea
    //a.prot = 20;
    //se poate, pentru ca este acelasi pachet
    //a.pack = 30;
    //nu se poate, pentru ca este private
    //a.priv = 40;
}

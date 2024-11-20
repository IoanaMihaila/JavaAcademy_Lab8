
import java.util.ArrayList;
import java.util.List;

class A{

}
class B extends A{

}
public class MetodeGenerice {
    public static <T extends Integer> void afisare(List<T> lista){
        for(T element:lista){
            System.out.println(element);
        }
    }
    public static <T extends A> void afisare2(List<T>lista){
        for(T element:lista){
            System.out.println(element);
        }
    }
    public static void afisare3(List<A>lista){
        for(A element:lista){
            System.out.println(element);
        }
    }
    public static void afisare4(List<? extends A>lista){//orice mosteneste A
        for(A element: lista){
            System.out.println(element);
        }
    }
    public static void main(String[] args) {
        List<Integer>lista=List.of(1,2,3);
        afisare(lista);

        List<A>listaA=new ArrayList<>();
        A a=new A();
        B b=new B();
        listaA.add(a);
        listaA.add(b);

        List<B>listaB=new ArrayList<>();
        afisare2(listaA);
        afisare2(listaB);

        afisare3(listaA);
        //afisare3(listaB); nu merge pt ca List<B> nu mosteneste List<A> chiar daca B mostenteste pe A

        A[] array=new B[100];//la fel ca A jucator=new B();

        afisare4(listaA);
        afisare4(listaB);
    }
}

import java.util.ArrayList;
import java.util.List;

public class P1P2P3 {
    public static <T> int gaseste(List<T> lista,T element){
        for(T t:lista){
            if(t.equals(element)){
                return lista.indexOf(t);
            }
        }
        return 0;
    }
    public static <T> List<T> invers(List<T>lista){
        List inversa=new ArrayList<>();
        inversa=lista.reversed();
        return inversa;
    }
    public static <T extends Number> int suma (List<T>lista){
        int suma=0;
        for(T t:lista){
            suma+=t.intValue();
        }
        return suma;
    }
    public static void main(String[] args) {
        List<Integer>lista=List.of(1,2,3,4,5);
        System.out.println("Elementul 5 se gaseste la indexul: "+gaseste(lista,5));
        System.out.println("Lista inversata: "+invers(lista));
        System.out.println(lista);
        System.out.println("Suma elementelor din lista: "+suma(lista));

    }
}

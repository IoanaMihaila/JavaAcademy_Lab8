import java.util.List;
import java.util.Optional;
//INTERFETE FUNCTIONALE JOI
public class MainOptional {
    public static <T> int cautaElement(List<T> lista, T element) {
        return lista.indexOf(element);
    }

    //Optional clasa generica, ne ascunde rezultatul
    public static Optional<String> cauta(String a, String b) {
        if (a.equals(b))
            //returnam mereu un optional care nu are o valoare null in interior
            //return Optional.of(a);
            return Optional.ofNullable(a);//returnam un Optional care poate sau n sa fie null
        return Optional.empty();//returnam un Optional care nu are nici un element
    }

    public static Optional<String> cautaAna(List<String> lista) {
        for (String str : lista) {
            if (str.endsWith("ana")) {
                return Optional.of(str);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        /*String rezultat=cauta("ana","bana");
        if(rezultat!=null){
            System.out.println(rezultat.toUpperCase());
        }*/
        Optional<String> optRezultat = cauta("a", "b");
        //v1
        if (optRezultat.isPresent()) {
            System.out.println(optRezultat.get().toUpperCase());
        }
        //v2
        //String rezultat=cauta("a","b").get();
        //String rezultat=cauta("a","b").orElse(null);
        //String rezultat=cauta("a","b").orElseThrow();
        //String rezultat = cauta("a", "b").orElseThrow(() -> new RuntimeException());

        List<String> lista = List.of("Alexandra", "Ioana", "Maria");
        String rezultatAna = cautaAna(lista).orElseThrow();
        System.out.println(rezultatAna);
        String rezultatAna1=cautaAna(lista).orElse(null);
        System.out.println(rezultatAna1);

        Optional<String> rezultatAna2 = cautaAna(lista);
        if (rezultatAna2.isPresent()) {
            System.out.println(rezultatAna2.get());
        }
    }
}

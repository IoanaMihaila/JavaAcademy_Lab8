package Tema;
/*In problema urmatoare, vom modela conceptul de firme, angajati si Statul Roman. Stim ca Statul este o singura instanta ce va fi responsabila de stocarea firmelor prezente in cadrul tarii. Astfel, va retine un tablou de obiecte de tipul Firma.
Fiecare firma va avea un numar unic de inregistrare (e.g.: RO1234), un nume, o data a infintarii (LocalDateTime), o localitate, un venit si un tablou de angajati. Atunci cand se creeaza o firma, tabloul de angajati va fi gol, putand seta in cadrul
constructorului, doar numele si localitatea firmei, venitul initial fiind pe 0. Numarul unic de inregistrare va fi oferit de catre entitatea StatulRoman, printr-un apel la metoda getNextAvailableNumber(). Astfel, la crearea fiecarui obiect de tipul Firma, se obliga apelarea urmatoarealor metode
din cadrul clasei StatulRoman:
- getNextAvailableNumber() - prima valoarea alocata va fi RO1000, ulterioarele incrementandu-se cu 1
- addFirma() - pentru ca Statul sa aiba in vedere faptul ca o noua firma exista pe piata.
Vor exista, astfel, doua tipuri de firme: FirmaTva, FirmaNoTva. Toate clasele vor fi obligate sa implementeze metoda platesteTaxe().
Astfel, FirmaTva va plati 19% din venituri catre Stat + 5% taxa UE, iar FirmaNoTva va plati doar 1% taxa UE din venituri. Astfel, veniturile firmei vor scadea cu acea valoare, iar Statul va stoca acea suma, printr-un apel la metoda addTax(double valoare).

Clasa Angajat va avea urmatoarele proprietati:
- un nume
- o varsta
- un nivel de experienta (intre 1 si 10)

Clasa Firma va mai avea urmatoarele metode:
- addAngajat() - va adauga un angajat in cadrul tabloului -> apelata din cadrul constructorului Angajat.
- removeAngajat() - va elimina un angajat din tablou, dar aceasta metoda va putea fi apelata doar printr-un apel la metoda demisioneaza() din cadrul clasei Angajat (desigur, asta va insemna ca Angajatul va sti la ce firma lucreaza)
Doar pentru clasa FirmaTva, de fiecare data cand un angajat pleaca din firma, firma va plati 1% din venit catre Stat.

Clasa StatulRoman va mai oferi o metoda afisareForConditie (Conditie conditie), ce va parcurge tabloul de firme si va afisa acele firme pentru care se respecta conditie. Conditie va fi o interfata ce va avea o metoda isRespected (Firma firma).
Pot exista mai multe conditii, astfel incat sa se afiseze:
- firmele pentru care veniturile sunt mai mari decat 10000
- firmele ce au mai mult de 5 angajati
- firmele fara angajati
- firmele dintr-o anumita localitate
*/

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StatulRoman {
    private static StatulRoman instance;
    private static List<Firma> firme;
    private static int count;
    private static double totalTaxe;

    private StatulRoman() {
        firme = new ArrayList<>();
        totalTaxe = 0;
        count = 999;
    }

    public static StatulRoman getInstance() {
        if (instance == null) {
            instance = new StatulRoman();
        }
        return instance;
    }

    public static String getNextAvailableNumber() {
        count++;
        return "RO" + count;
    }

    public static void addFirma(Firma f) {
        firme.add(f);
    }

    public static void addTax(double valoare) {
        totalTaxe += valoare;
    }

    public static void afisareForConditie(Conditie conditie) {
        for (Firma f : firme) {
            if (conditie.isRespected(f).orElse(false)) {
                System.out.println(f);
            }
        }
    }

    public static double getTotalTaxe() {
        return totalTaxe;
    }
}

class Angajat {
    private String nume;
    private int varsta;
    private int nivelExperienta; //intre 1 si 10
    private Firma firma;

    public Angajat(String nume, int varsta, int nivelExperienta, Firma firma) {
        this.nume = nume;
        this.varsta = varsta;
        this.nivelExperienta = nivelExperienta;
        this.firma = firma;
    }

    public void demisioneaza() {
        if (firma != null) {
            firma.removeAngajat(this);
            firma = null;
        }
    }

    @Override
    public String toString() {
        return "Nume: " + nume + ", varsta=" + varsta + ", nivelExperienta=" + nivelExperienta + ", firma: " + firma.getNume() + "\n";
    }
}

abstract class Firma {
    private String ID;
    private String nume;
    private LocalDateTime dataInfiintare;
    private String localitate;
    private double venit;
    private List<Angajat> angajati;

    public Firma(String nume, String localitate) {
        this.ID = StatulRoman.getNextAvailableNumber();
        this.nume = nume;
        this.dataInfiintare = LocalDateTime.now();
        this.localitate = localitate;
        this.venit = 0;
        this.angajati = new ArrayList<>();
        StatulRoman.addFirma(this);
    }

    public abstract void platesteTaxe();

    public String getNume() {
        return nume;
    }

    public void setVenit(double venit) {
        this.venit = venit;
    }

    public double getVenit() {
        return venit;
    }

    public void addAngajat(Angajat a) {
        angajati.add(a);
    }

    public void removeAngajat(Angajat a) {
        angajati.remove(a);
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public String getLocalitate() {
        return localitate;
    }

    @Override
    public String toString() {
        return "ID firma: '" + ID + ", nume='" + nume + ", dataInfiintare=" + dataInfiintare + ", localitate='" + localitate + '\'' + ", venit=" + venit + ", numar angajati=" + angajati.size() + "\n";
    }
}

class FirmaTva extends Firma {
    public FirmaTva(String nume, String localitate) {
        super(nume, localitate);
    }

    @Override
    public void platesteTaxe() {
        double taxe = 0.19 * this.getVenit() + 0.05 * this.getVenit();
        this.setVenit(this.getVenit() - taxe);
        StatulRoman.addTax(taxe);
    }

    @Override
    public void removeAngajat(Angajat a) {
        super.removeAngajat(a);
        double taxaDemise = 0.01 * this.getVenit();
        this.setVenit(this.getVenit() - taxaDemise);
        StatulRoman.addTax(taxaDemise);
    }
}

class FirmaNoTva extends Firma {
    public FirmaNoTva(String nume, String localitate) {
        super(nume, localitate);
    }

    @Override
    public void platesteTaxe() {
        double taxe = 0.01 * this.getVenit();
        this.setVenit(this.getVenit() - taxe);
        StatulRoman.addTax(taxe);
    }
}

interface Conditie {
    Optional<Boolean> isRespected(Firma firma);
}

class VenitMaiMareDeZeceMii implements Conditie {
    @Override
    public Optional<Boolean> isRespected(Firma firma) {
        return Optional.of(firma.getVenit() > 10000);
    }
}

class MaiMultDeCinciAngajati implements Conditie {
    @Override
    public Optional<Boolean> isRespected(Firma firma) {
        return Optional.of(firma.getAngajati().size() > 5);
    }
}

class FirmeFaraAngajati implements Conditie {
    @Override
    public Optional<Boolean> isRespected(Firma firma) {
        return Optional.of(firma.getAngajati().isEmpty());
    }
}

class FirmeAnumitaLocatie implements Conditie {
    private String localitate;

    public FirmeAnumitaLocatie(String localitate) {
        this.localitate = localitate;
    }

    @Override
    public Optional<Boolean> isRespected(Firma firma) {
        return Optional.of(firma.getLocalitate().equalsIgnoreCase(localitate));
    }
}

public class Problema1 {
    public static void main(String[] args) {
        StatulRoman stat = StatulRoman.getInstance();

        Firma firmaTva = new FirmaTva("Firma TVA SRL", "Bucuresti");
        Firma firmaNoTva = new FirmaNoTva("Firma No TVA SRL", "Cluj");
        Firma firmaNoTva1 = new FirmaNoTva("Firma No TVA PFA", "Hunedoara");

        System.out.println("Firmele create:");
        System.out.println(firmaTva);
        System.out.println(firmaNoTva);
        System.out.println(firmaNoTva1);

        Angajat angajat1 = new Angajat("Ion Popescu", 30, 7, firmaTva);
        Angajat angajat2 = new Angajat("Maria Ionescu", 25, 5, firmaTva);
        Angajat angajat3 = new Angajat("Andrei Vasile", 40, 9, firmaNoTva);

        firmaTva.addAngajat(angajat1);
        firmaTva.addAngajat(angajat2);

        firmaNoTva.addAngajat(angajat3);

        System.out.println("\nLista de angajați după angajare:");
        System.out.println("Firma TVA: " + firmaTva.getAngajati());
        System.out.println("Firma No TVA: " + firmaNoTva.getAngajati());

        firmaTva.setVenit(20000);
        firmaNoTva.setVenit(8000);
        firmaNoTva1.setVenit(10000);

        firmaTva.platesteTaxe();
        firmaNoTva.platesteTaxe();
        firmaNoTva1.platesteTaxe();

        System.out.println("\nVenituri după plata taxelor:");
        System.out.println("Firma TVA: " + firmaTva.getVenit());
        System.out.println("Firma No TVA: " + firmaNoTva.getVenit());

        angajat1.demisioneaza();

        System.out.println("\nLista de angajați după demisie:");
        System.out.println("Firma TVA: " + firmaTva.getAngajati());

        Conditie conditieVenitMare = new VenitMaiMareDeZeceMii();
        Conditie conditieFaraAngajati = new FirmeFaraAngajati();
        Conditie conditieDinCluj = new FirmeAnumitaLocatie("Cluj");

        System.out.println("\nFirmele cu venit mai mare de 10.000:");
        StatulRoman.afisareForConditie(conditieVenitMare);

        System.out.println("\nFirmele fără angajați:");
        StatulRoman.afisareForConditie(conditieFaraAngajati);

        System.out.println("\nFirmele din Cluj:");
        StatulRoman.afisareForConditie(conditieDinCluj);

        System.out.println("\nTotal taxe colectate de Statul Roman: " + StatulRoman.getTotalTaxe());
    }
}

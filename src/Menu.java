/*24. Clasa Numar “NATURAL”. Creaţi soft pentru implementarea TAD număr natural  poate avea cel mult 1000 cifre.
        Operaţii: suma, înmulţire, împarţire, modulo, primalitate, radical, comparare, etc.
        Creaţi soft care verifică  toate operaţiile implenttate în TAD.*/
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    /*Se citeste un numar intreg
     * Date de intrare: s - un string cu rolul de a fi textul inainte de citire
     * Date de iesire: nr - un numar natural sub forma de string
     * Exceptii: In cazul in care datele citite nu reprezinta un numar intreg se va sarii la cazul de exceptie,
     * unde utilizatorului o sa-i fie dat un text explicativ
     * */
    public static String citNatural(String s){
        try {
            String nr = "";
            Scanner scs = new Scanner(System.in);
            System.out.print(s);
            nr = scs.nextLine();//Citirea numarului de la tastatura
            return nr; //Returnarea numarului
        }
        catch (Exception e){
            //Cazul de exceptie
            System.out.println("Datele introduse sunt gresite. Mai incearca");
            return citNatural(s);//Reincercare citirii unui numar, prin apelare recursiva
        }
    }

    /*Se citeste un numar intreg
     * Date de intrare: s - un string cu rolul de a fi textul inainte de citire
     * Date de iesire: i - un numar intreg
     * Exceptii: In cazul in care datele citite nu reprezinta un numar intreg se va sarii la cazul de exceptie,
     * unde utilizatorului o sa-i fie dat un text explicativ
     * */
    public static int citInt(String s){
        try {
            int i = 0;
            Scanner scs = new Scanner(System.in);
            System.out.print(s);
            i = scs.nextInt();//Citirea numarului de la tastatura
            return i; //Returnarea numarului
        }
        catch (Exception e){
            //Cazul de exceptie
            System.out.println("Datele introduse sunt gresite. Mai incearca");
            return citInt(s);//Reincercare citirii unui numar, prin apelare recursiva
        }
    }

    //Optiunile din meniu
    public static void menuOptions(){
        System.out.print("1. Afisare istoric");
        System.out.println("          2. Verifica daca e patrat perefect");
        System.out.print("3. Adunare");
        System.out.println("          4. Scadere");
        System.out.print("5. Inmultire");
        System.out.println("          6. Impartire");
        System.out.print("7. Modulo");
        System.out.println("          8. Primalitate");
        System.out.print("9. Radical");
        System.out.println("          10. Comparare");
        System.out.print("11. Putere");
        System.out.println("            12. CLEAR MEMORY");
        System.out.println("13.Citire numar ");
        System.out.println("14.Undo ");
        System.out.println("Any other number to EXIT!!");
    }

    public static void menu() throws IOException {
        int i = 1;
        Logic l = new Logic();
        l.fromIstoric();
        while ((i >= 1) && (i <= 14)) {
            try{
            switch (i) {
                case 1: {
                    //Citirea si recitirea(dupa caz) a numerelor naturale din fisier
                    System.out.println(l.getI());
                    break;
                }
                case 2: {
                    //Afisarea numerelor care indeplinesc conditia de patrat perfect
                    System.out.println(l.perfect());
                    break;
                }
                case 3: {
                    //Adunare
                    System.out.println(l.addunare(citNatural("Dati un numar natural: ")) + " - suma");
                    break;
                }
                case 4: {
                    //Scadere
                    System.out.println(l.scadere(citNatural("Dati un numar natural: ")) + " - diferenta");
                    break;
                }
                case 5: {
                    //Inmultire
                    System.out.println(l.inmultire(citNatural("Dati un numar natural: ")) + " - produsul");
                    break;
                }
                case 6: {
                    //Impartire
                    System.out.println(l.impartire(citNatural("Dati un numar natural: ")) + " - catul");
                    break;
                }
                case 7: {
                    //Modulo
                    System.out.println(l.rest(citNatural("Dati un numar natural: ")) + " - restul");
                    break;
                }
                case 8: {
                    //Primalitate
                    System.out.println(l.primalitate());
                    break;
                }
                case 9: {
                    //Radical
                    System.out.println("Radicalul este: " + l.radical());
                    break;
                }
                case 10: {
                    //Comparare
                    System.out.println(l.comparare(citNatural("Dati un numar natural: ")));
                    break;
                }
                case 11: {
                    //ridicare la putere
                    System.out.println(l.putere(citNatural("Dati puterea: ")) + " - numarul ridicat la putere");
                    break;
                }
                case 12: {
                    l.clear();
                    break;
                }
                case 13:{
                    l.setLast(citNatural("Dati un numar natural: "));
                    break;
                }
                case 14:{
                    l.undo();
                    break;
                }
            }}
            catch (Exception e){
                System.out.println(e);
            }
            menuOptions(); // printare optiuni meniu
            System.out.println("Numarul din memorie: " + l.getLast());
            i = citInt("Alegeti optiunea dorita... "); //citirea optiunii dorite
            System.out.println();
        }
        l.writeToFile();
    }

    public static void main(String []args)
    {
        try {
            menu();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}

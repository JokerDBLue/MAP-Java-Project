import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Natural {
    private int []nat; //retine numarul
    private int length; // retine lungimea numarului
    private int startPos; // retine pozitia de start a numarului in sir
    private int maxCap = 1000; //retine capacitatea maxima

    //Constructor
    public Natural(){
        this.nat = new int[maxCap];
        this.length = 1;
        this.startPos = 999;
    }

    //Constructor cu parametri
    public Natural(String n){
        this.nat = new int[maxCap];
        char sign = n.charAt(0); // Se verifica semnul numarului
        if(sign == '-') {
            this.length = 1;
            this.startPos = 999;
        }
        else if (n.length() > maxCap) // Se verifica ca lungimea numarului sa nu fie mai mare decat cea maxima
        {
            Arrays.fill(nat,9);
            this.length = 1000;
            this.startPos = 0;
        }
        else {
            this.length = n.length();
            this.startPos = maxCap - length;
            //Salvarea sirului in array-ul nat, care reprezint un numar natural
            for(int i = 0; i < n.length(); i++) {
                int aux = Character.getNumericValue(n.charAt(i));
                nat[startPos + i] = aux;
            }
        }
        while(this.nat[this.startPos] == 0 && this.length != 1) {
            this.startPos += 1;
            this.length -= 1;
        }
    }


    //Accesori
    public void setNat(String nat) {
        if(this.nat == null) {
            this.nat = new int[maxCap];
            this.length = 1;
            this.startPos = 999;
        }
        Arrays.fill(this.nat,0);
        this.length = 1;
        char sign = nat.charAt(0); // Se verifica semnul numarului
        if(sign == '-') {
            this.length = 1;
            this.startPos = 999;
        }
        else if (nat.length() > maxCap) // Se verifica ca lungimea numarului sa nu fie mai mare decat cea maxima
        {
            Arrays.fill(this.nat,9);
            this.length = 1000;
            this.startPos = 0;
        }
        else {
            this.length = nat.length();
            this.startPos = maxCap - length;
            for(int i = 0; i < nat.length(); i++) {
                int aux = Character.getNumericValue(nat.charAt(i));
                this.nat[startPos + i] = aux;
            }
        }
        while(this.nat[this.startPos] == 0 && this.length != 1) {
            this.startPos += 1;
            this.length -= 1;
        }
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getNat() { return nat; }

    public int getLength() { return length; }

    /**
     * Descriere: Adunarea a doua numere naturale, rezultatul fiind salvat in obiectul curent
     * Input: new_n - un obiect de tip natural
     * Output:
     * */
    public void add(Natural new_n){
        int sp = 0;
        if(new_n.length > this.length){
            sp = new_n.startPos;
            this.startPos = sp;
        }
        else {
            sp = this.startPos;
        }

        for(int i = 999; i >= sp; i--) {
            this.nat[i] += new_n.nat[i];
            if(nat[i] > 9){
                this.nat[i] = nat[i] % 10;
                this.nat[i - 1]++;
            }
        }
        //Se verfica daca cifra de pe pozitia 10
        if(nat[sp - 1] != 0) {
            this.startPos = sp - 1;
            this.length++;
        }
    }

    /***
     * Scaderea a doua numere naturale
     * @param new_n
     */
    public void sub(Natural new_n){
        if(this.isLower(new_n))
            throw (new ArithmeticException("Nu poti scadea un numar mai mare dintr-un numar mai mic"));
        int sp = new_n.startPos;
        for(int i = 999; i >= sp; i--) {
            if(this.nat[i] < new_n.nat[i]){
                this.nat[i] += 10;
                if(this.nat[i - 1] == 0){
                    int j = i - 1;
                    while(this.nat[j] == 0){
                        this.nat[j] = 9;
                        j--;
                    }
                    this.nat[j] -= 1;
                }
                else
                    this.nat[i - 1] -= 1;
            }
            this.nat[i] -= new_n.nat[i];
        }

        while(this.nat[this.startPos] == 0 && this.length != 1) {
            this.startPos += 1;
            this.length -= 1;
        }
    }

    /**
     * Descriere: Inmulteste doua numere de tip natural, rezultatul fiind memorat in obiectul curent
     * Input: new_n - un obiect de tip natural
     * Output:
     * */
    public void mul(Natural new_n){
        if(new_n.equals(new Natural("0")) || this.equals(new Natural("0")))
            this.setNat("0");
        else {
            Natural vn = new Natural(); //Un numar natural in care vom memora suma inmultirilor pe parcurs
            for (int i = 999; i >= this.startPos; i--) {
                Natural aux = new Natural(new_n.toString()); // Un auxiliar in care il mentinem pe new_n
                int cf = 0, j = 0; // cf = carry flag pentru inmultire
                for (j = 999; j >= aux.startPos; j--) {
                    aux.nat[j] = aux.nat[j] * this.nat[i] + cf;
                    cf = 0; // cf devine 0 dupa folosire
                    cf = aux.nat[j] / 10; // cf va primi valoarea cifrei zecimale
                    aux.nat[j] = aux.nat[j] % 10; // aux.nat va deveni cifra decimala
                }
                if (cf != 0) { //Verifica daca carry flag e diferit de 0
                    aux.nat[j] = cf;
                    aux.startPos = j;
                    aux.length++;
                }
                String s = aux.toString();
                //Formarea corecta a numarului natural obtinut dupa inmultirea cu cifra n[i]
                for (int k = this.maxCap - 1 - i; k > 0; k--) {
                    s += "0";
                }
                vn.add(new Natural(s));//Adunarea lui vn cu numarul reprezentat de s
            }
            this.startPos = vn.startPos;
            this.length = maxCap - vn.startPos;
            for (int i = vn.startPos; i < vn.maxCap; i++)
                this.nat[i] = vn.nat[i]; // Salvarea numarului natural din vn in n
        }
    }

    public void div(Natural new_n){
        if(new_n.equals(new Natural("0")))
            throw(new ArithmeticException("Nu poti imparti la 0"));
        else if(this.isLower(new_n)){
            System.out.println();
            this.setNat("0");
        }
        else
        if(new_n.equals(new Natural("1")) == false) {
            String s = "";
            //Natural nr = new Natural();
            Natural aux = new Natural(this.toString());
            Natural index = new Natural("0");
            String rez = "";
            for(int i = 0; i < new_n.length; i++)
                s += this.nat[this.startPos + i];
            if(new Natural(s).isLower(new_n))
                s = s + this.nat[this.startPos + new_n.length];
            Natural produs = new Natural(new_n.toString());
            produs.mul(index);
            while(index.isLower(new Natural("9")) && produs.isLower(new Natural(s))) {
                index.add(new Natural("1"));
                produs.setNat(new_n.toString());
                produs.mul(index);
            }
            if(produs.isLower(new Natural(s)) == false && produs.equals(new Natural(s)) == false)
                index.sub(new Natural("1"));
            produs.setNat(new_n.toString());
            produs.mul(index);
            rez += index.toString();
            int inceput = s.length();
            aux.setNat(s);
            aux.sub(produs);
            s = aux.toString();
            for(int i = inceput; i < this.length; i++){
                if(s.equals("0"))
                    s = "";
                s = s +  this.nat[this.startPos + i];
                if(new_n.isLower(new Natural(s)) || new_n.equals(new Natural(s))){
                    index.setNat("0");
                    produs.setNat(new_n.toString());
                    produs.mul(index);
                    while(index.isLower(new Natural("9")) && produs.isLower(new Natural(s))) {
                        index.add(new Natural("1"));
                        produs.setNat(new_n.toString());
                        produs.mul(index);
                    }
                    if(produs.isLower(new Natural(s)) == false && produs.equals(new Natural(s)) == false)
                        index.sub(new Natural("1"));
                    produs.setNat(new_n.toString());
                    produs.mul(index);
                    rez += index;
                    aux.setNat(s);
                    aux.sub(produs);
                    s = aux.toString();
                    if(s.equals("0"))
                        s = "";
                }
                else rez += "0";
            }
            this.setNat(rez);
        }
    }

    /**
     *
     * @param new_n
     * @return
     */
    public Natural mod(Natural new_n){
        Natural aux = new Natural(this.toString());
        aux.div(new_n);
        Natural rest = new Natural(aux.toString());
        rest.mul(new_n);
        aux.setNat(this.toString());
        aux.sub(rest);
        return aux;
    }

    public Natural sqrt(){
        if (this.equals(new Natural("0")) || this.equals(new Natural("1")))
            return this;

        // Do Binary Search for floor(sqrt(x))
        Natural start = new Natural("1"),
                end = new Natural(this.toString()),
                ans = new Natural("0"),
                doi = new Natural("2"),
                suma = new Natural("0"),
                mid = new Natural(),
                produs = new Natural();
        end.div(doi);
        while (start.isLower(end) || start.equals(end))
        {
            suma.setNat("0");
            suma.add(start);
            suma.add(end);
            mid.setNat(suma.toString());
            mid.div(doi);
            produs.setNat(mid.toString());
            produs.mul(produs);
            // If x is a perfect square
            if (produs.equals(this))
                return mid;

            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (produs.isLower(this))
            {
                start.setNat(mid.toString());
                start.add(new Natural("1"));
                ans.setNat(mid.toString());
            }
            else   // If mid*mid is greater than x
            {
                end.setNat(mid.toString());
                end.sub(new Natural("1"));
            }
        }
        return ans;
    }

    /**
     * Descriere: Verifica daca obiectul curent este mai mic decat un obiect de tip Natural n
     * Input: n - un obiect de tip natural
     * Output: true - daca numarul e mai mic
     *         false - daca numarul e mai mare
     * */
    public boolean isLower(Natural n){
        if(this.length < n.length){
            return true;
        }
        if(this.length == n.length){
            for(int i = this.startPos; i < maxCap; i++) {
                if (this.nat[i] < n.nat[i])
                    return true;
                else if(this.nat[i] > n.nat[i])
                    return false;
            }
        }
        return false;
    }

    public void pow(Natural new_n){
        Natural nr_pasi = new Natural("1");
        Natural unu = new Natural("1");
        Natural aux = new Natural(this.toString());
        while(!new_n.equals(nr_pasi)){
            this.mul(aux);
            new_n.sub(unu);
        }
    }

    /***Suprascrierea functiei equals*/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Natural)) { //Se verifica daca obiectul primit este de tip Natural
            return false;
        }
        Natural other = (Natural)obj;
        if(this.length != other.length)
            return false;
        for(int i = this.startPos; i < maxCap; i++)
            if(this.nat[i] != other.nat[i])
                return false;
        return true;
    }

    //Suprascrierea functiei toString
    @Override
    public String toString() {
        String s = "";
        for(int i = startPos; i < maxCap; i++) {
            s += nat[i];
        }
        return s;
    }
}


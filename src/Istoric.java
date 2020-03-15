import java.io.*;
import java.util.Vector;

public class Istoric {
    private Vector<Natural> vn;

    //Constructor fara parametri
    public Istoric(){
        this.vn = new Vector<Natural>();
        this.vn.add(new Natural("0"));
    }

    //Constructor cu parametri
    public Istoric(Vector<Natural> vn){ this.vn = vn; }

    //Adaugarea unui elemnt in vector
    public void addElem(String nat){ vn.add(new Natural(nat)); }

    //Stergerea ultimului element din istoric
    public void deleteLast(){ vn.remove(vn.size() - 1); }

    //Stergerea tuturor elementelor din istoric
    public void deleteAll(){
        vn.removeAllElements();
        vn.add(new Natural("0"));
    }

    //Getter al atributului vn
    public Vector<Natural> getVn() {
        return vn;
    }

    public Natural getLast(){
        return vn.lastElement();
    }

    public void setLast(Natural n){
        this.vn.add(n);
    }

    public int getSize(){
        return vn.size();
    }

    /*
     * Descriere: Verifica daca un numar este patrat perfect
     * Input: n - un obiect de tip natural
     * Output: true - daca numarul e patrat perfect
     *         false - in caz contrar
     * */
    public boolean isPerfect(){
        Natural perf = vn.lastElement().sqrt();
        perf.mul(perf);
        return perf.equals(vn.lastElement());
    }

    public Natural adunare(Natural n){
        Natural aux = new Natural(vn.get(vn.size() - 1).toString());
        aux.add(n);
        this.vn.add(new Natural(aux.toString()));
        return this.getLast();
    }

    public Natural scadere(Natural n){
        Natural aux = new Natural(vn.get(vn.size() - 1).toString());
        aux.sub(n);
        this.vn.add(new Natural(aux.toString()));
        return this.getLast();
    }

    public Natural inmultire(Natural n){
        Natural aux = new Natural(vn.get(vn.size() - 1).toString());
        aux.mul(n);
        this.vn.add(new Natural(aux.toString()));
        return this.getLast();
    }

    public Natural impartire(Natural n){
        Natural aux = new Natural(vn.get(vn.size() - 1).toString());
        aux.div(n);
        this.vn.add(new Natural(aux.toString()));
        return this.getLast();
    }

    public Natural modulo(Natural n){
        this.vn.add(new Natural(vn.lastElement().mod(n).toString()));
        return this.getLast();
    }

    public Natural radical(){
        this.vn.add(new Natural(vn.lastElement().sqrt().toString()));
        return this.getLast();
    }

    public String comparare(Natural n){
        if(vn.lastElement().isLower(n))
            return "<";
        if(vn.equals(n))
            return "=";
        return ">";
    }

    public Natural putere(Natural n){
        Natural aux = new Natural(vn.get(vn.size() - 1).toString());
        aux.pow(n);
        this.vn.add(new Natural(aux.toString()));
        return this.getLast();
    }


    public void writeToFile(String file_name){
        try {
            FileWriter fw = new FileWriter(file_name);
            for (int i = 0; i < this.vn.size(); i++)
                fw.write("" + this.vn.get(i) + "\n");
            fw.close();
        }
        catch (Exception e){ System.out.println(e); }
    }

    /*
     * Descriere: Citeste dintr-un fisier mai multe numere naturale
     * Input: file_name - un string care reprezinta locatia fisierului
     * Output:
     * */
    public void readFromFile(String file_name){
        try {
            vn = new Vector<Natural>();
            File f = new File(file_name);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String st;
            while ((st = br.readLine()) != null){
                if(st.length() != 0)
                {
                    st.replace("\n", "").replace("\r", "");;
                    this.vn.add(new Natural(st));
                }
            }
        }
        catch (FileNotFoundException ef){
            System.out.println(ef);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}

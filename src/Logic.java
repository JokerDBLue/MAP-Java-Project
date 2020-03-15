import java.util.Vector;

public class Logic {
    Istoric i;

    public Logic(){
        i = new Istoric();
    }

    public void setLast(String n){
        i.setLast(new Natural(n));
    }

    public Vector<Natural> getI() {
        return i.getVn();
    }

    public Natural getLast(){ return i.getLast();}

    public void undo(){
        if (i.getSize() > 1)
            i.deleteLast();
    }

    public Natural addunare(String n){
        return i.adunare(new Natural(n));
    }

    public Natural scadere(String n){
        return i.scadere(new Natural(n));
    }

    public Natural inmultire(String n){
        return i.inmultire(new Natural(n));
    }

    public Natural impartire(String n){
        return i.impartire(new Natural(n));
    }

    public Natural rest(String n){
        return i.modulo(new Natural(n));
    }

    public Natural radical(){
        return i.radical();
    }

    public String comparare(String n){
        return i.comparare(new Natural(n));
    }

    public String primalitate(){
        Natural n = new Natural(i.getLast().toString());
        Natural zero = new Natural("0");
        Natural doi = new Natural("2");
        Natural trei = new Natural("3");
        if(n.isLower(doi))
            return "Nu e prim";
        if(n.equals(doi)) return "E prim";
        if(n.equals(trei)) return "E prim";
        if((n.mod(doi).equals(zero)) || (n.mod(trei).equals(0))) return "Nu e prim";
        Natural index = new Natural("5");
        Natural aux = new Natural("5");
        aux.mul(index);

        while((aux.isLower(n)) || (aux.equals(n))){

            if(n.mod(index).equals(zero)) return "Nu e prim";
            index.add(doi);

            if(n.mod(index).equals(zero)) return "Nu e prim";
            index.add(new Natural("4"));
            aux.setNat(index.toString());

            aux.mul(index);
        }
        return "E prim";
    }

    public Natural putere(String n){
        return i.putere(new Natural(n));
    }

    public void clear(){
        i.deleteAll();
    }

    public boolean perfect(){
        return i.isPerfect();
    }

    public void fromIstoric(){
        i.readFromFile("history.txt");
    }

    public void writeToFile(){
        i.writeToFile("history.txt");
    }
}

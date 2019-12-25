package contest.model;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 25, 2008
 * Time: 3:35:19 PM
 */
public class Participant {
    private String ID, nume;
    private int nrProbe, punctaj;
    public Participant(String ID, String nume){
        this.ID=ID;
        this.nume=nume;
        nrProbe=0;
        punctaj=0;
    }
    public String getNume(){
        return nume;
    }
    public String getID(){
        return ID;
    }
    public int getNrProbe(){
        return nrProbe;
    }
    public int getPunctaj(){
        return punctaj;
    }

    public void adaugaRezultat(int puncte){
        this.punctaj+=puncte;
        nrProbe++;
    }

    public void setNrProbe(int nrProbe) {
        this.nrProbe = nrProbe;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public boolean equals(Object o){
        if (o instanceof Participant){
            Participant p=(Participant)o;
            return ID.equals(p.ID);
        }
        return false;
    }

    public String toString(){
        return ID+' '+nume+' '+nrProbe+' '+punctaj;
    }

    public static final String ID_FIELD = "id";
	public static final String NAME_FIELD = "name";
	public static final String POINTS_FIELD = "points";
    public static final String TESTS_FIELD = "tests";

}

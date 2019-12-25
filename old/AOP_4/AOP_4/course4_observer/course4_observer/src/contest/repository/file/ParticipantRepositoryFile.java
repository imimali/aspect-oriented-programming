package contest.repository.file;

import contest.model.Participant;
import contest.repository.ParticipantRepository;
import contest.repository.RepositoryException;


import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Oct 29, 2009
 * Time: 8:20:15 PM
 */
public class ParticipantRepositoryFile implements ParticipantRepository{
    private final String partFile; //="participants.txt";
    private Map<String, Participant> participants;
   
    public ParticipantRepositoryFile(String partFile) {
        this.partFile = partFile;
        readParticipants();
    }



    private void readParticipants() {
        participants=new TreeMap<String, Participant>();
         BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(partFile));
            String linie;
            while((linie=br.readLine())!=null){
                String[] elem=linie.split("[|]");
                if (elem.length!=4){
                    System.err.println("[ParticipantRepository:] Linie invalida: "+linie);
                    continue;
                }
                Participant p=new Participant(elem[0],elem[1]);
                try{
                    p.setNrProbe(Integer.parseInt(elem[2]));
                    p.setPunctaj(Integer.parseInt(elem[3]));
                    participants.put(p.getID(), p);
                }catch(NumberFormatException e){
                    System.err.println("[ParticipantRepository:] Linie invalida: "+linie);

                }
            }
            br.close();
        } catch (IOException e) {
            throw new RepositoryException("Reading error "+e);
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RepositoryException("Closing error "+e);
                }
            }
        }
    }



    public void save(Participant p) {

        Participant ePart=participants.get(p.getID());
        if (ePart!=null)
            throw new RepositoryException("Participant" +p+" already exists!");
        participants.put(p.getID(),p);
        writeParticipants();
    }

    private void writeParticipants() {
        System.out.println("scriem participanti");
        Collection<Participant> parts=participants.values();
        PrintWriter pw=null;
        try{
             pw=new PrintWriter(partFile);
            for (Participant part: parts){
                pw.println(part.getID()+'|'+part.getNume()+'|'+part.getNrProbe()+'|'+part.getPunctaj());
            }
            pw.close();
        }catch(IOException e){
            throw new RepositoryException("[IOException: ] "+e.getMessage());
        }finally{
            if (pw!=null)
                pw.close();
        }
    }

    public void update(String codePart, Participant p) {

        Participant ePart=participants.get(codePart);
        if (ePart==null)
            throw new RepositoryException("Participant "+codePart+" does not exist!");
        participants.put(codePart, p);
        writeParticipants();
    }

    public List<Participant> getAll() {
        return new ArrayList<Participant>(participants.values());
    }

    public Participant findById(String code) {
        Participant part=participants.get(code);
        if (part==null)
            throw new RepositoryException("Participant "+code+ "does not exist!");
        return part;
    }

    public List<Participant> getByPoints() {
        List<Participant> lp=new ArrayList<Participant>(participants.values());
        Collections.sort(lp, new Comparator<Participant>(){
            public int compare(Participant p1, Participant p2) {
                return (p2.getPunctaj()-p1.getPunctaj());
            }
        });
        return lp;
    }




}

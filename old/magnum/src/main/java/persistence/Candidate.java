package persistence;

public class Candidate {
    private Long id;
    private String name;
    private Integer votes;

    public Candidate(Long id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    public Candidate(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return name+" "+votes;
    }
}

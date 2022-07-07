package uk.ac.bristol.cs.application.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Ward extends ModelClass implements Serializable {
    @Id private String id;
    private String name;
    private int electorate;

    @OneToMany(mappedBy = "ward", fetch = FetchType.EAGER)
    private List<Candidate> candidates = new ArrayList<Candidate>();

    public String getName() { return name; }
    public String getId() { return id; }
    public int getElectorate() { return electorate; }
    public List<Candidate> getCandidates() { return candidates; }
    public int getTotalVotes() {
        return candidates.stream().map(x -> x.getVotes()).reduce(0, (a, b) -> a + b);
    }
    
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setElectorate(int electorate) { this.electorate = electorate; }
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }


    @Override
    public int hashCode() { 
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
     }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ward other = (Ward) obj;
        return Objects.equals(this.id, other.id);
    }
}

package uk.ac.bristol.cs.application.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Candidate extends ModelClass implements Serializable {
    @Id private String id;
    private String name;
    private String ward; //Stores the ward ID
    private int votes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "party")
    private Party party;

    public String getName() { return name; }
    public String getId() { return id; }
    public Party getParty() { return party; }
    public String getWard() { return ward; }
    public int getVotes() { return votes; }
    
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setParty(Party party) { this.party = party; }
    public void setWard(String ward) { this.ward = ward; }
    public void setVotes(int votes) {this.votes = votes; }

    @Override
    public int hashCode() { 
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
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
        final Candidate other = (Candidate) obj;
        return Objects.equals(this.id, other.id);
    }
}

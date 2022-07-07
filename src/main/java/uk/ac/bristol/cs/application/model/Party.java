package uk.ac.bristol.cs.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Party extends ModelClass implements Serializable {
    @Id private String id;
    private String name;

    public String getName() { return name; }
    public String getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }

    @Override
    public int hashCode() { 
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.name);
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
        final Party other = (Party) obj;
        return Objects.equals(this.id, other.id);
    }
}

package uk.ac.bristol.cs.application.model;

/**
 * A class that contains only the ward name and id. This is done to minimise data sent 
 * to client
 */
public class WardListData {
    public String id;
    public String name;
    
    /**
     * Creates minimal representation of ward
     * @param w The ward
     */
    public WardListData(Ward w){
        id = w.getId();
        name = w.getName();
    }

    //Getters
    public String getId() { return this.id; }
    public String getName() { return this.name; }

    //Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
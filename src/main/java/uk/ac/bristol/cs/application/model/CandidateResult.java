package uk.ac.bristol.cs.application.model;

public class CandidateResult {
    private String id; //Stores id of candidate
    private String name; //Stores candidate name
    private String party; //Stores party name
    private int votes; //Stores number of votes given to candidate
    private String votePercentString; //Stores percent of votes given to candidate as a percent

    /**
     * Creates new Candidate result
     * @param candidate The candidate to generate the calculations for
     */
    public CandidateResult(Candidate candidate, int totalVotes){
        id = candidate.getId();
        name = candidate.getName();
        votes = candidate.getVotes();
        party = candidate.getParty().getName();
        double votePercent = ((double) votes / (double) totalVotes) * 100.0;
        votePercentString = String.format("%.1f", votePercent) + "%";
    }

    //Getters
    public String getId(){ return this.id; }
    public String getName() { return this.name; }
    public String getParty() { return this.party; }
    public int getVotes() { return this.votes; }
    public String getVotePercentString() { return this.votePercentString; }

    //Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setParty(String party) { this.party = party; }
    public void setVotes(int votes) { this.votes = votes; }
    public void setVotePercentString(String votePercentString) { 
        this.votePercentString = votePercentString; 
    }
}
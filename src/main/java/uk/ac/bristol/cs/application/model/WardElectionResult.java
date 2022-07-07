package uk.ac.bristol.cs.application.model;

import java.util.ArrayList;
import java.util.List;

public final class WardElectionResult {
    private String id; //Ward ID
    private String wardName;
    //List of results, with winner at element 0
    private List<CandidateResult> results = new ArrayList<CandidateResult>();
    private int totalVotes;
    private String turnoutString; //Stores turnout as a String
    private int electorate; //Stores total number of voters possible for ward

    /**
     * Creates new Ward Election result
     * @param w The ward to generate the result calculations for
     */
    public WardElectionResult(Ward w) {
        totalVotes = w.getTotalVotes();
        id = w.getId();
        wardName = w.getName();
        electorate = w.getElectorate();
        double turnout = ((double) totalVotes / (double) electorate) * 100.0;
        turnoutString = String.format("%.1f", turnout) + "%";
        for(Candidate cand : w.getCandidates()) results.add(new CandidateResult(cand, totalVotes));
        results.sort((a, b) -> Integer.compare(b.getVotes(), a.getVotes()));
    }

    //Getters
    public String getId() { return this.id; }
    public List<CandidateResult> getResults() { return this.results; }
    public int getTotalVotes() { return this.totalVotes; }
    public String getTurnoutString() { return this.turnoutString; }
    public String getWardName() { return this.wardName; }
    public int getElectorate() { return this.electorate; }

    //Setters
    public void setId(String id) { this.id = id; }
    public void setResults(List<CandidateResult> results) { this.results = results; }
    public void setTotalVotes(int totalVotes) { this.totalVotes = totalVotes; }
    public void setTurnoutString(String turnoutString) { this.turnoutString = turnoutString; }
    public void setWardName(String wardName) { this.wardName = wardName; }
    public void setElectorate(int electorate) { this.electorate = electorate; }

}
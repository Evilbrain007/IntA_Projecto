package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;

public class IDAStarSearch extends InformedSearch {
    /*
     * Note that, on each iteration, the search is done in a depth first search way.    
     */
    
    private double limit;
    private double newLimit;    

    @Override
    public Solution search(Problem problem) {
        //TODO
        return null;
    }

    @Override
    protected Solution graphSearch(Problem problem) {
        //TODO
        return null;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //TODO
    }

    @Override
    public String toString() {
        return "IDA* search";
    }
}

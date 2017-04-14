package searchmethods;

import agent.State;
import java.util.List;

public class GreedyBestFirstSearch extends InformedSearch {

    //f = h
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //TODO
    }

    @Override
    public String toString() {
        return "Greedy best first search";
    }
}
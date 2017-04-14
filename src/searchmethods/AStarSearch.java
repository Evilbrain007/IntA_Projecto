package searchmethods;

import agent.State;
import java.util.List;

public class AStarSearch extends InformedSearch {

    //f = g + h
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //TODO
    }

    @Override
    public String toString() {
        return "A* search";
    }
}

package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    //Graph Search without explored list
    @Override
    protected Solution graphSearch(Problem problem) {
        //TODO
        return null;
    }

    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //TODO
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}

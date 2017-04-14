package searchmethods;

import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }
    
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //TODO
    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}
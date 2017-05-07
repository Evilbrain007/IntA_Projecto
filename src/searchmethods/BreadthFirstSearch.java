package searchmethods;

import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }
    
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //para cd estado sucessor da lista de sucessores
        //se o sucessor nao esta na fronteira nem na lista de nós explorados
        //entaoacrescentar sucessor ao fim da fronteira (first in first out)

        for (State successor : successors) {
            if (!frontier.containsState(successor) && !(explored.contains(successor))){
                frontier.addLast(new Node(successor, parent));
                //parent é o no original que foi passado como parametro na funcao
            }
        }
    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}
package searchmethods;

import agent.State;
import java.util.List;
import utils.NodePriorityQueue;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue> {

    public UniformCostSearch(){
        frontier = new NodePriorityQueue();
    }    
    
    // f = g
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

            for (State successor: successors){
                double g = parent.getG() + successor.getAction().getCost();

                if(!frontier.containsState(successor)){
                    if(!explored.contains(successor)){
                        Node node = new Node(successor, parent, g, g);//neste caso, a prioridade tb é o custo
                        frontier.add(node);
                    }
                } else { //ver se o estado k esta na fronteira tem um custo superior ao deste
                    if (frontier.getNode(successor).getG() > g){
                        frontier.removeNode(successor);
                        Node node = new Node(successor, parent, g, g);
                        frontier.add(node);
                    }
                }
            }

    }

    @Override
    public String toString() {
        return "Uniform cost search";
    }
}

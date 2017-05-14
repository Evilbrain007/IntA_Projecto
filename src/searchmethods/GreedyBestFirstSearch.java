package searchmethods;

import agent.State;
import java.util.List;

public class GreedyBestFirstSearch extends InformedSearch {

    //f = h
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State successor: successors){ //para cd estado sucessor calculamos o custo
            double g = parent.getG() + successor.getAction().getCost();

            //se a fornteira nao tiver o sucessor e a lista de nós explorados tb nao
            if(!frontier.containsState(successor)){
                if(!explored.contains(successor)){
                    //criamos um novo nó
                    double h = heuristic.compute(successor);
                    Node node = new Node(successor, parent, g, h); // a prioridade aqui é a heuristica ja nao é o custo
                    frontier.add(node);
                }
            } else { //ver se o estado k esta na fronteira tem um custo superior ao deste
                if (frontier.getNode(successor).getG() > g){
                    frontier.removeNode(successor);
                    double h = heuristic.compute(successor);
                    Node node = new Node(successor, parent, g, h);
                    frontier.add(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Greedy best first search";
    }
}
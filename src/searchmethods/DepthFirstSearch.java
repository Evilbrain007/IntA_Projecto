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
        //initialize the frontier using the initial state of problem
        frontier.clear();
        //cd vez k keremos acrescentar algo a fronteiroa temos k crair um nó
        frontier.add(new Node(problem.getInitialState()));

//        while(frontier is not empty), ie, qd ja nao ha mais nos para expandir
        while (!frontier.isEmpty() && !stopped){ //se a flag stopped nao estiver a true

            //        remove the first node from the frontier
            Node node = frontier.poll();

//        if the node contains a goal state then
            if(problem.isGoal(node.getState())){
                //  return the corresponding solution
                return new Solution(problem, node);
            }

//        expand the node, adding the resulting nodes to the frontier only if
//        not in the frontier or explored set
            List<State> successors = problem.executeActions(node.getState());
            addSuccessorsToFrontier(successors, node);

            computeStatistics(successors.size());

        }

//        return failure

        return null;
    }

    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        //para cd estado sucessor da lista de sucessores
        //se o sucessor nao esta na fronteira nem na lista de nós explorados
        //entao acrescentar sucessor ao inicio da fronteira (last in first out) -pk aki usamos pilha em vez
        //de fila como no breathFirsSearch

        for (State successor : successors) {
            if (!frontier.containsState(successor)){
                Node node = new Node(successor, parent);

                if (!node.isCycle()){
                    frontier.addFirst(node);
                }

                //parent é o no original que foi passado como parametro na funcao
            }
        }
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}

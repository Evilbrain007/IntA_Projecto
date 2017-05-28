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
        stopped = false;
        statistics.reset();
        this.heuristic = problem.getHeuristic();

        limit = heuristic.compute(problem.getInitialState());//definimos que o limite é o valor estimado
        //dd o estado inicial ate ao fim

        Solution solution = null;

        do {
            solution = graphSearch(problem);
        } while (solution == null);

        return solution;
    }

    @Override
    protected Solution graphSearch(Problem problem) {
        newLimit = Double.POSITIVE_INFINITY;

        //initialize the frontier using the initial state of problem
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

//        while(frontier is not empty), ie, qd ja nao ha mais nos para expandir
        while (!frontier.isEmpty() && !stopped) { //se a flag stopped nao estiver a true

            //        remove the first node from the frontier
            Node node = frontier.poll();

//        if the node contains a goal state then
            if (problem.isGoal(node.getState())) {
                //  return the corresponding solution
                return new Solution(problem, node);
            }

//        expand the node, adding the resulting nodes to the frontier only if
//        not in the frontier or explored set
            List<State> successors = problem.executeActions(node.getState());
            addSuccessorsToFrontier(successors, node);

            computeStatistics(successors.size());

        }

        limit = newLimit;
        return null;

    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State sucsessor : successors) {

            double g = parent.getG() + sucsessor.getAction().getCost();
            double h = heuristic.compute(sucsessor);
            double f = g + h;
            Node node = new Node(sucsessor, parent, g, f);

            if (!frontier.containsState(sucsessor) && !(explored.contains(sucsessor))){

                if (f<=limit){

                    if (!node.isCycle()){
                        frontier.add(node);
                    }
                } else {
                    newLimit = Math.min(f, newLimit);
                }

            }else{
                if(frontier.getNode(sucsessor).getG() > g){
                    frontier.removeNode(sucsessor);
                    frontier.add(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "IDA* search";
    }
}

package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;

public class IterativeDeepeningSearch extends DepthFirstSearch {
    /*
     * We do not use the code from DepthLimitedSearch because we can optimize
     * so that the algorithm only verifies if a state is a goal if its depth is
     * equal to the limit. Note that given a limit X we are sure not to
     * encounter a solution below this limit because a (failed) limited depth
     * search has already been done. That's why we do not extend this class from
     * DepthLimitedSearch. We extend from DepthFirstSearch so that we don't need
     * to rewrite method insertSuccessorsInFrontier again.
     * After the class, please see a version of the search algorithm without
     * this optimization.
     */    
    
    private int limit;

    @Override
    public Solution search(Problem problem) {
        statistics.reset();
        stopped = false;

        Solution solution = null;

        limit = 0;
        do{
            solution = graphSearch(problem);
            this.limit++;
        } while(solution==null);

        return solution;

    }
    
    @Override
    protected Solution graphSearch(Problem problem) {
        //initialize the frontier using the initial state of problem
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));
//        while(frontier is not empty), ie, qd ja nao ha mais nos para expandir
        while (!frontier.isEmpty() && !stopped) { //se a flag stopped nao estiver a true

            //        remove the first node from the frontier
            Node node = frontier.poll();
//        se a profundidade do nó for igual ao limite que estabelecemos.
            if (node.getDepth() == limit && problem.isGoal(node.getState())) {
                //  return the corresponding solution
                return new Solution(problem, node);
            }

//        expand the node, adding the resulting nodes to the frontier only if
//        not in the frontier or explored set

            List<State> successors = problem.executeActions(node.getState());

            if (node.getDepth() < limit) {
                addSuccessorsToFrontier(successors, node);
            }
            computeStatistics(successors.size());
        }
        return null;
    }

    @Override
    public String toString() {
        return "Iterative deepening search";
    }
}
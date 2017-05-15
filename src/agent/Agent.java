package agent;

import java.util.ArrayList;
import searchmethods.*;

public class Agent<E extends State> {

    protected E environment;
    protected ArrayList<SearchMethod> searchMethods;
    protected SearchMethod searchMethod;
    protected ArrayList<Heuristic> heuristics;
    protected Heuristic heuristic;
    protected Solution solution;

    public Agent(E environment) {
        this.environment = environment;
        searchMethods = new ArrayList<>();
        searchMethods.add(new BreadthFirstSearch());
        searchMethods.add(new UniformCostSearch());
        searchMethods.add(new DepthFirstSearch());
        searchMethods.add(new DepthLimitedSearch());
        searchMethods.add(new IterativeDeepeningSearch());
        searchMethods.add(new GreedyBestFirstSearch());
        searchMethods.add(new AStarSearch());
        searchMethods.add(new BeamSearch());
        searchMethods.add(new IDAStarSearch());
        searchMethod = searchMethods.get(0);
        heuristics = new ArrayList<>();
    }
    //Inicia a resolucao do problema que lhe foi passado. Se houver uma heuristica seleccionada faz set a ela
    //para ser usada na resolucao do problema
    public Solution solveProblem(Problem problem) {
        if (heuristic != null) {
            problem.setHeuristic(heuristic); //set a heuristica no problem
            heuristic.setProblem(problem); //set ao problem na heuristica
        }
        solution = searchMethod.search(problem); //com base no metodo de busca seleccionado, chama o metodo search
        // para procurar a solucao. o search devolve uma solucao para o problema dado
        //por fim, o metodo solve devolve a solucao
        return solution;
    }

    public void executeSolution() {    
        for(Action action : solution.getActions()){
            environment.executeAction(action);
        }
    }

    public boolean hasSolution() {
        return solution != null;
    }

    public void stop() {
        getSearchMethod().stop();
    }

    public boolean hasBeenStopped() {
        return getSearchMethod().hasBeenStopped();
    }

    public E getEnvironment() {
        return environment;
    }

    public void setEnvironment(E environment) {
        this.environment = environment;
    }

    public SearchMethod[] getSearchMethodsArray() {
        SearchMethod[] sm = new SearchMethod[searchMethods.size()];
        return searchMethods.toArray(sm);
    }

    public SearchMethod getSearchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    public Heuristic[] getHeuristicsArray() {
        Heuristic[] sm = new Heuristic[heuristics.size()];
        return heuristics.toArray(sm);
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public String getSearchReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(searchMethod).append("\n");
        if (solution == null) {
            sb.append("No solution found\n");
        } else {
            sb.append("Solution cost: ").append(Double.toString(solution.getCost())).append("\n");
        }
        sb.append("Num of expanded nodes: ").append(searchMethod.getStatistics().numExpandedNodes).append("\n");
        sb.append("Max frontier size: ").append(searchMethod.getStatistics().maxFrontierSize).append("\n");
        sb.append("Num of generated nodes: ").append(searchMethod.getStatistics().numGeneratedNodes).append("\n");

        return sb.toString();
    }
}

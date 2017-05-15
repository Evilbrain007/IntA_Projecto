package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utils.NodeCollection;

public abstract class GraphSearch<L extends NodeCollection> implements SearchMethod {

    protected L frontier; //tem que ser algo que extenda de NodeCollection
    protected Set<State> explored = new HashSet<State>();
    protected Statistics statistics = new Statistics();    
    protected boolean stopped;

    @Override //metodo da interface SearchMethod
    public Solution search(Problem problem) {
        statistics.reset();
        stopped = false;
        return graphSearch(problem); //chama o metodo graphSeardesta classe e devolve a solucao por ele encontrada
    }

    /*
     function GRAPH-SEARCH(problem) returns a solution, or failure
        initialize the frontier using the initial state of problem
        initialize the explored set to be empty
        while(frontier is not empty)
            remove the first node from the frontier
            if the node contains a goal state then return the corresponding solution
            add the node to the explored set
            expand the node, adding the resulting nodes to the frontier only if
                not in the frontier or explored set
        return failure
     */
    protected Solution graphSearch(Problem problem) {
        //inicializar a fronteira usando o estado inicial do problema
        //cd vez k keremos acrescentar algo a fronteiroa temos k criar um nó
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        //inicializa a lista de nos explorados vazia
        explored.clear();

        //ekto a fronteira não estiver vazia, ie, qd ja nao ha mais nos para expandir e
        //se a flag stopped nao estiver a true
        while (!frontier.isEmpty() && !stopped){

            // o poll() remove o no da fronteira e guarda esse no num objecto Node
            //para que depois esse no seja usado para expandir
            Node node = frontier.poll();

            //Verificamos se o estado contido no nó é o estado objectivo, se for, devolvemos a solução
            if(problem.isGoal(node.getState())){

                return new Solution(problem, node);
            }
            //adicionamos o nó (que foi feito poll() à lista de nos explorados
            explored.add(node.getState());

            //expande o nó executando tds as acçoes para tdas as possibilidades desse estado (executeActions)
            // essas acçoes dao origem a estados sucessores que ficam numa lista successors

            List<State> successors = problem.executeActions(node.getState());

            //adiciona os nos a fronteira - este metodo é diferente consoante o searchMethod
            addSuccessorsToFrontier(successors, node);

            computeStatistics(successors.size());

        }

        //return failure
        return null;
    }

    public abstract void addSuccessorsToFrontier(List<State> successors, Node parent);

    protected void computeStatistics(int successorsSize) {
        statistics.numExpandedNodes++;
        statistics.numGeneratedNodes += successorsSize;
        statistics.maxFrontierSize = Math.max(statistics.maxFrontierSize, frontier.size());
    }
    
    public Statistics getStatistics(){
        return statistics;
    }

    public void stop() {
        stopped = true;
    }

    public boolean hasBeenStopped() {
        return stopped;
    }
}
    
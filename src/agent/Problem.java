package agent;

import java.util.ArrayList;
import java.util.List;

public abstract class Problem <S extends State>{

    protected S initialState;
    protected ArrayList<Action> actions;
    private Heuristic heuristic;

    public Problem(S initialState, ArrayList<Action> actions) {
        this.initialState = initialState;
        this.actions = actions;
    }

    //metodo chamado pelos algoritmos de procura que devolve todos os estados que o algoritmo computa a partir de um estado
    public abstract List<S> executeActions(S state);

    //metodo para verificar se o estado recebido é o objectivo ou nao
    public abstract boolean isGoal(S state);

    //calcula o custo para chegar à solucao
    public double computePathCost(List<Action> path){

        double sum = 0;

        for (Action action :
                path) {
            sum += action.getCost();
        }
        return  sum;
    }

    public S getInitialState(){
        return  initialState;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }
}

package montacargas;

import agent.Heuristic;

/**
 * Created by sorai on 15-May-17.
 */
public class HeuristicBetterToTheLeft extends Heuristic<MontaCargasProblem, MontaCargasState> {

    @Override
    public double compute(MontaCargasState state) {
        return state.computeColumnValueSum();
    }

    @Override
    public String toString() {
        return "HeuristicBetterToTheLeft";
    }
}

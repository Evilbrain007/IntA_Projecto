package montacargas;

import agent.Heuristic;

/**
 * Created by sorai on 15-May-17.
 */

//a classe especifica que extend de Heuristic é que vai ter como genericos o MontacargasProblem e o state
public class HeuristicBoxesInTheWay extends Heuristic <MontaCargasProblem, MontaCargasState>{

    @Override
    public double compute(MontaCargasState state) {
        return state.computeBoxesInTheWay();
    }

    @Override
    public String toString() {
        return "HeuristicBoxesInTheWay";
    }
}

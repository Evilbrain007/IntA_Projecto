package montacargas;

import agent.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sorai on 23-Apr-17.
 */
public class MontaCargasProblem extends Problem<MontaCargasState> {

    //o estado final apenas interessa quando a coluna do forklift é a última
    //private MontaCargasState goalState;

    public MontaCargasProblem(MontaCargasState initialState) {
       super(initialState, new ArrayList<>(4));

    }

    @Override
    public List<MontaCargasState> executeActions(MontaCargasState state) {
        return null;
    }

    @Override
    public boolean isGoal(MontaCargasState state) {
        return false;
    }
}

package eightpuzzle;

import agent.Action;
import agent.Problem;

import java.util.ArrayList;
import java.util.List;

public class EightPuzzleProblem extends Problem<EightPuzzleState> {
    public EightPuzzleProblem(EightPuzzleState initialState, ArrayList<Action> actions) {
        super(initialState, actions);
    }

    @Override
    public List<EightPuzzleState> executeActions(EightPuzzleState state) {
        return null;
    }

    @Override
    public boolean isGoal(EightPuzzleState state) {
        return false;
    }

    //TODO
}

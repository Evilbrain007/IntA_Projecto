package montacargas;

import agent.Action;

public class ActionDown extends Action<MontaCargasState> {

    public ActionDown() {
        super(1);
    }

    public void execute(MontaCargasState state) {
        state.moveCurrentObjDown();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state) {

        return state.canMoveCurrentObjDown();

    }
}
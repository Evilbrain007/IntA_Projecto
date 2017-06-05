package montacargas;

import agent.Action;

public class ActionDown extends ActionWithObj{

    public ActionDown() {
        super(1);
    }

    public void execute(MontaCargasState state) {
        if(this.objIndex!=-1){
            state.setCurrentObject(this.objIndex);
        }
        state.moveCurrentObjDown();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state) {

        return state.canMoveCurrentObjDown();

    }

    @Override
    public Object clone() {
        return new ActionDown();
    }
}
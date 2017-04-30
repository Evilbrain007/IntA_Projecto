package montacargas;

import agent.Action;

public class ActionLeft extends ActionWithObj{

    public ActionLeft(){
        super(1);
    }

    public void execute(MontaCargasState state){
        if(this.objIndex!=-1){
            state.setCurrentObject(this.objIndex);
        }
       state.moveCurrentObjLeft();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return state.canMoveCurrentObjLeft();
    }
}

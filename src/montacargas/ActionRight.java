package montacargas;

import agent.Action;

public class ActionRight extends ActionWithObj{

    public ActionRight(){
        super(1);
    }

    public void execute(MontaCargasState state){
        if(this.objIndex!=-1){
            state.setCurrentObject(this.objIndex);
        }
       state.moveCurrentObjRight();
       state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return state.canMoveCurrentObjRight();

    }
}
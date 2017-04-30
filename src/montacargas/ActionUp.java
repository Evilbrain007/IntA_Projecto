package montacargas;

import agent.Action;

public class ActionUp extends ActionWithObj{

    public ActionUp(){
        super(1);
    }

    public void execute(MontaCargasState state){
        if(this.objIndex!=-1){
            state.setCurrentObject(this.objIndex);
        }
       state.moveCurrentObjUp();
       state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return state.canMoveCurrentObjUp();
    }
}
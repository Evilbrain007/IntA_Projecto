package montacargas;

import agent.Action;

public class ActionUp extends Action<MontaCargasState>{

    public ActionUp(){
        super(1);
    }

    public void execute(MontaCargasState state){
       state.moveCurrentObjUp();
       state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return state.canMoveCurrentObjUp();
    }
}
package montacargas;

import agent.Action;

public class ActionUp extends Action<MontaCargasState>{

    public ActionUp(){
        super(1);
    }

    public void execute(MontaCargasState state){
       // state.moveUp();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return false;
        //return state.canMoveUp();
    }
}
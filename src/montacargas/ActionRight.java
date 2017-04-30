package montacargas;

import agent.Action;

public class ActionRight extends Action<MontaCargasState>{

    public ActionRight(){
        super(1);
    }

    public void execute(MontaCargasState state){
       // state.moveRight();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return false;
                //state.canMoveRight();
    }
}
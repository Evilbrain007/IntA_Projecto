package montacargas;

import agent.Action;
import eightpuzzle.EightPuzzleState;

public class ActionDown extends Action<MontaCargasState>{

    public ActionDown(){
        super(1);
    }

    public void execute(MontaCargasState state){
      //  state.moveDown();
        state.setAction(this);
    }

    public boolean isValid(MontaCargasState state){

        return false;
                //state.canMoveDown();
    }
}
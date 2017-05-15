package montacargas;

import agent.Action;
import agent.Problem;
import montacargas.model.Forklift;
import montacargas.model.GridObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sorai on 23-Apr-17.
 */
public class MontaCargasProblem extends Problem<MontaCargasState> {

    //o estado final apenas interessa quando a coluna do forklift é a última
    //private MontaCargasState goalState;

    public MontaCargasProblem(MontaCargasState initialState) {
       super(initialState, new ArrayList<>(4));

       this.actions.add(new ActionUp());
       this.actions.add(new ActionDown());
       this.actions.add(new ActionRight());
       this.actions.add(new ActionLeft());

    }

    @Override
    public List<MontaCargasState> executeActions(MontaCargasState state) {

        LinkedList<MontaCargasState> successors = new LinkedList<>();
        ArrayList<GridObject> gridObjects = state.getGridObjects();

        int stateObject = state.getCurrentObject();

        for (Action action: this.actions){
            for (int i = 0; i < gridObjects.size(); i++) {
                //a cada iteraçao diz ao MontaCargasState qual o objecto corrente em k o ciclo está
                state.setCurrentObject(i);
                //depois verifica se a accao é valida para esse objecto chamando o isvalid da acçao,
                //k chama o isvalid do estado e, no estado, sabendo que o current object é i
                //veriica se é valido para esse objecto

                if(action.isValid(state)){
                    //se accao for valida, faz um clone do Estado
                    //e executa a acçao sobre esse objecto corrente
                    MontaCargasState successor = (MontaCargasState) state.clone();
                    successor.setCurrentObject(i);

                    successor.executeAction(action);

                    //guardamos o estado na lista de sucessores
                    successors.add(successor);

                }
            }
        }
        state.setCurrentObject(stateObject);
        return successors;
    }

    @Override
    public boolean isGoal(MontaCargasState state) {

        Forklift forklift = state.getForklift();
        int size = state.getMatrixSize();
        return forklift.getPosition().y==size-1;
    }
}

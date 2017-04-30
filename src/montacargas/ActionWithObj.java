package montacargas;

import agent.Action;

/**
 * Created by sorai on 30-Apr-17.
 */
public abstract class ActionWithObj extends Action<MontaCargasState> {

    protected  int objIndex;

    public ActionWithObj(double cost) {
        super(cost);
        //inicializamos o indice do objecto a -1 pk é um indice k nc vai existir
        this.objIndex=-1;
    }

    public int getObjIndex() {
        return objIndex;
    }

    public void setObjIndex(int objIndex) {
        this.objIndex = objIndex;
    }
}

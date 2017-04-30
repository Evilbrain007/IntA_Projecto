package montacargas.model;

import java.awt.*;

/**
 * Created by sorai on 26-Apr-17.
 */
public class Box extends GridObject {

    public Box(int x, int y, int boxNum) {
        //definimos a osição do objecto
        this.position = new Point(x, y);

        //definimos a orientaçao que o objecto vai ter a partir do numero da caixa
        this.orientation = (boxNum%2)==0 ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        /*
        if(boxNum%2 == 0){
            this.orientation = Orientation.HORIZONTAL;
        } else{
            this.orientation = Orientation.VERTICAL;
        }*/

        //a partir do numero da caixa inferimos o seu tamanho (conjunto de 2, 3 ou 4 caixas)
        this.size = boxNum/2;
        this.objectValue = boxNum;

    }


}

package montacargas.model;

import java.awt.*;

/**
 * Created by sorai on 26-Apr-17.
 */
public class Forklift extends GridObject {
    //o monta cargas so precisa de receber a posicao pk os outros parametros nao mudam
    public Forklift(int x, int y) {
        this.position = new Point(x, y);
        this.orientation = Orientation.HORIZONTAL;
        this.size = 1;
        this.objectValue = 1;
    }
}

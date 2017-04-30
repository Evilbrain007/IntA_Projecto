package montacargas.model;

import java.awt.*;

/**
 * Created by sorai on 26-Apr-17.
 */
public abstract class GridObject {

    protected Point position;
    protected int size;
    protected Orientation orientation;
    protected int objectValue; //valor que o objecto tem consoante o seu tamanho e orientacao

    public int getSize() {
        return size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getPosition() {
        return position;
    }

    public int getObjectValue() {
        return objectValue;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}

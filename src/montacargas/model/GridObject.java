package montacargas.model;

import java.awt.*;

/**
 * Created by sorai on 26-Apr-17.
 */
public abstract class GridObject {

    protected Point position;
    protected int size;
    protected Orientation orientation;

    public int getSize() {
        return size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getPosition() {
        return position;
    }
}

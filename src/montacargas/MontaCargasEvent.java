package montacargas;

import java.util.EventObject;

/**
 * Created by sorai on 15-Apr-17.
 */
public class MontaCargasEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MontaCargasEvent(MontaCargasState source) {
        super(source);
    }
}

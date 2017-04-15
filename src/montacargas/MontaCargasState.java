package montacargas;

import agent.Action;
import agent.State;
import gui.PuzzleTableModel;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sorai on 15-Apr-17.
 */
public class MontaCargasState extends State implements Cloneable {

    private  int[][] matrix;
    //pode ou nao ser utilizado
    private int montaCargasRow;
    private int montaCargasColumn;

    public MontaCargasState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for(int l = 0; l<matrix.length; l++){
            for (int c = 0; c < matrix.length; c++) {
                this.matrix[l][c] = matrix[l][c];
                //pode ou nao ser utilizado - o valor 1 representa o nosso montaCargas
                if(this.matrix[l][c] == 1){
                    montaCargasRow = l;
                    montaCargasColumn = c;
                }
            }
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        firedPuzzleChanged(null);
    }

    @Override
    public int hashCode() {
        //nao sei se é bem assim
        return 97*7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MontaCargasState)){
            return false;
        }

        MontaCargasState state = (MontaCargasState) obj;

        if(this.matrix.length!= state.matrix.length){
            return false;
        }

        return Arrays.deepEquals(this.matrix, state.matrix);

    }

    @Override
    protected Object clone() {
        return new MontaCargasState(this.matrix);
    }

    public int getNumRows(){
        return matrix.length;
    }

    public int getNumColumns(){
        return matrix.length;
    }

    private transient ArrayList<MontaCargasListener> listeners = new ArrayList<>(3);

    private void firedPuzzleChanged(MontaCargasEvent event) {
        for (MontaCargasListener listener: listeners) {
            listener.puzzleChanged(null);
        }
    }

    public int getTileValue(int row, int col) {

        if(!isValidPosition(row, col)){
            throw new IndexOutOfBoundsException("A posição não é válida!");
        }
        return matrix[row][col];
    }

    private boolean isValidPosition(int row, int col) {
        //verifica se o numero da linha e da coluna está dentro dos limites da matriz
        return !(row < 0 || col < 0) && !(row > matrix.length || col > matrix.length);

        //return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;

    }

    public synchronized void addListener(MontaCargasListener l) {
        if(!this.listeners.contains(l)){
            listeners.add(l);
        }

    }

    public synchronized void removeListener(MontaCargasListener l){
        if(l!=null && this.listeners.contains(l)){
            listeners.remove(l);
        }
    }

}

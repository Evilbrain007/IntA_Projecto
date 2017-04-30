package montacargas;

import agent.Action;
import agent.State;
import montacargas.model.Box;
import montacargas.model.Forklift;
import montacargas.model.GridObject;
import montacargas.model.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sorai on 15-Apr-17.
 */
public class MontaCargasState extends State implements Cloneable {

    private int[][] matrix;
    //pode ou nao ser utilizado
    private int montaCargasRow;
    private int montaCargasColumn;
    //lista de objectos do estado
    private ArrayList<GridObject> gridObjects;
    //atributo para apanhar o objecto corrente da lista de gridobjects que está a ser percorrido
    private int currentObject;


    public MontaCargasState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int l = 0; l < matrix.length; l++) {
            for (int c = 0; c < matrix.length; c++) {
                this.matrix[l][c] = matrix[l][c];
                //pode ou nao ser utilizado - o valor 1 representa o nosso montaCargas
                if (this.matrix[l][c] == 1) {
                    montaCargasRow = l;
                    montaCargasColumn = c;
                }
            }
        }

        gridObjects = new ArrayList<>();
        generateGridObjects();
    }

    public void setCurrentObject(int currentObject) {
        this.currentObject = currentObject;
    }

    public ArrayList<GridObject> getGridObjects() {
        return gridObjects;
    }


    public void generateGridObjects() {
        int[][] copy = new int[matrix.length][matrix.length];


        for (int l = 0; l < matrix.length; l++) {
            for (int c = 0; c < matrix.length; c++) {
                copy[l][c] = matrix[l][c];
            }
        }

        for (int l = 0; l < matrix.length; l++) {
            for (int c = 0; c < matrix.length; c++) {
                int cell = copy[l][c];
                if (cell == 1) {
                    gridObjects.add(new Forklift(l, c));
                } else if (cell != 0) {
                    Box box = new Box(l, c, cell);
                    gridObjects.add(box);
                    int boxSize = box.getSize();
                    Orientation boxOriention = box.getOrientation();

                    //consoante o tamanho e orientação das caixas vamos eliminando posicoes adjacentes onde surja o mm
                    //num de caixa, para que cd uma so conte uma vez
                    if (boxOriention.equals(Orientation.HORIZONTAL)) {
                        for (int i = 1; i < boxSize; i++) {
                            copy[l][c + i] = 0;
                        }
                    } else {
                        for (int i = 1; i < boxSize; i++) {
                            copy[l + i][c] = 0;
                        }
                    }

                    /*
                    switch (boxSize){
                        case 2:
                            if(boxOriention.equals(Orientation.HORIZONTAL)){
                                copy[l][c+1]= 0;
                            } else{
                                copy[l+1][c]=0;
                            }
                            break;
                        case 3:
                            if(boxOriention.equals(Orientation.HORIZONTAL)){
                                copy[l][c+1]= 0;
                                copy[l][c+2] = 0;
                            } else{
                                copy[l+1][c]=0;
                                copy[l+2][c]=0;
                            }
                            break;
                        case 4:
                            if(boxOriention.equals(Orientation.HORIZONTAL)){
                                copy[l][c+1] = 0;
                                copy[l][c+2] = 0;
                                copy[l][c+3] = 0;
                            } else{
                                copy[l+1][c]=0;
                                copy[l+2][c]=0;
                                copy[l+3][c]=0;
                            }
                    } */

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
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MontaCargasState)) {
            return false;
        }

        MontaCargasState state = (MontaCargasState) obj;

        if (this.matrix.length != state.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(this.matrix, state.matrix);

    }

    @Override
    protected Object clone() {
        return new MontaCargasState(this.matrix);
    }

    public int getNumRows() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix.length;
    }

    private transient ArrayList<MontaCargasListener> listeners = new ArrayList<>(3);

    private void firedPuzzleChanged(MontaCargasEvent event) {
        for (MontaCargasListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    public int getTileValue(int row, int col) {

        if (!isValidPosition(row, col)) {
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
        if (!this.listeners.contains(l)) {
            listeners.add(l);
        }

    }

    public synchronized void removeListener(MontaCargasListener l) {
        if (l != null && this.listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public boolean canMoveCurrentObjDown() {

        return canMoveCurrentObj(Orientation.HORIZONTAL, gridObjects.get(currentObject).getSize(), 0);

    }

    public boolean canMoveCurrentObjUp() {

        return canMoveCurrentObj(Orientation.VERTICAL, -1, 0);

    }

    public boolean canMoveCurrentObjRight() {

        return canMoveCurrentObj(Orientation.HORIZONTAL, 0, gridObjects.get(currentObject).getSize());

    }

    public boolean canMoveCurrentObjLeft() {

        return canMoveCurrentObj(Orientation.HORIZONTAL, 0, -1);

    }

    public boolean canMoveCurrentObj(Orientation orientation, int xToAdd, int yToAdd){
        //vai buscar o objecto que esta naquele indice da grid
        //e verifica a orientacao desse objecto
        GridObject object = gridObjects.get(currentObject);
        Orientation objectOrientation = object.getOrientation();

        //compara a orientacao do objecto com a orientacao recebida, se for a mesma
        //continua a verificar
        if (objectOrientation == orientation) {

            //vamos buscar a posicao do objecto antigo e fazemos uma nova posicao que é aquela para onde keremos mover
            //o X corresponde às linhas e o Y as colunas
            // o xToAdd e o YToAdd são a quantidade de linhas ou colunas que queremos andar na vertical ou na horizontal
            //cada um deles ja foi verificado nos metodos especificos (canMoveCurrentObjDown, CanmoveUpt etc.
            Point oldPosition = object.getPosition();
            Point potentialPosition = new Point(oldPosition.x + xToAdd, oldPosition.y + yToAdd);

            if(isValidPosition(potentialPosition.x, potentialPosition.y)){
                if(getTileValue(potentialPosition.x, potentialPosition.y) == 0){
                    return true;
                }
            }
        }

        return false;
    }

  /*  public void setTileValue(int value){

    }*/

    public void moveCurrentObjRight(){

        //precisamos sp do objectsize para kd chamamos o moveCurrentObj dizermos
        //em que extençao e em que direccao (+ ou -) vai ser feita a acçao
        int objectSize = gridObjects.get(currentObject).getSize();
        moveCurrentObj(0, 1, objectSize);

    }

    public void moveCurrentObjLeft(){

        int objectSize = gridObjects.get(currentObject).getSize();
        moveCurrentObj(0, -1, -objectSize);

    }

    public void moveCurrentObjDown(){

        int objectSize = gridObjects.get(currentObject).getSize();
        moveCurrentObj(1, 0, objectSize);

    }

    public void moveCurrentObjUp(){

        int objectSize = gridObjects.get(currentObject).getSize();
        moveCurrentObj(-1, 0, -objectSize);

    }

    public void moveCurrentObj(int xToAdd, int yToAdd, int toMove){

        GridObject object = gridObjects.get(currentObject);
        Point oldPosition = object.getPosition();

        Point newPosition = new Point(oldPosition.x + xToAdd, oldPosition.y + yToAdd);

        if (object.getOrientation() == Orientation.HORIZONTAL){
            //mudamos a 1a peça para a posicao a seguir a ultima
            matrix[oldPosition.x][oldPosition.y + toMove] = object.getObjectValue();
        } else {
            //mudamos a 1a peça para a posicao a seguir a ultima
            matrix[oldPosition.x + toMove][oldPosition.y] = object.getObjectValue();
        }

        //limpamos a posiçao anterior onde estava a peça antes de a movermos
        matrix[oldPosition.x][oldPosition.y] = 0;

        //dizemos ao objecto qual é agora a nova posicao dele
        object.setPosition(newPosition);

    }
}

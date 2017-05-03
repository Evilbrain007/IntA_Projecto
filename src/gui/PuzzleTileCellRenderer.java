package gui;

import utils.ImageLoader;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class PuzzleTileCellRenderer extends JLabel implements TableCellRenderer {

    private int doorLine = -1;
    private LinkedList<Point> boxPositions;

    public PuzzleTileCellRenderer(LinkedList<Point> boxPositions) {
       this.boxPositions = boxPositions;
       setBorder(null);
    }

    //o value é o valor que esta naquele sítio da matriz: se for 0, corresponde a imagem em branco, se for 2
    //é a imagem da carga 2, etc.
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus, int row,
            int column) {


        ImageLoader loader = ImageLoader.getLoader();

        //buscar o num de colunas da tabela para sabermos quando acaba a linha onde esta o montacargas
        int numCols = table.getColumnCount();

        //verificar se a row recebida é igual a doorline (que inicializámos com -1) e se
        //a coluna é a última coluna e, se for, colocamos la o icone da saida
        if(row==doorLine && column == numCols -1){
            setIcon(loader.getIcon(Properties.IMAGE_DOOR));
            return this;
        }


        int val = (Integer) value;

        switch (val){
            case 0:
                setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
                break;
            case 1:
                setIcon(loader.getIcon(Properties.IMAGE_FORKLIFT));
                //se o num recebido corresponde ao forklift, entao na ultima posicao desta linha temos k
                //por a door, por isso actualizamos o valor desta variavel
                doorLine = row;
                break;
            default: //se nao for branco nem forklift usamos um operador ternario para ver se o valor recebido é par
                //ou impar - se for par é horizontal, se for impar é vertical
                String imgOrientation = val%2==0 ? Properties.HORIZONTAL_IMAGE : Properties.VERTICAL_IMAGE;

                // devolve sempre a parte inteira da divisão - se der 1, corresponde à wall que nao
                //tem numero e devolvemos string vazia "", se nao, devolvemos o resultado da divisao
                //converito pa string
                String imgIndex = val/2==1 ? "" : val/2 + "";


                String alternate = "";
                for(Point position : this.boxPositions){
                    if(position.x==row && position.y==column && val!=2 && val!=3){
                        alternate = "_1";
                        break;
                    }
                }

                setIcon(loader.getIcon(Properties.IMAGE_PREFIX + imgOrientation +  imgIndex + alternate + Properties.IMAGE_SUFFIX));

        }

        return this;
    }
}

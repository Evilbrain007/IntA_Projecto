package gui;

import utils.ImageLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class PuzzleTileCellRenderer extends JLabel implements TableCellRenderer {

    public PuzzleTileCellRenderer() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Monospaced", Font.BOLD, 49));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus, int row,
            int column) {
        
        String text = ((Integer) value == 0) ? "" : value.toString();
        setText(text);




        /*LOADING EACH IMAGE WITH A SWITCH (not tested with all the boxes)*/
//        ImageLoader loader = ImageLoader.getLoader();
//        setText("");
//
//        int val = (Integer)value;
//
//        switch (val){
//            case 0:
//                setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
//                break;
//            case 1:
//                setIcon(loader.getIcon("forklift.png"));
//                break;
//            case 2:
//                setIcon(loader.getIcon("wallH.png"));
//                break;
//            case 3:
//                setIcon(loader.getIcon("wallV.png"));
//                break;
//            case 4:
//                setIcon(loader.getIcon("wallH2.png"));
//                break;
//            case 5:
//                setIcon(loader.getIcon("wallV2.png"));
//                break;
//            case 6:
//                setIcon(loader.getIcon("wallH3.png"));
//                break;
//            case 7:
//                setIcon(loader.getIcon("wallV3.png"));
//                break;
//            case 8:
//                setIcon(loader.getIcon("wallH4.png"));
//                break;
//            case 9:
//                setIcon(loader.getIcon("wallV4.png"));
//                break;
//        }


        /*LOADING EACH IMAGE WITH AN ALGORITM*/
//        ImageLoader loader = ImageLoader.getLoader();
//        setText("");
//
//        int val = (Integer)value;
//        if (val == 0) {
//            setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
//        } else if (val == 1){
//            setIcon(loader.getIcon("forklift.png"));
//        }else {
//            String orientation = val % 2 == 0 ? "H" : "V";
//            String imageIndex = val / 2 == 1 ? "" : val/2 + "";
//
//            setIcon(loader.getIcon("wall"+orientation+imageIndex+".png"));
//        }


/*
        if (((Integer) value).intValue() == 0) {
            setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
        } else {
            setIcon(loader.getIcon(Properties.IMAGE_PREFIX + ((Integer) value).intValue() + Properties.IMAGE_SUFFIX));
        }*/

        return this;
    }
}

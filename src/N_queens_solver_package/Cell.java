package N_queens_solver_package;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell extends JLabel{
    int ImageSize = 0;

    Cell (int color , boolean Queen , int ImageSize){
        
        this.ImageSize = ImageSize;
        
        //this move to resize the image White Queen
        ImageIcon WhiteQueen = new ImageIcon("Queen 1 NLB.jpg"); //size 49*100
        Image WhiteQueen_1 = WhiteQueen.getImage();// we need to make an imageicon then you getimage so that this one can work
        Image WhiteQueen_2 = WhiteQueen_1.getScaledInstance(ImageSize, ImageSize, java.awt.Image.SCALE_SMOOTH);
        WhiteQueen.setImage(WhiteQueen_2);
        
        //this move to resize the image of Black Queen
        ImageIcon BlackQueen = new ImageIcon("Queen 1 NDB.jpg"); //size 49*100
        Image BlackQueen_1 = BlackQueen.getImage();// we need to make an imageicon then you getimage so that this one can work
        Image BlackQueen_2 = BlackQueen_1.getScaledInstance(ImageSize, ImageSize, java.awt.Image.SCALE_SMOOTH);
        BlackQueen.setImage(BlackQueen_2);
        
        switch (color){
            case 0: // white block
                if(Queen){ // see if it has Queen or not
                    WhiteQueen(WhiteQueen);
                }
                else{
                    WhiteBlock();
                }
                break;
            case 1: // Black block
                if(Queen){  // see if it has Queen or not
                    BlackQueen(BlackQueen);
                }
                else{
                    BlackBlock();
                }
        }
    }
    private void WhiteQueen (ImageIcon WhiteQueen){
        this.setIcon(WhiteQueen);
        this.setOpaque(true);
    }
    private void WhiteBlock (){
        this.setOpaque(true);
        this.setBackground(new Color(0xf9e4b8));
    }
    private void BlackQueen (ImageIcon BlackQueen){
        this.setIcon(BlackQueen);
        this.setOpaque(true);
    }
    private void BlackBlock (){
        this.setOpaque(true);
        this.setBackground(new Color(0x9e623e));
    }
}
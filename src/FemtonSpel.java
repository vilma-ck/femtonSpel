import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-22
 * Project: femtonSpel
 * Copyright: MIT
 */
public class FemtonSpel extends JFrame {

    private JPanel panel = new JPanel();
    private List<JButton> buttonsOrdered = new ArrayList<>();
    private List<JButton> buttonsGame = new ArrayList<>();

    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton b4 = new JButton("4");
    private JButton b5 = new JButton("5");
    private JButton b6 = new JButton("6");
    private JButton b7 = new JButton("7");
    private JButton b8 = new JButton("8");
    private JButton b9 = new JButton("9");
    private JButton b10 = new JButton("10");
    private JButton b11 = new JButton("11");
    private JButton b12 = new JButton("12");
    private JButton b13 = new JButton("13");
    private JButton b14 = new JButton("14");
    private JButton b15 = new JButton("15");
    private JButton bNull = new JButton();

    // testcomment

    public FemtonSpel(){
        buttonsGame.add(b1); buttonsGame.add(b2); buttonsGame.add(b3); buttonsGame.add(b4); buttonsGame.add(b5);
        buttonsGame.add(b6); buttonsGame.add(b7); buttonsGame.add(b8); buttonsGame.add(b9); buttonsGame.add(b10);
        buttonsGame.add(b11); buttonsGame.add(b12); buttonsGame.add(b13); buttonsGame.add(b14); buttonsGame.add(b15);
        buttonsGame.add(bNull);

        //makeButtons();

        add(panel);

        panel.setLayout(new GridLayout(4,4));

        Collections.shuffle(buttonsGame);
        for(JButton button : buttonsGame){
            panel.add(button);
            button.addMouseListener(myMouseListener);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }

    public void makeButtons(){
        for (int i = 1; i < 16; i++) {
            buttonsOrdered.add(new JButton(String.valueOf(i)));
            buttonsGame.add(new JButton(String.valueOf(i)));
            //System.out.println(i);
        }
    }

    MouseAdapter myMouseListener = new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {

        }
    };


        public static void main(String[] args) {
        FemtonSpel fs = new FemtonSpel();
    }

}



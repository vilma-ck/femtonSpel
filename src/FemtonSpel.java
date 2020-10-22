import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-10-22
 * Project: femtonSpel
 * Copyright: MIT
 */
public class FemtonSpel extends JFrame{

    private JPanel panel = new JPanel();
    private List<JButton> buttonsOrdered = new ArrayList<>();
    private List<JButton> buttonsGame = new ArrayList<>();

    // testcomment

    public FemtonSpel(){
        makeButtons();

        add(panel);

        panel.setLayout(new GridLayout(4,4));

        Collections.shuffle(buttonsGame);
        for(JButton button : buttonsGame){
            panel.add(button);
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

    public static void main(String[] args) {
        FemtonSpel fs = new FemtonSpel();
    }

}
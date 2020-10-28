import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vilma Couturier Kaijser och Lisa Ramel
 * Date: 2020-10-22
 * Project: femtonSpel
 * Copyright: MIT
 */
public class FemtonSpel extends JFrame {

    private JPanel panel = new JPanel();
    //private JPanel southPanel = new JPanel(new GridLayout(1, 2));
    private JPanel northPanel = new JPanel();
    private List<JButton> buttonsGame = new ArrayList<>();
    private JLabel outputText = new JLabel();
    private JButton newGame = new JButton("Spela igen!");

    public FemtonSpel(){
        add(newGame, BorderLayout.SOUTH);

        add(panel);
        add(newGame, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        panel.setLayout(new GridLayout(4,4));

        //southPanel.add(newGame);

        northPanel.add(outputText);

        makeButtons();
        shuffleButtons();
        addButtonListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    public void makeButtons(){
        for (int i = 1; i < 16; i++) {
            buttonsGame.add(new JButton(String.valueOf(i)));
        }
        buttonsGame.add(new JButton(" "));
    }

    public void shuffleButtons(){
        Collections.shuffle(buttonsGame);
        //panel.removeAll();
        //northPanel.removeAll();
        for (JButton button : buttonsGame) {
            panel.add(button);
        }
        panel.revalidate();
        panel.repaint();

        outputText.setText("Välkommen!");
        //northPanel.add(outputText);
    }

    public void addButtonListeners(){

        ActionListener l = new MoveButtonListener();
        ActionListener l2 = new NewGameListener();

        for(JButton b : buttonsGame){
            b.addActionListener(l);
        }
        newGame.addActionListener(l2);
    }

    class MoveButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int indexCounter = 0;
            int index = -1;
            for(JButton button: buttonsGame){
                if(actionEvent.getSource() == button){
                    index = indexCounter;
                }
                indexCounter ++;
            }

            if(besideEmpty(index)!= 0){
                int indexForNull = index + besideEmpty(index);
                switchPlace(index, indexForNull);
            }

            checkOrder();

        }
    }

    class NewGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
                shuffleButtons();
        }
    }

    public void checkOrder(){
        int buttonsInOrderCounter = 0;
        for (int i = 0; i < buttonsGame.size()-1; i++) {

            if(buttonsGame.get(i + 1) != null){
                if(!buttonsGame.get(i).getText().equals(" ") && !buttonsGame.get(i + 1).getText().equals(" ")){
                    if(Integer.parseInt(buttonsGame.get(i).getText())+1 == Integer.parseInt(buttonsGame.get(i+1).getText())){
                        buttonsInOrderCounter++;
                    }
                }
            }
        }
        if(buttonsInOrderCounter == 14){
            youWonPrintOut();
        }
        else
            tryAgain();
    }

    public void switchPlace(int index, int indexForNull){
        buttonsGame.get(indexForNull).setText(buttonsGame.get(index).getText());
        buttonsGame.get(index).setText(" ");

        panel.revalidate();
        panel.repaint();
    }

    public int besideEmpty(int index){

        if(index%4 != 0){
            if(buttonsGame.get(index-1).getText().equals(" ")){
                return -1;
            }
        }

        if (index%4 != 3){
            if(buttonsGame.get(index+1).getText().equals(" ")){
                return +1;
            }
        }

        if(index > 3){
            if(buttonsGame.get(index-4).getText().equals(" ")){
                return -4;
            }
        }

        if(index < 12){
            if(buttonsGame.get(index+4).getText().equals(" ")){
                return +4;
            }
        }

        return 0;
    }

    public void youWonPrintOut(){
        outputText.setText("Grattis du vann!");
    }

    public void tryAgain(){
        outputText.setText("Inte rätt ordning än.");
    }

    public static void main(String[] args) {
        FemtonSpel fs = new FemtonSpel();
    }

}



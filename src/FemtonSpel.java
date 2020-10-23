import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel southPanel = new JPanel(new GridLayout(1, 2));
    private JPanel northPanel = new JPanel();
    private List<JButton> buttonsGame = new ArrayList<>();
    private JLabel outputText = new JLabel();

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
    private JButton bNull = new JButton(" ");
    private JButton newGame = new JButton("Spela igen!");
    private JButton checkOrder = new JButton("Kolla ordning");

    public FemtonSpel(){
        buttonsGame.add(b1); buttonsGame.add(b2); buttonsGame.add(b3); buttonsGame.add(b4); buttonsGame.add(b5);
        buttonsGame.add(b6); buttonsGame.add(b7); buttonsGame.add(b8); buttonsGame.add(b9); buttonsGame.add(b10);
        buttonsGame.add(b11); buttonsGame.add(b12); buttonsGame.add(b13); buttonsGame.add(b14); buttonsGame.add(b15);
        buttonsGame.add(bNull);

        add(newGame, BorderLayout.SOUTH);

        add(panel);
        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        panel.setLayout(new GridLayout(4,4));

        southPanel.add(newGame);
        southPanel.add(checkOrder);

        northPanel.add(outputText);

        shuffleButtons();

        addButtonListeners();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void shuffleButtons(){
        Collections.shuffle(buttonsGame);
        panel.removeAll();
        northPanel.removeAll();
        for (JButton button : buttonsGame) {
            panel.add(button);
        }
        panel.revalidate();
        panel.repaint();

        outputText.setText("Välkommen!");
        northPanel.add(outputText);
    }

    public void addButtonListeners(){

        ActionListener l = new MoveButtonListener();
        ActionListener l2 = new NewGameListener();
        ActionListener l3 = new CheckOrderListener();

        for(JButton b : buttonsGame){
            b.addActionListener(l);
        }

        newGame.addActionListener(l2);

        checkOrder.addActionListener(l3);

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

            //System.out.println("index " + index);

            if(besideEmpty(index)!= 0){
                int indexForNull = index + besideEmpty(index);
                //System.out.println("index knapp: " + index);
                //System.out.println("index null: " + indexForNull);

                switchPlace(index, indexForNull);
            }

            //System.out.println("besideEmpty: " + besideEmpty(index));

        }
    }

    class NewGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){

                shuffleButtons();

        }
    }

    class CheckOrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            int buttonsInOrderCounter = 0;
            for (int i = 0; i < buttonsGame.size()-1; i++) {

                if(buttonsGame.get(i + 1) != null){
                    if(!buttonsGame.get(i).getText().equals(" ") && !buttonsGame.get(i + 1).getText().equals(" ")){
                        //System.out.println(buttonsGame.get(i).getText());

                        if(Integer.parseInt(buttonsGame.get(i).getText())+1 == Integer.parseInt(buttonsGame.get(i+1).getText())){
                            //System.out.println("knappen " + buttonsGame.get(i).getText() + " är före " + buttonsGame.get(i+1).getText());
                            buttonsInOrderCounter++;
                        }
                    }
                }
            }
            if(buttonsInOrderCounter == 14){
                //System.out.println("alla knappar var iordning!!");
                youWonPrintOut();
            }
            else
                tryAgain();
        }
    }

    public void switchPlace(int index, int indexForNull){
        buttonsGame.get(indexForNull).setText(buttonsGame.get(index).getText());
        buttonsGame.get(index).setText(" ");

        panel.revalidate();
        panel.repaint();
    }

    public int besideEmpty(int index){

        if(index%4 != 0){
            // vi kollar index-1
            if(buttonsGame.get(index-1).getText().equals(" ")){
                return -1;
            }
        }

        if (index%4 != 3){
            // vi kollar index+1
            if(buttonsGame.get(index+1).getText().equals(" ")){
                return +1;
            }
        }

        if(index > 3){
            // vi kollar index-4
            if(buttonsGame.get(index-4).getText().equals(" ")){
                return -4;
            }
        }

        if(index < 12){
            // vi kollar index+4
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
        outputText.setText("Fel ordning. Försök igen!");
    }

    public static void main(String[] args) {
        FemtonSpel fs = new FemtonSpel();
    }

}



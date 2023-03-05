import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener{

    GenerateNumber generateNumber = new GenerateNumber();
   
    String sbmtVal = "Submit";
    int chancesLeft = 3;

    JLabel bg, label, eLabel;
    JTextField userInput;
    JButton sbmt;

    public GameGUI() {
    
        setSize(500, 500);
        setTitle("Guess the Number - Game");
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //for background (Resuzable hence image and imageIcon both are used)
        ImageIcon imgIcon = new ImageIcon("GuessTheNumber/src/resources/bg.jpg");
        Image img = imgIcon.getImage();
        Image imgNew = img.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(imgNew);
        setContentPane(new JLabel(imgIcon));
        setLayout(null);


        //for label
        label = new JLabel("Guess a number between 0-99");
        label.setBounds(125,170, 250, 20);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.white);
        add(label);

        //user input text field
        userInput = new JTextField(null);
        userInput.setBounds(200, 205, 80, 18);
        userInput.setHorizontalAlignment(JTextField.CENTER);
        add(userInput);

        //error label
        eLabel = new JLabel("");
        eLabel.setBounds(150,225, 200, 20);
        eLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        eLabel.setForeground(Color.yellow);
        add(eLabel);


        //only allows numeric data to be entered (till 99) or backspaces
        userInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && userInput.getText().length() < 2) || e.getKeyChar() == KeyEvent.VK_BACK_SPACE ) {
                    userInput.setEditable(true);
                    eLabel.setText("");
                } else {
                    userInput.setEditable(false);
                    eLabel.setText("* Enter 1 or 2 digit numbers only");
                }

                if(sbmtVal == "Replay"){
                    userInput.setEditable(false);
                    eLabel.setText("Click on replay to play");
                }
        
            }
         });
   
        //submit button
        sbmt = new JButton(sbmtVal);
        sbmt.setBounds(190,250, 100, 30);
        sbmt.setBackground(Color.CYAN);
       
        sbmt.addActionListener(this);
        add(sbmt);

        setVisible(true);

    }
    
    //when user wins
    public void winWindow(){
        JOptionPane.showMessageDialog(this, "You win");
        userInput.setText("");
        sbmtVal = "Replay";
        sbmt.setText(sbmtVal);
    }

    //for wrong answers
    public void loseWindow(){

        String text = "";

        if(chancesLeft > 1){
            chancesLeft--;
            text = "\nChances left: "+chancesLeft;
        }

        else{
            sbmtVal = "Replay";
            text = "\nNumber was: " + generateNumber.numberGenerated;
            sbmt.setText(sbmtVal);
        }

        JOptionPane.showMessageDialog(this, "You lose"+text);
        userInput.setText("");

    }

    //submitting without input
    public void noValueWindow(){
        JOptionPane.showMessageDialog(this, "Please enter a number");
    }



    @Override
    public void actionPerformed(ActionEvent e) {
             
        if(e.getSource() == sbmt){
    
            String s = userInput.getText();

            if(sbmtVal == "Submit"){
                if(s.isEmpty()){
                    noValueWindow();
                }
                else if(generateNumber.numberGenerated == Integer.parseInt(userInput.getText())){
                    winWindow();
                }
                else{
                    loseWindow();
                }
            }
            else{
                sbmtVal = "Submit";
                sbmt.setText(sbmtVal);

                chancesLeft = 3;
                generateNumber.numberGenerated = generateNumber.randomNumber();

            }
        }
    
    }

}

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class ButtonLogic {

    private static String num1="", num2="";
    private static String operationChar;


    private final JTextField TEXT_FIELD;
    private JButton addButton, subButton, multButton, divButton, eqButton;
    public JButton [] numButtons;
    private JButton delButton, clrButton, signButton, dotButton;

    private JButton[] funcButotns;


    public ButtonLogic(Dimension size, Font font, JTextField textField){
        this.TEXT_FIELD = textField;
        makeButtons();
        setButtonsAppearance(font,size);
        makeActionListeners();
        makeKeyListeners();
        numButtonsFunc();
    }

    private void makeButtons(){
        numButtons = new JButton[10];
        for(int i = 0; i<10;i++){
            numButtons[i] = new JButton(String.valueOf(i));
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        divButton = new JButton("/");
        multButton = new JButton("*");
        eqButton = new JButton("=");
        dotButton = new JButton(".");
        signButton = new JButton("+/-");


        delButton = new JButton("del");
        clrButton = new JButton("clr");

        funcButotns = new JButton[]{addButton,subButton,multButton,divButton,
                eqButton,signButton,dotButton};
    }

    private void setButtonsAppearance(Font font, Dimension size){

        for(JButton button:numButtons){
            button.setSize(size);
            button.setFont(font);
            button.setFocusable(false);
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
        }
        for(JButton button: funcButotns){
            button.setSize(size);
            button.setFont(font);
            button.setFocusable(false);
            button.setBackground(Color.GRAY);
            button.setForeground(Color.WHITE);
        }

        delButton.setFont(font);
        delButton.setBounds(5,365,185,size.height);
        delButton.setFocusable(false);
        delButton.setBackground(Color.GRAY);
        delButton.setForeground(Color.WHITE);

        clrButton.setFont(font);
        clrButton.setBounds(195,365,185,size.height);
        clrButton.setFocusable(false);
        clrButton.setBackground(Color.GRAY);
        clrButton.setForeground(Color.WHITE);
    }

    private void numButtonsFunc() {

        for (JButton button : numButtons) {
            button.addActionListener(e -> {
                if(Objects.equals(TEXT_FIELD.getText(), "0")){
                    TEXT_FIELD.setText("");
                }
                TEXT_FIELD.setText(TEXT_FIELD.getText().concat(button.getText()));
            });
        }
    }
    private void makeKeyListeners(){
        TEXT_FIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(c == KeyEvent.VK_BACK_SPACE){
                    delButton.doClick();
                }else if(c == KeyEvent.VK_ENTER || c == '=') {
                    eqButton.doClick();
                }
                else if(!Character.isDigit(c)){
                    switch (c){
                        case '+' -> addButton.doClick();
                        case '-' -> subButton.doClick();
                        case '*' -> multButton.doClick();
                        case '/' -> divButton.doClick();
                        case 'c' ->clrButton.doClick();
                        case '.' -> dotButton.doClick();
                        default -> e.consume();
                    }
                }else{
                    int index = Integer.parseInt(String.valueOf(c));
                    numButtons[index].doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JButton[] getButtons(){
        JButton [] allButtons = new JButton[numButtons.length+funcButotns.length];
        System.arraycopy(numButtons, 0, allButtons, 0, 10);
        System.arraycopy(funcButotns, 0, allButtons, 10, 7);
        return allButtons;
    }
    public JButton[] get2Buttons(){
        return new JButton[] {delButton,clrButton};
    }

    private void makeActionListeners(){

        makeNumButtonsActionListener();
        makeEqButtonActionListener();
        makeClrButtonActionListener();
        makeDelButtonActionListener();
        makeDotButtonActionListener();
        makeSignButtonActionListener();
    }

    private void makeEqButtonActionListener(){
        eqButton.addActionListener(e -> {
            if(!TEXT_FIELD.getText().isEmpty()){
                num2 = TEXT_FIELD.getText();
                Calculator.textField2.getTextField().setText("");
                if(!num2.isEmpty() && !num1.isEmpty()) {
                    switch (operationChar) {
                        case "+" -> TEXT_FIELD.setText(String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2)));
                        case "-" -> TEXT_FIELD.setText(String.valueOf(Double.parseDouble(num1) - Double.parseDouble(num2)));
                        case "*" -> TEXT_FIELD.setText(String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2)));
                        case "/" -> {
                            if (Double.parseDouble(num2) != 0) {
                                TEXT_FIELD.setText(String.valueOf(Double.parseDouble(num1) / Double.parseDouble(num2)));
                            } else {
                                TEXT_FIELD.setText("");
                            }
                        }
                    }
                }
            }
        });
    }
    private void makeNumButtonsActionListener(){
        for(int i = 0;i<4;i++){
            int finalI = i;
            funcButotns[i].addActionListener(e -> {
                num1 = TEXT_FIELD.getText();
                Calculator.textField2.getTextField().setText(" "+funcButotns[finalI].getText());
                operationChar =  funcButotns[finalI].getText();
                TEXT_FIELD.setText("");
            });
        }
    }
    private void makeClrButtonActionListener(){
        clrButton.addActionListener(e -> {
            TEXT_FIELD.setText("");
            Calculator.textField2.getTextField().setText("");
        });
    }
    private void makeDelButtonActionListener(){
        delButton.addActionListener(e -> {
            if(!TEXT_FIELD.getText().isEmpty()){
                String text = TEXT_FIELD.getText().substring(0, TEXT_FIELD.getText().length()-1);
                Calculator.textField2.getTextField().setText("");
                TEXT_FIELD.setText(text);
            }
        });
    }
    private void makeDotButtonActionListener(){
        dotButton.addActionListener(e -> {
            if(!TEXT_FIELD.getText().contains(".")){
                TEXT_FIELD.setText(TEXT_FIELD.getText().concat("."));
            }
        });
    }
    private void makeSignButtonActionListener(){
        signButton.addActionListener(e -> {
            String text = TEXT_FIELD.getText();
            if(!text.isEmpty()){
                String changedText;
                if(text.charAt(0)=='-'){
                    changedText=text.substring(1);
                }else{
                    changedText = "-".concat(text);
                }
                TEXT_FIELD.setText(changedText);
            }
        });
    }
}

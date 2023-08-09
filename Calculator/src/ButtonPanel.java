import javax.swing.*;
import java.awt.*;


public class ButtonPanel {

    private JPanel panel;

    public ButtonPanel(JButton[] calcButton){
        makePanel();
        setPanel();
        addButtons(calcButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    private void makePanel(){
        panel = new JPanel();
    }

    private void setPanel(){
        panel.setLayout(new GridLayout(4,4,5,5));
        panel.setBounds(5,60,375,300);
    }

    private void addButtons(JButton[] calcButton){

        for(int i = 1; i<4;i++) panel.add(calcButton[i]);
        panel.add(calcButton[10]);
        for(int i = 4; i<7;i++) panel.add(calcButton[i]);
        panel.add(calcButton[11]);
        for(int i = 7; i<10;i++) panel.add(calcButton[i]);
        panel.add(calcButton[12]);
        panel.add(calcButton[15]);
        panel.add(calcButton[0]);
        panel.add(calcButton[16]);
        panel.add(calcButton[13]);

    }
}

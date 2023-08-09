import javax.swing.*;
import java.awt.*;

public class Display {

    public JTextField textField;

    public Display(Font font, Dimension size){
        makeTextField();
        setTextField(font,size);
    }

    public JTextField getTextField() {
        return textField;
    }

    private void makeTextField(){
        textField = new JTextField("");
    }

    private void setTextField(Font font, Dimension size){
        textField.setFont(font);
        textField.setEditable(false);
        textField.setSize(size);
        textField.setBounds(5,5,size.width,size.height);
        textField.setForeground(Color.white);
        textField.setBackground(Color.DARK_GRAY);
    }

}

import javax.swing.*;
import java.awt.*;

public class Calculator {

    private static final Font FONT = new Font("Arial", Font.BOLD,30);
    private static final Dimension TEXT_FIELD_SIZE = new Dimension(235,50);
    private static final Dimension BUTTON_SIZE = new Dimension(50,50);
    private static final Dimension SIZE_OF_WINDOW = new Dimension(400,460);

    public static Display textField2=new Display(FONT, TEXT_FIELD_SIZE);
    private JFrame window;

    public Calculator(){
        initialize();
        addComponents();
    }

    private void initialize(){
        window = new JFrame("Calculator");
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(SIZE_OF_WINDOW);
        window.setPreferredSize(SIZE_OF_WINDOW);

    }

    private void addComponents(){
        JTextField textField = addTextFields();
        addButtons(textField);

        window.pack();
    }


    private JTextField addTextFields(){
        Display textField = new Display(FONT, TEXT_FIELD_SIZE);
        window.add(textField.getTextField());

        textField2.getTextField().setBounds(240,5,45,50);
        textField2.getTextField().setBackground(Color.GRAY);
        textField2.getTextField().setEditable(false);
        window.add(textField2.getTextField());
        return textField.getTextField();
    }

    private void addButtons(JTextField textField){
        ButtonLogic buttonLogic = new ButtonLogic(BUTTON_SIZE,FONT,textField);
        JButton[] buttons = buttonLogic.getButtons();

        JButton eqButton = buttons[14];
        eqButton.setBounds(290,5,90,50);
        window.add(eqButton);

        ButtonPanel buttonPanel = new ButtonPanel(buttons);
        window.add(buttonPanel.getPanel());

        JButton[] twoButtons = buttonLogic.get2Buttons();
        window.add(twoButtons[0]);
        window.add(twoButtons[1]);
    }

    public void show(){
        window.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.show();

        });

    }
}

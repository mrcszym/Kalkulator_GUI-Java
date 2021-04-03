import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kalkulator implements ActionListener, KeyListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("TimesRoman", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Kalkulator() {

        frame = new JFrame("Prosty kalkulator");
        // poniżej oczywiście należy zmienić ścieżkę
        Image icon = Toolkit.getDefaultToolkit()
                .getImage("C:/Source/Repos/Kalkulator-java/Kalkulator/Kalkulator_GUI-Java/img/logo.png");
        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.getIconImage();
        frame.addKeyListener(this);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        // frame.requestFocusInWindow();

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.DARK_GRAY);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("del");
        clrButton = new JButton("clr");
        negButton = new JButton("neg");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(Color.LIGHT_GRAY);
            functionButtons[i].setForeground(Color.BLACK);
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.WHITE);
            numberButtons[i].setForeground(Color.BLACK);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        delButton.setBackground(Color.RED);
        delButton.setForeground(Color.WHITE);
        clrButton.setBounds(250, 430, 100, 50);
        equButton.setBackground(Color.GREEN);
        equButton.setForeground(Color.WHITE);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.DARK_GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Kalkulator kalk = new Kalkulator();
    }

    public static double doingCalc(char operator, double result, double num1, double num2) {

        switch (operator) {
        case '+':
            result = num1 + num2;
            break;
        case '-':
            result = num1 - num2;
            break;
        case '*':
            result = num1 * num2;
            break;
        case '/':
            result = num1 / num2;
            break;
        }
        System.out.println("--------------------\nwynik: " + result);

        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionEvent (button)");

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        } else if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        } else if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        } else if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        } else if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        else if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());
            textField.setText(String.valueOf(doingCalc(operator, result, num1, num2)));
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++)
                textField.setText(textField.getText() + string.charAt(i));

        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        int numKey = e.getKeyChar();

        if (numKey == 10) {
            System.out.println("keyEvent (equals)");
            equButton.doClick();
        } else if (numKey == 8) {
            System.out.println("keyEvent (del)");
            delButton.doClick();
        }

        else if (numKey >= 48 && numKey <= 57) {
            int number = numKey - 48;
            System.out.println("keyEvent (number)");
            numberButtons[number].doClick();
        }

        else {
            operator = e.getKeyChar();
            switch (operator) {
            case ',':
                textField.setText(textField.getText().concat("."));
                break;
            case '+':
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
                break;
            case '-':
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
                break;
            case '*':
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
                break;
            case '/':
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
                break;
            default:
                System.out.println("wrong key");
            }
            System.out.println("keyEvent (operator)");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
package projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator {
    private JPanel rootPanel;
    private JTextField lengthTextField;
    private JTextField PathTextField;
    private JRadioButton zapisNaEkranRadioButton;
    private JRadioButton zapisDoPlikuRadioButton;
    private JTextField Lsfr1LengthTextField;
    private JTextField Lfsr1StartTextField;
    private JTextField Lfsr3StartTextField;
    private JTextField Lfsr2StartTextField;
    private JTextField Lfsr2LengthTextField;
    private JTextField Lfsr3LengthTextField;
    private JTextArea genarated;
    private JButton button;
    private JTextField Funkcja1TextField;
    private JTextField funkcja2TextField;
    private JTextField funkcja3TextField;

    public Generator(){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Generate g = new Generate();
                ArrayList<Integer> r1 = new ArrayList<>();
                ArrayList<Integer> r2 = new ArrayList<>();
                ArrayList<Integer> r3 = new ArrayList<>();
                genarated.setText(null);
                if(Lfsr1StartTextField.getText().length()!=Integer.parseInt(Lsfr1LengthTextField.getText())){
                    genarated.setText("LFSR1 - dlugosc musi byc rowna wartosci startowej!");
                    return;
                }
                else if(Lfsr2StartTextField.getText().length()!=Integer.parseInt(Lfsr2LengthTextField.getText())){
                    genarated.setText("LFSR2 - dlugosc musi byc rowna wartosci startowej!");
                    return;
                }
                else if(Lfsr3StartTextField.getText().length()!=Integer.parseInt(Lfsr3LengthTextField.getText())){
                    genarated.setText("LFSR3 - dlugosc musi byc rowna wartosci startowej!");
                    return;
                }
                String funkcja1 = Funkcja1TextField.getText();
                String funkcja2 = funkcja2TextField.getText();
                String funkcja3 = funkcja3TextField.getText();
                StringBuilder helper = new StringBuilder();
                boolean gate = false;
                for (int i = 0; i < funkcja1.length(); i++) {
                    char charr = funkcja1.charAt(i);
                    if (Character.isDigit(charr)) {
                        if (i == 0) {
                        } else if (funkcja1.charAt(i - 1) == 'x' || funkcja1.charAt(i - 1) == 'X') {
                            if(Integer.parseInt(String.valueOf(charr))>=Integer.parseInt(Lsfr1LengthTextField.getText())){
                                genarated.setText("LFSR1 - Funkcja nie pasuje do dlugosci rejestru!");
                                return;
                            }
                            gate = true;
                        }
                        helper.append(charr);
                    }
                    if (charr == 'x' || charr == 'X') {
                        if (Integer.parseInt(Lsfr1LengthTextField.getText())<=1){
                            genarated.setText("LFSR1 - Funkcja nie pasuje do dlugosci rejestru!");
                            return;
                        }
                        if (i == funkcja1.length() - 1) r1.add(1);
                        else if (!Character.isDigit(funkcja1.charAt(i + 1))) r1.add(1);
                    }
                    if (charr == '+') {
                        if (helper.length() > 0) {
                            if (gate) r1.add(Integer.parseInt(helper.toString()));
                            else g.setFunctionValue(Integer.parseInt(helper.toString()));
                        }
                        gate = false;
                        helper.setLength(0);
                    }
                }
                gate = false;
                helper.setLength(0);
                for (int i = 0; i < funkcja2.length(); i++) {
                    char charr = funkcja2.charAt(i);
                    if (Character.isDigit(charr)) {
                        if (i == 0) {
                        } else if (funkcja2.charAt(i - 1) == 'x' || funkcja2.charAt(i - 1) == 'X') {
                            if(Integer.parseInt(String.valueOf(charr))>=Integer.parseInt(Lfsr2LengthTextField.getText())){
                                genarated.setText("LFSR2 - Funkcja nie pasuje do dlugosci rejestru!");
                                return;
                            }
                            gate = true;
                        }
                        helper.append(charr);
                    }
                    if (charr == 'x' || charr == 'X') {
                        if (Integer.parseInt(Lfsr2LengthTextField.getText())<=1){
                            genarated.setText("LFSR2 - Funkcja nie pasuje do dlugosci rejestru!");
                            return;
                        }
                        if (i == funkcja2.length() - 1) r2.add(1);
                        else if (!Character.isDigit(funkcja2.charAt(i + 1))) r2.add(1);
                    }
                    if (charr == '+') {
                        if (helper.length() > 0) {
                            if (gate) r2.add(Integer.parseInt(helper.toString()));
                            else g.setFunction2Value(Integer.parseInt(helper.toString()));
                        }
                        gate = false;
                        helper.setLength(0);
                    }
                }
                gate = false;
                helper.setLength(0);
                for (int i = 0; i < funkcja3.length(); i++) {
                    char charr = funkcja3.charAt(i);
                    if (Character.isDigit(charr)) {
                        if (i == 0) {
                        } else if (funkcja3.charAt(i - 1) == 'x' || funkcja3.charAt(i - 1) == 'X') {
                            if(Integer.parseInt(String.valueOf(charr))>=Integer.parseInt(Lfsr3LengthTextField.getText())){
                                genarated.setText("LFSR2 - Funkcja nie pasuje do dlugosci rejestru!");
                                return;
                            }
                            gate = true;
                        }
                        helper.append(charr);
                    }
                    if (charr == 'x' || charr == 'X') {
                        if (Integer.parseInt(Lfsr3LengthTextField.getText())<=1){
                            genarated.setText("LFSR3 - Funkcja nie pasuje do dlugosci rejestru!");
                            return;
                        }
                        if (i == funkcja3.length() - 1) r3.add(1);
                        else if (!Character.isDigit(funkcja3.charAt(i + 1))) r3.add(1);
                    }
                    if (charr == '+') {
                        if (helper.length() > 0) {
                            if (gate) r3.add(Integer.parseInt(helper.toString()));
                            else g.setFunction3Value(Integer.parseInt(helper.toString()));
                        }
                        gate = false;
                        helper.setLength(0);
                    }
                }
                g.setRegister_1_gates(r1);
                g.setRegister_2_gates(r2);
                g.setRegister_3_gates(r3);
                g.setRegister_1_length(Integer.parseInt(Lsfr1LengthTextField.getText()));
                g.setRegister_2_length(Integer.parseInt(Lfsr2LengthTextField.getText()));
                g.setRegister_3_length((Integer.parseInt(Lfsr3LengthTextField.getText())));
                g.setRegister_1_start(Lfsr1StartTextField.getText());
                g.setRegister_2_start(Lfsr2StartTextField.getText());
                g.setRegister_3_start(Lfsr3StartTextField.getText());
                g.setFunctionValue(1);
                g.setFunction2Value(1);
                g.setFunction3Value(1);
                String result = g.generate(Integer.parseInt(lengthTextField.getText()));
                if (zapisNaEkranRadioButton.isSelected()) {
                    genarated.setText(result);
                } else if (zapisDoPlikuRadioButton.isSelected()) {
                    PrintWriter printWriter = null;
                    try {
                        printWriter = new PrintWriter(PathTextField.getText());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    assert printWriter != null;
                    printWriter.println(Funkcja1TextField.getText());
                    printWriter.println(Lsfr1LengthTextField.getText());
                    printWriter.println(funkcja2TextField.getText());
                    printWriter.println(Lfsr2LengthTextField.getText());
                    printWriter.println(funkcja3TextField.getText());
                    printWriter.println(Lfsr3LengthTextField.getText());
                    printWriter.println(result);
                    printWriter.close();
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Generator");
        frame.setContentPane(new Generator().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

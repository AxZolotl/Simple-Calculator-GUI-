import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    
    JPanel btnpanel = new JPanel();

    String[] symbols = 
    {"C", "+/-", "/", "DEL",
    "*", "-", "+", "%", "."};

    JButton[] funcbuttons = new JButton[7];
    JButton[] numbuttons = new JButton[10];
    JButton[] otherbuttons = new JButton[2];
    JButton equal = new JButton("=");

    JTextField output1 = new JTextField();
    JTextField output2 = new JTextField("0");

    JScrollPane sp;

    double result = 0, ans = 0, num;
    char operator;
    boolean decimal = false;
    boolean number = true;
    boolean operation = false;
    boolean per = false;
    boolean eq = false;
    boolean pm = false;
    boolean del = false;
    boolean opt = false;
    

    DecimalFormat df1 = new DecimalFormat("####.00");
    DecimalFormat df2 = new DecimalFormat("####");
    DecimalFormat df3 = new DecimalFormat("####.00000");

    public static void main(String[] args) {
        new SimpleCalculator();
    }

    public SimpleCalculator() {
        EventQueue.invokeLater(new Runnable() {
            @Override
                public void run() {
                    setTitle("Simple Calculator");
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setLayout(null);
                    setSize(400,600);
                    setResizable(false);
                    getContentPane().setBackground(new Color(255,255,255));

                    //set the program in the center of screen
                    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
                    int width = 400;
                    int height = 600;
                    setBounds(center.x - width / 2, center.y - height / 2, width, height);

                    //create and set a program icon
                    ImageIcon icon = new ImageIcon("calcu.png");
                    setIconImage(icon.getImage());

                    add(btnpanel);
                    add(output1);
                    add(sp);

                    setVisible(true);
                }
        });

        //Output Field 1
        output1.setBounds(0,0,387,25);
        output1.setFont(new Font("VCR OSD Mono",Font.PLAIN,20));
        output1.setForeground(new Color(160,160,160));
        output1.setBackground(Color.WHITE);
        output1.setBorder(null);
        output1.setText("");
        output1.setHorizontalAlignment(JTextField.RIGHT);
        output1.setEditable(false);

        //Output Field 2
        output2.setBounds(0,25,387,75);
        output2.setFont(new Font("VCR OSD Mono",Font.PLAIN,40));
        output2.setForeground(Color.BLACK);
        output2.setBackground(Color.WHITE);
        output2.setBorder(null);
        output2.setHorizontalAlignment(JTextField.RIGHT);
        output2.setEditable(false);

        //Scrollpane for Output Field 2
        sp = new JScrollPane(output2,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(0,25,387,75);
        sp.setBorder(null);

        //Button Placements
        btnpanel.setBounds(0,100, 390, 465);
        btnpanel.setLayout(new GridLayout(5,4,0,0));
        btnpanel.setBackground(Color.WHITE);

        //Function Buttons
        for (int i=0;i<7;i++) {
            funcbuttons[i] = new JButton(symbols[i]);
            funcbuttons[i].setFont(new Font("VCR OSD Mono",Font.PLAIN,15));
            funcbuttons[i].setBackground(new Color(219,224,232));
            funcbuttons[i].setForeground(new Color(54,128,255));
            funcbuttons[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
            funcbuttons[i].setFocusable(false);
            funcbuttons[i].addActionListener(this);
        }

        //Number Buttons
        for (int i=0;i<10;i++) {
            numbuttons[i] = new JButton(String.valueOf(i));
            numbuttons[i].setFont(new Font("VCR OSD Mono",Font.PLAIN,15));
            numbuttons[i].setBackground(Color.WHITE);
            numbuttons[i].setForeground(Color.BLACK);
            numbuttons[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
            numbuttons[i].setFocusable(false);
            numbuttons[i].addActionListener(this);
        }

        //Other Buttons
        for (int i=0;i<2;i++) {
            otherbuttons[i] = new JButton(symbols[i+7]);
            otherbuttons[i].setFont(new Font("VCR OSD Mono",Font.PLAIN,15));
            otherbuttons[i].setBackground(Color.WHITE);
            otherbuttons[i].setForeground(Color.BLACK);
            otherbuttons[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());
            otherbuttons[i].setFocusable(false);
            otherbuttons[i].addActionListener(this);
        }
        
        //Equal Button
        equal.setFont(new Font("VCR OSD Mono",Font.PLAIN,15));
        equal.setBackground(new Color(54,128,255));
        equal.setForeground(Color.WHITE);
        equal.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        equal.setFocusable(false);
        equal.addActionListener(this);

        //Add Buttons to Panel
        for (int i=0;i<4;i++) {
            btnpanel.add(funcbuttons[i]);
        }

        for (int i=7;i>6 && i<10;i++) {
            btnpanel.add(numbuttons[i]);
        }

        btnpanel.add(funcbuttons[4]);

        for (int i=4;i>3 && i<7;i++) {
            btnpanel.add(numbuttons[i]);
        }

        btnpanel.add(funcbuttons[5]);

        for (int i=1;i>0 && i<4;i++) {
            btnpanel.add(numbuttons[i]);
        }

        btnpanel.add(funcbuttons[6]);
        btnpanel.add(otherbuttons[0]);
        btnpanel.add(numbuttons[0]);
        btnpanel.add(otherbuttons[1]);
        btnpanel.add(equal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Number Buttons
        for (int i=0;i<10;i++) {
            if (e.getSource() == numbuttons[i]) {
                if (!number) {
                    output2.setText(output2.getText() + i);
                    num = Double.parseDouble(output2.getText());
                    eq = true;
                } else {
                    output2.setText("");
                    output2.setText(output2.getText() + i);
                    num = Double.parseDouble(output2.getText());
                    decimal = false;
                    number = false;
                    per = true;
                    eq = true;
                    pm = true;
                    del = true;
                    opt = true;
                    funcbuttons[3].setEnabled(true);
                }
            }
        }

        //Percent Button
        if (e.getSource() == otherbuttons[0]) {
            if (per) {    
                output2.setText(String.valueOf(df3.format(Double.parseDouble(output2.getText()) / 100)));
                num = Double.parseDouble(output2.getText());
                number = true;
                decimal = true;
            }
        }

        //Point Button
        if (e.getSource() == otherbuttons[1]) {
            if (!decimal) {
                output2.setText(output2.getText() + ".");
                decimal = true;
                number = false;
                per = true;
                eq = true;
                pm = true;
                del = true;
                opt = true;
            }
        }

        //Add Button
        if (opt) {
            if (!operation) {
                if (e.getSource() == funcbuttons[6]) {
                    num = Double.parseDouble(output2.getText());
                    operator = 1;
                    decimal = false;
                    operation = true;
                    eq = false;
                    funcbuttons[3].setEnabled(true);
                    operation();
                    output2.setText("");

                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " +");
                    } else {
                        output1.setText(df3.format(num) + " +");
                    }
                }
            } else {
                if (e.getSource() == funcbuttons[6]) {
                    num = Double.parseDouble(output2.getText());
                    operation();
                    operator = 1;
                    output2.setText("");

                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " +");
                    } else {
                        output1.setText(df3.format(num) + " +");
                    }
                }
            }
        }

        //Subtract Button
        if (opt) {
            if (!operation) {
                if (e.getSource() == funcbuttons[5]) {
                    num = Double.parseDouble(output2.getText());
                    num *= -1;
                    operator = 2;
                    decimal = false;
                    operation = true;
                    eq = false;
                    funcbuttons[3].setEnabled(true);
                    operation();
                    output2.setText("");
                    
                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " -");
                    } else {
                        output1.setText(df3.format(num) + " -");
                    }
                }
            } else {
                if (e.getSource() == funcbuttons[5]) {
                    num = Double.parseDouble(output2.getText());
                    operation();
                    operator = 2;
                    output2.setText("");
                    
                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " -");
                    } else {
                        output1.setText(df3.format(num) + " -");
                    }
                }
            }
        }

        //Multiply Button
        if (opt) {
            if (!operation) {
                if (e.getSource() == funcbuttons[4]) {
                    num = Double.parseDouble(output2.getText());
                    result = 1;
                    operator = 3;
                    decimal = false;
                    operation = true;
                    eq = false;
                    funcbuttons[3].setEnabled(true);
                    operation();
                    output2.setText("");
                    
                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " *");
                    } else {
                        output1.setText(df3.format(num) + " *");
                    }
                }
            } else {
                if (e.getSource() == funcbuttons[4]) {
                    num = Double.parseDouble(output2.getText());
                    operation();
                    operator = 3;
                    output2.setText("");

                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " *");
                    } else {
                        output1.setText(df3.format(num) + " *");
                    }
                }
            }
        }

        //Divide Button
        if (opt) {
            if (!operation) {
                if (e.getSource() == funcbuttons[2]) {
                    num = Double.parseDouble(output2.getText());
                    result = Math.pow(num, 2);
                    operator = 4;
                    decimal = false;
                    operation = true;
                    eq = false;
                    funcbuttons[3].setEnabled(true);
                    operation();
                    output2.setText("");
                    
                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " /");
                    } else {
                        output1.setText(df3.format(num) + " /");
                    }
                }
            } else {
                if (e.getSource() == funcbuttons[2]) {
                    num = Double.parseDouble(output2.getText());
                    operation();
                    operator = 4;
                    output2.setText("");
                    
                    if (Double.toString(result).endsWith(".0")) {
                        output1.setText(df1.format(num) + " /");
                    } else {
                        output1.setText(df3.format(num) + " /");
                    }
                }
            }
        }

        //PlusMinus Button
        if (e.getSource() == funcbuttons[1]) {
            if (pm) {
                num = Double.parseDouble(output2.getText());
                num *= -1;
                number = true;
                decimal = true;
                output2.setText(df3.format(num));
            }
        }

        //Delete Button
        if (e.getSource() == funcbuttons[3]) {
            if (del) {
                String string = output2.getText();
                output2.setText("");
                for(int i=0;i<string.length()-1;i++) {
                    output2.setText(output2.getText()+string.charAt(i));
                }
                num = Double.parseDouble(output2.getText());
            }
        }

        //Equal Button
        if (e.getSource() == equal) {
            if (eq) {
                result();
                result = 0;
                decimal = true;
                number = true;
                operation = false;
                eq = false;
                funcbuttons[3].setEnabled(false);
            }
        }

        //Clear Button
        if (e.getSource() == funcbuttons[0]) {
            result = 0;
            num = 0;
            funcbuttons[3].setEnabled(true);
            decimal = false;
            number = true;
            operation = false;
            per = false;
            eq = false;
            pm = false;
            del = false;
            opt = false;
            output1.setText("");
            output2.setText("0");
        }
    }
    
    public void operation() {
        switch (operator) {
            case 1:
                result += num;
                break;

            case 2:
                result -= num;
                break;

            case 3:
                result *= num;
                break;

            case 4:
                if (result != 0) {
                    result /= num;
                } else {
                    output1.setText("Syntax Error");
                    output2.setText("Syntax Error");
                }
                break;
        }
        num = result;
    }

    public void result() {
        switch (operator) {
            case 1:
                ans = result + num;
                if (Double.toString(ans).endsWith(".0")) {
                    output1.setText(df1.format(result) + " + " + df1.format(num) + " = " + String.valueOf(df1.format(ans)));
                    output2.setText(String.valueOf(df2.format(ans)));
                } else {
                    output1.setText(df3.format(result) + " + " + df3.format(num) + " = " + String.valueOf(df3.format(ans)));
                    output2.setText(String.valueOf(df3.format(ans)));
                }
                break;

            case 2:
                ans = result - num;
                if (Double.toString(ans).endsWith(".0")) {
                    output1.setText(df1.format(result) + " - " + df1.format(num) + " = " + String.valueOf(df1.format(ans)));
                    output2.setText(String.valueOf(df2.format(ans)));
                } else {
                    output1.setText(df3.format(result) + " - " + df3.format(num) + " = " + String.valueOf(df3.format(ans)));
                    output2.setText(String.valueOf(df3.format(ans)));
                }
                break;

            case 3:
                ans = result * num;
                if (Double.toString(ans).endsWith(".0")) {
                    output1.setText(df1.format(result) + " * " + df1.format(num) + " = " + String.valueOf(df1.format(ans)));
                    output2.setText(String.valueOf(df2.format(ans)));
                } else {
                    output1.setText(df3.format(result) + " * " + df3.format(num) + " = " + String.valueOf(df3.format(ans)));
                    output2.setText(String.valueOf(df3.format(ans)));
                }
                break;

            case 4:
                if (num != 0) {
                    ans = result / num;
                    if (Double.toString(ans).endsWith(".0")) {
                        output1.setText(df1.format(result) + " / " + df1.format(num) + " = " + String.valueOf(df1.format(ans)));
                        output2.setText(String.valueOf(df2.format(ans)));
                    } else {
                        output1.setText(df3.format(result) + " / " + df3.format(num) + " = " + String.valueOf(df3.format(ans)));
                        output2.setText(String.valueOf(df3.format(ans)));
                    }
                } else {
                    output1.setText("Syntax Error");
                    output2.setText("Syntax Error");
                }
                break;
        }
    }
}

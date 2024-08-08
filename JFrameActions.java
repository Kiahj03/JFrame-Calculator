import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class JFrameActions {
    private JTextField textBox;
    private boolean isRad = false;
    private double memory = 0;
    private double num1 = 0;
    private String o = "";
    private boolean newNum = true;
    private Stack<Double> vals = new Stack<>();
    private Stack<String> ops = new Stack<>();
    private boolean isSecond = false;
    private JButton clearButton, radButton, sinButton, cosButton, tanButton, sinhButton, coshButton, tanhButton, expButton, tenExpButton, lnButton, logButton; 

    public JFrameActions(JTextField textBox, JButton clearButton, JButton radButton, JButton sinButton, JButton cosButton, JButton tanButton, JButton sinhButton, JButton coshButton, JButton tanhButton, JButton expButton, JButton tenExpButton, JButton lnButton, JButton logButton) {
        this.textBox = textBox;
        this.clearButton = clearButton;
        this.radButton = radButton;
        this.radButton.setText("Rad");
        this.sinButton = sinButton;
        this.cosButton = cosButton;
        this.tanButton = tanButton;
        this.sinhButton = sinhButton;
        this.coshButton = coshButton;
        this.tanhButton = tanhButton;
        this.expButton = expButton;
        this.tenExpButton = tenExpButton;
        this.lnButton = lnButton;
        this.logButton = logButton;
    }

    private void toggleFuncs() {
        sinButton.setText(isSecond ? "sin^(-1)" : "sin");
        cosButton.setText(isSecond ? "cos^(-1)" : "cos");
        tanButton.setText(isSecond ? "tan^(-1)" : "tan");
        sinhButton.setText(isSecond ? "sinh^(-1)" : "sinh");
        coshButton.setText(isSecond ? "cosh^(-1)" : "cosh");
        tanhButton.setText(isSecond ? "tanh^(-1)" : "tanh");
        expButton.setText(isSecond ? "y^x" : "x^y");
        tenExpButton.setText(isSecond ? "2^x" : "10^x");
        lnButton.setText(isSecond ? "logy" : "ln");
        logButton.setText(isSecond ? "log2" : "log10");
    }

    //Action listener for second button
    public ActionListener getSecond() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isSecond = !isSecond;
                toggleFuncs();
            }
        };
    }

    //Action listener for numbers
    public ActionListener getNumber() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String c = e.getActionCommand();
                if(newNum) {
                    textBox.setText(c);
                    newNum = false;
                }
                else {
                    textBox.setText(textBox.getText() + c);
                }
                if(clearButton.getText().equals("AC")) {
                    clearButton.setText("C");
                }
            }
        };
    }

    //Action listener for clear
    public ActionListener getClear() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(clearButton.getText().equals("C")) {
                    textBox.setText("0");
                    clearButton.setText("AC");
                }
                else {
                    textBox.setText("0");
                    num1 = 0;
                    o = "";
                    newNum = true;
                }
            }
        };
    }
    //Action listener for simple operations
    public ActionListener getOperation(String operator) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textBox.getText());
                o = operator;
                newNum = true;
            }
        };
    }
    
    //Action listener for equals button
    public ActionListener getEquals() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num2;
                try {
                    num2 = Double.parseDouble(textBox.getText());
                }
                catch (NumberFormatException ex) {
                    textBox.setText("Error");
                    newNum = true;
                    return;
                }
                double res = 0;
                boolean error = false;

                switch(o) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        if(num2 == 0){
                            textBox.setText("Error");
                            error = true;
                        }
                        else {
                            res = num1 / num2;
                        }
                        break;
                    case "x^y":
                        res = Math.pow(num1, num2);
                        break;
                    case "x^(1/y)":
                        res = Math.pow(num1, 1 / num2);
                        break;
                    case "EE":
                        res = num1 * Math.pow(10, num2);
                        break;
                    case "y^x":
                        res = Math.pow(num2, num1);
                        break;
                    case "log_y":
                        res = Math.log(num1) / Math.log(num2);
                        break;
                }
                if(!error) {
                    textBox.setText(String.valueOf(res));
                }

                o = "";
                newNum = true;
            }
        };
    }

    //Action listener for negs and pos
    public ActionListener getPlusMinus() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                if(Math.signum(val) == -1.0) {
                    val = Math.abs(val);
                    textBox.setText(String.valueOf(val));
                }
                else {
                    val = -val;
                    textBox.setText(String.valueOf(val));
                }
                newNum = true;
            }
        };
    }

    //Action listeners for parenthesis
    public ActionListener getLeftP() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vals.push(Double.parseDouble(textBox.getText()));
                ops.push(o);
                textBox.setText("0");
                o = "";
                newNum = true;
            }
        };
    }
    public ActionListener getRightP() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num2;
                try {
                    num2 = Double.parseDouble(textBox.getText());
                }
                catch(NumberFormatException ex) {
                    textBox.setText("Error");
                    newNum = true;
                    return;
                }
                double res = 0;
                boolean error = false;

                switch(o) {
                    case "+":
                        res = vals.pop() + num2;
                        break;
                    case "-":
                        res = vals.pop() - num2;
                        break;
                    case "*":
                        res = vals.pop() * num2;
                        break;
                    case "/":
                        if(num2 == 0){
                            textBox.setText("Error");
                            error = true;
                        }
                        else {
                            res = vals.pop() / num2;
                        }
                        break;
                    case "x^y":
                        res = Math.pow(vals.pop(), num2);
                        break;
                    case "x^(1/y)":
                        res = Math.pow(vals.pop(), 1 / num2);
                        break;
                    case "EE":
                        res = vals.pop() * Math.pow(10, num2);
                        break;
                }
                if(!error) {
                    if(!ops.isEmpty()) {
                        o = ops.pop();
                    }
                    num1 = res;
                    textBox.setText(String.valueOf(res));
                }
                newNum = true;
            }
        };
    }
    //Action listeners for getting values of e, pi, and rand
    public ActionListener getE() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textBox.setText(String.valueOf(Math.E));
            }
        };
    }
    public ActionListener getPi() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textBox.setText(String.valueOf(Math.PI));
            }
        };
    }
    public ActionListener getRand() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Math.random();
                textBox.setText(String.valueOf(val));
            }
        };
    }

    //Action listener for radian button
    public ActionListener getRads() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isRad = !isRad;
                radButton.setText(isRad? "Deg" : "Rad");
            }
        };
    }

    //Action listener for decimal button
    public ActionListener getDecimal() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textBox.getText().contains(".")) {
                    textBox.setText(textBox.getText() + ".");
                    newNum = false;
                }
            }
        };
    }

    //Action listeners for memory functions
    public ActionListener getMemoryClear() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memory = 0;
                textBox.setText("0");
            }
        };
    }
    public ActionListener getMemoryAdd() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memory += Double.parseDouble(textBox.getText());
                newNum = true;
            }
        };
    }
    public ActionListener getMemorySub() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memory -= Double.parseDouble(textBox.getText());
                newNum = true;
            }
        };
    }
    public ActionListener getMemoryRecall() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textBox.setText(String.valueOf(memory));
                newNum = true;
            }
        };
    }

    //Action listeners for everything else
    public ActionListener getPercent() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                val /= 100;
                textBox.setText(String.valueOf(val));
                newNum = true;
            }
        };
    }
    public ActionListener getSq() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                val = val* val;
                textBox.setText(String.valueOf(val));
                newNum = true;
            }
        };
    }
    public ActionListener getCube() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                val = val* val * val;
                textBox.setText(String.valueOf(val));
                newNum = true;
            }
        };
    }
    public ActionListener getESqX() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                if(isSecond) {
                    num1 = val;
                    o = "y^x";
                    newNum = true;
                }
                else {
                    double res = Math.exp(val);
                    textBox.setText(String.valueOf(res));
                    newNum = true;
                }
            }
        };
    }
    public ActionListener getTenSqX() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res = isSecond ? Math.pow(2, val) : Math.pow(10, val);
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getOneDivX() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                val = 1 / val;
                textBox.setText(String.valueOf(val));
                newNum = true;
            }
        };
    }
    public ActionListener getSqrt() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                if(val < 0) {
                    textBox.setText("Error");
                }
                else {
                    val = Math.sqrt(val);
                    textBox.setText(String.valueOf(val));
                }
                newNum = true;
            }
        };
    }
    public ActionListener getCrt() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                val = Math.cbrt(val);
                textBox.setText(String.valueOf(val));
                newNum = true;
            }
        };
    }
    public ActionListener getln() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                if (isSecond) {
                    num1 = val;
                    o = "log_y";
                    newNum = true;
                } 
                else {
                    double res = Math.log(val);
                    textBox.setText(String.valueOf(res));
                    newNum = true;
                }
            }
        };
    }
    public ActionListener getLog10() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res = isSecond ? Math.log(val) / Math.log(2) : Math.log10(val);
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getFact() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                if(val == 0) {
                    val = 1;
                    textBox.setText(String.valueOf(val));
                }
                else if(val < 0) {
                    textBox.setText("Error");
                }
                else {
                    long fact = 1;
                    for (int i = 1; i <= val; i++) {
                        fact *= i;
                    }
                    textBox.setText(String.valueOf(fact));
                }
                newNum = true;
            }
        };
    }
    public ActionListener getSin() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res;
                if(isSecond) {
                    if(val < -1 || val > 1) {
                        textBox.setText("Error");
                        newNum = true;
                        return;
                    }
                    res = Math.asin(val);
                    if (!isRad) {
                        res = Math.toDegrees(res);
                    }
                } else {
                    if(!isRad) {
                        val = Math.toRadians(val);
                    }
                    res = Math.sin(val);
                }
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getCos() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res;
                if(isSecond) {
                    if(val < -1 || val > 1) {
                        textBox.setText("Error");
                        newNum = true;
                        return;
                    }
                    res = Math.acos(val);
                    if(!isRad) {
                        res = Math.toDegrees(res);
                    }
                } else {
                    if(!isRad) {
                        val = Math.toRadians(val);
                    }
                    res = Math.cos(val);
                }
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getTan() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double result;
                if(isSecond) {
                    result = Math.atan(val);
                    if(!isRad) {
                        result = Math.toDegrees(result);
                    }
                } else {
                    if(!isRad) {
                        val = Math.toRadians(val);
                    }
                    result = Math.tan(val);
                }
                textBox.setText(String.valueOf(result));
                newNum = true;
            }
        };
    }
    public ActionListener getSinh() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res;
                if(isSecond) {
                    res = Math.log(val + Math.sqrt(val * val + 1));
                } 
                else {
                    res = Math.sinh(val);
                }
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getCosh() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res;
                if(isSecond) {
                    if(val < 1) {
                        textBox.setText("Error");
                        newNum = true;
                        return;
                    }
                    res = Math.log(val + Math.sqrt(val * val - 1));
                } else {
                    res = Math.cosh(val);
                }
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }
    public ActionListener getTanh() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double val = Double.parseDouble(textBox.getText());
                double res;
                if(isSecond) {
                    if(val <= 0 || val >= 1) {
                        textBox.setText("Error");
                        newNum = true;
                        return;
                    }
                    res = 0.5 * Math.log((1 + val) / (1 - val));
                } else {
                    res = Math.tanh(val);
                }
                textBox.setText(String.valueOf(res));
                newNum = true;
            }
        };
    }

    //Updating textbox
    private void updateTextBox(String t) {
        if(textBox.getText().equals("0") || newNum) {
            textBox.setText(t);
            newNum = false;
        }
        else {
            textBox.setText(textBox.getText() + t);
        }
    }
}

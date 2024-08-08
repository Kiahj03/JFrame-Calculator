import java.awt.*;
import javax.swing.*;

public class JFrameCalculator {
    private JTextField textBox;
    private JButton[] numbers;

    JFrameCalculator() {
        //Creating the main frame
        JFrame frame = new JFrame("Kiah's Apple Calculator");
        frame.setSize(910, 235);

        //Setting up panels
        JPanel mainPanel = new JPanel();

        JPanel textPanel = new JPanel();
        textBox = new JTextField("0", 33);
        Font f = new Font("Verdana", Font.BOLD, 20);
        textBox.setFont(f);
        textBox.setHorizontalAlignment(JTextField.RIGHT);
        textBox.setBorder(BorderFactory.createEmptyBorder());
        textBox.setEditable(false);
        textPanel.add(textBox);

        //Creating all buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 10, 2, 3));
        JButton leftP = new JButton("(");
        JButton rightP= new JButton(")");
        JButton mc = new JButton("mc");
        JButton mPlus = new JButton("m+");
        JButton mMinus = new JButton("m-");
        JButton mR = new JButton("mr");
        JButton clear = new JButton("AC");
        JButton plusminus = new JButton("+/-");
        JButton percent= new JButton("%");
        JButton divide = new JButton("/");

        JButton second = new JButton("2nd");
        JButton sq = new JButton("x^2");
        JButton cubed = new JButton("x^3");
        JButton xSqY = new JButton("x^y");
        JButton eSqX = new JButton("e^x");
        JButton tenSqX = new JButton("10^x");
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        JButton mult = new JButton("*");

        JButton oneDivX = new JButton("1/x");
        JButton sqrt = new JButton("sqrt(x)");
        JButton crt = new JButton("x^(1/3)");
        JButton yRtX= new JButton("x^(1/y)");
        JButton ln = new JButton("ln");
        JButton log10 = new JButton("log10");
        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        JButton minus = new JButton("-");

        JButton fact = new JButton("x!");
        JButton sin = new JButton("sin");
        JButton cos = new JButton("cos");
        JButton tan = new JButton("tan");
        JButton e = new JButton("e");
        JButton EE = new JButton("EE");
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton plus = new JButton("+");

        JButton rad = new JButton("Rad");
        JButton sinh = new JButton("sinh");
        JButton cosh = new JButton("cosh");
        JButton tanh = new JButton("tanh");
        JButton pi = new JButton("pi");
        JButton rand = new JButton("Rand");
        JButton zero = new JButton("0");
        JButton decimal = new JButton(".");
        JButton equal = new JButton("=");

        //Adding buttons to array + instance for actions
        numbers = new JButton[]{zero, one, two, three, four, five, six, seven, eight, nine};
        JFrameActions actions = new JFrameActions(textBox, clear, rad, sin, cos, tan, sinh, cosh, tanh, eSqX, tenSqX, ln, log10);
        for(JButton b : numbers) {
            b.addActionListener(actions.getNumber());
        }

        //Adding action listeners to operation buttons
        plus.addActionListener(actions.getOperation("+"));
        minus.addActionListener(actions.getOperation("-"));
        mult.addActionListener(actions.getOperation("*"));
        divide.addActionListener(actions.getOperation("/"));
        xSqY.addActionListener(actions.getOperation("x^y"));
        yRtX.addActionListener(actions.getOperation("x^(1/y)"));
        EE.addActionListener(actions.getOperation("EE"));

        percent.addActionListener(actions.getPercent());
        sq.addActionListener(actions.getSq());
        cubed.addActionListener(actions.getCube());
        eSqX.addActionListener(actions.getESqX());
        tenSqX.addActionListener(actions.getTenSqX());
        oneDivX.addActionListener(actions.getOneDivX());
        sqrt.addActionListener(actions.getSqrt());
        crt.addActionListener(actions.getCrt());
        ln.addActionListener(actions.getln());
        log10.addActionListener(actions.getLog10());
        fact.addActionListener(actions.getFact());
        sin.addActionListener(actions.getSin());
        cos.addActionListener(actions.getCos());
        tan.addActionListener(actions.getTan());
        sinh.addActionListener(actions.getSinh());
        cosh.addActionListener(actions.getCosh());
        tanh.addActionListener(actions.getTanh());

        plusminus.addActionListener(actions.getPlusMinus());
        equal.addActionListener(actions.getEquals());
        clear.addActionListener(actions.getClear());
        rad.addActionListener(actions.getRads());
        decimal.addActionListener(actions.getDecimal());

        e.addActionListener(actions.getE());
        pi.addActionListener(actions.getPi());
        rand.addActionListener(actions.getRand());

        mc.addActionListener(actions.getMemoryClear());
        mPlus.addActionListener(actions.getMemoryAdd());
        mMinus.addActionListener(actions.getMemorySub());
        mR.addActionListener(actions.getMemoryRecall());
        leftP.addActionListener(actions.getLeftP());
        rightP.addActionListener(actions.getRightP());

        second.addActionListener(actions.getSecond());
        
        //Adding buttons to panel
        buttonPanel.add(leftP);
        buttonPanel.add(rightP);
        buttonPanel.add(mc);
        buttonPanel.add(mPlus);
        buttonPanel.add(mMinus);
        buttonPanel.add(mR);
        buttonPanel.add(clear);
        buttonPanel.add(plusminus);
        buttonPanel.add(percent);
        buttonPanel.add(divide);

        buttonPanel.add(second);
        buttonPanel.add(sq);
        buttonPanel.add(cubed);
        buttonPanel.add(xSqY);
        buttonPanel.add(eSqX);
        buttonPanel.add(tenSqX);
        buttonPanel.add(seven);
        buttonPanel.add(eight);
        buttonPanel.add(nine);
        buttonPanel.add(mult);

        buttonPanel.add(oneDivX);
        buttonPanel.add(sqrt);
        buttonPanel.add(crt);
        buttonPanel.add(yRtX);
        buttonPanel.add(ln);
        buttonPanel.add(log10);
        buttonPanel.add(four);
        buttonPanel.add(five);
        buttonPanel.add(six);
        buttonPanel.add(minus);

        buttonPanel.add(fact);
        buttonPanel.add(sin);
        buttonPanel.add(cos);
        buttonPanel.add(tan);
        buttonPanel.add(e);
        buttonPanel.add(EE);
        buttonPanel.add(one);
        buttonPanel.add(two);
        buttonPanel.add(three);
        buttonPanel.add(plus);
        
        buttonPanel.add(rad);
        buttonPanel.add(sinh);
        buttonPanel.add(cosh);
        buttonPanel.add(tanh);
        buttonPanel.add(pi);
        buttonPanel.add(rand);
        buttonPanel.add(zero);
        buttonPanel.add(decimal);
        buttonPanel.add(equal);

        //Setting layout for main frame
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
    public static void main(String[] args) {
        JFrameCalculator calc = new JFrameCalculator();
    }
}

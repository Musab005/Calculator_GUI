/**
 * ECSE 202 - Assignment 4
 * Calculator GUI
 * 19th March, 2022
 * @author Musab Umair
 * McGill Id: 261017073
 * This program uses ACM libraries
 * Link: https://cs.stanford.edu/people/eroberts/jtf/
 */

import acm.gui.TableLayout;
import acm.program.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * This class implements the GUI for the calculator
 * The GUI uses a table layout for the design, text fields for the input and output expressions, buttons to enter into
 * the input text field, and a slider for controlling the significant figures of the answer.
 */
public class JCalcGui extends Program {

    JTextField expressionField = new JTextField();
    JTextField answerField = new JTextField();
    JSlider sigF = new JSlider(0,16,5);
    JLabel currentSigF = new JLabel("current sig. fig = "+ sigF.getValue());

    /**
     * This method was copied from the ECSE 202 tutorial class led by TA Katrina Poulin.
     * This method indicates its priority whenever the calculator application is run.
     *  @param args
     */
    public static void main(String[] args) {
       new JCalcGui().start(args);
    }


    /**
     * This method implements the calculator GUI on the screen and adds action listeners to it.
     */
    public void init() {
       calcLayout();
       setBackground(Color.GRAY);
       addActionListeners();
    }


    /**
     * This method is called when actions are performed on the calculator GUI.
     * The possible actions performed are clicking a button on the calculator or adjusting the slider for
     * significant figures.
     * @param e is the action event that is performed by the user.
     */
    public void actionPerformed(ActionEvent e) {
        Queue b = new Queue();
        String expression;
        String command = e.getActionCommand();

        //ENTER key on the keyboard is pressed
        if (e.getSource() == expressionField) {
            expression = expressionField.getText();
            if (expression.equals("")) {           //the input text field is blank
                expressionField.setText("");
                answerField.setText("");
            } else {
                expressionField.setText(expression + "=");
                answerField.setText(evaluate(expression));
            }
        }
        //Reactions to all buttons on the calculator.
        switch(command) {
            case "C":
                expressionField.setText("");
                answerField.setText("");
                break;
            case "/":
                expression = expressionField.getText();
                expressionField.setText(expression+"/");
                break;
            case "(":
                expression = expressionField.getText();
                expressionField.setText(expression+"(");
                break;
            case ")":
                expression = expressionField.getText();
                expressionField.setText(expression+")");
                break;
            case "9":
                expression = expressionField.getText();
                expressionField.setText(expression+"9");
                break;
            case "8":
                expression = expressionField.getText();
                expressionField.setText(expression+"8");
                break;
            case "7":
                expression = expressionField.getText();
                expressionField.setText(expression+"7");
                break;
            case "6":
                expression = expressionField.getText();
                expressionField.setText(expression+"6");
                break;
            case "5":
                expression = expressionField.getText();
                expressionField.setText(expression+"5");
                break;
            case "4":
                expression = expressionField.getText();
                expressionField.setText(expression+"4");
                break;
            case "3":
                expression = expressionField.getText();
                expressionField.setText(expression+"3");
                break;
            case "2":
                expression = expressionField.getText();
                expressionField.setText(expression+"2");
                break;
            case "1":
                expression = expressionField.getText();
                expressionField.setText(expression+"1");
                break;
            case "0":
                expression = expressionField.getText();
                expressionField.setText(expression+"0");
                break;
            case "+":
                expression = expressionField.getText();
                expressionField.setText(expression+"+");
                break;
            case "=": //this case evaluates the expression entered by the user
                expression = expressionField.getText();
                boolean check = b.checkAlphabets(expression);
                if (check) { //checks if alphabets were entered
                    expressionField.setText("");
                    answerField.setText("");
                } else if (expression.equals("")) { //checks if the text field was blank
                    expressionField.setText("");
                    answerField.setText("");
                } else {   //evaluates
                    expressionField.setText(expression + "=");
                    answerField.setText(evaluate(expression));
                }
                break;
            case "-":
                expression = expressionField.getText();
                expressionField.setText(expression+"-");
                break;
            case "*":
                expression = expressionField.getText();
                expressionField.setText(expression+"*");
                break;
            case "Quit": //application is closed
                exit();
                break;
            default:
                expression = expressionField.getText();
                expressionField.setText(expression);
                expressionField.setText(expression);
        }
    }

    /**
     * This method creates the GUI for the calculator using Table Layout, JButtons and JSlider.
     */
    public void calcLayout() {
        setLayout(new TableLayout(9, 4));
        add(expressionField, "gridwidth = 4"); //4 columns 1 row
        expressionField.addActionListener(this);
        add(answerField, "gridwidth = 4");
        answerField.setEditable(false);

        add(currentSigF, SOUTH);
        add(new JLabel("Sig. Fig. slider: "), NORTH);
        add(new JLabel("0"), NORTH);
        add(sigF, NORTH);
        add(new JLabel("16"), NORTH);

        add(new JButton("C"), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton("/"), "width = 75");
        add(new JButton("7"), "width = 75");
        add(new JButton("8"), "width = 75");
        add(new JButton("9"), "width = 75");
        add(new JButton("*"), "width = 75");
        add(new JButton("4"), "width = 75");
        add(new JButton("5"), "width = 75");
        add(new JButton("6"), "width = 75");
        add(new JButton("-"), "width = 75");
        add(new JButton("1"), "width = 75");
        add(new JButton("2"), "width = 75");
        add(new JButton("3"), "width = 75");
        add(new JButton("+"), "width = 75");
        add(new JButton("0"), "width = 75");
        add(new JButton("."), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton("="), "width = 75");
        add(new JButton("("), "width = 75");
        add(new JButton(")"), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton(""), "width = 75");
        add(new JButton("Quit"), "width = 75");
    }

    /**
     * This method evaluates the input String expression entered into the calculator to its String value.
     * @param input the String infix expression entered by the user
     * @return the value of the expression as a String
     */
    public String evaluate(String input) {
        PostFix p2 = new PostFix();
        input = input.replaceAll("=", "");
        Queue Qinfix = p2.parse(input); // Parse input string
        Queue Qpostfix = p2.in2Post(Qinfix); // Convert to postfix
        double value = p2.postEval(Qpostfix); // Evaluate the expression
        double roundedValue = sigFigures(value); //account for significant figures set by the user on the slider
        return Double.toString(roundedValue);
    }

    /**
     * This method was copied from Sean Owen on Stackoverflow
     * link: https://stackoverflow.com/questions/7548841/round-a-double-to-3-significant-figures
     * It rounds the answer to significant figures set by the user using the slider.
     * @param value is the unaltered value of the input expression
     * @return the rounded value to the desired significant figures
     */
    public double sigFigures(double value) {
        int sf = sigF.getValue(); //get the significant numbers from the slider
        currentSigF.setText("current sig. fig = "+sf); //update the slider to this new number
        BigDecimal bd = new BigDecimal(value);
        bd = bd.round(new MathContext(sf));
        return bd.doubleValue();
    }


}

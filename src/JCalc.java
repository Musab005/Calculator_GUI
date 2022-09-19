/**
 * ECSE 202 - Assignment 4
 * Calculator GUI
 * 19th March, 2022
 * @author Musab Umair
 * McGill Id: 261017073
 * This program uses ACM libraries
 * Link: https://cs.stanford.edu/people/eroberts/jtf/
 */

import acm.program.ConsoleProgram;

/**
 * This class evaluates an infix expression to its double value without the calculator GUI.
 * It uses a simple interactive window by extending ConsoleProgram from the ACM libraries.
 */
public class JCalc extends ConsoleProgram {
    PostFix pf = new PostFix();
    Queue q = new Queue();

    /**
     * This method was copied from the assignment pdf posted on MyCourses by prof Frank Ferrie
     * This method prompts the user for the input expression.
     * A blank line terminates the program.
     * If there are no invalid characters like alphabets, the input String is parsed into a Queue.
     * This Queue is then converted into a postfix String and then finally evaluated to a double value.
     * To achieve this goal, several methods are used from the classes "Postfix" and "Queue"
     */
    public void run() {
        println("Infix to Postfix conversion, enter expression or blank line to exit.");
        while (true) {
            String input = readLine("expression: "); // Get expression
            if (input.equals("")) break; // Terminate on blank line
            Queue Qinfix = pf.parse(input); // Parse input string
            Queue Qpostfix = pf.in2Post(Qinfix); // Convert to postfix
            String postString = Qpostfix.toString();
            //check postString for alphabets
            boolean alphabetCheck = q.checkAlphabets(postString);
            if (alphabetCheck) {
                println("Invalid expression entered");
            } else {
                println(input + " => " + postString); // Display postfix result
                double value = pf.postEval(Qpostfix); // Evaluate the expression
                    println(postString + " evaluates to " + value); // display the answer
            }
        }
        println("Program terminated.");
    }
}
import java.util.StringTokenizer;

/**
 * This class exports the following methods:
 * 'parse' which will tokenize and then convert the infix String to type Queue.
 * 'in2Post' which will convert the infix Queue to postfix Queue. (shunting yard algorithm).
 * 'isOperator' to check for operators in the infix expression during conversion to postfix.
 * 'precedence' to check the precedence of operators during the conversion from infix to postfix.
 * 'postEval' to evaluate the postfix queue to a double value
 */
public class PostFix {


    /**
     * This method parses the String infix expression entered by the user into an infix Queue.
     * This is done by first tokenizing the string into the separate numbers and operators.
     * Then each token is individually enqueued in a Queue with order preserved.
     * @param infix is the String expression that the user inputs into the program.
     * @return the infix expression as a Queue of individual tokens with order preserved.
     */
    public Queue parse(String infix) {

        String newInfix;
        Queue qIn = new Queue();

        //all white spaces are removed so that they are not considered as a separate token.
        infix = infix.replaceAll(" ", "");

        //Accounting for a negative sign at the beginning of the expression
        if (infix.indexOf("-") == 0) {
           newInfix = infix.substring(2);
           String t = infix.substring(0,2);
           StringTokenizer a = new StringTokenizer(t);
            while (a.hasMoreTokens()){
                qIn.enqueue(a.nextToken());
            }
        }
        else {
            newInfix = infix;
        }

        //Assuming that numbers and operators alternate in the infix expression. So the number between
        //two operators is a single token
        StringTokenizer st = new StringTokenizer(newInfix, "+-/*()^", true);
        while (st.hasMoreTokens()){
            qIn.enqueue(st.nextToken());
        }
        return qIn;
    }

    /**
     * This method converts the infix Queue to postfix Queue.
     * This is done based on the shunting yard algorithm. To summarize:-
     * 1. All numbers in the infix Queue are directly enqueued to the postfix Queue.
     * 2. The higher precedence operators in the infix Queue are enqueued first to the postfix Queue except brackets.
     * 3. An opening bracket in the infix Queue is held in the stack until a closing bracket is dequeued from the infix Queue.
     * When that happens, all operators on top of the opening bracket in the stack are enqueued to the output Queue.
     * The brackets are discarded.
     * 4. No two operators of the same precedence can live on top of each other in the stack.
     * 5. All operators still left in the stack after the infix expression is fully analyzed will be enqueued.
     * @param qIn is the infix expression of type Queue.
     * @return the postfix Queue representation of the infix Queue.
     */
    public Queue in2Post(Queue qIn) {
        Queue qOut = new Queue();       //postfix Queue
        Stack stack = new Stack();
        while (true) {
            String q = qIn.dequeue();   //token dequeued from infix Queue
            if (q == null) break;

            //token is an operator
            if (isOperator(q)) {
                int a = precedence(q); //precedence of token
                String s = stack.pop();
                if (s == null) {
                    stack.push(q);
                } else {
                    int b = precedence(s);
                    if (b >= a) {    //compare precedence of operator on top of stack with operator dequeued from infix
                        // operator from top of stack enqueued to postfix if its precedence => dequeued operator from infix
                        qOut.enqueue(s);

                        //check if the next operator on the stack has the same precedence as the operator dequeued from infix Queue
                        String d = stack.pop();
                        if (d == null) {
                            stack.push(q);
                        } else {
                            int k = precedence(d);
                            if (a == k) {
                                qOut.enqueue(d);
                                stack.push(q);
                            } else {
                                stack.push(d);
                                stack.push(q);
                            }
                        }
                    } else {
                        stack.push(s);
                        stack.push(q); //the operator dequeued from infix is pushed on stack in all cases
                    }
                }
            }

            //token is "("
            else if (q.equals("(")) {
                stack.push(q);
            }

            //token is ")"
            else if (q.equals(")")) {
                while (true) {
                    String z = stack.pop();
                    if (z == null || z.equals("(")) {
                        break;
                    } else {
                        qOut.enqueue(z); //enqueue all operators from stack to postfix until "(" reached
                    }
                }
            }

            //token is an operand
            else {
                qOut.enqueue(q);
            }
        }

        //empty remaining stack
        while (true) {
            String y = stack.pop();
            if (y == null) break;
            qOut.enqueue(y);
        }
        return qOut;
    }


    /**
     * This method checks whether the token dequeued from the infix Queue is an operator.
     * @param token is dequeued from the infix Queue
     * @return true if the token is an operator. False otherwise.
     */
    public boolean isOperator(String token) {
        switch (token) {
            case "+": case "-": case "*": case "/": case "^":
                return true;
            default:
                return false;
        }
    }

    /**
     * This method checks the precedence of operators according to the shunting yard algorithm.
     * @param operator is dequeued from the infix Queue
     * @return the integer precedence
     */
    public int precedence(String operator) {
        switch (operator) {
            case "^":
                return 4;
            case "*": case "/":
                return 3;
            case "(":
                return 0;
            default:
                return 2;
        }
    }

    /**
     * This method was copied from the ECSE 202 Tutorial led by TA Katrina Poulin.
     * This method evaluates the postfix Queue to its actual value.
     * Operands from the postfix queue are always pushed onto the stack.
     * Whenever an operator is dequeued, the two top most operands are popped from the stack and the result of the
     * operation is stored back onto the stack.
     * @param qPostfix is the postfix queue
     * @return the value (type double) of the postfix queue
     */
    public double postEval (Queue qPostfix) {
        Stack s2 = new Stack();
        double result;
        while (qPostfix.head != null) {
            String q = qPostfix.dequeue();   //token dequeued from infix Queue
            if (isOperator(q)) {
                //pop two operands from stack
                String b = s2.pop();
                if (b == null) break;
                double B = Double.parseDouble(b);
                String a = s2.pop();
                if (a == null) break;
                double A = Double.parseDouble(a);

                //check which operator was dequeued to perform the correct operation between the operands.
                switch (q) {
                    case "-":
                        result = A - B;
                        break;
                    case "*":
                        result = A * B;
                        break;
                    case "/":
                        result = A / B;
                        break;
                    case "^":
                        result = Math.pow(A, B);
                        break;
                    default:
                        result = A + B;
                        break;
                }
                s2.push(Double.toString(result)); //the result of the operation is pushed back onto the stack
            }
            else {
                s2.push(q); //all operands at first are pushed onto the stack
            }
        }
            result = Double.parseDouble(s2.pop());
            return result;
        }
}

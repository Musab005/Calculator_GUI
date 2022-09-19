/**
 * The Stack class allows the program to implement a Stack data structure.
 * The field 'top' is instantiated which represents a ListNode object on top of the Stack.
 */
public class Stack {
    ListNode top;

    /**
     * This method pushes a String to the top of the Stack.
     * @param a is the String object to be pushed on top of the Stack.
     */
    public void push(String a) {
        ListNode node = new ListNode();
        node.data = a;
        node.next = top;
        top = node;
    }

    /**
     * This method extracts the String on top of the Stack.
     * @return the String object sitting on top of the stack. If stack empty, return null.
     */
    public String pop() {
        if (top == null) return null;
        String data = top.data;
        top=top.next;
        return data;
    }

    }

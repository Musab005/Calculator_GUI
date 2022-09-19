/**
 * The Queue class contains the 'enqueue', 'dequeue', 'toString', and 'checkAlphabets' methods.
 * Two fields 'head' and 'tail' are instantiated to help develop these methods.
 */
public class Queue {
    ListNode head;
    ListNode tail;


    /**
     * This method enqueues a token to the end of the Queue.
     * @param b is the String token that needs to be stored as data to a ListNode object to be enqueued.
     */
    public void enqueue(String b) {
        ListNode node = new ListNode();
        node.data = b;
        node.next = null;
        if (tail != null) {
            tail.next = node;
        } else {
            head = node;
        }
        tail = node;
    }

    /** This method dequeues the String data from the ListNode object at the beginning of the Queue.
     * @return the token/data of type String that is dequeued from the Queue. If the Queue is empty, return null.
     */
    public String dequeue() {
        if (head != null) {
            if (head == tail) tail = null;
                String j = head.data;
                head = head.next;
                return j;
            } else {
            return null;
        }
    }

    /**
     * This method converts a Queue to type String with order preserved
     * @return the String expression of the Queue. Blank string returned if Queue is empty.
     */
    public String toString() {
        String result = "";
        ListNode curr = head;
        while (curr != null) {
            String data = curr.data;
            result += " " + data;
            curr = curr.next;
        }
        return result;
    }

    /**
     * This method checks for alphabets in the infix expression the user might input in the calculator through
     * the keyboard.
     * @param input the string infix expression.
     * @return true if alphabets exist in the infix expression, false otherwise.
     */
    public boolean checkAlphabets(String input) {
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            boolean check = Character.isLetter(temp);
            if (check) return true;
        }
        return false;
    }

}

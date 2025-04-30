
package linkedlist;


public class ActionStack {
    


    private ActionNode top;
    private int size;

    private static class ActionNode {
        Action data;
        ActionNode next;

        public ActionNode(Action data) {
            this.data = data;
            this.next = null;
        }
    }

    public ActionStack() {
        top = null;
        size = 0;
    }

    public void push(Action item) {
        ActionNode newNode = new ActionNode(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Action data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public Action peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
    


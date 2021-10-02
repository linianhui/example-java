package example.core.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    public Node deleteNodeFromEnd(final Node head, int rightIndex) {
        if (head==null) {
            return null;
        }
        Node result = new Node();
        result.next = head;

        Node fast = head;
        for (int i = 1; i <= rightIndex + 1; i++) {
            fast = fast.next;
        }

        Node slow = head;
        while (fast!=null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return result.next;
    }

    @Test
    public void test_deleteNodeFromEnd() {
        Node n1 = new Node();
        n1.value = 2;

        Node n2 = new Node();
        n1.next = n2;
        n2.value = 3;

        Node n3 = new Node();
        n2.next = n3;
        n3.value = 4;

        Node n4 = new Node();
        n3.next = n4;
        n4.value = 5;

        Node n = deleteNodeFromEnd(n1, 2);

        Assertions.assertEquals(5, n.next.next.value);
    }

    public static class Node {
        public int value;
        public Node next;
    }
}

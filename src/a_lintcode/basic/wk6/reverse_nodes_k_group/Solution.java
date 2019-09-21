package a_lintcode.basic.wk6.reverse_nodes_k_group;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class Solution {
    /**
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head != null) {
            head = reverseK(k, head);
        }

        return dummy.next;
    }

    // head -> n1 -> n2 -> ... nk -> nk+1
    // head -> nk -> nk-1 -> ... n1 -> nk + 1
    // return n1
    private ListNode reverseK(int k, ListNode head) {
        ListNode nk = head;
        int runner = k;
        while (nk != null && runner > 0) {
            nk = nk.next;
            runner--;
        }

        if (nk == null) {
            return null;
        }


        ListNode n1 = head.next;
        ListNode nkplus = nk.next;
        ListNode prev = null;
        ListNode current = head.next;
        for (int i = 0; i < k; i++) {
            ListNode tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }

        // connect
        head.next = nk;
        n1.next = nkplus;
        return n1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        head.next = node3;
        node3.next = node2;
        node2.next = node4;
        s.reverseKGroup(head, 4);
    }
}



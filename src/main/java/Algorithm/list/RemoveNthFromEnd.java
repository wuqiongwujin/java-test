package Algorithm.list;

/**
 * @Description
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * @date 2019/11/24
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        int a = 0;
        while (fast.next != null) {
            fast = fast.next;
            a++;
            if (a > n) {
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        return head;
    }
}



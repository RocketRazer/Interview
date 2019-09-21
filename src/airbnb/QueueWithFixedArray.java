package airbnb;

/**
 * Implement a queue with fixed-size arrays (or arraylist)
 *
 * Solution
 * Use the last position of the array to store the next array
 */

import java.util.ArrayList;
import java.util.List;

public class QueueWithFixedArray {
    private int fixedSize;

    private int count; //total count
    private int head;  // index used for poll on the head list
    private int tail;  // index used for offer on the tail list
    private List<Object> headList;  // reference to the head list
    private List<Object> tailList;  // reference to the tail list

    public QueueWithFixedArray(int fixedSize) {
        this.fixedSize = fixedSize;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        this.headList = new ArrayList<>();
        this.tailList = this.headList;
    }

    public void offer(int num) {
        if (tail == fixedSize - 1) {
            List<Object> newList = new ArrayList<>();
            newList.add(num);
            tailList.add(newList);
            tailList = (List<Object>)tailList.get(tail);
            tail = 0;
        } else {
            tailList.add(num);
        }
        count++;
        tail++;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }

        int num = (int)headList.get(head);
        head++;
        count--;

        // if the head index point to the last index of fix array
        // upate the head list to the next list
        if (head == fixedSize - 1) {
            List<Object> newList = (List<Object>)headList.get(head);
            headList.clear();
            headList = newList;
            head = 0;
        }

        return num;
    }

    public int size() {
        return count;
    }


    public static void main(String[] args) {
        QueueWithFixedArray queue = new QueueWithFixedArray(5);
        System.out.println(queue.poll());
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll());
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}


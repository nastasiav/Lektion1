package org.my.edy;

/**
 * 703
 You are part of a university admissions office and need to keep track of the
 kth highest test score from applicants in real-time. This helps to determine cut-
 off marks for interviews and admissions dynamically as new applicants submit
 their scores.

 You are tasked to implement a class which, for a given integer k, maintains a
 stream of test scores and continuously returns the kth highest test score after
 a new score has been submitted. More specifically, we are looking for the kth
 highest score in the sorted list of all scores.

 Implement the KthLargest class:

 KthLargest(int k, int[] nums) Initializes the object with the integer k and
 the stream of test scores nums.
 int add(int val) Adds a new test score val to the stream and returns the
 element representing the kᵗʰ largest element in the pool of test scores so far.

 Example 1:

 Input: ["KthLargest", "add", "add", "add", "add", "add"] [[3, [4, 5, 8, 2]], [3
 ], [5], [10], [9], [4]]
 Output: [null, 4, 5, 5, 8, 8]

 Explanation:
 KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 kthLargest.add(3); //return 4
 kthLargest.add(5); // return 5
 kthLargest.add(10); // return 5
 kthLargest.add(9); // return 8
 kthLargest.add(4); // return 8

 Example 2:

 Input: ["KthLargest", "add", "add", "add", "add"] [[4, [7, 7, 7, 7, 8, 3]], [2]
 , [10], [9], [9]]
 Output: [null, 7, 7, 7, 8]

 Explanation: KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 kthLargest.add(2); // return 7
 kthLargest.add(10); // return 7
 kthLargest.add(9); // return 7
 kthLargest.add(9); // return 8

 Constraints:

 0 <= nums.length <= 10⁴
 1 <= k <= nums.length + 1
 -10⁴ <= nums[i] <= 10⁴
 -10⁴ <= val <= 10⁴
 At most 10⁴ calls will be made to add.

 Related Topics Tree Design Binary Search Tree Heap (Priority Queue) Binary
 Tree Data Stream 👍 6411 👎 4070
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class KthLargestElementInAStream {
    public static void main(String[] args) {
//        Output: [null, 4, 5, 5, 8, 8]
        KthLargest solution = new KthLargestElementInAStream().new KthLargest(3, new int[] {4, 5, 8, 2});
        System.out.println(solution.add(3));
        System.out.println(solution.add(5));
        System.out.println(solution.add(10));
        System.out.println(solution.add(9));
        System.out.println(solution.add(4));

        System.out.println("---------------");
//        Output: [null, 7, 7, 7, 8]
        KthLargest solution2 = new KthLargestElementInAStream().new KthLargest(4, new int[] {7, 7, 7, 7, 8, 3});
        System.out.println(solution2.add(2));
        System.out.println(solution2.add(10));
        System.out.println(solution2.add(9));
        System.out.println(solution2.add(9));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {
        private final int k;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(Integer::intValue));

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for(int i : nums) {
                if (queue.size() < k) {
                    queue.add(i);
                } else {
                    if (queue.peek() < i) {
                        queue.remove();
                        queue.add(i);
                    }
                }
            }
        }

        public int add(int val) {
            if (queue.size() < k) {
                queue.add(val);
            } else {
                if (queue.peek() < val) {
                    queue.remove();
                    queue.add(val);
                }
            }
            return queue.isEmpty() ? -1 : queue.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

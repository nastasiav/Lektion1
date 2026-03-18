package org.my.edy;

/**
 * 933
 You have a RecentCounter class which counts the number of recent requests
 within a certain time frame.

 Implement the RecentCounter class:

 RecentCounter() Initializes the counter with zero recent requests.
 int ping(int t) Adds a new request at time t, where t represents some time in
 milliseconds, and returns the number of requests that has happened in the past 30
 00 milliseconds (including the new request). Specifically, return the number of
 requests that have happened in the inclusive range [t - 3000, t].

 It is guaranteed that every call to ping uses a strictly larger value of t
 than the previous call.

 Example 1:
 Input
 ["RecentCounter", "ping", "ping", "ping", "ping"]
 [[], [1], [100], [3001], [3002]]
 Output
 [null, 1, 2, 3, 3]

 Explanation
 RecentCounter recentCounter = new RecentCounter();
 recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return
 2
 recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001],
 return 3
 recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002]
 , return 3

 Constraints:
 1 <= t <= 10⁹
 Each test case will call ping with strictly increasing values of t.
 At most 10⁴ calls will be made to ping.

 Related Topics Design Queue Data Stream 👍 842 👎 1198
 */

import java.util.PriorityQueue;
import java.util.Queue;

class NumberOfRecentCalls {
    public static void main(String[] args) {
//        [null, 1, 2, 3, 3]
        RecentCounter solution = new NumberOfRecentCalls().new RecentCounter();
        System.out.println(solution.ping(1));
        System.out.println(solution.ping(100));
        System.out.println(solution.ping(3001));
        System.out.println(solution.ping(3002));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class RecentCounter {
        private Queue<Integer> queue = new PriorityQueue<>();

        public RecentCounter() {
        }

        public int ping(int t) {
//        queue.add(t);
//        return queue.stream()
//                .filter(el -> el <= t
//                && el >= t - 3000)
//                .toList()
//                .size();
            queue.add(t);
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

//RecentCounter() Инициализирует счетчик нулевым количеством недавних запросов.
//int ping(int t) Добавляет новый запрос в момент времени t,
// где t — время в миллисекундах, и возвращает количество запросов,
// поступивших за последние 3000 миллисекунд (включая новый запрос).
// В частности, верните количество запросов, поступивших в диапазоне [t - 3000, t].
//Гарантируется, что при каждом вызове ping значение t будет строго больше, чем при предыдущем вызове.
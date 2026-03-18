package org.my.edy;

/**
 * 387
 Given a string s, find the first non-repeating character in it and return its
 index. If it does not exist, return -1.

 Example 1:
 Input: s = "leetcode"
 Output: 0

 Explanation:

 The character 'l' at index 0 is the first character that does not occur at any
 other index.

 Example 2:
 Input: s = "loveleetcode"
 Output: 2

 Example 3:
 Input: s = "aabb"
 Output: -1

 Constraints:

 1 <= s.length <= 10⁵
 s consists of only lowercase English letters.

 Related Topics Hash Table String Queue Counting 👍 9776 👎 334
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        Solution solution = new FirstUniqueCharacterInAString().new Solution();
        System.out.println(solution.firstUniqChar_map("leetcode")); //0
        System.out.println(solution.firstUniqChar_map("loveleetcode")); //2
        System.out.println(solution.firstUniqChar_map("aabb")); //-1
        System.out.println(solution.firstUniqChar("leetcode")); //0
        System.out.println(solution.firstUniqChar("loveleetcode")); //2
        System.out.println(solution.firstUniqChar("aabb")); //-1
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int firstUniqChar_map(String s) {
            Queue<Character> characterQueue = new ArrayDeque<>();
            Map<Character, Integer> map = new HashMap<>();
            for (char ch : s.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            for (char ch : s.toCharArray()) {
                if (map.get(ch) == 1) {
                    return s.indexOf(ch);
                }
            }
            return -1;
        }

        //       using Queue
        public int firstUniqChar(String s) {
            int[] temp = new int[26];
            Queue<Character> val = new ArrayDeque<>();
            for (char ch : s.toCharArray()) {
                val.add(ch);
                temp[ch - 'a'] ++;
                while (!val.isEmpty() && temp[val.peek() - 'a'] > 1) {
                    val.remove();
                }
            }
            return val.isEmpty() ? -1 : s.indexOf(val.peek());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

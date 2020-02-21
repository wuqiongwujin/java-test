package Algorithm.hash;


/**
 * @Description 无重复字符的最长子串
 * @date 2019/12/2
 */
public class LongestSubstring {

    public static void main(String[] args) {
        String str = "abcabcbb";
        LongestSubstring t = new LongestSubstring();
        t.lengthOfLongestSubstring(str);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String tmp = "";
        char[] chars = s.toCharArray();
        int i=0,j=0;
        int maxLength = 1;
        for (;j<chars.length;j++) {
            tmp = s.substring(i,j);
            if (i == j) {
            } else if (tmp.contains(String.valueOf(chars[j]))) {
                int duplicateIndex = s.indexOf(chars[j], i);
                i = duplicateIndex + 1;
            } else {
                int newMaxLength = j-i+1;
                maxLength = newMaxLength > maxLength ? newMaxLength : maxLength;
            }
        }
        return maxLength;
    }
}

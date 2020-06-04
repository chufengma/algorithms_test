package string;

public class KMP {

    public int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pattern.length() - 1) {
            // 1. 当 Pj = Pk时，next[j] = k
            // 2. 当 Pj != Pk, next[k] = k
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * KMP核心思想是，当发生不匹配的时候利用之前已经匹配的子串，从而避免从头开始重新匹配
     */
    public int search(String str, String pattern) {
        if (pattern.length() > str.length()) {
            return -1;
        }

        // 获取next数组，标识当发生不匹配的时候，该从子串的哪一位置重新开始比较
        int[] next = getNext(pattern);
        int subIndex = 0;
        int index = 0;

        // 依次匹配
        while(index < str.length() && subIndex < pattern.length()) {
            // 1. 如果比较的是第一个元素，
            //    或者发生对应位置字符相匹配，则比较下一个字符
            // 2. 如果发生字符不匹配，则调整子串匹配位置。
            if (subIndex == -1
                    || pattern.charAt(subIndex) == str.charAt(index)) {
                subIndex++;
                index++;
            } else {
                subIndex = next[subIndex];
            }
        }

        if (subIndex == pattern.length()) {
            return index - subIndex;
        }

        return -1;
    }

    public static String str = "KMP and several other asymptotically efficient string search methods like Boyer-Moore and Boyer-Moore-Horspool require extra memory -- in the case of KMP, O(m) memory, where m is the size of the substring being searched for. Although this is often acceptable, library designers have to make tradeoffs so that their code performs acceptably well in many different situations. Probably the main reason is that due to both the preprocessing required by KMP, and its more complex inner loop in the search phase, the constant factor slowdown may make it several times slower than the naive O(mn) substring search in many common cases (e.g. searching for a substring of < 10 characters in a long string). Also, someone searching for a large substring might be perplexed to find the runtime library running out of memory as it tries to allocate a large memory buffer for the KMP fallback function table.\n" +
            "\n" +
            "Perhaps a better question is why O(m+n)-time, O(1)-space algorithms like the Two-Way Algorithm have not yet been adopted by mainstream language runtime libraries. Again, the answer is likely to be the constant-factor slowdown in common cases. Nevertheless in at least one C runtime library implementation, the corresponding strstr() function has been updated to use this algorithm.\n" +
            "\n" +
            "Someone around me told me that for short string KMP is good enough, but if you need performance and you intend to use with large string then is not a good choice.\n" +
            "\n" +
            "Well, that's exactly backwards from my understanding, which is that the naive O(mn) substring search is good enough (and probably the best) for short strings, but will eventually lose out to asymptotically faster O(m+n) algorithms like KMP as the strings become longer.";

    public static String pattern = "question is why O(m+n)-time, O(1)-space algorithms like";

    public static void main(String[] args) {
        KMP kmp = new KMP();
        long start = System.nanoTime();
        int index = kmp.search(str, pattern);
        System.out.println("search:" + index + "， cost:" + (System.nanoTime() - start));

        long start2 = System.nanoTime();
        int index2 = str.indexOf(pattern);
        System.out.println("search system:" + index2 + "， cost:" + (System.nanoTime() - start2));
    }
}

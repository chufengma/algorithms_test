package string;

import javafx.util.Pair;

import java.util.*;

public class Taest {

    public static class PagePathItem {
        public String[] pagePathArray;
        public int repeatCount;

        @Override
        public String toString() {
            return "PagePathItem{" +
                    "pagePathArray=" + Arrays.toString(pagePathArray) +
                    ", repeatCount=" + repeatCount +
                    '}';
        }
    }

    public static class SubStrItem {
        public String subStr;
        public int start;
        public int end;

        public SubStrItem(String subStr, int start, int end) {
            this.subStr = subStr;
            this.start = start;
            this.end = end;
        }

        public int size() {
            return subStr.length();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubStrItem that = (SubStrItem) o;
            return Objects.equals(subStr, that.subStr);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subStr);
        }

        @Override
        public String toString() {
            return "SubStrItem{" +
                    "subStr='" + subStr + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private Map<Character, String> totalCharacterMap = new HashMap<>();
    private Map<String, Character> totalCharacterMapByChar = new HashMap<>();
    private int currentCharacterIndex = 1;

    /**
     * 将用户轨迹映射成字符串
     */
    public String pageDataMapToString(String[] pageData) {
        StringBuilder totalStrBuilder = new StringBuilder();
        for (String pageItem : pageData) {
            Character mapToChar = totalCharacterMapByChar.get(pageItem);
            if (mapToChar == null) {
                mapToChar = (char) (++currentCharacterIndex);
                totalCharacterMapByChar.put(pageItem, mapToChar);
                totalCharacterMap.put(mapToChar, pageItem);
            }
            totalStrBuilder.append(mapToChar);
        }
        return totalStrBuilder.toString();
    }

    /**
     * 在一个字符串中寻找连续出现的子串，并返回开始位置和重复次数
     *
     * @param totalStr               输入字符串
     * @param minRepetitiveThreshold 最小重复次数
     */
    private Map<SubStrItem, Integer> findAllRepetitiveSubStr(String totalStr, int minRepetitiveThreshold) {
        String str = totalStr;

        // 寻找所有子串序列
        List<SubStrItem> subStrList = new ArrayList<>();
        for (int i = 1; i < str.length() / 2 + 1; i++) {
            for (int j = 0; j < str.length(); j++) {
                int start = j;
                int end = Math.min(j + i, str.length());
                String subStr = str.substring(start, end);
                if (!subStrList.contains(subStr)) {
                    subStrList.add(new SubStrItem(subStr, start, end));
                }
            }
        }

        Map<SubStrItem, Integer> subStrMap = new HashMap<>();
        for (SubStrItem sub : subStrList) {
            int subLength = sub.size();
            for (int pos = 0; pos < str.length(); pos++) {
                int findCount = 0;

                // 寻找连续重复子串
                SubStrItem tmpSubStrItem = new SubStrItem("", - 1, - 1);
                for (int i = pos; i < str.length(); i += subLength) {
                    String subStr = str.substring(i, Math.min(i + subLength, str.length()));
                    if (subStr.equals(sub.subStr)) {
                        findCount++;
                        if (findCount >= minRepetitiveThreshold) {
                            int maxCount = findCount;
                            tmpSubStrItem.subStr = subStr;
                            if (subStrMap.containsKey(tmpSubStrItem)) {
                                maxCount = Math.max(subStrMap.get(tmpSubStrItem), maxCount);
                            }
                            subStrMap.put(sub, maxCount);
                        }
                    } else {
                        findCount = 0;
                    }
                }
            }
        }

        return subStrMap;
    }

    /**
     * 将寻找的子串映射成用户轨迹
     */
    private List<PagePathItem> translateSubStrToPagePathList(Map<SubStrItem, Integer> subStrMap) {
        List<PagePathItem> pagePathItems = new ArrayList<>();
        for (Map.Entry<SubStrItem, Integer> item : subStrMap.entrySet()) {
            PagePathItem pagePathItem = new PagePathItem();
            pagePathItem.repeatCount = item.getValue();

            SubStrItem subStrItem = item.getKey();
            String[] pathArray = new String[subStrItem.size()];
            for (int i = 0; i < subStrItem.size(); i++) {
                pathArray[i] = totalCharacterMap.get(subStrItem.subStr.charAt(i));
            }
            pagePathItem.pagePathArray = pathArray;
            pagePathItems.add(pagePathItem);
        }
        return pagePathItems;
    }

    public List<PagePathItem> antiTestForRepeatPath(String[] userPagePathArray, int minRepetitiveCount) {
        String totalString = pageDataMapToString(userPagePathArray);
        Map<SubStrItem, Integer> subStrMap = findAllRepetitiveSubStr(totalString, minRepetitiveCount);
        List<PagePathItem> pagePathItems = translateSubStrToPagePathList(subStrMap);
        return pagePathItems;
    }

    public static void main(String[] args) {
        Taest taest = new Taest();
        List<PagePathItem> pagePathItems = taest.antiTestForRepeatPath(new String[]{"appLaunch",
                "hotel", "hotelList", "hoteDetail", "orderItem",
                "hotel", "hotelList", "hoteDetail", "orderItem",
                "hotel", "hotelList", "hoteDetail", "orderItem",
                "hotel", "hotelList", "hoteDetail", "orderItem",
                "home", "myctrip", "home", "myctrip", "flight"}, 2);
        for (PagePathItem item : pagePathItems) {
            System.out.println("发现连续用户行为轨迹：" + item);
        }
    }
}

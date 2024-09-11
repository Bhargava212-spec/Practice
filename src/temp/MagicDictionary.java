package temp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MagicDictionary {
    Map<Integer, List<String>> map;

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        map = Arrays.stream(dictionary).collect(Collectors.groupingBy(String::length));
    }

    public boolean search(String searchWord) {
        var length = searchWord.length();
        if (!map.containsKey(length)) {
            return false;
        }
        var list = map.get(length);
        for (var str : list) {
            if (str.equals(searchWord)) {
                continue;
            }
            var start = 0;
            var end = str.length() - 1;
            var count = 0;
            while (start <= end) {
                if (count > 1) {
                    break;
                }
                if (str.charAt(start) != searchWord.charAt(start)) {
                    count++;
                }
                if (end != start && str.charAt(end) != searchWord.charAt(end)) {
                    count++;
                }
                start++;
                end--;
            }
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}

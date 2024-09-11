package temp;

public class WordFilter {
    String[] arr;
    public WordFilter(String[] words) {
        arr = words;
    }
    public int f(String pref, String suff) {
        var res = -1;
        var start = 0;
        var end = arr.length - 1;
        while (start <= end) {
            if (arr[start].startsWith(pref) && arr[start].endsWith(suff)) {
                res = start;
            }
            if (end != start && arr[end].startsWith(pref) && arr[end].endsWith(suff)) {
                res = end;
                break;
            }
            start++;
            end--;
        }
        return res;
    }
}

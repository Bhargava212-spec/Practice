package temp;

public class Bitset {
    StringBuilder sb;
    int len;

    public Bitset(int size) {
        sb = new StringBuilder();
        len = size;
        sb.append("0".repeat(size));
    }

    public void fix(int idx) {
        sb.setCharAt(idx, '1');
    }

    public void unfix(int idx) {
        sb.setCharAt(idx, '0');
    }

    public void flip() {
        var num = Integer.parseInt(sb.toString(), 2);
        var flip = ~num;
        var st = Integer.toBinaryString(flip);
        if (st.length() < len) {
            st = "1".repeat(len - st.length()) + st;
        }
        sb = new StringBuilder(st);

    }

    public boolean all() {
        var start = 0;
        var end = len - 1;
        while (start <= end) {
            if (sb.charAt(start) == '0' || sb.charAt(end) == '0') {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean one() {
        return sb.toString().contains("1");
    }

    public int count() {
        var res = 0;
        var start = 0;
        var end = len - 1;
        while (start <= end) {
            if (sb.charAt(start) == '1') {
                res++;
            }
            if (sb.charAt(end) == '1') {
                res++;
            }
            start++;
            end--;
        }
        return res;
    }

    public String toString() {
        return sb.toString();
    }

}


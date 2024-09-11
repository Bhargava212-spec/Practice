package temp;

import java.util.ArrayList;
import java.util.List;

public class BrowserHistory {

    List<String> al;
    int index = 0;

    public BrowserHistory(String homepage) {
        al = new ArrayList<>();
        al.add(homepage);
        index++;
    }

    public void visit(String url) {
        al.add(url);
        index = al.size();
    }

    public String back(int steps) {
        index = index - (steps);
        if (index < 0) {
            index = 0;
            return al.getFirst();
        }
        return al.get(index - 1);
    }

    public String forward(int steps) {
        index = index + (steps);
        if (index > al.size()) {
            index = al.size();
            return al.getLast();
        }
        return al.get(index - 1);
    }
}

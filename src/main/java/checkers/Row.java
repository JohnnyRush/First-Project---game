package checkers;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<Figure> listFigure = new ArrayList<>();

    public Row() {
        int i;
        for (i = 0; i < 8; i++) {
            listFigure.add(i, new None("none"));
        }
    }

    public List<Figure> getFigures() {
        return listFigure;
    }

    @Override
    public String toString() {
        String s = "|";
        for (Figure figure : listFigure) {
            s += figure.toString() + '|';
        }
        return s;
    }
}

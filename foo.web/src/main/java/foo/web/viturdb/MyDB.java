package foo.web.viturdb;

import java.util.ArrayList;

public class MyDB extends AbstractDB {
    static private ArrayList<String> db;

    public MyDB() {
        db = new ArrayList<>();
        db.add("foo");
    }

    public String getIndex(int index) {
        return db.get(index);
    }
}

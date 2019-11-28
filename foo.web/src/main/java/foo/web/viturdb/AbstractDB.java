package foo.web.viturdb;

import java.util.List;

public abstract class AbstractDB<E> {

    List<E> db;

    public E getIndex(){
        throw new UnsupportedOperationException();
    }

}

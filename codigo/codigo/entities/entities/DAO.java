package entities;

import java.io.IOException;

public interface DAO<T extends IDataToText> {

    public T getNext();

    public T[] getAll() throws IOException;

    public void add(T dado) throws IOException;

    public void addAll(T[] dado);

}
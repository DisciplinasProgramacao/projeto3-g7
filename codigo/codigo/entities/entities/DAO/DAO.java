package entities.DAO;

import java.io.IOException;

import entities.IDataToText;

public interface DAO<T extends IDataToText> {

    public T getNext();


    public T[] getAll() throws IOException;

    public void add(T dado) throws IOException;

    public void addAll(T[] dado);
}
package entities.interfaces;

import entities.Enums.Servicos;

public interface IFabrica<T> {
    public T create();

    public T create(Servicos servico);
}

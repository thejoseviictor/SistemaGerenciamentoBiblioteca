package main.dao;

import main.model.Usuario;

import java.util.List;

/**
 * <b>Esta classe implementa os métodos CRUD, para evitar repetição de código</b>
 *
 * @author Micael Muniz
 */
public interface CRUD<T> {
    public void criar(T objeto);
    public List<T> lerTodos();
    public T encontrarPorID(long id);
    public void atualizar(T objeto);
    public void deletar(T objeto);
    public void deletarTodos();
}

package com.dh.C23.repository;

import java.util.List;

public interface IDao <T>{
    List<T> listarElementos();
    T buscarXId(int id);
    T buscarXCriterio(String criterio);
    void eliminar(int id);
    T guardar(T  t);
    T actualizar(T t);
}

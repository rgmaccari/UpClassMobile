package dao;

import java.util.List;

public interface IGenericDao<Aluno> {
    long insert(Aluno aluno);
    long update(Aluno aluno);
    long delete (Aluno aluno);
    Aluno getById(long id);
    List<Aluno> getAll();
}

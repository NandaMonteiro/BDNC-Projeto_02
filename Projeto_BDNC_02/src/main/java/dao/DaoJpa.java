/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author NandaPC
 */
public class DaoJpa<T> implements Dao<T>{
    
    protected EntityManager entityManager;

    public DaoJpa() {
        this("persistence");
    }

    public DaoJpa(String uniPersistencia) {
        entityManager = Persistence.createEntityManagerFactory(uniPersistencia).createEntityManager();
    }

    @Override
    public boolean salvar(T obj) {
        System.out.println("Dao " + obj);
        EntityTransaction transacao = entityManager.getTransaction();

        try {
            transacao.begin();
            entityManager.persist(obj);
            System.out.println("Persistido" + obj);
            transacao.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transacao.isActive()) {
                transacao.rollback();
            }
            return false;
        }

    }

    @Override
    public boolean atualizar(T obj) {
        EntityTransaction transacao = entityManager.getTransaction();

        try {
            transacao.begin();
            entityManager.merge(obj);
            transacao.commit();
            return true;
        } catch (Exception ex) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(T obj) {
        EntityTransaction transacao = entityManager.getTransaction();

        try {
            transacao.begin();
            entityManager.remove(obj);
            transacao.commit();
            return true;
        } catch (Exception ex) {
            if (transacao.isActive()) {
                transacao.rollback();
            }
            return false;
        }
    }

    @Override
    public T buscar(Object chave, Class<T> entidade) {
        return entityManager.find(entidade, chave);
    }

    @Override
    public List<T> consultaLista(String consulta, Map<String, Object> parametros) {
        Query query = entityManager.createNamedQuery(consulta);
        if (parametros != null && !parametros.isEmpty()) {
            for (String parametro : parametros.keySet()) {
                query.setParameter(parametro, parametros.get(parametro));
            }
        }
        return query.getResultList();
    }

    @Override
    public T consultaSimples(String consulta, Map<String, Object> parametros) {
        Query query = entityManager.createNamedQuery(consulta);
        if (parametros != null && !parametros.isEmpty()) {
            for (String parametro : parametros.keySet()) {
                query.setParameter(parametro, parametros.get(parametro));
            }
        }
        System.out.println("PASSA AKI ");
        System.out.println(query.toString());
        return (T) query.getSingleResult();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}

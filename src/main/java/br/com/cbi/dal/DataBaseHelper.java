/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.dal;

import br.com.fs.api.dal.EntityManagerHelper;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

/**
 *
 * @author Tiago
 */
public class DataBaseHelper implements EntityManagerHelper {

    private final Map<String, ThreadLocal<EntityManager>> sessions = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(DataBaseHelper.class.getName());
    private static final Map<String, String> propMap = new HashMap();
    public final static int SALVAR = 0, ATUALIZAR = 1, DELETAR = 2;
    public final static String DATABASE_PU = "DATABASE_PU";

    public DataBaseHelper() {
    }

    @Override
    public boolean getOperation(int operation_type, Object object, String persistence_unit) {
        EntityManager session = getSession(persistence_unit);
        EntityTransaction transaction = session.getTransaction();
        try {
            transaction.begin();
            switch (operation_type) {
                case SALVAR:
                    LOG.info("Salvando registro no banco de dados");
                    session.persist(object);
                    session.getTransaction().commit();
                    break;
                case ATUALIZAR:
                    LOG.info("Atualizando registro no banco de dados");
                    session.merge(object);
                    session.getTransaction().commit();
                    break;
                case DELETAR:
                    LOG.info("Deletando registro no banco de dados");
                    session.remove(session.merge(object));
                    session.getTransaction().commit();
                    break;
            }
            this.closeSession(persistence_unit);
            return true;
        } catch (Exception e) {
            //transaction.rollback();
            this.closeSession(persistence_unit);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EntityManager getSession(String persistence_unit) {
        EntityManager session = null;
        if (sessions.isEmpty()) {
            sessions.put(persistence_unit, new ThreadLocal());
            session = sessions.get(persistence_unit).get();
            session = session == null ? DataBaseFactoryService.getEntityManagerFactory(persistence_unit, propMap).createEntityManager() : session;
        } else {
            session = sessions.get(persistence_unit).get();
            session = session == null ? DataBaseFactoryService.getEntityManagerFactory(persistence_unit, propMap).createEntityManager() : session;
        }
        return session;
    }

    @Override
    public void closeSession(String persistence_unit) {
        EntityManager session = null;
        if (!sessions.isEmpty()) {
            session = sessions.get(persistence_unit).get();
            LOG.info("Encerrando sessão do banco de dados");
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        LOG.info("Removendo Entity Manager desta sessão");
        sessions.remove(persistence_unit);
    }

    @Override
    public void closeAll() {
        LOG.info("Encerrando todas as sessões");
        sessions.clear();
    }

    @Override
    public Connection getConnection(String persistence_unit) {
        try {
            EntityManager entityManager = getSession(persistence_unit);
            Connection conn = ((EntityManagerImpl) (entityManager.getDelegate())).getServerSession().getAccessor().getConnection();
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List getObjectList(String strHQL, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional getObjectList(Class entity, String strHQL, Map parameters, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getObjectList(String strHQL, String strParam, Object valor, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional getObjectListNamedQuery(Class classType, String namedQuery, String[] strParam, Object[] valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createNamedQuery(namedQuery, classType);
            int cont = 0;
            if (strParam != null) {
                for (String p : strParam) {
                    query.setParameter(p, valor[cont++]);
                }
            }
            Optional<List<?>> objects = Optional.ofNullable(query.getResultList());
            this.closeSession(persistence_unit);
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            this.closeSession(persistence_unit);
            return Optional.empty();
        }
    }

    @Override
    public Optional getObjectNamedQuery(Class classType, String namedQuery, String strParam, Object valor, String persistence_unit) {
        try {
            EntityManager session = this.getSession(persistence_unit);
            session.getTransaction().begin();
            Query query = session.createNamedQuery(namedQuery, classType);
            if (strParam != null) {
                query.setParameter(strParam, valor);
            }
            Object object = query.getSingleResult();
            this.closeSession(persistence_unit);
            return Optional.ofNullable(object);
        } catch (Exception e) {
            //e.printStackTrace();
            this.closeSession(persistence_unit);
            return Optional.empty();
        }
    }

    @Override
    public List getObjectList(String strHQL, String strParam, Boolean valor, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObject(String strHQL, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObject(String strHQL, String strParam, Object valor, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObject(String strHQL, String[] strParam, String[] valor, String persistence_unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

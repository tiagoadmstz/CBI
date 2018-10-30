/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.dal;

import br.com.fs.api.dal.EntityManagerFactoryService;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago
 */
public class DataBaseFactoryService extends EntityManagerFactoryService {

    private static final Logger LOG = Logger.getLogger(DataBaseHelper.class.getName());
    private static final Map<String, EntityManagerFactory> FACTORIES = new HashMap<String, EntityManagerFactory>();
    private static final Map<String, String> PROPERTIES = new HashMap<String, String>();

    public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName, Map<String, String> propMap) {
        if (FACTORIES.containsKey(persistenceUnitName)) {
            return FACTORIES.get(persistenceUnitName);
        }

        EntityManagerFactory emf = null;

        if (!propMap.isEmpty()) {
            emf = Persistence.createEntityManagerFactory(persistenceUnitName, propMap);
        } else {
            emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        }

        FACTORIES.put(persistenceUnitName, emf);
        return emf;
    }

    public static void closeFactories() {
        LOG.info("Closing entity manager factories");
        FACTORIES.values().stream().forEach(fc -> {
            fc.close();
        });
        FACTORIES.clear();
    }

}

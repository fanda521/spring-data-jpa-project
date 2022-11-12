package com.jeffrey.spring_data_jpa_xml.untils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2020-09-12 18:18
 */
public class JpaUntils {
    public static EntityManagerFactory factory = null;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getManager(){
        EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }
}

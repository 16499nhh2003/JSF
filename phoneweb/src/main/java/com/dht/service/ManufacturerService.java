/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.service;

import com.dht.phoneweb.HibernateUtil;
import com.dht.pojo.Manufacturer;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Huy Hòa Nguyễn
 */
public class ManufacturerService {
    private final static SessionFactory factory = HibernateUtil.getFactory();
    public List<Manufacturer> getManufacturers() {
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> query = builder.createQuery(Manufacturer.class);
            Root<Manufacturer> root = query.from(Manufacturer.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public Manufacturer getManufacturerById(int manuId) {
        try (Session session = factory.openSession()) {
            return session.get(Manufacturer.class, manuId);
        }
    }
}

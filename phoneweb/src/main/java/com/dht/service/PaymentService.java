/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.service;

import com.dht.phoneweb.HibernateUtil;
import com.dht.pojo.Payment;
import com.dht.pojo.PaymentDetail;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Huy Hòa Nguyễn
 */
public class PaymentService {

    private final static SessionFactory factory = HibernateUtil.getFactory();

    public boolean add(Payment payment ,List<PaymentDetail> paymentDetail) {
        try ( Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.save(payment);
                for(PaymentDetail o: paymentDetail){
                    session.save(o);
                }
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return false;
    }
}

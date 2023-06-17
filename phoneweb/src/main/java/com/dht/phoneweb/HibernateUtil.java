/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.phoneweb;

import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.pojo.Payment;
import com.dht.pojo.PaymentDetail;
import com.dht.pojo.Product;
import com.dht.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author duonghuuthanh
 */
public class HibernateUtil {

    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/saledb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "Huyhoa2003#");
        conf.setProperties(props);

        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Manufacturer.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Payment.class);
        conf.addAnnotatedClass(PaymentDetail.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties())
                .build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }
}

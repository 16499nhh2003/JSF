/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.bean;

import com.dht.pojo.Payment;
import com.dht.pojo.PaymentDetail;
import com.dht.pojo.Product;
import com.dht.pojo.User;
import com.dht.service.PaymentService;
import com.dht.service.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huy Hòa Nguyễn
 */
@ManagedBean
@Named(value = "paymentBean")
@RequestScoped
public class PaymentBean {
    
    private static final ProductService productService = new ProductService();
    private static final PaymentService paymentService = new PaymentService();

    /**
     * Creates a new instance of PaymentBean
     */
    public PaymentBean() {
    }
    
    public String add() {
        Map<String, Object> cart = (Map<String, Object>) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("cart");
        if (cart != null) {
            Payment payment = new Payment();
            payment.setCreatedDate(new Date());
            payment.setUser((User) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("user"));
            List<PaymentDetail> details = new ArrayList<>();
            for (Object o : cart.values()) {
                Map<String, Object> d = (Map<String, Object>) o;
                Product product = productService.getProductById(Integer.parseInt(d.get("productId").toString()));
                PaymentDetail detail = new PaymentDetail();
                detail.setProduct(product);
                detail.setPayment(payment);
                detail.setCount(Integer.parseInt(d.get("count").toString()));
                details.add(detail);
            }
            if(paymentService.add(payment, details)){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cart");
                return "index?faces-redict=true";
            }
        }
        return "payment";
    }
    
}

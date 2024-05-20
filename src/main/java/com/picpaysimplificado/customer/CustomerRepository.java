package com.picpaysimplificado.customer;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, Long> {

    public Customer findByCpf(String cpf){
        return find("cpf", cpf).firstResult();
    }
}

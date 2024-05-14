package com.picpaysimplificado.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {
    
    @Inject
    CustomerRepository repository;

    @Transactional
    public void create(CustomerDTO customer) {
        Customer entity = new Customer();
        entity.setNomeCompleto(customer.nomeCompleto());
        entity.setCpf(customer.cpf());
        entity.setEmail(customer.email());
        entity.setSenha(customer.senha());
        repository.persist(entity);
    }

    public List<CustomerDTO> selectAll() {
        List<Customer> customers = repository.listAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c: customers){
            CustomerDTO dto = new CustomerDTO(c.getNomeCompleto(), c.getCpf(), c.getEmail(), c.getSenha());
            customerDTOS.add(dto);
        }
        return customerDTOS;
    }
}

package com.picpaysimplificado.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.h2.command.ddl.CreateSchema;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository repository;

    @Transactional
    public void create(CustomerDTO customer) throws Exception {
        if (repository.findByCpf(customer.cpf()) != null) {
            throw new Exception("CPF já cadastrado!");
        }

        Customer entity = new Customer();
        entity.setNomeCompleto(customer.nomeCompleto());
        entity.setIdade(customer.idade());
        entity.setCpf(customer.cpf());
        entity.setEmail(customer.email());
        entity.setSenha(customer.senha());
        repository.persist(entity);
    }

    public List<CustomerDTO> selectAll() {
        List<Customer> customers = repository.listAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            CustomerDTO dto = new CustomerDTO(c.getNomeCompleto(), c.getIdade(), c.getCpf(), c.getEmail(), c.getSenha());
            customerDTOS.add(dto);
        }
        return customerDTOS;
    }

    @Transactional
    public void updateAll(Long id, CustomerDTO customer) {
        Customer entity = repository.findById(id);
        entity.setNomeCompleto(customer.nomeCompleto());
        entity.setIdade(customer.idade());
        entity.setCpf(customer.cpf());
        entity.setEmail(customer.email());
        entity.setSenha(customer.senha());
        repository.persist(entity);
    }

    @Transactional
    public void updatePartial(Long id, CustomerDTO customer) {
        Customer entity = repository.findById(id);
        entity.setIdade(customer.idade());
        entity.setCpf(customer.cpf());
        entity.setEmail(customer.email());
        repository.persist(entity);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new Exception("Registro não encontrado!");
        }
        repository.deleteById(id);
    }

    public CustomerDTO selectById(Long id) {
        Customer entity = repository.findById(id);
        CustomerDTO dto = new CustomerDTO(
                entity.getNomeCompleto(),
                entity.getIdade(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getSenha());
        return dto;

    }
}

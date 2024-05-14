package com.picpaysimplificado.customer;

public record CustomerDTO(
        String nomeCompleto,
        String cpf,
        String email,
        String senha
) {
}

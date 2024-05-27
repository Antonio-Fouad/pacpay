package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.UsuarioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/transacao")
public class TransacaoResource {
    @Inject
    TransacaoService service;


    /////### Criar o relacionamento da tabela Usuario com transferencia , para a classe transferencia poder adicionar saldo a conta do usuario


    @POST
    public Response criarTransacao(TransacaoDTO transacaoDTO) throws Exception {
        try {
            Transacao novaTransacao = service.criarTransacao(transacaoDTO);
            return Response.status(Response.Status.OK).entity(novaTransacao).build();
        } catch (Exception e) {
            return Response.status(422).entity("{ \"msg\": \"" + e.getMessage() + "\"}").build();
        }
    }
}

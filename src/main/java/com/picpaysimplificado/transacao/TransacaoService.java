package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.Usuario;
import com.picpaysimplificado.transacao.TransacaoDTO;
import com.picpaysimplificado.usuario.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransacaoService {
    @Inject
    UsuarioService usuarioService;
//    @Inject
//    TransacaoService repository;
    TransacaoRepository repository = new TransacaoRepository();
    @Transactional
    public Transacao criarTransacao(TransacaoDTO transacao) throws Exception {

        Usuario pagador = usuarioService.findUserById(transacao.PagadorId());
        Usuario recebedor = usuarioService.findUserById(transacao.RecebedoId());

        usuarioService.validarTransacao(pagador, transacao.quantia());
        Transacao novaTransacao =  new Transacao();
        novaTransacao.setQuantia(transacao.quantia());
        novaTransacao.setEnviar(pagador);
        novaTransacao.setReceber(recebedor);

        pagador.setSaldo(pagador.getSaldo().subtract(transacao.quantia()));
        recebedor.setSaldo(recebedor.getSaldo().add(transacao.quantia()));

        repository.persist(novaTransacao);

        return novaTransacao;
    }

//    public List<TransacaoDTO> selectAll() {
//        List<Transacao> transacaos = repository.listAll();
//        List<TransacaoDTO> usuarioDTOS = new ArrayList<>();
//        for (Transacao t : transacaos) {
//            TransacaoDTO dto = new TransacaoDTO(t.getEnviar(),t.getReceber(),t.getQuantia());
//            usuarioDTOS.add(dto);
//        }
//        return usuarioDTOS;
//    }


}

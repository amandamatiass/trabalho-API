package com.loja.api.service;

import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Pedido;
import com.loja.api.repository.ClienteRepository;
import com.loja.api.repository.PedidoRepository;
import com.loja.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido criar(Pedido pedido) {
        // Valida se o cliente existe
        clienteRepository.findById(pedido.getClienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Cliente não encontrado com id: " + pedido.getClienteId()));

        // Valida se o produto existe
        produtoRepository.findById(pedido.getProdutoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Produto não encontrado com id: " + pedido.getProdutoId()));

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com id: " + id));
    }

    public Pedido atualizar(Long id, Pedido dados) {
        Pedido existente = buscarPorId(id);
        existente.setClienteId(dados.getClienteId());
        existente.setProdutoId(dados.getProdutoId());
        existente.setQuantidade(dados.getQuantidade());
        return pedidoRepository.save(existente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        pedidoRepository.deleteById(id);
    }
}

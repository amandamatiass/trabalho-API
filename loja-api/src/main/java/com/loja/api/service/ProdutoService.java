package com.loja.api.service;

import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Produto;
import com.loja.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com id: " + id));
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setPreco(dados.getPreco());
        existente.setEstoque(dados.getEstoque());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        buscarPorId(id); // valida existência antes de deletar
        repository.deleteById(id);
    }
}

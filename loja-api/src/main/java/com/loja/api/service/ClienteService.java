package com.loja.api.service;

import com.loja.api.exception.RecursoNaoEncontradoException;
import com.loja.api.model.Cliente;
import com.loja.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id));
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setClienteDesde(dados.getClienteDesde());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

package com.loja.api.controller;

import com.loja.api.model.Cliente;
import com.loja.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // POST /clientes — Criar cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        Cliente criado = service.criar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // GET /clientes — Listar todos
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /clientes/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /clientes/{id} — Atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.atualizar(id, cliente));
    }

    // DELETE /clientes/{id} — Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

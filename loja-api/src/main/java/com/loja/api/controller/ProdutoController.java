package com.loja.api.controller;

import com.loja.api.model.Produto;
import com.loja.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // POST /produtos — Criar produto
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto criado = service.criar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // GET /produtos — Listar todos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /produtos/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /produtos/{id} — Atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(service.atualizar(id, produto));
    }

    // DELETE /produtos/{id} — Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

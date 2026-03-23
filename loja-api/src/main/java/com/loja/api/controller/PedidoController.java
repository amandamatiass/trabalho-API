package com.loja.api.controller;

import com.loja.api.model.Pedido;
import com.loja.api.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // POST /pedidos — Criar pedido
    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido) {
        Pedido criado = service.criar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // GET /pedidos — Listar todos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /pedidos/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // PUT /pedidos/{id} — Atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(service.atualizar(id, pedido));
    }

    // DELETE /pedidos/{id} — Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

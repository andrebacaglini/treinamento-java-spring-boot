package br.com.cast.treinamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.service.CategoriaCursoService;

@RestController
@RequestMapping(value = "/categoria-curso")
public class CategoriaCursoController {

	@Autowired
	private CategoriaCursoService service;

	@GetMapping
	public ResponseEntity<List<CategoriaCursoDTO>> consultar() {
		return ResponseEntity.ok(service.consultarTodos());
	}

	@PostMapping
	public ResponseEntity<CategoriaCursoDTO> inserir(@Valid @RequestBody CategoriaCursoDTO categoriaCursoDTO) {
		return ResponseEntity.ok(service.salvar(categoriaCursoDTO));
	}

	@PutMapping
	public ResponseEntity<CategoriaCursoDTO> atualizar(@RequestBody CategoriaCursoDTO categoriaCursoDTO) {
		return ResponseEntity.ok(service.salvar(categoriaCursoDTO));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> excluir(@PathVariable(name = "id") long id) {
		service.excluir(id);
		return ResponseEntity.ok(id);
	}

	@PostMapping(value = "/consultar-paginado")
	public ResponseEntity<Page<CategoriaCursoDTO>> consultarPaginado(
			@RequestBody() PaginacaoDTO<CategoriaCursoDTO> filtroPaginado) {
		return ResponseEntity.ok(service.consultarPaginado(filtroPaginado));
	}

	@PostMapping(value = "/consultar-paginado-customizado")
	public ResponseEntity<Page<CategoriaCursoDTO>> consultarPaginadoCustomizado(
			@RequestBody() PaginacaoDTO<CategoriaCursoDTO> filtroPaginado) {
		return ResponseEntity.ok(service.consultarPaginado(filtroPaginado));
	}

}

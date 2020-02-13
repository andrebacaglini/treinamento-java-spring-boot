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

import br.com.cast.treinamento.model.dto.CursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.service.CursoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

	@Autowired
	private CursoService service;

	@ApiOperation(value = "API para consulta de todos os cursos")
	@GetMapping
	public ResponseEntity<List<CursoDTO>> consultar() {
		return ResponseEntity.ok(service.consultarTodos());
	}

	@ApiOperation(value = "API para cadastro de curso")
	@PostMapping
	public ResponseEntity<CursoDTO> inserir(@Valid @RequestBody CursoDTO cursoDTO) {
		return ResponseEntity.ok(service.salvar(cursoDTO));
	}

	@ApiOperation(value = "API para atualizar um curso.")
	@PutMapping
	public ResponseEntity<CursoDTO> atualizar(@RequestBody CursoDTO cursoDTO) {
		return ResponseEntity.ok(service.salvar(cursoDTO));
	}

	@ApiOperation(value = "API para excluir um curso por Id.")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CursoDTO> excluir(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(service.excluir(id));
	}

	@ApiOperation(value = "API para consultar cursos paginado, possibilitando filtrar por nome do curso.")
	@PostMapping(value = "/consultar-paginado")
	public ResponseEntity<Page<CursoDTO>> consultarPaginado(@RequestBody() PaginacaoDTO<CursoDTO> filtroPaginado) {
		return ResponseEntity.ok(service.consultarPaginado(filtroPaginado));
	}
	
//	@PostMapping(value = "/consultar-paginado-customizado")
//	public ResponseEntity<Page<CategoriaCursoDTO>> consultarPaginadoCustomizado(@RequestBody() PaginacaoDTO<CategoriaCursoDTO> filtroPaginado){
//		return ResponseEntity.ok(service.consultarPaginado(filtroPaginado));		
//	}

}

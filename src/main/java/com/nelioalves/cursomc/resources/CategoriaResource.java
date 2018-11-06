package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
	return ResponseEntity.ok(categoriaService.find(id));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
	Categoria obj = categoriaService.fromDTO(objDTO);
	obj = categoriaService.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
	Categoria obj = categoriaService.fromDTO(objDTO);
	obj.setId(id);
	obj = categoriaService.update(obj);
	return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
	categoriaService.delete(id);
	return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
	List<Categoria> list = categoriaService.findAll();
	List<CategoriaDTO> listDTo = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDTo);
    }
    
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
	    @RequestParam(value = "page", defaultValue = "0") Integer page, 
	    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
	    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
	    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
	Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
	Page<CategoriaDTO> listDTo = list.map(obj -> new CategoriaDTO(obj));
	return ResponseEntity.ok().body(listDTo);
    }
}

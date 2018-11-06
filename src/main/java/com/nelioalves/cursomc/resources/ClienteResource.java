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

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
	return ResponseEntity.ok(clienteService.find(id));
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
	Cliente obj = clienteService.fromDTO(objDTO);
	obj = clienteService.insert(obj);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
	Cliente obj = clienteService.fromDTO(objDTO);
	obj.setId(id);
	obj = clienteService.update(obj);
	return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
	clienteService.delete(id);
	return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
	List<Cliente> list = clienteService.findAll();
	List<ClienteDTO> listDTo = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDTo);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
	    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
	    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
	    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
	Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
	Page<ClienteDTO> listDTo = list.map(obj -> new ClienteDTO(obj));
	return ResponseEntity.ok().body(listDTo);
    }
}

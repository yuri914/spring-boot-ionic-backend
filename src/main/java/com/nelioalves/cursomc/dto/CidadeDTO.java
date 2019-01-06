package com.nelioalves.cursomc.dto;

import com.nelioalves.cursomc.domain.Cidade;

public class CidadeDTO {

    private Integer id;

    private String nome;

    //private Integer estadoId;
    
    public CidadeDTO(Cidade cidade) {
	this.id = cidade.getId();
	this.nome = cidade.getNome();
	//this.estadoId = cidade.getEstado().getId();
    }
    
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

//    public Integer getEstadoId() {
//        return estadoId;
//    }
//
//    public void setEstadoId(Integer estadoId) {
//        this.estadoId = estadoId;
//    }
    
}

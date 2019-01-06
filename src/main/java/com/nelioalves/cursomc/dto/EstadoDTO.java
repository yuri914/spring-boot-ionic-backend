package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Estado;

public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = -7174870145413056055L;
    
    private Integer id;
    
    private String nome;
    
    //private List<CidadeDTO> cidades = new ArrayList<>();
    
    public EstadoDTO(Integer id, String nome) {
	this.id = id;
	this.nome = nome;
	//this.cidades = cidades;
    }
    
    public EstadoDTO(Estado estado) {
	this.id = estado.getId();
	this.nome = estado.getNome();
	//this.cidades = estado.getCidades();
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

//    public List<CidadeDTO> getCidades() {
//        return cidades;
//    }
//
//    public void setCidades(List<CidadeDTO> cidades) {
//        this.cidades = cidades;
//    }
    
}

package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {

    private static final long serialVersionUID = -7174870145413056055L;

    private Integer numeroDeParcelas;
    
    public PagamentoComCartao() {
	super();
    }
    
    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
	super(id, estado, pedido);
	this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
    
}

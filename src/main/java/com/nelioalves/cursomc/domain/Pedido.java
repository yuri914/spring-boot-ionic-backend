package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = -7174870145413056055L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;
    
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();
    
    public Pedido() {
	super();
    }
    
    public Pedido(Integer id, Date instant, Cliente cliente, Endereco enderecoDeEntrega) {
	super();
	this.id = id;
	this.instant = instant;
	this.cliente = cliente;
	this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getInstant() {
	return instant;
    }

    public void setInstant(Date instant) {
	this.instant = instant;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
	return itens.stream().mapToDouble(ip -> ip.getSubtotal()).sum();
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pedido other = (Pedido) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	
	StringBuilder builder = new StringBuilder();
	builder.append("Pedido número: ");
	builder.append(getId());
	builder.append(", Instante: ");
	builder.append(sdf.format(getInstant()));
	builder.append(", Cliente: ");
	builder.append(getCliente().getNome());
	builder.append(", Situação do pagamento: ");
	builder.append(getPagamento().getEstado().getDescricao());
	builder.append("\nDetalhes:\n");
	for (ItemPedido ip : getItens()) {
	    builder.append(ip.toString());
	}
	builder.append("Valor total: ");
	builder.append(nf.format(getValorTotal()));
	return builder.toString();
    }
    
}

package com.tnicacio.bd2project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnicacio.bd2project.entities.pk.ItensPK;

@Entity
public class Itens implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItensPK id = new ItensPK();
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qtProduto;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double precoUnitario;
	
	public Itens() {}

	public Itens(Produto produto, Venda venda, Double qtProduto, Double precoUnitario) {
		super();
		id.setProduto(produto);
		id.setVenda(venda);
		this.qtProduto = qtProduto;
		this.precoUnitario = precoUnitario;
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}
	
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}

	public Double getQtProduto() {
		return qtProduto;
	}

	public void setQtProduto(Double qtProduto) {
		this.qtProduto = qtProduto;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public Double getSubTotal() {
		return qtProduto * precoUnitario;
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
		Itens other = (Itens) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

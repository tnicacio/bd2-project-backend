package com.tnicacio.bd2project.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tnicacio.bd2project.entities.enums.OrderStatus;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dtVenda;

	private Boolean isPagtoPrazo;
	private Integer nrParcelas;
	
	@Column(length = 1)
	private String status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dtAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "id.venda")
	private Set<Itens> itens = new HashSet<>();
	
	public Venda() {}

	public Venda(Integer id, Instant dtVenda, Boolean isPagtoPrazo, Integer nrParcelas, OrderStatus status,
			Instant dtAtualizacao, Usuario usuario) {
		super();
		this.id = id;
		this.dtVenda = dtVenda;
		this.isPagtoPrazo = isPagtoPrazo;
		this.nrParcelas = nrParcelas;
		setStatus(status);
		this.dtAtualizacao = dtAtualizacao;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Instant dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Boolean getIsPagtoPrazo() {
		return isPagtoPrazo;
	}

	public void setIsPagtoPrazo(Boolean isPagtoPrazo) {
		this.isPagtoPrazo = isPagtoPrazo;
	}

	public Integer getNrParcelas() {
		return nrParcelas;
	}

	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}

	public OrderStatus getStatus() {
		return OrderStatus.nameOfStatus(status);
	}

	public void setStatus(OrderStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}
	}

	public Instant getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Instant dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Set<Itens> getItens() {
		return itens;
	}
	
	public Double getTotal() {
		Double sum = itens.stream()
						.map(item -> item.getSubTotal())
						.reduce(0.0, (a,b) -> a + b);
		return sum;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

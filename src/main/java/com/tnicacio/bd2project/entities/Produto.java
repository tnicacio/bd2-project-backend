package com.tnicacio.bd2project.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double preco;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qtEstoque;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qtEstoqueMinimo;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qtReservada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dtAtualizacao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Produto() {}

	public Produto(Integer id, String descricao, Double preco, Double qtEstoque, Double qtEstoqueMinimo,
			Double qtReservada, Instant dtAtualizacao, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.qtEstoque = qtEstoque;
		this.qtEstoqueMinimo = qtEstoqueMinimo;
		this.qtReservada = qtReservada;
		this.dtAtualizacao = dtAtualizacao;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getQtEstoque() {
		return qtEstoque;
	}

	public void setQtEstoque(Double qtEstoque) {
		this.qtEstoque = qtEstoque;
	}

	public Double getQtEstoqueMinimo() {
		return qtEstoqueMinimo;
	}

	public void setQtEstoqueMinimo(Double qtEstoqueMinimo) {
		this.qtEstoqueMinimo = qtEstoqueMinimo;
	}

	public Double getQtReservada() {
		return qtReservada;
	}

	public void setQtReservada(Double qtReservada) {
		this.qtReservada = qtReservada;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

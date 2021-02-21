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
	private Double qt_estoque;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qt_estoque_minimo;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double qt_reservada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dt_atualizacao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Produto() {}

	public Produto(Integer id, String descricao, Double preco, Double qt_estoque, Double qt_estoque_minimo,
			Double qt_reservada, Instant dt_atualizacao, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.qt_estoque = qt_estoque;
		this.qt_estoque_minimo = qt_estoque_minimo;
		this.qt_reservada = qt_reservada;
		this.dt_atualizacao = dt_atualizacao;
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

	public Double getQt_estoque() {
		return qt_estoque;
	}

	public void setQt_estoque(Double qt_estoque) {
		this.qt_estoque = qt_estoque;
	}

	public Double getQt_estoque_minimo() {
		return qt_estoque_minimo;
	}

	public void setQt_estoque_minimo(Double qt_estoque_minimo) {
		this.qt_estoque_minimo = qt_estoque_minimo;
	}

	public Double getQt_reservada() {
		return qt_reservada;
	}

	public void setQt_reservada(Double qt_reservada) {
		this.qt_reservada = qt_reservada;
	}

	public Instant getDt_atualizacao() {
		return dt_atualizacao;
	}

	public void setDt_atualizacao(Instant dt_atualizacao) {
		this.dt_atualizacao = dt_atualizacao;
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

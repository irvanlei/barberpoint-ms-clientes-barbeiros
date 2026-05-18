package com.barberpoint.users.servicos.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServicoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer duracao;
    private LocalDateTime dataCriacao;

    public ServicoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}

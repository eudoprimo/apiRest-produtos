package com.produtos.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_transacoes")
public class Transacao implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum TipoTransacao {
        debito, credito
    }

    public enum TipoOrdem {
        compra, venda
    }

    public enum StatusOrdem {
        pendente, em_processamento, concluida, cancelada, falha
    }

    public enum StatusSafer {
        pendente, aprovado, reprovado, em_analise
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal preco;

    @NotNull
    @Positive
    @Column(name = "quantidade_de_moedas", nullable = false, precision = 20, scale = 8)
    private BigDecimal quantidadeDeMoedas;

    @NotNull
    @Column(name = "criacao_ordem", nullable = false)
    private LocalDateTime criacaoOrdem;

    @NotNull
    @Column(name = "confirmacao_ordem", nullable = false)
    private LocalDateTime confirmacaoOrdem;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao", nullable = false)
    private TipoTransacao tipoTransacao;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private String idUsuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ordem", nullable = false)
    private TipoOrdem tipoOrdem;

    @NotNull
    @Column(name = "tipo_ativo", nullable = false, length = 10)
    private String tipoAtivo;

    @NotNull
    @Column(name = "id_ordem", nullable = false, unique = true)
    private String idOrdem;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_ordem", nullable = false)
    private StatusOrdem statusOrdem;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_safer", nullable = false)
    private StatusSafer statusSafer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getQuantidadeDeMoedas() {
        return quantidadeDeMoedas;
    }

    public void setQuantidadeDeMoedas(BigDecimal quantidadeDeMoedas) {
        this.quantidadeDeMoedas = quantidadeDeMoedas;
    }

    public LocalDateTime getCriacaoOrdem() {
        return criacaoOrdem;
    }

    public void setCriacaoOrdem(LocalDateTime criacaoOrdem) {
        this.criacaoOrdem = criacaoOrdem;
    }

    public LocalDateTime getConfirmacaoOrdem() {
        return confirmacaoOrdem;
    }

    public void setConfirmacaoOrdem(LocalDateTime confirmacaoOrdem) {
        this.confirmacaoOrdem = confirmacaoOrdem;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoOrdem getTipoOrdem() {
        return tipoOrdem;
    }

    public void setTipoOrdem(TipoOrdem tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public String getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(String idOrdem) {
        this.idOrdem = idOrdem;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public StatusSafer getStatusSafer() {
        return statusSafer;
    }

    public void setStatusSafer(StatusSafer statusSafer) {
        this.statusSafer = statusSafer;
    }
}

package com.bancoeconomico.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Conta {

    private Integer numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;

    public Conta() {
        this.cliente = cliente;
        this.numero = new Random().nextInt();
        this.dataCriacao = LocalDate.now();
        this.saldo = BigDecimal.ZERO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setNumero(int abs) {
    }
}

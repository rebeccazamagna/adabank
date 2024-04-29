package com.bancoeconomico;

import com.bancoeconomico.model.*;
import com.bancoeconomico.service.factory.OperacoesBancariasFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        processarClientes();

    }


    static Consumer<Cliente> deposito(BigDecimal valor) {
        return cliente -> {
            Conta conta = cliente.getContas().get(0);
            OperacoesBancariasFactory.getInstance().get(cliente)
                    .depositar(cliente, conta.getNumero(), valor);
            print("deposito: " + valor + " saldo " + conta.getSaldo());
        };
    }




    static void print(Object o) {
        System.out.println(o);
    }

    static void processarClientes() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("C:\\arquivo\\pessoas.csv"));
             PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("C:\\arquivo\\clientes_importados.csv")))) {

            writer.println("nome;documento;PF/PJ;número da conta;saldo em conta");

            lines.skip(1)
                    .map(line -> line.split(","))
                    .filter(values -> values.length == 4)
                    .map(Main::createAccount)
                    .forEach(data -> {
                        String nome = (String) data[0];
                        String documento = (String) data[1];
                        int tipo = (int) data[2];
                        Conta conta = (Conta) data[3];

                        writer.printf("%s;%s;%s;%s;%s%n", nome, documento, tipo == 1 ? "PJ" : "PF",
                                conta.getNumero(), conta.getSaldo());
                    });

        }


    }

    private static Conta criarContaCorrente() {
        Conta conta = new Conta();
        conta.setSaldo(BigDecimal.valueOf(50));
        conta.setNumero(Math.abs(new Random().nextInt()));
        return conta;





    }


    private static Object[] createAccount(String[] values) {
        String nome = values[0];
        LocalDate nascimentoCriacao = LocalDate.parse(values[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String documento = values[2];
        int tipo = Integer.parseInt(values[3]);


        Conta conta = criarContaCorrente();


        return new Object[]{nome, documento, tipo, conta};
    }
}
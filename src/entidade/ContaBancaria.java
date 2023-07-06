package entidade;

import entities.enums.TipoConta;

public class ContaBancaria {

	private String nome;
	private int numero;
	private String agencia;
	private String banco;
	private TipoConta tipo;
	private double saldo;

	public ContaBancaria(String nome, int numero, String agencia, String banco, TipoConta tipo) {
		this.nome = nome;
		this.numero = numero;
		this.agencia = agencia;
		this.banco = banco;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public double getSaldo() {
		return saldo;
	}

	public String abrir() {
		return "\nConta aberta com as seguintes informações: " + "\n\tCliente: " + nome + "\n\tNúmero da conta: "
				+ numero + "\n\tAgência: " + agencia + "\n\tNome do Banco: " + banco + "\n\tTipo de conta: " + tipo
				+ "\n\tSaldo atual: R$ " + String.format("%.2f", saldo);
	}

	public void encerrar() {
		if (saldo >= 0) {
			double saldoAtual = this.saldo;
			this.saldo = 0.0;
			this.tipo = TipoConta.CONTA_ENCERRADA;
			System.out.println("\nConta encerrada. Saldo final: " + saldoAtual);
		} else {
			System.out.println("Não é possivel encerrar a conta com saldo negativo.");
		}
	}

	public void consultarSaldo() {
		System.out.println("\nSaldo atual: " + saldo);
	}

	public void depositar(double valor) {
		this.saldo = saldo + valor;
		System.out.println("Valor depositado: R$ " + valor);
	}

	public void sacar(double valor) {
		if (tipo != TipoConta.ENCERRAR && tipo != TipoConta.CONTA_ENCERRADA) {
			if (this.saldo >= valor) {
				this.saldo -= valor;
				System.out.println("Valor debitado: R$ " + valor);
			} else {
				System.out.println("Saldo insuficiente para debitar o valor desejado.");
			}
		} else {
			System.out.println("Conta encerrada, não é possível realizar saque.");
		}
	}
}

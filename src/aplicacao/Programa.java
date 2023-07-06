package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import entidade.ContaBancaria;
import entities.enums.TipoConta;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TimeUnit timeUnit = TimeUnit.SECONDS;
		long sleepTimeInSeconds = 3;

		System.out.println("***SEJA MUITO BEM VINDO(A) AO BANCO VV!***");
		System.out.println();
		System.out.println("Para começarmos favor preencher os dados solicitados abaixo: ");

		try {

			System.out.print("\nFavor informar o seu nome: ");
			String nome = sc.nextLine();

			System.out.print("Digite o número da conta: ");
			int numero = sc.nextInt();

			System.out.print("Digite o nome da agência do bairro que você se localiza: ");
			String agencia = sc.next();

			System.out.print("Digite o nome do banco: ");
			String banco = sc.next();

			TipoConta tipoConta = null;
			while (tipoConta != TipoConta.CONTA_CORRENTE && tipoConta != TipoConta.CONTA_POUPANÇA
					&& tipoConta != TipoConta.CORRENTE && tipoConta != TipoConta.POUPANÇA) {
				System.out.print("Informe seu tipo de conta(Corrente ou Poupança): ");
				tipoConta = TipoConta.valueOf(sc.next().toUpperCase());
			}

			ContaBancaria conta = new ContaBancaria(nome, numero, agencia, banco, tipoConta);

			boolean encerrarPrograma = false;

			while (!encerrarPrograma) {
				timeUnit.sleep(sleepTimeInSeconds);

				System.out.println("\nTemos as seguintes opções de movimentação:");
				System.out.println("\n[1] - Abrir conta");
				System.out.println("[2] - Encerrar conta");
				System.out.println("[3] - Consultar saldo");
				System.out.println("[4] - Depositar");
				System.out.println("[5] - Sacar");
				System.out.println("[6] - Sair");
				System.out.print("\nSelecione a opção desejada informando o número:");

				int opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					System.out.println(conta.abrir());
					break;	
				case 2:
					conta.encerrar();
					break;
				case 3:
					conta.consultarSaldo();
					break;
				case 4:
					if (!conta.getTipo().equals(TipoConta.CONTA_ENCERRADA) && !conta.getTipo().equals(TipoConta.ENCERRAR)) {
						System.out.println();
						System.out.print("Digite o valor que deseja depositar: ");
						double valorDepositado = sc.nextDouble();
						conta.depositar(valorDepositado);
						break;
					} else {
						System.out.println("Conta encerrada, não é possível realizar depósito.");
					}

				case 5:
					if (!conta.getTipo().equals(TipoConta.CONTA_ENCERRADA) && !conta.getTipo().equals(TipoConta.ENCERRAR)) {
						System.out.println();
						System.out.print("Digite o valor que deseja sacar: ");
						double valorSacado = sc.nextDouble();
						conta.sacar(valorSacado);
						break;
					} else {
						System.out.println("Conta encerrada, não é possível realizar saque!.");
					}
					
				case 6:
					encerrarPrograma = true;
					break;
				default:
					System.out.println();
					System.out.println("Opção inválida. Tente novamente.");
				}
			}

			System.out.println("Programa encerrado.");
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida. O programa será encerrado.");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}

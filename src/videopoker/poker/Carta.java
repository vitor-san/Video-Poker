package videopoker.poker;

import videopoker.util.Random;

/**
 * Implementa uma carta de baralho tradicional.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 */
public class Carta {
	
	private int numero;		// 1 a 13, sendo A = 1, [...], J = 11, Q = 12, K = 13
	private int naipe;		// 1 a 4, sendo 1 = Ouros, 2 = Espadas, 3 = Copas e 4 = Paus
	
	/**
	 * Cria uma carta com naipe e número aleatórios
	 */
	public Carta() {
		Random rand = new Random();
		
		this.numero = rand.getIntRand(1, 14);
		this.naipe = rand.getIntRand(1, 5);
	}
	
	/**
	 * Cria uma carta com naipe e número definidos pelo usuário.
	 * @param num número da carta (com A = 1 e K = 13).
	 * @param naipe	String com o naipe da carta.
	 * @throws IllegalArgumentException	a exceção é jogada caso um número menor
	 * que 1 ou maior que 13 seja enviado, ou caso a String para o naipe esteja
	 * digitada incorretamente.
	 */
	public Carta(int num, String naipe) throws IllegalArgumentException {
		if (num < 1 || num > 13) {
			System.out.println("Número inválido!");
			throw new IllegalArgumentException();
		}
		this.numero = num;
		
		naipe = naipe.replaceAll(" ", "");		// retiro todos os espacos desnecessarios da String
		naipe = naipe.toLowerCase();		// faco a String ficar em lower case para facilitar a comparacao
		
		switch (naipe) {
		case "ouros":
			this.naipe = 1;
			break;
		case "espadas":
			this.naipe = 2;
			break;
		case "copas":
			this.naipe = 3;
			break;
		case "paus":
			this.naipe = 4;
			break;
		default:
			System.out.println("Nome de naipe inválido!");
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Transforma a carta em uma ASCII art. 
	 */
	@Override
	public String toString() {
		String cb = "+-------+\n";	// cima e baixo
		String l2 = "";	// linha 2
		String l3 = "";	// linha 3
		String l4 = "";	// linha 4
		String l5 = ""; // linha 5
		String l6 = "";	// linha 6
		
		switch (this.getNumero()) {
			case 1:
				l2 = "| A     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     A |\n";
				break;
			case 2:
				l2 = "| 2     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     2 |\n";
				break;
			case 3:
				l2 = "| 3     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     3 |\n";
				break;
			case 4:
				l2 = "| 4     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     4 |\n";
				break;
			case 5:
				l2 = "| 5     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     5 |\n";
				break;
			case 6:
				l2 = "| 6     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     6 |\n";
				break;
			case 7:
				l2 = "| 7     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     7 |\n";
				break;
			case 8:
				l2 = "| 8     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     8 |\n";
				break;
			case 9:
				l2 = "| 9     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     9 |\n";
				break;
			case 10:
				l2 = "| 10    |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|    10 |\n";
				break;
			case 11:
				l2 = "| J     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     J |\n";
				break;
			case 12:
				l2 = "| Q     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     Q |\n";
				break;
			case 13:
				l2 = "| K     |\n";
				l3 = "|       |\n";
				l5 = "|       |\n";
				l6 = "|     K |\n";
				break;
		}
		
		switch (this.getNaipe()) {
			case 1:
				l4 = "|   ♦   |\n";
				break;
			case 2:
				l4 = "|   ♠   |\n";
				break;
			case 3:
				l4 = "|   ♥   |\n";
				break;
			case 4:
				l4 = "|   ♣   |\n";
		}
		
		return cb + l2 + l3 + l4 + l5 + l6 + cb;
	}
	

	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getNaipe() {
		return naipe;
	}


	public void setNaipe(int naipe) {
		this.naipe = naipe;
	}
	
}
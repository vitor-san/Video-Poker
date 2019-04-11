/**
 * Classe responsável por administrar o jogo, recebendo as apostas, realizando os pagamentos, e decidindo quando se deve embaralhar o baralho.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 */
public class Dealer {
	private Jogador player;
	
	/**
	 * Instancia um jogador com um determinado número de fichas.
	 * @param valor - número de fichas que o jogador receberá.
	 */
	public Dealer(int valor) {
		player = new Jogador(valor);
	}
	
	/**
	 * Da 5 cartas ao jogador
	 */
	public void daCartas() {
		player.daCartas();
	}
	
	/**
	 * Embaralha o baralho
	 */
	public void embaralha() {
		player.recolheCartasEmbaralha();
	}
	
	/**
	 * Troca as cartas enviadas pela string s.
	 * Se uma string inválida for enviada, o programa irá assumir que o jogador não quis fazer troca alguma.
	 * @param s - a string contendo as cartas q devem ser trocadas
	 * @return - o vetor com as novas cartas do jogador
	 */
	public Carta[] daCartas (String s){
		return player.daCartas(s);
	}
	
	
	/**
	 * Realiza a aposta, verificando se é possivel apostar
	 * @param valorAposta - o valor a ser apostado
	 * @throws IllegalArgumentException - caso a aposta seja maior q o saldo
	 */
	public void aposta(int valorAposta) throws IllegalArgumentException {
		//verifica o saldo
		if(valorAposta > player.getSaldo()){
			throw new IllegalArgumentException("Fichas Insuficientes");
		}
		//caso tenha saldo a aposta é feita
		else {
			player.aposta(valorAposta);
		}
	}
	
	/**
	 * Verifica o jogo feito e paga o valor da aposta
	 */
	public void pagarAposta() {
		//armazena as cartas do jogador
		Carta[] cartas = new Carta[5];
		
		//copia as cartas do jogador
		cartas = player.cartasDoJogador();
		
		//declara um vetor de frequencia dos naipes
		int[] frequenciaNaipe = new int[4];
		for (int i = 0; i < 4; i++) {
			frequenciaNaipe[i] = 0;
		}
		
		//declara um vetor de frequencia dos numeros
		//OBS: a carta 14 é o AS. O AS é representado duas vezes para se verificar sequencias, pois o AS pode fazer
		// A-1-2-3-4 ou 10-J-Q-K-A
		int[] frequenciaNumeros = new int [14];
		for (int i = 0; i < 14; i++) {
			frequenciaNumeros[i] = 0;
		}
		
		//preenche o vetor de frequencia dos numeros
		for (int i = 0; i < 5; i++) {
			int numeroDaCarta = cartas[i].getNumero();
			frequenciaNumeros[numeroDaCarta-1]++;
			if (numeroDaCarta == 1) frequenciaNumeros[13]++;
		}
		
		//preenche o vetor de frequencia dos naipes
		for (int i = 0; i < 5; i++) {
			int naipeDaCarta = cartas[i].getNaipe();
			frequenciaNaipe[naipeDaCarta-1]++;
		}
		
		/*
		 * Temos:
		 * 0 - 2 pares
		 * 1 - trinca
		 * 2 - quadra
		 * 3 - flush
		 * 4 - sequencia
		 * 5 - Royal
		 * 6 - 1 par
		 */
		
		//vetor boolean que registra os jogos feitos
		boolean[] jogos = new boolean[7];
		for (int i = 0; i < 7; i++) {
			jogos[i] = false;
		}
		
		int contador = 0;
		
		//verifica par e 2 pares
		for (int i = 0; i < 13; i++) {
			if(frequenciaNumeros[i] == 2) {
				contador ++;
			}
		}
		//caso tenha dois pares
		if(contador == 2) jogos[0] = true;
		//caso tenha 1 par
		if(contador >= 1) jogos[6] = true;
		
		
		contador = 0;
		//verifica trincas
		for (int i = 0; i < 13; i++) {
			if(frequenciaNumeros[i] == 3) {
				contador ++;
			}
		}
		//caso tenha trincas
		if (contador >= 1) jogos[1] = true;
		
		contador = 0;
		//verifica quadras
		for (int i = 0; i < 13; i++) {
			if (frequenciaNumeros[i] == 4) {
				contador ++;
			}
		}
		//caso tenha quadras
		if (contador >= 1) jogos[2] = true;
		
		contador = 0;
		//verifica flush
		for (int i = 0; i < 4; i++) {
			if (frequenciaNaipe[i] == 5) {
				contador ++;
			}
		}
		//caso tenha flush
		if(contador >= 1) jogos[3] = true;
		
		contador = 0;
		int i = 0;
		//verifica sequencias
		for (i = 0; i < 14; i++) {
			if (frequenciaNumeros[i] == 1) {
				contador++;
			}
			else {
				contador = 0;
			}
			//caso ele encontre 5 cartas seguidas o contador vai estar em 5
			if (contador == 5) break;
		}
		//caso tenha sequencia
		if(contador == 5) jogos[4] = true;
		//caso tenha sequencia e seja a maior do jogo (10-A)
		if(contador == 5 && i == 14) jogos[5] = true;

		
		//verifica qual jogo o jogador fez
		
		//Royal Straight Flush
		if (jogos[5] == true && jogos[3] == true) {
			player.recebePremio(200);
		}
		
		//Straight Flush
		else if (jogos[4] == true && jogos[3] == true) {
			player.recebePremio(100);
		}
		
		//Quadra
		else if (jogos[2] == true) {
			player.recebePremio(50);
		}
		
		//Full House
		else if (jogos[6] == true && jogos[1] == true) {
			player.recebePremio(20);
		}
		
		//Flush
		else if (jogos[3] == true) {
			player.recebePremio(10);
		}
		
		//Straight
		else if (jogos[4] == true) {
			player.recebePremio(5);
		}
		
		//Trinca
		else if (jogos[1] == true) {
			player.recebePremio(2);
		}
		
		//Dois pares 
		else if (jogos[0] == true) {
			player.recebePremio(1);
		}
		
		//Um par ou nada
		else {
			player.recebePremio(0);
		}
	}
	
	@Override
	/**
	 * Retorna o desenho das 5 cartas do jogador
	 */
	public String toString() {
		return player.toString();
	}
	
	/**
	 * Retorna quantas fichas o jogador tem
	 * @return - numero de fichas
	 */
	public int fichas() {
		return player.getSaldo();
	}
}

package videopoker.poker;

/**
 * Classe feita para administrar os jogadores, contendo a quantidade de dinheiro que cada um tem
 * e suas cartas.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 *
 */
public class Jogador {
	
	private Carta[] cartas;
	private int fichas;
	private Baralho baralho;
	private int fichasApostadas;
	
	/**
	 * Método construtor do jogador. Inicia um jogador com um baralho e 200 fichas.
	 */
	public Jogador(){
		cartas = new Carta[5];
		for(int i = 0; i < 5; i++){
			cartas[i] = null;
		}
		
		fichas = 200;
		baralho = new Baralho();
		fichasApostadas = 0;
	}
	
	/**
	 * Método construtor do jogador. Inicia um jogador com o número de fichas indicadas. 
	 * @param fichas - o numero de fichas que o jogador vai receber
	 */
	public Jogador(int fichas) {
		cartas = new Carta[5];
		for(int i = 0; i < 5; i++){
			cartas[i] = null;
		}
		this.fichas = fichas;
		baralho = new Baralho();
	}
	
	/**
	 * recolhe todas as cartas, e reembaralha o baralho
	 */
	public void recolheCartasEmbaralha() {
		//recolhe as cartas do jogador
		for(int i = 0; i < 5; i++){
			cartas[i] = null;
		}
		//embaralha o baralho
		baralho.embaralha();
	}
	
	/**
	 * da 5 cartas ao jogador
	 * @return - um vetor de int com as cartas dadas
	 */
	public Carta[] daCartas(){
		for(int i = 0; i < 5; i++) {
			cartas[i] = baralho.pegaCarta();
		}
		return cartas;
	}
	
	/**
	 * Troca as carta do jogador de acordo com o vetor passado (metodo interno da classe).
	 * @param quais - vetor boolean que mostra quais cartas seram trocadas
	 * @return - o vetor de cartas do jogador apos receber as novas cartas
	 */
	private Carta[] daCartas(boolean[] quais){
		for (int i = 0; i < 5; i++) {	
			if (quais[i]) {
				cartas[i] = baralho.pegaCarta();
			}
		}
		
		return cartas;
	}
	
	/**
	 * Dada uma string com quais cartas o jogador quer trocar, a função troca essas cartas.
	 * @param s - a string contendo quais cartas serão trocadas
	 * @return - o vetor de cartas do jogador após receber as novas cartas
	 */
	public Carta[] daCartas(String s){
		int convertido;
		char converter;
		
		//cria o vetor de boolean com quais cartas seram trocadas
		boolean quais[] = new boolean[5];
		for(int i = 0; i < 5; i++) {
			quais[i] = false;
		}
		
		//le o vetor de string e transforma ele no de boolean q sera usado para trocar as cartas
		for(int i = 0; i < s.length(); i++) {
			//caso seja um numero
			if(s.charAt(i) != ' ') {
				//verifica se � um valido
				if(i == 0 && i != (s.length()-1) && s.charAt(i+1) == ' '){
					converter = s.charAt(i);
					convertido =  Character.getNumericValue(converter);
					if(1 <= convertido && convertido <= 5)
					quais[convertido - 1] = true;
				}
				
				//verifica se � um valido
				else if(i != 0 && i != (s.length()-1) && s.charAt(i+1) == ' ' && s.charAt(i-1) == ' '){
					converter = s.charAt(i);
					convertido =  Character.getNumericValue(converter);
					if(1 <= convertido && convertido <= 5)
					quais[convertido - 1] = true;
					}
				//verifica se � um valido
				else if(i != 0 && i == (s.length()-1) && s.charAt(i-1) == ' '){
					converter = s.charAt(i);
					convertido =  Character.getNumericValue(converter);
					if(1 <= convertido && convertido <= 5)
					quais[convertido - 1] = true;
					}
				//verifica se e um valido
				else if(s.length() == 1){
					converter = s.charAt(i);
					convertido =  Character.getNumericValue(converter);
					if(1 <= convertido && convertido <= 5)
					quais[convertido - 1] = true;
					}
			}
		}
		
		return daCartas(quais);
	}
	
	/**
	 * Pega dinhero do jogador para realizar a aposta, n verifica saldo, caso seja maior que o saldo do jogado
	 * o mesmos ficara negativo
	 * @param valorAposta - o valor a ser apostado
	 */
	public void aposta(int valorAposta) {
			fichas -= valorAposta;
			fichasApostadas = valorAposta;
	}
	
	/**
	 * Add valor as fichas do jogador apostadas vezes o valor
	 * @param valor - valor a ser multiplicado a aposta (OBS: 0  signifca n receber nada)
	 */
	public void recebePremio(int valor) {
			fichas += valor*fichasApostadas;
			fichasApostadas = 0;
	}
	
	/**
	 * retorna a quantidade de fichas do jogador
	 * @return - quantidade de fichas
	 */
	public int getSaldo() {
		return fichas;
	}
	
	/**
	 * Devolve um vetor de int com as cartas que o jogador possui
	 * @return - cartas que o jogar tem na mao
	 */
	public Carta[] cartasDoJogador() {
		return cartas;
	}
	
	@Override
	/**
	 * A funcao desenha com caracteres as cartas do jogador
	 * @return - um conjunto de caracteres das cartas do jogador
	 */
	public String toString() {
		
		String result = "";
		String space = "\t\t";
		String[][] lines = new String[5][7];	//de 0 a 6 representa a carta 1, de 7 a 13 representa a carta 2 e assim por diante...
		
		for (int i = 0; i < 5; i++) {
			lines[i] = this.cartas[i].toString().split("\n");
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				result += (lines[j][i] + space);
			}
			result += "\n";
		}
		
		return result;
	}
	
}



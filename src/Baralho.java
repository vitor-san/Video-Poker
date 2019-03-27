/**
 * Implementa um baralho, utilizando como base a classe Carta.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 */
public class Baralho {
	private Carta[] baralho;
	private int topoBaralho;	//posicao da carta que esta no topo do baralho
	
	/**
	 * Cria um novo baralho com 52 cartas. 
	 * As cartas virão em ordem (de A a K), sendo que a cada bloco de 13 cartas os naipes também estarão em ordem (ouro, espadas, copas, paus).
	 * Ou seja, o baralho NÃO vira embaralhado. Para tanto, utilize o método embaralhar().
	 */
	public Baralho() {
		baralho = new Carta[52];
		
		//primeiro, crio todas as cartas de ouros
		for (int i = 0; i < 13; i++) {
			baralho[i] = new Carta(i+1, "Ouros");
		}
		//em seguida, todas as cartas de espadas
		for (int i = 0; i < 13; i++) {
			baralho[i+13] = new Carta(i+1, "Espadas");
		}
		//depois, todas as de copas
		for (int i = 0; i < 13; i++) {
			baralho[i+26] = new Carta(i+1, "Copas");
		}
		//por fim, as de paus
		for (int i = 0; i < 13; i++) {
			baralho[i+39] = new Carta(i+1, "Paus");
		}
		//no baralho, a carta do topo sera a da ultima posicao (ja que o baralho se comporta como uma pilha)
		this.topoBaralho = 51;	//posicao da ultima carta
	}
	
	/**
	 * Embaralha o baralho, utilizando todas as suas cartas.
	 * O topo do baralho é atualizado após o embaralhamento.
	 */
	public void embaralha() {
		//baralho auxiliar, utilizado para embaralhar
		Carta[] baralhoAux = new Carta[52];
		Carta cartaAtual;
		int novaPosicao;
		Random aleat = new Random();
		
		//coloca em todas as posicoes que nenhuma delas possui carta ainda
		for (int i = 0; i < 52; i++) {
			baralhoAux[i] = null;
		}
		
		for (int i = 0; i < 52; i++) {
			//escolhe a proxima carta do baralho
			cartaAtual = baralho[i];
			//gera um nova posicao aleatoria para a carta
			novaPosicao = aleat.getIntRand(52);
			//faz um sondagem linear da posicao da carta
			while (baralhoAux[novaPosicao] != null) {
				novaPosicao = novaPosicao + 1;
				novaPosicao = novaPosicao%52;	//para que ela se mantenha no "range" [0, 51]
			}
			//coloca a carta em sua nova posicao no baralho auxiliar
			baralhoAux[novaPosicao] = cartaAtual;
		}

		//remonta o baralho de acordo com o auxiliar
		for (int i = 0; i < 52; i++) {
			this.baralho[i] = baralhoAux[i];
		}
				
		//volta o topo do baralho para o inicial
		topoBaralho = 51;
	}
	
	/**
	 * Retorna a carta que está no topo do baralho.
	 * @return a carta que estava no topo.
	 * @throws ArrayIndexOutOfBoundsException o baralho não possui mais cartas.
	 */
	public Carta pegaCarta() throws ArrayIndexOutOfBoundsException{
		if (this.topoBaralho < 0) {
			throw new ArrayIndexOutOfBoundsException("Baralho Vazio");
		} 
		else {
			topoBaralho--;
			return baralho[topoBaralho + 1];
		}
	}
}

import java.io.IOException;

/**
 * Implementa o jogo Video Poker utilizando as classes Baralho, Jogador e Dealer.
 * O jogo utiliza um menu para interagir com o jogador.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 */
public class VideoPoker {
	/**
	 * Roda o jogo.
	 * @param args
	 * @throws IOException caso seja digitado um valor inválido aos métodos provenientes da classe EntradaTeclado
	 */
	public static void main (String[] args) throws IOException {
		
		Dealer dealer = new Dealer(200);	// o Dealer dá ao jogador 200 fichas para ele começar
		boolean sair = false;
		int aposta = 0;
		String trocaCartas;
		
		System.out.println("Fichas: " + dealer.fichas() + "\n");	// avisa ao jogador, no console, que ele tem 200 fichas
		
		//loop do menu
		while(!sair) {	
			//O jogador faz a aposta e o programa verifica se a aposta é valida
			boolean valido = false;
			while(!valido) {
				try {
					System.out.println("Digite o valor da aposta (0 para sair):");
					aposta = EntradaTeclado.leInt();
					if (aposta < 0) throw new Exception();
					//realiza a aposta
					dealer.aposta(aposta);
					//serve para saber se a aposta foi bem efetuada
					valido = true;
				}
				catch (Exception e) {
					System.out.println(">> Digite um numero inteiro e positivo que não exceda o total de fichas que você tem! <<\n");
					//caso a aposta nao seja bem efetuada o loop continua
					continue;
				}
			}
				
			//caso o jogador queira sair do jogo	
			if (aposta == 0) {
				sair = true;	
				System.out.println("\nJogo encerrado!");
			}
			
			else {	
				//o baralho é embaralhado e são distribuidas as cartas
				dealer.embaralha();
				dealer.daCartas();
				
				//mostra as cartas
				System.out.println("\n Carta 1\t\t Carta 2\t\t Carta 3\t\t Carta 4\t\t Carta 5");
				System.out.println(dealer);
				//faz a primeira troca de cartas
				System.out.println("Digite quais cartas deseja trocar (ENTER caso nenhuma):");
				trocaCartas = EntradaTeclado.leString();
				dealer.daCartas(trocaCartas);
				
				//mostra as cartas
				System.out.println("\n Carta 1\t\t Carta 2\t\t Carta 3\t\t Carta 4\t\t Carta 5");
				System.out.println(dealer);
				//faz a segunda troca de cartas
				System.out.println("Digite quais cartas deseja trocar (ENTER caso nenhuma):");
				trocaCartas = EntradaTeclado.leString();
				dealer.daCartas(trocaCartas);
				
				//mostra as cartas
				System.out.println("\n Carta 1\t\t Carta 2\t\t Carta 3\t\t Carta 4\t\t Carta 5");
				System.out.println(dealer);
				//paga o quanto o jogador ganhou
				dealer.pagarAposta();
				//mostra sua quantidade de fichas
				System.out.println("Fichas: " + dealer.fichas() + "\n");
				
				//caso o jogador perca todas as suas fichas
				if(dealer.fichas() == 0) {
					System.out.println("\nJogo encerrado!");
					sair = true;
				}
			}
		}
	}
}

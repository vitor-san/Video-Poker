package videopoker.util;

import java.util.Calendar;

/**
 * Esta classe implementa métodos para gerar números inteiros aleatórios.
 * @author Rodrigo Cesar Arboleda & Vitor Santana Cordeiro
 */

public class Random {
	
	private final long p = 2147483648L;
	private final long m = 843314861L;
	private final long a = 453816693L;
	
	private long xi = 1023;		// seed
	
	public Random(int k) {
		xi = k;
	}
	
	public Random() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException exc) {
			;
		}
		xi = Calendar.getInstance().getTimeInMillis()%p;	// caso o usuario nao passe nenhum valor para a seed, a classe pega o tempo em milisegundos
	}
	
	@Override
	public String toString() {
		return xi + "";		// quando o metodo toString() for chamado, retornara a seed
	}
	
	public void setSeed(int k) {
		xi = k;
	}
	
	/**
	 * @return um n�mero real aleat�rio entre 0 e 1.
	 */
	public double getRand() {
		xi = (a + m*xi)%p;
		return xi/(double)p;
	}
	
	/**
	 * Retorna um n�mero inteiro aleat�rio entre 0 e m�x.
	 * @param max o n�mero m�ximo a ser retornado + 1.
	 * @return n�mero inteiro aleat�rio.
	 */
	public int getIntRand(int max) {
		double x = getRand();
		return (int)(x*max);
	}

	/**
	 * Retorna um n�mero inteiro aleat�rio entre min e m�x (excluindo o pr�prio m�ximo).
	 * @param min o n�mero m�nimo a ser retornado.
	 * @param max o n�mero m�ximo a ser retornado + 1.
	 * @return n�mero inteiro aleat�rio.
	 */
	public int getIntRand(int min, int max) throws IllegalArgumentException {
		if (max <= min) throw new IllegalArgumentException("Parametros invalidos");
		return min + getIntRand(max-min);
	}

	/**
	 * Retorna um n�mero inteiro aleat�rio.
	 * @return n�mero inteiro aleat�rio.
	 */
	public int getIntRand() {
		return getIntRand(Integer.MAX_VALUE);
	}
	
}
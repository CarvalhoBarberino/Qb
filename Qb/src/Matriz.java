
public class Matriz{
	int numeroDeLinha;
	int numeroDeColuna;
	Complexo[][] elemento;
	//****
	public Matriz(int numeroDeLinhaArg, int numeroDeColunaArg){
		numeroDeLinha = numeroDeLinhaArg;
		numeroDeColuna = numeroDeColunaArg;
		elemento = new Complexo[numeroDeLinha][numeroDeColuna];
		for(int l = 0; l < numeroDeLinha; l++){
			for(int c = 0; c < numeroDeColuna; c++){
				elemento[l][c] = new Complexo();
			}
		}
	}
	//****
	public String getString(){
		String retornar = "";
		for(int l = 0; l < numeroDeLinha; l++){
			for(int c = 0; c < numeroDeColuna; c++){
				retornar = retornar + elemento[l][c].getString() + " ";
			}
			retornar = retornar + "\n";
		}
		return retornar;
	}
	//****
	public void mostrar(){
		System.out.println(getString());
	}
	//****
	public Matriz clone(){
		Matriz retornar = new Matriz(numeroDeLinha, numeroDeColuna);
		for(int l = 0; l < numeroDeLinha; l++){
			for(int c = 0; c < numeroDeColuna; c++){
				retornar.elemento[l][c] = elemento[l][c].clone();
			}
		}
		return retornar;
	}
	//****
	public static Matriz multiplicacao(Matriz a, Matriz b){
		Matriz resultado = new Matriz(a.numeroDeLinha, b.numeroDeColuna);
		for(int l = 0; l < a.numeroDeLinha; l++){
			for(int c = 0; c < b.numeroDeColuna; c++){
				Complexo celula = new Complexo();
				for(int i = 0; i < a.numeroDeColuna; i++){
					celula = Complexo.soma(celula, Complexo.multiplicacao(a.elemento[l][i], b.elemento[i][c]));
				}
				resultado.elemento[l][c] = celula;
			}
		}
		return resultado;
	}
	//****
	public static Matriz produtoTensorial(Matriz a, Matriz b){
		Matriz resultado = new Matriz(a.numeroDeLinha*b.numeroDeLinha, a.numeroDeColuna*b.numeroDeColuna);
		for(int la = 0; la < a.numeroDeLinha; la++){
			for(int ca = 0; ca < a.numeroDeColuna; ca++){
				for(int lb = 0; lb < b.numeroDeLinha; lb++){
					for(int cb = 0; cb < b.numeroDeColuna; cb++){
						resultado.elemento[la*b.numeroDeLinha+lb][ca*b.numeroDeColuna+cb] = Complexo.multiplicacao(a.elemento[la][ca], b.elemento[lb][cb]);
					}
				}
			}
		}
		return resultado;
	}
	//****
	public static Matriz chio(Matriz x){//Este metodo usa a regra de chio para retornar uma matriz de tamanho menor porem com a mesma determinante
		Complexo menos1 = new Complexo(-1, 0);
		int linhaOtima = 0, colunaOtima = 0;
		double aux = Complexo.soma(x.elemento[0][0], menos1).norma * Complexo.soma(x.elemento[0][0], menos1).norma * Complexo.soma(x.elemento[0][0], new Complexo(1, 0)).norma / x.elemento[0][0].norma;
		System.out.println(linhaOtima + "    " + colunaOtima + "    " + aux);
		for(int l = 0; l < x.numeroDeLinha; l++){//Este conjunto de loop procura o elemento mais perto do 1 e longe do 0
			for(int c = 0; c < x.numeroDeColuna; c++){
				if(0 != x.elemento[0][0].norma){
					if(aux > Complexo.soma(x.elemento[l][c], menos1).norma * Complexo.soma(x.elemento[l][c], new Complexo(1, 0)).norma/ x.elemento[l][c].norma){
						aux = Complexo.soma(x.elemento[l][c], menos1).norma * Complexo.soma(x.elemento[l][c], new Complexo(1, 0)).norma/ x.elemento[l][c].norma;
						linhaOtima = l;
						colunaOtima = c;
						System.out.println(linhaOtima + "    " + colunaOtima + "    " + aux);
					}
				}
			}
		}
		Complexo elementoOtimo = x.elemento[linhaOtima][colunaOtima];//Todos os elementos da linha otima serão divididos por este elemento para obtermos o elemento 1
		int linhaCompensada = linhaOtima==0?1:0;
		for(int c = 0; c < x.numeroDeColuna; c++){//Multiplica todos os elementos da linha otima por "elementoOtimo.pow(-1)"
			x.elemento[linhaOtima][c] = Complexo.multiplicacao(x.elemento[linhaOtima][c], elementoOtimo.pow(-1));
			x.elemento[linhaCompensada][c] = Complexo.multiplicacao(x.elemento[linhaCompensada][c], elementoOtimo);
		}
		
		Matriz resposta = new Matriz(x.numeroDeLinha-1, x.numeroDeColuna-1);
		for(int l = 0; l < x.numeroDeLinha; l++){
			int ln;
			for(int c = 0; c < x.numeroDeColuna; c++){
				int cn;
				ln = l>linhaOtima?(l-1):l;
				cn = c>colunaOtima?(c-1):c;
				if((l != linhaOtima) && (c != colunaOtima)){
					resposta.elemento[ln][cn] = Complexo.soma(x.elemento[l][c], Complexo.multiplicacao(x.elemento[l][colunaOtima], x.elemento[linhaOtima][c]).negativo());
				}
			}
		}
		return resposta;
	}
	//****
	private class MatrizNaoQuadradaException extends Exception{
		public MatrizNaoQuadradaException(String msg){
			super(msg);
		}
	}
	//****
	public double determinante() throws MatrizNaoQuadradaException {
		if(numeroDeColuna != numeroDeLinha){
			throw new MatrizNaoQuadradaException("Não existe determinante para matrizesnão quadradas.");
		}
		return 1;
	}
}


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
	public void trocaLinha(int x, int y){
		Complexo aux = new Complexo();
		for(int c = 0; c < numeroDeColuna; c++){
			aux = elemento[x][c];
			elemento[x][c] = elemento[y][c];
			elemento[y][c] = aux;
		}
	}
	//****
	public void trocaColuna(int x, int y){
		Complexo aux = new Complexo();
		for(int l = 0; l < numeroDeLinha; l++){
			aux = elemento[l][x];
			elemento[l][x] = elemento[l][y];
			elemento[l][y] = aux;
		}
	}
	//****
	public void negativaLinha(int x){
		for(int c = 0; c < numeroDeColuna; c++){
			elemento[x][c] = elemento[x][c].negativo();
		}
	}
	//****
	public static Matriz chio(Matriz arg){//Este metodo usa a regra de chio para retornar uma matriz de tamanho menor porem com a mesma determinante
		Matriz x = arg.clone();
		Complexo c1 = new Complexo(1, 0);
		int linhaOtima = 0, colunaOtima = 0;
		int parametroPontuaçãoPivotExato = 0;
		boolean matrizNula = true;
		double raio = x.elemento[0][0].norma, aux;

		for(int l = 0; l < x.numeroDeLinha; l++){//Este loop verifica se a matriz eh nula e defini um valor inicial para aux
			for(int c = 0; c < x.numeroDeColuna; c++){
				raio = x.elemento[l][c].norma;
				if(raio != 0){
					matrizNula = false;
					linhaOtima = l; colunaOtima = c;
					l = x.numeroDeLinha + 1; c = x.numeroDeColuna + 1;
				}
			}
		}
		aux = raio + 1 / raio;
		
		if(matrizNula){return(new Matriz(1, 1));}
		for(int l = 0; l < x.numeroDeLinha; l++){//Este conjunto de loop procura o elemento mais apropriado como pivot. Em ordem decrescente de importancia são: 1, -1, i, -i
			for(int c = 0; c < x.numeroDeColuna; c++){
				if(x.elemento[l][c].equals(c1) && (parametroPontuaçãoPivotExato < 4)){
					parametroPontuaçãoPivotExato = 4;
					linhaOtima = l; colunaOtima = c;
				}
				if(x.elemento[l][c].equals(c1.negativo()) && (parametroPontuaçãoPivotExato < 3)){
					parametroPontuaçãoPivotExato = 3;
					linhaOtima = l; colunaOtima = c;
				}
				if(x.elemento[l][c].equals(c1.multiplicarPorI()) && (parametroPontuaçãoPivotExato < 2)){
					parametroPontuaçãoPivotExato = 2;
					linhaOtima = l; colunaOtima = c;
				}
				if(x.elemento[l][c].equals(c1.multiplicarPorI().negativo()) && (parametroPontuaçãoPivotExato < 1)){
					parametroPontuaçãoPivotExato = 1;
					linhaOtima = l; colunaOtima = c;
				}
			}
		}
		
		System.out.println(parametroPontuaçãoPivotExato+"pontuacao");
		if(parametroPontuaçãoPivotExato == 0){//Caso não tenha encontrato um pivot apropriado no loop acima
			for(int l = 0; l < x.numeroDeLinha; l++){
				for(int c = 0; c < x.numeroDeColuna; c++){
					raio = x.elemento[l][c].norma;
					if(raio != 0){
						if(aux > raio + 1 / raio){
							linhaOtima = l; colunaOtima = c;
							aux = raio + 1 / raio;
						}
					}
				}
			}
		}
		Complexo elementoOtimo = x.elemento[linhaOtima][colunaOtima];//Todos os elementos da linha otima serão divididos por este elemento para obtermos o elemento 1
		int linhaCompensada = (linhaOtima == 0) ? 1 : 0;//Impede que a linhaCompensada seja a linhaOtima
		for(int c = 0; c < x.numeroDeColuna; c++){//Multiplica todos os elementos da linha otima por "elementoOtimo.pow(-1)"
			x.elemento[linhaOtima][c] = Complexo.multiplicacao(x.elemento[linhaOtima][c], elementoOtimo.pow(-1));
			x.elemento[linhaCompensada][c] = Complexo.multiplicacao(x.elemento[linhaCompensada][c], elementoOtimo);
			x.elemento[linhaCompensada][c].mostrar();
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
		x.elemento[linhaOtima][colunaOtima].mostrar();
		System.out.println(linhaOtima + "    " + colunaOtima);
		return resposta;
	}
}

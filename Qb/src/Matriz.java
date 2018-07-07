
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
}

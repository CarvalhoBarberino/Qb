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
	public class StringForaDePadraoParaMatriz extends Exception{
		public StringForaDePadraoParaMatriz(String msg){
			super(msg);
		}
	}
	//****
	public Matriz(String arg) throws StringForaDePadraoParaMatriz{
		String aux = "";
		String aux2 = "";
		try{
			int nlinhas = Integer.valueOf(arg.substring(0, arg.indexOf("x")));
			numeroDeLinha = nlinhas;
			aux = arg.substring(arg.indexOf("x")+1, arg.length());
			int ncolunas = Integer.valueOf(aux.substring(0, aux.indexOf(" ")));
			numeroDeColuna = ncolunas; 
			aux = aux.substring(aux.indexOf(" ")+1, aux.length()) + "  ";
			elemento = new Complexo[nlinhas][ncolunas];
			for(int l = 0; l < nlinhas; l++){
				for(int c = 0; c < ncolunas; c++){
					int valorDaQuebra = 0;
					for(int p = 0; p < aux.length(); p++){
						if(valorDaQuebra > 2){
							aux2 = aux.substring(0, p-1);
							elemento[l][c] = new Complexo(aux2);
							aux = aux.substring(p, aux.length());
							break;
						}
						if(aux.charAt(p) == ' '){valorDaQuebra++;}
						if(aux.charAt(p) == "\n".charAt(0)){valorDaQuebra = 3;}
						
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new StringForaDePadraoParaMatriz("String fora de padrão de leitura para matrizes");
		}
	}
	//****
	public String getString(){
		String retornar = "";
		for(int l = 0; l < numeroDeLinha; l++){
			for(int c = 0; c < numeroDeColuna; c++){
				retornar = retornar + elemento[l][c].getString() + "  ";
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
	public void setTrocaLinha(int x, int y){
		Complexo aux = new Complexo();
		for(int c = 0; c < numeroDeColuna; c++){
			aux = elemento[x][c];
			elemento[x][c] = elemento[y][c];
			elemento[y][c] = aux;
		}
	}
	//****
	public void setTrocaColuna(int x, int y){
		Complexo aux = new Complexo();
		for(int l = 0; l < numeroDeLinha; l++){
			aux = elemento[l][x];
			elemento[l][x] = elemento[l][y];
			elemento[l][y] = aux;
		}
	}
	//****
	public void setNegativaLinha(int x){
		for(int c = 0; c < numeroDeColuna; c++){
			elemento[x][c].setNegativo();
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

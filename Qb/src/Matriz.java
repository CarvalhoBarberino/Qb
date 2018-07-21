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
	public Matriz(String arg) throws IllegalArgumentException{
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
			throw new IllegalArgumentException("String fora de padrão de leitura para matrizes");
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
	//****
	public static Matriz chio(Matriz arg) throws IllegalArgumentException{//Este metodo usa a regra de chio para retornar uma matriz de tamanho menor porem com a mesma determinante
		if(arg.numeroDeColuna != arg.numeroDeLinha){throw new IllegalArgumentException("A matriz deve ser quadrada");}
		Matriz x = arg.clone();
		Complexo c1 = new Complexo(1, 0);
		int linhaPivot = 0, colunaPivot = 0;
		int parametroPontuaçãoPivotExato = 0;
		boolean matrizNula = true;
		double raio = x.elemento[0][0].norma, aux;

		for(int l = 0; l < x.numeroDeLinha; l++){//Este loop verifica se a matriz eh nula e define um valor inicial para aux
			for(int c = 0; c < x.numeroDeColuna; c++){
				raio = x.elemento[l][c].norma;
				if(raio != 0){
					matrizNula = false;
					linhaPivot = l; colunaPivot = c;
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
					linhaPivot = l; colunaPivot = c;
				}
				if(x.elemento[l][c].equals(c1.getNegativo()) && (parametroPontuaçãoPivotExato < 3)){
					parametroPontuaçãoPivotExato = 3;
					linhaPivot = l; colunaPivot = c;
				}
				if(x.elemento[l][c].equals(c1.multiplicarPorImaginario()) && (parametroPontuaçãoPivotExato < 2)){
					parametroPontuaçãoPivotExato = 2;
					linhaPivot = l; colunaPivot = c;
				}
				if(x.elemento[l][c].equals(c1.multiplicarPorImaginario().getNegativo()) && (parametroPontuaçãoPivotExato < 1)){
					parametroPontuaçãoPivotExato = 1;
					linhaPivot = l; colunaPivot = c;
				}
			}
		}
		
		if(parametroPontuaçãoPivotExato == 0){//Caso não tenha encontrato um pivot apropriado no loop acima
			for(int l = 0; l < x.numeroDeLinha; l++){
				for(int c = 0; c < x.numeroDeColuna; c++){
					raio = x.elemento[l][c].norma;
					if(raio != 0){
						if(aux > raio + 1 / raio){
							linhaPivot = l; colunaPivot = c;
							aux = raio + 1 / raio;
						}
					}
				}
			}
		}
		
		if(x.numeroDeLinha - 1 != linhaPivot){x.setTrocaLinha(x.numeroDeLinha - 1, linhaPivot);}
		else{x.setNegativaLinha(linhaPivot);}
		if(x.numeroDeColuna - 1 != colunaPivot){
			x.setTrocaColuna(x.numeroDeColuna - 1, colunaPivot);}
		else{x.setNegativaLinha(linhaPivot);}
		
		Complexo elementoPivot = x.elemento[x.numeroDeLinha - 1][x.numeroDeColuna - 1];//Todos os elementos da linha pivot serão divididos por este elemento para obtermos o elemento 1
		for(int c = 0; c < x.numeroDeColuna; c++){//Multiplica todos os elementos da linha pivot por "elementoPivot.pow(-1)"
			x.elemento[x.numeroDeLinha - 1][c] = Complexo.multiplicacao(x.elemento[x.numeroDeLinha - 1][c], elementoPivot.getPow(-1));
			x.elemento[0][c] = Complexo.multiplicacao(x.elemento[0][c], elementoPivot);
		}
		
		Matriz resposta = new Matriz(x.numeroDeLinha-1, x.numeroDeColuna-1);
		for(int l = 0; l < x.numeroDeLinha - 1; l++){
			for(int c = 0; c < x.numeroDeColuna - 1; c++){
				resposta.elemento[l][c] = Complexo.soma(x.elemento[l][c], Complexo.multiplicacao(x.elemento[l][x.numeroDeColuna - 1], x.elemento[x.numeroDeLinha - 1][c]).getNegativo());
			}
		}
		return resposta;
	}
	//****
	public static Matriz chioAte1x1(Matriz x) throws IllegalArgumentException{
		if(x.numeroDeLinha > 1){
			return(Matriz.chioAte1x1((Matriz.chio(x))));
		}
		else{
			return x;
		}
	}
	//****
	public static Complexo determinante(Matriz arg) throws IllegalArgumentException{
		Complexo det = Matriz.chioAte1x1(arg).elemento[0][0];
		return det;
	}
}

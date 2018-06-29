
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
}

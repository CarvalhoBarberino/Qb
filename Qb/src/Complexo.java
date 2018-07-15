
public class Complexo{
	double real;
	double imaginario;
	double norma;
	double fase;
	//****
	public Complexo(){
		real = 0;
		imaginario = 0;
		norma = 0;
		fase = 0;
	}
	//****
	public Complexo(double r, double i){
		real = r;
		imaginario = i;
		norma = norma();
		if(norma != 0) {
			fase = Math.acos(real/norma);
		}else {fase = 0;}
	}
	//****
	private class StringForaDePadraoParaComplexo extends Exception{
		public StringForaDePadraoParaComplexo(String msg){
			super(msg);
		}
	}
	//****
	public Complexo(String arg) throws StringForaDePadraoParaComplexo{
		String aux;
		Double sinal = (double)1;
		try{
			int corteNoString = -2;
			if((arg.indexOf(" + i*") == -1) ^ (arg.indexOf(" - i*") == -1)){
				if(arg.indexOf(" + i*") != -1){
					corteNoString = arg.indexOf(" + i*");
				}
				if(arg.indexOf(" - i*") != -1){
					corteNoString = arg.indexOf(" - i*");
					sinal = -sinal;
				}
			}
			else{
				throw new StringForaDePadraoParaComplexo("String fora de padrão de leitura para numeros complexos");
			}
			real = Double.valueOf(arg.substring(0, corteNoString));
			aux = arg.substring(corteNoString + 5);
			imaginario = sinal * Double.valueOf(aux);
			norma = norma();
			if(norma != 0) {
				fase = Math.acos(real/norma);
			}else {fase = 0;}
		}catch(NumberFormatException e){
			throw new StringForaDePadraoParaComplexo("String fora de padrão de leitura para numeros complexos");
		}
		
	}
	//****
	public static Complexo soma(Complexo a, Complexo b){
		return(new Complexo(a.real + b.real, a.imaginario + b.imaginario));
	}
	//****
	public static Complexo multiplicacao(Complexo a, Complexo b){
		return(new Complexo((a.real*b.real - a.imaginario*b.imaginario), (a.real*b.imaginario + a.imaginario*b.real)));
	}
	//****
	public String getString(){
		if(imaginario >= 0) {
			return(real + " + i*" + imaginario);
		}else {
			return(real + " - i*" + (-imaginario));
		}
	}
	public void mostrar(){
		System.out.println(getString());
	}
	//****
	public double norma() {
		return(Math.sqrt(real*real+imaginario*imaginario));
	}
	//****
	public void setNorma(double normaArg){
		setPolar(normaArg, fase);
	}
	//****
	public void mostrarPolar() {
		System.out.println(norma + " < " + (fase*180/Math.PI) + " graus");
	}
	//****
	public void setConjugado(){
		imaginario = -imaginario;
	}
	//****
	public Complexo getConjugado(){
		return(new Complexo(real, -imaginario));
	}
	//****
	public Complexo clone(){
		return(new Complexo(real, imaginario));
	}
	//****
	public static Complexo newPolar(double norma, double fase){
		return(new Complexo(norma * Math.cos(fase), norma * Math.sin(fase)));
	}
	//****
	public void setPolar(double normaArg, double faseArg){
		real = normaArg * Math.cos(faseArg);
		imaginario = normaArg * Math.sin(faseArg);
		norma = normaArg;
		fase = faseArg;
	}
	//****
	public Complexo getPow(double e){
		return(Complexo.newPolar(Math.pow(norma, e), e * fase));
	}
	//****
	public void setPow(double e){
		setPolar(Math.pow(norma, e), e * fase);
	}
	//****
	public void setNegativo(){
		real = -real;
		imaginario = -imaginario;
	}
	//****
	public Complexo getNegativo(){
		return(new Complexo(-real, -imaginario));
	}
	//****
	public Complexo multiplicarPorImaginario(){
		return(new Complexo(-imaginario, real));
	}
	public boolean equals(Complexo arg){
		if((real == arg.real) && (imaginario == arg.imaginario)){
			return true;
		}
		else{
			return false;
		}
	}
}

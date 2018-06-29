
public class Complexo {
	double real;
	double imaginario;
	double norma;
	double fase;
	//****
	public Complexo() {
		real = 0;
		imaginario = 0;
		norma = 0;
		fase = 0;
	}
	//****
	public Complexo(double r, double i) {
		real = r;
		imaginario = i;
		norma = norma();
		if(norma != 0) {
			fase = Math.acos(real/norma);
		}else {fase = 0;}
	}
	//****
	public static Complexo soma(Complexo a, Complexo b) {
		return(new Complexo(a.real + b.real, a.imaginario + b.imaginario));
	}
	//****
	public static Complexo multiplicacao(Complexo a, Complexo b) {
		return(new Complexo((a.real*b.real - a.imaginario*b.imaginario), (a.real*b.imaginario + a.imaginario*b.real)));
	}
	//****
	public void mostrar() {
		if(imaginario >= 0) {
			System.out.println(real + " + i*" + imaginario);
		}else {
			System.out.println(real + " - i*" + (-imaginario));
		}
		
	}
	//****
	public double norma() {
		return(Math.sqrt(real*real+imaginario*imaginario));
	}
	//****
	public void mostrarPolar() {
		System.out.println(norma + " < " + fase*180/Math.PI);
	}
	//****
	public void conjugado(){
		imaginario = -imaginario;
	}
	//****
	public Complexo conjugado(Complexo x){
		return(new Complexo(x.real, -x.imaginario));
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
	public Complexo pow(double e){
		return(Complexo.newPolar(Math.pow(norma, e), e * fase));
	}
}


public class Complexo {
	double real;
	double imaginario;
	double norma;
	double fase;
	public Complexo() {
		real = 0;
		imaginario = 0;
		norma = 0;
		fase = 0;
	}
	public Complexo(double r, double i) {
		real = r;
		imaginario = i;
		norma = norma();
		if(norma > 0) {
			fase = Math.acos(real/norma);
		}else {fase = 0;}
	}
	public static Complexo soma(Complexo a, Complexo b) {
		return(new Complexo(a.real + b.real, a.imaginario + b.imaginario));
	}
	public static Complexo multiplicacao(Complexo a, Complexo b) {
		return(new Complexo((a.real*b.real - a.imaginario*b.imaginario), (a.real*b.imaginario + a.imaginario*b.real)));
	}
	public void mostrarCartesiano() {
		if(imaginario >= 0) {
			System.out.println(real + " + i*" + imaginario);
		}else {
			System.out.println(real + " - i*" + (-imaginario));
		}
		
	}
	public double norma() {
		return(Math.sqrt(real*real+imaginario+imaginario));
	}
	public void mostraPolar() {
		System.out.println(norma + " < " + fase);
	}
}

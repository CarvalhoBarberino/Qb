
public class Main {
	public static void main(String args[]) {
		Complexo x = new Complexo(0,1);
		x.mostrarCartesiano();
		x.mostraPolar();
		Complexo z = Complexo.multiplicacao(x, x);
		z.mostrarCartesiano();
		z.mostraPolar();
	}
}


public class Main{
	public static void main(String args[]){
		Matriz x = new Matriz(2, 2);
		x.mostrar();
		x.elemento[1][1] = new Complexo(3, 4);
		x.mostrar();
		Matriz y = x.clone();
		y.mostrar();
		y.elemento[1][1] = new Complexo(1, 1);
		x.mostrar();
		y.mostrar();
		
		/*
		System.out.println("Qual a raiz quadrada de -1?");
		Complexo menos1 = new Complexo(-1, 0);
		System.out.print("Resposta: ");
		menos1.pow(0.5).mostrar();
		System.out.println();
		Complexo x = new Complexo(0.3, 0.4);
		x.mostrar();
		x.mostrarPolar();
		Complexo y = Complexo.newPolar(2, 30*Math.PI/180);
		y.mostrar();
		y.mostrarPolar();
		Complexo z = y.pow(0.5);
		z.mostrar();
		z.mostrarPolar();*/
	}
}

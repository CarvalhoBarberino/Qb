
public class Main{
	public static void main(String args[]){
		Matriz a = new Matriz(4, 4);
		a.elemento[0][0] = new Complexo(5, 0);
		a.elemento[0][1] = new Complexo(4, 0);
		a.elemento[0][2] = new Complexo(3, 0);
		a.elemento[0][3] = new Complexo(3, 0);
		a.elemento[1][0] = new Complexo(2, 0);
		a.elemento[1][1] = new Complexo(4, 0);
		a.elemento[1][2] = new Complexo(-3, 0);
		a.elemento[1][3] = new Complexo(5, 0);
		a.elemento[2][0] = new Complexo(7, 0);
		a.elemento[2][1] = new Complexo(3, 0);
		a.elemento[2][2] = new Complexo(5, 0);
		a.elemento[2][3] = new Complexo(-6, 0);
		a.elemento[3][0] = new Complexo(1, 0);
		a.elemento[3][1] = new Complexo(4, 0);
		a.elemento[3][2] = new Complexo(4, 0);
		a.elemento[3][3] = new Complexo(1, 0);
		a.mostrar();
		Matriz.chio(a).mostrar();
		
		
		
		/*
		Matriz a = new Matriz(2, 2);
		a.elemento[0][0] = new Complexo(1, 0);
		a.elemento[0][1] = new Complexo(2, 0);
		a.elemento[1][0] = new Complexo(3, 0);
		a.elemento[1][1] = new Complexo(4, 0);
		Matriz b = new Matriz(3, 2);
		b.elemento[0][0] = new Complexo(5, 0);
		b.elemento[0][1] = new Complexo(6, 0);
		b.elemento[1][0] = new Complexo(7, 0);
		b.elemento[1][1] = new Complexo(8, 0);
		b.elemento[2][0] = new Complexo(9, 0);
		b.elemento[2][1] = new Complexo(10, 0);
		Matriz c = Matriz.produtoTensorial(a, b);
		c.mostrar();*/
		
		/*
		Matriz a = new Matriz(2, 3);
		a.elemento[0][0] = new Complexo(1,0);
		a.elemento[0][1] = new Complexo(2,0);
		a.elemento[0][2] = new Complexo(3,0);
		a.elemento[1][0] = new Complexo(4,0);
		a.elemento[1][1] = new Complexo(5,0);
		a.elemento[1][2] = new Complexo(6,0);
		Matriz b = new Matriz(3, 2);
		b.elemento[0][0] = new Complexo(7,0);
		b.elemento[0][1] = new Complexo(10,0);
		b.elemento[1][0] = new Complexo(8,0);
		b.elemento[1][1] = new Complexo(11,0);
		b.elemento[2][0] = new Complexo(9,0);
		b.elemento[2][1] = new Complexo(12,0);
		a.mostrar();
		b.mostrar();
		Matriz c = Matriz.multiplicacao(a, b);
		c.mostrar();
		c = Matriz.multiplicacao(b, a);
		c.mostrar();*/
		
		/*
		Matriz x = new Matriz(2, 2);
		x.mostrar();
		x.elemento[1][1] = new Complexo(3, 4);
		x.mostrar();
		Matriz y = x.clone();
		y.mostrar();
		y.elemento[1][1] = new Complexo(1, 1);
		x.mostrar();
		y.mostrar();*/
		
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

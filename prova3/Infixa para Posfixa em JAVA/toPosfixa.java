import java.io.*;
class Celula{
	public char elemento ;
	public Celula prox;
	public Celula(){
		this.elemento = null();
		this.prox = null;
	}
	public Celula(char x){
		this.elemento = x;
		this.prox = null;
	}
}
class toPosfixa{
	int n;
	private Celula primeiro, ultimo;
	public toPosfixa(){
		primeiro = new Celula();
		ultimo = primeiro;
	}
	public void inserirFim(char x){
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}
	public void removerFim() {
		Celula i;
		for(i=primeiro;i.prox!=ultimo;i=i.prox);
		Personagem elemento = ultimo.elemento;
		ultimo = i;
		i = ultimo.prox = null;
	}
public static void main(String[]args){
		String temp = MyIO.readLine();
		int num = Integer.parseInt(temp);
		for(int i = 0;i<num;i++){
			toPosfixa lista = new toPosfixa();
			String temp2 = MyIO.readLine();
			String temp3[] = temp2.split("");
			char infixo[] = new char[temp3.length];
			for(int j = 0;j<temp3.length;j++){
				infixo[j]=temp3[j].charAt(0);
				lista.inserifim(infixo[j];
			}
			
			System.out.println();
		}
	}
}

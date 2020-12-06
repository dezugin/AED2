import java.io.*;
class No{
	char elemento;
	No esq, dir;
	No(char c){
		elemento = c;
		esq = dir = null;
	}
}
class Indice{
	int indice = 0;
}
class toPosfixa{
	No raiz;
	No construirArvore(char infixo[], int inicio,int fim, No no){
		if(inicio>fim){
			return null;
		}
		int i = max(infixo,inicio,fim);
		no = new No(infixo[i]);
		if ( inicio == fim){
			return no;
		}
		no.esq = construirArvore(infixo,inicio,i-1,no.esq);
		no.dir = construirArvore(infixo,i+1,fim,no.dir);
		return no;
	}
	int max(char arr[],int init, int fim){
		int i;
		char max = arr[init];
		int maxind = init;
		for(i = init+1;i<=fim;i++){
			if(arr[i]>max){
				max = arr[i];
				maxind = i;
			}
		}
		return maxind;
	}
	void mostrarPos(No no){
		if(no == null){
			return;
		}
		mostrarPos(no.esq);
		mostrarPos(no.dir);
		System.out.print(no.elemento+ "");
	}
	public static void main(String[]args){
		String temp = MyIO.readLine();
		int num = Integer.parseInt(temp);
		for(int i = 0;i<num;i++){
			toPosfixa arvore = new toPosfixa();
			String temp2 = MyIO.readLine();
			String temp3[] = temp2.split("");
			char infixo[] = new char[temp3.length];
			for(int j = 0;j<temp3.length;j++){
				infixo[j]=temp3[j].charAt(0);
			}
			int tamanho = infixo.length;
			No noz = arvore.construirArvore(infixo,0,tamanho-1,arvore.raiz);
			arvore.mostrarPos(noz);
			System.out.println();
		}
	}
}

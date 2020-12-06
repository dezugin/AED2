import java.util.Scanner;
import java.util.Arrays;
public class laboratorio {  
	class nodo{  
		int elemento;  
		nodo ant;  
		nodo prox;  
		public nodo(){
			this(0);
		}
		public nodo(int elemento) {  
			this.elemento = elemento; 
			this.prox = this.ant = null;
		}  
	}  
	nodo primeiro, ultimo = null;
	public laboratorio(){
		primeiro = new nodo();
		ultimo = primeiro;
	}
	public void adicionarNodo(int elemento) {  
		nodo tmp = new nodo(elemento);  
		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant=tmp;
		}
		tmp = null;
	}
	public void ordenar() {  
		nodo atual = null, index = null;  
		int temp;  
		if(primeiro == null) {  
			return;  
		}  
		else {  
			for(atual = primeiro; atual.prox != null; atual = atual.prox) {  
				for(index = atual.prox; index != null; index = index.prox) {  
					if(atual.elemento > index.elemento) {  
						temp = atual.elemento;  
						atual.elemento = index.elemento;  
						index.elemento = temp;  
					}  
				}  
			}  
		}  
	}  
	public void mostrar() {  
		nodo atual = primeiro;  
		if(primeiro == null) {  
			System.out.println("Lista vazi");  
			return;  
		}  
		while(atual != null) {  
			System.out.print(atual.elemento + " ");  
			atual = atual.prox;  
		}  
		System.out.println();  
	}  
	public laboratorio clonar() {  
		nodo atual = primeiro;
		laboratorio novaLista = new laboratorio();  
		while(atual != null) {  
			nodo novoNodo = new nodo(atual.elemento);
			novaLista.ultimo.prox = novoNodo;
			novoNodo.ant = novaLista.ultimo;
			novaLista.ultimo = novoNodo;
			novaLista.ultimo.prox = null;  
			atual = atual.prox;  
		}  
		return novaLista;  
	}  
	public int comparar(laboratorio l2){
		nodo a = this.primeiro.prox, b = l2.primeiro.prox;
		int saida = 0;
		int comparacoes = 0;
		while(a!=null&&b!=null&&saida!='1'){
			if(a.elemento==b.elemento){
				comparacoes++;
			}else{
				saida = 1;
			}
			a = a.prox;
			b = b.prox;
		}
		return comparacoes;
	}
	public void adicionarDepois(laboratorio l2){
		nodo a = l2.primeiro.prox;
		this.ultimo.prox = a;
		this.ultimo.prox.ant = this.ultimo;
		this.ultimo = this.ultimo.prox;

	}
	public int comparar2(int num){
		nodo a = this.primeiro.prox;
		nodo b = this.primeiro.prox;
	//	num = num/2;
	//	System.out.println(a.elemento);
		int comparacoes = 0;
		for(int i = 0;i<num;i++){
			b = b.prox;
		}
	//	System.out.println(b.elemento);
		for(int j = 1;j<num;j++){
	//		System.out.println(a.elemento+" "+b.elemento);
			if(a.elemento==b.elemento){
				comparacoes++;
			}else{
				j=num;
			}
			a = a.prox;
			b = b.prox;
		}
		return comparacoes;

	}
	public static void main(String[] args) {  
		Scanner s = new Scanner(System.in);
		int i = 0;
		int comparacoes = 0;
		int numero[] = new int[100];
		while(s.hasNext()){
			String temp = s.nextLine();
			numero[i] = Integer.parseInt(temp);
			System.out.println(numero[i]);
			i++;
		}
		int t = 0;
//		int numero2[] = new int 100;
		for(int j = 0;j<i;j++){
			laboratorio lista = new laboratorio(); 
			laboratorio lista1 = new laboratorio();
//			if(j==0||numNumeros-j==0){			
				int numNumeros = numero[j];
			System.out.println("inicial"+numero[j]);
			int numero2[] = new int [100];
			int digitos = 0;
			if(j==0||numNumeros-j==0){			
				for(int z = 0;z<numNumeros;z++){
					numero2[z] = numero[t];
					System.out.println("t="+t+"n2-9"+numero[t]);
					System.out.println("z="+z+"n2-1="+numero2);
				t++;
				}
			}
			Arrays.sort(numero2);
			
			for(int k = 1;k<numNumeros;k++){
				digitos = 0;
				j++;
				int temp = numero2[k];
				System.out.println("k ="+k+"temp1 = "+temp);
				while(temp>0){
					lista.adicionarNodo(temp%10);  
					temp/=10;
					digitos++;
				}
				k++;
				j++;
				System.out.println("kn="+k+"n2="+numero2[k]);
				temp = numero2[k];
				System.out.println("k2="+k+"temp="+temp);
				while(temp>0){
					lista1.adicionarNodo(temp%10);
					temp/=10;
					digitos++;
				}
//				lista.adicionarDepois(lista1);
			}
			lista.mostrar();
		//		System.out.println(digitos);
			int maior = lista.comparar(lista1);
			//	System.out.println(maior);
			if(maior>comparacoes){
				comparacoes = maior;
				System.out.println(comparacoes);
			}
				//lista.mostrar();
				/*
				laboratorio lista1 = new laboratorio();
				if(k==0){
					lista1 = lista.clonar();
				}else{
					int maior = 0;
					maior = lista1.comparar(lista);
					if(maior>comparacoes){
						comparacoes = maior;
					}
				}
				if(k==numNumeros-1){
					System.out.println(comparacoes);
				}
				*/
		}
	}  
}  

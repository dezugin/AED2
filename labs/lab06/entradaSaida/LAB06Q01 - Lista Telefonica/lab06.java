import java.util.Scanner;
class CelulaDupla{
	public int elemento;
	public CelulaDupla prox, ant;
	public CelulaDupla(){
		this(0);
	}
	public CelulaDupla(int x){
		this.elemento = x;
		this.prox = this.ant = null;
	}
	public int getElemento(){
	return elemento;
	}
	public void setElemento(int elemento){
		this.elemento = elemento;
	}
}
public class ListaDupla{
	private CelulaDupla primeiro, ultimo;
	public ListaDupla(){
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}
	public int tamanho(){
		int tamanho = 0;
		for(CelulaDupla i = primeiro;i!=null;i=i.prox,tamanho++);
		return tamanho;
	}
	public void inserirFim(int x){
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}
	public void inserirInicio(int x){
		CelulaDupla tmp = new CelulaDupla(x);
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
	public void inserir(int x, int pos)throws Exception{
		int tamanho = tamanho();
		if(pos<0||pos>tamanho){ throw new Exception("Erro!");
		}else if(pos == 0){ inserirInicio(x);
		}else if(pos == tamanho){inserirFim(x);
		}else{
			CelulaDupla i = primeiro;
			for(int j = 0; j<pos;j++,i=i.prox);
			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}
	}
	public void mostrarCompara(int k){
		int count = 0;
		for(int i = 0;i<k-1;i++){
			CelulaDupla z = lista[i+1].primeiro;
			for(CelulaDupla j = lista[i].primeiro;j!=null;j=j.prox,z = z.prox){
				if(j.getElemento()==z.getElemento()){
					count++;
				}else{
					j = null;
				}
			}
		}

	}
	public void ordenar(){
		int trocado, i;
		CelulaDupla ptr1;
		CelulaDupla lptr = null;
		do{
			trocado = 0;
			ptr1 = primeiro;
			while(ptr1.prox!=lptr){
				if(ptr1.elemento>ptr1.prox.elemento){
					int t = ptr1.elemento;
					ptr1.elemento = ptr1.prox.elemento;
					ptr1.prox.elemento = t;
					trocado = 1;
				}
				ptr1 = ptr1.prox;
			}
			lptr = ptr1;
		}while(trocado!=0);
	}
	void swap (int i1, int i2)throws Exception{
			CelulaDupla temp = percorrer(i1);
			CelulaDupla temp2 = percorrer(i2);
			int zoid = temp.getElemento();
			temp.setElemento(temp2.getElemento());
			temp2.setElemento(zoid);
	}
	public CelulaDupla percorrer(int x){
		CelulaDupla i = primeiro;
		for(int z = 0;z<x;z++,i=i.prox);
		return i;
	}	
	void quick()throws Exception{
		quicksort(0,tamanho());
	}
	void quicksort(int esq,int dir)throws Exception{
		int i = esq, j = dir;
		int pivo = percorrer((dir+esq)/2).getElemento();
		while(i<=j){
			while(percorrer(i).getElemento()<pivo){
				comparacoes++;
				i++;
			}
			while(percorrer(j).getElemento()>pivo){
				j--;
			}
			if(i<=j){
				swap(i,j); i++; j--;
			}
		}
			if(esq<j){
				quicksort(esq,j);
			}
			if(i<dir){
				quicksort(i,dir);
			}
	}
	public void tratar(int numero[]){
		for(int i = 0;i<numero.length;i++){
			int num [] = new int[100];
			int numNumeros = numero[i];
			int k = 0;
			ListaDupla lista[] = new Lista[numNumeros];
			for(int j = 0;j<numNumeros;j++){
				i++;
				int temp = numero[i];
				while(temp>0){
					lista.inserirInicio(temp%10);
					temp/=10;
				}
			lista[k].quick();
			k++;
			}
			lista[].mostrarCompara(k);
		}

	}
	public static void main(String[]args){
		Scanner s = new Scanner(System.in);
		int i = 0;
		int numero[] = new int[100];
		while(s.hasNext()){
			String temp = s.next();
			numero[i] = Integer.parseInt(temp);
			i++;
		}
		ordenar(numero);
	}
}

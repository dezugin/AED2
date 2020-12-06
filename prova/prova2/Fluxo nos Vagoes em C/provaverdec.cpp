#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
typedef struct {char entradas[100];}entrada;
typedef struct Celula {
	int elemento;
	struct Celula *prox;
	struct Celula *ant;
	int flag = 0;
}Celula;
typedef struct Fila {
	Celula *primeiro = NULL;
	Celula *ultimo= NULL;
}Fila;
struct Celula* novoItemNulo(){
	Celula* temp = (struct Celula*)malloc(sizeof(struct Celula));
	temp->elemento = 0;
	temp->prox = NULL;
	temp->ant = NULL;
	return temp;
};
struct Fila* criarFila(int x){
	Fila* f = (struct Fila*)malloc(sizeof(struct Fila));
	f->primeiro = f->ultimo = novoItemNulo();
	for(int i = 0;i<x;i++){
		Celula* tmp = novoItemNulo();
		f->ultimo->prox = tmp;	
		f->ultimo->prox->ant = f->ultimo;
		f->ultimo = f->ultimo->prox;
	}
	return f;
}
int main (){	
	int numEntrada = 0;
	entrada *input = (entrada*) malloc (100 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	int num[1000];
	fgets(buffer, 100, stdin);
	char * vagaTividade = strtok(buffer, " ");
	int vagoes = atoi(vagaTividade);
	Fila* fila = criarFila(vagoes);	
	vagaTividade = strtok(NULL, " ");
	int atividades = atoi(vagaTividade);
	//printf("%i %i",vagoes, atividades);
  	for(int i =0;i<atividades;i++){
		fgets(buffer, 100, stdin);
		char * temp = strtok(buffer, " ");
		int numeroAtividade = atoi(temp);
		if(numeroAtividade==1){
			char* xvagoes = strtok(NULL, " ");
			int x = atoi(xvagoes);
			char* ypessoas = strtok(NULL, " ");
			int y = atoi(ypessoas);
			Celula* t = fila->primeiro;
			for(int z = 0;z<x;z++,t=t->prox);
			t->elemento+=y;
		}else{
			int contador = 0;
			char* intervalon11string = strtok(NULL, " ");
			int n11 = atoi(intervalon11string);
			char* intervalon12string = strtok(NULL, " ");
			int n12 = atoi(intervalon12string);
			char* intervalon21string = strtok(NULL, " ");
			int n21 = atoi(intervalon21string);
			char* intervalon22string = strtok(NULL, " ");
			int n22 = atoi(intervalon22string);
			Celula* k = fila->primeiro;
			for(int z = 0;z<n11;z++,k=k->prox);
			int diferenca = n12 - n11;
			for(int t = 0;t<=diferenca;t++,k=k->prox){
					contador+=k->elemento;
					k->flag = 1;
			}
			Celula* l = fila->primeiro;
			for(int z = 0;z<n21;z++,l=l->prox);
			int diferenca2 = n22 - n21;
			for(int t = 0;t<=diferenca2;t++,l=l->prox){
			if(l->flag==0){
				contador+=l->elemento;
				l->flag = 1;
			}
			}
			printf("%d\n",contador);
			for(Celula* r = fila->primeiro;r!=NULL;r=r->prox){
				r->flag=0;
			}

		}
	}
}

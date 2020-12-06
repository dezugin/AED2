/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
#include <stdio.h>
#include <stdlib.h>
#include <err.h>

//TIPO CELULA ===================================================================
typedef struct Celula {
	int elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(int elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

Celula* topo; 


void funcao01(){
   funcao02();
}

void funcao02(){
   funcao03();
}

void funcao03(){
   topo->prox->elemento = 5;
}

int main(int argc, char** argv){
   funcao01();
   topo = novaCelula(0);
   topo->prox = novaCelula(0);
}

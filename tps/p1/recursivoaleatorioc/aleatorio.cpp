#include <stdio.h> 
#include <stdlib.h> 
#include <time.h> 
#include <string.h>
 	struct entrada {char entradas[100];};
	//alterador aleatorio
	void alteracao(char * alterada){
			srand(4);
			for(int i = 0;i<strlen(alterada)-1;i++){
				char sorteado1 =  (char) (rand() % (122-97+1)) + 97;
				char sorteado2 =  (char) (rand() % (122-97+1)) + 97;
				if(alterada[i] == sorteado1){
					alterada[i] = sorteado2;
				}
			}
			}
	
void funcaoRecursiva(int numEntrada, struct entrada * input){
	if(numEntrada!=-1){
		funcaoRecursiva(numEntrada-1,input);
		alteracao(input[numEntrada].entradas);
	}
}
int main (){
	struct entrada *input = (entrada*) malloc (1000 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	int numEntrada = 0;
	//Leitura da entrada padrao
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		numEntrada+=1;
	} while (strcmp(buffer,"FIM\n")!=0);
	numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
	funcaoRecursiva(numEntrada-1,input);
	for(int i = 0;i<numEntrada;i++){
		printf("%s",input[i].entradas);
	}
	return 0;
}


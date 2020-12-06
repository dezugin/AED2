#include <stdio.h> 
#include <stdlib.h> 
#include <time.h> 
#include <string.h>
 	struct entrada {char entradas[100];};
	//alterador aleatorio
	void alteracao(char * alterada){
			srand(4);						
			char sorteado1 =   rand() % (122 - 97 + 1) + 97;
			char sorteado2 =  rand() % (122 - 97 + 1) + 97;
			for(int i = 0;i<strlen(alterada);i++){
				char sorteado1 =   rand() % (122 - 97 + 1) + 97;
				char sorteado2 =  rand() % (122 - 97 + 1) + 97;
				if(alterada[i] == sorteado1){
					alterada[i] = sorteado2;
				}
			}
			}
	
void funcaoRecursiva(int numEntrada, struct entrada * input){
	if(numEntrada!=-1){
		funcaoRecursiva(numEntrada-1,input);
		char [100] resp= alteracao(input[numEntrada].entradas);
		printf("%s\n", resp);
	}
}
int main (){
	struct entrada *input = (entrada*) malloc (100 * sizeof(entrada));
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
	return 0;
}


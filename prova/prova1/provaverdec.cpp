#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
typedef struct {char entradas[100];}entrada;
int main (){	
	int numEntrada = 0;
	entrada *input = (entrada*) malloc (100 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	int num[1000];
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		//int numero = atoi(buffer);
		//num[numEntrada] = numero;
		numEntrada+=1;
	} while (strstr(buffer,"0")==NULL);
	numEntrada--;
	for(int i = 0;i<numEntrada;i++){
		int num = atoi(input[i].entradas);
		i++;
		char* divide;
		int numero [num];
		divide = strtok(input[i].entradas, " ");
		int z = 0;
		while(divide!=0){
			numero[z] = atoi(divide);
			//printf("%d",numero[z]);
			divide = strtok(0," ");
			z++;
		}

		for(int j =0;j<num;j++){
			int compara = numero[j];
				for(int k = j;k<num;k++){
					if(numero[j]==numero[k]){
						for(int t = k;i<num;i++){
							numero[t] = numero[t+1];
						}						
						for(int u = j;i<num;i++){
							numero[u] = numero[u+1];
						}
						k = num;
						num--;
						num--;
					}
				}			
		}
		printf("%d\n",numero[0]);
		
	}
}

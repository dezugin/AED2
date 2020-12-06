#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
#include <math.h>
#define Lista_Length 6
typedef struct { char nome[100]; char corDoCabelo[100]; double peso; char corDaPele[100]; char corDosOlhos[100]; char anoNascimento[100];char  genero[100];int altura;char homeworld[100];} Personagem;
typedef struct {char entradas[100];}entrada;
//METODO PARA CORTAR UMA STRING, PARTINDO DE UM NUMERO DE CARACTERES N
char* substringInicio(char* str2, int n){
	int i,j;
	char* str1=  (char *) malloc(sizeof(char)*5000);
	for(i = n,j = 0; i<strlen(str2)+1; i++,j++){
		if(i==strlen(str2)){
			str1[j]='\0';
		}else{
			str1[j]=str2[i];
		}
	}
	return str1;
}
//metodo para cortar uma string, do inicio a um numero de caracteres n
char* substringFim(char* str2, int fim){
	int j;
	char* str1= (char *) malloc(sizeof(char)*5000);
	for(j = 0; j<=fim;j++){
		if(j==fim){
			str1[j]='\0';
		}else{
			str1[j]=str2[j];
		}
	}
	return str1;
}
int indexOf(const char*needle, char* haystack){
	int i;
	char * found = strstr(haystack,needle);
	if(found != NULL){
		i = found - haystack;
	}
	return i;
}
double removeTags(char * s){
	double resp;
	char * num = (char*)calloc(100,sizeof(char));
		for(int i = 0;i<strlen(s);i++){
			if (s[i]!=','){
				strncat(num, &s[i], 1);
			}
		}
//	sscanf( num, "%d", &resp);
	resp = atoi(num);
	//free(num);
	return resp;
}
void ler(char nomeArquivo[],Personagem* personagem){
	char * linha1 = (char*) malloc(sizeof(char)*5000);
	char * linha = (char *) malloc(sizeof(char)*5000);
	char * linhaCopy = (char *) malloc(sizeof(char)*5000);
	nomeArquivo[strlen(nomeArquivo)-1] = '\0';
	FILE * p = fopen (nomeArquivo, "r");
	//ler paragrafo relevante
	fscanf(p,"%4999[^\n]\n",linha1);
	//fread(&linha1, sizeof(char)*5000, 4999,p);
	strcpy(linhaCopy, linha1);
	//ler NOME
	linha = substringInicio(linha1,indexOf("name",linha1)+8);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).nome, linha);
	// ler ALTURA **
	memset(linha,0,strlen(linha));
	strcpy(linha1,linhaCopy);	
	linha = substringInicio(linha1,indexOf("height",linha1)+10);
	linha = substringFim(linha, indexOf("'",linha));
	bool seAlt0 = strstr(linha,"nkn")!=NULL;
	if(seAlt0){
		(*personagem).altura = 0;
	}else{
		(*personagem).altura = atoi(linha);
	}
	//ler PESO*
	memset(linha,0,strlen(linha));
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("mass",linha1)+8);
	linha = substringFim(linha, indexOf("'",linha));
	bool sePes0 = strstr(linha,"nkn")!=NULL;
	bool seVirgula = strstr(linha,",")!=NULL;
	if(seVirgula){
		(*personagem).peso = removeTags(linha);
	}else{
		if(sePes0){
			(*personagem).peso = 0;
		}else{
			//printf("debug %s", linha);
			sscanf(linha, "%lf", &personagem->peso);
			//personagem->peso = atof(linha);
		//	printf("debug2 %g",personagem->peso);
		}
	}
	// ler COR DO CABELO
	memset(linha,0,strlen(linha));
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("hair_color",linha1)+14);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).corDoCabelo, linha);	
	// ler COR DA PELE
	memset(linha,0,strlen(linha));
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("skin_color",linha1)+14);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).corDaPele, linha);
	// ler COR DOS OLHOS
	memset(linha,0,strlen(linha));
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("eye_color",linha1)+13);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).corDosOlhos, linha);
	// LER ANO DE NASCIMENTO
	memset(linha,0,strlen(linha));	
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("birth_year",linha1)+14);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).anoNascimento, linha);
	// LER GENERO
	memset(linha,0,strlen(linha));	
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("gender",linha1)+10);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).genero, linha);
	// ler mundo natal
	memset(linha,0,strlen(linha));	
	strcpy(linha1,linhaCopy);
	linha = substringInicio(linha1,indexOf("homeworld",linha1)+13);
	linha = substringFim(linha, indexOf("'",linha));
	strcpy((*personagem).homeworld, linha);
	//free(linha);
	free(linha1);
	free(linhaCopy);
	fclose(p);
}
void imprimir(Personagem *personagem){
	printf("## %s ## %i ## ", personagem->nome,personagem->altura);
	int seZero = (int) (personagem->peso*1000);			
	if(seZero%1000==0){
		int weight = (int) personagem->peso;
		printf("%i",weight);				
	}else{
		printf("%.1lf",personagem->peso);
		}
	printf(" ## %s ## %s ## %s ## %s ## %s ## %s ## \n", personagem->corDoCabelo,personagem->corDaPele,personagem->corDosOlhos, personagem->anoNascimento,personagem->genero,personagem->homeworld);
}
typedef struct Celula {
	Personagem elemento;
	struct Celula *prox;
	struct Celula *ant;
}Celula;
void mostrar(Celula * primeiro){
	while(primeiro){
		imprimir(&primeiro->elemento);
		primeiro = primeiro->prox;
	}
}
void inserir2(Celula** primeiro, Personagem x){
	Celula * temp = (struct Celula*)malloc(sizeof(struct Celula));
	temp->ant = NULL;
	temp->prox = (*primeiro);
	if((*primeiro)!=NULL){
		(*primeiro)->ant = temp;
	}
	(*primeiro) = temp;
}
Celula* ultimaCelula(Celula* primeiro){
	while(primeiro&&primeiro->prox){
		primeiro = primeiro->prox;
	}
	return primeiro;
}
void swap (Personagem* i1, Personagem* i2){
	Personagem temp = *i1;
	*i1 = *i2;
	*i2 = temp;
}
struct Celula * particao(Celula * esq,Celula* dir){
	Personagem x = dir->elemento;
	Celula *i = esq->ant;
	for(Celula * j = esq;j!=dir;j=j->prox){
		if(strcmp(j->elemento.corDoCabelo,x.corDoCabelo)<0||(strstr(j->elemento.corDoCabelo,x.corDoCabelo)&&(strcmp(j->elemento.nome,x.nome)<0))){
			i = ( i = NULL )?esq:i->prox;
			swap(&(i->elemento),&(j->elemento));
		}
	}
	i = (i==NULL)?esq:i->prox;
	swap(&(i->elemento),&(dir->elemento));
	return i;
}
void quicksort(Celula * esq, Celula* dir){
	if(dir!=NULL&&esq!=dir&&esq!=dir->prox){
		Celula * c = particao(esq,dir);
		quicksort(esq,c->ant);
		quicksort(c->prox,dir);
	}
}
void quick(Celula* primeiro){
	Celula * ultimo = ultimaCelula(primeiro);
	quicksort(primeiro, ultimo);	
}
int main (){
	setlocale(LC_ALL, "");
	Personagem personagem;
	entrada *input = (entrada*) malloc (100 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	int numEntrada = 0;
	//Fila* lista = criarFila();
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		numEntrada+=1;
	//	printf("%s",buffer);
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
	ler(input[0].entradas,&personagem);
//	Fila* lista = criarFila(personagem);
	Celula * c = NULL;
	for(int i = 0; i < numEntrada; i++){
		ler(input[i].entradas, &personagem);
	//	printf("%s",personagem.nome);
		inserir2(&c,personagem);
	}
	quick(c);
	mostrar(c);		
	return 0;
}

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
#include <time.h>
#include <math.h>
typedef struct { char nome[100]; char corDoCabelo[100]; double peso; char corDaPele[100]; char corDosOlhos[100]; char anoNascimento[100];char  genero[100];int altura;char homeworld[100];} Personagem;
typedef struct {char entradas[100];}entrada;
int comparacoes = 0;
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
typedef struct Celula{
	Personagem elemento;
	struct Celula* prox;
}Celula;
struct Celula* novaCelula(Personagem elemento){
	Celula* temp = (struct Celula*)malloc(sizeof(struct Celula));
	temp->elemento = elemento;
	temp->prox = NULL;
	return temp;
}
struct Celula* novaNula(){
	Celula* temp = (struct Celula*)malloc(sizeof(struct Celula));
	Personagem p;
	temp->elemento = p;
	temp->prox = NULL;
	return temp;
}
typedef struct Lista {
	Celula* primeiro;
	Celula* ultimo;
}Lista;
struct Lista* novaLista(){
	Lista *jkk = (Lista*)malloc(sizeof(Lista));
	jkk->primeiro = jkk->ultimo = novaNula();
	return jkk;
}

void inserirLista(Personagem x, Lista *lista){
	Celula* temp = novaCelula(x);
	lista->ultimo->prox = temp;
	lista->ultimo = lista->ultimo->prox;
}	
bool pesquisarLista(char * x, Lista * lista){
	bool retorno = false;
	for(Celula* i = lista->primeiro->prox;i!=NULL;i = i->prox){
		if(strcmp(i->elemento.nome,x)==0){
			retorno = true;
			i = lista->ultimo;
		}
	}
	return retorno;
}
typedef struct HashIndiretoLista{
	Lista * tabela;
}HashIndiretoLista;
struct HashIndiretoLista* novoHash(){
	HashIndiretoLista* hs;
	hs->tabela = (Lista*)malloc(25*sizeof(Lista));
	for(int i = 0;i<25;i++){
		hs->tabela[i] = novaLista();	
	}
	return hs;
}
int h(int elemento){
	return elemento % 25;
}
bool pesquisar(char* elemento, HashIndiretoLista * hash){
	int pos = h(elemento);
	bool retorno = false;
	for(int i = 0;i<25;i++){
		if(hash->tabela[i]->primeiro->prox!=NULL){
			retorno = pesquisarLista(elemento, tabela[i];
			if(retorno){
				i = 25;
			}	
		}
	}
	return retorno;
}
void inserir(Personagem x, HashIndiretoLista * hash){
	int pos = h(x.altura);
	inserirLista(x,hash[pos]);
}
int main (){
	clock_t t; 
	t = clock();
	setlocale(LC_ALL, "");
	Personagem personagem;
	entrada *input = (entrada*) malloc (100 * sizeof(entrada));
	entrada *input2 = (entrada*) malloc (100 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	char * buffer2 = (char *) malloc (100 * sizeof(char));
	int numEntrada = 0;
	int numEntrada2 = 0;
	HashIndiretoLista * hash = novoHash();
	//Leitura da entrada padrao
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		numEntrada+=1;
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
	do {
		fgets(buffer, 100, stdin);
		strcpy(input2[numEntrada2].entradas,  buffer);
		numEntrada2+=1;
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM
	//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
	for(int i = 0; i < numEntrada; i++){
		ler(input[i].entradas, &personagem);
		inserir(personagem,hash);
	}
	//mostrarPre(raiz); 
	for(int j = 0;j<numEntrada2;j++){
		input2[j].entradas[strlen(input2[j].entradas)-1] = '\0';
		bool temp = pesquisar(input2[j].entradas, hash);
		if(temp){
			printf(" SIM\n");
		}else{
			printf(" NÃO\n");
		}			
	}
	t = clock() - t; 
	char tempCompara[20];
	snprintf(tempCompara, 20, "%f", t);
	double time_taken = ((double)t)/CLOCKS_PER_SEC;
	FILE * fp;
	fp = fopen ("395702_avl.txt", "w+");
	char str[10];
	sprintf(str, "%d", comparacoes);
	fputs("396702\t", fp);
	fputs(tempCompara, fp);
	fputs("\t",fp);
	fputs(str, fp);
	fputs("\t",fp);
	fclose (fp);		
	return 0;
}

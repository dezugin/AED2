#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
#include <time.h>
#include <math.h>
#define Lista_Length 6
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
typedef struct No {
	Personagem elemento;
	struct No *esq;
	struct No *dir;
	int nivel;
}No;
No* raiz;
void start(){
	raiz = NULL;
}

struct No* novoItem(Personagem x){
	No* temp = (struct No*)malloc(sizeof(struct No));
	temp->elemento = x;
	temp->esq = NULL;
	temp->dir = NULL;
	temp->nivel = 1;	
	return temp;
};
struct No* novo(Personagem x, No* esq, No*dir, int nivel){
	No* temp = (struct No*)malloc(sizeof(struct No));
	temp->elemento = x;
	temp->esq = esq;
	temp->dir = dir;
	temp->nivel = nivel;	
	return temp;
};
int getNivel(No* temp){
	return(temp=NULL) ? 0 : temp->nivel;
}
struct No** setNivel(No** temp){
	int z = (getNivel((*temp)->esq) > getNivel((*temp)->dir) ) ? getNivel((*temp)->esq) : getNivel((*temp)->dir);
	(*temp)->nivel = 1 + z;
	return temp;
}
bool pesquisar2(No* no, char* x){
	bool resp;
	if(no==NULL){
		resp = false;
	}else if(strcmp(x,no->elemento.nome)==0){
		resp = true;
	}else if( strcmp(x, no->elemento.nome)<0){
		printf("esq ");
		resp = pesquisar2(no->esq,x);
	}else{
		printf("dir ");
		resp = pesquisar2(no->dir,x);
	}
	return resp;
}
bool pesquisar(char* elemento){
	return pesquisar2(raiz,elemento);
}

No** rotacionarDir(No** no){
	No** noEsq = &((*no)->esq);
	No** noEsqDir = &((*noEsq)->dir);
	(*noEsq)->dir = (*no);
	(*no)->esq = (*noEsqDir);
	no = setNivel(no);
	noEsq = setNivel(noEsq);
	return noEsq;
}
No** rotacionarEsq(No** no) {
	No** noDir = &((*no)->dir);
	No** noDirEsq = &((*noDir)->esq);
	(*noDir)->esq = (*no);
	(*no)->dir = (*noDirEsq);
	no = setNivel(no);
	noDir = setNivel(noDir);
	return noDir;
}

void balancear(No** no){
	if(*no!=NULL){
		int fator = getNivel((*no)->dir) - getNivel((*no)->esq);
		if(abs(fator)<=1){
			setNivel(no);
		}else if(fator == 2){
			int fatorFilhoDir = getNivel((*no)->dir->dir) - getNivel((*no)->dir->esq);
			if(fatorFilhoDir == -1){
				rotacionarDir(&((*no)->dir));
			}
			rotacionarEsq(no);
		}else if(fator == -2){
			int fatorFilhoEsq = getNivel((*no)->esq->dir) - getNivel((*no)->esq->esq);
			if( fatorFilhoEsq == 1){
				rotacionarEsq(&((*no)->esq));
			}
			rotacionarDir(no);
		}else{
			exit(1);
		}
	}
}
	
void inserir2(No** no, Personagem x){
	if(*no == NULL){
		*no = novoItem(x);
	}else if( strcmp(x.nome, (*no)->elemento.nome)<0){
		inserir2(&((*no)->esq), x);
	}else if (strcmp(x.nome, (*no)->elemento.nome)>0){
		inserir2(&((*no)->dir), x);
	}else{
		exit(1);
	}
	no = balancear(no);
}
void inserir(Personagem x){
	inserir2(&raiz,x);	
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
	start();
	//Leitura da entrada padrao
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		numEntrada+=1;
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
	//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
	for(int i = 0; i < numEntrada; i++){
		ler(input[i].entradas, &personagem);
		inserir(personagem);
	}
	
	for(int j = 0;j<numEntrada;j++){
		printf("%s raiz ",input[j].entradas);
		bool temp = pesquisar(input[j].entradas);
		if(temp){
			printf("SIM");
		}else{
			printf("NÃO");
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

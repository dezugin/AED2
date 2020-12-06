#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h> 
#include <locale.h>
#include <time.h>
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
typedef struct {
	Personagem array[100];
	int n;
}Lista;
void start(Lista * lista){
	lista->n = 0;
}
void inserirInicio(Personagem x, Lista * lista){
	if(lista->n>=100){
		exit(1);
	}
	for(int i = lista->n;i>0;i--){
		lista->array[i] = lista->array[i-1];
	}
	lista->array[0] = x;
	lista->n++;
}
void inserirFim(Personagem x, Lista * lista){
	if(lista->n>=100){
		exit(1);
	}
	lista->array[lista->n]=x;
	lista->n++;
}
void inserir(Personagem x, int pos, Lista * lista){
	if(lista->n>=100){
		exit(1);
	}
	for(int i = lista->n;i>pos;i--){
		lista->array[i]=lista->array[i-1];
	}
	lista->array[pos]=x;
	lista->n++;
}
Personagem removerInicio(Lista * lista){
	if(lista->n==0){
		exit(1);
	}
	Personagem resp = lista->array[0];
	lista->n--;
	for(int i = 0;i<lista->n;i++){
		lista->array[i]=lista->array[i+1];
	}
	return resp;
}
Personagem removerFim(Lista * lista){
	if(lista->n==0){
		exit(1);
	}
	return lista->array[--lista->n];
}
Personagem remover(int pos, Lista * lista){
	if(lista->n==0||pos<0||pos>=lista->n){
		exit(1);
	}
	Personagem resp = lista->array[pos];
	lista->n--;
	for(int i = pos;i<lista->n;i++){
		lista->array[i]=lista->array[i+1];
	}
	return resp;
}
void mostrar(Lista * lista){
	for(int i =1;i<=lista->n;i++){
		printf("[%d]  ",i);
		imprimir(&lista->array[i]);
	}
}
void tratar(char* s, Lista *lista){
	char * comando = (char*) malloc(sizeof(char)*100);
	int pos;
	Personagem resp;
	Personagem respRemove;
	if(lista->n>=100){
		exit(1);
	}
	bool seII = strstr(s,"II")!=NULL;
	bool seIF = strstr(s,"IF")!=NULL;
	bool seR = strstr(s,"R*")!=NULL;
	bool seRI = strstr(s,"RI")!=NULL;
	bool seRF = strstr(s,"RF")!=NULL;
	bool seI = strstr(s,"I*")!=NULL;
	
	if(seII){
		comando = substringInicio(s,indexOf(" ",s)+1);
		ler(comando,&resp);
		inserirInicio(resp, lista);
	}else if(seIF){
		comando= substringInicio(s,indexOf(" ",s)+1);
		ler(comando,&resp);
		inserirFim(resp, lista);
	}else if(seR){
		comando= substringInicio(s,indexOf(" ",s)+1);
		pos = atoi(comando);
		respRemove = remover(pos, lista);
		printf("(R) %s\n", respRemove.nome);
	}else if(seRI){
		respRemove = removerInicio(lista);
		printf("(R) %s\n", respRemove.nome);
	}else if(seRF){
		respRemove = removerFim(lista);
		printf("(R) %s\n", respRemove.nome);
	}else if(seI){
		char * instruct = substringInicio(s,indexOf(" ",s)+1);
		comando= substringInicio(instruct,indexOf(" ",instruct)+1);
		instruct = substringFim(instruct,indexOf(" ",instruct));
		pos = atoi(instruct);
		ler(comando,&resp);
		inserir(resp,pos, lista);
	}
}
void pesquisaBinaria(char* s, Lista *lista){
	bool seTem = false;
	for(int i = 0;i<lista->n;i++){
		comparacoes++;
		if(strstr(s,lista->array[i].nome)){
		seTem = true;
		}
	}
	if(seTem){
		comparacoes++;
		printf("SIM\n");
	}else{
		printf("NAO\n");
	}
}
void swap (int i1, int i2, Lista * lista){
	Personagem temp = lista->array[i1];
	lista->array[i1] = lista->array[i2];
	lista->array[i2] = temp;
}
void quicksort(int esq, int dir,Lista * lista){
	int i = esq, j = dir;
	Personagem pivo = lista->array[(dir+esq)/2];
	while(i<=j){
		while(strcmp(lista->array[i].corDoCabelo,pivo.corDoCabelo)<0||(strcmp(lista->array[i].corDoCabelo,pivo.corDoCabelo)==0&&strcmp(lista->array[i].nome,pivo.nome)<0)){
			i++;
			comparacoes++;
		}
		while(strcmp(lista->array[j].corDoCabelo,pivo.corDoCabelo)>0||(strcmp(lista->array[j].corDoCabelo,pivo.corDoCabelo)==0&&strcmp(lista->array[j].nome,pivo.nome)>0)){
			j--;
			comparacoes++;
		}
		comparacoes+=3;
		if(i<=j){
			comparacoes++;
			swap(i,j,lista);
			i++;
			j--;
		}
	}
	if(esq<j){
		quicksort(esq,j,lista);
	}
	if(i<dir){
		quicksort(i,dir,lista);
	}
	comparacoes+=2;
}
int main (){
	clock_t t; 
	t = clock(); 
	setlocale(LC_ALL, "");
	//char * result = setlocale (LC_ALL, "");
	//printf("locale eh %s",result);
	Personagem personagem;
	entrada *input = (entrada*) malloc (100 * sizeof(entrada));
//	entrada *input2 = (entrada*) malloc (100 * sizeof(entrada));
	char * buffer = (char *) malloc (100 * sizeof(char));
	char * buffer2 = (char *) malloc (100 * sizeof(char));
	int numEntrada = 0;
//	int numEntrada2;
	Lista lista;
	start(&lista);
	//Leitura da entrada padrao
	do {
		fgets(buffer, 100, stdin);
		strcpy(input[numEntrada].entradas,  buffer);
		numEntrada+=1;
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
/*	do {
		fgets(buffer, 100, stdin);
		strcpy(input2[numEntrada2].entradas,  buffer);
		numEntrada2+=1;
	} while (strstr(buffer,"FIM")==NULL);
	numEntrada2--;
*/	for(int i = 0; i < numEntrada; i++){
		ler(input[i].entradas, &personagem);
		inserirFim(personagem,&lista);
	}
	/*for(int z = 0;z<numEntrada2;z++){
		//tratar(input2[z].entradas,&lista);
		pesquisaBinaria(input2[z].entradas,&lista);
	}*/
//	printf("%d",numEntrada);
	quicksort(0,numEntrada,&lista);
	mostrar(&lista);
	t = clock() - t; 
	char tempCompara[20];
	snprintf(tempCompara, 20, "%f", t);
	double time_taken = ((double)t)/CLOCKS_PER_SEC;
	FILE * fp;
	fp = fopen ("395702_quicksort.txt", "w+");
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

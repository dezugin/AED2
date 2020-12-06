/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */

#include "no.h"
#define bool   short
#define true   1
#define false  0

bool pesquisarRec(int, No*);
void mostrarCentralRec(No*);
void mostrarPreRec(No*);
void mostrarPosRec(No*);
void inserirRec(int, No**);
void removerRec(int, No**);
void antecessor(No**, No**);

void start();
bool pesquisar(int);
void mostrarCentral();
void mostrarPre();
void mostrarPos();
void inserir(int);
void remover(int);

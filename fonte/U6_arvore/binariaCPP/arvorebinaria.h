/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */

#include "no.h"

class ArvoreBinaria {
   private:
      No* raiz; // Raiz da arvore.
	   bool pesquisar(int, No*);
	   void mostrarCentral(No*);
	   void mostrarPre(No*);
	   void mostrarPos(No*);
	   void inserir(int, No* &);
	   void remover(int, No* &);
	   void antecessor(No*, No* &);

   public:
      ArvoreBinaria();
	   bool pesquisar(int);
	   void mostrarCentral();
	   void mostrarPre();
	   void mostrarPos();
	   void inserir(int);
	   void remover(int);
};

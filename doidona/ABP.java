import java.io.*;

class ABP{

   private No raiz;
   private int n;

   public ABP(){

      raiz = null;
      n = 0;
   }

   public No getRaiz(){ return raiz; }

   public void inserir(int e){
      raiz = inserir(e, raiz);
   }

   private No inserir(int e, No i){

      if(i == null) {         
         i = new No(e);
         n++;
      } else if(e < i.elemento) {
         i.esq = inserir(e, i.esq);
      } else if(e > i.elemento) {
         i.dir = inserir(e, i.dir);
      } else {
         MyIO.println("Erro!");
      }

      return i;
   }
   
   public boolean pesquisar(int e){
      return pesquisar(e, raiz);
   }

   public boolean pesquisar(int e, No i){

      boolean resp;

      if(i == null){
         resp = false;
      } else if(e == i.elemento){
         resp = true;
      } else if(e < i.elemento){
         resp = pesquisar(e, i.esq);
      } else{
         resp = pesquisar(e, i.dir);
      }

      return resp;
   }  


   public void mostrarCentral(){
      MyIO.print("[ ");
      mostrarCentral(raiz);
      MyIO.println("]");
   }

   private void mostrarCentral(No i){

      if(i != null){
         mostrarCentral(i.esq);
         MyIO.print(i.elemento + " ");
         mostrarCentral(i.dir);
      }
   }


   public void mostrarPos(){
      MyIO.print("[ ");
      mostrarPos(raiz);
      MyIO.println("]");
   }
 
   private void mostrarPos(No i){

      if(i != null){
         mostrarPos(i.esq);
         mostrarPos(i.dir);
         MyIO.print(i.elemento + " ");
      }
   }


   public void mostrarPre(){
      MyIO.print("[ ");
      mostrarPre(raiz);
      MyIO.println("]");
   }
 
   private void mostrarPre(No i){

      if(i != null){
         MyIO.print(i.elemento + " ");
         mostrarPre(i.esq);
         mostrarPre(i.dir);
      }
   }


   public int soma(){
      return soma(raiz);
   }

   private int soma(No i){

      int soma = 0;

      if(i != null){
         soma += i.elemento + soma(i.esq) + soma (i.dir);
      }

      return soma;
   }


   public int quantPares(){
      return quantPares(raiz);
   }

   private int quantPares(No i){

      int cont = 0;

      if(i != null){

         if(i.elemento % 2 == 0){
            cont = 1 + quantPares(i.esq) + quantPares(i.dir);
         } else {
            cont = quantPares(i.esq) + quantPares(i.dir);
         }

      }

      return cont;
   }


   public static boolean iguais(ABP ar1, ABP ar2){
      return iguais(ar1.getRaiz(), ar2.getRaiz());
   }

   private static boolean iguais(No a, No b){
      
      boolean teste = true;

      if(a != null && b != null){         
         teste = teste && (a.elemento == b.elemento) && iguais(a.esq, b.esq) && iguais(a.dir, b.dir);
      } else if (a == null && b == null){
         teste = true;
      } else {
         teste = false;
      }

      return teste;
   }


   public boolean mod11(){
      return mod11(raiz);
   }

   private boolean mod11(No i){
      
      boolean teste = false;

      if(i != null){         
         teste = (i.elemento % 11 == 0) || mod11(i.esq) || mod11(i.dir);
      }

      return teste;
   }


   public No toArvoreBinaria(Celula a, CelulaDupla b){        
      return toArvoreBinaria(a.prox, b.prox, null); 
   }

   private No toArvoreBinaria(Celula i, CelulaDupla j, No rz){

      if(i != null && j != null){

         rz = inserir(i.elemento, rz);
         rz = inserir(j.elemento, rz);

         rz = toArvoreBinaria(i.prox, j.prox, rz);

      } else if (i != null && j == null){

         rz = inserir(i.elemento, rz);

         rz = toArvoreBinaria(i.prox, j, rz);

      } else if (i == null && j != null) {

         rz = inserir(j.elemento, rz);

         rz = toArvoreBinaria(i, j.prox, rz);

      }

      return rz;
   }

   public static void mostrarABCentral(No raiz){
      MyIO.print("[ ");
      mostrarArvoreCentral(raiz);
      MyIO.println("]");
   }

   private static void mostrarArvoreCentral(No i){

      if(i != null){
         mostrarArvoreCentral(i.esq);
         MyIO.print(i.elemento + " ");
         mostrarArvoreCentral(i.dir);
      }
   }


   public void inserirNoReturn(int e){

      if(raiz == null) {         
         raiz = new No(e);         
      } else if(e < raiz.elemento) {
         inserirFilho(raiz.esq, raiz, e);
      } else if(e > raiz.elemento) {
         inserirFilho(raiz.dir, raiz, e);
      } else {
         MyIO.println("Erro!");
      }
   }

   private void inserirFilho(No filho, No pai, int e){

      if(filho == null){

         if(e < pai.elemento){
            pai.esq = new No(e);
         } else {
            pai.dir = new No(e);
         }

      } else if(e < filho.elemento){
         inserirFilho(filho.esq, filho, e);
      } else if(e > filho.elemento){
         inserirFilho(filho.dir, filho, e);
      } else{
         MyIO.println("Erro! - F");
      }
      
   }


   public void removerNoReturn(int e) {
      
      if(raiz == null){
         MyIO.println("Erro - RNR!");
      } else if (e < raiz.elemento){
         removerFilho(raiz.esq, raiz, e);
      } else if (e > raiz.elemento){
         removerFilho(raiz.dir, raiz, e);
      } else if (raiz.dir == null){
         raiz = raiz.esq;
      } else if (raiz.esq == null){
         raiz = raiz.dir;
      } else {
         raiz.esq = antecessor(raiz, raiz.esq);
      }
   }

   private void removerFilho(No filho, No pai, int e) {
      
      if(filho == null){
         MyIO.println("Erro - RF!");
      } else if (e < filho.elemento){
         removerFilho(filho.esq, filho, e);
      } else if (e > filho.elemento){
         removerFilho(filho.dir, filho, e);
      } else if (filho.dir == null){

         if(e < pai.elemento){
            pai.esq = filho.esq;
         } else {
            pai.dir = filho.esq;
         }

      } else if(filho.esq == null){

         if(e < pai.elemento){
            pai.esq = filho.dir;
         } else {
            pai.dir = filho.dir;
         }

      } else {
         filho.esq = antecessor(filho, filho.esq);
      }
   }


   private No antecessor (No i, No j){

      if(j.dir != null){
         j.dir = antecessor(i, j.dir);
      } else {
         i.elemento = j.elemento;
         j = j.esq;
      }

      return j;
   }


   public void remover(int e){
      raiz = remover(e, raiz);
   }

   private No remover(int e, No i){
      
      if(i == null){
         MyIO.println("ERRO - R");
      } else if(e < i.elemento){
         i.esq = remover(e, i.esq);
      } else if(e > i.elemento){
         i.dir = remover(e, i.dir);
      } else if(i.dir == null){
         i = i.esq;
      } else if(i.esq == null){
         i = i.dir;
      } else{
         i.esq = anterior(i, i.esq);
      }

      return i;
   }

   private No anterior(No i, No j){
      
      if(j.dir != null){
         j.dir = anterior(i, j.dir);
      } else {
         i.elemento = j.elemento;
         j = j.esq;
      }

      return j;
   }


   public void treeSort(int [] a){      

      for (int i = 0; i < a.length; i++){
         inserir(a[i]);
      }

      treeSort(raiz);
   }

   private void treeSort(No i){

      if(i != null){
         treeSort(i.esq);
         MyIO.println(i.elemento + " ");
         treeSort(i.dir);
      }
   }

   public int removerReturnE(int e) {

      int removido = 0;
      
      if(raiz == null){
         MyIO.println("Erro - RNR!");
      } else if (e < raiz.elemento){
         removido = removerReturnE(raiz.esq, raiz, e);
      } else if (e > raiz.elemento){
         removido = removerReturnE(raiz.dir, raiz, e);
      } else if (raiz.dir == null){
         raiz = raiz.esq;
      } else if (raiz.esq == null){
         raiz = raiz.dir;
      } else {
         raiz.esq = antecessor(raiz, raiz.esq);
      }

      return removido;
   }

   private int removerReturnE(No filho, No pai, int e) {

      int removido = 0;
      
      if(filho == null){
         MyIO.println("Erro - RF!");
      } else if (e < filho.elemento){
         removido = removerReturnE(filho.esq, filho, e);
      } else if (e > filho.elemento){
         removido = removerReturnE(filho.dir, filho, e);
      } else if (filho.dir == null){

         if(e < pai.elemento){
            pai.esq = filho.esq;
         } else {
            pai.dir = filho.esq;
         }

      } else if(filho.esq == null){

         if(e < pai.elemento){
            pai.esq = filho.dir;
         } else {
            pai.dir = filho.dir;
         }

      } else {
         filho.esq = antecessor(filho, filho.esq);
      }

      return removido;
   }

}

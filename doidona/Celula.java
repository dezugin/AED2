class Celula{

   public int elemento;
   public Celula prox;

   public Celula(){
      this(0);
   }

   public Celula(int e){

      elemento = e;
      prox = null;
   }

}

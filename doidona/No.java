class No{

   public int elemento;
   public No esq;
   public No dir;

   public No(){
      this(0);
   }

   public No(int e){

      elemento = e;
      esq = null;
      dir = null;
   }

}

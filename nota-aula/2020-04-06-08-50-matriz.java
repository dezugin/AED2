class Matriz {
   private int linha, coluna;
   Matriz(){...}
   Matriz(int, int){...}

   void showDiagonalPrincipal(){
      if(this.linha == this.coluna && this.linha > 0){
           Celula i = inicio;
           do {
              System.out.println(i.elemento);
              i = i.prox;
              if(i != null){
                 i = i.inf;
              }
           } while (i != null);
      }
   }

   public Matriz soma(Matriz outra){
      return (this, outra);
   }

   public static Matriz soma(Matriz A, Matriz B){
      Matriz resp = null;
      if(A.linha == B.linha && A.coluna == B.coluna){
         resp = new Matriz(A.linha, A.coluna);
         Celula iResp = resp.inicio;
         Celula iA    = A.inicio;
         Celula iB    = B.inicio;

         for(int i = 0; i < linha; i++){
            Celula jResp = iResp;
            Celula jA    = iA;
            Celula jB    = iB;

            for(int j = 0; j < coluna; j++){
               jResp.elemento = jA.elemento + jB.elemento;   
               jResp = jResp.prox;
               jA = jA.prox;
               jB = jB.prox;
            }
            iResp = iResp.inf;
            iA = iA.inf;
            iB = iB.inf;
         }
      }
      return resp;
   }
}




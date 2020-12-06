class Celula {
   int elemento;
   Celula prox, ant, inf, sup;
   public Celula (){
      this(0);
   }
   public Celula(int elemento){
      this.elemento = elemento;
      prox = ant = inf = sup = null;
   }
}

public class Matriz {
   int linha, coluna;
   Celula inicio;
   public Matriz (){
      this(3, 3);
   }
   public Matriz(int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
      int x = 0;

      Celula i, j;
      int lin, col;

      //Criar a primeira celula
      inicio = new Celula(x++);

      //Criar as demais (coluna-1) celulas da 1a linha
      for(j = inicio, col = 1; col < coluna; j = j.prox, col++){
         j.prox = new Celula(x++);
         j.prox.ant = j;
      }

      //Criar as demais (linha-1) linhas
      for(i = inicio, lin = 1; lin < linha; i = i.inf, lin++){
         i.inf = new Celula(x++);
         i.inf.sup = i;

         for(j = i.inf, col = 1; col < coluna; j = j.prox, col++){
            j.prox = new Celula(x++);
            j.prox.ant = j;
            j.prox.sup = j.sup.prox;
            j.sup.prox.inf = j.prox;
         }
      }
   }

   public void mostrar(){
      for(Celula i = inicio; i != null; i = i.inf){
         for(Celula j = i; j != null; j = j.prox){
            System.out.println("[" + j + "] = " + j.elemento + " -- (prox = " + j.prox + "/ ant =" + j.ant + " / inf = " + j.inf + "/ sup = " + j.sup +")");
         }
      }
   }

   public void showDiagonalPrincipal(){
      if(this.linha == this.coluna && linha > 0){
         Celula i = inicio;
         do {
            System.out.println(i.elemento);
            i = i.prox;
            if(i != null){
               i = i.inf;
            }
         } while(i != null);
      }
   }

   public Matriz soma(Matriz a){
      return soma(this, a);
   }

   public static Matriz soma(Matriz a, Matriz b){
      Matriz resp = null;

      if(a.linha == b.linha && a.coluna == b.coluna){
         resp = Matriz(a.linha, a.coluna);
            
         Celula iResp = resp.inicio;
         Celula iA    = a.inicio;
         Celula iB    = b.inicio;

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

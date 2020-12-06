class Doidona {

   final int NULO = -0x7FFFFF;

   private int tamanho1;
   private int reserva;
   private int tamanho3;

   int[] tabH1;  
   int[] tabH3;
   
   Lista lista;
   ABP ab1;
   ABP ab2;

   public Doidona (){
      this(7, 3 , 7);
   }

   public Doidona(int tm1, int tm3, int res){

      tamanho1 = tm1;
      tamanho3 = tm3;
      reserva = res;

      tabH1 = new int [tamanho1];
      tabH3 = new int [tamanho3];

      for(int i = 0 ; i < tamanho1 ; i++){
         tabH1[i] = NULO;
      }

      for(int i = 0 ; i < tamanho3 ; i++){
         tabH3[i] = NULO;
      }

      lista = new Lista();
      ab1 = new ABP();
      ab2 = new ABP();
   }

   private int hashT1(int e){
      return e % tamanho1;
   }

   private int hashT2(int e){
      return e % reserva;
   }

   private int hashT3(int e){
      return e % tamanho3;
   }

   private int rehashT3(int e){
      return ++e % tamanho3;
   }

   public void inserir(int e){

      int pos = hashT1(e);
        
      if(tabH1[pos] == NULO){

         tabH1[pos] = e;

      } else if(hashT2(e) == 0){

         pos = hashT3(e);

         if(tabH3[pos] == NULO){

            tabH3[pos] = e;

         } else {

            pos = rehashT3(e);

            if(tabH3[pos] == NULO){

               tabH3[pos] = e;

            } else {

               ab1.inserir(e);

            }
         }

      } else if (hashT2(e) == 1){

         lista.inserirFim(e);

      } else if (hashT2(e) == 2){
         
         ab2.inserir(e);

      } else {
         MyIO.println("erro");
      }
   }


   public void remover (int e){

      int pos = hashT1(e);

      if (tabH1[pos] == e){

         tabH1[pos] = NULO;

      } else {

         pos = hashT2(e);

         if (pos == 0){

            pos = hashT3(e);

            if(tabH3[pos] == e){

               tabH3[pos] = NULO;

            } else {

               pos = rehashT3(e);

               if (tabH3[pos] == e){

                  tabH3[pos] = NULO;

               } else {

                  ab1.remover(e);

               }
            }

         } else if (pos == 1){

            lista.remover(e);

         } else {

            ab2.remover(e);

         }
      }      
   }
   

   public boolean pesquisar (int e){

      boolean resp = false;

      int pos = hashT1(e);

      if (tabH1[pos] == e){

         resp = true;

      } else {

         pos = hashT2(e);

         if (pos == 0){

            pos = hashT3(e);

            if(tabH3[pos] == e){

               resp = true;

            } else {

               pos = rehashT3(e);

               if (tabH3[pos] == e){

                  resp = true;

               } else {

                  resp = ab1.pesquisar(e);

               }
            }

         } else if (pos == 1){

            resp = lista.pesquisar(e);

         } else {

            resp = ab2.pesquisar(e);

         }
      }

      return resp;
   }

   public void mostrar(){

      for(int i = 0 ; i < tamanho1 ; i++){
      
         if(tabH1[i] != NULO){
            MyIO.println(tabH1[i]);
         }
      }

      for(int i = 0 ; i < tamanho3 ; i++){
         
         if(tabH3[i] != NULO){
            MyIO.println(tabH3[i]);
         }
      }
      
      lista.mostrar();
      ab1.mostrarCentral();
      ab2.mostrarCentral();
   }
}

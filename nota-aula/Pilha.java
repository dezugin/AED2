/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class Pilha {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}


	/**
	 * Insere elemento na pilha (politica FILO).
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
      tmp = null;
	}


	/**
	 * Remove elemento da pilha (politica FILO).
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		int resp = topo.elemento;
      Celula tmp = topo;
		topo = topo.prox;
      tmp.prox = null;
      tmp = null;
		return resp;
	}


	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for(Celula i = topo; i != null; i = i.prox){
			System.out.print(i.elemento + " ");
      }
		System.out.println("] ");
	}

   public void mostrarRec(){
		System.out.print("[ ");
      mostrarRec(topo);
		System.out.println("] ");
   }


   public void mostrarRec(Celula i){
      if(i != null){
         mostrarRec(i.prox);
			System.out.print(i.elemento + " ");
      }
   }



	public int soma() {
      int soma = 0;
		for(Celula i = topo; i != null; i = i.prox){
			soma = soma + i.elemento;
      }
      return soma;
	}
   
   public int somaRec(){
      return somaRec(topo);
   }

   public int somaRec(Celula i){
      int soma = 0;
      if(i != null){
         soma = i.elemento + somaRec(i.prox);
      }
      return soma;
   }

   public int getMaiorV0(){
      int maior = 0;
      for(Celula i = topo; i != null; i = i.prox){
         if(i.elemento > maior){
            maior = i.elemento;
         }
      }
      return maior;
   }
   
   public int getMaiorV1() throws Exception {
      int maior = 0;
		if (topo == null) {
			throw new Exception("Erro pilha vazia!");
		} else {
         maior = topo.elemento;
         for(Celula i = topo.prox; i != null; i = i.prox){
            if(i.elemento > maior){
               maior = i.elemento;
            }
         }
      }
      return maior;
   }

	public int getMaiorRec(){
      return getMaiorRec(topo);
   } 

	public int getMaiorRec(Celula i){
		int resp = 0;
		
		if(i != null){
			int maiorRec = getMaiorRec(i.prox);
			resp = (i.elemento > maiorRec)? i.elemento: maiorRec; 
		}
		
		return resp;
	}


}

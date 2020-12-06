/**
 * Algoritmo de ordenacao Quicksort
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class Quicksort extends Geracao {

	/**
	 * Construtor.
	 */
   public Quicksort(){
      super();
   }


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
   public Quicksort(int tamanho){
      super(tamanho);
   }


	/**
	 * Algoritmo de ordenacao Quicksort.
	 */
	public static void quicksort() {
      quicksort(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
	 */
    private static void quicksort(int esq, int dir) {
        Sop("CHAMANDO QS: esq("+esq+ ") e dir("+dir+")");
		  mostrar();

        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        Sop("pivo: " + pivo);
        while (i <= j) {
            while (array[i] < pivo) i++;
            Sop("i: " + i);
            while (array[j] > pivo) j--;
            Sop("j: " + j);
            if (i <= j) {
                Sop("trocando elemento nas posicoes i e j...");
                swap(i, j);
                i++;
                j--;
		          mostrar();
                Sop("Incrementando i("+i+") e decrementando j("+j+")");
            }
        }
		  mostrar();
        Sop("fim QS: esq("+esq+ ") e dir("+dir+")");
        Sop("fim QS: i("+i+ ") e j("+j+")");
        if (esq < j)  quicksort(esq, j);
        if (i < dir)  quicksort(i, dir);
    }
 

	public static void main(String[] args) throws Exception {
      Quicksort quicksort = new Quicksort(16);
      quicksort.setarArray(0, 1, 5, 3, 15, 16, 9, ..., 48, 71, 82);
		
		long comeco = now();
		quicksort.quicksort();
		long fim = now();

		//quicksort.mostrar();
		System.out.println("Tempo para ordenar: " + (fim-comeco)/1000.0 + " s.");
	}
}

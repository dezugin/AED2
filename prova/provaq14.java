void inserir(int k){
	for (int i = k+1; i > 1 && array[i] > array[i/2]; i /= 2){
		swap(i, i/2);
	}
}

// O metodo pega a posicao k + 1 no array, que nao esta em forma de heap, e a coloca no heap. Para o fazer roda um loop
// que checa se o pai (posicao i/2) e maior que o filho (i), se for os troca, colocando ordenando o array ate k+1 em forma de heap.

void chamarPilha(int array[], int array2[]){
	mostraRec(array,array2,array.length);
}
void mostraRec(int  array[], int array2[], int i){
	if(i>=0){
		System.out.println(array[i]);
		System.out.println(arra2[i]);
		mostraRec(array,array2,i-1)
	}
}

// A complexidade e O de n. O metodo recursivo printa 2 arrays em forma de pilha intercalados, do elemento do array que entrou por ultimo
// ate o elemento do array que entrou primeiro, e para quando o contador i e executado n vezes, onde n e o numero de elementos no primeiro
// array. O metodo assume que as duas pilhas tem o mesmo numero de elementos.

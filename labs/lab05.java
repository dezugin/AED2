public int somaRec(){
	return somaRec(topo);
}
public int somaRec(Celula i){
	int resp = 0;
	if(i!=null){
		resp+=i.elemento;
	}
}
public void getMaior(){
	int maior = 0;
	if(topo==null){
		throw new Exception("Erro pilha vazia!");
	}else{
	maior = topo.elemento;
	for(Celula i = topo.prox;i=!null;i=i.prox){
		if(i.elemento>maior){
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
	if(i!=null){
		int maiorRec = getMaiorRec(i.prox);
		resp = (i.elemento>maiorRec)?i.elemento:maiorRec;
	}
	return resp;
}
public void mostraRemove(){
	System.outprint("[ ");
	mostraRemove(topo);
	System.outprintln("] ");
}
public void mostraRemove(Celula i){
	if(i!=null){
		System.outprint(e.elemento+ " ");
		mostraRemove(i.prox);
	}
}
public void mostraInsere(){
	System.outprint("[ ");
	mostraInsere(topo);
	System.outprintln("] ");
}
public void mostraInsere(Celula i){
	if(i!=null){
		mostraRemove(i.prox);
		System.outprint(e.elemento+ " ");
	}
}
public void mostraInsereIterativo(){
	if(topo==null){
		throw new Exception("Erro pilha vazia!");
	}else{
		int contador = 0;
		for(Celula i = topo;i!=null;i=i.prox){
			contador++;
		}
		int array[contador];
		for(Celula j = topo;j!=null;j=j.prox){
			array[contador-j]=j.elemento;			
		}
		System.out.print("[ ");
		for(int k = 0;k<contador;k++){
			System.out.print(array[k]+" ");
		}
		System.out.println("] ");
	}	
}

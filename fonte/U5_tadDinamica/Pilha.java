
public class Pilha {
	private Celula topo;

	public Pilha() {
		topo = null;
	}

	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

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

	public void mostrar() {
		System.out.print("[ ");
		for(Celula i = topo; i != null; i = i.prox){
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}
	
	public void Soma(){
		System.out.println(" A soma dos elementos: " + 
			somaRec(topo));
	}

	public int somaRec(Celula i) {
		int resp = 0;
		
		if(i != null)
			resp += i.elemento + somaRec(i.prox);
		
		return resp;
	}

	public int getMaiorRec(Celula i){
		int resp = 0;
		
		if(i != null){
			int maiorRec = getMaiorRec(i.prox);
			resp = (i.elemento > maiorRec)? i.elemento: maiorRec; 
		}
		
		return resp;
	}
 
	public void mostrar(Celula i) {
		if(i != null){
			mostrar(i.prox);
			System.out.print("- " + i.elemento + " -");
		}
	}
}

class Celula 
{  public int elemento;
	public Celula inf, sup, esq, dir;

	public Celula(){
		this(0, null, null, null, null);
	}

	public Celula(int elemento){
		this(elemento, null, null, null, null);
	}

	public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
		this.elemento = elemento;
		this.inf = inf;
		this.sup = sup;
		this.esq = esq;
		this.dir = dir;
	}
}
class batuke{
int linha;
	int coluna;
	Celula inicio,fim;

	public Matriz(){
		this(3,3);
	}
	public void inserirAbaixo(int x){
		while(fim.esq!=null){
			fim = fim.esq;}   
		fim.inf = new Celula(x);
		fim.inf.sup = fim;
		fim = fim.inf;
	}
	public void inserir01(int x){
		      fim.inf = new Celula();
   		   fim = fim.inf;
    		  fim.elemento = x;
	}
	public void inserirDireitaL1(int x){
		fim.dir = new Celula(x);
		fim.dir.esq = fim;
		fim = fim.dir;
	}
	public void inserirDireitaL2(int x){
		fim.dir = new Celula(x);
		fim.dir.esq = fim;
		fim.dir.sup = fim.sup.dir;
		fim.sup.dir = fim.dir.sup;
		fim = fim.dir;
	}
	public void removerNoCabeca(){
		Celula tempow = inicio;
		inicio = inicio.inf;
		tempow.inf = null;
		tempow = null;
	}
	public Matriz(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
		inicio = new Celula();
		fim = inicio;
	}
	public void percorrer(Matriz matrix, int k, int l){
		int resp= 0;
		int m = 0;
		Celula i = matrix.inicio;
		for(int m = 0;m<=k;m++,i=i.inf);
		Celula j = i;
		for(int n = 0;n<=l;n++,j = j.dir);
		
		for(

	}
	public static void main (String [] args){
		String numeroMatrizes = MyIO.readLine();
		String [] pedacos = numeroMatrizes.split(" ");
		int nMatrizes = Integer.parseInt(pedacos[0]);
		int contador = 0;
		int linha = nMatrizes;
		int coluna = nMatrizes;
		Matriz m1 = new Matriz(linha,coluna);
		Matriz m2 = new Matriz(linha,coluna);
		for(int j = 0;j<coluna;j++){
			for(int z = 0;z<linha;z++){
				contador++;					
				if(z==0&&j==0){
					m1.inserir01(contador);
				}else if(j==0){
					m1.inserirDireitaL1(contador);
				}else if(z == 0){
					m1.inserirAbaixo(contador);
				}else{
					m1.inserirDireitaL2(contador);
				}
			}
		}
		m1.removerNoCabeca();
		percorrer(m1, Integer.parseInt(pedacos[1]), Integer.parseInt(pedacos[2]));
}

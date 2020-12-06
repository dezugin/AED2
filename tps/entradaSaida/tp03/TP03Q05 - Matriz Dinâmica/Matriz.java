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
class Matriz {
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
	public int percorrer(Matriz matrix, int k, int l){
		//Celula i;
		//Celula j;
		int resp= 0;
		int m = 0;
		for(Celula i = matrix.inicio;m<=k;m++,i=i.inf){
			int n = 0;
			for(Celula j = i;n<=l;n++,j = j.dir){
				resp = j.elemento;
			}
		}
		return resp;
	}
	public Matriz multiplicar(Matriz outra){
		Matriz resp = null;
		if(this.linha == outra.linha && this.coluna == outra.coluna){
			resp = new Matriz(this.linha, this.coluna);
			for(int t = 0;t<coluna;t++){
				for(int w = 0;w<linha;w++){
							if(w==0&&t==0){
								resp.inserir01(0);
							} else if(t==0){
								resp.inserirDireitaL1(0);
							}else if(w == 0){
								resp.inserirAbaixo(0);
							}else{
								resp.inserirDireitaL2(0);
							}
				}
			}
			resp.removerNoCabeca();
			int l=0;
			for(Celula i = resp.inicio, iA = this.inicio, iB = outra.inicio; i!=null; i = i.inf, iA = iA.inf, iB = iB.inf,l++){
				int k = 0;
				for(Celula j =i, jA = iA, jB = iB; j!= null; j = j.dir, jB=jB.dir,jA = jA.dir,k++){
					int respInt = 0;
					int t = 0;
					j.elemento = 0;
						for(Celula z =this.inicio;z!=null;t++,z=z.dir){
 							j.elemento += percorrer(this,l,t)*percorrer(outra,t,k);
							//System.out.println("l = "+l+" k= "+k+" z= "+z+"Elemento 1  -"+percorrer(this,l,t)+" Elemento 2 = "+percorrer(this,l,t));
						}
/*
					for(k = jA1,l = jB1;k!=null;l=l.dir,k=k.dir){
						for(iA2 = k, jA2 = l;jA2!=null;jA2 = jA2.inf,iA2 = iA2.inf){
							respInt+= 							


						}
						System.out.println("1elemento+"+j.elemento+"a+"+respA.elemento+"b+"+respB.elemento);
						j.elemento+=respA.elemento*respB.elemento;
						System.out.println("2elemento+"+j.elemento+"a+"+respA.elemento+"b+"+respB.elemento);
					}
				*/
				}
			}
		}
			return resp;
	}


	public void mostrar(){
		for(Celula i = this.inicio; i != null; i = i.inf){
			for(Celula j = i; j != null; j = j.dir){
				System.out.print(j.elemento+" ");
			}
			System.out.println("");
		}
	}


	public Matriz soma (Matriz outra){
		Matriz resp = null;
		if(this.linha == outra.linha && this.coluna == outra.coluna){
			resp = new Matriz(this.linha, this.coluna);
			for(int t = 0;t<coluna;t++){
				for(int w = 0;w<linha;w++){
							if(w==0&&t==0){
								resp.inserir01(0);
							} else if(t==0){
								resp.inserirDireitaL1(0);
							}else if(w == 0){
								resp.inserirAbaixo(0);
							}else{
								resp.inserirDireitaL2(0);
							}
				}
			}
			resp.removerNoCabeca();
					for(Celula i = resp.inicio, iA = this.inicio, iB = outra.inicio; i != null; i = i.inf, iA = iA.inf, iB = iB.inf){
						for(Celula j = i, jA = iA, jB = iB; j != null; j = j.dir, jA = jA.dir, jB = jB.dir){
							j.elemento = jA.elemento + jB.elemento;
						}
					}
		}
		return resp;
	}

	public void getDiagonalPrincipal(){
		if(this.linha == this.coluna){
			Celula i;
			Celula j;
			int contador1;
			int contador2;
			for(i = inicio,contador1=0; i != null;contador1++, i = i.inf){
				for(j =i,contador2=0;j!=null;j=j.dir,contador2++){
					if(contador1==contador2){
					System.out.print(j.elemento+" ");
					}
				}
				contador2=0;
			}
			System.out.println("");
		}
	}
	public void getDiagonalSecundaria(){
		if(this.linha == this.coluna){
			Celula i;
			Celula j;
			int contador1;
			int contador2;
			for(i = inicio,contador1=0;i!=null;i=i.inf, contador1++){
				for(j=i,contador2=0;j!=null;contador2++, j = j.dir){
					if((contador1+contador2)==(linha-1)){
					System.out.print(j.elemento+" ");
					}
				}
				contador2 = 0;
			}
			System.out.println("");
		}
	}

	public static void main (String [] args){
		String numeroMatrizes = MyIO.readLine();
		int nMatrizes = Integer.parseInt(numeroMatrizes);
		for(int i = 0;i<nMatrizes;i++){
			String sLinha = MyIO.readLine();
			String sColuna = MyIO.readLine();
			int linha = Integer.parseInt(sLinha);
			int coluna = Integer.parseInt(sColuna);
			Matriz m1 = new Matriz(linha,coluna);
			Matriz m2 = new Matriz(linha,coluna);
			for(int j = 0;j<coluna;j++){
				String valoresDaLinha = MyIO.readLine();
				String [] valorCortado = valoresDaLinha.split(" ");
				for(int z = 0;z<linha;z++){
					int valorInserido = Integer.parseInt(valorCortado[z]);
					if(z==0&&j==0){
						m1.inserir01(valorInserido);
					}else if(j==0){
						m1.inserirDireitaL1(valorInserido);
					}else if(z == 0){
						m1.inserirAbaixo(valorInserido);
					}else{
						m1.inserirDireitaL2(valorInserido);
					}

				}
			}
			m1.removerNoCabeca();
			String s2Linha = MyIO.readLine();
			String s2Coluna = MyIO.readLine();
			int linha2 = Integer.parseInt(s2Linha);
			int coluna2 = Integer.parseInt(s2Coluna);
			for(int t = 0;t<coluna2;t++){
				String valoresDaLinha2 = MyIO.readLine();
				String [] valorCortado2 = valoresDaLinha2.split(" ");
				for(int w = 0;w<linha2;w++){
					int valorInserido2 = Integer.parseInt(valorCortado2[w]);
							if(w==0&&t==0){
								m2.inserir01(valorInserido2);
							} else if(t==0){
								m2.inserirDireitaL1(valorInserido2);
							}else if(w == 0){
								m2.inserirAbaixo(valorInserido2);
							}else{
								m2.inserirDireitaL2(valorInserido2);
							}
				}
			}
			m2.removerNoCabeca();
			m1.getDiagonalPrincipal();
			m1.getDiagonalSecundaria();
			Matriz m3 = new Matriz(linha,coluna);
			m3 = m1.soma(m2);
			m3.mostrar();
			Matriz m4 = new Matriz(linha,coluna);
			m4 = m1.multiplicar(m2);
			m4.mostrar();
		}

	}
}

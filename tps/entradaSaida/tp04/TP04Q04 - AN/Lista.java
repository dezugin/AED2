import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
class Personagem {
	private String nome, corDoCabelo, codDaPele, corDosOlhos, anoNascimento, genero, homeworld;
	private int altura;
	private double peso;
	public Personagem() {
		nome = corDoCabelo = codDaPele = corDosOlhos = anoNascimento = genero = homeworld = "";
		altura = 0;
		peso = 0;
	}
	//TODO: gets and sets
	public String getNome(){
		return nome;
	}
	public String getCorDoCabelo(){
		return corDoCabelo;
	}
	public String getCodDaPele(){
		return codDaPele;
	}
	public double getPeso(){
		return peso;
	}
	public String getCorDosOlhos(){
		return corDosOlhos;
	}
	public String getAnoNascimento(){
		return anoNascimento;
	}
	public String getGenero(){
		return genero;
	}
	public int getAltura(){
		return altura;
	}
	public String getHomeworld(){
		return homeworld;
	}
	public void setNome(){
		this.nome = nome;
	}
	public void setCorDoCabelo(){
		this.corDoCabelo = corDoCabelo;
	}
	public void setCodDaPele(){
		this.codDaPele = codDaPele;
	}
	public void setCorDosOlhos(){
		this.corDosOlhos = corDosOlhos;
	}
	public void setAnoNascimento(){
		this.anoNascimento = anoNascimento;
	}
	public void setGenero(){
		this.genero = genero;
	}
	public void setAltura(){
		this.altura = altura;
	}
	public void setPeso(){
		this.peso = peso;
	}
	public void setHomeworld(){
		this.homeworld = homeworld;
	}
	public void imprimir() {
		String printaPeso = (peso % 1==0) ? Integer.toString((int)peso) : Double.toString(peso);
		System.out.println(" ## "+ nome + " ## " +  altura + " ## " + printaPeso +" ## "+ corDoCabelo + " ## " + codDaPele + " ## " + corDosOlhos+ " ## "+ anoNascimento+ " ## "+ genero+ " ## "+ homeworld+ " ## " );
	}
	//TODO: Clone
	public Personagem clone (){
		Personagem resp = new Personagem();
		resp.nome = this.nome;
		resp.altura = this.altura;
		resp.peso = this.peso;
		resp.corDoCabelo = this.corDoCabelo;
		resp.codDaPele = this.codDaPele;
		resp.corDosOlhos = this.corDosOlhos;
		resp.anoNascimento = this.anoNascimento;
		resp.genero = this.genero;
		resp.homeworld = this.homeworld;
		return resp;
	}
	public String removeTags(String linha){
		String resp = "";
		for(int i = 0;i<linha.length();i++){
			if(linha.charAt(i)!=','){
				resp+=linha.charAt(i);
			}
		}
	return resp;
	}
	public void ler(String nomeArquivo){
		try{		
			String linha = "";
			String linha1 = "";
			FileReader file = new FileReader(nomeArquivo);
			BufferedReader buffer = new BufferedReader(file);
			linha1 = buffer.readLine();
			//ler NOME
			linha = linha1.substring(linha1.indexOf("name")+8);
			linha = linha.substring(0,linha.indexOf("'"));
			this.nome = linha;
			// ler ALTURA*
			linha = linha1.substring(linha1.indexOf("height")+10);
			linha = linha.substring(0,linha.indexOf("'"));	
			if(linha.contains("nkn")){
				this.altura = 0;
			}else{
				this.altura = Integer.parseInt(linha);
			}
			//ler PESO*
			linha = linha1.substring(linha1.indexOf("mass")+8);
			linha = linha.substring(0,linha.indexOf("'"));
			if(linha.contains(",")){
				linha = removeTags(linha);		
			}
			if(linha.contains("nkn")){
				this.peso = 0;
			}else{
				this.peso = Double.parseDouble(linha);
			}
			// ler COR DO CABELO
			linha = linha1.substring(linha1.indexOf("hair_color")+14);
			linha = linha.substring(0,linha.indexOf("'"));
			this.corDoCabelo = linha;
			// ler COR DA PELE
			linha = linha1.substring(linha1.indexOf("skin_color")+14);
			linha = linha.substring(0,linha.indexOf("'"));
			this.codDaPele = linha;
			// ler COR DOS OLHOS
			linha = linha1.substring(linha1.indexOf("eye_color")+13);
			linha = linha.substring(0,linha.indexOf("'"));
			this.corDosOlhos = linha;
			// ler ANO DE NASCIMENTO
			linha = linha1.substring(linha1.indexOf("birth_year")+14);
			linha = linha.substring(0,linha.indexOf("'"));
			this.anoNascimento = linha;
			// ler GENERO
			linha = linha1.substring(linha1.indexOf("gender")+10);
			linha = linha.substring(0,linha.indexOf("'"));
			this.genero = linha;
			// ler MUNDO NATAL
			linha = linha1.substring(linha1.indexOf("homeworld")+13);
			linha = linha.substring(0,linha.indexOf("'"));
			this.homeworld = linha;
			buffer.close();
		}catch(IOException g){
		}
	}
}
class No{
	public Personagem elemento = new Personagem();
	public No esq,dir;
	public boolean cor;
	public No(Personagem elemento){
		this(elemento,null,null,false);
	}
	public No(Personagem elemento, boolean cor){
		this(elemento,null,null,cor);
	}
	public No(Personagem elemento, No esq, No dir,boolean cor){
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.cor = cor;
	}
}
public class Lista{
	int comparacoes;
	private No raiz;
	public Lista(){
		raiz = null;		
	}
	public void inserir(Personagem x) throws Exception{
		if(raiz==null){
			raiz = new No(x,false);
		}else if(raiz.esq == null && raiz.dir == null){
			if(raiz.elemento.getNome().compareTo(x.getNome())>0){
				raiz.esq = new No(x, true);
			}else{
				raiz.dir = new No(x, true);
			}
		} else if(raiz.esq == null){
			if(raiz.elemento.getNome().compareTo(x.getNome())>0){
				raiz.esq = new No(x);
			}else if(raiz.dir.elemento.getNome().compareTo(x.getNome())>0){
				raiz.esq = new No(raiz.elemento);
				raiz.elemento = x;
			}else{
				raiz.esq = new No(raiz.elemento);
				raiz.elemento = raiz.dir.elemento;
				raiz.dir.elemento = x;
			}
			raiz.esq.cor = raiz.dir.cor = false;
		}else if(raiz.dir == null){
			if(raiz.elemento.getNome().compareTo(x.getNome())<0){
				raiz.dir = new No(x);
			}else if(raiz.esq.elemento.getNome().compareTo(x.getNome())<0){
				raiz.dir = new No(raiz.elemento);
				raiz.elemento = x;
			}else{
				raiz.dir = new No(raiz.elemento);
				raiz.elemento = raiz.esq.elemento;
				raiz.esq.elemento = x;
			}
			raiz.esq.cor = raiz.dir.cor = false;
		}else{
			inserir(x,null,null,null,raiz);
		}
		raiz.cor = false;
	}
	private void balancear(No bisavo, No avo, No pai, No i){
		if(pai.cor == true){
			if(pai.elemento.getNome().compareTo(avo.elemento.getNome())>0){
				if(i.elemento.getNome().compareTo(pai.elemento.getNome())>0){
					avo = rotacaoEsq(avo);
				}else{
					avo = rotacaoDirEsq(avo);
				}
			}else{
				if(i.elemento.getNome().compareTo(pai.elemento.getNome())<0){
					avo = rotacaoDir(avo);
				}else{
					avo = rotacaoEsqDir(avo);
				}
			}
			if(bisavo == null){
				raiz = avo;
			}else{
				if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome())<0){
					bisavo.esq = avo;
				}else{
					bisavo.dir = avo;
				}
			}
			avo.cor = false;
			avo.esq.cor = avo.dir.cor = true;
		}
	}
	private void inserir(Personagem elemento, No bisavo, No avo, No pai, No i)throws Exception{
		if(i==null){
			if(elemento.getNome().compareTo(pai.elemento.getNome())<0){
				i = pai.esq = new No(elemento, true);
			}else{
				i = pai.dir = new No(elemento, true);
			}
			if(pai.cor != false){
				balancear(bisavo,avo,pai,i);
			}
		}else{
			if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
				i.cor = true;
				i.esq.cor = i.dir.cor = false;
				if(i==raiz){
					i.cor = false;
				}else if(pai.cor == true){
					balancear(bisavo,avo,pai,i);
				}
			}
			if(elemento.getNome().compareTo(i.elemento.getNome())<0){
				inserir(elemento,avo,pai,i,i.esq);
			}else if(elemento.getNome().compareTo(i.elemento.getNome())>0){
				inserir(elemento,avo,pai,i,i.dir);
			}else{
				throw new Exception("erro");
			}
		}
	}
	private No rotacaoDir(No no){
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;
		noEsq.dir = no;
		no.esq = noEsqDir;
		return noEsq;
	}
	private No rotacaoEsq(No no){
		No noDir = no.dir;
		No noDirEsq = noDir.esq;
		noDir.esq = no;
		no.dir = noDirEsq;
		return noDir;
	}
	private No rotacaoDirEsq(No no){
		no.dir = rotacaoDir(no.dir);
		return rotacaoEsq(no);
	}
	private No rotacaoEsqDir(No no){
		no.esq = rotacaoEsq(no.esq);
		return rotacaoDir(no);
	}
	public int getComparacoes(){
		return comparacoes;
	}
	public boolean pesquisar(String x){
		return pesquisar(x,raiz);
	}
	private boolean pesquisar(String x, No i){
		boolean resp = false;
		if(i==null){
			resp = false;
		}else if(x.compareTo(i.elemento.getNome())==0){
			resp = true;
			return resp;
		}else if(x.compareTo(i.elemento.getNome())<0){
			System.out.print("esq ");			
			resp = pesquisar(x,i.esq);
		}else{
			System.out.print("dir ");
			resp = pesquisar(x,i.dir);
		}
		return resp;
	}
	public void mostrar(){
		mostrar2(raiz);
	}
	public void mostrar2(No i){
		if(i!=null){
			System.out.println(i.elemento.getNome());
			mostrar2(i.esq);
			mostrar2(i.dir);
		}
	}
public static String converterEncoding(String str) throws Exception {

        byte[] isoBytes = str.getBytes("ISO-8859-1");
        return new String(isoBytes, "UTF-8");
    }
	public static void main (String [] args){
		MyIO.setCharset("UTF-8");	
		long startTime = System.nanoTime();
		String[] entrada = new String[1000];
		int numEntrada = 0;
		int numEntrada2 = 0;
		String [] entrada2 = new String[1000];
		try{
			//Leitura da entrada padrao
			do {
				//String buffer = ISO88591toUTF8(MyIO.readLine());
				//entrada[numEntrada] = buffer;
				entrada[numEntrada] = MyIO.readLine();
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
			Lista lista = new Lista();
			//Leitura da entrada padrao
			do {
				//String buffer = ISO88591toUTF8(MyIO.readLine());
				entrada2[numEntrada2] = MyIO.readLine();
			} while (!entrada2[numEntrada2++].contains("FIM"));
			numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM
			//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				Personagem personagem = new Personagem();
				personagem.ler(entrada[i]);
				//System.out.println(entrada[i]);
				lista.inserir(personagem);
				//System.out.println(personagem.getNome());
			}
			/*String num = MyIO.readLine();
			int numero = Integer.parseInt(num);
			for ( int j = 0;j<numero;j++){
				entrada2[j]= MyIO.readLine();
			}

			for(int z = 0;z<numero;z++){
				lista.tratar(entrada2[z]);
			}
			//lista.mostrar();
			lista.mostrar(raiz);
			*/
			//lista.mostrar();
			for(int z = 0;z<numEntrada2;z++){
				System.out.print(entrada2[z]+" raiz ");
				boolean temp = lista.pesquisar(entrada2[z]);				
				if(temp==true){
					System.out.println("SIM");
				}
				if(temp==false){
					int c = 195;
					MyIO.setCharset("ISO-8859-1");
					//MyIO.println(converterEncoding("NÃO"));
					MyIO.println("NÃO");
				}
			}		//	lista.quick();
			//lista.removerInicio();
			//lista.mostrarParcial(10);
			//lista.mostrar();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_alvinegra.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			//hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

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
	public No(Personagem elemento){
		this(elemento,null,null);
	}
	public No(Personagem elemento, No esq, No dir){
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}
public class Lista{
	int comparacoes;
	private No raiz;
	public Lista(){
		raiz = null;		
	}
	public void inserir(Personagem x) throws Exception {
		raiz = inserir(x,raiz);
	}
	private No inserir(Personagem x, No i) throws Exception{
	if(i==null){
	i = new No(x);
	} else if (x.getNome().compareTo(i.elemento.getNome())<0){
		i.esq = inserir(x,i.esq);
	} else if (x.getNome().compareTo(i.elemento.getNome())>0){
		i.dir = inserir(x,i.dir);
	} else { 
		throw new Exception ("Erro!");
	}
	return i;
}
	public int getComparacoes(){
		return comparacoes;
	}
	public boolean pesquisar(String x){
		return pesquisar(x,raiz);
	}
	private boolean pesquisar(String x, No2 i){
		boolean resp = false;
		resp = pesquisar2(x);
		if(resp==false){
			System.out.print("esq ");
			resp = pesquisar(x, i.esq);
		}
		if(resp == false){
			System.out.print("dir ");
			resp = pesquisar(x,i.dir);
		}
		return resp;
	}
	public boolean pesquisar2(String x){
		return pesquisar(x,raiz);
	}
	private boolean pesquisar2(String x, No2 i){
		boolean resp;
		//System.out.print(x+"+"+i.elemento.getNome()+ " ");
		if(i==null){
			resp = false;
		//System.out.print("1");
		}else if(x.compareTo(i.elemento.getNome())==0){
			resp = true;
		//System.out.print("2");
		}else if(x.compareTo(i.elemento.getNome())<0){
			System.out.print("esq ");			
			resp = pesquisar(x,i.esq);
		//System.out.print("3");
		}else{
			System.out.print("dir ");
			resp = pesquisar(x,i.dir);
		//System.out.print("4");
		}
		return resp;
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
			*/for(int z = 0;z<numEntrada2;z++){
				System.out.print(entrada2[z]+" raiz ");
				boolean temp = lista.pesquisar(entrada2[z]);				
				if(temp==true){
					System.out.println("SIM");
				}
				if(temp==false){
					int c = 195;
					MyIO.setCharset("ISO-8859-1");
					MyIO.println(converterEncoding("NÃO"));
				}
			}		//	lista.quick();
			//lista.removerInicio();
			//lista.mostrarParcial(10);
			//lista.mostrar();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_arvoreBinaria.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			//hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

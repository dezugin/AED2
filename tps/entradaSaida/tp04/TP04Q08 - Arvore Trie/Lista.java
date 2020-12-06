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
	public char elemento;
	public int tamanho = 255;
	public No[] prox;
	public boolean folha;
	public No(){
		this(' ');
	}
	public No(char elemento){
		this.elemento = elemento;
		prox = new No[tamanho];
		for(int i = 0;i<tamanho;i++)prox[i]=null;
		folha = false;
	}
	public static int hash (char x){
		return(int)x;
	}
}
public class Lista{
	int comparacoes;
	private No raiz;
	public Lista(){
		raiz = new No();		
	}
	public void inserir(String x) throws Exception {
		inserir(x,raiz,0);
	}
	public void inserir(String s, No no, int i) throws Exception{
		if(no.prox[s.charAt(i)]==null){
			no.prox[s.charAt(i)]=new No(s.charAt(i));
			if(i==s.length()-1){
				no.prox[s.charAt(i)].folha = true;
			}else{
				inserir(s,no.prox[s.charAt(i)],i+1);
			}
		} else if (no.prox[s.charAt(i)].folha == false && i< s.length()-1){
			inserir(s,no.prox[s.charAt(i)],i+1);
		} else { 
			throw new Exception ("Erro!");
		}
	}
	public int getComparacoes(){
		return comparacoes;
	}
	public boolean pesquisar(String x)throws Exception{
		return pesquisar(x,raiz,0);
	}
	private boolean pesquisar(String s, No no, int i)throws Exception{
		boolean resp;
		if(no.prox[s.charAt(i)]==null){
			resp = false;
		}else if(i==s.length()-1){
			resp = (no.prox[s.charAt(i)].folha == true);
		}else if(i<s.length()-1){
			resp = pesquisar(s,no.prox[s.charAt(i)],i+1);
		}else{ throw new Exception(" Erro na pesquisa");
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
			do {
				//String buffer = ISO88591toUTF8(MyIO.readLine());
				//entrada[numEntrada] = buffer;
				entrada[numEntrada] = MyIO.readLine();
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;
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
				lista.inserir(personagem.getNome());
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
				System.out.print(entrada2[z]);
				boolean temp = lista.pesquisar(entrada2[z]);				
				if(temp==true){
					System.out.println(" SIM");
				}
				if(temp==false){
					int c = 195;
					MyIO.setCharset("ISO-8859-1");
				//	MyIO.println(converterEncoding(" NÃO"));
					MyIO.println(" NÃO");
				}
			}		//	lista.quick();
			//lista.removerInicio();
			//lista.mostrarParcial(10);
			//lista.mostrar();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_arvoreTrie.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			//hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

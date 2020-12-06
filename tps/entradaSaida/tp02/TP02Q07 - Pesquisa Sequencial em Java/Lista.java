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
public class Lista{
	int n;
	int comparacoes;
	Personagem []  array;
	Lista () { this(100);}
	Lista (int tamanho) {
		this.array = new Personagem[tamanho];
		n = 0;
		comparacoes = 0;
	}
	void inserirInicio(Personagem x)throws Exception{
		if(n>= array.length){
			throw new Exception("Erro");
		}
		for(int i = n;i>0;i--){
			array[i] = array[i-1];
		}
		array[0] = x;
		n++;
	}
	void inserirFim(Personagem x)throws Exception{
		if(n>= array.length){
			throw new Exception("Erro");
		}
		array[n] = x;
		n++;
	}
	void remover(int pos)throws Exception{
		if(n==0||pos<0||pos>=n){
			throw new Exception("Erro");
		}
		String resp = array[pos].getNome();
		System.out.println("(R) "+resp);
		n--;
		for(int i = pos; i<n;i++){
			array[i]=array[i+1];
		}
	}
	void removerInicio()throws Exception{
		if(n==0){
			throw new Exception("Erro");
		}
		String resp = array[0].getNome();
		System.out.println("(R) "+resp);
		n--;
		for(int i = 0; i<n;i++){
			array[i]=array[i+1];
		}
	}
	void removerFim()throws Exception{
		if(n==0){
			throw new Exception("Erro");
		}
		String resp = array[--n].getNome();
		System.out.println("(R) "+resp);
	}
	void inserir(Personagem x,int pos)throws Exception{
		if(n>=array.length||pos<0||pos>n){
			throw new Exception("Erro!");
		}
		for(int i = n; i>pos;i--){
			array[i] = array[i-1];
		}
		array[pos]=x;
		n++;
	}
	void mostrar(){
		for(int i = 0;i<n;i++){
			System.out.print("["+(i)+"] ");
			array[i].imprimir();
		}
	}
	void tratar(String s)throws Exception{
		String comando = "";
		int pos;
		Personagem resp = new Personagem();
		if(n>=array.length){
			throw new Exception("Erro");
		}
		/*if(s.contains("II")){
			comando = s.substring(s.indexOf(" ")+1);
			resp.ler(comando);
			inserirInicio(resp);
		}*/
		if(s.contains("IF")){
			comando= s.substring(s.indexOf(" ")+1);
			resp.ler(comando);
			inserirFim(resp);
		}/*
		if(s.contains("R*")){
			comando = s.substring(s.indexOf(" ")+1);
			pos = Integer.parseInt(comando);
			remover(pos);
		}
		if(s.contains("RI")){
			removerInicio();
		}*/
		if(s.contains("RF")){
			removerFim();
		}/*
		if(s.contains("I*")){
			String instruct = s.substring(s.indexOf(" ")+1);
			comando = instruct.substring(s.indexOf(" ")+1);
			pos = Integer.parseInt(instruct.substring(0,instruct.indexOf(" ")));
			resp.ler(comando);
			inserir(resp, pos);
		}*/
	}
	int pesquisaSequencial (String s){
		int result = 2;
		for(int i = 0;i<n;i++){
			String nomey = array[i].getNome();
			if(nomey.contains(s)){
				result = 1;
				comparacoes +=1;
				break;
			}else{
				result = 0;
				comparacoes +=1;
			}
		}
		return result;
	}
	public int getComparacoes (){
		return comparacoes;
	}
	public static String ISO88591toUTF8(String strISO) throws Exception {
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
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
				String buffer = ISO88591toUTF8(MyIO.readLine());
				entrada[numEntrada] = buffer;
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
			Lista lista = new Lista();
			//Leitura da entrada padrao
			do {
				String buffer = ISO88591toUTF8(MyIO.readLine());
				entrada2[numEntrada2] = buffer;
			} while (!entrada2[numEntrada2++].contains("FIM"));
			numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM
			//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				Personagem personagem = new Personagem();
				personagem.ler(entrada[i]);
				lista.inserirFim(personagem);
			}
			for(int z = 0;z<numEntrada2;z++){
				int result = lista.pesquisaSequencial(entrada2[z]);
				if(result == 1){
					System.out.println("SIM");
				}
				if(result == 0){
					System.out.println("NAO");
				}
			}
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_sequencial.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

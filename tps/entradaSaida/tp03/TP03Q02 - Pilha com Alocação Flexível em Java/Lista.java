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
class Celula{
	public Personagem elemento = new Personagem();
	public Celula prox;
	public Celula(){
		this.elemento = new Personagem();
		this.prox = null;
	}
	public Celula(Personagem x){
		this.elemento = x;
		this.prox = null;
	}
}
public class Lista{
	int n;
	int comparacoes;
	private Celula primeiro, ultimo;
	public Lista(){
		primeiro = new Celula();
		ultimo = primeiro;
	}
	public void inserirFim(Personagem x){
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}
	public void inserirInicio(Personagem x){
		Celula tmp = new Celula(x);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}
		tmp = null;
	}
	public void remover(int pos)throws Exception{
	Personagem elemento = new Personagem();
	int tamanho = tamanho();
	if(primeiro == ultimo || pos<0 || pos >= tamanho){throw new Exception("Erro!");
	}else if(pos==0){  removerInicio();
	}else if(pos == tamanho -1){ removerFim();
	}else{
		Celula i = primeiro;
		for(int j = 0; j<pos;j++, i = i.prox);
		Celula tmp = i.prox;
		elemento = tmp.elemento;
		i.prox = tmp.prox;
		tmp.prox = null;
		i = tmp = null;
	}
	System.out.println("(R) "+elemento.getNome());
	}
	public int tamanho(){
		int contador=0;
		for(Celula i = primeiro;i!=null;i=i.prox){
			contador+=1;
		}
		return contador;
	}
	public void removerInicio()throws Exception {
	if(primeiro == null){throw new Exception("Erro!");
	}
	Personagem elemento = primeiro.prox.elemento;
	Celula tmp = primeiro.prox;
	primeiro.prox = primeiro.prox.prox;
	tmp.prox = null;
	tmp = null;
	System.out.println("(R) "+elemento.getNome());
	}
	public void removerFim() throws Exception{
		if(primeiro == ultimo){
			throw new Exception("Erro!");
		}
		Celula i;
		for(i=primeiro;i.prox!=ultimo;i=i.prox);
		Personagem elemento = ultimo.elemento;
		ultimo = i;
		i = ultimo.prox = null;
		System.out.println("(R) "+elemento.getNome());
	}
	public void inserir(Personagem x, int pos)throws Exception{
	int tamanho = tamanho();
	if(pos<0||pos>tamanho){throw new Exception("Erro!");
	}else if (pos == 0){inserirInicio(x);
	}else if(pos == tamanho){inserirFim(x);
	}else {
		Celula i = primeiro;
		for(int j = 0;j<pos;j++,i=i.prox);
		Celula tmp = new Celula(x);
		tmp.prox = i.prox;
		i.prox = tmp;
		tmp = i = null;
	}

	}
	public void mostrar(){
		int contador = 0;
		for(Celula i = primeiro.prox; i!= null;i = i.prox){
			System.out.print("["+contador+"] ");
			i.elemento.imprimir();
			contador+=1;
		}
	}
	public int getComparacoes(){
		return comparacoes;
	}
	void tratar(String s)throws Exception{
		String comando = "";
		int pos;
		Personagem resp = new Personagem();
		/*if(s.contains("II")){
			comando = s.substring(s.indexOf(" ")+1);
			resp.ler(comando);
			inserirInicio(resp);
		}*/
		if(s.contains("I ")){
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
		if(s.charAt(0)==('R')){
			removerFim();
		}
		/*if(s.contains("I*")){
			String instruct = s.substring(s.indexOf(" ")+1);
			comando = instruct.substring(s.indexOf(" ")+1);
			pos = Integer.parseInt(instruct.substring(0,instruct.indexOf(" ")));
			resp.ler(comando);
			inserir(resp, pos);
			}*/
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
		/*	//Leitura da entrada padrao
			do {
				String buffer = ISO88591toUTF8(MyIO.readLine());
				entrada2[numEntrada2] = buffer;
			} while (!entrada2[numEntrada2++].contains("FIM"));
			numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM
		*/	//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				Personagem personagem = new Personagem();
				personagem.ler(entrada[i]);
				//System.out.println(entrada[i]);
				lista.inserirFim(personagem);
				//System.out.println(personagem.getNome());
			}
			String num = MyIO.readLine();
			int numero = Integer.parseInt(num);
			for ( int j = 0;j<numero;j++){
				entrada2[j]= MyIO.readLine();
			}

			for(int z = 0;z<numero;z++){
				lista.tratar(entrada2[z]);
			}
			lista.mostrar();
			/*for(int z = 0;z<numEntrada2;z++){
				int result = lista.pesquisaSequencial(entrada2[z]);
				if(result == 1){
					System.out.println("SIM");
				}
				if(result == 0){
					System.out.println("NAO");
				}
			}*/
			//lista.quick();
			//lista.removerInicio();
			//lista.mostrarParcial(10);
			//lista.mostrar();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_quicksortparcial.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			//hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

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
	public int getPesoInt(){
		return (int) peso;
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
		//System.out.println("(R) "+resp);
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
			//System.out.print("["+(i)+"] ");
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
	void selecao(){
		for(int i = 0;i<n-1;i++){
			int menor = i;
			for(int j = (i+1);j<n;j++){
				if(array[menor].getNome().compareTo(array[j].getNome())>0){
					menor = j;
					comparacoes +=1;
				}else{
					comparacoes+=1;
				}
			}
			swap(menor,i);
		}
	}
	void swap (int i1, int i2){
		Personagem temp = new Personagem();
		temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	void insercao(){
		for(int i = 1; i< n;i++){
			int j = i -1;
			Personagem tmpPersonagem = new Personagem();
			tmpPersonagem = array[i];
			while((j>=0)&&(array[j].getAnoNascimento().compareTo(tmpPersonagem.getAnoNascimento())>0)){
				array[j+1]=array[j];
				j--;
				comparacoes+=2;
			}
			array[j+1]=tmpPersonagem;
			j = i-1;
			while((j>=0)){
				if((array[j].getNome().compareTo(array[j+1].getNome())>0)&&(array[j].getAnoNascimento()==array[j+1].getAnoNascimento())){
					Personagem personagemSwap = new Personagem();
					personagemSwap = array[j+1];
					array[j+1] = array[j];
					array[j] = personagemSwap;
					comparacoes+=2;
					j--;
				}else{
					j--;
				}
				}
			}			
		}
		void heapsort(){
			//construcao do heap
			for(int tam = 2; tam<n;tam++){
				constroi(tam);
				comparacoes+=1;
			}
			//ordenacao do heap
			int tam = n-1;
			while(tam>1){
				//printar tam
				swap(1,tam--);
				reconstroi(tam);
				comparacoes+=1;
			}
		}
		void constroi(int tam){
			for(int i = tam; i>1 && array[i].getAltura() > array[i/2].getAltura() || i>1 && (array[i].getAltura()==array[i/2].getAltura() && array[i].getNome().compareTo(array[i/2].getNome())>0 ); i/=2){
				swap(i,i/2);
				comparacoes+=3;
			}
		}
		void reconstroi(int tam){
			int i = 1, filho;
			while(i<=tam/2){
				comparacoes+=1;
				filho = getMaiorFilho(i,tam);
				if(array[i].getAltura()<array[filho].getAltura()){
					comparacoes+=1;
					swap(i,filho);
					i = filho;
				}else if(array[i].getAltura()==array[filho].getAltura()&&array[i].getNome().compareTo(array[filho].getNome())<0){
					comparacoes+=2;
					swap(i,filho);
					i = filho;
				}
				else{
					comparacoes+=1;
					i = tam;
				}
			}
		}
		int getMaiorFilho(int i, int tam){
			int filho;
			if(2*i==tam || array[2*i].getAltura()>array[2*i+1].getAltura()){
				filho = 2*i;
				if(2*1==tam){
					comparacoes+=1;
				}else{
				comparacoes+=2;
				}
			}else if(array[2*i].getAltura()==array[2*i+1].getAltura()&&array[2*1].getNome().compareTo(array[2*i+1].getNome())>0){
					filho = 2*i;
					comparacoes+=2;
			}else {
				comparacoes+=2;
				filho = 2*i+1;
			}
			return filho;
		}
	public void setLista(Personagem[] personagem){
			this.array = personagem;
		}
	void countingsort(){
		//contar ocorrencias;
		int count[] = new int[this.getMaior()+1];
		Personagem [] ordenado = new Personagem[this.n];
		// inicializar c 0 elementos do array de contagem
		for(int i = 0; i<count.length;count[i] = 0,comparacoes++,i++);
		// alocar a count[i] o numero de elementos iguais a i
		for (int i = 0; i<this.n;count[array[i].getPesoInt()]++,comparacoes++,i++);
		//mudar count[i] para que contenha a posicao desse elemento no array a ser ordenado
		for(int i = 1; i<count.length;count[i]+= count[i-1],comparacoes++,i++);
		//ordenando
		for(int i = n-1;i>=0;ordenado[count[this.array[i].getPesoInt()]-1] = this.array[i],comparacoes++, count[this.array[i].getPesoInt()]--,i--);
		this.setLista(ordenado);
		}
	int getMaior(){
			int maior = 0;
			for(int i =0;i<n;i++){
				if(array[i].getPesoInt()>maior){
					maior = array[i].getPesoInt();
				}
			}
			return maior;
		}
	public static void main (String [] args){
		MyIO.setCharset("UTF-8");	
		long startTime = System.nanoTime();
		String[] entrada = new String[1000];
		int numEntrada = 0;
		//int numEntrada2 = 0;
		//String [] entrada2 = new String[1000];
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
			}
			/*for(int z = 0;z<numEntrada2;z++){
				int result = lista.pesquisaSequencial(entrada2[z]);
				if(result == 1){
					System.out.println("SIM");
				}
				if(result == 0){
					System.out.println("NAO");
				}
			}*/
			lista.countingsort();
			//lista.removerInicio();
			lista.mostrar();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_countingsort.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}

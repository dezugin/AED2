import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
public class Personagem {

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
	public static String ISO88591toUTF8(String strISO) throws Exception {
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");
	}
	public static void main (String [] args){
		//MyIO.setCharset("UTF-8");	

		try{
			String[] entrada = new String[1000];
			int numEntrada = 0;		
			//Leitura da entrada padrao
			do {
				String buffer = ISO88591toUTF8(MyIO.readLine());
				entrada[numEntrada] = buffer;
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
			//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				//System.out.println("Time"+i+" "+entrada[i]);
				Personagem personagem = new Personagem();
				personagem.ler(entrada[i]);
				personagem.imprimir();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}


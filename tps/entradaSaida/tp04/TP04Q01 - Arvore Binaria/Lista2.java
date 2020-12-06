import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
class Time {
	private String fullName, apelido, estadio, treinador, liga, pagina;
	private int capacidade, fundacaoDia, fundacaoMes, fundacaoAno;
	private long paginaTam;
	public Time() {
		fullName = apelido = estadio = treinador = liga = pagina = "";
		paginaTam = capacidade = fundacaoDia = fundacaoMes = fundacaoAno = 0;
	}
	public String getFullName(){
		return fullName;
	}
	public String getEstadio(){
		return estadio;
	}
	public String getPagina(){
		return pagina;
	}
	public long getPaginaTam(){
		return paginaTam;
	}
	public String getApelido(){
		return apelido;
	}
	public String getTreinador(){
		return treinador;
	}
	public String getLiga(){
		return liga;
	}
	public int getCapacidade(){
		return capacidade;
	}
	public int getFundacaoDia(){
		return fundacaoDia;
	}
	public int getFundacaoMes(){
		return fundacaoMes;
	}
	public int getFundacaoAno(){
		return fundacaoAno;
	}
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	public void setEstadio(String estadio){
		this.estadio = estadio;
	}
	public void setPagina(String pagina){
		this.pagina = pagina;
	}
	public void setPaginaTam(long paginaTam){
		this.paginaTam = paginaTam;
	}
	public void setApelido(String apelido){
		this.apelido = apelido;
	}
	public void setTreinador(String treinador){
		this.treinador = treinador;
	}
	public void setLiga(String liga){
		this.liga = liga;
	}
	public void setCapacidade(int capacidade){
		this.capacidade = capacidade;
	}
	public void setFundacaoDia(int fundacaoDia){
		this.fundacaoDia = fundacaoDia;
	}
	public void setFundacaoMes(int fundacaoMes){
		this.fundacaoMes = fundacaoMes;
	}
	public void setFundacaoAno(int fundacaoAno){
		this.fundacaoAno = fundacaoAno;
	}
	public Time clone (){
		Time resp = new Time();
		resp.fullName = this.fullName;
		resp.pagina = this.pagina;
		resp.apelido = this.apelido;
		resp.treinador = this.treinador;
		resp.liga = this.liga;
		resp.capacidade = this.capacidade;
		resp.fundacaoDia = this.fundacaoDia;
		resp.fundacaoMes = this.fundacaoMes;
		resp.fundacaoAno = this.fundacaoAno;
		return resp;
	}
	// METODO PARA PROCESSAR APELIDO DO TIME
	public static String apelidow(String s){
		String resp = "";
		for(int i = 0;i<s.length()-1;i++){

			if(s.charAt(i)=='"'){
				i+=1;
			}
			if(s.charAt(i)=='&'&&s.charAt(i+1)=='a'){
				i+=5;
			}
			if(s.charAt(i)=='<'&&s.charAt(i+1)=='s'&&s.charAt(i+2)=='p'){
				i+=20;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='/'&&s.charAt(i+2)=='s'&&s.charAt(i+3)=='p'){
				i+=6;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='i'&&s.charAt(i+2)=='>'){
				i+=2;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='/'&&s.charAt(i+2)=='i'){
				i+=3;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='b'&&s.charAt(i+2)=='r'&&s.charAt(i+3)==' '){
				i += 5;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='b'&&s.charAt(i+2)=='r'){
				i+=5;
			}else if(s.charAt(i)=='<'&&s.charAt(i+1)=='/'&&s.charAt(i+2)=='t'){
				i = s.length();
			}else {
				resp += s.charAt(i);
			}
		}
		if(resp.contains("&#")){
			String tempoResp = resp;
			resp = "";
			for (int i = 0;i<tempoResp.length();i++){
				if (tempoResp.charAt(i)=='&'&&tempoResp.charAt(i+1)=='#'){
					i+=11;
				}
				if(i<tempoResp.length()){
					resp += tempoResp.charAt(i);
				}
			}
		}
		if (resp.contains("<")){
			resp = removeTags(resp);
		}
		return resp;

	}
	// METODO PARA PROCESSAR CAPACIDADE
	public static int capacitow (String s){
		int resp;
		String num="";
		for( int i = 0;i<s.length();i++){
			if ( s.charAt(i)==' '&&s.charAt(i+1)<='9'&&s.charAt(i+1)>='0'&&i<s.length()){
				i++;
			}			
			if( s.charAt(i)==','){
				i++;
			}
			if (s.charAt(i)=='.'){
				i++;
			}
			if ( s.charAt(i)=='('){
				i = s.length();
				break;
			}
			if ( s.charAt(i)=='<'){
				i = s.length();
				break;
			}

			if( s.charAt(i)=='<'){
				i = s.length();
				break;
			}
			if( s.charAt(i)==' '&&(s.charAt(i+1)>9||s.charAt(i+1)<0)){
				i = s.length();
				break;
			}
			if( s.charAt(i)==';'){
				i = s.length();
				break;
			}

			if(i<s.length()){
				num += s.charAt(i);
			}
		}
		if (num.contains("<")){
			num = removeTags(num);
		}
		resp = Integer.parseInt(num);
		return resp;
	}
	// METODO PARA PROCESSAR DIA DE FUNDACAO
	public static int processaDia(String s){
		int resp;
		String num = "";
		if(s.charAt(2)>='a'&&s.charAt(2)<='z'){
			for(int j = 0;j<s.length()-1;j++){
				if((s.charAt(j)>='a'&&s.charAt(j)<='z')||(s.charAt(j)>='A'&&s.charAt(j)<='Z')||s.charAt(j)==' '){
					j++;
				}
				if(s.charAt(j)==','){
					j = s.length();
				}else if (s.charAt(j)>='0'&&s.charAt(j)<='9'){
					num += s.charAt(j);
				}

			}}else{
				for(int i = 0;i<s.length()-1;i++){
					if(s.charAt(i)=='&'||s.charAt(i)==' '){
						i = s.length();
					}else{
						if (s.charAt(i)>='0'&&s.charAt(i)<='9'){
							num += s.charAt(i);
						}
					}
				}
			}
		resp = Integer.parseInt(num);
		return resp;
	}
	// METODO PARA PROCESSAR MES DE FUNDACAO
	public static int processaMes(String s){
		int resp = 0;
		if (s.contains("Jan")){
			resp = 1;
		}else if (s.contains("Feb")){
			resp = 2;
		}else if (s.contains("Mar")){
			resp = 3;
		}else if (s.contains("Apr")){
			resp = 4;
		}else if (s.contains("May")){
			resp = 5;
		}else if (s.contains("Jun")){
			resp = 6;
		}else if (s.contains("Jul")){
			resp = 7;
		}else if (s.contains("Aug")){
			resp = 8;
		}else if (s.contains("Sep")){
			resp = 9;
		}else if (s.contains("Oct")){
			resp = 10;
		}else if (s.contains("Nov")){
			resp = 11;
		}else if (s.contains("Dec")){
			resp = 12;
		}
		return resp;
	}
	// METODO PARA PROCESSAR ANO DE FUNDACAO
	public static int processaAno(String s){
		int resp;
		String num = s;
		if (s.contains(" ")){ num =s.substring(s.indexOf(" ")+1);
		}
		if (num.contains(" ")){
			num = num.substring(num.indexOf(" ")+1);
		}
		if (num.contains("&#160;")){
			num = s.substring(s.indexOf("&#160;")+6);
		}
		if (num.contains("&#160;")){
			num = num.substring(num.indexOf("&#160;")+6);
		}
		resp = Integer.parseInt(num);
		return resp;
	}
	public void ler(String nomeArquivo){
		try{		
			String linha = "";
			String linha1 = "";
			String linhaEstadio = "";
			File file = new File(nomeArquivo);
			FileInputStream fis = new FileInputStream(file);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
			//tamanho da pagina
			this.paginaTam = file.length();
			// nome do arquivo
			this.pagina = nomeArquivo;
			fis.getChannel().position(0);
			buffer = new BufferedReader(new InputStreamReader(fis));
			//ler paragrafo relevante
			for(boolean stop = false; stop == false; linha1 = buffer.readLine() , stop = linha1.contains("ull name"));
			if (linha1.contains("vcard")){
				for (int k = 0; k<10;k++){
					linha1+= buffer.readLine();
				}
			}
			//ler NOMEi
			linha = linha1.substring(linha1.indexOf("<td>")+4);
			linha = linha.substring(0, linha.indexOf("</td>"));
			/*if(linha.contains("<a href")){
			  linha = linha.substring(0, linha.indexOf("<a href"));
			  }*/
			linha = removeTags(linha);
			this.fullName = linha;
			// ler ESTADIO
			if (linha1.contains(">Ground")){
				linha = linha1.substring(linha1.indexOf("Ground"));

				/*(if(linha.contains("title")){
				  linha = linha.substring(linha.indexOf("title="));
				  }else{
				  linha = linha.substring(linha.indexOf(">")+1);
				  }
				  linha = linha.substring(linha.indexOf(">")+1);
				  */	linha = linha.substring(linha.indexOf("<"),linha.indexOf("</td>"));
				linha = removeTags(linha);

			} else {
				for(boolean stop = false; stop == false; linhaEstadio = buffer.readLine(), stop = linhaEstadio.contains(">Ground"));
				linha = linhaEstadio.substring(linhaEstadio.indexOf(">Ground"));
				linha = linha.substring(linha.indexOf("title="));
				linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</td"));
				linha = removeTags(linha);
			}
			this.estadio = linha;
			//ler APELIDO
			linha = linha1.substring(linha1.indexOf("Nickname")+37);
			linha = linha.substring(0,linha.indexOf("scope"));
			linha = apelidow (linha); 
			this.apelido = linha;
			// ler TREINADOR
			if(linha1.contains("General manager")){
				linha = linha1.substring(linha1.indexOf("General manager")+15);
				linha = linha.substring(0,linha.indexOf("</td>"));
			}else if(linha1.contains("Head coach")){
				linha = linha1.substring(linha1.indexOf("Head coach")+10);
				linha = linha.substring(0,linha.indexOf("</td>"));
				linha = removeTags(linha);
				/*
				   if(linha.contains("</a>")){
				   linha = linha.substring(0,linha.indexOf("</a>"));
				   }else {
				   linha = linha.substring(0,linha.indexOf("</td>"));
				   }
				   linha = linha.substring(linha.indexOf("title=")+7);
				   linha = linha.substring(linha.indexOf(">")+1);
				   */
			}else if (linha1.contains("Manager")){
				linha = linha1.substring(linha1.indexOf("Manager"));	
				linha = linha.substring(linha.indexOf("agent")+7,linha.indexOf("</td"));
				if(linha.contains("title")){
					if(linha.contains("flag")){
						linha = removeTags(linha);
					}else{
						linha = linha.substring(linha.indexOf("title"));
						linha = linha.substring(linha.indexOf(">")+1);
						linha = linha.substring(0,linha.indexOf("<"));
					}
				}
			} else if (linha1.contains("oach")){

				linha = linha1.substring(linha1.indexOf("oach"));
				linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</td>"));
				linha = removeTags(linha);
				/*
				   linha = linha.substring(linha.indexOf("title="));
				   linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</"));
				   */
			} else{
				String linha2 = "";
				for(boolean stop = false; stop == false; linha2 = buffer.readLine() , stop = linha2.contains("Manager")||linha2.contains("oach")||Arq.hasNext()==false);
				if(linha2.contains("oach")){
					if (linha2.contains("Head coach")){	
						linha = linha2.substring(linha2.indexOf("Head coach"),linha2.indexOf("League"));
						linha = linha.substring(0,linha.indexOf("</a>"));
						linha = linha.substring(linha.indexOf("title=")+7);
						linha = linha.substring(linha.indexOf(">")+1);
					}

				}else{
					fis.getChannel().position(0);
					buffer = new BufferedReader(new InputStreamReader(fis));
					for(boolean stop = false; stop == false;linha2 = buffer.readLine(), stop = linha2.contains("Manager</th"));

					if(linha2.contains("Manager</th")){
						linha = linha2.substring(linha2.indexOf("Manager"));
						linha = linha.substring(linha.indexOf("agent")+7,linha.indexOf("</td"));
						if(linha.contains("title")){
							if(linha.contains("flag")){
								linha = removeTags(linha);
							}else{
								linha = linha.substring(linha.indexOf("title"));
								linha = linha.substring(linha.indexOf(">")+1);
								linha = linha.substring(0,linha.indexOf("<"));
							}
						}

					}
				}
				/*else if

				  (linha2.contains("oach")){
				  System.out.println("Energumeno");
				  System.out.println(linha2);
				  linha = linha2.substring(linha2.indexOf("Coach"));
				  linha = linha.substring(linha.indexOf("title="));
				  linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</"));
				  System.out.println("1"+linha);
				  } else if (linha2.contains("Manager")&&!linha2.contains("Coach")){
				  linha = linha2.substring(linha2.indexOf("Manager"));System.out.println(linha);	
				  linha = linha.substring(linha.indexOf("agent")+7,linha.indexOf("</td"));
				  if(linha.contains("title")){
				  linha = linha.substring(linha.indexOf("title"));
				  linha = linha.substring(linha.indexOf(">")+1);
				  linha = linha.substring(0,linha.indexOf("<"));
				  }
				  }*/
			}
			linha = removeTags(linha);
			this.treinador = linha;
			// ler LIGA
			if (linha1.contains(">League")){
				linha = linha1.substring(linha1.indexOf(">League"));
				linha = linha.substring(linha.indexOf("title="));
				linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</td>"));
			}else{
				String linha3 = "";
				fis.getChannel().position(0);
				buffer = new BufferedReader(new InputStreamReader(fis));
				for(boolean stop = false; stop == false; linha3 = buffer.readLine() , stop = linha3.contains("League"));

				linha = linha3.substring(linha3.indexOf("League"));
				linha = linha.substring(linha.indexOf("title="));
				linha = linha.substring(linha.indexOf(">")+1,linha.indexOf("</a>"));
			}
			linha = removeTags(linha);
			this.liga = linha;
			// ler CAPACIDADE DO ESTADIO
			if (linha1.contains("Capacity")){
				linha = linha1.substring(linha1.indexOf("Capacity"));
				linha = linha.substring(linha.indexOf("<td>")+4);
				if(linha.contains("</td")){
					linha = linha.substring(0,linha.indexOf("</td>"));
				}
			}else {
				String linhaCapacidade = "";
				fis.getChannel().position(0);
				buffer = new BufferedReader(new InputStreamReader(fis));
				for(boolean stop = false; stop == false; linhaCapacidade = buffer.readLine() , stop = linhaCapacidade.contains("Capacity"));
				linha = linhaCapacidade.substring(linhaCapacidade.indexOf("Capacity"));
				linha = linha.substring(linha.indexOf("<td>")+4,linha.indexOf("</td>"));
			}



			this.capacidade = capacitow(linha);
			// LER DATA DE FUNDACAO
			linha = linha1.substring(linha1.indexOf("Founded"));
			//linha = linha.substring(linha.indexOf("<td>")+4,linha.indexOf("</td>"));
			linha = linha.substring(linha.indexOf("<td>")+4,60);
			if (linha.contains("<span")){
				linha = linha.substring( 0, linha.indexOf("<span"));
			}
			if (linha.contains("</td")){
				linha = linha.substring(0,linha.indexOf("</td"));
			}
			if((linha.contains("&")||linha.indexOf(" ")==2||linha.indexOf(" ")==1)){

				if(linha.contains("&#160;")){
					if(linha.charAt(0)>='0'&&linha.charAt(0)<='9'){
						this.fundacaoDia = processaDia(linha);}else{
							if(linha.contains(",")){
								String linhaData = linha.substring(linha.indexOf(";")+1);
								this.fundacaoDia = processaDia(linhaData);
							}else{
								this.fundacaoDia = 0;
							}
						}
				}else{
					if(linha.charAt(1)>='a'&&linha.charAt(1)<='z'){
						this.fundacaoDia = 0;
					}else{
						this.fundacaoDia = processaDia(linha);
					}
				}
				this.fundacaoMes = processaMes(linha);
				this.fundacaoAno = processaAno(linha);
			}else if(linha.contains(";")||linha.contains(" ")){
				if(linha.charAt(2)>='a'&&linha.charAt(2)<='z'){
					this.fundacaoDia = processaDia(linha);
					this.fundacaoMes = processaMes(linha);
					this.fundacaoAno = processaAno(linha);
				}else{this.fundacaoDia = 00;
					this.fundacaoMes = processaMes(linha);
					this.fundacaoAno = processaAno(linha);
				}
			}else{
				if(linha.charAt(2)>='a'&&linha.charAt(2)<='z'){
					this.fundacaoDia = processaDia(linha);
					this.fundacaoMes = processaMes(linha);
					this.fundacaoAno = processaAno(linha);
				}else{
					this.fundacaoDia = 00;
					this.fundacaoMes = 00;
					this.fundacaoAno = processaAno(linha);
				}
			}
			buffer.close();

		}catch(IOException g){


		}
	}
	public  void imprimir(){
		String mes = String.valueOf(fundacaoMes);
		String dia = String.valueOf(fundacaoDia);
		if(fundacaoMes<10){
			mes = "0"+mes;
		}
		if(fundacaoDia<10){
			dia = "0"+dia;
		}
		if(fundacaoMes==0){
			mes = "00";
		}
		if(fundacaoDia==0){
			dia = "00";
		}
		String ano = String.valueOf(fundacaoAno);
		System.out.println(fullName+" ## "+apelido+" ## "+dia+"/"+mes+"/"+ano+" ## "+estadio+" ## "+capacidade+" ## "+treinador+" ## "+liga+" ## "+pagina+" ## "+paginaTam+" ## ");

	}
	public static String removeTags(String s){
		String resp = "";
		for (int i = 0; i < s.length(); i++){
			//remover tags entre < e >
			while(i < s.length() && s.charAt(i) == '<'){
				for (i++; s.charAt(i) != '>'; i++);
				i++;
			}
			if(i < s.length()){
				resp += s.charAt(i);
			}
		}
		String tempResp = resp;
		resp = "";
		for( int i = 0; i < tempResp.length();i++){
			if(tempResp.charAt(i)=='&'&&tempResp.charAt(i+1)=='#'&&tempResp.charAt(i+2)=='1'){
				i+=6;
				resp += " ";
			}else if(tempResp.charAt(i)=='&'&&tempResp.charAt(i+1)=='#'){
				i+=11;
				//resp += " ";
			}
			if(i<tempResp.length()){
				resp += tempResp.charAt(i);
			}
		}
		String tempRespo = resp;
		resp = "";
		for( int i = 0; i < tempRespo.length();i++){

			if(tempRespo.charAt(i)=='&'&&tempRespo.charAt(i+1)=='a'){
				i+=5;
				resp += " ";
			}
			if(i<tempRespo.length()){
				resp += tempRespo.charAt(i);
			}
		}
		return resp;
	}
}

class No{
	public Time elemento = new Time();
	public No esq,dir;
	public No(Time elemento){
		this(elemento,null,null);
	}
	public No(Time elemento, No esq, No dir){
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
	public void inserir(Time x) throws Exception {
		raiz = inserir(x,raiz);
	}
	private No inserir(Time x, No i) throws Exception{
	if(i==null){
	i = new No(x);
	} else if (x.getFullName().compareTo(i.elemento.getFullName())<0){
		i.esq = inserir(x,i.esq);
	} else if (x.getFullName().compareTo(i.elemento.getFullName())>0){
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
	private boolean pesquisar(String x, No i){
		boolean resp;
		System.out.println(x+"+"+i.elemento.getFullName());
		if(i==null){
			resp = false;
		System.out.println(1);
		}else if(x == i.elemento.getFullName()){
			resp = true;
		System.out.println(2);
		}else if(x.compareTo(i.elemento.getFullName())<0){
			resp = pesquisar(x,i.esq);
		System.out.println(3);
		}else{
			resp = pesquisar(x,i.dir);
		System.out.println(4);
		}
		return resp;
	}
	public static void main (String [] args){
		long startTime = System.nanoTime();
		MyIO.setCharset("UTF-8");	
		String[] entrada = new String[1000];
		int numEntrada = 0;
		int numEntrada2 = 0;

		String [] entrada2 = new String[1000];
		try{
			//Leitura da entrada padrao
			do {
				entrada[numEntrada] = MyIO.readLine();
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
			Lista lista = new Lista();
			//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				//System.out.println("Time"+i+" "+entrada[i]);
				Time time = new Time();
				time.ler(entrada[i]);
				lista.inserir(time);
				/*	if(i==0){
					lista.inserirFim(time);
					}*/
			}
			//Leitura da entrada padrao
			do {
				entrada2[numEntrada2] = MyIO.readLine();
			} while (!entrada2[numEntrada2++].contains("FIM"));
			numEntrada2--;   //Desconsiderar ultima linha contendo a palavra FIM
			for(int j = 0;j<numEntrada2;j++){
				boolean temp = lista.pesquisar(entrada2[j]);
				if(temp==true){
					System.out.println("SIM");
				}else{
					System.out.println("NÃO");
				}
			}
			//lista.percorrer(3).elemento.imprimir();
			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;
			int compara = lista.getComparacoes();
			FileWriter hugoFile;
			hugoFile = new FileWriter("396702_arvoreBinaria.txt");
			BufferedWriter hugoEscrever = new BufferedWriter(hugoFile);
			hugoEscrever.write("396702"+"\t"+timeElapsed+"\t"+compara);
			hugoEscrever.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}


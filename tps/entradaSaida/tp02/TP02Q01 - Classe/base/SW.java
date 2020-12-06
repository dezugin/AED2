import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

class SW {

	private String nome, corDoCabelo, codDaPele, corDosOlhos, anoNascimento, genero, homeworld;
	private int altura;
	private double peso;

	public Time() {
		nome = corDoCabelo, codDaPele, corDosOlhos, anoNascimento, genero, homeworld = "";
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
		System.out.println(toString());
	}

	public String toString() {
		String resp = "## "+ nome + " ## " + altura + " ## " + peso + " ## "+ corDoCabelo + " ## " + codDaPele + " ## " + corDosOlhos+ " ## "+ anoNascimento+ " ## "+ genero+ " ## "+ homeworld+ " ## ";
		//resp += ((altura = "unknown") ? "0" : altura) + " ## "+ ((peso = "unknown") ? "/" : "/0") 
		return resp;
	}

	//TODO: Clone
	public Time clone (){
		Time resp = new Time();
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

	public String removePunctuation(String campo) {
		campo = campo.replace(".", "");
		campo = campo.replace(",", "");
		campo = campo.replace(";", "");
		String resp = "";
		for (int i = 0; i < campo.length(); i++) {
			if (Character.isDigit(campo.charAt(i)))
				resp += campo.charAt(i);
			else
				i = campo.length();
		}

		return resp;
	}



	public String getSubstringEntre(String s, String antes, String depois) {
		String resp = "";
		int posInicio, posFim;

		posInicio = s.indexOf(antes) + antes.length();

		if (antes.compareTo(depois) != 0) {
			posFim = s.indexOf(depois);
		} else {
			posFim = s.indexOf(depois, posInicio);
		}

		if (0 <= posInicio && posInicio < posFim && posFim < s.length()) {
			resp = s.substring(posInicio, posFim);
		}

		return resp;
	}

	//TODO: removeTags (String s)

	public String removeTags(String s) {
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
	public void ler(String nomeArquivo) throws Exception {
		try
		{FileReader file = new FileReader(nomeArquivo);
		BufferedReader buffer = new BufferedReader(file);
		String html = "";
		String line = buffer.readLine();
		while (line != null) {
			html += line;
			line = buffer.readLine();
		}

		buffer.close();
		file.close();

		html = html.substring(html.indexOf("Full name"));
		html = html.substring(0, html.indexOf("<table style"));
		String campos[] = html.split("<tr>");

		this.pagina = nomeArquivo;

		for (String campo : campos) {
			// Full name
			if (removeTags(campo).contains("Full name")) {
				campo = removeTags(campo);
				this.fullName = campo.substring(9).trim();

				// Nickname(s)
			} else if (removeTags(campo).contains("Nickname(s)")) {
				campo = removeTags(campo);
				this.apelido = campo.substring(11).trim();

				// Founded
			} else if (removeTags(campo).toLowerCase().contains("founded")) {
				campo = removeTags(campo.split("<br />")[0]);
				this.fundacaoMes = this.getMes(campo.toLowerCase());

				if (this.fundacaoMes == 0) {
					this.fundacaoDia = 0;
					campo = campo.substring(7);
					this.fundacaoAno = Integer.parseInt(campo.substring(0, 4));
				} else {
					campo = campo.substring(7);
					String data[] = campo.split(" ");
					if (data.length < 3) {
						this.fundacaoDia = 0;
						this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
					} else {
						if (campo.contains(",")) {
							this.fundacaoDia = Integer.parseInt(data[1].replace("th", "").replace(",", ""));
							this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
						} else if (Character.isDigit(data[0].charAt(0))) {
							this.fundacaoDia = Integer.parseInt(data[0]);
							this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
						} else {
							this.fundacaoDia = 0;
							this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
						}
					}
				}

				// Ground
			} else if (removeTags(campo).toLowerCase().contains("ground")) {
				campo = removeTags(campo);
				this.estadio = campo.substring(6).trim();

				// Capacity
			} else if (removeTags(campo).toLowerCase().contains("capacity")) {
				campo = campo.split("<br")[0];
				campo = removeTags(campo.split("</td>")[0].replace(" ", ""));
				this.capacidade = Integer.parseInt(removePunctuation(campo.substring(8).split(";")[0]));

				// Coach
			} else if (removeTags(campo).toLowerCase().contains("coach") || campo.toLowerCase().contains("manager")) {
				campo = removeTags(campo).replace("(es)", "").trim();
				if (campo.toLowerCase().contains("coach")) {
					int index = campo.toLowerCase().indexOf("coach");
					this.treinador = campo.substring(index + 5).trim();
				} else if (campo.toLowerCase().contains("manager") && this.treinador.isEmpty()) {
					int index = campo.toLowerCase().indexOf("manager");
					this.treinador = campo.substring(index + 7).trim();
				}
				// League
			} else if (removeTags(campo).contains("League") && this.liga.isEmpty()) {
				campo = removeTags(campo);
				this.liga = campo.substring(6).trim();
			}
		}

		File f = new File(pagina);
		this.paginaTam = f.length();
		}catch(Exception z){
		System.out.println(z);
		}
	}
	public static void main (String [] args){	
		MyIO.setCharset("UTF-8");		
		try{
		Time time = new Time();
		String[] entrada = new String[1000];
		int numEntrada = 0;
		String [] entrada2 = new String[1000];
		//Leitura da entrada padrao
		do {
			entrada[numEntrada] = MyIO.readLine();
			System.out.println("1+"+entrada[numEntrada]);
		} while (!entrada[numEntrada++].contains("FIM"));
		numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
		/*		String num = MyIO.readLine();
				int numero = Integer.parseInt(num);
				for ( int j = 0;j<numero;j++){
				entrada2[j]= MyIO.readline();
				}		
				*/
		//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
		for(int i = 0; i < numEntrada; i++){
			//System.out.println("Time"+i+" "+entrada[i]);
			time.ler(entrada[i]);
			time.imprimir();
			System.out.println("2");
		}
	}catch(Exception e){
	}
	}
}


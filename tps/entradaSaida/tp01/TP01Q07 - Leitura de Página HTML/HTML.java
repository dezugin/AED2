import java.net.*;
import java.io.*;

public class HTML2 {
	// baixar conteudo HTTP do website
	private static String baixarHttp(String url) throws Exception {
		URL objeto = new URL(url);
		HttpURLConnection conexao = (HttpURLConnection) objeto.openConnection();

		int resultCode = conexao.getResponseCode();

		BufferedReader br = new BufferedReader(
				new InputStreamReader(conexao.getInputStream())
				);
		StringBuffer result = new StringBuffer();
		String coluna = "";

		while ((coluna = br.readLine()) != null)
			result.append(coluna);

		br.close();

		return result.toString();
	}
	// verificar se equals
	private static boolean ehEquals(String x, String y) {
		int contador = 0;
		boolean resposta = false;

		if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				if (x.charAt(i) == y.charAt(i))
					contador++;
			}
		}


		if (contador == x.length())
			resposta = true;

		return resposta;
	}
	//verificar se consoante
	private static int contarConsoantes(String result) {
		int contador = 0;

		for (int i = 0; i < result.length(); i++) {
			if ((result.charAt(i) == '<' && result.charAt(i + 1) == 'b'
						&& result.charAt(i + 2) == 'r' && result.charAt(i + 3) == '>'))
				i += 3;
			if ((result.charAt(i) == '<' && result.charAt(i + 1) == 't'
						&& result.charAt(i + 2) == 'a' && result.charAt(i + 3) == 'b'
						&& result.charAt(i + 4) == 'l' && result.charAt(i + 5) == 'e'
						&& result.charAt(i + 6) == '>'))
				i += 6;
			else {
				switch (result.charAt(i)) {
					case 'b':
					case 'c':
					case 'd':
					case 'f':
					case 'g':
					case 'h':
					case 'j':
					case 'k':
					case 'l':
					case 'm':
					case 'n':
					case 'p':
					case 'q':
					case 'r':
					case 's':
					case 't':
					case 'v':
					case 'w':
					case 'x':
					case 'y':
					case 'z':
						contador++;
				}
			}
		}

		return contador;
	}

	//verificar br e table
	private static int[] contarBrTable(String result) {
		String[] html = new String[] { "<br>", "<table>" };
		int[] contar = new int[2];
		String contador = "";

		for (int i = 0; i < result.length() - 3; i++) {
			if (result.charAt(i) == '<' && result.charAt(i + 1) == 'b'
					&& result.charAt(i + 2) == 'r' && result.charAt(i + 3) == '>')
				contar[0]++;
		}

		for (int i = 0; i < result.length() - 6; i++) {
			if (result.charAt(i) == '<' && result.charAt(i + 1) == 't'
					&& result.charAt(i + 2) == 'a' && result.charAt(i + 3) == 'b'
					&& result.charAt(i + 4) == 'l' && result.charAt(i + 5) == 'e'
					&& result.charAt(i + 6) == '>')
				contar[1]++;
		}

		return contar;
	}


	// contar todas as vogais.

	private static int[] contarVogais(String result) {
		int[] contar = new int[22];

		for (int i = 0; i < result.length(); i++) {
			if ((result.charAt(i) == '<' && result.charAt(i + 1) == 'b'
						&& result.charAt(i + 2) == 'r' && result.charAt(i + 3) == '>'))
				i += 3;
			if ((result.charAt(i) == '<' && result.charAt(i + 1) == 't'
						&& result.charAt(i + 2) == 'a' && result.charAt(i + 3) == 'b'
						&& result.charAt(i + 4) == 'l' && result.charAt(i + 5) == 'e'
						&& result.charAt(i + 6) == '>'))
				i += 6;
			else {
				switch(result.charAt(i)) {
					case 'a':
						contar[0]++;
						break;
					case 'e':
						contar[1]++;
						break;
					case 'i':
						contar[2]++;
						break;
					case 'o':
						contar[3]++;
						break;
					case 'u':
						contar[4]++;
						break;
					case 'á':
						contar[5]++;
						break;
					case 'é':
						contar[6]++;
						break;
					case 'í':
						contar[7]++;
						break;
					case 'ó':
						contar[8]++;
						break;
					case 'ú':
						contar[9]++;
						break;
					case 'à':
						contar[10]++;
						break;
					case 'è':
						contar[11]++;
						break;
					case 'ì':
						contar[12]++;
						break;
					case 'ò':
						contar[13]++;
						break;
					case 'ù':
						contar[14]++;
						break;
					case 'ã':
						contar[15]++;
						break;
					case 'õ':
						contar[16]++;
						break;
					case 'â':
						contar[17]++;
						break;
					case 'ê':
						contar[18]++;
						break;
					case 'î':
						contar[19]++;
						break;
					case 'ô':
						contar[20]++;
						break;
					case 'û':
						contar[21]++;
						break;
				}
			}
		}

		return contar;
	}   
	public static void main(String[] args) {
	String[] entrada = new String[1000];
		int[] cVogais, cHtml;
		String resultado = "", pageName = "";
		int nEntrada = 0, nConsoantes = 0;
		char[] vogais = new char[] { 'a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú', 'à', 'è', 'ì', 'ò', 'ù', 'ã', 'õ', 'â', 'ê', 'î', 'ô', 'û' };

		// Leitura da entrada padrao
		do {
			entrada[nEntrada] = MyIO.readLine();
		} while (!ehEquals(entrada[nEntrada++], "FIM"));

		nEntrada--; // Desconsiderar ultima linha contendo a palavra "FIM"

		try {
			for (int i = 0; i < nEntrada; i++) {
				String result = "";

				if (i % 2 == 1) {
					result = baixarHttp(entrada[i]);

					cVogais = contarVogais(result);
					cHtml = contarBrTable(result);
					nConsoantes = contarConsoantes(result);

					for (int j = 0; j < 22; j++) 
						resultado += vogais[j] + "(" + cVogais[j] + ") ";

					resultado += "consoante(" + nConsoantes + ") <br>(" + cHtml[0] + ") <table>(" + cHtml[1] + ") " + pageName; 
				}
				else
					pageName = entrada[i] + "\n";
			}

			MyIO.print(resultado);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

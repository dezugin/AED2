public class parenteses{
	public static boolean contarParenteses(String frase){
		int contadorEsquerda = 0;
		int contadorDireita = 0;
		boolean resp = false;		
		for(int i = 0;i<frase.length();i++){
			if(frase.charAt(i)=='('){
				contadorEsquerda++;
			}
			if(frase.charAt(i)==')'&&contadorEsquerda!=0){
				contadorDireita++;
			}
		}
			if(contadorEsquerda==contadorDireita){
				resp = true;
			}
		return resp;
	}
	public static void main (String [] args){
		String[] entrada = new String[1000];
			int numEntrada = 0;		
			//Leitura da entrada padrao
			do {
				entrada[numEntrada] = MyIO.readLine();
			} while (!entrada[numEntrada++].contains("FIM"));
			numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
			//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
			for(int i = 0; i < numEntrada; i++){
				boolean resposta = contarParenteses(entrada[i]);
				if(resposta){
				System.out.println("correto");
				}else{
				System.out.println("incorreto");
				}
			}
	}
}

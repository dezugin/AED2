import java.util.Random;
public class aleatorio{
	//alterador aleatorio
	public static String alteracao(String alterada){
			String alterar = "";
			Random gerador = new Random();
			gerador.setSeed(4);
			for(int i = 0;i<alterada.length();i++){
				char sorteado1 = (char)('a'+(Math.abs(gerador.nextInt())%26));
				char sorteado2 = (char)('a'+(Math.abs(gerador.nextInt())%26));
				char caractere = alterada.charAt(i);
				if(caractere == sorteado1){
					caractere = sorteado2;
				}
				alterar += caractere;
			}
			return alterar;
	}
	public static void main (String[]args){
		String [] entrada = new String[1000];
		int numEntrada = 0;
		//leitura de entrada padrao
		do{
			entrada[numEntrada] = MyIO.readLine();
		}while(entrada[numEntrada++].equals("FIM")==false);
		numEntrada--; // desconsiderar ultima
		for(int i = 0;i<numEntrada;i++){
			String teste = alteracao(entrada[i]);
			MyIO.println(teste);
		}
	}
}



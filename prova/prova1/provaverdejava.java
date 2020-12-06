public class provaverdejava{
	int n;
	String [] array;
	int contar;
	provaverdejava(int tamanho){
		this.array = new String[tamanho];
		n = 0;
		contar = 0;
	}
	void inserirFim(String x){
		array[n] = x;
		n++;
	}
	void mostrar(){
		for(int i = 0; i<n;i++){
			System.out.print(array[i]+ " ");
		}
		System.out.println();
	}
	int pesquisa(String s){
		int resp = 0;
		for(int i = 0;i<n;i++){
			String nome = array[i];
			if(nome.contains(s)){
				resp++;
			}
		}
		return resp;
	}
	void ordenacao(){
		for(int i = 0;i<n;i++){
			int menor = i;
			for(int j = (i+1);j<n;j++){
				if(array[menor].compareTo(array[j])>0){
					menor = j;
				}
			}
			swap(menor,i);
		}

	}
	void swap (int i1, int i2){
		String temp = array[i1];
		array[i1]=array[i2];
		array[i2]=temp;
	}
	public static void main(String[] args) {  
		String temp = MyIO.readLine();
		int numero = Integer.parseInt(temp);
		String entrada;	
		for(int i = 0;i<numero;i++){
			provaverdejava Lista = new provaverdejava(1000);	
			entrada = MyIO.readLine();
		//	System.out.println("entrada = "+entrada);
			String[] arrayDeEntrada = entrada.split(" ");
			for(String a:arrayDeEntrada){
		//		System.out.println("a1 = "+a);
				if(Lista.pesquisa(a)==0){
		//			System.out.println("a2 = "+a);
					Lista.inserirFim(a);
		//			Lista.contar++;
				}
			}
		Lista.ordenacao();
		Lista.mostrar();
		}
	}
}

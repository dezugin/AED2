class No{
	public char elemento;
	public No esq, dir;
	public No(char elemento){
		this(elemento,null,null);
	}
	public No(char elemento, No esq, No dir){
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

public class labzord{
	int preNum = 0;
	public No inserir(String in, String pre, int num, int num2){
		if(num>num2){
			return null;
		}
		//System.out.println(pre.charAt(preNum));
		No no = new No(pre.charAt(preNum++));
		if(num == num2){
			return no;
		}
		int inNum = procurar(in,num,num2,no.elemento);
		no.esq = inserir(in,pre,num,inNum-1);
		no.dir = inserir(in,pre,inNum+1,num2);
		return no;
	}
	int procurar(String in, int num, int num2, char elemento){
		int i;
		for(i=num;i<=num2;i++){
			if(in.charAt(i)==elemento){
				return i;
			}
		}
		return i;
	}
	void mostrarPos(No i){
		if(i!=null){
			mostrarPos(i.esq);
			mostrarPos(i.dir);
			System.out.print(i.elemento);
		}
	}
	public static void main (String [] args){
		String temp = MyIO.readLine();
		int num = Integer.parseInt(temp);
		for(int i = 0;i<num;i++){
			labzord Lab = new labzord();
			temp = MyIO.readLine();
			String zord[] = temp.split(" ");
			int num2 = Integer.parseInt(zord[0]);
			String prefixo = zord[1];
			String infixo = zord[2];
			
			No raiz = Lab.inserir(infixo,prefixo,0,num2-1);
			
			Lab.mostrarPos(raiz);	
			System.out.println();
		}
	}
}

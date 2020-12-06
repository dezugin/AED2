public class Agenda {
	private No raiz; 

	public Agenda() {
		raiz = new No ('M');
      raiz.esq = new No ('F');
      raiz.dir = new No ('T');
      raiz.esq.esq = new No ('C');
      //inserir todas as 26 letras do alfabeto...
	}

	public boolean pesquisarNome(String nome) {
		return pesquisarNome(raiz, nome);
	}
	private boolean pesquisarNome(No no, String nome) {
      boolean resp;
		if (no == null) { 
         resp = false;
      } else if (Char.toUpper(nome.charAt(0)) == no.letra) { 
         resp = false;
         for(Celula i = no.primeiro.prox; i != null; i = i.prox){
            if(i.nome.equals(nome) == true){
               i = null;
               resp = true;
            }
         }
      } else if (Char.toUpper(nome.charAt(0)) < no.letra) { 
         resp = pesquisarNome(no.esq, nome); 

      } else { 
         resp = pesquisarNome(no.dir, nome); 
      }
      return resp;
	}

	public void inserir(Contato contato) throws Exception {
		inserir(raiz, contato);
	}

	private void inserir(No no, Contato contato) throws Exception {
		if (no == null) { 
         throw new Exception("Erro ao inserir!"); 

      } else if (Char.toUpper(contato.nome.charAt(0)) == no.letra) { 
         no.ultimo.prox = new Celula(contato);
         no.ultimo = no.ultimo.prox;

      } else if (Char.toUpper(contato.nome.charAt(0)) < no.letra) { 
         inserir(no.esq, contato);
      } else { 
         inserir(no.dir, contato);
      }
	}
	
   public boolean pesquisar(int cpf) {
		return pesquisar(raiz, cpf);
	}

	private boolean pesquisar(No i, int cpf) {
      boolean resp = false;
		if (i != null) {
         resp = pesquisar(i.primeiro.prox, cpf);
         if(resp == false){
			   resp = pesquisar(i.esq, cpf);
            if(resp == false){
			      resp = pesquisar(i.dir, cpf);
            }
         }
		}
      return resp;
	}

   private boolean pesquisar(Celula i, int cpf){
      //efeuar a pesquisa na lista a partir do i
   }
}

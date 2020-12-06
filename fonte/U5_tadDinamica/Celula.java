class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.
	
	public Celula() { this(0); }

	public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}

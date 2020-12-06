public class TP02Q01Classe {

	public static String ISO88591toUTF8(String strISO) throws Exception {
		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		for (String arquivo = MyIO.readString(); arquivo.replace(" ", "").equals("FIM") == false; arquivo = MyIO
				.readString()) {
			Personagem personagem = new Personagem();
			personagem.ler(ISO88591toUTF8(arquivo));
		}
	}
}

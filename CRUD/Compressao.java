import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.io.File;

public class Compressao {
    // private RandomAccessFile rnd = new RandomAccessFile("dicionario.db", "rw");
    private int id;
    private int TAMANHODIC = 65535;
    boolean restored = false;
    public HashMap<String, Integer> dicionario = new HashMap<String, Integer>();

    public Compressao() throws IOException {
        id = 1;
    }

    public void criaDicionario() {
        dicionario.put(String.valueOf((char) 28), 0);
        for (int i = 32; i < 127; i++) {
            String aux = String.valueOf((char) i);
            dicionario.put(aux, id);
            id++;
        }
        for (int i = 128; i < 168; i++) {
            String aux = String.valueOf((char) i);
            dicionario.put(aux, id);
            id++;
        }

    }

    public void iniciaCompressao() throws IOException {
        FileOutputStream fil2 = new FileOutputStream("codificacao.db");
        DataOutputStream codificacao = new DataOutputStream(fil2);
        RandomAccessFile bd = new RandomAccessFile("netflix.db", "r");
        // RandomAccessFile leitura_dic = new RandomAccessFile("dicionario.db", "r");
        char[] vetsimbolo = particiona_int(bd.readInt());// cabecalho
        String verifica = EscreveCodificacaoEdicionario(vetsimbolo, codificacao);

        while (true) {
            try {
                vetsimbolo = verificaSimbolo(verifica, bd, 1);// Indicador de tamanho
                verifica = EscreveCodificacaoEdicionario(vetsimbolo, codificacao);

                vetsimbolo = verificaSimbolo(verifica, bd, 2);// Lápide
                verifica = EscreveCodificacaoEdicionario(vetsimbolo, codificacao);

                vetsimbolo = verificaSimbolo(verifica, bd, 1);// ID
                verifica = EscreveCodificacaoEdicionario(vetsimbolo, codificacao);

                for (int i = 0; i < 10; i++) {
                    vetsimbolo = verificaSimbolo(verifica, bd, 2);// Tipo,Titulo,Diretor,Elenco,Pais,DATAadd,DataLancamento,Avaliacao,Duração,Separador(;)
                    verifica = EscreveCodificacaoEdicionario(vetsimbolo, codificacao);
                }

            } catch (Exception eOFException) {
                codificacao.close();
                bd.close();
                System.out.println("Arquivo finalizado");
                break;
            }
        }

    }

    /* Método que particiona um número inteiro, em um vetor de char */
    private char[] particiona_int(int num) {
        String auxiliar = Integer.toString(num);
        char[] vetsimbolo = auxiliar.toCharArray();
        return vetsimbolo;

    }

    private String EscreveCodificacaoEdicionario(char[] vetsimbolo, DataOutputStream codf) throws IOException {
        String aux = "";
        int cod_escrita = dicionario.get(String.valueOf(vetsimbolo[0]));
        for (int i = 0; i < vetsimbolo.length; i++) {
            if (dicionario.get(String.valueOf(vetsimbolo[i])) == null) {
                vetsimbolo[i] = '?';
            }
            aux += String.valueOf(vetsimbolo[i]);
            if (dicionario.get(aux) == null) {
                if (id == TAMANHODIC) {
                    dicionario.clear();
                    id = 1;
                    restored = true;
                    criaDicionario();
                }
                dicionario.put(aux, id);

                aux = "";

                codf.write(cod_escrita / 256);
                codf.write(cod_escrita);

                id++;
                i--;
            } else {
                cod_escrita = dicionario.get(String.valueOf(aux));
            }
        }
        return aux;
    }

    private char[] verificaSimbolo(String verifica, RandomAccessFile bd, int leUTFouInt)
            throws IOException {
        String auxiliar = verifica;
        auxiliar += String.valueOf((char) 28);
        if (leUTFouInt == 1) {// Lê int
            int leitura = bd.readInt();

            char[] arraychar = particiona_int(leitura);
            char[] arrayFinal = new char[arraychar.length + auxiliar.length()];

            return criaVetArray(auxiliar, arrayFinal, arraychar);

        } else {// lê UTF
            String leitura = bd.readUTF();

            char[] arraychar = leitura.toCharArray();
            char[] arrayFinal = new char[arraychar.length + auxiliar.length()];

            return criaVetArray(auxiliar, arrayFinal, arraychar);
        }
    }

    private char[] criaVetArray(String auxiliar, char[] arrayFinal, char[] arraychar) {
        for (int i = 0; i < auxiliar.length(); i++) {
            arrayFinal[i] = auxiliar.charAt(i);
        }
        int z = 0;
        for (int i = auxiliar.length(); i < arrayFinal.length; i++) {
            arrayFinal[i] = arraychar[z];
            z++;
        }
        return arrayFinal;
    }

    public void ganho() {
        File arqcodif = new File("codificacao.db");
        File arqoriginal = new File("netflix.db");
        if (arqcodif.exists() && arqoriginal.exists()) {
            long tamanhoarqo = arqoriginal.length() / 1000;
            long tamanhoarqcom = arqcodif.length() / 1000;
            System.out.println("Tamanho do arquivo original: " + tamanhoarqo);
            System.out.println("Tamanho do arquivo comprimido: " + tamanhoarqcom);
            System.out.println("Arquivo original é: " + (float) tamanhoarqo / (float) tamanhoarqcom + " vezes maior");
        }
    }

}
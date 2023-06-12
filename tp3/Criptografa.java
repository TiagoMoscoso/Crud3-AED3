import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.DataOutputStream;
import java.io.File; 

public class Criptografa {
    public FileOutputStream arq;
    public DataOutputStream dos;
    private File aux;
    private String chave = "CARO";
    private String chaveAux = "";
    private String stringCrip = "";

    public Criptografa() throws IOException{
        aux = new File("cripto.db");
        if (aux.exists()){
            aux.delete();//reseta o arquivo
        }
        arq = new FileOutputStream("cripto.db", true);
        dos = new DataOutputStream(arq);
        RandomAccessFile rnd = new RandomAccessFile("netflix.db", "r");
        
        try{
            dos.writeInt(rnd.readInt());//escreve o cabecalho
            while(true){
                dos.writeInt(rnd.readInt());//escreve o indicador de tamanho do registro
                dos.writeUTF(rnd.readUTF());//escreve a lápide
                dos.writeInt(rnd.readInt());//escreve o ID
    
                for (int i=0;i<9;i++){
                    String palavra = rnd.readUTF();
                    palavra = trataString(palavra);
                    formataAux(palavra);
                    criptografaValor(palavra);
                    escreveArq();
                }
                dos.writeUTF(rnd.readUTF());//escreve o separador ;
    
            }
           }catch(Exception eOEFException){
            rnd.close();
            System.out.println("Fim da criptografia!");
           }
           
    }

    /*Método publico para fazer a criptografia de cada campo */
    public void criptografaValor(String palavra){
        stringCrip = "";
        for (int i=0;i<palavra.length();i++){
            int asciChave = chaveAux.charAt(i);
            int asciPalavra = palavra.charAt(i);

            //NAO IREMOS CRIPTOGRAFAR CARACTERES QUE NAO SEJAM ALFANUMERICAS, OU SEJA, O PRIMEIRO IF SERVE PARA VERIFICAR ISSO
            if(!(asciPalavra>=65 && asciPalavra<=90)){
                stringCrip += palavra.charAt(i);
            }
            else{
                int cripAuxiliar = asciChave + (asciPalavra-65);
            
                if (cripAuxiliar > 90){//maior valor de letra maiuscula da tabela ascii é 90, ou seja, precisa "resetar" caso o if seja true
                    cripAuxiliar = (cripAuxiliar-90) + 65;//pega o quanto sobrou e soma com o valor inicial
                }
                stringCrip += String.valueOf((char)cripAuxiliar);
            }
            
        }
    }

    /*Método para fazer o valor da String auxiliar ser do tamanho da palavra que precisa ser criptografada */
    public void formataAux(String palavra){
        chaveAux = "";//reseta o valor da chave aux para string nula
        int j = 0;
        for (int i=0;i<palavra.length();i++){
            if (j == chave.length()){
                j = 0;
            }
            chaveAux += chave.charAt(j);
            j++;
        }
 
    }

    /*Método para escrever no arquivo */
    public void escreveArq() throws IOException{
        dos.writeUTF(stringCrip);
    }

    /*Método para tratar toda a string e convertê-la em Maiuscula */
    public String trataString(String valor){
        String x = "";
        for (int i=0;i<valor.length();i++){
            int aux = valor.charAt(i);
            if (!(aux>=97 && aux <=122) && !(aux>=65 && aux <=90)){
                x += valor.charAt(i);
            }
            else{
                char auxc = Character.toUpperCase(valor.charAt(i));
                x += auxc;
                
            }
        }
        return x;
    }

}

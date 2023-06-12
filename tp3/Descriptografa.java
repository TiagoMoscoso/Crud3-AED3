import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class Descriptografa {
    public RandomAccessFile rnd;
    public DataOutputStream dos;
    public FileOutputStream fil;
    private String chave = "CARO";
    private String chaveAux = "";
    public Descriptografa() throws FileNotFoundException{
        File arq = new File("descripto.db");
        if (arq.exists()){
            arq.delete();
        }
        fil = new FileOutputStream("descripto.db",true);
        dos = new DataOutputStream(fil);
        rnd = new RandomAccessFile("cripto.db", "r");
        try{
            dos.writeInt(rnd.readInt());//escreve o cabecalho
            while(true){
                dos.writeInt(rnd.readInt());//escreve o indicador de tamanho
                dos.writeUTF(rnd.readUTF());//escreve A lapide
                dos.writeInt(rnd.readInt());//escreve o ID
                for (int i=0;i<9;i++){
                    String palavra = descp(rnd.readUTF());
                    dos.writeUTF(palavra);
                }
                dos.writeUTF(rnd.readUTF());//escreve o separador
            }
        }catch(Exception e){
            System.out.println("Descriptografado!");
        }
    }

    /*Método para fazer a descriptografia */
    private String descp(String palavra){
        formatKey(palavra);

        String decript = "";
        for (int i=0;i<palavra.length();i++){
            int asciChave = chaveAux.charAt(i);
            int asciPalavra = palavra.charAt(i);

            //NAO IREMOS CRIPTOGRAFAR CARACTERES QUE NAO SEJAM ALFANUMERICAS, OU SEJA, O PRIMEIRO IF SERVE PARA VERIFICAR ISSO
            if(!(asciPalavra>=65 && asciPalavra<=90)){
                decript += palavra.charAt(i);
            }
            else{
                int aux = asciPalavra - asciChave;
                if (aux<0){
                    aux = aux + 26 + 65 - 1;
                }
                else{
                    aux += 65;
                }
                decript += String.valueOf((char)aux);
                
            }
            
        }
        return decript;
    }
    /*Formata a chave, para ficar de acordo com o tamanho da palavra */
    private void formatKey(String palavra){
        chaveAux = "";
        int j =0;
        for (int i=0;i<palavra.length();i++){
            if (j==chave.length()){
                j = 0;
            }
            chaveAux+= chave.charAt(j);
            j++;
        }
    }
}

import java.io.IOException;
import java.io.RandomAccessFile;

public class Read extends ArquivoIndice{
    public Read(int id) throws IOException{
            leitura(id);    
    }


    private void leitura(int id) throws IOException{
        long posicao = procura_indice(id);
        RandomAccessFile bd = new RandomAccessFile("netflix.db", "r");
        bd.seek(posicao);
        bd.readInt();//lê indicador de tamanho
        if(bd.readUTF().equals("*")){
            System.out.println("O registro foi apagado da BD");
        }
        else{
            System.out.println("ID: "+bd.readInt());
            System.out.println("Tipo: "+bd.readUTF());
            System.out.println("Titutlo: "+bd.readUTF());
            System.out.println("Diretor: "+bd.readUTF());
            System.out.println("Elenco: "+bd.readUTF());
            System.out.println("Pais: "+bd.readUTF());
            System.out.println("Data Adicionada: "+bd.readUTF());
            System.out.println("Ano de lancamento: "+bd.readUTF());
            System.out.println("Avaliacao: "+bd.readUTF());
            System.out.println("Duracao: "+bd.readUTF());
            bd.close();
        }
    }   
}

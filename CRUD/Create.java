import  java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


public class Create extends TableInfo{
    
    /*Construtor que irá receber por parâmetro os valores que o usuário quer entrar,
     * lê o último índice que foi usado, que está no cabecalho e escreve no arquivo
     * através do DataOutputStream, usando o modo APPEND
     */
    
    Create(String tipo, String titulo, String diretor, String elenco, String pais,
    String ano_add, String ano_lanc, String avaliacao, String duracao) throws FileNotFoundException{
        
        RandomAccessFile leitor = new RandomAccessFile("netflix.db", "rw");
        
        FileOutputStream fout= new FileOutputStream("netflix.db", true);
        DataOutputStream dout = new DataOutputStream(fout);

        

        try{
            
            byte[] vet_byte;
            leitor.seek(0);
            
            int id_atual = leitor.readInt() + 1;
            
            leitor.seek(0);
            leitor.writeInt(id_atual);//Atualiza no cabecalho o ID

            String id_str = String.valueOf(id_atual);
            
            String[] vet_entradas = {id_str, tipo,titulo,diretor, elenco, pais, ano_add, ano_lanc, avaliacao, duracao};
            
            setALL(vet_entradas);
            vet_byte = converte_bytearray();//inserir os elementos dentro do vetor de bytes, como bytes
            dout.writeInt(vet_byte.length);//Escreve o tamanho do registro
            dout.write(vet_byte);//Escreve todo o registro, com indicador de tamanho
           
            dout.close();
            leitor.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }
}




import java.io.IOException;
import java.io.RandomAccessFile;

public class Delete extends TableInfo{
    Delete(int id) throws IOException{
        RandomAccessFile arq = new RandomAccessFile("netflix.db", "rw");
        arq.seek(0);
        arq.readInt();//Pula a leitura do cabecalho
        while (true){
            try{
                int leitor_id;
                int tamanho_registro;
                long ponteiro_atual = arq.getFilePointer();
                tamanho_registro = arq.readInt();
                Lapide = arq.readUTF();//verifica a lápide, pois as vezes o arquivo ja pode ter sido apagado
                leitor_id = arq.readInt();//lê o ID

                if (Lapide.compareTo("*")!=0){
                    if (leitor_id==id){
                        ponteiro_atual = arq.getFilePointer();
                        arq.seek(ponteiro_atual-7);
                        arq.writeUTF("*");
                        System.out.println("Registro apagado com sucesso!");
                        System.out.println("Regristo apagado: ");
                        System.out.println("ID: "+arq.readInt());
                        System.out.println("Tipo: "+arq.readUTF());
                        System.out.println("Titulo: "+arq.readUTF());
                        System.out.println("Diretor: "+arq.readUTF());
                        System.out.println("Elenco: "+arq.readUTF());
                        System.out.println("Pais: "+arq.readUTF());
                        System.out.println("Ano Adicionado: "+arq.readUTF());
                        System.out.println("Ano lancado: : "+arq.readUTF());
                        System.out.println("Avaliacao: "+arq.readUTF());
                        System.out.println("Duracao: "+arq.readUTF());
                        break;
                    }
                }

                ponteiro_atual = arq.getFilePointer()-7;
                arq.seek(ponteiro_atual);
                arq.seek(ponteiro_atual+tamanho_registro);

            }catch(Exception EOFException){
                arq.close();
                System.out.println("Nao existem registros com esse ID para serem apagados.");
                break;
            }
        }
        
    }
}

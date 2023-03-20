import java.io.IOException;
import java.io.RandomAccessFile;

public class Read extends TableInfo{
    RandomAccessFile arq;
    Read(int id) throws IOException{
        
        arq = new RandomAccessFile("netflix.db", "r");
        arq.seek(0);
        arq.readInt();//Pula a leitura do cabecalho
        leitura(id);
        

    }

    private void leitura(int id)throws IOException{
        while(true){
            
            int leitor_id;
            int tamanho_registro;
            long ponteiro_atual = arq.getFilePointer();
            

            try{
                tamanho_registro = arq.readInt();
                Lapide = arq.readUTF();//lê a lápide
                leitor_id = arq.readInt();//lê o ID

                if (Lapide.compareTo("*")!=0){

                    if (leitor_id==id){
                        System.out.println("ID: "+id);
                        System.out.println("Tipo: "+arq.readUTF());
                        System.out.println("Titulo: "+arq.readUTF());
                        System.out.println("Diretor: "+arq.readUTF());
                        System.out.println("Elenco: "+arq.readUTF());
                        System.out.println("Pais: "+arq.readUTF());
                        System.out.println("Ano Adicionado: "+arq.readUTF());
                        System.out.println("Ano lancado: : "+arq.readUTF());
                        System.out.println("Avaliacao: "+arq.readUTF());
                        System.out.println("Duracao: "+arq.readUTF());
                        arq.close();
                        break;
                    }
                }
                ponteiro_atual = arq.getFilePointer()-7;
                
                arq.seek(ponteiro_atual);//reseta a posicao do ponteiro para o primeiro indice do registro
                arq.seek(ponteiro_atual+tamanho_registro);//coloca o ponteiro exatamente no indicador de tamanho do proximo registro

            }catch(Exception EOFException){
                arq.close();
                System.out.println("ID não encontrado");
                break;
            }
        }
    }
        
}


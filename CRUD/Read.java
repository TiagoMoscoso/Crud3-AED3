import java.io.IOException;
import java.io.RandomAccessFile;

public class Read extends TableInfo{
    /*Construtor irá instanciar o atributo do RandomAcessFile que irá percorrer 
     * o arquivo atrás do ID que o usuário entrou*/
    public Read() throws IOException{
       /* RandomAccessFile arq;
        arq = new RandomAccessFile("netflix.db", "r");
        arq.seek(0);
        arq.readInt();//Pula a leitura do cabecalho
        leitura(arq, id);*/
        
    }
    /*Procedimento que irá Procurar o ID, pulando de registro em registro, com base
     * em seu tamanho
     */
    public static String[] leitura(int id) throws IOException{
        RandomAccessFile arq;
        
        arq = new RandomAccessFile("netflix.db", "r");
        arq.seek(0);
        arq.readInt();//Pula a leitura do cabecalho
        
        String lapide, tipo, titulo, diretor, elenco,
        pais, data_ad, ano_lancado, avaliacao, duracao;
        while(true){
            int leitor_id;
            int tamanho_registro;
            long ponteiro_atual = arq.getFilePointer();
            

            try{
                tamanho_registro = arq.readInt();
                lapide = arq.readUTF();//lê a lápide
                leitor_id = arq.readInt();//lê o ID

                if (lapide.compareTo("*")!=0){

                    if (leitor_id==id){
                        System.out.println("ID: "+id);
                        tipo = arq.readUTF();
                        System.out.println("Tipo: "+tipo);
                        titulo = arq.readUTF();
                        System.out.println("Titulo: "+titulo);

                        diretor = arq.readUTF();
                        System.out.println("Diretor: "+diretor);
                        elenco = arq.readUTF();
                        System.out.println("Elenco: "+elenco);
                        pais = arq.readUTF();
                        System.out.println("Pais: "+pais);
                        data_ad = arq.readUTF();
                        System.out.println("Ano Adicionado: "+data_ad);
                        
                        ano_lancado = arq.readUTF();
                        System.out.println("Ano lancado: : "+ano_lancado);
                        avaliacao = arq.readUTF();
                        System.out.println("Avaliacao: "+avaliacao);
                        duracao = arq.readUTF();
                        System.out.println("Duracao: "+duracao);
                        arq.close();
                        String[] aux = {tipo,titulo,diretor,elenco,pais,
                            data_ad,ano_lancado,avaliacao,duracao};
                        return aux;
                    }
                }
                ponteiro_atual = arq.getFilePointer()-7;
                
                arq.seek(ponteiro_atual);//reseta a posicao do ponteiro para o primeiro indice do registro(logo apos o indicador de tamanho)
                arq.seek(ponteiro_atual+tamanho_registro);//coloca o ponteiro exatamente no indicador de tamanho do proximo registro

            }catch(Exception EOFException){
                arq.close();
                System.out.println("ID não encontrado");
                String[] aux = {"-1"};
                return aux;
            }
        }
    }
 
    /*private void leitura(int id)throws IOException{
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
                        ID = id;
                        Type = arq.readUTF();
                        System.out.println("Tipo: "+Type);
                        Title = arq.readUTF();
                        System.out.println("Titulo: "+Title);

                        Director = arq.readUTF();
                        System.out.println("Diretor: "+Director);
                        Cast = arq.readUTF();
                        System.out.println("Elenco: "+Cast);
                        Country = arq.readUTF();
                        System.out.println("Pais: "+Country);
                        Date_Added = arq.readUTF();
                        System.out.println("Ano Adicionado: "+Date_Added);
                        
                        Release_Year = arq.readUTF();
                        System.out.println("Ano lancado: : "+Release_Year);
                        Rate = arq.readUTF();
                        System.out.println("Avaliacao: "+Rate);
                        Duration = arq.readUTF();
                        System.out.println("Duracao: "+Duration);
                        arq.close();
                        break;
                    }
                }
                ponteiro_atual = arq.getFilePointer()-7;
                
                arq.seek(ponteiro_atual);//reseta a posicao do ponteiro para o primeiro indice do registro(logo apos o indicador de tamanho)
                arq.seek(ponteiro_atual+tamanho_registro);//coloca o ponteiro exatamente no indicador de tamanho do proximo registro

            }catch(Exception EOFException){
                arq.close();
                System.out.println("ID não encontrado");
                break;
            }
        }
    }*/
        
}


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

public class Update extends TableInfo{
    private RandomAccessFile rnd;
    private int tamanho_registro;
    private long posicao;

    public Update(int id) throws IOException{
        ArquivoIndice arq = new ArquivoIndice();
        posicao = arq.procura_indice(id);
        if (posicao == 0){
            System.out.println("Nao há nenhum registro com esse ID");
        }
        else{
            rnd = new RandomAccessFile("netflix.db", "rw");
            rnd.seek(posicao);
            tamanho_registro = rnd.readInt();//lê o tamanho do registro
            if (rnd.readUTF().equals("*")){//verifica se o registro não é lápide
                System.out.println("Esse registro foi apagado, não há como atualizar");
            }
            else{
                updt();
            }
        }
        
        
    }

    private void updt() throws IOException{
        setID(rnd.readInt());
        Scanner sc = new Scanner(System.in);
        System.out.printf("Entre com o novo Tipo: ");
        setType(sc.nextLine());
        System.out.printf("\nEntre com o novo Título: ");
        setTitle(sc.nextLine());
        System.out.printf("\nEntre com o Diretor: ");
        setDirector(sc.nextLine());
        System.out.printf("\nEntre com o Elenco: ");
        setCast(sc.nextLine());
        System.out.printf("\nEntre com o pais: ");
        setCountry(sc.nextLine());
        System.out.printf("\nEntre com a nova data adicionada: ");
        setDate_Added(sc.nextLine());
        System.out.printf("\nEntre com o novo ano de lançamento: ");
        setRelease_Year(sc.nextLine());
        System.out.printf("\nEntre com a nova avaliação: ");
        setRate(sc.nextLine());
        System.out.printf("\nEntre com a nova Duração: ");
        setDuration(sc.nextLine());
        sc.close();
        atualiza_registro();
    }

    private void atualiza_registro() throws IOException{
        byte[] novo_registro = converte_bytearray();

        //verifica se o tamanho do registro atual é maior ou não que o antigo
        if (novo_registro.length<=tamanho_registro){
            //reseta o ponteiro
            rnd.seek(posicao);
            rnd.readInt();
            rnd.write(novo_registro);
        }
        else{
            //caso ele seja maior, iremos colocar a lápide no arquivo e escrever no final
            FileOutputStream fout = new FileOutputStream("netflix.db", true);
            DataOutputStream dout = new DataOutputStream(fout);
            //escreve a lapide
            rnd.seek(posicao);
            rnd.readInt();
            rnd.writeUTF("*");
            //escreve no arquivo, como append, sem precisar manipular o ponteiro, pois estamos usando DataOutputStream
            dout.writeInt(novo_registro.length);
            dout.write(novo_registro);
            fout.close();
            dout.close();
            //resetando o arquivo de indice, já que alteramos a posicao do registro que foi atualizado
            ArquivoIndice reseta = new ArquivoIndice();
            reseta.cria_arquivo();
        }
        

    }
}

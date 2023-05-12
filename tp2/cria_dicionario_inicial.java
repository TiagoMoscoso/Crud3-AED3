import java.io.FileOutputStream;
import java.io.DataOutputStream;

public class cria_dicionario_inicial {
    public void main(String[] args) {
        try {
            int id = 1;
            FileOutputStream fil = new FileOutputStream("dicionario.db");
            DataOutputStream dos = new DataOutputStream(fil);
            for (int i = 32; i < 127; i++) {
                String aux = String.valueOf((char) i);
                dos.writeInt(id);
                dos.writeUTF(aux);
                id++;
            }
            for (int i = 128; i < 169; i++) {
                String aux = String.valueOf((char) i);
                dos.writeInt(id);
                dos.writeUTF(aux);
                id++;
            }
            dos.close();
            fil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
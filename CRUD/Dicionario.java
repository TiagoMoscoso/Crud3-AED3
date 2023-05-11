import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File;

public class Dicionario{
    public static int main() {
        try {
            File f = new File("dicionario.db");
            f.delete();
            int id = 0;
            FileOutputStream fil = new FileOutputStream("dicionario.db");
            DataOutputStream dos = new DataOutputStream(fil);
            for (int i = 32; i < 127; i++) {
                char aux = (char) i;
                dos.writeInt(id);
                dos.writeUTF(String.valueOf(aux));
                id++;
            }
            for (int i = 128; i < 169; i++) {
                char aux = (char) i;
                dos.writeInt(id);
                dos.writeUTF(String.valueOf(aux));
                id++;
            }
            id--;
            dos.close();
            fil.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

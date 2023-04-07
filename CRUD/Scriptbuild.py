import os
import subprocess

#cria arquivos .class do java na versão 1.8, para poderem ser executadas no python jpype

class build():
    os.path.abspath(os.path.dirname(__file__))
    print("building ....")
    subprocess.call('javac -source 1.8 -target 1.8 Create.java')
    subprocess.call('javac -source 1.8 -target 1.8 Cria_Arquivo.java')
    subprocess.call('javac -source 1.8 -target 1.8 Delete.java')
    subprocess.call('javac -source 1.8 -target 1.8 Ordenacao_Externa.java')
    subprocess.call('javac -source 1.8 -target 1.8 Quick_Sort.java')
    subprocess.call('javac -source 1.8 -target 1.8 Read.java')
    subprocess.call('javac -source 1.8 -target 1.8 TableInfo.java')
    subprocess.call('javac -source 1.8 -target 1.8 Update.java')

    print("builded")
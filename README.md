# FundIA
Exercícos e trabalhos da disciplina de Fundamentos de Inteligência Artificial do PPGC da UFPel

O projeto esta escrito em Java com Maven, sendo operacional em qualquer ferramenta de desenvolvimento. Entretanto a 
ferramenta utilizada foi o Netbeans 8.1 e o JDK 1.8.0


java -jar BuscaExaustiva DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado


Para administrar o consumo de memória usar por exemplo:
      java -Xms7168m -Xmx8192m -jar BuscaExaustiva/target/BuscaExaustiva-1.0.jar IDS 3 40

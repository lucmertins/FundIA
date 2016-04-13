# FundIA
Exercícos e trabalhos da disciplina de Fundamentos de Inteligência Artificial do PPGC da UFPel

O projeto esta escrito em Java com Maven, sendo operacional em qualquer ferramenta de desenvolvimento. Entretanto a 
ferramenta utilizada foi o Netbeans 8.1 e o JDK 1.8.0

Heuristicas para A*
MANHATAN, HAMMING

java -jar BuscaExaustiva DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado heuristica


Para administrar o consumo de memória usar por exemplo:
      java -Xms6144m -Xmx6144m -jar BuscaExaustiva/target/BuscaExaustiva-1.0.jar ASTAR 3 400 MANHATAN 

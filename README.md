# FundIA
Exercícos e trabalhos da disciplina de Fundamentos de Inteligência Artificial do PPGC da UFPel

O projeto esta escrito em Java com Maven, sendo operacional em qualquer ferramenta de desenvolvimento. Entretanto a 
ferramenta utilizada foi o Netbeans 8.1 e o JDK 1.8.0



java -jar BuscaExaustiva/target/BuscaExaustiva DFS|BFS|IDS tamanhoTabuleiro vezesEmbaralhado heuristica


Para executar busca exaustiva:

      java -Xms6144m -Xmx6144m -jar BuscaExaustiva/target/BuscaExaustiva-1.0.jar IDS 3 400 5 false false

Onde:
1 parametro é o algoritmo:  DFS|BFS|IDS
2 parametro é o tamanho do tabuleiro
3 parametro é o número de movimentos aleatórios para embaralhar o tabuleiro
4 parametro é a tempo máximo de duração do algoritmo
5 paremetro é a solicitação para mostrar ou não o estado inicial após embaralhado
6 paremetro é a solicitação para mostrar ou não a solução encontrada



Para executar busca com Heurístic:

java -Xms6144m -Xmx6144m -jar BuscaComHeuristica/target/BuscaComHeuristica-1.0.jar ASTAR 3 40 5 false false MANHATAN

Onde:
1 parametro é o algoritmo:  ASTAR
2 parametro é o tamanho do tabuleiro
3 parametro é o número de movimentos aleatórios para embaralhar o tabuleiro
4 parametro é a tempo máximo de duração do algoritmo
5 paremetro é a solicitação para mostrar ou não o estado inicial após embaralhado
6 paremetro é a solicitação para mostrar ou não a solução encontrada
7 parametro é a heuristica MANHATAN|HAMMING




Para executar o Minimax

java -Xms6144m -Xmx6144m -jar BuscaCompetitiva/target/BuscaCompetitiva-1.0.jar MINIMAX 9

Onde:
1 parametro é o algoritmo:  MINIMAX
2 parametro é a profundidade máxima de busca
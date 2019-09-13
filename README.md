# Trabalho de Sistemas Operacionais
##  Ordenação paralela de uma lista encadeada.

### Ambiente

- S.O. Máquina MACOS High Sierra 10.13.6
- Java 1.8
- IDE Intellij

### Descrição do problema

Neste trabalho, o aluno terá que ler números inteiros de vários
arquivos, adicioná-los em uma lista encadeada na memória, ordená-la e
por último escrever a lista em um arquivo. O objetivo é usar
paralelismo sempre que possível para diminuir o tempo de execução.

##### Entrada do programa:

A entrada fornecida ao programa serão 10 arquivos contendo
listas de inteiros gerados randomicamente. Cada arquivo de ter, no
mínimo 1mil inteiros. Depois, altere a quantidade de números para 5mil
inteiros e 10mil inteiros.

Os 10 arquivos terão todos a mesma quantidade de inteiros. Serão
nomeados da seguinte forma: arquivo0.txt, arquivo1.txt, ... ,
arquivo9.txt. Eles devem estar no mesmo diretório do programa.

##### Primeira Etapa - Leitura dos Arquivos:

O programa deve criar 10 threads, um para cada arquivo. Cada thread
deve ler os inteiros do arquivo e adicioná-los na lista encadeada. A
lista é a mesma para todos os threads. Os threads irão concorrer pelo
acesso à lista, isso deve ser controlado.

Obs 1. Os números devem ser gerados ser nenhum tipo de ordenação e a
inserção na lista completa não deve ser ordenada.

##### Segunda Etapa - Ordenação:

O programa deve receber como entrada a quantidade de threads que será
utilizada. Ele então fazer a ordenação da lista
encadeada em paralelo. Pode ser usado qualquer algoritmo de ordenação.
Lembrem-se que nem sempre o melhor algoritmo serial será o melhor
paralelo. Ao final dessa etapa, a lista encadeada deve estar ordenada
na memória.

##### Terceira Etapa - Análise de Desempenho da Ordenação
O programa deve começar utilizando apenas uma thread e ordenar toda a
lista. A seguir, executar novamente o problema agora com duas threads.
Repita essa operação, aumentando de 2 em 2 threads, a quantidade de
threads de ordenação. O valor máximo da quantidade de Threads deve ser 20.

##### Saída do programa:

Com a lista ordenada, o programa deve escrevê-la em um arquivo chamado
chamado output.txt. Em teoria, o tamanho de output.txt deve
ser o somatório do tamanhos dos arquivos de entrada.

Obs 2. Para as etapas 2 e 3, em todas as execuções, seu programa deve
calcular o tempo total de ordenação. Desconsidere os tempos de leitura
dos arquivos na fase de entrada do programa, e também os tempos de
escrita no arquivo na fase de saída do programa. Seu programa deve
calcular apenas o tempo de ordenação, considerando as diferentes
quantidades de threads a serem utilizadas.

### Execução 

#### Requisitos

- JDK 1.8
- Gradle

#### Instruções

Adicionar uma lista de arquivos na pasta `./input` com os valores que serão
 utilizados para popular a lista encadeada. O Formato do arquivo deverá ser de um valor por linha.
 
O build pode ser feito com o commando ``./gradlew build``.
A execução é feita com o comando ``./gradlew run``.

Após a execução será criado um arquivo ``./output.txt`` com os valores ordenados.
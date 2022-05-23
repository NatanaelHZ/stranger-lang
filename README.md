# Stranger-lang

Projeto: Linguagem de programação

Matéria: Programação I (4ª fase)

Ciência da Computação - Universidade Federal da Fronteira Sul

### Exigências do Projeto
<br>Implementar um programa em Java que interprete uma linguagem de
programação criada pelos alunos. O programa deve funcionar da seguinte forma pela linha de comando:</br>

```Java

java NomePrograma arquivo.ext

```
sendo que o parâmetro arquivo.ext será o arquivo a ser interpretado pelo programa, que pode ter qualquer
nome e qualquer extensão, a critério dos alunos. O interpretador deve ser capaz de analisar e reagir corretamente
às seguintes situações no arquivo fonte que ele esteja interpretando:

* Declaração de variáveis;
* Atribuição de valor a variável;
* Expressões com no mínimo dois operandos, ex.: a + b;
* Operações de adição, subtração, divisão e multiplicação;
* Laço;
* Comando de saída, ex.: mostrar algo na tela;
* Controlador de fluxo, ex.: if;
* Aninhamento de comandos, ex.: ifs dentro de ifs, laços dentro de laços.

# Arquivos

<p> Os arquivos a serem interpretados devem ser salvo na pasta exemplos com a extensão .stg, por exemplo, nomeDoArquivoInterpretado.stg </p>

# Executando .jar

<p> Salve o arquivo a ser interpretado na pasta exemplos, no diretório fonte execute em sequencia os seguinte comandos no terminal:</p>

```Java
jar -cmvf manifest.txt  Stranger.jar *.class
java -jar Stranger.jar
java -jar Stranger.jar ../exemplos/nomeDoArquivoInterpretado.stg

```

# Problema com a versão do java no laboratório

<p> Caso ocorra algum problema com a versão do java execute os comandos:</p>

```Java
/usr/lib/jvm/java-11-openjdk-amd64/bin/jar -cmvf manifest.txt  Stranger.jar *.class
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar Stranger.jar
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar Stranger.jar ../exemplos/nomeDoArquivoInterpretado.stg

```



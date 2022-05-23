# Manual Stranger-lang

# Arquivos

<p> Os arquivos a serem interpretados devem ser salvo na pasta exemplos com a extensão .stg, por exemplo, nomeDoArquivoInterpretado.stg </p>

# Executando .jar

<p> Salve o arquivo a ser interpretado na pasta exemplos, no diretório fonte execute em sequencia os seguinte comandos no terminal:

```Java
jar -cmvf manifest.txt  Stranger.jar *.class
java -jar Stranger.jar
java -jar Stranger.jar ../exemplos/nomeDoArquivoInterpretado.stg

```

# Problema com a versão do java no laboratório

<p> Caso ocorra algum problema com a versão do java execute os comandos:

```Java
/usr/lib/jvm/java-11-openjdk-amd64/bin/jar -cmvf manifest.txt  Stranger.jar *.class
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar Stranger.jar
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar Stranger.jar ../exemplos/nomeDoArquivoInterpretado.stg

```

## Sintaxe

### Variáveis

<p>  Na linguagem Stranger-lang, há a necessidade de declarar as variáveis antes da execução do método principal, no início do programa declara-se as variáveis no escopo iniciado por “vars:”, seguidos de uma quebra de linha e uma indentação. Utiliza-se o símbolo “$”  para indicar uma variável, seguido pelo nome desejado, pelo símbolo que recebe “=”, e pelo valor, por exemplo, “$a = 0”. É aceito somente a declaração de números inteiros. Após declarar todas as variáveis que serão utilizadas no decorrer do programa (incluindo as várias usadas nos condicionais e nos laços de repetições), finaliza-se a declaração com o comando “end_vars;”.</p>

```Java

vars:
    $a = 30;
    $b = 0;
    $rocket = 2;
    $Iron = 10;
end_vars;

```

### Main

<p>  Inicia-se o método principal da linguagem com "stanger:", onde ocorrem todas funcionalidades, como looping, condicional, mostra na tela e operações matemáticas. O comandos dados após o inicio devem ser identados, encerra-se o processo com "end_strager".</p>

```Java

stranger:

	loop(4)
		$groot = $groot + 1;
		show("grootANDloop: ");
		showln($groot);
		loop(3)
			$rocket = $rocket + 2;
			showln($rocket);
		end_loop;

	end_loop;

	showln($groot);
	showln($rocket);

end_stranger;

```
### Mostra na Tela

<p>  Para exibir informações na tela utiliza-se o comando "show();", sem quebra de linha, "showln();", com quebra de linha. Pode ser exibido uma string, com o uso de aspas, "showln("mostra na tela"), há possibilidade de mostrar as variáveis, "showln($rocket)", além disso, operações matemáticas com variáveis podem ser realizadas, "showln($rocket + $Iron)". Os comandos são finalizado com ponto e vírgula.

```Java
stranger:
    show("mostra na tela sem quebra de linha");
    showln("mostra na tela com quebra de linha");
    showln($rocket);
    show($rocket + $Iron);
end_strager;

```
### Operações Matemáticas

<p>  Dentro da método "stranger:" é possível realizar cálculos por meio de alguns comando.</p>

#### Adição e Subtração

<p>  Basta apenas usar o operador '+' ou '-', permitindo a soma ou a subtração de duas variáveis já declaradas, ou até mesmo essas operações com dois números, armazenando o valor resultante em outra variável, que também deve ser previamente declarada e inicializada com zero.</p>

```Java

vars: 
	$result = 0;
	$soma = 0;
	$subtrai = 0;
	$a = 10;
	$b = 2;
end_vars;

stranger:
	$result = $a + $b;
	$soma = 2 + 4;
	$subtrai = $a - $b;

end_stranger;

```

#### Multiplicação e divisão

<p>  Basta apenas usar o operador '*' ou '/', permitindo a multiplicação ou a divisão de duas variáveis já declaradas, ou até mesmo essas operações com dois números, armazenando o valor resultante em outra variável, que também deve ser previamente declarada e inicializada com zero.</p>

```Java

vars: 
	$result = 0;
	$multi = 0;
	$div = 0;
	$a = 10;
	$b = 2;
end_vars;

stranger:
	$result = $a * $b;
	$multi = 2 * 4;
	$div = $a / $b;

end_stranger;

```
#### Números Primos

<p>  A função "cousin()" permite o cálculo de números primos, apenas passando um número ou uma variável como parâmetro para a função.</p>

```Java
var:
	$a = 37;
end_vars;

stranger:
	cousin(7);
	cousin($a);

end_stranger;

```

#### Raíz

<p>  A função "sqrt()" permite o cálculo de raízes, apenas passando um número ou uma variável como parâmetro para a função.</p>

```Java
var:
	$a = 16;
end_vars;

stranger:
	sqrt(4);
	sqrt($a);

end_stranger;

```
#### Resto da Divisão

<p> Ultilize o % para saber o resto da divisão de dois números</p>

```Java
vars:
	$a = 0;

end_vars;

stranger:
	$a = 4 % 2;
	showln($a);

end_stranger;

```

### Looping

<p>  O looping aceita apenas um valor como parâmetro, que definirá a quantidade de vezes que o looping ocorrerá, os quais também podem ser aninhados.

```Java

vars: 
	$Iron = 10;
	$group = 39;
	$fine = 16;

	$groot = 0;
	$rocket = 2;
end_vars;

stranger:

	loop(4)
		$groot = $groot + 1;
		show("grootANDloop: ");
		showln($groot);
		loop(3)
			$rocket = $rocket + 2;
			showln($rocket);
		end_loop;

	end_loop;

end_stranger;

```
### Condicional

<p> O condicional é efetuado pelo comando "if()", que pode testar números ou variáveis maiores, menores. O condicional também pode ser aninhado. O comando é finalizado por "end_if".

```Java

vars:
	$a = 4;
	$b = 7;

end_vars;

stranger:

	if($a < $b)
		showlln("a é menor que b");
	end_if;

	if(7 > 2)
		showln("abobrinha");
	end_if;
 
end_stranger;

```


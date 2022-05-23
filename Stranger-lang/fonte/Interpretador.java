class Interpretador {
		private Variavel[] variaveis = new Variavel[40];
		private String[] linhas = new String[400];
		private int linhaAtual;
		private Mathematic math = new Mathematic();
		private Print print = new Print();
		private Condicional condicional = new Condicional();

 
		public Interpretador() {
			this.linhaAtual = 0;
		}

		public void analizador() {
			for (int i = 0; i < this.linhas.length; i++, this.linhaAtual++) {
				if (linhas[i] == null) break;

				if (linhas[i].contains("vars:")) this.inicializadorVariaveis();

				if (linhas[i].contains("stranger:")) this.stranger();
			}
		}

		private void stranger() {
			for (int i = (this.linhaAtual + 1); i < this.linhas.length; i++, this.linhaAtual++) {
				if ( this.linhas[i] == null || this.linhas[i].contains("end_stranger;")) break;

				if (this.linhas[i].contains("+")) this.analizadorOperacaoMatematica(this.linhas[i], '+');
				if (this.linhas[i].contains("-")) this.analizadorOperacaoMatematica(this.linhas[i], '-');
				if (this.linhas[i].contains("*")) this.analizadorOperacaoMatematica(this.linhas[i], '*');
				if (this.linhas[i].contains("/")) this.analizadorOperacaoMatematica(this.linhas[i], '/');
				if (this.linhas[i].contains("%")) this.analizadorOperacaoMatematica(this.linhas[i], '%');
				if (this.linhas[i].contains("cousin(")) this.analizadorOperacaoNumeroPrimo(this.linhas[i]);
				if (this.linhas[i].contains("sqrt(")) this.analizadorOperacaoRaizQuadrada(this.linhas[i]);
				if (this.linhas[i].contains("if(")) {
					i += this.analizadorCondiconal(this.linhas[i], i);
					this.linhaAtual = i;
				} 
				
				if (this.linhas[i].contains("show(")) this.print.show(this.analizadorMostraNaTela(this.linhas[i]));
				if (this.linhas[i].contains("showln(")) this.print.showln(this.analizadorMostraNaTela(this.linhas[i]));

				if (this.linhas[i].contains("loop(")) {
					i += this.analizadorDeLoops(this.linhas[i], i);
					this.linhaAtual = i;
				} 
			}
		}

		private void loop(String[] linhas, int range) {
			for (int n = 0; n < range; n++) {
				for (int i = 0; i < linhas.length; i++) {
					if ( linhas[i] == null) break;

					if (linhas[i].contains("+")) this.analizadorOperacaoMatematica(linhas[i], '+');
					if (linhas[i].contains("-")) this.analizadorOperacaoMatematica(linhas[i], '-');
					if (linhas[i].contains("*")) this.analizadorOperacaoMatematica(linhas[i], '*');
					if (linhas[i].contains("/")) this.analizadorOperacaoMatematica(linhas[i], '/');
					if (linhas[i].contains("%")) this.analizadorOperacaoMatematica(linhas[i], '%');
					if (linhas[i].contains("cousin(")) this.analizadorOperacaoNumeroPrimo(linhas[i]);

					if (linhas[i].contains("show(")) this.print.show(analizadorMostraNaTela(linhas[i]));
					if (linhas[i].contains("showln(")) this.print.showln(analizadorMostraNaTela(linhas[i]));
					if (linhas[i].contains("if(")) {
						i += this.analizadorCondiconalNoLoop(linhas, linhas[i], i);
					} 

					if (linhas[i].contains("loop(")) {
						i += this.analizadorDeLoops(linhas[i], (i+this.linhaAtual+2));
					}
				}
			}
		}

		private int identificaRange(String linha) {
			String valor = new String();
			int iniciaValor = 0;
			int range;
			double rangeDouble;

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ')') break;

				if (iniciaValor == 1 ) valor = valor + linha.charAt(i);
				if (linha.charAt(i) == '(') iniciaValor = 1;
			}

			rangeDouble = this.diferenciaNumeroDeVarivael(valor);
			range = (int) rangeDouble;

			return range;
		}

		private int analizadorDeLoops(String linha, int linhaAtual) {
			String[] linhasLoop = new String[50];
			int numeroLoops = 0;
			linhaAtual++;

			for (int i = 0; i < linhas.length; i++, linhaAtual++) {

				if (this.linhas[linhaAtual].contains("loop(")) numeroLoops += 1;
				if (this.linhas[linhaAtual].contains("end_loop;")) numeroLoops -= 1;

				if ( (this.linhas[linhaAtual].contains("end_loop;")) && (numeroLoops <= 0) ) {
					break;
				}

				linhasLoop[i] = this.linhas[linhaAtual];
			}

			this.loop(this.vetorSemNull(linhasLoop), this.identificaRange(linha));
			
			return this.vetorSemNull(linhasLoop).length;
		}

		private String[] vetorSemNull(String[] linhas) {
			int tamanho;

			for (tamanho = 0; tamanho < linhas.length; tamanho++) {
				if (linhas[tamanho] == null) break;
			}

			String[] linhasLoop = new String[tamanho];

			for (int n = 0; n < linhasLoop.length; n++) {
				if (linhas[n] == null) break;
				linhasLoop[n] = linhas[n]; 
			}

			return linhasLoop;
		}

		private String analizadorMostraNaTela(String linha) {
			int iniciaValor = 0;
			String valor = new String();

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ')') break;

				if (iniciaValor == 1) valor = valor + linha.charAt(i);
				if (linha.charAt(i) == '(') iniciaValor = 1;
			}

			if (linha.contains("\"")) {
				return valor.replaceAll("\"", "");
			}

			return Double.toString(this.diferenciaNumeroDeVarivael(valor));
		}

		private void analizadorOperacaoNumeroPrimo(String linha) {
			int iniciaValor = 0;
			String valor = new String();
			double valorVerificado;

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ')') break;

				if (iniciaValor == 1) valor = valor + linha.charAt(i);
				if (linha.charAt(i) == '(') iniciaValor = 1;
			}

			valorVerificado = this.diferenciaNumeroDeVarivael(valor);
			this.encontraVariavelMudaValor(linha).setValor(this.math.cousin(valorVerificado));
			
		}

		private void analizadorOperacaoRaizQuadrada(String linha){
			int iniciaValor = 0;
			String valor = new String();
			double valorVerificado;

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ')') break;

				if (iniciaValor == 1) valor = valor + linha.charAt(i);
				if (linha.charAt(i) == '(') iniciaValor = 1;
			}

			valorVerificado = this.diferenciaNumeroDeVarivael(valor);
			this.encontraVariavelMudaValor(linha).setValor(this.math.raiz(valorVerificado));
		}

		private int analizadorCondiconal(String linha, int linhaAtual){
			double valor1 = 0;
			double valor2 = 0;
			int resultado = 0;
			linhaAtual++;
			for (int i = 0; i < linha.length(); i++){
				if (linha.charAt(i) == ';') break;

				if (linha.charAt(i) == '(') valor1 = this.encotraPrimeiroValorCondicional(linha);

				if (linha.charAt(i) == '>' || linha.charAt(i) == '<') valor2 = this.encontraSegunoValorCondicional(linha);
			}

			if(linha.contains(">")) {
				if (linha.contains("=")) {
					resultado = this.condicional.maiorIgual(valor1, valor2);
				} else {
					resultado = this.condicional.maior(valor1, valor2);
				}	
			}
			
			if(linha.contains("<")) {
				if (linha.contains("=")) {
					resultado = this.condicional.menorIgual(valor1, valor2);
				} else {
					resultado = this.condicional.menor(valor1, valor2);
				}	
			}
			
			if (resultado == 0) {
				return this.verificaConteudoCondicional(linhaAtual);
			}

			return 0;
		}

		private int analizadorCondiconalNoLoop(String[] linhas ,String linha, int linhaAtual){
			double valor1 = 0;
			double valor2 = 0;
			int resultado = 0;
			linhaAtual++;
			for (int i = 0; i < linha.length(); i++){
				if (linha.charAt(i) == ';') break;

				if (linha.charAt(i) == '(') valor1 = this.encotraPrimeiroValorCondicional(linha);

				if (linha.charAt(i) == '>' || linha.charAt(i) == '<') valor2 = this.encontraSegunoValorCondicional(linha);
			}

			if(linha.contains(">")) {
				if (linha.contains("=")) {
					resultado = this.condicional.maiorIgual(valor1, valor2);
				} else {
					resultado = this.condicional.maior(valor1, valor2);
				}	
			}
			
			if(linha.contains("<")) {
				if (linha.contains("=")) {
					resultado = this.condicional.menorIgual(valor1, valor2);
				} else {
					resultado = this.condicional.menor(valor1, valor2);
				}	
			}
			
			if (resultado == 0) {
				return this.verificaConteudoCondicionalNoLoop(linhas, linhaAtual);
			}

			return 0;
			}

		private int verificaConteudoCondicional(int linhaAtual) {
			int retorno = 1;
			int numCondicionais = 1;
			for (int i = linhaAtual; i < this.linhas.length; i++) {
				if (this.linhas[i].contains("if(")) numCondicionais++;
				if (this.linhas[i].contains("end_if;")) numCondicionais--;
				if (this.linhas[i].contains("end_if;") && numCondicionais == 0) {
				 	break;
				}

				retorno += 1;
			}
		
			return retorno;
		}

		private int verificaConteudoCondicionalNoLoop(String[] linhas, int linhaAtual) {
			int retorno = 1;
			int numCondicionais = 1;
			for (int i = linhaAtual; i < linhas.length; i++) {
				if (linhas[i].contains("if(")) numCondicionais++;
				if (linhas[i].contains("end_if;")) numCondicionais--;
				if (linhas[i].contains("end_if;")) {
				 	break;
				}

				retorno += 1;
			}
		
			return retorno;
		}

		private void analizadorOperacaoMatematica(String linha, char operacao) {
			Double resultado;
			switch (operacao) {
			case '+':
			resultado = this.math.sum(this.encontraValorPrimeiroOperando(linha),
						this.encontraValorSegundoOperando(linha));
			break;

			case '-':
			resultado = this.math.less(this.encontraValorPrimeiroOperando(linha),
						this.encontraValorSegundoOperando(linha));
			break;

			case '*':
			resultado = this.math.mutiplication(this.encontraValorPrimeiroOperando(linha),
						this.encontraValorSegundoOperando(linha));
			break;

			case '/':
			resultado = this.math.division(this.encontraValorPrimeiroOperando(linha),
						this.encontraValorSegundoOperando(linha));
			break;

			case '%':
			resultado = this.math.resto(this.encontraValorPrimeiroOperando(linha),
						this.encontraValorSegundoOperando(linha));
			break;

			default:
				resultado = 0.0;
			}

			this.encontraVariavelMudaValor(linha).setValor(resultado);
		}

		private Variavel encontraVariavelMudaValor(String linha) {
			return this.buscarVarPeloNome(this.encontraNomeVariavel(linha));
		}

		private Double encotraPrimeiroValorCondicional(String linha){
			int iniciaOperando = 0;
			double valorOperando = 0;
			String operando = new String();
			Variavel var = new Variavel();
			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == '>' || linha.charAt(i) == '<') break;

				if (iniciaOperando == 1) operando = operando + linha.charAt(i);
				if (linha.charAt(i) == '(') iniciaOperando = 1;
			}

			valorOperando = this.diferenciaNumeroDeVarivael(operando);

			return valorOperando;
		}

		private Double encontraValorPrimeiroOperando(String linha) {
			int iniciaOperando = 0;
			double valorOperando = 0;
			String operando = new String();
			Variavel var = new Variavel();

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == '+' || linha.charAt(i) == '-' || linha.charAt(i) == '*' ||
					linha.charAt(i) == '/' || linha.charAt(i) == '%') break;

				if (iniciaOperando == 1) operando = operando + linha.charAt(i);
				if (linha.charAt(i) == '=') iniciaOperando = 1;
			}

			valorOperando = this.diferenciaNumeroDeVarivael(operando);

			return valorOperando;
		}

		private Double diferenciaNumeroDeVarivael(String linha) {
			double valor;

			if (linha.contains("$")) {
				linha = linha.trim().replaceAll(" ", "");
				valor = this.buscarVarPeloNome(linha).getValor();
			} else {
				valor = Double.parseDouble(linha.trim().replaceAll(" ", ""));
			}

			return valor;
		}

		private Double encontraSegunoValorCondicional(String linha){
			int iniciaOperando = 0;
			double valorOperando = 0;
			String operando = new String();
			Variavel var = new Variavel();

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ')') break;

				if (iniciaOperando == 1) operando = operando + linha.charAt(i);
				if (linha.charAt(i) == '>' || linha.charAt(i) == '<') iniciaOperando = 1;
			}

			valorOperando =this.diferenciaNumeroDeVarivael(operando);
			
			return valorOperando;
		}

		private Double encontraValorSegundoOperando(String linha) {
			int iniciaOperando = 0;
			double valorOperando = 0;
			String operando = new String();
			Variavel var = new Variavel();

			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ';') break;

				if (iniciaOperando == 1) operando = operando + linha.charAt(i);
				if (linha.charAt(i) == '+' || linha.charAt(i) == '-' || linha.charAt(i) == '*' ||
					linha.charAt(i) == '/' || linha.charAt(i) == '%') iniciaOperando = 1;
			}

			valorOperando =this.diferenciaNumeroDeVarivael(operando);
			
			return valorOperando;
		}

		private void inicializadorVariaveis() {
			Variavel variavelAuxiliar = new Variavel();
			for (int i = (this.linhaAtual + 1); i < linhas.length; i++) {
				if (linhas[i] == null || linhas[i].contains("end_vars;") ) break;
				if (linhas[i].contains("$")) {
					variavelAuxiliar.setNome( this.encontraNomeVariavel(linhas[i]) );
					variavelAuxiliar.setValor( this.encontraValorVariavel(linhas[i]) );
					this.adicionaVariavel(variavelAuxiliar);
				}
			}
		}

		private String encontraNomeVariavel(String linha) { 
			int count = 0;
			String teste = new String();
			String nome = new String();
			while (linha.charAt(count) != '=') { 
				nome = nome + linha.charAt(count);
				count++;
			}

			return nome.trim().replaceAll(" ", "");
		}

		private double encontraValorVariavel(String linha) {
			int iniciaValor = 0;
			String valor = new String();
			String valorAux = new String();
			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ';') break;
				if (iniciaValor == 1) valor = valor + linha.charAt(i);
				if (linha.charAt(i) == '=') iniciaValor = 1;
			}
		
			return Double.parseDouble(valor.trim().replaceAll(" ", ""));
		}
		
		public void mostraListaVariaveis() {
			for (int i = 0; i < this.variaveis.length; i++ ) {
				if (variaveis[i] != null) {
					System.out.println("Numero de char " + variaveis[i].getNome().length() + " " +
					variaveis[i].getNome() + " = " + variaveis[i].getValor());
				}
				
			}
		}

		public void setLinhas(String l) {
			for (int i = 0; i < linhas.length; i++) {
				if (linhas[i] == null) {
					linhas[i] = l;
					break;
				}
			}
		}

		public void mostrarLinhas() {
			for (int i = 0; i < linhas.length; i++) {
				if (linhas[i] != null) {
					System.out.println(linhas[i]);
				}
			}
		}

		public Variavel buscarVarPeloNome(String n) {
			for (int i = 0; i < this.variaveis.length; i++ ) {
				if (variaveis[i] != null && variaveis[i].getNome().equals(n)) {
					return variaveis[i];
				}
			}	

			return null;
		}


		public void adicionaVariavel(Variavel var) {
			for (int i = 0; i < this.variaveis.length; i++) {
				if (this.variaveis[i] == null) {
					this.variaveis[i] = new Variavel();
					this.variaveis[i].setNome(var.getNome());
					this.variaveis[i].setValor(var.getValor());
					break;
				}
			}
		}

}
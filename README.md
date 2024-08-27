Squad 4:
Daniel Naiff
Hugo Gabriel
Thiago Gentil
Gabriel Silva
Rubens Costa

##Diagrama de Classe```mermaid
classDiagram
  class LivrariaVirtual {
    - int MAX_IMPRESSOS = 10
    - int MAX_ELETRONICOS = 20
    - int MAX_VENDAS = 50
    - int numImpressos
    - int numEletronicos
    - int numVendas
	+ cadastrarLivro() void
+ realizarVenda() void
+ listarLivrosImpressos() void
+ listarLivrosEletronicos() void
+ listarLivros() void
+ listarVendas() void
+ main(args : String[]) void
  }

  class Venda {
    - int numVendas
    - int numero
    - String cliente
    - float valor
+ addLivro(Livro l, int index) void
+ listarLivros() void
  }

  class Impresso {
    - float frete
    - int estoque
+ atualizarEstoque() void
+ toString() String
  }

  class Eletronico {
    - int tamanho
+ toString() String
  }

  class Livro {
    - String titulo
    - String autores
    - String editora
    - float preco
+ toString() String
  }

  LivrariaVirtual "*" *-- "0" Venda
  LivrariaVirtual "*" *-- "1" Impresso
  LivrariaVirtual "*" *-- "1" Eletronico
  Venda "1" *-- "*" Livro
  Impresso --> Livro
  Eletronico --> Livro
  Impresso -- Eletronico



Squad 4:
Daniel Naiff
Hugo Gabriel
Thiago Gentil
Gabriel Silva
Rubens Costa


```

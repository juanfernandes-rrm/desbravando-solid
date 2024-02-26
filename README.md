# Cotuba
Este repositório contém códigos e anotações desenvolvidos durantes o estudo do livro ***Desbravando SOLID: Práticas avançadas para códigos de qualidade em Java moderno*** de [***Alexandre Aquiles***](https://github.com/alexandreaquiles/desbravando-solid). 
No livro, a aplicação Cotuba, um gerador de ebook do formato ***.md*** para ***.pdf*** e ***.epub***, é utilizado para aplicar os conceitos **SOLID**.

## Tecnologias
- Java 17
- Maven 3.8+

## [Como utilizar](https://github.com/juanfernandes-rrm/desbravando-solid/tree/main/cotuba)

## Orientação a Objetos X SOLID
Orientação a Objetos (OO) é um paradigma de programação que tem como objetivo aproximar o mundo real do código, permitindo uma representação mais clara e eficaz de problemas complexos. Nesse modelo, os conceitos do mundo real são traduzidos em objetos, que combinam dados e comportamento, tornando a programação mais intuitiva e organizada.

A principal vantagem da OO é sua capacidade de criar código flexível, robusto e reutilizável. Isso é possível devido à organização de dados e funcionalidades em unidades autônomas chamadas de objetos, que colaboram para resolver problemas de maneira modular e coesa. Essa abordagem é amplamente adotada na indústria de software devido às suas inúmeras vantagens.

Utilizando Orientação a Objetos como ferramenta de controle de dependência e coesão, podemos criar código mais flexíveis, robustos e reutilizáveis. Para isso, foram criadas algumas metodologias que guiam o desenvolvimento com OO, esse é o caso do SOLID, um guia de princípios que busca otimizar o uso deste paradigma para fazer um bom uso do gerenciamento de dependencias.


## Principio da Inversão de Dependências (DIP): Dependências Estáveis

Quando uma classe possui várias dependências, ocorre um acoplamento com múltiplas classes. O acoplamento é inevitável, mas nem todo acoplamento é prejudicial. Dependências de classes estáveis são consideradas benéficas, enquanto depender de classes suscetíveis a mudanças é desfavorável.

Para diminuir o impacto de dependências voláteis, é recomendável utilizar abstrações. Elas são estáveis, diferente de implementações. Interfaces ou classes abstratas podem ser usadas em vez de classes concretas, criando assim uma **dependência invertida**. Ao depender de uma abstração, não tem importância em como a implementação funciona ou quais outras dependências são utilizadas, tudo isso está abstraído. 

Um aspecto importante é preservar a abstração, evitando o vazamento de detalhes de implementação.

Ao definir contratos, evite termos específicos de um caso de uso particular. Por exemplo, ao abstrair uma classe de envio de SMS, nomeie métodos de forma mais genérica para possibilitar sua utilização em vários contextos.

A ideia de criar abstrações não é nova. A comunidade já defende essa ideia há tempos. 

> Depender de abstrações e não de implementações.
>
> -- <cite> Uncle Bob </cite>

> Programe voltado à interface, não à implementação
> 
> -- <cite> Design Patterns (GAMMA et al., 1994) </cite>

**Divisão de Código: Alto Nível e Baixo Nível**

É possível definir que a parte mais importante do código de aplicação é a regra de negócio, enquanto coisas mais técnicas, como UI web e/ou mobile, persistência de dados, frameworks são detalhes de implementação.

Uncle Bob, divide o código em dois níveis:

- **Código de alto nível:** Código que implementa a regra de negócio.
- **Código de baixo nível:** Código que implementam detalhes técnicos.

Sendo que o código de alto nível tem menor chance mudança, e o de baixo nível com maior chance de mudança. Essa distinção facilita a manutenção e evolução do código ao isolar a complexidade das implementações técnicas.

**Princípio da Inversão de Dependências (DIP): Regras Fundamentais**

Uncle Bob definiu o Princípio da Inversão de Dependências em duas boas práticas:

> Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.
> 
> Abstrações não devem depender de detalhes. Detalhes devem depender de abstrações.
> 


**Design Pattern: Factory**

O padrão de projeto Factory resolve o desafio de depender de classes concretas para criar instâncias, pois, por definição, abstrações não podem ser instanciadas diretamente.

> Por definição, não é possível instanciar abstrações. Portanto, para criar instâncias, é preciso depender de classes concretas.
>
> -- <cite> Uncle Bob </cite>

É assim que este padrão funciona, uma classe concreta que é responsável por instanciar outras classes.

Vantagens do uso de uma Factory, segundo ***Craig Larman***:

- Separa a responsabilidade da criação de objetos complexos em objetos auxiliares coesos.
- Esconde lógica de instanciação potencialmente complexa.
- Permite a introdução de estratégias de gerenciamento de memória que melhoram o desempenho, como cache ou reciclagem de objetos.

**Dependency Injection e Spring: Gerenciamento de Dependências**

Enquanto o DIP se concentra na qualidade das dependências, a Injeção de Dependência (DI) aborda como objetos obtêm suas dependências. Frameworks como o Spring gerenciam as implementações de interfaces, utilizando injeção de dependência por construtores. Isso simplifica a declaração de dependências em uma classe, enquanto o Spring cuida da injeção por meio do construtor.

**Observer Pattern: Invertendo Dependências com Eventos**

Outra abordagem para a inversão de dependências é o Observer Pattern. Este padrão utiliza eventos; quando um evento ocorre, objetos inscritos (subscribers) recebem notificações do objeto que gerou o evento (publisher). É especialmente útil em sistemas distribuídos que utilizam sistemas de mensageria, como RabbitMQ ou Apache Kafka

**Inversão de Controle (IoC): Recebendo Dependências de Fora**

Tanto a Injeção de Dependência quanto o Observer Pattern seguem a ideia mais ampla da Inversão de Controle, onde objetos não buscam suas dependências, mas as recebem externamente. Isso promove flexibilidade e facilita a manutenção do código ao separar a configuração e a criação de objetos do restante do código.


## Princípio Aberto/Fechado (OCP): Objetos Flexíveis

O Princípio Aberto/Fechado está relacionado à flexibilidade de objetos.

> Entidades de software devem ser abertas para extensão e fechadas para modificação.
>
> -- <cite> Bertrand Meyer </cite>

Essa definição implica que devemos permitir a adição de novos comportamentos sem precisar modificar o código existente. Nesse sentido, os princípios DIP (Dependência de Inversão) e OCP (Princípio Aberto/Fechado) são complementares, pois incentivam o uso de abstrações que tornam o código flexível o suficiente para ser estendido sem modificação.

Por exemplo, consideremos o código do Cotuba, que possui duas classes responsáveis pela geração de ebooks em diferentes formatos, como PDF ou EPUB. Embora essas classes tenham comportamentos semelhantes, suas implementações são diferentes. Para tornar o código mais flexível, essas classes devem implementar uma interface que defina um método comum 
entre elas, como `gerar(Ebook ebook)`.

Dessa forma, o Princípio DIP é respeitado, pois separa as camadas de alto e baixo nível por meio de uma camada de abstração que aponta para a regra de negócio, enquanto o OCP permite a extensão sem modificar o comportamento já existente de geração de ebooks.

No entanto, é importante aplicar o DIP com cautela. Pode acontecer de existir uma interface que tenha apenas uma implementação. Nesse caso, mesmo que isso desrespeite o princípio, pode não ser necessário, pois criar mais código para manter a abstração não ofereceria vantagens significativas.

Portanto, é essencial entender quais pontos de um projeto devem permanecer abertos e flexíveis, justificando a real necessidade de abstrações. Essas abstrações devem ser aplicadas de forma seletiva em pontos específicos do código.

## Plugins: OCP ao extremo

Diante da demanda por novas funcionalidades no Cotuba, que a equipe de desenvolvimento atual não pode atender imediatamente, surge a necessidade de permitir a extensão do software por terceiros. Essa solução é viabilizada por meio dos plugins.

Um plugin representa um ponto de extensão que possibilita a aplicação de classes desenvolvidas externamente durante a execução da aplicação, sem que essas classes estejam integradas ao código principal do projeto.

Para que isso ocorra, é necessário definir uma abstração que represente o plugin. No caso do Cotuba, vamos estabelecer dois métodos em uma interface chamada Plugin: 

- ***aposRenderizacao(String html)***: permite customização após a renderização do HTML.
- ***aposGerar(Ebook ebook)***: permite customização após gerar o ebook.

Este tipo de plugin que está relacionando a ciclos de vida da aplicação são chamados de ***hooks***.

Com abstração definida, quem desejar criar um plugin para o Cotuba deve criar um projeto, adicionar o Cotuba como dependência, e criar uma classe que implemente a interface Plugin.

Com isso, temos um ponto de extensão, mas como é feito a ligação com uma implementação independente? Esse problema é resolvido a partir do Java SE 6, com a ***Service Loader API*** 

> Na Service Loader API, um ponto de extensão é chamado de service. Para provermos um service precisamos de:
> 

> Service Provider Interface (SPI): Interface ou classes abstratas que definem a assinatura do ponto de extensão.
> 

> Service Providers: uma ou mais implementações da SPI.
> 

No caso do Cotuba, a interface Plugin é uma SPI, e as implementações dela são Service Providers.

Para fazer a ligação da Service Provider com a SPI, é necessário que o JAR da provider declare o ***provider configuration file***, arquivo com o nome (***fully qualified name***)  da SPI dentro da pasta ***META-INF/services***. Dessa forma, através da presença do .jar, a Servide Loader API fará com que o comportamento da aplicação seja estendido sem necessitar de uma modificação, seguindo o princípio OCP.

Então, para usar a Service Provider API, utilizamos a classe java.util.ServiceLoader que fornece o método load, que recebe como parâmetro SPI e retorna uma instância de ServiceLoader com todas as implementações de SPI presentes nos jars disponíveis no ClassPath. Após isso, definimos dois métodos estáticos na SPI, renderizou e gerou, e utilizamos a ServiceLoader para buscar plugins. Esses métodos são chamados após a renderização do HTML, na classe ***RenderizadorMDParaHTML***, e geração do Ebook, na classe ***Cotuba***.

Desse modo, foi possível criar para essas duas etapas um ponto de extensão sem precisar criar modificações no código.

## Princípio de Substituição de Liskov (LSP): Herança do jeito certo

Este princípio, cunhado pela cientista ***Barbara Liskov***, estabelece que:

> "A ideia intuitiva de um subtipo é aquela cujos objetos fornecem todo o comportamento de objetos de outro tipo (o supertipo) mais algo extra."
> 

No entanto, é importante ressaltar que não basta que um subtipo tenha a mesma assinatura dos métodos do supertipo, ele também deve ter o mesmo comportamento. Por exemplo, uma pilha e uma fila podem ter métodos com as mesmas assinaturas, mas seus comportamentos serão diferentes.

Uncle Bob resgata esse princípio para o SOLID:

> Subtipos devem ser substituíveis por seus tipos base.
> 

Em outras palavras, ao utilizar a herança de maneira correta, uma classe filha poderá ser substituída por sua classe mãe. Porém, para que isso seja válido, a classe filha deve herdar todos os comportamentos da classe mãe. Caso contrário, o LSP é violado.

Um exemplo disso pode ser observado no projeto estatistica-ebook, que necessita contar as palavras de um ebook. Uma solução inicial seria criar uma classe ContagemDePalavras, herdando de TreeMap e implementando o método `adicionarPalavra`. No entanto, essa abordagem violaria o LSP, pois a classe ContagemDePalavras não seria substituível por sua classe mãe, uma vez que não forneceria suporte a todos os métodos dela.

Uma abordagem mais adequada seria utilizar a composição, onde a classe TreeMap seria um atributo privado da classe ContagemDePalavras.

Outro exemplo de violação do LSP pode ser observado nas classes que implementam a SPI Plugin do Cotuba. No projeto estatistica-ebook, a classe CalculadoraDeEstatisticas implementa a interface Plugin, mas sobrescreve apenas o comportamento de `aposGeracao`, enquanto a classe TemaParadizo, do projeto tema-paradizo, sobrescreve apenas `aposRenderizacao`. Dessa forma, nenhuma das subclasses é substituível pela superclasse, pois elas realizam menos atividades do que ela.

Por fim, a literatura enfatiza a preferência pela composição em relação a herança. Isso ocorre devido ao uso incorreto da herança, que pode causar um forte acoplamento entre classes desnecessariamente. A herança deve ser utilizada apenas nos casos em que uma subclasse é verdadeiramente um subtipo de sua superclasse, onde existe uma relação de "é um". Caso contrário, a composição é mais adequada, uma vez que a dependência é apenas um detalhe de implementação.


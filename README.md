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

## Princípio da Segregação de Interfaces (ISP): Clientes separados, interfaces separadas

Este princípio trata da coesão das abstrações, ou seja, se a abstração fornece um contrato simples e significativo para o cliente. Caso contrário, se a resposta for não, a abstração não é coesa e deve ser refatorada para atender ao cliente de forma adequada.

Uncle Bob esclarece esse pensamento definindo o princípio:

> Clientes não devem ser obrigados a depender de métodos que eles não usam.
> 

> Muitas interfaces específicas para cada cliente são melhores que uma interface de propósito geral.
> 

> Clientes separados, interfaces separadas.
> 

Com uma abstração que apresenta comportamentos simples e bem definidos, os clientes enfrentam menos dificuldades com alterações em outros pontos do sistema.

No contexto do Cotuba, encontramos uma violação desse princípio na SPI Plugin, que define dois métodos: `aposRenderizacao` e `aposGeracao`. No entanto, as Service Providers dessa interface não utilizam ambos os métodos; cada uma utiliza apenas um. Para atender ao ISP, devemos refatorar a SPI em duas interfaces separadas para atender aos clientes de forma coesa. Assim, criaremos uma interface para o hook de renderização do HTML (`AoRenderizarHTML`) e outra para a geração de ebook (`AoFinalizarGeracao`).

Outro ponto importante é pensar em interfaces específicas para quem as usa. Por exemplo, consideremos uma classe que representa uma nota fiscal, com atributos de endereço de cobrança, dados do cliente e itens da nota; e uma classe que calcula impostos com base nesses dados. Essa classe de cálculo realmente precisa de acesso a todos os atributos da classe nota fiscal? Não necessariamente. Ao fornecer acesso a todos os dados, tornamos nossa classe nota fiscal vulnerável a implementações maliciosas. Para resolver esse problema, podemos criar uma interface que forneça apenas os dados necessários para o cálculo de imposto. Ao implementar essa interface na classe nota fiscal, a classe de cálculo poderia receber apenas a interface, protegendo assim a classe nota fiscal enquanto fornece acesso apenas aos dados necessários para o cálculo.

> Se você tiver uma classe que tenha vários clientes, em vez de carregar a classe com todos os métodos de que os clientes precisam, crie interfaces específicas para cada cliente e implemente-as na classe.
>
> -- <cite> Uncle Bob </cite>

Uma situação semelhante ocorre no Cotuba. A SPI `AoFinalziarGeracao` possui o método `aposGeracao`que permite acesso e modificação (getters e setters) do ebook que foi gerado. Dessa forma, Service Providers mal-intencionados poderiam alterar o conteúdo do ebook, como capítulos.

Para evitar isso, vamos proteger nosso modelo de domínio com interfaces. Criamos interfaces que definem apenas os métodos getters das classes de domínio, e as classes de domínio implementam essas interfaces. Com isso, podemos permitir acesso apenas a essas interfaces, que fornecem apenas acesso aos atributos.


## Imutabilidade e encapsulamento

No capítulo 8, foram introduzidas as interfaces `CapituloSoParaLeitura` e `EbookSoParaLeitura`,
contendo apenas getters para proteger o modelo de domínio. Embora isso tenha permitido atender ao ISP, houve uma violação do DIP, uma vez que o código de alto nível passou a depender do código de baixo nível.

No entanto, existe uma solução que respeita o DIP e protege nossas classes de domínio? Essa solução é viável com o uso da imutabilidade.

Uma classe imutável é aquela cujos atributos não mudam de estado após a instanciação. Portanto, não inclui setters e seus atributos são declarados como final. Para permitir a criação de objetos, recorremos a métodos construtores.

Além disso, métodos que aparentemente modificariam o objeto criam, na verdade, novos objetos com novos valores. É importante também que a classe seja final, para evitar a possibilidade de herança.
Para garantir a imutabilidade mesmo em composições com objetos mutáveis, é necessária uma cópia defensiva. Por exemplo, no caso de uma lista, podemos usar métodos como `unmodifiableList`, `List.of`, ou `stream.toList`.

***Michael Feathers*** estabelece algumas regras para garantir a imutabilidade:

- Não forneça nenhum método que modifique o estado do objeto.
- Assegure que a classe não possa ser estendida.
- Defina todos os atributos como final.
- Faça com que todos os atributos sejam privados.
- Assegure acesso exclusivo a qualquer composição com objetos mutáveis.

Embora os objetos imutáveis possuam vantagens como simplicidade e thread-safety, eles também têm desvantagens, como o uso elevado de memória.

No Cotuba, vamos tornar as classes de domínio imutáveis.

### Design Pattern: Builder

Durante a refatoração do Cotuba para tornar as classes de domínio imutáveis, nos deparamos com um problema na criação de objetos em etapas. Isso motivou a introdução do Design Pattern Builder, que permite a construção de objetos complexos em etapas, de maneira independente das partes que os compõem.

```java
Capitulo capitulo = new CapituloBuilder()
  .comTitulo(tituloDoCapitulo);
  .comConteudoHTML(htmlModificado)
  .constroi();
```

O padrão Builder facilita a instanciação, fornecendo métodos intermediários que retornam o próprio builder, possibilitando chamadas encadeadas.

Use o Builder pattern quando:

> o algoritmo para criar um objeto complexo deve ser independente das partes que compõem o objeto e como elas são montadas.
> 

> e o processo de construção deve permitir diferentes representações para o objeto que é construído
> 

> GoF - Design Patterns
> 

### Imutabilidade com Records

A partir do Java 16, temos o recurso dos ***Records***, uma sintaxe para representar classes imutáveis de maneira simples.

```java
public record Capitulo(String capitulo, String conteudoHTML) {
}
```

Os Records geram automaticamente atributos imutáveis e métodos de acesso para cada componente, além de implementações de `equals`, `hashCode`, e `toString`.

### Encapsulamento

O encapsulamento é essencial para garantir a integridade e a segurança do código. Evita a exposição indevida de detalhes de implementação e protege os objetos de acesso não autorizado.

Um dos maiores problemas em códigos, segundo Kent Beck e Martin Fowler é a ***inveja de funcionalidades:***

> A essência dos objetos é que eles são uma técnica para empacotar dados com os processamentos desses dados.
> 

> Um indício clássico de problema é um método que parece mais interessado em uma classe diferente daquela na qual ele se encontra.
> 

> O foco mais comum da inveja são os dados.
> 

***Andy Hunt e Dave Thomas***, ilustram o problema de inveja de funcionalidade com a seguinte analogia:

> Suponha que o entregador de jornais vá até a sua porta, demandando o pagamento da semana. Você vira, o entregador puxa a carteira do bolso traseiro da sua calça, tira duas notas e devolve sua carteira.
> 

Nessa lógica, código bom é código tímido. Isto é, código reservado, que não revela muito de si e conversa somente o necessário, evitando expor suas coisas privadas. Ou seja, código tímido é código encapsulado, que preza por esconder o máximo possível suas implementações e suas informações.

Vale lembrar que apenas modificadores de acesso privados não garante o encapsulamento de uma classe, uma vez que detalhes de uma classe podem ser obtidos através de getters e setters. E nem mesmo evitando setters, o encapsulamento ainda pode ser quebrado através de getters.

Outra forma de violar o encapsulamento é através da Herança. Ao herdar de uma classe, a subclasse tem acesso as implementações da superclasse, e ao ocorrer uma mudança na superclasse, pode ser que a subclasse precise mudar, tornando o código acoplado. Por isso, no livro Design Patterns, é afirmado que há um conflito entre herança e encapsulamento.

Uma alternativa para manter código com fácil manutenção e entendimento é aplicar encapsulamento e imutabilidade, limitando o nosso código. Com a imutabilidade impedimos mudanças de estados em objeto e com encapsulamento restringimos a camada de compartilhamento entre objetos.

***Michael Feathers***, define essas restrições como ***Arquitetura Negativa***.

> “Você pode olhar para essa área do seu código e saber que tem uma coisa a menos para pensar/preocupar-se.”
> 

### Lei de Deméter

Uma forma de garantir o encapsulamento é seguindo a Lei de Deméter que afirmar que todo método de um objeto deve chamar apenas métodos pertencentes a:

> si mesmo
> 

> quaisquer parâmetros que foram passados para o método
> 

> quaisquer objetos criados
> 

> qualquer composição
> 

Ou seja, apenas interagir com vizinhos diretos.

Essa lei é complementada com o lema ***“Tell, don`t ask”*** de ***Andy Hunt e Dave Thomas***.

> Envie comandos para objetos dizendo o que você quer fazer. Explicitamente, não queremos consultar um objeto sobre seu estado, tomar uma decisão e, então, dizer ao objeto o que fazer.
> 

### Design Pattern: Iterator

Pensando no encapsulamento, na projeto estatistica-ebook, temos um for-each para percorrer os dados da classe `ContagemDePalavras`. Porém da maneira que está sendo feito atualmente, o detalhe da implementação está sendo exposto às classes que utilizam o método entrySet, quebrando o encapsulamento.

```java
for(Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()){
                String palavra = contagem.getKey();
                Integer ocorrencias = contagem.getValue();
                System.out.println(palavra + ": " + ocorrencias);
```

Para resolver esse problema, seria interessante percorrer o próprio objeto ContagemDePalavras em um for-each, sem expor a implementação. Para isso, podemos utilizar o padrão ***Iterator***, que permite que os detalhes da estrutura de dados usado (List, Set, entre outros) sejam encapsulados no objeto.

A `API Collections` do Java oferece a a interface `Iterator`, que define os métodos hasNext e next. Podemos utilizá-la para retornar o próprio objeto, e este seja utilizado para iterar. Para facilitar, definimos um record com os componentes que precisamos, que será o objeto a ser iterado. Mas precisamos definir uma classe anônima que implemente o métodos de Iterator.

```java
public Iterator<Contagem> iterator() {

        Iterator<Map.Entry<String, Integer>> iterator = this.map.entrySet().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Contagem next() {
                Map.Entry<String, Integer> entry = iterator.next();
                String palavra = entry.getKey();
                int ocorrencias = entry.getValue();
                return new Contagem(palavra, ocorrencias);
            }
        };
    }
```

Dessa forma, temos um objeto que pode ser percorrido através dos métodos hasNext e next. Mas dessa maneira temos uma iteração externa.

```java
Iterator<ContagemDePalavras.Contagem> iterator = contagemDePalavras.iterator();

while(iterator.hasNext()) {
	ContagemDePalavras.Contagem contagem = iterator.next();	
}
```

Podemos utilizar a ***Interação Interna***, que controla o valores fornecidos para o cliente, ao fazer com ContagemDePalavras implemente a interface ***Iterable*** e o método ***iterator***. Dessa maneira, ela poderia ser utilizada diretamente em um for-each.

```java
class ContagemDePalavras implements Iterable<ContagemDePalavras.Contagem>{
}
```

```java
for(ContagemDePalavras.Contagem contagem : contagemDePalavras) {
                System.out.println(contagem.palavra() + " - " + contagem.ocorrencias());
            }
```

Utilizando o Iterator, podemos iterar sobre o objeto sem expor sua implementação interna, promovendo assim um maior encapsulamento.


## Princípios de coesão e acoplamento de módulos

Módulos são componentes de organização de código que separa uma aplicação em “partes” independentes.

Algumas características:

- Implantáveis
- Reusáveis
- Testáveis
- Gerenciáveis
- Componíveis
- Sem estado

Essa são as mesmas características de um JAR (Java Archieve), arquivo compactado no formato zip que contêm pacotes e classes. Por isso, em Java, um módulo é um JAR.

O uso de módulo permite que uma aplicação tenha facilidade na capacidade de troca de módulos, compreensão de código, desenvolvimento paralelo, testabilidade, e flexibilidade.

Para definir a divisão de um código em módulos, Uncle Bob apresenta alguns princípios para manter a coesão.

### Princípio da Equivalência entre Entrega e Reúso (REP)

Para permitir o reúso de código por terceiro, é necessário pensar em como fazer a entrega de módulos. Existe algumas alternativas como um site próprio, repositório remotos (GitHub, GitLab, etc.), repositório centrais do Maven e Grandle, ou gerenciadores de artefatos (Nexus).

Ao utilizar alguma dessas opções, ao finalizar uma alteração no código, acontece o processo de publicação de uma nova versão. Somente módulos que seguem esse processo podem ser considerados reusáveis.

É importante ressaltar que um módulo é indivisível, ou seja, não é possível usar somente parte de um módulo, ele é uma estrutura atômica. Por isso, podemos concluir que existe uma equivalência entre a entrega e reúso do módulo.

### Princípio do Agrupamento Comum (CCP)

Seguindo a mesma ideia do SRP, para evitar grandes problemas quando ocorrer mudanças em uma aplicação, é recomendado que estas mudanças sejam feitas em um módulo. Para isso, um módulo deve ser formado apenas por classes que tenham um mesmo motivo para serem modificadas. Dessa forma, uma mudança ocorre apenas em um módulo e não em vários.

### Princípio do Reúso Comum (CRP)

Este princípio é equivalente ao ISP, um módulo não deve fornece classes que são utilizadas apenas por parte dos clientes, já que uma uma mudança que não seria necessária para parte dos clientes resulta na necessidade de uma nova publicação. Um módulo deve fornecer apenas as classes que todos os clientes usam.

### Módulos Maven

O Maven permite a modularização de uma aplicação (multi-module project). Dessa forma, um projeto pode ter vários módulos, e o Maven se responsabiliza por gerenciar as dependências e fazer a build de cada um de forma ordenada. Para isso, é necessário criar um supermódulo, que contêm os submódulos.

### Princípios de acoplamento de módulos

Normalmente, aplicações fazem o uso de módulos, e isto causa uma dependência e por tanto um acoplamento. Para minimizar este acoplamento, Uncle Bob define os princípios de acoplamento de módulos.

### Princípio das Dependências Acíclicas (ADP)

Ocorre um ciclo de dependências quando um módulo depende de outro e vice-versa. Isso pode fazer com que um módulo dependa de um outro módulo que dependa de outro módulo (dependência transitiva), sendo este último desnecessário para o primeiro módulo.

Isso pode causar problemas na compilação, já que a build deve ser feita em ordem hierarquia de módulos. Em casos de dependências cíclicas, a hierarquia é quebrada.

Para evitar esse problema, podemos inverter as dependências através de abstração. No exemplo do Cotuba, o módulo cotuba-core poderia fornecer uma abstração (Interface SPI GeracaoEbook) para os módulos cotuba-pdf, cotuba-epub e cotuba-html. Dessa forma, os módulos ficariam responsáveis por implementar a abstração, evitando que cotuba-core tenha dependência com esses módulos, além praticar o Princípio do Reúso Comum ao evitar que clientes recebam dependências desnecessárias.

### Princípio das Dependências Estáveis (SDP)

A estabilidade de um módulo está relacionada ao nível de atenção e cuidado que se deve ter ao realizar uma mudança. Essa métrica pode ser medida com quantidade de módulos dependentes, quanto mais módulos dependerem de um módulo, maior a estabilidade desse módulo.

Essa métrica é utiliza para ajudar na escolha da divisão de uma aplicação em módulos. Devemos fazer com que a aplicação tenha a menor quantidade de módulos estáveis possível, e que a dependência entre módulos sejam feita na direção da estabilidade.

### Princípio das Abstrações Estáveis (SAP)

Um módulo estável pode trazer grande dificuldade para mudanças, por isso é importante que um módulo estável seja o mais abstrato possível, pois permite extensão e por tanto flexibilidade. Esse é o princípio das abstrações estáveis: um módulo deve ser tão abstrato quanto for estável.

### Métrica instabilidade

Métrica definida por Uncle Bob para definir a instabilidade I de um módulo. Por ser calculada da seguinte forma:

- Fan-in: número de dependências de entradas, ou seja, número de módulos que são dependes  do módulo alvo.
- Fan-out: número de dependências de saídas, ou seja, número de módulos que são dependências do módulo alvo.
- I = fan-out / (fan-in + fan-out)

O valor de I será entre 0 e 1. O valor 0 indica que um módulo não tem dependências, mas outros módulos dependem dele, representando o máximo de estabilidade. 

E o valor 1 indica que o módulo depende de outros módulos, mas não é dependência de nenhum outro módulo, representando o o máximo de instabilidade.

### Métrica abstratividade

Uncle Bob ainda define outra métrica. Abstratividade é proporção de abstrações que um módulo possuí. Essa métrica pode ser calculada da seguinte forma:

- Na: número de classes abstratas e interfaces no módulo.
- Nc: número total de tipos, somando classes e interfaces.
- A = Na / Nc

O valor de A será entre 0 e 1. O valor 0 indica que o módulo não possui nenhuma classe abstrata ou interface.

E o valor 1 indica que o módulo tem somente classes abstratas ou interfaces.

Segundo o SAP, a abstratividade deve seguir a estabilidade de um módulo, portanto, quanto menor a instabilidade (maior estabilidade), maior a abstratividade.

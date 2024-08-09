
# RickAndMortyWiki

Aplicativo feito com Kotlin Multiplatform e Compose Multiplatform para exibição de personagens e detalhes da animação Rick and Morty

## Checklist de Implementação

- [x] Tela de listagem de personagens (com paginação)
- [x] Tela de detalhes do personagem
- [x] Suporte a rotação de tela (orientações portrait e landscape)
- [x] Escrever testes unitários e/ou de UI
- [ ] Filtro de personagens
- [ ] Suporte ao modo offline (cachear localmente response da API)
- [ ] Adicionar regras do Proguard
- [ ] Comandos gradlew

##### O que não foi implementado
- Os comandos gradlew foram testados de última hora na minha máquina e por alguma razão estava reclamando da versão do Java e não tive tempo de buscar uma solução.
- Suporte ao modo offline foi parcialmente implementado, pois foi criado um banco de dados local, porém, faltou salvar os personagens localmente também, e não apenas localizações e episódios.
- Não consegui a tempo implementar o Filtro de personagens e regras do Proguard.

## Instalação

Instruções passo a passo para instalação do projeto.
- Clone o repositório.
- Acesse o projeto no AndroidStudio e após o projeto ser carregado, **realize o sync** e builde o aplicativo no Android ou no iOS,
  **caso não** for dado o sync e só builde, o aplicativo será buildado e uma tela em branco será exibida.

**Observação**: Para rodar o aplicativo no **iOS** é necessário estar em uma máquina com o sistema operacional **MacOS**

## Decisões do Projeto

### Decisão 1: Modelo de dados

Devido o modelo de response do personagem não trazer com detalhes informações sobre as localizações e os episódios,
decidi criar uma tela de splash screen para buscar as localizações e episódios e salvar localmente estes dados,
para que possam ser utilizados para montar uma classe de personagem mais detalhada e mostrar mais informações na tela de detalhes.

### Decisão 2: Banco de dados

Foi decidido a utilização do RealmDB devido este ser seguro para implementação em KMP,
o Room ainda está em estágios iniciais e muitos erros acontecem em dispositivos fisicos, já o Realm
está a mais tempo em KMP e além de ser mais fácil implementação, ocasiona menos erros inesperados.


### Decisão 3: Requisições

Foi decidido o uso do Ktor para requisições pois o Retrofit ainda não funciona no KMP.

### Decisão 4: Paginação

Foi decidido criar uma paginação na mão em relação ao paging3 devido ao baixo esforço de implementação
e por não utilizar bibliotecas externas, já que o paging3 ainda não é 100% KMP e para usá-lo teria que
usar bibliotecas externas.

### Decisão 5: Testes

Foi decidido a biblioteca mokkery para facilitar a criação de testes unitários,
pois se assemelha bastante ao mockk e o mesmo ainda não está disponível para o kmp.

### Decisão 6: Arquitetura

Foi decidido a utilização de uma arquitetura multimodular para tornar o projeto um pouco mais robusto
e desacoplado.

Na qual temos uma estrutura basicamente divida entre **core** e **features**

##### Módulos core:
Módulo **:core:common** é um módulo separado em 3 módulos na qual é onde as entidades do projeto estão localizadas e seus casos de uso,
além disso, possui código comum entre todos os módulos.

Módulo **:core:designsystem** é o módulo com todos os resources e composables comuns entre o aplicativo.

##### Módulos feature:
Cada tela é uma feature e respectivamente possui seu próprio módulo.
Módulo **:feature:splash** é o módulo com a tela de splash screen, na qual busca os episódios e localizações e salva-os localmente no banco de dados enquanto exibe uma animação de loading.

Módulo **:feature:home** é o módulo com a tela de home, na qual exibe todos os personagens realizando paginação.

Módulo **:feature:details** é o módulo com a tela de detalhes, na qual exibe detalhes de um personagem.

##### Módulo app:
O módulo **:app** é o módulo onde realiza a união de todos os outros módulos e unindo em um framework só para a utilização no **iOS**,
além disto, ele é responsável por montar o aplicativo, definindo a navegação e expondo um **composable** para a utilização nos aplicativos **Android** e **iOS**.

##### Módulo androidApp:
Módulo do aplicativo **Android** em si, onde temos o **MainApp** e a **MainActivity**.

##### Módulo iosApp:
Módulo do aplicativo **iOS**.




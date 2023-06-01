# Trabalho Prático: O Problema dos Leitores e Escritores

<sub>Última atualização: 21/05/2023</sub>

## Sumário

- [Objetivo](#objetivo)
- [Introdução](#introdução)
- [Tarefas](#tarefas)
  - [Implementação](#implementação)
  - [Relato](#relato)
- [Autoria e política de colaboração](#autoria-e-política-de-colaboração)
- [Entrega](#entrega)
- [Avaliação](#avaliação)
- [Dúvidas e informações](#dúvidas-e-informações)

## Objetivo

O objetivo deste trabalho é estimular o projeto e implementação de soluções para problemas por meio de programação concorrente, mais especificamente colocando em prática os conceitos e mecanismos de sincronzação de processos/*threads*.

## Introdução

O Problema dos Leitores e Escritores (*Readers-Writers Problem*) é um problema clássico no contexto de programas concorrentes, o qual serve inclusive como modelo para outros problemas com características similares. Proposto originalmente por P. J. Courtois, F. Heymans e D. L. Parnas em seu artigo [*Concurrent control with "readers" and "writers"*](https://doi.org/10.1145/362759.362813), publicado em 1971, o problema diz respeito à existência de um recurso compartilhado entre dois tipos de processos, leitores e escritores, os quais tentam acessar tal recurso de forma simultânea. Os processos leitores realizam operações de leitura sobre o recurso compartilhado, enquanto os processos escritores realizam operações de escrita. O problema consiste, portanto, em estabelecer uma forma de sincronização entre esses processos de modo preservar um estado consistente a ao recurso compartilhado.

## Tarefas

A tarefa central a ser realizada neste trabalho consiste em projetar e implementar soluções concorrentes para duas variações do Problema dos Leitores e Escritores utilizando conceitos e técnicas de programação concorrente, incluindo a criação, execução e sincronização de *threads*. Tais variações são descritas a seguir.

**Preferência aos leitores**

Considerando que o objetivo principal do Problema dos Leitores e Escritores é deixar o recurso compartilhado em um estado consistente, uma solução natural ao problema seria garantir exclusão mútua ao processo que está acessando o recurso, de modo que nenhum processo poderia acessar o recurso enquanto outro processo, independentemente do seu tipo, estiver de posse dele. Todavia, essa solução, apesar de resolver o problema de forma satisfatória, não é ótima pelo fato de processos leitores não modificarem o estado do recurso e, portanto, não precisariam estar em exclusão mútua entre si.

A solução que dá preferência aos leitores (*readers-preference*) permite que um processo leitor possa realizar uma operação de leitura sobre o recurso compartilhado mesmo que ele esteja de posse de outro processo leitor. Nesse caso, se um processo escritor tentar acessar o recurso, este será bloqueado e poderá ter acesso ao recurso quando não houver mais nenhum processo leitor fazendo uso deste. Caso o recurso esteja inicialmente de posse de um processo escritor, quaisquer outros processos que desejarem acessar o recurso, sejam eles leitores ou escritores, serão bloqueados e deverão aguardar pela sua liberação.

**Preferência aos escritores**

A solução que dá preferência aos escritores (*writers-preference*) prioriza a execução desse tipo de processo. Ou seja, havendo um processo escritor pronto para acessar o recurso compartilhado, a posse deste lhe será concedida, devendo quaisquer outros tipos de processos (leitores e/ou escritores) serem bloqueados em razão da exclusão mútua garantida à operação de escrita. Ao término da operação de escrita corrente, havendo um processo escritor bloqueado, a posse do recurso compartilhado lhe será garantida. Com isso, processos leitores só poderão acessar o recurso compartilhado caso não haja nenhum processo escritor acessando ou aguardando o acesso ao recurso.

### Implementação

As soluções poderão ser implementadas utilizando facilidades providas pelas linguagens de programação C/C++, Java **ou** Python, sendo que outra linguagem diferente dessas três poderá ainda ser utilizada contanto que a proposta seja previamente validada com o docente. O mecanismo de sincronização é de livre escolha dentre os disponibilizados pela linguagens de programação escolhida.

Além da implementação do núcleo de cada solução, deverá ser implementado, para fins de demonstração, um código fonte principal que realize a instanciação de um determinado número de *threads* realizando operações de leitura e escrita sobre alguma variável, objeto ou arquivo (recurso compartilhado). A execução do programa deverá exibir, na saída padrão, o comportamento das *threads* no tocante ao acesso ao recurso compartilhado e eventuais bloqueios caso de o recurso compartilhado estar em uso.

*Dica:* Caso seja implementado um projeto único envolvendo as duas soluções implementadas, uma sugestão é solicitar ao usuário, a partir da entrada padrão, que solução será executada, se aquela dando preferência aos leitores ou aos escritores.

### Relato

Uma vez realizadas as tarefas de implementação, deverá ser elaborado um relatório contendo, pelo menos, uma descrição acerca:

- de como a solução foi projetada;
- a lógica de sincronização utilizada, em termos dos mecanismos empregados e como ela é feita entre os fluxos de execução do programa, além de ser justificada a escolha do mecanismo de sincronização;
- como é garantida a corretude da solução com relação a concorrência; eventuais dificuldades encontradas durante o desenvolvimento, e;
- instruções para compilação e execução do programa.

Além do relatório escrito, deverá ser **obrigatoriamente** respondida a seguinte pergunta no arquivo [`author.md`](https://github.com/ufrn-concprog/readers-writers/tree/master/author.md) presente no repositório, na seção [Garantia de Corretude](https://github.com/ufrn-concprog/readers-writer/tree/master/author.md#garantia-de-corretude):

*As soluções implementadas garantem a ausência de condições de deadlock e starvation? Se sim, como? Se não, como fazer com que haja tal garantia?*

A ausência de resposta a essa pergunta implicará na **redução de -30%** da nota obtida na avaliação do trabalho. A resposta fornecida será devidamente avaliada de modo que eventuais imprecisões ou incorretudes poderão implicar em redução de nota.

## Autoria e política de colaboração

Este trabalho deverá necessariamente ser realizado em equipe composta de **até dois estudantes**, sendo importante, dentro do possível, dividir as tarefas igualmente entre os integrantes da equipe. Após a implementação das soluções para os problemas propostos, o arquivo [`author.md`](https://github.com/ufrn-concprog/readers-writers/tree/master/author.md) presente no repositório deverá ser editado preenchendo as informações de identificação dos integrantes da equipe, na seção [Informações de Autoria](https://github.com/ufrn-concprog/readers-writer/tree/master/author.md#identificação-de-autoria).

O trabalho em cooperação entre estudantes da turma é estimulado, sendo admissível a discussão de ideias e estratégias. Contudo, tal interação não deve ser entendida como permissão para utilização de (parte de) código fonte de colegas, o que pode caracterizar situação de plágio. **Trabalhos copiados no todo ou em parte de outros colegas ou da Internet serão anulados e receberão nota zero.**

## Entrega

O sistema de controle de versões [Git](https://git-scm.com) e o serviço de hospedagem de repositórios [GitHub](https://github.com) serão utilizados para possibilitar a entrega da implementação realizadas. Para possibilitar a associação de repositórios Git para cada equipe e reuni-los sob uma mesma infraestrutura, foi criada uma atividade (*assignment*) no GitHub Classroom. Cada integrante de equipe deverá acessar este [*link*](https://classroom.github.com/a/yUDknnfd), aceitar o convite para ingressar no GitHub Classroom e finalmente seguir as instruções em tela para acessar a atividade e ingressar em uma equipe existente ou criar outra. Este [vídeo](https://youtu.be/ObaFRGp_Eko) demonstra como ocorre esse processo.

No momento de criação de uma equipe, o GitHub Classroom cria um repositório Git privado acessível unicamente pelos integrantes da equipe e pelo docente, sob a organização [`ufrn-concprog`](https://github.com/ufrn-concprog). A fim de garantir a boa manutenção do repositório, deve-se ainda configurar corretamente o arquivo `.gitignore` para desconsiderar arquivos que não devam ser versionados, a exemplo dos arquivos executáveis gerado a partir da compilação do código fonte. Também não é necessário versionar os arquivos de saída resultantes do processo de ordenação.

A entrega deste trabalho deverá ser realizada até as **23:59 do dia 31 de maio de 2023** no respectivo repositório Git da equipe. O relatório elaborado, preferencialmente em formato *Adobe Portable Format* (PDF), deverá ser enviado como arquivo através da opção *Tarefas* da Turma Virtual do SIGAA, juntamente com, no campo *Comentários*, o endereço do repositório. Um único membro da equipe deve realizar esse envio e não serão aceitos envios por outros meios ou repositórios que não sejam os descritos nesta especificação.

## Avaliação

A avaliação do trabalho contabilizará nota de até 6,0 pontos na 2ª Unidade da disciplina. O trabalho será avaliado de acordo com os seguintes critérios:

- utilização correta dos conceitos e mecanismos de sincronização de processos/*threads*;
- a corretude da execução do programa implementado, tantocom relação a funcionalidades quanto a concorrência;
- a aplicação de boas práticas de programação, incluindo legibilidade, organização e documentação de código fonte;
- correta utilização do repositório Git, no qual deverá ser registrado todo o histórico da implementação por meio de *commits*, e;
- qualidade do relatório produzido

## Dúvidas e informações

Caso haja qualquer dúvida, questionamento ou necessidade de informação adicional, é possível:

- enviar *e-mail* para o endereço <everton.cavalcante@ufrn.br>;
- enviar mensagem privada diretamente ao docente, utilizando o servidor Discord;
- enviar mensagem no canal de texto `#duvidas` no servidor Discord, ou;
- agendar encontros síncronos pelo canal de áudio `Fale com Prof. Everton` no servidor Discord.

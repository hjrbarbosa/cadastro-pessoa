
# Cadastro Pessoa

README - Aplicação Spring Boot + PrimeFaces

==============================================

Este é um projeto de uma aplicação Spring Boot com PrimeFaces para cadastro de pessoa. A aplicação permite adicionar, editar e excluir informações de pessoas, incluindo nome, data de nascimento, sexo e endereço.
## Tecnologias utilizadas

Spring Boot | PrimeFaces | Maven | H2 | JoinFaces

Pré-requisitos:
JDK 8 ou superior instalado e
Maven 3.6.0 ou superior instalado

## Somente execução do projeto

na pasta raiz do projeto execute o comando:

```bash
  mvn clean install
```
na pasta target que será criada haverá um arquivo chamado: cadastro-pessoa-0.0.1-SNAPSHOT.jar

com o JRE 8 instalado execute o comando:

    java -jar cadastro-pessoa-0.0.1-SNAPSHOT.jar


Abra um navegador de sua escolha e digite a URL http://localhost:8080/index.xhtml para acessar a página inicial da aplicação.
## Instalação

Clone o repositório para sua máquina local.
Abra o projeto em sua IDE de preferência.
Execute o comando:

    mvn clean install 

para baixar as dependências do projeto.

## Executando a aplicação
Navegue até a classe principal

    PessoaApplication.java

Execute a aplicação.
Abra um navegador de sua escolha e digite a URL http://localhost:8080/index.xhtml para acessar a página inicial da aplicação.

## Utilização
Ao acessar a aplicação, a página inicial exibe uma tabela com a lista de pessoas cadastradas. É possível adicionar uma nova pessoa clicando no botão "Nova Pessoa" e preenchendo as informações de nome, data de nascimento, sexo e endereço.

Também é possível editar ou excluir uma pessoa existente através dos botões correspondentes na tabela.

## Estrutura do projeto
**src/main/java:** contém o código-fonte da aplicação.  
**src/main/resources:** contém os arquivos de configuração da aplicação.

## A estrutura de pacotes do projeto é a seguinte:
**com.kumulus:** pacote raiz da aplicação.  
**bean:** contém os beans das telas.  
**repository:** contém as interfaces dos repositórios da aplicação.  
**service:** contém as classes de serviço da aplicação.  
**META-INF/resources:** contém as páginas XHTML da aplicação.

### Uma justificativa para o uso de frameworks ou bibliotecas (caso sejam usadas);
A combinação do Spring Boot com o PrimeFaces oferece uma poderosa plataforma de desenvolvimento para a criação de aplicações web escaláveis e altamente interativas. O Spring Boot simplifica a configuração e o gerenciamento do projeto, enquanto o PrimeFaces oferece componentes prontos para uso e altamente personalizáveis para criar uma experiência do usuário de alta qualidade.  
O Spring Boot é um framework popular para o desenvolvimento de aplicações Java que simplifica a configuração e o desenvolvimento de projetos com o Spring Framework. Com ele, é possível criar rapidamente aplicações robustas e escaláveis com mínima configuração. Além disso, o Spring Boot oferece diversas facilidades, como gerenciamento de dependências, contêiner embutido, monitoramento e logging.
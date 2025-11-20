# Projeto S.A.G.E. - Sistema de Automação e Gerenciamento Energético

<br />

<div align="center">
	<img src="https://i.imgur.com/IaD4lwg.png" title="source: imgur.com" width="35%"/>
</div>
<br />

<div align="center">
  <img src="https://img.shields.io/github/languages/top/queren-alves/sage?style=flat-square" />
</div>

------

<br />

## 1. Descrição

<br />

Este projeto é um sistema em console desenvolvido em Java, criado para gerenciar dispositivos, sensores, ambientes e blocos com foco em eficiência energética e apoio ao ODS 7 da ONU.

O sistema controla automaticamente o acionamento de dispositivos conforme a detecção de presença por sensores e permite que administradores gerenciem toda a estrutura de forma simples e organizada. Usuários comuns têm acesso a consultas e relatórios de consumo energético, incluindo tempo de uso dos dispositivos e impacto da geração de energia por painéis solares.

Este projeto, desenvolvido em **Java**, foca no estudo e aplicação dos conceitos de **Programação Orientada a Objetos (POO)**, incluindo:

- Classes e Objetos;
- Atributos e Métodos;
- Modificadores de Acesso;
- Herança e Polimorfismo;
- Classes Abstratas;
- Interfaces.

Além de servir como um simulador funcional, o projeto oferece uma base prática para compreender os princípios fundamentais da POO aplicados a um cenário realista.

<br />

## 2. Funcionalidades do Projeto

<br />

### 👨‍💼  Administrador

### **Gerenciar Usuários**

-   **Cadastrar Usuário**: cria um novo usuário definindo nome, login,
    senha e tipo (comum ou admin).\
-   **Listar Usuários**: exibe todos os usuários cadastrados.\
-   **Editar Usuário**: permite alteração de dados como nome, senha e
    tipo.\
-   **Excluir Usuário**: remove um usuário específico do sistema.

### **Gerenciar Blocos**

-   **Cadastrar Bloco**: registra novos blocos que agrupam ambientes.\
-   **Listar Blocos**: mostra todos os blocos cadastrados.\
-   **Editar Bloco**: atualiza informações.\
-   **Excluir Bloco**: remove um bloco existente.

### **Gerenciar Ambientes**

-   **Cadastrar Ambiente**: cria ambientes com nome, área e consumo
    base.\
-   **Listar Ambientes**: exibe todos os ambientes registrados.\
-   **Editar Ambiente**: permite alterar informações do ambiente.\
-   **Excluir Ambiente**: remove um ambiente.\
-   **Vincular Ambiente a um Bloco**: conecta um ambiente a um bloco
    específico.

### **Gerenciar Sensores**

-   **Cadastrar Sensor**: registra sensores de presença associados a um
    ambiente.\
-   **Listar Sensores**: mostra todos os sensores cadastrados.\
-   **Editar Sensor**: altera configurações do sensor.\
-   **Excluir Sensor**: remove o sensor do sistema.\
-   **Vincular Sensor a Ambiente**: adiciona um sensor ao ambiente
    desejado.

### **Gerenciar Dispositivos**

-   **Cadastrar Dispositivo**: adiciona dispositivos com potência e
    características próprias.\
-   **Listar Dispositivos**: exibe todos os dispositivos cadastrados.\
-   **Editar Dispositivo**: altera suas propriedades.\
-   **Excluir Dispositivo**: remove o dispositivo.\
-   **Vincular Dispositivo a Ambiente**: organiza dispositivos por
    ambiente.

### **Gerenciar Painéis Solares**

-   **Cadastrar Painel Solar**: registra painéis solares com sua
    capacidade de geração.\
-   **Listar Painéis Solares**: mostra todos os painéis cadastrados.\
-   **Excluir Painel Solar**: remove o painel selecionado.\
-   **Vincular Painel Solar a Ambientes**: inclui a energia gerada nos
    cálculos dos relatórios.

------------------------------------------------------------------------

### 👤 Usuário Comum

### **Acesso às Listagens**

O usuário comum pode visualizar todos os dados:

-   Blocos\
-   Ambientes\
-   Dispositivos\
-   Sensores\
-   Painéis solares\
-   Usuários (somente consulta)

### **Relatórios**

-   **Relatório de Consumo por Ambiente**: calcula o consumo diário de
    cada ambiente com base:
    -   nos dispositivos presentes\
    -   no tempo em que ficaram ligados\
    -   no acionamento automático via sensores\
-   **Custo Total com Desconto de Energia Solar** (se houver painel
    vinculado)

<br />

## 3. Relação com o ODS 7

Este projeto está alinhado ao **Objetivo de Desenvolvimento Sustentável
7: Energia Acessível e Limpa**, pois:

-   incentiva a automação inteligente do consumo energético;\
-   permite monitoramento preciso do gasto de energia;\
-   integra geração de energia renovável por painéis solares;\
-   apoia decisões mais sustentáveis em ambientes e edifícios.

<br />

## 4. Requisitos

<br />

Para executar os códigos localmente, você precisará de:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Eclipse](https://eclipseide.org/) ou [STS](https://spring.io/tools)

<br />

## 5. Como Executar o projeto no Eclipse/STS

<br />

### 5.1. Importando o Projeto

1. Clone o repositório do Projeto [S.A.G.E.](https://github.com/queren-alves/sage) dentro da pasta do *Workspace* do Eclipse/STS

```bash
git clone https://github.com/queren-alves/sage.git
```

2. **Abra o Eclipse/STS** e selecione a pasta do *Workspace* onde você clonou o repositório do projeto
3. No menu superior do Eclipse/STS, clique na opção: **File 🡲 Import...**
4. Na janela **Import**, selecione a opção: **General 🡲 Existing Projects into Workspace** e clique no botão **Next**
5. Na janela **Import Projects**, no item **Select root directory**, clique no botão **Browse...** e selecione a pasta do Workspace onde você clonou o repositório do projeto
6. O Eclipse/STS reconhecerá automaticamente o projeto
7. Marque o Projeto Conta Bancária no item **Projects** e clique no botão **Finish** para concluir a importação

<br />

### 5.2. Executando o projeto

1. Na guia **Package Explorer**, localize o Projeto Conta Bancária
2. Abra a **Classe Menu**
3. Clique no botão **Run** <img src="https://i.imgur.com/MtBQjUp.png" title="source: imgur.com" width="3%"/> para executar a aplicação
4. Caso seja perguntado qual é o tipo do projeto, selecione a opção **Java Application**
5. O console exibirá o menu do Projeto.

<br />

## 6. Contribuição

<br />

Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:

- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java.

<br />

##  9. Contato

<br />

Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue.
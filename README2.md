
# **1. Objetivos:**

* Crição de projeto base para estudos e aprimoramento do conhecimento dos fundamentos e do funcionamento base de um projeto WS;
* Criar e desenvolver arquitetura base para projeto WS com base em implementação Java EE utilizando servidor de aplicações Wildfly 10.1.0.Final;
* Desenvolver e estudar o funcionamento do Docker e como embarcar aplicações WS utilizando Wildfly container e MySQL server container;


# **2. Executando o projeto:**

## **2.1 EXECUTAR PROJETO COM WILDFLY:**

### **Passos:**
1 - Baixe o Wildfly 10.1.0.Final através do link http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip;
2 - Extrair em pasta;
3 - Copiar conteúdo data pasta 'config/wildfly' para pasta raiz onde foi extraído conteúdo do 'wildfly-10.1.0.Final.zip';
4 - Copiar arquivo 'config/wildfly/modelo-dev-ds.xml' para pasta 'raiz/standalone/deployments';
5 - Abrir o cmd, navegar até pasta raiz do projeto e executar comando mvn -e clean install -P dev;
6 - Copiar arquivo 'target/modelo.war' para pasta 'raiz/standalone/deployments';
7 - Navegar no cmd até pasta 'raiz/bin' e execute comnando standalone.sh -b 0.0.0.0;
8 - Abra o browser e informe a URL localhost:8080/modelo;

## **2.1 EXECUTAR PROJETO COM DOCKER:**

### **Passos:**
1 - Baixe e instale o Docker;
2 - Abra o cmd, nabegue até a pasta raiz do projeto;
3 - Execute o comando 'mvn -e clean install -P docker';
4 - Execute o comando 'docker build -t banco config/banco/.';
5 - Execute o comando 'docker run --name banco -d banco';
6 - Execute o comando 'docker build -t projeto-modelo .';
7 - Execute o comando 'docker run --name app --link banco -p 80:8080 -p 9990:9990 -it projeto-modelo';
8 - Abra o browser e informe a URL localhost;

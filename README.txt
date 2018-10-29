--------------------------------------------------------------------------------------
|####################################################################################|
|######| EXECUTAR O PROJETO |########################################################|
|####################################################################################|
--------------------------------------------------------------------------------------

1		EXECUTANDO O PROJETO:

1.1 	EXECUTAR PROJETO COM WILDFLY:
1.1.1	Baixe o Wildfly 10.1.0.Final através do link http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip;
1.1.2	Extrair em pasta;
1.1.3	Copiar conteudo data pasta 'config/wildfly' para pasta raiz onde foi extraído conteúdo do 'wildfly-10.1.0.Final.zip';
1.1.4	Copiar arquivo 'config/wildfly/modelo-dev-ds.xml' para pasta 'raiz/standalone/deployments';
1.1.5	Abrir o cmd, navegar até pasta raiz do projeto e executar comando mvn -e clean install -P dev;
1.1.6	Copiar arquivo 'target/modelo.war' para pasta 'raiz/standalone/deployments';
1.1.7	Navegar no cmd até pasta 'raiz/bin' e execute comnando standalone.sh -b 0.0.0.0;
1.1.8	Abra o browser e informe a URL localhost:8080/modelo;

1.2 	EXECUTAR PROJETO COM DOCKER:
1.2.1	Baixe e instale o Docker;
1.2.2	Abra o cmd, nabegue até a pasta raiz do projeto;
1.2.3	Execute o comando 'mvn -e clean install -P docker';
1.2.4	Execute o comando 'docker build -t banco config/banco/.';
1.2.5	Execute o comando 'docker run --name banco -d banco';
1.2.6	Execute o comando 'docker build -t projeto-modelo .';
1.2.7	Execute o comando 'docker run --name app --link banco -p 80:8080 -p 9990:9990 -it projeto-modelo';
1.1.8	Abra o browser e informe a URL localhost;

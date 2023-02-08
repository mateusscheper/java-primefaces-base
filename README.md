# JSF/PrimeFaces
## _Project base para iniciar aplicações de JSF com PrimeFaces_

---

O objetivo dessa aplicação é criar rapidamente projetos de JSF com [PrimeFaces](https://www.primefaces.org/) sem precisar fazer todas as configurações manualmente. 
O projeto conta com uma página de [login](./web/login.xhtml), uma página [home](./web/index.xhtml) e uma página de [registro](./web/registrar.xhtml). 
A autenticação e autorização do sistema é feita pelo [Apache Shiro](https://shiro.apache.org/).

## Tecnologias
- Servidor [WildFly](https://www.wildfly.org/)
- Java 8+
- Banco de dados [PostgreSQL](https://www.postgresql.org/)
- [PrimeFaces 12](https://www.primefaces.org/)
- [Apache Shiro](https://shiro.apache.org/)
- [Lombok](https://projectlombok.org/)

## Rodando a aplicação
- Configure o servidor WildFly indo no arquivo standalone.xml e criando o datasource _BaseDS_ conforme utilizado no [persistence.xml](./src/main/resources/META-INF/persistence.xml) da aplicação;
- Crie um banco de dados no servidor PostgreSQL conforme seu datasource _BaseDS_. Também crie um schema de nome _base_, conforme indicado no _@Table_ da entidade [Usuario](./src/main/java/scheper/mateus/entity/Usuario.java);
- Gere o artefato da aplicação através do Maven e faça o deploy no WildFly manualmente ou através de sua IDE;
- Acesse sua aplicação via _localhost_ na porta configurada no servidor (geralmente 8080).

## Atualização e manutenção
Sendo que o JSF é considerado legado, não pretendo atualizar versões dessa aplicação de template. Ainda assim, caso encontre algum problema, crie um _issue_ aqui no GitHub ou entre em contato comigo via [LinkedIn](https://www.linkedin.com/in/mateusscheper/) que eu realizo a correção assim que possível.

# Geranciador de Tarefas Spring

Um gerenciador de tarefas simples feito em Spring. Seguindo a semana TreinaWeb - Java & Spring

### Banco de Dados
Para utilizar a aplicação é preciso um banco de dados. Pode ser utilizado um docker de MySQL:

	docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=gerenciador_tarefas -v /var/lib/mysql:/var/lib/mysql -d -p 3306:3306 mysql:8.0

Ou um docker PostgreSQL:

	docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres

Com o banco instalado e configurado, para criar as tabelas execute o script em: 

	db/1-create-database.sql

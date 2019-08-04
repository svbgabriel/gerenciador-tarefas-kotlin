# Geranciador de Tarefas Spring

Um gerenciador de tarefas simples feito em Spring. Seguindo a semana TreinaWeb - Java & Spring

### Banco de Dados
Para utilizar a aplicação é preciso um banco de dados. Pode ser utilizado um docker de MySQL:

	docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=gerenciador_tarefas -v /var/lib/mysql:/var/lib/mysql -d -p 3306:3306 mysql:8.0

Com o banco instalado e configurado, para criar as tabelas execute o script em: 

	db/1-create-database.sql

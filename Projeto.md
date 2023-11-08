Criar como Java with Ant>Java Web>Web APP;

Controller: Servlet com um switch para cada operação (CRUD), que chama um método do DAO, define um parâmetro na request como o valor retornado pelo método (tipo uma lista de itens num GET), e redireciona para o JSP;
DAO: Classe que realiza operações no banco. Instanciada dentro do Controller;
DB: Classe da conexão com o banco de dados;
JSP: View HTML;
VO: Classe que é exibida para a View;

-------
3 TABELAS
# customers
id
name
cpf
phone

# vehicles
id
make
model
plate
daily_rate

# rents
id
initial_date
final_date
status
id_cliente
id_veiculo
total_rate (daily rate * quantidade dias)

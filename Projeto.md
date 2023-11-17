Criar como Java with Ant>Java Web>Web APP;

Controller: Servlet com um switch para cada operação (CRUD), que chama um método do DAO, define um parâmetro na request como o valor retornado pelo método (tipo uma lista de itens num GET), e redireciona para o JSP;
DAO: Classe que realiza operações no banco. Instanciada dentro do Controller;
DB: Classe da conexão com o banco de dados;
JSP: View HTML;
VO: Classe que é exibida para a View;

---

- Ajustar design;

3 TABELAS

# customers

id (int)
name (varchar)
cpf (varchar)
phone (varchar)

# vehicles

id (int)
make (varchar)
model (varchar)
plate (varchar)
daily_rate (float)

# rents

id (int)
initial_date (date)
final_date (date)
status (boolean - tinyint)
id_cliente (int)
id_veiculo (int)
total_rate (daily rate \* quantidade dias) (float)
card_owner (varchar)
card_number (varchar)
card_exp (varchar)
card_code (int)

- Vai pegar o id_cliente pelo cpf - Fazer busca pelo cpf na hora de salvar;
- total_rate vai ser calculada na função antes de mandar pro banco;
- Talvez seja o caso de digitar os valores nos campos, clicar em enviar, redirecionar p/ mesma tela porém com o nome do usuário e a taxa total, pra daí confirmar;

- Criar Controller, VO e DAO;
- Criar tela de listagem;
- Criar tela de cadastro;
- Criar tela de alteração;
- Criar exclusão;

- Formulário cadastrar aluguel:
  Carro
  Data Inicial
  Data Final
  CPF
  Titular do Cartão
  Número do Cartão
  Vencimento
  CVV

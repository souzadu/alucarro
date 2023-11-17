<%@page import="java.util.List"%>
<%@page import="VO.CustomerVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <nav class="navbar">
            <h1 class="logo">AluCarro</h1>
        </nav>
        <main class="main">
            <aside class="aside">
                <form id="customers" name="customers" method="get" action="CustomerController">                    
                    <button type="submit" value="find-all" name="operation">Clientes</button>
                </form>
                <form name="vehicles" method="get" action="CustomerController">
                    <button type="submit" value="find-all" name="operation">Veículos</button>
                </form>
                <form name="rents" method="get" action="CustomerController">
                    <button type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside>
            <div class="main-content">
                <h2>Cadastrar Cliente</h2>
                <form class="form" name="create" method="post" action="CustomerController">
                    <label for="name">Nome</label>
                    <input id="name" name="name">
                    <label for="cpf">CPF</label>
                    <input id="cpf" name="cpf">
                    <label id="phone">Celular</label>
                    <input id="phone" name="phone">
                    <button type="submit" value="create" name="operation">Cadastrar</button>
                </form>
            </div>
        </main>
    </body>
</html>

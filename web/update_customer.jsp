<%@page import= "VO.CustomerVO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <nav class="navbar">
            <h1 class="logo">AluCarro</h1>
        </nav>
        <%
            CustomerVO customer = (CustomerVO) request.getAttribute("customer");
        %>

        <main class="main">
            <aside class="aside">
                <form id="customers" name="customers" method="get" action="CustomerController">                    
                    <button class="myButton" type="submit" value="find-all" name="operation">Clientes</button>
                </form>
                <form name="vehicles" method="get" action="CustomerController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Veículos</button>
                </form>
                <form name="rents" method="get" action="RentController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside>
            <div class="main-content">
                <h2>Alterar Cliente</h2>
                <form class="form" name="update" method="post" action="CustomerController">
                    <input type="hidden" name="id" value="<%=customer.getId()%>">
                    <label for="name">Nome</label>
                    <input id="name" name="name" value="<%=customer.getName()%>">
                    <label for="cpf">CPF</label>
                    <input id="cpf" name="cpf" value="<%=customer.getCpf()%>">
                    <label id="phone">Celular</label>
                    <input id="phone" name="phone" value="<%=customer.getPhone()%>">
                    <button class="myButton__alterar" type="submit" value="update" name="operation">Alterar</button>
                </form>
            </div>            
        </main>
    </body>
</html>

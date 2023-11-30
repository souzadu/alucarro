<%@page import="java.util.List"%>
<%@page import="VO.CustomerVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Clientes</title>
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
                    <button class="myButton"type="submit" value="find-all" name="operation">Clientes</button>
                </form>
                <form  id="vehicles" name="vehicles" method="get" action="VehicleController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Veículos</button>
                </form>
                <form name="rents" method="get" action="RentController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside> 
            <div class="main-content">
                <div class="main-topbar" style="width:70%">
                    <button class="myButton"><a href="./create_customer.jsp">Cadastrar</a></button>
                </div>
                <%
                    List customers = (List) request.getAttribute("list");

                    out.print("<table class='tabela' width=\"70%\"  cellspacing=\"0\">");
                    out.print("<tr>");
                    out.print("<th class='tabela__header'>Nome</th>");
                    out.print("<th class='tabela__header'>CPF</th>");
                    out.print("<th class='tabela__header'>Celular</th>");
                    out.print("<th class='tabela__header__acoes'' colspan='2'>Ações</th>");
                    out.print("</tr>");

                    if (customers == null) {
                        out.print("<tr><td class='error' colspan='3'>Nenhum Cliente encontrado</td></tr>");
                    }

                    for (int cont = 0; cont < customers.size(); cont++) {
                        CustomerVO customer = new CustomerVO();
                        customer = (CustomerVO) customers.get(cont);
                         String classeLinha = (cont % 2 == 0) ? "tabela__linha-par" : "tabela__linha-impar";
                        out.print("<tr class='" + classeLinha + "'>");
                        out.print("<td>" + customer.getName() + "</td>");
                        out.print("<td>" + customer.getCpf() + "</td>");
                        out.print("<td>" + customer.getPhone() + "</td>");
                        out.print("<td><a class='myButton__alterar' href=\"CustomerController?operation=find-by-id&id="+customer.getId()+"\">Alterar</a></td>");
                        out.print("<td ><a class='myButton__excluir' href=\"CustomerController?operation=delete&id="+customer.getId()+"\">Excluir</a></td>");
                        out.print("</tr>");
                    }

                    out.print("</table>");
                %>                
            </div>
        </main>
    </body>
</html>

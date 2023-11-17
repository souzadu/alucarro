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
                <div class="main-topbar">
                    <button><a href="./create_customer.jsp">Cadastrar</a></button>
                </div>
                <%
                    List customers = (List) request.getAttribute("list");

                    out.print("<table width=\"50%\" border=\"1\" cellspacing=\"0\">");
                    out.print("<tr>");
                    out.print("<th>Nome</th>");
                    out.print("<th>CPF</th>");
                    out.print("<th>Celular</th>");
                    out.print("<th colspan='2'>Ações</th>");
                    out.print("</tr>");

                    if (customers == null) {
                        out.print("<tr><td class='error' colspan='3'>Nenhum Cliente encontrado</td></tr>");
                    }

                    for (int cont = 0; cont < customers.size(); cont++) {
                        CustomerVO customer = new CustomerVO();
                        customer = (CustomerVO) customers.get(cont);
                        out.print("<tr>");
                        out.print("<td>" + customer.getName() + "</td>");
                        out.print("<td>" + customer.getCpf() + "</td>");
                        out.print("<td>" + customer.getPhone() + "</td>");
                        out.print("<td><a href=\"CustomerController?operation=find-by-id&id="+customer.getId()+"\">Alterar</a></td>");
                        out.print("<td><a href=\"CustomerController?operation=delete&id="+customer.getId()+"\">Excluir</a></td>");
                        out.print("</tr>");
                    }

                    out.print("</table>");
                %>                
            </div>
        </main>
    </body>
</html>

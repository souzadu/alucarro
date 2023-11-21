<%@page import="java.util.List"%>
<%@page import="VO.RentVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Aluguéis</title>
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
                <form name="vehicles" method="get" action="VehicleController">
                    <button type="submit" value="find-all" name="operation">Veículos</button>
                </form>
                <form name="rents" method="get" action="RentController">
                    <button type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside> 
            <div class="main-content">
                <div class="main-topbar">
                    <form name="create_rent" method="get" action="RentController">
                        <button type="submit" value="create-step-one" name="operation">Cadastrar</a></button>
                    </form>                    
                </div>
                <%
                    List rents = (List) request.getAttribute("list");

                    out.print("<table width=\"50%\" border=\"1\" cellspacing=\"0\">");
                    out.print("<tr>");
                    out.print("<th>Cliente</th>");
                    out.print("<th>Veículo</th>");
                    out.print("<th>Data Inicial</th>");
                    out.print("<th>Data Final</th>");
                    out.print("<th>Status</th>");
                    out.print("<th>Valor total</th>");
                    out.print("<th colspan='2'>Ações</th>");
                    out.print("</tr>");

                    if (rents == null) {
                        out.print("<tr><td class='error' colspan='3'>Nenhum veículo encontrado</td></tr>");
                    }

                    for (int cont = 0; cont < rents.size(); cont++) {
                        RentVO rent = new RentVO();
                        rent = (RentVO) rents.get(cont);
                        out.print("<tr>");
                        out.print("<td>" + rent.getCustomerName() + "</td>");
                        out.print("<td>" + rent.getCar() + "</td>");
                        out.print("<td>" + rent.getInitialDate() + "</td>");
                        out.print("<td>" + rent.getFinalDate() + "</td>");
                        out.print("<td>" + RentVO.convertStatus(rent.isStatus()) + "</td>");
                        out.print("<td>R$" + rent.getTotalRate() + "</td>");
                        out.print("<td><a href=\"RentController?operation=update-step-one&id="+rent.getId()+"\">Alterar</a></td>");
                        out.print("<td><a href=\"RentController?operation=delete&id="+rent.getId()+"\">Excluir</a></td>");
                        out.print("</tr>");
                    }

                    out.print("</table>");
                %>                
            </div>
        </main>
    </body>
</html>

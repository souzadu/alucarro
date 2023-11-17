<%@page import="java.util.List"%>
<%@page import="VO.VehicleVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Veículos</title>
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
                <form name="rents" method="get" action="CustomerController">
                    <button type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside> 
            <div class="main-content">
                <div class="main-topbar">
                    <button><a href="./create_vehicle.jsp">Cadastrar</a></button>
                </div>
                <%
                    List vehicles = (List) request.getAttribute("list");

                    out.print("<table width=\"50%\" border=\"1\" cellspacing=\"0\">");
                    out.print("<tr>");
                    out.print("<th>Marca</th>");
                    out.print("<th>Modelo</th>");
                    out.print("<th>Placa</th>");
                    out.print("<th>Valor da diária</th>");
                    out.print("<th colspan='2'>Ações</th>");
                    out.print("</tr>");

                    if (vehicles == null) {
                        out.print("<tr><td class='error' colspan='3'>Nenhum veículo encontrado</td></tr>");
                    }

                    for (int cont = 0; cont < vehicles.size(); cont++) {
                        VehicleVO vehicle = new VehicleVO();
                        vehicle = (VehicleVO) vehicles.get(cont);
                        out.print("<tr>");
                        out.print("<td>" + vehicle.getMake() + "</td>");
                        out.print("<td>" + vehicle.getModel() + "</td>");
                        out.print("<td>" + vehicle.getPlate() + "</td>");
                        out.print("<td>" + vehicle.getDailyRate() + "</td>");
                        out.print("<td><a href=\"VehicleController?operation=find-by-id&id="+vehicle.getId()+"\">Alterar</a></td>");
                        out.print("<td><a href=\"VehicleController?operation=delete&id="+vehicle.getId()+"\">Excluir</a></td>");
                        out.print("</tr>");
                    }

                    out.print("</table>");
                %>                
            </div>
        </main>
    </body>
</html>

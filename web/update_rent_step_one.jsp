<%@page import="java.util.List"%>
<%@page import="VO.VehicleVO"%>
<%@page import="VO.RentVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Aluguel</title>
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
                    <button class="myButton" type="submit" value="find-all" name="operation">Clientes</button>
                </form>
                <form name="vehicles" method="get" action="VehicleController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Veículos</button>
                </form>
                <form name="rents" method="get" action="RentController">
                    <button class="myButton" type="submit" value="find-all" name="operation">Aluguéis</button>
                </form>
            </aside>
            <div class="main-content">
                <h2>Alterar Aluguel</h2>
                <form class="form" name="create" method="post" action="RentController">
                    <label for="vehicle">Carro</label>                    
                    <%
                        List vehicles = (List) request.getAttribute("vehicles");
                        RentVO rent = (RentVO) request.getAttribute("rent");
                        out.print("<input type='hidden' name='rent_id' value='"+rent.getId()+"'>");
                        out.print("<select name='vehicle' id='vehicle'>");
                        if (vehicles != null && !vehicles.isEmpty()) {
                            for (int cont = 0; cont < vehicles.size(); cont++) {
                                VehicleVO vehicle = new VehicleVO();
                                vehicle = (VehicleVO) vehicles.get(cont);                            
                                out.print("<option value='"+vehicle.getId()+"'");
                                if (vehicle.getId() == rent.getIdVehicle()) {
                                    out.print(" selected");
                                }
                                out.print(">");
                                out.print(vehicle.getMake() + " " + vehicle.getModel() + " - " + vehicle.getPlate() + " - R$" + vehicle.getDailyRate());
                                out.print("</option>");
                            }
                       } else {
                            out.println("Nenhum veículo disponível.");
                       }
                    out.print("</select>");
                    out.print("<label for='initial_date'>Data Inicial</label>");
                    out.print("<input id='nitial_date' name='initial_date' type='date' value='"+rent.getInitialDate()+"'>");
                    out.print("<label for='final_date'>Data Final</label>");
                    out.print("<input id='final_date' name='final_date' type='date' value='"+rent.getFinalDate()+"'>");
                    out.print("<label for='cpf'>CPF</label>");
                    out.print("<input id='cpf' name='cpf' value='"+rent.getCustomerCpf()+"'>");
                     %>
                    <button class="myButton" type="submit" value="update-step-two" name="operation">Próximo</button>
                </form>
            </div>
        </main>
    </body>
</html>

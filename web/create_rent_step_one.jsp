<%@page import="java.util.List"%>
<%@page import="VO.VehicleVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Aluguel</title>
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
                <h2>Cadastrar Aluguel</h2>
                <form class="form" name="create" method="post" action="RentController">
                    <label for="vehicle">Carro</label>
                    <select name="vehicle" id="vehicle">
                    <%
                        List vehicles = (List) request.getAttribute("list");
                        if (vehicles != null && !vehicles.isEmpty()) {
                            for (int cont = 0; cont < vehicles.size(); cont++) {
                                VehicleVO vehicle = new VehicleVO();
                                vehicle = (VehicleVO) vehicles.get(cont);                            
                                out.print("<option value='"+vehicle.getId()+"'>");
                                out.print(vehicle.getMake() + " " + vehicle.getModel() + " - " + vehicle.getPlate() + " - R$" + vehicle.getDailyRate());
                                out.print("</option>");
                            }
                       } else {
                            out.println("Nenhum veículo disponível.");
                       }
                     %>
                    </select>
                    <label for="initial_date">Data Inicial</label>
                    <input id="initial_date" name="initial_date" type="date">
                    <label for="final_date">Data Final</label>
                    <input id="final_date" name="final_date" type="date">
                    <label for="cpf">CPF</label>
                    <input id="cpf" name="cpf">
                    <button class="myButton" type="submit" value="create-step-two" name="operation">Próximo</button>
                </form>
            </div>
        </main>
    </body>
</html>

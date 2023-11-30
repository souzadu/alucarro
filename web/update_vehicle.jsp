<%@page import= "VO.VehicleVO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alterar Veículo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <nav class="navbar">
            <h1 class="logo">AluCarro</h1>
        </nav>
        <%
            VehicleVO vehicle = (VehicleVO) request.getAttribute("vehicle");
        %>

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
                <h2>Alterar Veículo</h2>
                <form class="form" name="update" method="post" action="VehicleController">
                    <input type="hidden" name="id" value="<%=vehicle.getId()%>">
                    <label for="make">Marca</label>
                    <input id="make" name="make" value="<%=vehicle.getMake()%>">
                    <label for="model">Modelo</label>
                    <input id="model" name="model" value="<%=vehicle.getModel()%>">
                    <label id="plate">Placa</label>
                    <input id="plate" name="plate" value="<%=vehicle.getPlate()%>">
                    <label id="daily_rate">Valor da diária</label>
                    <input id="daily_rate" name="daily_rate" value="<%=vehicle.getDailyRate()%>">
                    <button class="myButton__alterar" type="submit" value="update" name="operation">Alterar</button>
                </form>
            </div>            
        </main>
    </body>
</html>

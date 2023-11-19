<%@page import="java.util.List"%>
<%@page import="VO.VehicleVO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Veículo</title>
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
                <h2>Cadastrar Veículo</h2>
                <form class="form" name="create" method="post" action="VehicleController">
                    <label for="make">Marca</label>
                    <input id="make" name="make">
                    <label for="model">Modelo</label>
                    <input id="model" name="model">
                    <label id="plate">Placa</label>
                    <input id="plate" name="plate">
                    <label id="daily_rate">Valor da diária</label>
                    <input id="daily_rate" name="daily_rate">
                    <button type="submit" value="create" name="operation">Cadastrar</button>
                </form>
            </div>
        </main>
    </body>
</html>

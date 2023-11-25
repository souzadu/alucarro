<%@page import="java.util.List"%>
<%@page import="VO.VehicleVO"%>
<%@page import="VO.RentVO"%>
<%@page import="VO.CustomerVO"%>

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
                <h2>Alterar Aluguel</h2>
                <form class="form" name="create" method="post" action="RentController">                
                    <%
                    RentVO rent = (RentVO) request.getAttribute("rent");
                    VehicleVO vehicle = (VehicleVO) request.getAttribute("vehicle");
                    String initialDate = (String) request.getAttribute("initial_date");
                    String finalDate = (String) request.getAttribute("final_date");
                    float totalRate = (float) request.getAttribute("total_rate");
                    String cpf = (String) request.getAttribute("cpf");
                    CustomerVO customer = (CustomerVO) request.getAttribute("customer");

                    out.print("<label for='vehicle'>Carro</label>");
                    out.print("<select name='vehicle_d' id='vehicle_d' disabled>");
                        out.print("<option>");
                        out.print(vehicle.getMake() + " " + vehicle.getModel() + " - " + vehicle.getPlate() + " - R$" + vehicle.getDailyRate());
                        out.print("</option>");
                    out.print("</select>");
                    out.print("<input type='hidden' name='rent_id' value='"+rent.getId()+"'>");
                    out.print("<input id='vehicle' name='vehicle' hidden value='"+vehicle.getId()+"'>");
                    out.print("<label for='initial_date'>Data Inicial</label>");
                    out.print("<input id='initial_date_d' name='initial_date_d' type='date' disabled value='"+initialDate+"'>");
                    out.print("<input id='initial_date' name='initial_date' type='date' hidden value='"+initialDate+"'>");
                    out.print("<label for='final_date'>Data Final</label>");
                    out.print("<input id='final_date_d' name='final_date_d' type='date' disabled value='"+finalDate+"'>");
                    out.print("<input id='final_date' name='final_date' type='date' hidden value='"+finalDate+"'>");
                    out.print("<label for='cpf'>CPF</label>");
                    out.print("<input id='cpf' name='cpf' disabled value='"+cpf+"'>");
                    out.print("<label for='customer'>Cliente</label>");
                    out.print("<input id='customer' name='customer' disabled value='"+customer.getName()+"'>");
                    out.print("<label for='total_rate'>Valor total</label>");
                    out.print("<input id='total_rate_d' name='total_rate_d' disabled value='R$"+totalRate+"'>");
                    out.print("<input id='total_rate' name='total_rate' hidden value='R$"+totalRate+"'>");
                    out.print("<input id='customer_id' name='customer_id' hidden value='"+customer.getId()+"'>");
                    out.print("<label for='card_number'>Número do Cartão</label>");
                    out.print("<input id='card_number' name='card_number' value='"+rent.getCardNumber()+"'>");
                    out.print("<label for='card_owner'>Titular do Cartão</label>");
                    out.print("<input id='card_owner' name='card_owner' value='"+rent.getCardOwner()+"'>");
                    out.print("<label for='card_exp'>Data de expiração do Cartão</label>");
                    out.print("<input id='card_exp' name='card_exp' value='"+rent.getCardExp()+"'>");
                    out.print("<label for='card_cvv'>CVV do Cartão</label>");
                    out.print("<input id='card_cvv' name='card_cvv' value='"+rent.getCardCvv()+"'>");
                    out.print("<label for='status'>Status</label>");
                    out.print("<select name='status' id='status'>");                    
                    out.print("<option value='false' " + (rent.isStatus() ? "selected" : "") + ">");
                    out.print("Pendente");
                    out.print("</option>");
                    out.print("<option value='true' " + (rent.isStatus() ? "selected" : "") + ">");
                    out.print("Finalizado");
                    out.print("</option>");                    
                    out.print("</select>");                    
                     %>
                    <button type="submit" value="update" name="operation">Próximo</button>
                </form>
            </div>
        </main>
    </body>
</html>

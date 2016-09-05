<%-- 
    Document   : navbar
    Created on : 05/09/2016, 19:36:24
    Author     : Priscila Gouveia <priscilaggouveia@gmail.com>
--%>

<div class="navbar navbar-fixed-top navbarIndex" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand brand" href="index.jsp">BoaCompra.com</a>
        </div>
        <div class="navbar-collapse collapse">
            <a href="login.html" class="navbar-text">Olá, Visitante! Entre ou Cadastre-se!</a>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="opcao" href="index.jsp">Home</a></li>
                <li>
                    <a href="meucarrinho.jsp">
                        <img src="imagens/shopping-cart.png" height="35" width="35">(${carrinho.qtdItens})
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>

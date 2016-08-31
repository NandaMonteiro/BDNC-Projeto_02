/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.Carrinho;
import entidades.Cliente;
import entidades.Compra;
import entidades.Pagamento;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
public class RealizarCompra implements Command{
    
     @Override
     public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            Carrinho carrinho = null;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            if (lojaService.buscarIdSessao().equals(idSessao)) {
                carrinho = lojaService.buscarCarrinhoDeCompras();
            }

            if (carrinho != null) {
                Compra compra = new Compra();
                compra.setCarrinho(carrinho);
                compra.setCliente((Cliente)request.getSession().getAttribute("cliente"));
                compra.setEnderecoParaEntrega(compra.getCliente().getEndereco());
                Pagamento pagamento = new Pagamento();
                pagamento.setEmpresa(request.getParameter("empresa"));
                pagamento.setTitular(request.getParameter("titular"));
                pagamento.setNumero(Integer.parseInt(request.getParameter("numero")));
                pagamento.setCodSeguranca(request.getParameter("codigo"));
                compra.setPagamento(pagamento);
                lojaService.salvarCompra(compra);
                lojaService.limparCarrinho(carrinho);
                request.getSession().removeAttribute("carrinho");
                response.sendRedirect("");
            }
            
            response.sendRedirect("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.Carrinho;
import entidades.Produto;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
public class AlterarQtdeProduto implements Command{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            Carrinho carrinhoDeCompras;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            if (lojaService.buscarIdSessao().equals(idSessao)) {
                carrinhoDeCompras = lojaService.buscarCarrinhoDeCompras();
                if (carrinhoDeCompras == null) {
                    carrinhoDeCompras = new Carrinho();
                }
            } else {
                carrinhoDeCompras = new Carrinho();
            }

            Produto produto = lojaService.buscarProduto(Long.parseLong(request.getParameter("idProduto")));
            carrinhoDeCompras.getProdutos().remove(produto);
            int qtd = Integer.parseInt(request.getParameter("quantidade"));
            if (qtd == 0) {
                carrinhoDeCompras = lojaService.removerProdutoCarrinho(carrinhoDeCompras, produto);
            } else {
                produto.setQtdeVenda(qtd);
                carrinhoDeCompras.addProduto(produto);
            }
            
            lojaService.salvarCarrinhoDeCompras(carrinhoDeCompras);

            request.getSession().setAttribute("carrinho", carrinhoDeCompras);
            RequestDispatcher dispatcher = request.getRequestDispatcher("");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

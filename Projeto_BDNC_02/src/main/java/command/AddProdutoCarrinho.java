/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.Carrinho;
import entidades.Produto;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
public class AddProdutoCarrinho {
    
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            Carrinho carrinhoDeCompras = null;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            String idSalvo = lojaService.buscarIdSessao();
            if (idSalvo != null && idSalvo.equals(idSessao)) {
                carrinhoDeCompras = lojaService.buscarCarrinhoDeCompras();
            }

            if (carrinhoDeCompras == null) {
                carrinhoDeCompras = new Carrinho();
            }

            List<Produto> produtos = carrinhoDeCompras.getProdutos();
            long idProduto = Long.parseLong(request.getParameter("idProduto"));
            Produto produto = null;
            for (Produto p : produtos) {
                if (p.getId() == idProduto) {
                    p.setQtdeVenda(p.getQtdeVenda() + 1);
                    produto = p;
                    break;
                }

            }
            if (produto == null) {
                produto = lojaService.buscarProduto(idProduto);
                produto.setQtdeVenda(1);
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

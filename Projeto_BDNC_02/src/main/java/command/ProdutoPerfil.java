/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.Produto;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
public class ProdutoPerfil implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            LojaService lojaService = new LojaService();
            Produto produto = lojaService.buscarProduto(Long.parseLong(request.getParameter("idProduto")));
            request.setAttribute("produto", produto);            
            request.setAttribute("produtosSugeridos", lojaService.buscarProdutosSugeridos(produto));

            RequestDispatcher dispather = request.getRequestDispatcher("");
            dispather.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

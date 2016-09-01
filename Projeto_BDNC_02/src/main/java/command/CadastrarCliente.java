/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entidades.Cliente;
import entidades.Endereco;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ClienteService;

/**
 *
 * @author NandaPC
 */
public class CadastrarCliente implements Command{
    
     @Override
     public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            Cliente cliente = new Cliente();            
            cliente.setNome(request.getParameter("nome"));
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setEmail(request.getParameter("email"));
            cliente.setSenha(request.getParameter("senha"));
            
            Endereco endereco = new Endereco();
            endereco.setRua(request.getParameter("rua"));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCidade(request.getParameter("cidade"));
            endereco.setNumero(request.getParameter("numero"));
            endereco.setEstado(request.getParameter("estado"));
            
            cliente.setEndereco(endereco);
            
            ClienteService clienteService = new ClienteService();            
            clienteService.salvar(cliente);
            
            request.getSession().setAttribute("cliente", clienteService.buscarPorEmail(cliente.getEmail()));
            
            RequestDispatcher dispather = request.getRequestDispatcher("index.html");
            dispather.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

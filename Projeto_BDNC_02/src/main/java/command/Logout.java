/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NandaPC
 */
public class Logout implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getSession().removeAttribute("cliente");

            RequestDispatcher dispather = request.getRequestDispatcher("");
            dispather.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

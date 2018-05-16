/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetocarlos.servlets;

import br.com.projetocarlos.service.UserService;
import br.com.projetocarlos.dominio.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //RECUPERA OS DADOS DO USUARIO DO REQUEST
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        User user = new User(email, senha);
        User u = userService.logar(user);

        if (u != null) {
            //CRIO A SESSAO
            HttpSession session = req.getSession();
            //ADICIONO O USUARIO A SESSAO
            session.setAttribute("user", u);
            session.setMaxInactiveInterval(30 * 60);

            Cookie cookieEmail = new Cookie("email", u.getEmail());
            Cookie cookieSenha = new Cookie("senha", u.getSenha());
            cookieEmail.setMaxAge(30 * 60);
            cookieSenha.setMaxAge(30 * 60);
            resp.addCookie(cookieEmail);
            resp.addCookie(cookieSenha);

            resp.sendRedirect("logado/index.html");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color='red'>Usuário ou senha inválido</font>");
            rd.include(req, resp);
        }
    }

}

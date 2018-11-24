package by.itacademy.servlet;

import by.itacademy.model.Person;
import by.itacademy.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Optional<Person> defaultPerson = PersonService.getInstance().getPersonById(id);
        req.setAttribute("person", defaultPerson.get());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/person.jsp")
                .forward(req, resp);
    }
}

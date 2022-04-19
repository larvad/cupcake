package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "updateuser", urlPatterns = {"/updateuser"} )
public class UpdateUser extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // You shouldn't end up here with a GET-request, thus you get sent back to frontpage
        doPost(request, response);
        response.sendRedirect("index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(connectionPool);


        User user = (User) session.getAttribute("user");

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        boolean isAdmin = Boolean.parseBoolean(user.getRole());


        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        String newEmail = request.getParameter("newEmail");
        String confirmNewEmail = request.getParameter("confirmNewEmail");
        String oldPassword = request.getParameter("oldPassword");

        // Hvis blanketten ikke er udfyldt sættes brugernavn til forrige
        if (newUsername == null) {
            newUsername = username;
        }

        // Hvis den nye kode ikke er udfyldt, eller de to indtastede koder ikke stemmer overens sættes koden til forrige.
        if (newPassword == null) {
            newPassword = password;
        }
        if (newPassword != null && !newPassword.equals(confirmNewPassword)) {
                request.setAttribute("errormsg", "You failed to confirm your new password.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
        }

        // Hvis blanketten er udfyldt og de to nye emails stemmer overens skiftes emailen
        if (newEmail == null) {
            newEmail = email;
        }
        if (newEmail != null && !newEmail.equals(confirmNewEmail)) {
                request.setAttribute("errormsg", "You failed to confirm your new email.");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
        }


        if (oldPassword.equals(password)) { //kør hvis det indtastede kodeord matcher det nuværende.
            try {
                user = userMapper.updateUser(email, newPassword, newEmail, newUsername, isAdmin);
                session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (DatabaseException e) {
                Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);


            }
        }

    }
}
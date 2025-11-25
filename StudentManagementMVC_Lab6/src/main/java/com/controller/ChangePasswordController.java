/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.model.User;

/**
 *
 * @author User
 */
@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
    
    private UserDAO userDAO;
    @Override
    public void init() {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Show change password jsp page
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Get current user from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login?error=Please login first");
            return;
        }
        User currentUser = (User) session.getAttribute("user");
        // TODO: Get form parameters (currentPassword, newPassword, confirmPassword)
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        // TODO: Validate current password
        if (!BCrypt.checkpw(currentPassword, currentUser.getPassword())) {
            request.setAttribute("error", "Current password is incorrect.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }
        // TODO: Validate new password (length, match)
        if (newPassword == null || newPassword.length() < 6) {
            request.setAttribute("error", "New password must be at least 6 characters.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirmation do not match.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }
        // TODO: Hash new password
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        // TODO: Update in database
        boolean updated = userDAO.updatePassword(currentUser.getId(), hashedPassword);
        if (!updated) {
            request.setAttribute("error", "Failed to update password. Please try again.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }
        // TODO: Show success/error message
        currentUser.setPassword(hashedPassword);
        session.setAttribute("user", currentUser);
        request.setAttribute("success", "Password updated successfully.");
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
}

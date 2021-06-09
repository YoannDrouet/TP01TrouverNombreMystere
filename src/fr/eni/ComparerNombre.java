package fr.eni;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

public class ComparerNombre extends HttpServlet {
    private boolean trouver = false;
    private int nombreMystere;

    @Override
    public void init() throws ServletException {
        int borneMax = 10;
        int borneMin = 0;
        try {
            borneMax = Integer.parseInt(this.getInitParameter("BORNE_MAX"));
            borneMin = Integer.parseInt(this.getInitParameter("BORNE_MIN"));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        nombreMystere = new Random().nextInt(borneMax+1-borneMin)+borneMin;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nombreUtilisateur = -1;

        try {
            nombreUtilisateur = Integer.parseInt(request.getParameter("nombre"));
            if (!trouver && nombreUtilisateur==nombreMystere){
                response.sendRedirect("victoire.html");
                trouver = true;
            } else{
                response.sendRedirect("defaite.html");
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

}

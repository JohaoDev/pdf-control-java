/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itext7.itext7.web;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Johao Perlaza - perlazajohao.vercel.app
 */
@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class delete extends HttpServlet {
//    private static final String ROOT = "C:\\oraclexe\\directories\\document-digitalization-system\\";
    private static final String ROOT = "C:\\oraclexe\\directories\\test1\\";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Responses res;
            res = new Responses();
            
            Gson gson = new Gson();
            String jsonData;

            String fileName;
            fileName = request.getParameter("fileName");
            
            try {
                deletePdf(fileName);
                
                res.setStatus(200);
                res.setResponseState(true);
                res.setMessage("File deleted");
            } catch(IOException e) {
//                System.out.println(e.getMessage());
                res.setStatus(500);
                res.setResponseState(true);
                res.setMessage(e.getMessage());
            }
            
            jsonData = gson.toJson(res);
            
            try {
                out.println(jsonData);
            } finally {
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void deletePdf(String fileName) throws IOException {
        File filePath = new File(ROOT + fileName + ".pdf");

        if (filePath.exists()) { 
            filePath.delete();
        }
    }
    
    private static class Responses
    {
        // Atributos de la clase Responses
        int status;
        Boolean responseState;
        String message;

        // Métodos de la clase Responses
        public int getStatus() {
            return status;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        
        public Boolean getResponseState() {
            return responseState;
        }
        public void setResponseState(Boolean responseState) {
            this.responseState = responseState;
        }
    
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}

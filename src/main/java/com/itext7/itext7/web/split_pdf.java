/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itext7.itext7.web;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
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
@WebServlet(name = "split_pdf", urlPatterns = {"/split_pdf"})
public class split_pdf extends HttpServlet {
    //DIRECTORIES
    private static final String ROOT = "C:\\oraclexe\\directories\\document-digitalization-system\\";
    private static final String FILES = "C:\\oraclexe\\directories\\document-digitalization-system\\indexes\\";
    
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
        response.setContentType("text/html; charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter() ) {
            int initialPage;
            int finalPage;
            String bookName;
            String fileName;

            initialPage = Integer.parseInt(request.getParameter("initialPage"));
            finalPage = Integer.parseInt(request.getParameter("finalPage"));
            bookName = request.getParameter("bookName");
            fileName = request.getParameter("fileName");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Split PDF File</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>The servlet has received a GET. This is the reply...</p>");
            out.println("</body></html>");
            
            try {
                out.println("<p>Trying to split PDF...</p>");
                dividePdf(initialPage, finalPage, bookName, fileName);
                out.println("<p>PDF successfully divided...</p>");
            } catch(Exception e) {System.out.println(e.getMessage());}
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
    
    private void dividePdf(int initialPage, int finalPage, String bookName, String fileName) {
        try (
                PdfDocument pdfDocument = new PdfDocument(new PdfReader(ROOT + bookName + ".pdf")); //Verifies the existence of the PDF in the directory.
                PdfDocument resultPDF = new PdfDocument(new PdfWriter(FILES + fileName + ".pdf")); //Create empty PDF.
            ) {
            for (int i = initialPage; i <= finalPage; i++) { //Scrolls through pages entered by the user.
                PdfPage page = pdfDocument.getPage(i).copyTo(resultPDF); //Select the page and save it in the "page" variable.
                resultPDF.addPage(page); //The page is added to the empty PDF.
            }
        } catch(Exception e) {System.out.println(e.getMessage());}
    }
    

}


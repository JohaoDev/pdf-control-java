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
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Johao Perlaza - perlazajohao.vercel.app
 */
@WebServlet(name = "view", urlPatterns = {"/view"})
public class view extends HttpServlet {
    //DIRECTORIES
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
        response.setContentType("application/pdf");
                
        try ( OutputStream out = response.getOutputStream() ) {
            String folderName;
            folderName = request.getParameter("folderName");
            
            switch (folderName) {
                case "indexes":
                {
                    String fileName;
                    fileName = request.getParameter("fileName");
                    
                    String initialPage;
                    initialPage = request.getParameter("initialPage");
                    
                    String finalPage;
                    finalPage = request.getParameter("finalPage");
                    
                    PdfWriter writer = new PdfWriter(out); //Initialize PDF writer
                    try (
                            PdfDocument pdfDocument = new PdfDocument(new PdfReader(ROOT + fileName + ".pdf")); //Verifies the existence of the PDF in the directory.
                            PdfDocument resultPDF = new PdfDocument(writer); //Create empty PDF.
                            ) {
                        for (int i = Integer.parseInt(initialPage); i <= Integer.parseInt(finalPage); i++) { //Scrolls through pages defined by user.
                            PdfPage page = pdfDocument.getPage(i).copyTo(resultPDF); //Select the page and save it in the "page" variable.
                            resultPDF.addPage(page); //The page is added to the empty PDF.
                        }
                    }       break;
                }
            default:
                {
                    String fileName;
                    fileName = request.getParameter("fileName");
                    PdfWriter writer = new PdfWriter(out); //Initialize PDF writer
                    try (
                            PdfDocument pdfDocument = new PdfDocument(new PdfReader(ROOT + fileName + ".pdf")); //Verifies the existence of the PDF in the directory.
                            PdfDocument resultPDF = new PdfDocument(writer); //Create empty PDF.
                            ) {
                        for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) { //Scrolls through pages.
                            PdfPage page = pdfDocument.getPage(i).copyTo(resultPDF); //Select the page and save it in the "page" variable.
                            resultPDF.addPage(page); //The page is added to the empty PDF.
                        }
                    }       break;
                }
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

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.account;
import model.cart;
import model.product;
/**
 *
 * @author vh69
 */
@WebServlet(name="TotalMoneyCartControl", urlPatterns={"/totalMoneyCart"})
public class TotalMoneyCartControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        account a = (account) session.getAttribute("acc");
        int accountID = a.getId();
        DAO dao = new DAO();
        List<cart> list = dao.getCartByAccountID(accountID);
        List<product> list2 = dao.getAllProduct();
        double totalMoney=0;
        for(cart o : list) {
        	for(product p : list2) {
        		if(o.getProductID()==p.getId()) {
        			totalMoney=o.getAmount();
        		}
        	}
        }
//        double totalMoneyVAT=totalMoney*1.1;
//        totalMoneyVAT = Math.round(totalMoneyVAT);
//        PrintWriter out = response.getWriter();
//        		out.println(" <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Total product money</strong><strong>"+totalMoney+"</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Shipping fee</strong><strong>Free ship</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">VAT</strong><strong>10 %</strong></li>\r\n"
//        				+ "                                        <li class=\"d-flex justify-content-between py-3 border-bottom\"><strong class=\"text-muted\">Total money</strong>\r\n"
//        				+ "                                            <h5 class=\"font-weight-bold\">"+totalMoneyVAT+"</h5>\r\n"
//        				+ "                                        </li>");
    } 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

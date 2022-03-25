package controllers;

import models.Transaction;
import org.apache.log4j.Logger;
import services.ApplicationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(HistoryServlet.class);
    private ApplicationService histService;

    @Override
    public void init() throws ServletException {
        histService = new ApplicationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(String.format("METHOD:%s STATUS:%s URI:%s LOCALE:%s SESSION_ID:%s",
                req.getMethod(), resp.getStatus(), req.getRequestURI(), resp.getLocale(), req.getRequestedSessionId()));
        List<Transaction> histList = histService.getAllTransactions();
        List<Transaction> histListId = histService.getTransactionsByUserId(1);
        req.setAttribute("histList", histList);
        req.setAttribute("histListId", histListId);
        req.getRequestDispatcher("view/History.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        histService.destroy();
    }
}

package priv.yimeng.demo.servlet;

import com.baidu.ueditor.ActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-30
 *
 * @author yimeng
 * @version 1.0
 */
@WebServlet(urlPatterns = "/ue", name = "ue")
public class UeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootPath = req.getServletContext().getRealPath("/");
        String configFileName = UeServlet.class.getClassLoader().getResource("config.json").getPath();

        try (PrintWriter writer = resp.getWriter()) {
            resp.setCharacterEncoding("UTF-8");
            writer.write(new ActionEnter(req, rootPath, configFileName).exec());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ueditor加载失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}

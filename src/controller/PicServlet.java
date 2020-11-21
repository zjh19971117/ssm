package controller;

import util.Sys;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/pic",initParams = {@WebInitParam(name="source",value= "ABCDEFGHIJKLMNOPQRSTUVWXYZ")})
public class PicServlet extends HttpServlet {
    private String source="abcdefghijkmnopqrstuvwxyz0123456789";

    private String getCode(int len){
        String s="";
        Random r=new Random();
        for(int i=1;i<=len;i++) {
            int local=r.nextInt(source.length());
            s += source.substring(local, local+1);
        }
        return s;
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //制作验证码
        //用于完成图画对象
        resp.setContentType("image/png");
        BufferedImage bufferedImage=new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
        Graphics g=bufferedImage.getGraphics();//画图上下文
        g.setColor(new Color(0,0,255));//Color.GREEN
        g.fillRect(0,0,100,50);
        Random r=new Random();
        g.setColor(Color.black);
        g.setFont(new Font("隶书",Font.ITALIC,40));
        String ss=this.getCode(4);//调用方法，获得4个长度的随机字符串
        req.getSession().setAttribute(Sys.CODE,ss);
        g.drawString(ss,10,40);
        for(int i=1;i<=20;i++){
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            g.drawLine(r.nextInt(101),r.nextInt(51),r.nextInt(101),r.nextInt(51));
        }
        g.dispose();//绘画完毕
        //生成一个图片
        ImageIO.write(bufferedImage,"png",resp.getOutputStream());
    }
}

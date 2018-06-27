package com.student.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyImage extends HttpServlet {
	private int width=60;                       //验证码图片的宽度。
    private int height=20;                      //验证码图片的高度。
    private int codeCount=4;                    //验证码字符个数
    private int lines = 10;                     //干扰线数
    private int points = 100;                   //干扰点数
    private String sfont = "Times New Roman";   //验证码字体
    private int x=0;
    private int fontHeight;                     //字体高度
    private int codeY;
    private char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private BufferedImage buffImg = null;
    private StringBuffer randomCode = null;
    private Graphics2D g = null;

    /**
    * 初始化验证图片属性
    */
    public void init() throws ServletException {
        //从web.xml中获取初始信息
        String strFont=this.getInitParameter("font");           //字体1
        String strWidth=this.getInitParameter("width");         //宽度2
        String strHeight=this.getInitParameter("height");       //高度3
        String strCodeCount=this.getInitParameter("codeCount"); //字符个数4
        String strLines=this.getInitParameter("lines");         //干扰线数5
        String strPoints=this.getInitParameter("points");       //干扰线数6

        //将配置的信息转换成数值
        try{
            if(strFont!=null && strFont.length()!=0){
                sfont=strFont;
            }
            if(strWidth!=null && strWidth.length()!=0){
                width=Integer.parseInt(strWidth);
            }
            if(strHeight!=null && strHeight.length()!=0){
                height=Integer.parseInt(strHeight);
            }
            if(strCodeCount!=null && strCodeCount.length()!=0){
                codeCount=Integer.parseInt(strCodeCount);
            }
            if(strLines!=null && strLines.length()!=0){
                lines=Integer.parseInt(strLines);
            }
            if(strPoints!=null && strPoints.length()!=0){
                points=Integer.parseInt(strPoints);
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        x=width/(codeCount+1);
        fontHeight=height-2;
        codeY=height-4;

        //定义图像buffer
        buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        g = buffImg.createGraphics();
        genImage();
    }

    private void genImage(){
        Random random = new Random();
        g.setColor(Color.WHITE);        //将图像填充为白色
        g.fillRect(0, 0, width, height);
        Font font = new Font(sfont, Font.BOLD, fontHeight);
        g.setFont(font);
        
        //画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        
        //产生随机干扰线。
        g.setColor(Color.BLACK);
        for(int i=0; i<lines; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        
        //产生随机干扰点。
        for(int i=0; i<points; i++ ) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }
        
        //randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        //随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            //得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(10)]);
            
            //产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            
            //用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (int)((i+0.5) * x), codeY);
            
            //将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
    }
    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        genImage();
        
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("validateCode", randomCode.toString());
        
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        
        //将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
    
}

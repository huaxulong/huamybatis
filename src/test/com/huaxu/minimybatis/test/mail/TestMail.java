package com.huaxu.minimybatis.test.mail;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-04-13 3:58 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class TestMail {

    public static Properties SMTP_QQ(boolean debug) {
        Properties props = defaultConfig(debug);
        props.put("mail.smtp.host", "smtp.qiye.aliyun.com");
        return props;
    }

    public static Properties defaultConfig(Boolean debug) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "false");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", null != debug ? debug.toString() : "false");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.port", "465");
        return props;
    }

    @Before
    public void before() throws GeneralSecurityException {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(true), "message@shantaijk.cn", "!QAZ2wsx123");
    }

    @Test
    public void testSendText() throws MessagingException, SendMailException {
        OhMyEmail.subject("这是偷偷发给唐老板的TEXT邮件")
                .from("小姐姐的邮箱")
                .to("huadongxu@shantaijk.cn")
                .text("这是测试信件内容")
                .html("<h1 font=red>信件body</h1>" +
                        "<table  border=\"1px solid #ccc\" cellspacing=\"0\" cellpadding=\"0\">" +
                        "<tr><th style=background-color:#00FF00 text-align:center width=\"150px\">卡产品编号</th>" +
                        "<th style=background-color:#00FF00 text-align:center width=\"150px\">卡名称</th>" +
                        "<th style=background-color:#00FF00 text-align:center width=\"150px\">卡库存</th>" +
                        "<th style=background-color:#00FF00 text-align:center width=\"150px\">告警内容</th>" +
                        "</tr><tr><td>202110001101</td><td>测试产品名称101</td><td>30</td><td>库存数量不足100张</td></tr></table>")
                .attach(new File("/Users/dongxuhua/Downloads/abcd.png"), "测试图片.png")
                .send();
    }

    @Test
    public void testSendHtml() throws MessagingException, SendMailException {
        OhMyEmail.subject("这是一封测试HTML邮件")
                .from("小姐姐的邮箱")
                .to("xiaojiejie@gmail.com")
                .html("<h1 font=red>信件内容</h1>" +
                        "<tableborder=\"1\"><tr><th>Month</th><th>Savings</th></tr><tr><td>January</td><td>$100</td></tr></table>")
                .send();
    }

    @Test
    public void testSendAttach() throws MessagingException, SendMailException, MalformedURLException {
        OhMyEmail.subject("这是一封测试附件邮件")
                .from("小姐姐的邮箱")
                .to("xiaojiejie@gmail.com")
                .html("<h1 font=red>信件内容</h1>")
                .attach(new File("/Users/biezhi/Downloads/hello.jpeg"), "测试图片.jpeg")
                .attachURL(new URL("https://avatars1.githubusercontent.com/u/2784452?s=40&v=4"), "测试图片222.jpeg")
                .send();
    }

    @Test
    public void testSendAttachURL() throws MessagingException {
        try {
            OhMyEmail.subject("这是一封测试网络资源作为附件的邮件")
                    .from("小姐姐的邮箱")
                    .to("xiaojiejie@gmail.com")
                    .html("<h1 font=red>信件内容</h1>")
                    .attachURL(new URL("https://avatars1.githubusercontent.com/u/2784452?s=40&v=4"), "测试图片.jpeg")
                    .send();
        } catch (SendMailException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /*@Test
    public void testPebble() throws IOException, PebbleException, MessagingException {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("username", "biezhi");
        context.put("email", "admin@biezhi.me");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Pebble模板邮件")
                .from("小姐姐的邮箱")
                .to("xiaojiejie@gmail.com")
                .html(output)
                .send();
    }*/

    /*
    @Test
    public void testJetx() throws IOException, PebbleException, MessagingException {
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.getTemplate("/register.jetx");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("username", "biezhi");
        context.put("email", "admin@biezhi.me");
        context.put("url", "<a href='http://biezhi.me'>https://biezhi.me/active/asdkjajdasjdkaweoi</a>");

        StringWriter writer = new StringWriter();
        template.render(context, writer);
        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Jetx模板邮件")
                .from("小姐姐的邮箱")
                .to("xiaojiejie@gmail.com")
                .html(output)
                .send();
    }*/

}

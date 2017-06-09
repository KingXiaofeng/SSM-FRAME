package com.ssm.frame.servlet;

import javax.servlet.http.HttpServlet;

/**
 * Copyright: Copyright (c) 2017 LanRu-Caifu
 *
 * @ClassName: JcaptchaServlet.java
 * @Description: 验证码
 * @author:wangxf
 * @date: 2017年5月6日 下午2:20:58
 */
@SuppressWarnings("serial")
public class JcaptchaServlet extends HttpServlet {

    /*public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

    private ImageCaptchaService captchaService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext appCtx = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        captchaService = (ImageCaptchaService) BeanFactoryUtils
                .beanOfTypeIncludingAncestors(appCtx, ImageCaptchaService.class);

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        byte[] captchaChallengeAsJpeg = null;
        // the output stream to render the captcha image as jpeg into
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // get the session id that will identify the generated captcha.
            // the same id must be used to validate the response, the session id
            // is a good candidate!

            String captchaId = request.getSession().getId();
            BufferedImage challenge = captchaService.getImageChallengeForID(
                    captchaId, request.getLocale());
            // Jimi.putImage("image/jpeg", challenge, jpegOutputStream);
            ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // catch (JimiException e) {
        // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        // return;
        // }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // flush it in the response
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/" + CAPTCHA_IMAGE_FORMAT);

        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }*/
}

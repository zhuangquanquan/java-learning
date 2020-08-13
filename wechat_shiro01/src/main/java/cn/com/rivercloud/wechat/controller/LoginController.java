package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.common.util.UserLoginErrorCounts;
import cn.com.rivercloud.wechat.config.SystemBaseConfig;
import cn.com.rivercloud.wechat.entity.user.User;
import cn.com.rivercloud.wechat.jwt.JwtUtils;
import cn.com.rivercloud.wechat.jwt.LogoutCache;
import cn.com.rivercloud.wechat.service.user.impl.UserServiceImpl;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private Producer codeProducer;

    @Autowired
    private SystemBaseConfig systemBaseConfig;

    private static final String CAPTCHA_SESSION_KEY = "-CAPTCHA-SESSION-KEY-";

    //@RequiresRoles(value={"admin","manage","customer"},logical = Logical.OR)
    @RequiresAuthentication
    @PostMapping(value = "/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        Claims claims = jwtUtils.getClaimByToken(subject.getPrincipal().toString()) ;
        LogoutCache.me().put(claims.getId(), claims.getExpiration().getTime());
        //注销
        jwtUtils.addCookie(request, response, null);
        return Result.succ(null);
    }

    @PostMapping(value = "/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(System.currentTimeMillis() + "==>" + loginDto.getUsername());
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        //开启验证码
        if (systemBaseConfig.isCaptchaEnable()) {
            int errCount = UserLoginErrorCounts.getLoginErrorCounts(request.getServletContext(), username);
            if (errCount >= systemBaseConfig.getCaptchaLoginFailNumEnable()) {
                if (Strings.isEmpty(loginDto.getCode())) {
                    return Result.fail("验证码不能为空");
                }
                HttpSession session = request.getSession();
                String captcha = (String) session.getAttribute(CAPTCHA_SESSION_KEY);
                if (!loginDto.getCode().toLowerCase().equals(captcha.toLowerCase())) {
                    return Result.fail("验证码错误");
                }
            }
        }
        User user = userService.getOne(username);
        if (Objects.isNull(user)) {
            return Result.fail("用户名或密码错误");
        }
        if (!user.getPassword().equals(password)) {
            UserLoginErrorCounts.updateLoginErrorCounts(request.getServletContext(), username, false);
            return Result.fail("用户名或密码错误");
        }
        String token = jwtUtils.generateToken(UUID.randomUUID().toString(), user.getId());
        response.setHeader(jwtUtils.getHeader(), token);
        response.setHeader("Access-control-Expose-Headers", jwtUtils.getHeader());
        jwtUtils.addCookie(request, response, token);
        UserLoginErrorCounts.updateLoginErrorCounts(request.getServletContext(), username, true);
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("userName", user.getUserName())
                .put("realName", user.getRealName())
                .put(jwtUtils.getHeader(), token)
                .map());
    }

    /**
     * TODO 由于并发量小，暂时把数据存储在session中
     */
    @GetMapping(value = "/kaptcha")
    public void getKaptcha(HttpServletResponse response, HttpSession session) {
        // 生成验证码
        String text = codeProducer.createText();
        BufferedImage image = codeProducer.createImage(text);
        // 将验证码存入session
        session.setAttribute(CAPTCHA_SESSION_KEY, text);
        // 将突图片输出给浏览器
        response.setContentType("image/png");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            log.error("获取验证码异常", e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

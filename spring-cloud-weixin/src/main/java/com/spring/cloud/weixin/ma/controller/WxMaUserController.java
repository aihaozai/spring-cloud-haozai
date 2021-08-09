package com.spring.cloud.weixin.ma.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.spring.cloud.weixin.ma.config.WxMaConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haozai
 * @description 用户接口
 * @date 2021/8/6  22:26
 */
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public WxMaJscode2SessionResult login(@PathVariable String appid, String jscode) throws WxErrorException {
        if (StringUtils.isBlank(jscode)) {
            throw new WxErrorException("empty jscode");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(jscode);
        this.logger.info(session.getSessionKey());
        this.logger.info(session.getOpenid());
        //TODO 可以增加自己的逻辑，关联业务相关数据
        return session;
    }

    /**
     * 获取用户信息接口
     */
    @GetMapping("/info")
    public WxMaUserInfo info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) throws WxErrorException {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new WxErrorException("user check failed");
        }

        // 解密用户信息

        return wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @GetMapping("/phone")
    public WxMaPhoneNumberInfo phone(@PathVariable String appid, String sessionKey, String signature,
                        String rawData, String encryptedData, String iv) throws WxErrorException {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new WxErrorException("user check failed");
        }

        // 解密

        return wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
    }

}

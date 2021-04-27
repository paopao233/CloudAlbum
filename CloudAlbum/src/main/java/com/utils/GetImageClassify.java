package com.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 调用腾讯云Api获取图像所属类别
 * 可用base64,url
 * @author x1yyy
 */
public class GetImageClassify {
    public static String getClassify(String url) {
        try{
            Credential cred = new Credential("AKIDPOClSuo0kBLFqDCwJaXVIEKRL4VhQnHx", "f9C6XkmEPPFosjLx0oOes2CQr8C4hCQS");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tiia.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            TiiaClient client = new TiiaClient(cred, "ap-beijing", clientProfile);

            DetectLabelRequest req = new DetectLabelRequest();
            req.setImageUrl(url);

            DetectLabelResponse response = client.DetectLabel(req);

            int length = Objects.requireNonNull(response).getLabels().length;
            String classify = "";
            for (int i = 0; i < length; i++) {
                if (!"其他".equals(response.getLabels()[i].getFirstCategory())) {
                    classify = Objects.requireNonNull(response).getLabels()[i].getName();
                    break;
                }
            }

            return classify;
        } catch (TencentCloudSDKException e) {
            return "无法识别的图片";
        }
    }
}

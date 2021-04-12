package com.github.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author HAN
 * @version 1.0
 * @create 2021/4/12
 */
@SpringBootTest
public class VodTest {

    @SneakyThrows
    @Test
    void testVod() {
        // 初始化对象
        DefaultAcsClient client = InitVodClient.initVodClient("LTAI5tQuSDdfWgK4PKARXAf5", "CDtiUfRWo2vK5Pf6dJVqqS5T6xhr4a");

        // 创建获取视频地址的request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 向response里设置视频id
        request.setVideoId("fe3c7cfec9bf47339c405f94b3607e02");

        // 调用初始化对象中的方法
        GetPlayInfoResponse response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }


}

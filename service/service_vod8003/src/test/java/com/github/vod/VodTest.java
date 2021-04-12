package com.github.vod;

import cn.hutool.core.util.IdUtil;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
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

    // 测试视频上传
    @Test
    void testUpload() {
        UploadVideoRequest request = new UploadVideoRequest("LTAI5tQuSDdfWgK4PKARXAf5",
                "CDtiUfRWo2vK5Pf6dJVqqS5T6xhr4a",
                IdUtil.fastSimpleUUID(),
                "D:\\test.mp4");
        /*可指定分片上传时每个分片的大小，默认为1M字节*/
        request.setPartSize(1024L * 1024L);
        /*可指定分片上传时的并发线程数，默认为1（注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(4);
        /*是否开启断点续传，默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        /*OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        //request.setSlowRequestsThreshold(300000L);
        /*可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /*是否使用默认水印（可选），指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setIsShowWaterMark(true);
        /*自定义消息回调设置（可选），参数说明参考文档 https://help.aliyun.com/document_detail/86952.html#UserData*/
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");

        /*视频分类ID（可选）*/
        //request.setCateId(0);
        /*视频标签,多个用逗号分隔（可选）*/
        //request.setTags（"标签1,标签2"）;
        /*视频描述（可选）*/
        //request.setDescription("视频描述");
        /*封面图片（可选）*/
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /*模板组ID（可选）*/
        //request.setTemplateGroupId("8c4792cbc8694*****d5330e56a33d");
        /*点播服务接入点*/
        //request.setApiRegionId("cn-shanghai");
        /*ECS部署区域，如果与点播存储（OSS）区域相同，则自动使用内网上传文件至存储*/
        // request.setEcsRegionId("cn-shanghai");
        /*存储区域（可选）*/
        //request.setStorageLocation("in-2017032*****18266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /*开启默认上传进度回调*/
        // request.setPrintProgress(true);
        /*设置自定义上传进度回调（必须继承 ProgressListener）*/
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" +  response.getVideoId()  + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId="  + response.getVideoId()  + "\n");
            System.out.print("ErrorCode="  + response.getCode() +  "\n");
            System.out.print("ErrorMessage=" +  response.getMessage()  + "\n");
        }
    }

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

package com.hiczp.bilibili.api.web.live;

import com.hiczp.bilibili.api.web.live.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.File;

public interface LiveService {
    /**
     * 直播间心跳包
     *
     * @param timestamp 时间戳(不是 unix 时间戳)
     * @return 未登录时返回 401
     */
    @GET("feed/v1/feed/heartBeat")
    Call<SendHeartBeatResponseEntity> sendHeartBeat(@Query("_") long timestamp);

    default Call<SendHeartBeatResponseEntity> sendHeartBeat() {
        return sendHeartBeat(System.currentTimeMillis());
    }

    /**
     * 获取用户信息
     *
     * @param timestamp 时间戳(不是 unix 时间戳)
     * @return 成功时, code 为 "REPONSE_OK", 未登录时返回 500
     */
    @GET("User/getUserInfo")
    Call<UserInfoEntity> getUserInfo(@Query("ts") long timestamp);

    default Call<UserInfoEntity> getUserInfo() {
        return getUserInfo(System.currentTimeMillis());
    }

    /**
     * 获取直播分区列表，多层
     *
     * @return 成功时code为0
     */
    @GET("room/v1/Area/getList?show_pinyin=1")
    Call<LiveAreaListEntity> getLiveAreaList();


    /**
     * 获取直播间信息（主要是拿roomId）
     *
     * @return LiveInfoEntity
     */
    @GET("i/api/liveinfo")
    Call<LiveInfoEntity> getLiveInfo();

    /**
     * 更新房间标题
     *
     * @return
     */
    @POST("room/v1/Room/update")
    @FormUrlEncoded
    Call<UpdateRoomTitleEntity> updateRoomTitle(@Field("room_id") String roomId, @Field("title") String title, @Field("csrf_token") String csrfToken);

    /**
     * 根据roomId 获取推流的地址
     *
     * @param roomId
     * @return LiveRoomStreamInfoEntity
     */
    @GET("live_stream/v1/StreamList/get_stream_by_roomId")
    Call<LiveRoomStreamInfoEntity> getStreamByRoomId(@Query("room_id") String roomId);

    /**
     * 开始直播
     *
     * @param roomId
     * @param platform
     * @param areaId
     * @param csrfToken (csrfToken是cookie里的bili_jct cookie这个的值)
     * @return StartLiveEntity
     */
    @POST("room/v1/Room/startLive")
    @FormUrlEncoded
    Call<StartLiveEntity> startLive(@Field("room_id") String roomId, @Field("platform") String platform,
                                    @Field("area_v2") String areaId, @Field("csrf_token") String csrfToken);

    /**
     * 停止直播
     *
     * @param roomId
     * @param platform
     * @param areaId
     * @param csrfToken
     * @return
     */
    @POST("room/v1/Room/stopLive")
    @FormUrlEncoded
    Call<StopLiveEntity> stopLive(@Field("room_id") String roomId, @Field("platform") String platform, @Field("csrf_token") String csrfToken);
}

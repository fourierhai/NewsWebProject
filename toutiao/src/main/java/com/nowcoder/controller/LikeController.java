package com.nowcoder.controller;

import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ToutiaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LikeController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    LikeService likeService;

    @Autowired
    NewsService newsService;

    @RequestMapping(path={"/like"}, method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String like(@RequestParam("newsId") int newsId){
        // 得到用户id
        int userId = hostHolder.getUser().getId();
        // 目前EntityType 只有 ENTITY_NEWS

        long likeCount = likeService.like(userId, EntityType.ENTITY_NEWS,newsId);
        // 在 News model 里有个属性 likeCount
        // 将likeCount更新到数据库中
        newsService.updateLikeCount(userId,(int)likeCount);

        return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
    }

    @RequestMapping(path={"/dislike"}, method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String dislike(@RequestParam("newsId") int newsId){
        // 得到用户id
        int userId = hostHolder.getUser().getId();
        // 目前EntityType 只有 ENTITY_NEWS

        long likeCount = likeService.dislike(userId, EntityType.ENTITY_NEWS,newsId);
        // 在 News model 里有个属性 likeCount
        // 将likeCount更新到数据库中
        newsService.updateLikeCount(userId,(int)likeCount);

        return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
    }
}

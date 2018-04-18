<#include "header.ftl">
<div id="main">
    <!--
        <div class="hero">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="app-iphone">
                            <div class="carousel slide" data-ride="carousel" data-interval="3000">
                                <div class="carousel-inner" role="listbox">
                                    <div class="item">
                                      <img alt="牛客网应用截图阅读精选" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x">
                                      <div class="carousel-caption">阅读精选</div>
                                    </div>
                                    <div class="item active">
                                      <img alt="牛客网应用截图订阅主题" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x">
                                      <div class="carousel-caption">订阅主题</div>
                                    </div>
                                    <div class="item">
                                      <img alt="牛客网应用截图分享干货" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x">
                                      <div class="carousel-caption">分享干货</div>
                                    </div>
                                    <div class="item">
                                      <img alt="牛客网应用截图兑换礼品" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x">
                                      <div class="carousel-caption">兑换礼品</div>
                                    </div>
                                    <div class="item">
                                      <img alt="牛客网应用截图建立品牌" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x">
                                      <div class="carousel-caption">建立品牌</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="intro">
                            <div class="title">
                              <h1>牛客网</h1>
                              <h3>程序员必装的 App</h3>
                            </div>
                            <div class="media">
                                <div class="media-left">
                                    <img class="media-object app-qrcode" src="../images/res/qrcode.png" alt="App qrcode web index">
                                </div>
                                <div class="media-body">
                                    <div class="buttons">
                                      <p><a onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;app&#39;, &#39;download&#39;, &#39;ios&#39;])" class="btn btn-success btn-lg" href="http://nowcoder.com/s/ios"><i class="fa icon-apple"></i> iPhone 版</a></p>
                                      <p><a onclick="_hmt.push([&#39;_trackEvent&#39;, &#39;app&#39;, &#39;download&#39;, &#39;apk&#39;])" class="btn btn-success btn-lg" href="http://nowcoder.com/s/apk"><i class="fa icon-android"></i> Android 版</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        -->

    <div class="container" id="daily">
        <div class="jscroll-inner">
        <div class="daily">
        <#assign cur_date = ''/>
        <#list vos as vo>
            <#if cur_date != "${vo.news.createdDate?string('yyyy-MM-dd')}">
                <#if 0 < vo_index>
                </div> <#--上一个要收尾-->
                </#if>
                <#assign cur_date= "${vo.news.createdDate?string('yyyy-MM-dd')}"/>
                <h3 class="date">
                        <i class="fa icon-calendar"></i>
                        <span>头条资讯 &nbsp; ${vo.news.createdDate?string('yyyy-MM-dd')}</span>
                    </h3>
            <div class="posts">
            </#if>
                <div class="post">
                    <div class="votebar">
                        <button class="click-like up" aria-pressed="false" title="赞同"><i class="vote-arrow"></i><span
                                class="count">${vo.news.likeCount}</span></button>
                        <button class="click-dislike down" aria-pressed="true" title="反对"><i class="vote-arrow"></i>
                        </button>
                    </div>
                    <div class="content" data-url="http://nowcoder.com/posts/5l3hjr">
                        <div>
                            <img class="content-img" src="${vo.news.image}" alt="">
                        </div>
                        <div class="content-main">
                            <h3 class="title">
                                <a target="_blank" rel="external nofollow" href="${vo.news.link}">${vo.news.title}</a>
                            </h3>
                            <div class="meta">
                                ${vo.news.link}
                                <span>
                                            <i class="fa icon-comment"></i> ${vo.news.commentCount}
                                        </span>
                            </div>
                        </div>
                    </div>
                    <div class="user-info">
                        <div class="user-avatar">
                            <a href="/user/${vo.user.id}/"><img width="32" class="img-circle" src="${vo.user.headUrl}"></a>
                        </div>

                        <!--
                        <div class="info">
                            <h5>分享者</h5>

                            <a href="http://nowcoder.com/u/251205"><img width="48" class="img-circle" src="http://images.nowcoder.com/images/20141231/622873_1420036789276_622873_1420036771761_%E8%98%91%E8%8F%87.jpg@0e_200w_200h_0c_1i_1o_90Q_1x" alt="Thumb"></a>

                            <h4 class="m-b-xs">冰燕</h4>
                            <a class="btn btn-default btn-xs" href="http://nowcoder.com/signin"><i class="fa icon-eye"></i> 关注TA</a>
                        </div>
                        -->
                    </div>

                    <div class="subject-name">来自 <a href="/user/${vo.user.id}/">${vo.user.name}</a></div>
                </div>

                <!--
                <div class="alert alert-warning subscribe-banner" role="alert">
                  《头条八卦》，每日 Top 3 通过邮件发送给你。      <a class="btn btn-info btn-sm pull-right" href="http://nowcoder.com/account/settings">立即订阅</a>
                </div>
                -->
                <#if vo_has_next == false>
                    </div> <#--最后有个元素要收尾-->
                </#if>
            </#list>


        </div>
    </div>
</div>

</div>

<#if pop??>
<script>
    window.loginpop = ${pop};
</script>
</#if>

<#include "footer.ftl">

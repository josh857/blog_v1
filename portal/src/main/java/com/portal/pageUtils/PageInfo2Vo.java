package com.portal.pageUtils;

import com.github.pagehelper.PageInfo;
import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Entity.Comment;
import com.portal.pojo.Entity.Message;
import com.portal.pojo.Vo.BlogVo;
import com.portal.pojo.Vo.CommentVo;
import com.portal.pojo.Vo.MessageVo;

public class PageInfo2Vo {

        /**
         * Blog 分頁管理
         * 配置pageHelper DAO層找出LIST ENTITY PageInfo實體類 轉換 VO PageInfo回傳
         * @param pageInfo<Blog>
         * @return pageVoInfo<BlogVo>
         */
    public static PageInfo<BlogVo> PageUtil(PageInfo<Blog> pageInfo){
            PageInfo pageVoInfo = new PageInfo();
            pageVoInfo.setPrePage(pageInfo.getPrePage());
            pageVoInfo.setNextPage(pageInfo.getNextPage());
            pageVoInfo.setNavigatePages(pageInfo.getNavigatePages());
            pageVoInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
            pageVoInfo.setNavigateLastPage(pageInfo.getNavigateLastPage());
            pageVoInfo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
            pageVoInfo.setHasNextPage(pageInfo.isHasNextPage());
            pageVoInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
            pageVoInfo.setIsFirstPage(pageInfo.isIsFirstPage());
            pageVoInfo.setIsLastPage(pageInfo.isIsLastPage());
            return pageVoInfo;
    }

        /**
         * Message Data 分頁管理 資料轉換
         * @param pageInfo
         * @return
         */
        public static PageInfo<MessageVo> PageUtilmessage(PageInfo<Message> pageInfo){
                PageInfo<MessageVo> pageVoInfo = new PageInfo();
                pageVoInfo.setPrePage(pageInfo.getPrePage());
                pageVoInfo.setNextPage(pageInfo.getNextPage());
                pageVoInfo.setNavigatePages(pageInfo.getNavigatePages());
                pageVoInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
                pageVoInfo.setNavigateLastPage(pageInfo.getNavigateLastPage());
                pageVoInfo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
                pageVoInfo.setHasNextPage(pageInfo.isHasNextPage());
                pageVoInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
                pageVoInfo.setIsFirstPage(pageInfo.isIsFirstPage());
                pageVoInfo.setIsLastPage(pageInfo.isIsLastPage());
                return pageVoInfo;
        }

    /**
     * 分頁管理comment 轉 Vo
     * @param pageInfo <CommentVo>
     * @return
     */
    public static PageInfo<CommentVo> PageUtilComment(PageInfo<Comment> pageInfo) {
                PageInfo<CommentVo> voPageInfo = new PageInfo<>();
        voPageInfo.setPrePage(pageInfo.getPrePage());
        voPageInfo.setNextPage(pageInfo.getNextPage());
        voPageInfo.setNavigatePages(pageInfo.getNavigatePages());
        voPageInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
        voPageInfo.setNavigateLastPage(pageInfo.getNavigateLastPage());
        voPageInfo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        voPageInfo.setHasNextPage(pageInfo.isHasNextPage());
        voPageInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
        voPageInfo.setIsFirstPage(pageInfo.isIsFirstPage());
        voPageInfo.setIsLastPage(pageInfo.isIsLastPage());
        voPageInfo.setPageSize(pageInfo.getPageSize());
        voPageInfo.setPages(pageInfo.getPages());
        return voPageInfo;

    }
}

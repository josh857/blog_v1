package com.portal.pageUtils;

import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Entity.Comment;
import com.portal.pojo.Entity.Message;
import com.portal.pojo.Vo.BlogVo;
import com.portal.pojo.Vo.CommentVo;
import com.portal.pojo.Vo.MessageVo;

import java.util.ArrayList;
import java.util.List;

public class Entitytrans4VoList {

    /**
     * Comment 轉換
     * @param comments
     * @return
     */
    public static List<CommentVo> CommenttransfertoVo (List<Comment> comments){
        List<CommentVo> commentvos= new ArrayList<>();
        for(Comment c :comments){
            CommentVo cv = new CommentVo();
            cv.setId(c.getId());
            cv.setName(c.getName());
            cv.setContent(c.getContent());
            cv.setCreatedate(c.getCreatetime());
            commentvos.add(cv);
        }
        return commentvos;
    }

    /**
     * messageVo 轉換
     * @param messages
     * @return
     */
    public static List<MessageVo>  MessageTransfertoVo(List<Message> messages){
        List<MessageVo> messagevo= new ArrayList<>();
        for(Message message:messages){
            MessageVo mv=new MessageVo();
            mv.setId(message.getId());
            mv.setName(message.getUsername());
            mv.setTitle(message.getTitle());
            mv.setContent(message.getContent());
            messagevo.add(mv);
        }
        return messagevo;
    }

    public static List<BlogVo> BlogTransfertoVo(List<Blog> blogs){
        List<BlogVo> vo = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogVo blogvo = new BlogVo();
            blogvo.setTitle(blog.getTitle());
            blogvo.setContent(blog.getContent());
            blogvo.setId(blog.getId());
            blogvo.setImage(blog.getImage());
            blogvo.setCreatedatetime(blog.getCreatedatetime());
            vo.add(blogvo);
        }
        return vo;
    }

}

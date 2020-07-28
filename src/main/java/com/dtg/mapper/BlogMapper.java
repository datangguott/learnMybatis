package com.dtg.mapper;

import com.dtg.bean.Blog;

public interface BlogMapper {
    public Blog selectBlog(Integer id);
    public int deleteBlogById(Integer id);
    public int updateBlog(Blog blog);
    public int addBlog(Blog blog);
}

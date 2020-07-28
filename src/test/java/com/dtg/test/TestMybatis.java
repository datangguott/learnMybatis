package com.dtg.test;

import com.dtg.bean.Blog;
import com.dtg.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    public static String resource = "mybatis-config.xml";

    @Test
    public void test01() {

        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlog(1);
            System.out.println("res:" + blog);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }




    @Test
    public void testAdd() {


        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(1);
            blog.setTitle("titletext");
            blog.setContent("contenttext");

            int res = blogMapper.addBlog(blog);
            System.out.println("res:" + res + ",blog:" + blog);
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();

        }
    }


    @Test
    public void testDel() {
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Integer id = 1;
            Blog blog = blogMapper.selectBlog(id);
            System.out.println("before blog:" + blog);
            int res = blogMapper.deleteBlogById(id);
            System.out.println("res:" + res);


            blog = blogMapper.selectBlog(id);
            System.out.println("after blog:" + blog);

            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();

        }
    }


    @Test
    public void testUpdate() {
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Integer id = 2;
            Blog blog = blogMapper.selectBlog(id);
            System.out.println("before blog:" + blog);
            blog.setTitle("titlemodified");
            blog.setContent("contentmodified");
            int res = blogMapper.updateBlog(blog);
            System.out.println("res:" + res);


            blog = blogMapper.selectBlog(id);
            System.out.println("after blog:" + blog);

            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();

        }
    }
}

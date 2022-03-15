/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.blog.dao;
import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author rushr
 */
public class PostDao {
    Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

public ArrayList<Category> getAllCategories(){
    ArrayList<Category>list = new ArrayList<>();
    
    try
    {
        String q="select * from categories";
        Statement st=this.con.createStatement();
        ResultSet set=st.executeQuery(q);
        
        while(set.next())
        {
            int cid = set.getInt("cid");
            String name = set.getString("name");
            String description = set.getString("description");
            Category c = new Category(cid,name,description);
            list.add(c);
        }
        
        
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    
    return list;
    
    
}

public boolean savepost(Post p){
    boolean f=false;
    try{
        
        String q="insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(q);
        pstmt.setString(1, p.getpTitle());
        pstmt.setString(2, p.getpContent());
        pstmt.setString(3, p.getpCode());
        pstmt.setString(4, p.getpPic());
        pstmt.setInt(5, p.getCatId());
        pstmt.setInt(6, p.getUserId());
        
        pstmt.executeUpdate();
        f=true;
        
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    
    return f;
}

    public boolean savePost(Post p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

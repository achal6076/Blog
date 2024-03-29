/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import java.sql.*;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

//    import data to database
    public boolean saveUser(User user) {
        boolean f = false;
        try {

            String query = "insert into user(name,email,password,gender) values(?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());

            pstmt.executeUpdate();

            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }

    //get user detail for login page
    public User getUserbyEmailAndPassword(String email, String password) {

        User user = null;
        try {
            String query= "Select * from user where email=? and password=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet set=pstmt.executeQuery();
            
            if(set.next())
            {
                user= new User();
                
                
                //database user
                String name=set.getString("name");
                
                //set the user name
                user.setName(name);
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));
              
                
                
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        return user;
    }
    
    //updating data for signup page
    
    public boolean updateUser(User user){
        
        boolean f=false;
        try{
            
            String query= "update user set name=? , email=? , password=? , gender=? , profile=?  where id=? " ;
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getProfile());
            p.setInt(6, user.getId());
            
            p.executeUpdate();
            f=true;
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

}

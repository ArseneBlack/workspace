/**
 * 
 */

package com.sapint.db.mysql;

/**
 * @author vincent
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//�������ǽ���һ��DBHelper��
public class BaseDao3 {
  
   //�˷���Ϊ��ȡ���ݿ����ӣ��˴��Լ�����������ʹ�õĶ���mysql
  public static Connection getCon() {
         Connection con = null;
         try {
              String driver = "com.mysql.jdbc.Driver"; //���ݿ�����
              String url = "jdbc:MySQL://127.0.0.1:3306/test";//
              String user = "root"; //�û���
              String password = "root";//����
              Class.forName(driver); //�������ݿ�����
              if(null == con)
                con = DriverManager.getConnection(url, user, password);
          } 
         catch(ClassNotFoundException e) 
     {   
       System.out.println("Sorry,can't find the Driver!");   
       e.printStackTrace();   
     }
          catch(SQLException e) 
     {   
       e.printStackTrace();   
     } 
         catch (Exception e) {
               e.printStackTrace();
         }
          return con;
     } 

    /**  
     * ��ѯ���
     *   
     * @param sql  
     */  
     public static ResultSet executeQuery(String sql) throws SQLException {
          Connection con = getCon();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          return rs;
     }

     public static ResultSet executeQuery(String sql, Object... obj)   throws SQLException {
        Connection con = getCon();
        PreparedStatement pstmt = con.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
              pstmt.setObject(i + 1, obj[i]);
        }
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }
     
     /**  
      * �жϼ�¼�Ƿ����
      *   
      * @param sql  
      */ 
     public static Boolean exist(String sql) throws SQLException{
       
       try{
         ResultSet rs = executeQuery(sql);
         rs.last();
         int count = rs.getRow();
         if(count > 0)
           return true;
         else
           return false;
       }catch(Exception e)
       {
         e.printStackTrace();
         return false;
       }
       
       
     }
     
     /**  
      * ��ȡ��ѯ��¼��������
      *   
      * @param sql  
      */ 
     public static int count(String sql) throws SQLException{
       ResultSet rs = executeQuery(sql);
       rs.last();
       int count = rs.getRow();
       return count;
     }
     
     /**  
      * ִ����ɾ��
      *   
      * @param sql    
      */  
    public static int executeNonQuery(String sql) throws SQLException {
        Connection con = getCon();
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(sql);
   }

    public static int executeNonQuery(String sql, Object... obj) throws SQLException {
        Connection con = getCon();
        PreparedStatement pstmt = con.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
             pstmt.setObject(i + 1, obj[i]);
        }
       return pstmt.executeUpdate();
    }
    
    /**  
     * �ͷ�������Դ  
     *   
     * @param rs  
     * @param st  
     * @param conn  
     */  
    public static void free(ResultSet rs, Statement st, Connection conn) {   
        try {   
            if (rs != null)   
                rs.close();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (st != null)   
                    st.close();   
            } catch (SQLException e) {   
                e.printStackTrace();   
            } finally {   
                if (conn != null)   
                    try {   
                        conn.close();   
                    } catch (SQLException e) {   
                        e.printStackTrace();   
                    }   
            }   
        }
    }

}


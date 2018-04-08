大概思路:
	需求:实现一个可以通过传入实体对象，然后拼装出正确的sql语句的功能。
如果实体对象里的值有数据，则作为sql条件去查找数据。


1.获取到实体上的类注解并取值，此注解相当于表名。
2.获取实体上的变量注解并取值，此注解相当于字段名。
（实现:
	通过变量注解，取得变量名。再通过反射，取得getter方法的值。
）
3.最后将表名和字段名的值作处理并拼接最终的sql语句。
            









----------------------------------------

步骤:
1.定义一个描述用户表的注解
2.定义一个描述用户表属性字段的注解
3. 定义映射到数据库的bean
4.返回SQL查询语句的实现
5.返回SQL语句的测试类
6.结果



----------------------------------------

具体实现:
1.定义一个描述用户表的注解:
@Target({ElementType.TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Table {  
    String value();  
}   
2.定义一个描述用户表属性字段的注解:
@Target({ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Column {  
    String value();  
} 
3. 定义映射到数据库的bean
(用户表，字段包括：用户ID、用户名、昵称、年龄、性别、所在城市、邮箱、手机号)
@Table("user")  
public class User {  
      
    @Column("id")  
    private int id;  
      
    @Column("user_name")  
    private String userName;  
      
    @Column("nick_name")  
    private String nickName;  
      
    @Column("age")  
    private int age;  
      
    @Column("city")  
    private String city;  
      
    @Column("email")  
    private String email;  
      
    @Column("mobile")  
    private String mobile;  
  
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
    public String getNickName() {  
        return nickName;  
    }  
    public void setNickName(String nickName) {  
        this.nickName = nickName;  
    }  
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public String getCity() {  
        return city;  
    }  
    public void setCity(String city) {  
        this.city = city;  
    }  
    public String getEmail() {  
        return email;  
    }  
    public void setEmail(String email) {  
        this.email = email;  
    }  
    public String getMobile() {  
        return mobile;  
    }  
    public void setMobile(String mobile) {  
        this.mobile = mobile;  
    }  
}  
4.返回SQL查询语句的实现
public class ReturnQuery {  
    public static String query(Object u){  
        StringBuilder sqlStrBuilder = new StringBuilder();  
        //1.获取到Class  
        Class c = u.getClass();  
        //判断是否包含Table类型的注解  
        if(!c.isAnnotationPresent(Table.class)){  
            return null;  
        }  
        //2.获取到table的名字  
        Table t = (Table) c.getAnnotation(Table.class);  
        String tableName = t.value();  
        //加入 where 1=1 ，是为了防止未来如果没有条件的情况下也不会报错  
        sqlStrBuilder.append("select * from ").append(tableName).append(" where 1=1");  
          
        Field[] fArray = c.getDeclaredFields();   //获取类属性的所有字段，  
        //3.遍历所有的字段  
        for (Field field : fArray) {   
            //4.处理每个字段对应的sql  
            //判断是否包含Column类型的注解  
            if(!field.isAnnotationPresent(Column.class)){  
                continue;  
            }  
            //4.1.拿到字段上面注解的值，即Column注解的值  
            Column column = field.getAnnotation(Column.class);  
            String columnName = column.value();    
            //4.2.拿到字段的名  
            String filedName = field.getName();  
            //获取相应字段的getXXX()方法  
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()  
                    + filedName.substring(1);  
            //字段的值  
            Object fieldValue = null;//属性有int、String等，所以定义为Object类  
            try {  
                Method getMethod = c.getMethod(getMethodName);  
                fieldValue = getMethod.invoke(u);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }   
              
            //4.3.拼接sql  
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){  
                continue;  
            }  
            sqlStrBuilder.append(" and ").append(filedName);  
            if(fieldValue instanceof String){  
                if(((String)fieldValue).contains(",")){  
                    String[] values = ((String)fieldValue).split(",");  
                    sqlStrBuilder.append(" in(");  
                    for (String v : values) {  
                        sqlStrBuilder.append("'").append(v).append("'").append(",");  
                    }  
                    sqlStrBuilder.deleteCharAt(sqlStrBuilder.length()-1);  
                    sqlStrBuilder.append(")");  
                }  
                else{  
                    sqlStrBuilder.append("=").append("'").append(fieldValue).append("'");  
                }  
            }  
            else if(fieldValue instanceof Integer){  
                sqlStrBuilder.append("=").append(fieldValue);  
            }  
        }  
        return sqlStrBuilder.toString();  
          
    }  
}  
5.返回SQL语句的测试类
public class Test {  
    public static void main(String[] args) {  
        User u1 = new User();  
        u1.setId(9);  //查询id为9的用户  
          
        User u2 = new User();  
        u2.setUserName("JTZeng");   //模糊查询用户名为JTZeng的用户  
        u2.setAge(21);  
          
        User u3 = new User();  
        u3.setEmail("123@163.com,123@qq.com");  //查询邮箱有任意一个的用户  
          
        String sql1 = ReturnQuery.query(u1);    //查询id为9的用户  
        String sql2 = ReturnQuery.query(u2);    //查询用户名为JTZeng和年龄为21的用户  
        String sql3 = ReturnQuery.query(u3);    //查询邮箱中有任意一个的用户  
          
        System.out.println(sql1);  
        System.out.println(sql2);  
        System.out.println(sql3);  
    }  
}  

6.结果
拼装出正确的sql语句
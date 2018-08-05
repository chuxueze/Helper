1:加密方式与步骤
2:引入相关依赖
3:代码实例









------------------------------------------------------------
1:加密方式与步骤
加密方式:
两次 MD5
1)客户端：PASS = MD5 (明文+固定 Salt)
2)服务商：PASS = MD5 (用户输入+随机 Salt)


客户端进行1次 MD5 之后传递到后端，此操作为
MD5 (明文+固定 Salt)
js 代码:
var SALT = "1a2b3c4d";
var str = "" + SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);



-------------
步骤:
MD5 加密是不可逆的。
保存时:
1)前端为用户输入的密码增加特定的 Salt 特定字符，再拼接上 md5 加密后的密码传递到后端。
2)后端接收到密码之后，再次经过 md5 加密并加盐保存起来，要把后端拼接的随机的盐也保存进数据库。
校验时:
1)前端同样加密并加盐传递给后端
2)后端取到之后，加密加盐，并与数据库保存的密码做比较，盐从对应查出来的数据获取。

好处:
1)前端传递的密码由明文转为密文并加盐，就算被获取，也无法得知真正的明文密码。
2)后端数据库中保存的密码，其实是经过前端与后端两层的加密与加盐得到的，并不是真正的密码，
  就算是获得了数据库权限，也无法得知用户真正的密码。




------------------------
2:引入相关依赖
	  <!--MD5-->
      <dependency>
          <groupId>commons-codec</groupId>
          <artifactId>commons-codec</artifactId>
      </dependency>
      <dependency>
          <groupId>org.apache.commons</groupId>
          <artifactId>commons-lang3</artifactId>
          <version>3.4</version>
      </dependency>
      <!--MD5-->

------------------------
3:代码实例

package com.imooc.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String SALT = "1a2b3c4d";

    /**
     * 从前端输入密码加密
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass){
        String str = "" + SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input,String saltDB){
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        //把formPassWord转化成DBPassWord
        System.out.println(formPassToDBPass(inputPassToFormPass("123456"),"cdh1020 "));
        System.out.println(inputPassToDbPass("123456","cdh1020"));
    }
}



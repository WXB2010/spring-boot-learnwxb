package com.wxb.springbootlearnwxb.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wangxianbing
 * @date 2021-07-21 12:05:14
 */
public class Test2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String,Object> map  =new HashMap<>();
        map.put("actionUid","611979991916318721,244327876889935872");
        String[] split = StringUtils.split(map.get("actionUid") + "", ",");
        for (String s : split) {
            Long actionUid = Long.parseLong(s);
            System.out.println(actionUid);
        }



        /*AtomicLong communicateCount = new AtomicLong(4);
        communicateCount.getAndAdd(2);
        communicateCount.getAndAdd(2);
        System.out.println(communicateCount.get());
        //long andSet = communicateCount.getAndSet(2);
        //System.out.println(andSet);

        System.out.println("================================");
        String fdActionKey = "沟通：\"王红燕;王晋云;123;456\"";
        String[] split = StringUtils.split(fdActionKey, "：");
        System.out.println("====" + split[1]);
        for (String s : split) {
            System.out.println(s);
            String[] split2 = StringUtils.split(s, ";");
            System.out.println("------" + split2.length);
            for (String s1 : split2) {
                System.out.println(s1);
            }
        }*/




        /*Map<Object, Object> map = new HashMap<>();
        map.put("recommunicate",true);

        Boolean recommunicate = Boolean.parseBoolean(map.get("recommunicate")+"");
        System.out.println(recommunicate);*/

        /*String str = "401355493320163328";
        String[] split = StringUtils.split(str, ",");
        for (String s : split) {
            System.out.println(s);
        }*/

        //OrderType anEnum = OrderType.getEnum("1");
        //System.out.println(anEnum);

        //Set<Integer> set = new HashSet<>();
        //set.add(1);
        //set.add(2);
        //set.add(3);
        //System.out.println(set.toString());
        //String a = set.stream().map(Integer::toString);
        //set.stream().map(Integer::intValue).collect(Collectors.joining(""));


        /*User user = new User();
        user.setAge(11);
        user.setName("王先兵");
        user.setFormInstanceId("12345678");

        System.out.println(user.toString());

        Object s = ClassUser(user);
        //System.out.println(s.toString());

        System.out.println("==================");
        String[] fields ={"name","age","formInstanceId"};
        getUser(user,fields);
        //getUser2(user,fields);*/







        /* String locationName = "广州二产云埔";
        String location ="广州二产云埔";
        System.out.println(locationName.contains(location));
        StringBuffer receiveTips = new StringBuffer();
        receiveTips.append("武士").append(":").append("嗡嗡嗡");

        System.out.println(receiveTips.toString());
        receiveTips = new StringBuffer("啦啦啦");
        System.out.println(receiveTips.toString());*/


        //String pattern = "^[1][3,4,5,7,8][0-9]{9}$";
        //String str = "15527020658";
        //boolean matcher = !Pattern.matches(pattern,str);
        //System.out.println(matcher);

        /*String regEx="[a-zA-Z]";
        String str = "https://fee.cvte.com/h5/#/materials?cd=IT00041702";
        System.out.println(str.indexOf("="));
        System.out.println(str.indexOf("=")!=-1);
        //截取?之前字符串
        //String str0=str.substring(str.indexOf("=")+1);
        //System.out.println(str0);
        //截取?之后字符串
        //String str2=str.substring(str0.length()+1);
        //System.out.println(str2);
        Pattern compile = Pattern.compile(regEx);
        Matcher matcher = compile.matcher(str);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()){
            buffer.append(matcher.group());
        }
        System.out.println(buffer.toString());*/
    }

    private static Object ClassUser(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object o = null;
        try {
            Class clazz = obj.getClass();
            Field[] fields = obj.getClass().getDeclaredFields();//获得属性
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
                        clazz);
                Method getMethod = pd.getReadMethod();//获得get方法
                o = getMethod.invoke(obj);//执行get方法返回一个Object
                System.out.println("field.getName():" + field.getName() + ",field:" + field + ",执行get方法返回一个Object:" + o);

            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }

    private static Object getUser(Object obj, String[] fileds) {
        Object o = null;
        Map<String, Object> param = new HashMap<>(10);
        try {
            Class clazz = obj.getClass();
            for (String filed : fileds) {

                PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);
                Method getMethod = pd.getReadMethod();//获得get方法
                if (pd != null) {

                    o = getMethod.invoke(obj);//执行get方法返回一个Object
                    param.put(filed, o);
                    System.out.println("filed:" + filed + ",执行get方法返回一个Object:" + o);

                }
            }


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("map====" + param.toString());
        return o;
    }

    private static Object getUser2(Object obj, String[] fileds) {
        Class<?> aClass = obj.getClass();
        Map<String, Object> param = new HashMap<>(10);
        try {
            for (String filed : fileds) {
                Field declaredField = aClass.getDeclaredField(filed);
                if (declaredField == null) {
                    continue;
                }
                declaredField.setAccessible(true);
                param.put(filed, declaredField.get(obj).toString());
                System.out.println("filed:" + filed + "，执行get方法获取的值：" + declaredField.get(obj).toString());


            }
        } catch (Exception e) {

        }
        System.out.println("map====" + param.toString());
        return null;
    }

}

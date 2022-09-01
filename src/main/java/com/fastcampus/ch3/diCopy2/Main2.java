package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{}
class SportsCar extends Car{}
class Truck extends Car{}
class Engine{}
class AppContext {
    Map map; //객체 저장소

    AppContext() {
       /* map.put("car", new SportsCar());
        map.put("engine",new Engine());
*/
        try {

            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            //Properties에 저장된 내용을 Map에 저장

            map = new HashMap(p);

            //반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장
            for (Object key : map.keySet()){

                Class clazz = Class.forName((String) map.get(key));//map 에 저장된 key 를 이용해 clazz 정보를 얻어온다.
                map.put(key, clazz.newInstance());
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);
    }
}


public class Main2 {
    public static void main(String[] args) throws Exception {

        AppContext ac = new AppContext();
        Car car = (Car) ac.getBean("car");
        System.out.println("car = " + car);
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("Engine = " + engine);
    }

    static Object getBean(String key) throws Exception{
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));

        return clazz.newInstance();
    }
    static Car getCar() throws Exception{
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty("car"));

        return (Car) clazz.newInstance();
    }
}

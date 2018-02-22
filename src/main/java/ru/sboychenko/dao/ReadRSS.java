// This Project created by Anton Romanov (www.antonromanov.com) 20.02.2018 at 15:31



package ru.sboychenko.dao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadRSS {


    List<Person> titles  = new ArrayList<>();
  //  List<Person> titles  = Arrays.asList();

    public List<Person> jaxbparse() {
        try {

            JAXBContext jc = JAXBContext.newInstance("ru.sboychenko.dao");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            URL rssUrl = new URL("https://news.yandex.ru/science.rss");
           //URL rssUrl = new URL("http://bdoughan.blogspot.com/atom.xml");
            InputStream xml = rssUrl.openStream();

    //        MyFeed myfeed = (MyFeed) unmarshaller.unmarshal(new StreamSource(xml));



           // File customerXML = new File("src/ya.xml");
          //  Rss rss = (Rss) JAXBIntrospector.getValue(unmarshaller.unmarshal(customerXML));
            Rss rss = (Rss) JAXBIntrospector.getValue(unmarshaller.unmarshal(new StreamSource(xml)));

            Rss.Channel channel = rss.getChannel();
            List<Rss.Channel.Item> items = channel.getItem();

            System.out.println("///");
           // System.out.println(rss.GetMyTitle());

            for (Rss.Channel.Item its : items) {
            //    System.out.println(its.getTitle());

             /*   List<Person> people = Arrays.asList(
                        new Person(its.getTitle()));*/

                titles.add(new Person(its.getTitle()));

                //return titles;

            }

            for (Person tttt : titles) {
                System.out.println(tttt.getName());

            }

            System.out.println("---------------------закончено -----------------------");



        } catch (JAXBException e) {
            e.printStackTrace();
        }  catch (IOException ioe) {
            System.out.println("Что-то пошло не так....");

        }

        //return null;
        System.out.println(titles.get(1));
        return titles;

    }

}

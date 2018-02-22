package ru.sboychenko.dao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadRSS {

    List<Person> titles = new ArrayList<>();

    public List<Person> jaxbparse() {
        try {

            JAXBContext jc = JAXBContext.newInstance("ru.sboychenko.dao");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            URL rssUrl = new URL("https://news.yandex.ru/science.rss");
            InputStream xml = rssUrl.openStream();
            Rss rss = (Rss) JAXBIntrospector.getValue(unmarshaller.unmarshal(new StreamSource(xml)));
            Rss.Channel channel = rss.getChannel();
            List<Rss.Channel.Item> items = channel.getItem();
            titles.clear();

            for (Rss.Channel.Item its : items) {
                titles.add(new Person(its.getTitle()));
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Что-то пошло не так....");
        }
        return titles;
    }

}

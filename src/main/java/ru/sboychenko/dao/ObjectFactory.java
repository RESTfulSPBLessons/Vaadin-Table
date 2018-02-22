package ru.sboychenko.dao;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public Rss createRss() {
        return new Rss();
    }

    public Rss.Channel createRssChannel() {
        return new Rss.Channel();
    }

    public Rss.Channel.Image createRssChannelImage() {
        return new Rss.Channel.Image();
    }

    public Rss.Channel.Item createRssChannelItem() {
        return new Rss.Channel.Item();
    }

}

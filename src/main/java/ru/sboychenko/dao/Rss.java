package ru.sboychenko.dao;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "channel"
})
@XmlRootElement(name = "rss")
public class Rss {

    @XmlElement(required = true)
    protected Rss.Channel channel;
    @XmlAttribute(name = "version")
    protected String version;


    public Rss.Channel getChannel() {
        return channel;
    }


    public void setChannel(Rss.Channel value) {
        this.channel = value;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String value) {
        this.version = value;
    }

    public String GetMyTitle() {

        Channel ch = getChannel();
        return ch.getTitle();
    }

    public List<String> GetAllItemsTitle() {

        Channel ch = getChannel();
        List<String> mynames = new ArrayList();

        for(Rss.Channel.Item item : ch.getItem()){
            mynames.add(item.getTitle());
        }

        return mynames;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "title",
            "link",
            "description",
            "image",
            "lastBuildDate",
            "item"
    })
    public static class Channel {

        @XmlElement(required = true)
        protected String title;
        @XmlElement(required = true)
        protected String link;
        @XmlElement(required = true)
        protected String description;
        @XmlElement(required = true)
        protected Rss.Channel.Image image;
        @XmlElement(required = true)
        protected String lastBuildDate;
        protected List<Rss.Channel.Item> item;


        public String getTitle() {
            return title;
        }


        public void setTitle(String value) {
            this.title = value;
        }


        public String getLink() {
            return link;
        }


        public void setLink(String value) {
            this.link = value;
        }


        public String getDescription() {
            return description;
        }


        public void setDescription(String value) {
            this.description = value;
        }


        public Rss.Channel.Image getImage() {
            return image;
        }


        public void setImage(Rss.Channel.Image value) {
            this.image = value;
        }


        public String getLastBuildDate() {
            return lastBuildDate;
        }


        public void setLastBuildDate(String value) {
            this.lastBuildDate = value;
        }


        public List<Rss.Channel.Item> getItem() {
            if (item == null) {
                item = new ArrayList<Rss.Channel.Item>();
            }
            return this.item;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url",
                "link",
                "title"
        })
        public static class Image {

            @XmlElement(required = true)
            protected String url;
            @XmlElement(required = true)
            protected String link;
            @XmlElement(required = true)
            protected String title;


            public String getUrl() {
                return url;
            }


            public void setUrl(String value) {
                this.url = value;
            }


            public String getLink() {
                return link;
            }


            public void setLink(String value) {
                this.link = value;
            }


            public String getTitle() {
                return title;
            }


            public void setTitle(String value) {
                this.title = value;
            }

        }



        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "title",
                "link",
                "description",
                "pubDate",
                "guid"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String title;
            @XmlElement(required = true)
            protected String link;
            @XmlElement(required = true)
            protected String description;
            @XmlElement(required = true)
            protected String pubDate;
            @XmlElement(required = true)
            protected String guid;


            public String getTitle() {
                return title;
            }


            public void setTitle(String value) {
                this.title = value;
            }


            public String getLink() {
                return link;
            }


            public void setLink(String value) {
                this.link = value;
            }


            public String getDescription() {
                return description;
            }


            public void setDescription(String value) {
                this.description = value;
            }


            public String getPubDate() {
                return pubDate;
            }


            public void setPubDate(String value) {
                this.pubDate = value;
            }

            public String getGuid() {
                return guid;
            }


            public void setGuid(String value) {
                this.guid = value;
            }

        }

    }

}

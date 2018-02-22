package ru.sboychenko.dao;



import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ar.ya package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ar.ya
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Rss }
     *
     */
    public Rss createRss() {
        return new Rss();
    }

    /**
     * Create an instance of {@link Rss.Channel }
     *
     */
    public Rss.Channel createRssChannel() {
        return new Rss.Channel();
    }

    /**
     * Create an instance of {@link Rss.Channel.Image }
     *
     */
    public Rss.Channel.Image createRssChannelImage() {
        return new Rss.Channel.Image();
    }

    /**
     * Create an instance of {@link Rss.Channel.Item }
     *
     */
    public Rss.Channel.Item createRssChannelItem() {
        return new Rss.Channel.Item();
    }


}

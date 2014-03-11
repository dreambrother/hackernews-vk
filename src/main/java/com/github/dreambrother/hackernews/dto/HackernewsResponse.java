package com.github.dreambrother.hackernews.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class HackernewsResponse {

    @XmlElement
    private HackernewsChannel channel;

    public HackernewsChannel getChannel() {
        return channel;
    }

    public void setChannel(HackernewsChannel channel) {
        this.channel = channel;
    }
}

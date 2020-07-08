package com.okta.spring.example.xmldto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "video")
@XmlAccessorType(XmlAccessType.FIELD)
public class Video implements Serializable {

    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ref_no")
    private String refNo;

    @XmlElement(name="clip_key")
    private String clipKey;

    @XmlElement(name="title")
    private String title;

    @XmlElementWrapper(name="tags")
    @XmlElement(name="tag")
    private List<String> tags;

    @XmlElement(name="tag_number")
    private String tagNumber;

    @XmlElement(name="tag_string")
    private String tagString;

    @XmlElement(name="video_source")
    private String videoSource;

    @XmlElement(name="stream_name")
    private String streamName;

    @XmlElement(name="channel_ref")
    private String channelRef;

    @XmlElement(name="duration")
    private String duration;

    @XmlElement(name="date_created")
    private String dateCreated;

    @XmlElement(name="date_modified")
    private String dateModified;

    @XmlElement(name="file_size")
    private String fileSize;

    private byte[] image;

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getClipKey() {
        return clipKey;
    }

    public void setClipKey(String clipKey) {
        this.clipKey = clipKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getChannelRef() {
        return channelRef;
    }

    public void setChannelRef(String channelRef) {
        this.channelRef = channelRef;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
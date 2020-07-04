package com.okta.spring.example.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    private String refNo;
    private String clipKey;
    private String title;
    private String tags;
    private String tagNumber;
    private String tagString;
    private String videoSource;
        private String streamName;
        private String channelRef;
        private String duration;
        private String dateCreated;
        private String dateModified;
        private String fileSize;

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

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
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

}
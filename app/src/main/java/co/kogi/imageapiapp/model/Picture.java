package co.kogi.imageapiapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 5/07/2016.
 */
public class Picture implements Parcelable {
    String id;
    String title;
    String description;
    long datetime;
    String type;
    boolean animated;
    long width;
    long height;
    long size;
    long views;
    long bandwidth;
    String deletehash;
    String link;
    String gifv;
    String mp4;
    long mp4_size;
    boolean looping;
    String vote;
    boolean favorite;
    boolean nsfw;
    long comment_count;
    String topic;
    long topic_id;
    String section;
    String account_url;
    long account_id;
    long ups;
    long downs;
    long polongs;
    long score;
    boolean is_album;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGifv() {
        return gifv;
    }

    public void setGifv(String gifv) {
        this.gifv = gifv;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public long getMp4_size() {
        return mp4_size;
    }

    public void setMp4_size(long mp4_size) {
        this.mp4_size = mp4_size;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAccount_url() {
        return account_url;
    }

    public void setAccount_url(String account_url) {
        this.account_url = account_url;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getUps() {
        return ups;
    }

    public void setUps(long ups) {
        this.ups = ups;
    }

    public long getDowns() {
        return downs;
    }

    public void setDowns(long downs) {
        this.downs = downs;
    }

    public long getPolongs() {
        return polongs;
    }

    public void setPolongs(long polongs) {
        this.polongs = polongs;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public boolean is_album() {
        return is_album;
    }

    public void setIs_album(boolean is_album) {
        this.is_album = is_album;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeLong(this.datetime);
        dest.writeString(this.type);
        dest.writeByte(this.animated ? (byte) 1 : (byte) 0);
        dest.writeLong(this.width);
        dest.writeLong(this.height);
        dest.writeLong(this.size);
        dest.writeLong(this.views);
        dest.writeLong(this.bandwidth);
        dest.writeString(this.deletehash);
        dest.writeString(this.link);
        dest.writeString(this.gifv);
        dest.writeString(this.mp4);
        dest.writeLong(this.mp4_size);
        dest.writeByte(this.looping ? (byte) 1 : (byte) 0);
        dest.writeString(this.vote);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeByte(this.nsfw ? (byte) 1 : (byte) 0);
        dest.writeLong(this.comment_count);
        dest.writeString(this.topic);
        dest.writeLong(this.topic_id);
        dest.writeString(this.section);
        dest.writeString(this.account_url);
        dest.writeLong(this.account_id);
        dest.writeLong(this.ups);
        dest.writeLong(this.downs);
        dest.writeLong(this.polongs);
        dest.writeLong(this.score);
        dest.writeByte(this.is_album ? (byte) 1 : (byte) 0);
    }

    public Picture() {
    }

    protected Picture(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.datetime = in.readLong();
        this.type = in.readString();
        this.animated = in.readByte() != 0;
        this.width = in.readLong();
        this.height = in.readLong();
        this.size = in.readLong();
        this.views = in.readLong();
        this.bandwidth = in.readLong();
        this.deletehash = in.readString();
        this.link = in.readString();
        this.gifv = in.readString();
        this.mp4 = in.readString();
        this.mp4_size = in.readLong();
        this.looping = in.readByte() != 0;
        this.vote = in.readString();
        this.favorite = in.readByte() != 0;
        this.nsfw = in.readByte() != 0;
        this.comment_count = in.readLong();
        this.topic = in.readString();
        this.topic_id = in.readLong();
        this.section = in.readString();
        this.account_url = in.readString();
        this.account_id = in.readLong();
        this.ups = in.readLong();
        this.downs = in.readLong();
        this.polongs = in.readLong();
        this.score = in.readLong();
        this.is_album = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}

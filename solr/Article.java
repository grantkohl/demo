package demo.solr;

import java.util.Arrays;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class Article {
    @Id
    @Field
    private String id;
    @Field
    private String[] title;
    @Field
    private Date[] time;
    @Field
    private String[] author;
    @Field
    private String[] info;
    // 省略getter和setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public Date[] getTime() {
        return time;
    }

    public void setTime(Date[] time) {
        this.time = time;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title=" + Arrays.toString(title) +
                ", time=" + Arrays.toString(time) +
                ", author=" + Arrays.toString(author) +
                ", info=" + Arrays.toString(info) +
                '}';
    }
}
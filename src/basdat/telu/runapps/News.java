/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basdat.telu.runapps;

import java.util.List;

/**
 *
 * @author Aas Suhendar
 */
public class News {

    String id;
    String author;
    String neigbhord;
    List<News> listNewsSameAuthor;

    public News() {
    }

    public News(String id, String author) {
        this.id = id;
        this.author = author;
    }
    
    

    public News(String id, String author, List<News> listNewsSameAuthor) {
        this.id = id;
        this.author = author;
        this.listNewsSameAuthor = listNewsSameAuthor;
    }

    public News(String id, String author, String neigbhord) {
        this.id = id;
        this.author = author;
        this.neigbhord = neigbhord;
    }

    public String getNeigbhord() {
        return neigbhord;
    }

    public void setNeigbhord(String neigbhord) {
        this.neigbhord = neigbhord;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<News> getListNewsSameAuthor() {
        return listNewsSameAuthor;
    }

    public void setListNewsSameAuthor(List<News> listNewsSameAuthor) {
        this.listNewsSameAuthor = listNewsSameAuthor;
    }

}

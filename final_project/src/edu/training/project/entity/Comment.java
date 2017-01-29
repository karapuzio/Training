package edu.training.project.entity;

/**
 * Created by Dell on 26.12.2016.
 */
public class Comment {
    private int id;
//    private int like;
    private String content;
    private int userId;
    private int songId;

    public Comment(){
    }

    public Comment(int id, String content, int userId, int songId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Comment comment = (Comment) object;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (songId != comment.songId) return false;
        return content != null ? content.equals(comment.content) : comment.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + songId;
        return result;
    }


}

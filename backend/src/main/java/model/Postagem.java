package model;

import java.sql.Date;
import java.util.UUID;

import org.json.JSONObject;

public class Postagem {
  private UUID id;
  private String title;
  private String content;
  private String media;
  private UUID categoryId;
  private Date date;
  private String ownerUsername;

  public Postagem(UUID id, String title, String content, String media, UUID categoryId, Date date,
      String ownerUsername) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.media = media;
    this.categoryId = categoryId;
    this.date = date;
    this.ownerUsername = ownerUsername;
  }

  public JSONObject toJSON() {
    JSONObject resp = new JSONObject();

    resp.put("id", id.toString());
    resp.put("title", title);
    resp.put("content", content);
    resp.put("media", media);
    resp.put("categoryId", categoryId.toString());
    resp.put("date", date.getTime());
    resp.put("ownerUsername", ownerUsername);

    return resp;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getMedia() {
    return media;
  }

  public void setMedia(String media) {
    this.media = media;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }
}

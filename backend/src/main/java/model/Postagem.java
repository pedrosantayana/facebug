package model;

import java.sql.Date;
import java.util.UUID;

public class Postagem {
  private UUID id;
  private Usuario user;
  private String content;
  private String media;
  private Categoria category;
  private String title;
  private Date date;

  public Postagem(UUID id, Usuario user, String content, String media, Categoria category, String title, Date date) {
    this.id = id;
    this.user = user;
    this.content = content;
    this.media = media;
    this.category = category;
    this.title = title;
    this.date = date;
  }
  public Postagem() {
    
  }
  public Usuario getUser() {
    return user;
  }
  public void setUser(Usuario user) {
    this.user = user;
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
  public Categoria getCategory() {
    return category;
  }
  public void setCategory(Categoria category) {
    this.category = category;
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
}

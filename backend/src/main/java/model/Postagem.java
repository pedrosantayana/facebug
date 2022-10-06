package model;

public class Postagem {
  private Usuario user;
  private String content;
  private String media;
  private Categoria category;

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
}

package utilities;

import java.util.LinkedList;

public class Token{
  private int id;
  private boolean mark;
  private LinkedList<Token> adjacentList;
  private int coordenateX;
  private int coordenateY;

  public Token(int idX){
    this.id = idX;
    this.mark = false;
    this.coordenateX = -1;
    this.coordenateY = -1;
    this.adjacentList = new LinkedList<Token>();
  }

  public Token(int idX, boolean markX){
    this.id = idX;
    this.mark = markX;
    this.coordenateX = -1;
    this.coordenateY = -1;
    this.adjacentList = new LinkedList<Token>();
  }

  public int getId(){
    return this.id;
  }

  public boolean getMark(){
    return this.mark;
  }

  public int getCoordenateX(){
    return this.coordenateX;
  }

  public int getCoordenateY(){
    return this.coordenateY;
  }

  public LinkedList<Token> getAdjacent(){
    return adjacentList;
  }

  public void setId(int idX){
    this.id = idX;
  }

  public void setMark(boolean markX){
    this.mark = markX;
  }

  public void setCoordenateX(int coordenate){
    this.coordenateX = coordenate;
  }

  public void setCoordenateY(int coordenate){
    this.coordenateY = coordenate;
  }

  public void setAdjacentList(LinkedList<Token> adj){
    this.adjacentList = adj;
  }

  public void addAdjacent(Token x){
    this.adjacentList.add(x);
  }

  public boolean equals(Token other){
    return (this.id == other.id && this.coordenateX == other.coordenateX && this.coordenateY == other.coordenateY) ;
  }
}

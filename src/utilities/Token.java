package utilities;

/**
 * Title:        Token
 * Description:  Implements a token
 * @author Armas Lucas, Sanchez Daniel
 * @version 0.1
 */
public class Token{
  private int id;
  private boolean mark;
  private int coordenateX;
  private int coordenateY;

  /**
   * Class builder
   * @param  int idX           type of the token
   */
  public Token(int idX){
    this.id = idX;
    this.mark = false;
    this.coordenateX = -1;
    this.coordenateY = -1;
  }

  /**
   * Class builder
   * @param  int     idX           type of the token
   * @param  boolean markX         mark of the token
   */
  public Token(int idX, boolean markX){
    this.id = idX;
    this.mark = markX;
    this.coordenateX = -1;
    this.coordenateY = -1;
  }

  /**
   * [Token description]
   * @param  int idX           type of the token
   * @param  int row           coordenate x of the token on the board
   * @param  int column        coordenate y of the token on the board
   */
  public Token(int idX, int row, int column){
    this.id = idX;
    this.mark = false;
    this.coordenateX = column;
    this.coordenateY = row;
  }

  /**
   * Indicates the type of the token
   * @return int, 1 if it is a user's token and 2 if it is a CPU's token
   */
  public int getId(){
    return this.id;
  }

  /**
   * Indicates whether a token is visited
   * @return true if it is visited or false if it is not visited
   */
  public boolean getMark(){
    return this.mark;
  }
  /**
   * Indicates the x-coordinate of the token
   * @return int between 0 and 6
   */
  public int getCoordenateX(){
    return this.coordenateX;
  }

  /**
   * Indicates the y-coordinate of the token
   * @return int between 0 and 6
   */
  public int getCoordenateY(){
    return this.coordenateY;
  }

  /**
   * Set the type of the token
   * @param int idX type of the token
   */
  public void setId(int idX){
    this.id = idX;
  }

  /**
   * Set the mark on the token
   * @param boolean markX true if it is visited or false if it is not visited
   */
  public void setMark(boolean markX){
    this.mark = markX;
  }

  /**
   * Set de x-coordinate of the token
   * @param int coordenate
   */
  public void setCoordenateX(int coordenate){
    this.coordenateX = coordenate;
  }

  /**
   * Set de y-coordinate of the token
   * @param int coordenate
   */
  public void setCoordenateY(int coordenate){
    this.coordenateY = coordenate;
  }

  /**
   * Indicates whether two states are equal
   * @param  Token other
   * @return       true if they are same or false if they are different
   */
  public boolean equals(Token other){
    return (this.id == other.id && this.coordenateX == other.coordenateX && this.coordenateY == other.coordenateY) ;
  }
}

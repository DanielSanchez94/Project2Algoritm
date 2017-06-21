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
   * @pre idX==1 or idX==2
   * @post a new token with id 1 or 2
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
   * @pre (idX==1 or idX==2) and (markX==true or markX==false)
   * @post a new token with id 1 or 2 and a mark
   */
  public Token(int idX, boolean markX){
    this.id = idX;
    this.mark = markX;
    this.coordenateX = -1;
    this.coordenateY = -1;
  }

  /**
   * Class builder
   * @param  int idX           type of the token
   * @param  int row           coordenate x of the token on the board
   * @param  int column        coordenate y of the token on the board
   * @pre (idX==1 or idX==2) and row>=0 and row<=6 and column>=0 and column<=6
   * @post a new token with id 1 or 2 and
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
   * @pre true
   * @post token id is returned
   */
  public int getId(){
    return this.id;
  }

  /**
   * Indicates whether a token is visited
   * @return true if it is visited or false if it is not visited
   * @pre true
   * @post token mark is returned
   */
  public boolean getMark(){
    return this.mark;
  }

  /**
   * Indicates the x-coordinate of the token
   * @return int between 0 and 6
   * @pre true
   * @post token x-coordinate is returned
   */
  public int getCoordenateX(){
    return this.coordenateX;
  }

  /**
   * Indicates the y-coordinate of the token
   * @return int between 0 and 6
   * @pre true
   * @post token y-coordinate is returned
   */
  public int getCoordenateY(){
    return this.coordenateY;
  }

  /**
   * Set the type of the token
   * @param int idX type of the token
   * @pre idX==1 or idX==2
   * @post id is set
   */
  public void setId(int idX){
    this.id = idX;
  }

  /**
   * Set the mark on the token
   * @param boolean markX true if it is visited or false if it is not visited
   * @pre markX==true or markX==false
   * @post mark is set
   */
  public void setMark(boolean markX){
    this.mark = markX;
  }

  /**
   * Set de x-coordinate of the token
   * @param int coordenate
   * @pre coordenate>=0 and coordenate<=6
   * @post x-coordinate is set
   */
  public void setCoordenateX(int coordenate){
    this.coordenateX = coordenate;
  }

  /**
   * Set de y-coordinate of the token
   * @param int coordenate
   * @pre coordenate>=0 and coordenate<=6
   * @post y-coordinate is set
   */
  public void setCoordenateY(int coordenate){
    this.coordenateY = coordenate;
  }

  /**
   * Indicates whether two states are equal
   * @param  Token other
   * @return       true if they are same or false if they are different
   * @pre this!=null and other!=null
   * @post true if this and other are the same or false if they are not
   */
  public boolean equals(Token other){
    return (this.id == other.id && this.coordenateX == other.coordenateX && this.coordenateY == other.coordenateY) ;
  }
}

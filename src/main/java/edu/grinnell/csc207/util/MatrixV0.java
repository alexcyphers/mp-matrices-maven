package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Alex Cyphers
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  private T[][] contents;

  private int width;

  private int height;

  private T defaultVal;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) {
    this.width = width;
    this.height = height;
    this.defaultVal = def;
    this.contents = (T[][]) new Object[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.contents[i][j] = def;
      } // for-loop
    } // for-loop
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return contents.length;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return contents[0].length;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
   @SuppressWarnings("unchecked")
  public void insertRow(int row) {
     T[] defRow = (T[]) new Object[width];
     for (int i = 0; i < width; i++) {
       defRow[i] = defaultVal;
     }
     insertRow(row, defRow);
   } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row, T[] vals) {
    if (row < 0 || row > height) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] tempContents = (T[][]) new Object[height + 1][width];
      for (int i = 0; i < row; i++) {
        tempContents[i] = contents[i];
      } // for-loop
      tempContents[row] = vals;
      for (int i = row; i < height; i++) {
        tempContents[i + 1] = contents[i];
      } // for-loop
      contents = tempContents;
      height++;
    } // if/else
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col) {
    T[] defCol = (T[]) new Object[height];
    for (int i = 0; i < height; i++) {
      defCol[i] = defaultVal;
    }
    insertCol(col, defCol);
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col, T[] vals) {
    if (col > width || col < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] tempContents = (T[][]) new Object[height][width + 1];
      for (int r = 0; r < height(); r++) {
        for (int i = 0; i < col; i++) {
          tempContents[r][i] = contents[r][i];
        } // for-loop
        tempContents[r][col] = vals[r];
        for (int i = col; i < width(); i++) {
          tempContents[r][i + 1] = contents[r][i];
        } // for-loop
      } // for-loop
      contents = tempContents;
      width++;
    } // if/else
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  @SuppressWarnings("unchecked")
  public void deleteRow(int row) {
    if (row < 0 || row >= height) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] tempContents = (T[][]) new Object[height() - 1][width()];
      
      for (int i = 0; i < row; i++) {
        tempContents[i] = contents[i];
      } // for-loop
      for (int i = row + 1; i < height(); i++) {
        tempContents[i - 1] = contents[i];
      } // for-loop
      contents = tempContents;
      height--;
    } // if/else
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  @SuppressWarnings("unchecked")
  public void deleteCol(int col) {
    if (col < 0 || col >= width) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] tempContents = (T[][]) new Object[height()][width() - 1];
      for (int r = 0; r < height(); r++) {
        for (int i = 0; i < col; i++) {
          tempContents[r][i] = contents[r][i];
        } // for-loop
        for (int i = col + 1; i < width(); i++) {
          tempContents[r][i - 1] = contents[r][i];
        } // for-loop
      } // for-loop
      contents = tempContents;
      width--;
    } // if/else
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    for (int r = startRow; r < endRow; r++) {
      for (int c = startCol; c < endCol; c++) {
        contents[r][c] = val;
      } // for-loop
    } // for-loop
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int r = startRow;
    int c = startCol;
    while (r < endRow && c < endCol) {
      contents[r][c] = val;
      r += deltaRow;
      c += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    MatrixV0<T> clonedMatrix = new MatrixV0<>(width(), height(), defaultVal);
    for (int i = 0; i < height(); i++) {
      for (int j = 0; j < width(); j++) {
        clonedMatrix.set(i, j, get(i, j));
      }
    }
    return clonedMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) {
      return false;
    } else {
      MatrixV0<T> obj = (MatrixV0<T>) other;
      if (height != obj.height() || width != obj.width()) {
        return false;
      } // if
      for (int i = 0; i < height(); i++) {
        for (int j = 0; j < width(); j++) {
          if (!this.get(i, j).equals(obj.get(i, j))) {
            return false;
          } // if
        } // for-loop
      } // for-loop
    } // if/else
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0

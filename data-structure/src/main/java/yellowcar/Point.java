package yellowcar;

import java.util.Objects;

public class Point {

    private Integer pointX;

    private Integer pointY;

    public Point(Integer pointX, Integer pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public Integer getPointX() {
        return pointX;
    }

    public void setPointX(Integer pointX) {
        this.pointX = pointX;
    }

    public Integer getPointY() {
        return pointY;
    }

    public void setPointY(Integer pointY) {
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "[" + this.pointX + ", " + this.pointY + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(pointX, point.pointX) && Objects.equals(pointY, point.pointY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointX, pointY);
    }
}

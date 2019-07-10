public class PointCalculator {

    private Point rightTop;
    private Point leftBottom;

    public PointCalculator(Point rightTop, Point leftBottom) {

        this.rightTop = rightTop;
        this.leftBottom = leftBottom;
    }

    public Point getRelative(RelativePosition pos){

        double xPercent = pos.xPercent;
        double yPercent = pos.yPercent;

        double xAxis = rightTop.getX() - leftBottom.getX();
        double yAxis = leftBottom.getY() - rightTop.getY();

        double x = leftBottom.getX() + (xAxis*xPercent)/100;
        double y = rightTop.getY() + (yAxis*yPercent)/100;

        return new Point((int)x,(int)y);
    }
}

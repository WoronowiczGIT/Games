public enum RelativePosition {
    BATTLE_START(91,91,52000),
    WATCH_ADD(50,86,0),
    CLOSE_ADD1(95,6,0),
    CLOSE_ADD2(99,1,0),
    CLOSE_ADD3(98,2,0),
    CLOSE_ADD4(97,3,0),
    ARCHER(29,39,7000),
    ARCHER2(23,39,8000),
    BISHOP(29,25,10000)

    ;
    double xPercent;
    double yPercent;
    int interval;

    RelativePosition(double xPercent, double yPercent, int interval) {
        this.xPercent = xPercent;
        this.yPercent = yPercent;
        this.interval = interval;
    }
}

public enum RelativePosition {
    GAME_ON(2,2,0),
    BATTLE_START(92,92,52000),

    WATCH_ADD(48,86,0),
    CLOSE_ADD_MAX(93,10,0),

    MAT_ITEM(45,76,0),
    SELECT_DRAGON(49,32,0),
    GREEN_DRAGON(89,22,0),
    BLACK_DRAGON(89,38,0),
    RED_DRAGON(89,54,0),

    ARCHER(29,39,1000),
    ARCHER2(23,39,1000),
    BISHOP(29,25,1000)

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

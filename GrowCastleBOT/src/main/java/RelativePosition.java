public enum RelativePosition {
    CONTROL_POINT(1,3,0),
    BATTLE_START(92,92,52000),
    REPLAY(78,90,52000),
    REPLAY_MODE1(66,87,52000),

    MID_POINT(50,50,0),
    CLOSE(75,69,0),

    WATCH_ADD(48,86,0),
    CLOSE_ADD_MAX(93,10,0),

    MAT_ITEM(45,76,0),
    SELECT_DRAGON(49,32,0),
    GREEN_DRAGON(89,22,0),
    BLACK_DRAGON(89,38,0),
    RED_DRAGON(89,54,0),

    ARCHER(29,39,100),
    ARCHER2(23,39,100),
    BISHOP(29,25,100),
    SMITH(29,11,100),
    WITCH1(29,53,100),
    WITCH2(35,53,100),

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

1.获取当前系统时间:
2.计算时间间隔:


















-----------------------------------------------------------------------------------------------------------------
1.获取当前系统时间:

private static void test23(){
    //获取当前时间
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate);
    LocalTime localTime = LocalTime.now();
    System.out.println(localTime);
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    //输出指定时间
    LocalDateTime localDateTime12 = LocalDateTime.of(2018,12,12,10,4,30,338);
    System.out.println(localDateTime12);
}

输出:
2018-11-21
17:26:06.873
2018-11-21T17:26:06.874
2018-12-12T10:04:30.000000338


--------------------------

private static void test24() {
    Instant instant = Instant.now();
    //UTC标准时区时间
    System.out.println(instant);
    //带偏侈量的时区时间
    System.out.println(instant.atOffset(ZoneOffset.ofHours(8)));
    //时间戳
    System.out.println(instant.toEpochMilli());
}

输出:
2018-11-21T09:44:38.102Z
2018-11-21T17:44:38.102+08:00
1542793991703


----------------------------------------------------------
2.计算时间间隔:

	private static void test2(){
        Instant instantStart = Instant.now();
        //睡眠一小时
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instantEnd = Instant.now();

        //计算两个时间相隔的时间
        Duration duration = Duration.between(instantStart,instantEnd);
        System.out.println(duration.toNanos());
    }
输出:
1000000

------------------------------
	private static void test3(){
        LocalDate localDate1 = LocalDate.of(2012,7,22);
        LocalDate localDate2 = LocalDate.now();

        //计算两个日期的间隔时间，年，月，日
        Period period = Period.between(localDate1,localDate2);
        System.out.println(period.getYears() + "" + period.getMonths() + "" + period.getDays() );
    }
输出:
6330
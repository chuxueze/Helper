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
/**
 * java8 计算两个日期之间的天数
 */
public interface DateUtil {

    /**
     * 计算当前日期与{@code endDate}的间隔天数
     *
     * @param endDate
     * @return 间隔天数
     */
     static long until(LocalDate endDate){
      return LocalDate.now().until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 计算日期{@code startDate}与{@code endDate}的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return 间隔天数
     */
    static long until(LocalDate startDate, LocalDate endDate){
        return startDate.until(endDate, ChronoUnit.DAYS);
    }



}





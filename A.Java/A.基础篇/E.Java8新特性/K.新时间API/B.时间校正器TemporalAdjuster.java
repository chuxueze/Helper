1.时间校正器










----------------------------------------------------------------------------------------------------------------------------------------
1.时间校正器
  TemporalAdjuster
  有时我们可能需要获取例如:将日期调整到“下个周日”等的操作。

  TemporalAdjusters:该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现
  如:



获取下个周日:
private static void test26(){
    //get next sunday
    LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    System.out.println(nextSunday);
}


自定义日期
获取下个工作日
private static void test27(){
    //Custom date
    //get next working day
    LocalDateTime newWorkingDay = LocalDateTime.now().with((temporal) -> {
        LocalDateTime localDateTime = (LocalDateTime) temporal;
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        if(DayOfWeek.FRIDAY.equals(dayOfWeek)){
            return localDateTime.plusDays(1);
        }else if(DayOfWeek.SATURDAY.equals(dayOfWeek)) {
            return localDateTime.plusDays(2);
        }else {
            return localDateTime.plusDays(1);
        }
    });
    System.out.println(newWorkingDay);
}
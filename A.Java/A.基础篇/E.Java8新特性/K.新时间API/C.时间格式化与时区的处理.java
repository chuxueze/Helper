1.DateTimeFormatter:格式化时间/日期
2.时区的处理












--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1.DateTimeFormatter:格式化时间/日期

private static void test28(){
    //time formatting
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
    LocalDateTime localDateTime = LocalDateTime.now();
    String dateStr = localDateTime.format(dtf);
    System.out.println(dateStr);

    //custom time formatting
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
    String dateStr1 = localDateTime.format(dtf1);
    System.out.println(dateStr1);

    //Convert string to date
    LocalDateTime localDateTime1 = localDateTime.parse(dateStr1,dtf1);
    System.out.println(localDateTime1);
}



--------------------------------------------------------------------------
2.时区的处理

//获取时区的时间
private static void test30(){
    LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Chisinau"));
    System.out.println(localDateTime);
}
private static void test29(){
    //time zone
    String[] zoneIds = TimeZone.getAvailableIDs();
    for (int i = 0; i < zoneIds.length; i++) {
        System.out.println(zoneIds[i]);
    }
}
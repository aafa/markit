import Helpers._

trait TestCsvData { this: ProcessData =>

  override def csvLines(s:String): Result[String] =
    fs2.Stream.emits {
      """Date,Open,High,Low,Close,Volume
        |3-Aug-17,930.34,932.24,922.24,923.65,1202512
        |2-Aug-17,928.61,932.60,916.68,930.39,1824448
        |1-Aug-17,932.38,937.45,929.26,930.83,1277734
        |31-Jul-17,941.89,943.59,926.04,930.50,1970095
        |28-Jul-17,929.40,943.83,927.50,941.53,1846351
        |27-Jul-17,951.78,951.78,920.00,934.09,3212996
        |26-Jul-17,954.68,955.00,942.28,947.80,2088256
        |25-Jul-17,953.81,959.70,945.40,950.70,4660979
        |24-Jul-17,972.22,986.20,970.77,980.34,3248347
        |21-Jul-17,962.25,973.23,960.15,972.92,1711000
        |20-Jul-17,975.00,975.90,961.51,968.15,1624463
        |19-Jul-17,967.84,973.04,964.03,970.89,1224540
        |18-Jul-17,953.00,968.04,950.60,965.40,1153964
        |17-Jul-17,957.00,960.74,949.24,953.42,1165537
        |14-Jul-17,952.00,956.91,948.00,955.99,1053774
        |13-Jul-17,946.29,954.45,943.01,947.16,1294687
        |12-Jul-17,938.68,946.30,934.47,943.83,1532144
        |11-Jul-17,929.54,931.43,922.00,930.09,1113235
        |10-Jul-17,921.77,930.38,919.59,928.80,1192825
        |7-Jul-17,908.85,921.54,908.85,918.59,1637785
        |6-Jul-17,904.12,914.94,899.70,906.69,1424503
        |5-Jul-17,901.76,914.51,898.50,911.71,1813884
        |3-Jul-17,912.18,913.94,894.79,898.70,1710373
        |30-Jun-17,926.05,926.05,908.31,908.73,2090226
        |29-Jun-17,929.92,931.26,910.62,917.79,3299176
        |28-Jun-17,929.00,942.75,916.00,940.49,2721406
        |27-Jun-17,942.46,948.29,926.85,927.33,2579930
        |26-Jun-17,969.90,973.31,950.79,952.27,1598355
        |23-Jun-17,956.83,966.00,954.20,965.59,1527856
        |22-Jun-17,958.70,960.72,954.55,957.09,941958
        |21-Jun-17,953.64,960.10,950.76,959.45,1202233
        |20-Jun-17,957.52,961.62,950.01,950.63,1125990
        |19-Jun-17,949.96,959.99,949.05,957.37,1533336
        |16-Jun-17,940.00,942.04,931.60,939.78,3094711
        |15-Jun-17,933.97,943.34,924.44,942.31,2133050
        |14-Jun-17,959.92,961.15,942.25,950.76,1489715
        |13-Jun-17,951.91,959.98,944.09,953.40,2013337
        |12-Jun-17,939.56,949.36,915.23,942.90,3763529
        |9-Jun-17,984.50,984.50,935.63,949.83,3309389
        |8-Jun-17,982.35,984.57,977.20,983.41,1481916
        |7-Jun-17,979.65,984.15,975.77,981.08,1453874
        |6-Jun-17,983.16,988.25,975.14,976.57,1814624
        |5-Jun-17,976.55,986.91,975.10,983.68,1252106
        |2-Jun-17,969.46,975.88,966.00,975.60,1750955
        |1-Jun-17,968.95,971.50,960.01,966.95,1410458
        |31-May-17,975.02,979.27,960.18,964.86,2448067
        |30-May-17,970.31,976.20,969.49,975.88,1466654
        |26-May-17,969.70,974.98,965.03,971.47,1252010
        |25-May-17,957.33,972.63,955.47,969.54,1660474
        |24-May-17,952.98,955.09,949.50,954.96,1034199
        |23-May-17,947.92,951.47,942.58,948.82,1270817
        |22-May-17,935.00,941.88,935.00,941.86,1120385
        |19-May-17,931.47,937.76,931.00,934.01,1393024
        |18-May-17,921.00,933.17,918.75,930.24,1596897
        |17-May-17,935.67,939.33,918.14,919.62,2362072
        |16-May-17,940.00,943.11,937.58,943.00,969479
        |15-May-17,932.95,938.25,929.34,937.08,1108496
        |12-May-17,931.53,933.44,927.85,932.22,1050601
        |11-May-17,925.32,932.53,923.03,930.60,835386
        |10-May-17,931.98,932.00,925.16,928.78,1173925
        |9-May-17,936.95,937.50,929.53,932.17,1581809
        |8-May-17,926.12,936.92,925.26,934.30,1329825
        |5-May-17,933.54,934.90,925.20,927.13,1911275
        |4-May-17,926.07,935.93,924.59,931.66,1422144
        |3-May-17,914.86,928.10,912.54,927.04,1499532
        |2-May-17,909.62,920.77,909.45,916.44,1587219
        |1-May-17,901.94,915.68,901.45,912.57,2115993
        |28-Apr-17,910.66,916.85,905.77,905.96,3276255
        |27-Apr-17,873.60,875.40,870.38,874.25,2026816
        |26-Apr-17,874.23,876.05,867.75,871.73,1237167
        |25-Apr-17,865.00,875.00,862.81,872.30,1671972
        |24-Apr-17,851.20,863.45,849.86,862.76,1372541
        |21-Apr-17,842.88,843.88,840.60,843.19,1323583
        |20-Apr-17,841.44,845.20,839.32,841.65,959031
        |19-Apr-17,839.79,842.22,836.29,838.21,954330
        |18-Apr-17,834.22,838.93,832.71,836.82,836722
        |17-Apr-17,825.01,837.75,824.47,837.17,895015
        |13-Apr-17,822.14,826.38,821.44,823.56,1122362
        |12-Apr-17,821.93,826.66,821.02,824.32,900480
        |11-Apr-17,824.71,827.43,817.02,823.35,1079732
        |10-Apr-17,825.39,829.35,823.77,824.73,978905
        |7-Apr-17,827.96,828.48,820.51,824.67,1057253
        |6-Apr-17,832.40,836.39,826.46,827.88,1254433
        |5-Apr-17,835.51,842.45,830.72,831.41,1555328
        |4-Apr-17,831.36,835.18,829.04,834.57,1045363
        |3-Apr-17,829.22,840.85,829.22,838.55,1671503
        |31-Mar-17,828.97,831.64,827.39,829.56,1401893
        |30-Mar-17,833.50,833.68,829.00,831.50,1055339
        |29-Mar-17,825.00,832.76,822.38,831.41,1786321
        |28-Mar-17,820.41,825.99,814.03,820.92,1620542
        |27-Mar-17,806.95,821.63,803.37,819.51,1894990
        |24-Mar-17,820.08,821.93,808.89,814.43,1981006
        |23-Mar-17,821.00,822.57,812.26,817.58,3487056
        |22-Mar-17,831.91,835.55,827.18,829.59,1401465
        |21-Mar-17,851.40,853.50,829.02,830.46,2463484
        |20-Mar-17,850.01,850.22,845.15,848.40,1231521
        |17-Mar-17,851.61,853.40,847.11,852.12,1716471
        |16-Mar-17,849.03,850.85,846.13,848.78,977560
        |15-Mar-17,847.59,848.63,840.77,847.20,1381474
        |14-Mar-17,843.64,847.24,840.80,845.62,780198
        |13-Mar-17,844.00,848.68,843.25,845.54,1223647
        |10-Mar-17,843.28,844.91,839.50,843.25,1704024
        |9-Mar-17,836.00,842.00,834.21,838.68,1261517
        |8-Mar-17,833.51,838.15,831.79,835.37,989773
        |7-Mar-17,827.40,833.41,826.52,831.91,1037630
        |6-Mar-17,826.95,828.88,822.40,827.78,1109037
        |3-Mar-17,830.56,831.36,825.75,829.08,896378
        |2-Mar-17,833.85,834.51,829.64,830.63,942476
        |1-Mar-17,828.85,836.26,827.26,835.24,1496540
        |28-Feb-17,825.61,828.54,820.20,823.21,2260769
        |27-Feb-17,824.55,830.50,824.00,829.28,1101466
        |24-Feb-17,827.73,829.00,824.20,828.64,1392202
        |23-Feb-17,830.12,832.46,822.88,831.33,1472771
        |22-Feb-17,828.66,833.25,828.64,830.76,987248
        |21-Feb-17,828.66,833.45,828.35,831.66,1262337
        |17-Feb-17,823.02,828.07,821.66,828.07,1611039
        |16-Feb-17,819.93,824.40,818.98,824.16,1287626
        |15-Feb-17,819.36,823.00,818.47,818.98,1313617
        |14-Feb-17,819.00,823.00,816.00,820.45,1054732
        |13-Feb-17,816.00,820.96,815.49,819.24,1213324
        |10-Feb-17,811.70,815.25,809.78,813.67,1134976
        |9-Feb-17,809.51,810.66,804.54,809.56,990391
        |8-Feb-17,807.00,811.84,803.19,808.38,1155990
        |7-Feb-17,803.99,810.50,801.78,806.97,1241221
        |6-Feb-17,799.70,801.67,795.25,801.34,1184483
        |3-Feb-17,802.99,806.00,800.37,801.49,1463448
        |2-Feb-17,793.80,802.70,792.00,798.53,1532138
        |1-Feb-17,799.68,801.19,791.19,795.70,2029744
        |31-Jan-17,796.86,801.25,790.52,796.79,2160556
        |30-Jan-17,814.66,815.84,799.80,802.32,3246573
        |27-Jan-17,834.71,841.95,820.44,823.31,2965771
        |26-Jan-17,837.81,838.00,827.01,832.15,2973891
        |25-Jan-17,829.62,835.77,825.06,835.67,1627304
        |24-Jan-17,822.30,825.90,817.82,823.87,1474010
        |23-Jan-17,807.25,820.87,803.74,819.31,1963628
        |20-Jan-17,806.91,806.91,801.69,805.02,1670045
        |19-Jan-17,805.12,809.48,801.80,802.18,919325
        |18-Jan-17,805.81,806.20,800.99,806.07,1294407
        |17-Jan-17,807.08,807.14,800.37,804.61,1362115
        |13-Jan-17,807.48,811.22,806.69,807.88,1099215
        |12-Jan-17,807.14,807.39,799.17,806.36,1353057
        |11-Jan-17,805.00,808.15,801.37,807.91,1065936
        |10-Jan-17,807.86,809.13,803.51,804.79,1176780
        |9-Jan-17,806.40,809.97,802.83,806.65,1274645
        |6-Jan-17,795.26,807.90,792.20,806.15,1640170
        |5-Jan-17,786.08,794.48,785.02,794.02,1335167
        |4-Jan-17,788.36,791.34,783.16,786.90,1072958
        |3-Jan-17,778.81,789.63,775.80,786.14,1657268
        |30-Dec-16,782.75,782.78,770.41,771.82,1769950
        |29-Dec-16,783.33,785.93,778.92,782.79,744272
        |28-Dec-16,793.70,794.23,783.20,785.05,1153824
        |27-Dec-16,790.68,797.86,787.66,791.55,789321
        |23-Dec-16,790.90,792.74,787.28,789.91,623944
        |22-Dec-16,792.36,793.32,788.58,791.26,972169
        |21-Dec-16,795.84,796.68,787.10,794.56,1211346
        |20-Dec-16,796.76,798.65,793.27,796.42,951014
        |19-Dec-16,790.22,797.66,786.27,794.20,1232087
        |16-Dec-16,800.40,800.86,790.29,790.80,2443796
        |15-Dec-16,797.34,803.00,792.92,797.85,1626499
        |14-Dec-16,797.40,804.00,794.01,797.07,1704150
        |13-Dec-16,793.90,804.38,793.34,796.10,2145209
        |12-Dec-16,785.04,791.25,784.36,789.27,2104117
        |9-Dec-16,780.00,789.43,779.02,789.29,1821914
        |8-Dec-16,772.48,778.18,767.23,776.42,1488059
        |7-Dec-16,761.00,771.36,755.80,771.19,1760966
        |6-Dec-16,764.73,768.83,757.34,759.11,1690689
        |5-Dec-16,757.71,763.90,752.90,762.52,1394223
        |2-Dec-16,744.59,754.00,743.10,750.50,1452484
        |1-Dec-16,757.44,759.85,737.02,747.92,3017947
        |30-Nov-16,770.07,772.99,754.83,758.04,2392890
        |29-Nov-16,771.53,778.50,768.24,770.84,1616618
        |28-Nov-16,760.00,779.53,759.80,768.24,2188151
        |25-Nov-16,764.26,765.00,760.52,761.68,587421
        |23-Nov-16,767.73,768.28,755.25,760.99,1478417
        |22-Nov-16,772.63,776.96,767.00,768.27,1593108
        |21-Nov-16,762.61,769.70,760.60,769.20,1330639
        |18-Nov-16,771.37,775.00,760.00,760.54,1547145
        |17-Nov-16,766.92,772.70,764.23,771.23,1286961
        |16-Nov-16,755.20,766.36,750.51,764.48,1472594
        |15-Nov-16,746.97,764.42,746.97,758.49,2384001
        |14-Nov-16,755.60,757.85,727.54,736.08,3654385
        |11-Nov-16,756.54,760.78,750.38,754.02,2431815
        |10-Nov-16,791.17,791.17,752.18,762.56,4745183
        |9-Nov-16,779.94,791.23,771.67,785.31,2607121
        |8-Nov-16,783.40,795.63,780.19,790.51,1366873
        |7-Nov-16,774.50,785.19,772.55,782.52,1585070
        |4-Nov-16,750.66,770.36,750.56,762.02,2134812
        |3-Nov-16,767.25,769.95,759.03,762.13,1943175
        |2-Nov-16,778.20,781.65,763.45,768.70,1918414
        |1-Nov-16,782.89,789.49,775.54,783.61,2406356
        |31-Oct-16,795.47,796.86,784.00,784.54,2427284
        |28-Oct-16,808.35,815.49,793.59,795.37,4269902
        |27-Oct-16,801.00,803.49,791.50,795.35,2749221
        |26-Oct-16,806.34,806.98,796.32,799.07,1647733
        |25-Oct-16,816.68,816.68,805.14,807.67,1576404
        |24-Oct-16,804.90,815.18,804.82,813.11,1697514
        |21-Oct-16,795.00,799.50,794.00,799.37,1266181
        |20-Oct-16,803.30,803.97,796.03,796.97,1757528
        |19-Oct-16,798.86,804.63,797.64,801.56,1766798
        |18-Oct-16,787.85,801.61,785.56,795.26,2056903
        |17-Oct-16,779.80,785.85,777.50,779.96,1092973
        |14-Oct-16,781.65,783.95,776.00,778.53,852487
        |13-Oct-16,781.22,781.22,773.00,778.19,1365277
        |12-Oct-16,783.76,788.13,782.06,786.14,937435
        |11-Oct-16,786.66,792.28,780.58,783.07,1372461
        |10-Oct-16,777.71,789.38,775.87,785.94,1174923
        |7-Oct-16,779.66,779.66,770.75,775.08,933158
        |6-Oct-16,779.00,780.48,775.54,776.86,1070692
        |5-Oct-16,779.31,782.07,775.65,776.47,1461151
        |4-Oct-16,776.03,778.71,772.89,776.43,1201350
        |3-Oct-16,774.25,776.06,769.50,772.56,1278821
        |30-Sep-16,776.33,780.94,774.09,777.29,1585333
        |29-Sep-16,781.44,785.80,774.23,775.01,1314746
        |28-Sep-16,777.85,781.81,774.97,781.56,1109834
        |27-Sep-16,775.50,785.99,774.31,783.01,1153247
        |26-Sep-16,782.74,782.74,773.07,774.21,1533206
        |23-Sep-16,786.59,788.93,784.15,786.90,1411937
        |22-Sep-16,780.00,789.85,778.44,787.21,1486223
        |21-Sep-16,772.66,777.16,768.30,776.22,1167810
        |20-Sep-16,769.00,773.33,768.53,771.41,978631
        |19-Sep-16,772.42,774.00,764.44,765.70,1172824
        |16-Sep-16,769.75,769.75,764.66,768.88,2049338
        |15-Sep-16,762.89,773.80,759.96,771.76,1346751
        |14-Sep-16,759.61,767.68,759.11,762.49,1094490
        |13-Sep-16,764.48,766.22,755.80,759.69,1395046
        |12-Sep-16,755.13,770.29,754.00,769.02,1310986
        |9-Sep-16,770.10,773.24,759.66,759.66,1885496
        |8-Sep-16,778.59,780.35,773.58,775.32,1270264
        |7-Sep-16,780.00,782.73,776.20,780.35,894021
        |6-Sep-16,773.45,782.00,771.00,780.08,1442822
        |2-Sep-16,773.01,773.92,768.41,771.46,1072658
        |1-Sep-16,769.25,771.02,764.30,768.78,925131
        |31-Aug-16,767.01,769.09,765.38,767.05,1248556
        |30-Aug-16,769.33,774.47,766.84,769.09,1130029
        |29-Aug-16,768.74,774.99,766.62,772.15,847565
        |26-Aug-16,769.00,776.08,765.85,769.54,1166681
        |25-Aug-16,767.00,771.89,763.18,769.41,926883
        |24-Aug-16,770.58,774.50,767.07,769.64,1071999
        |23-Aug-16,775.48,776.44,771.78,772.08,928232
        |22-Aug-16,773.27,774.54,770.05,772.15,951362
        |19-Aug-16,775.00,777.10,773.13,775.42,861546
        |18-Aug-16,780.01,782.86,777.00,777.50,719429
        |17-Aug-16,777.32,780.81,773.53,779.91,924226
        |16-Aug-16,780.30,780.98,773.44,777.14,1028047
        |15-Aug-16,783.75,787.49,780.11,782.44,938186
        |12-Aug-16,781.50,783.40,780.40,783.22,740498
        |11-Aug-16,785.00,789.75,782.97,784.85,975113
        |10-Aug-16,783.75,786.81,782.78,784.68,786363
        |9-Aug-16,781.10,788.94,780.57,784.26,1318894
        |8-Aug-16,782.00,782.63,778.09,781.76,1107857
        |5-Aug-16,773.78,783.04,772.34,782.22,1801205
      """.stripMargin.split("\n")
    }
}

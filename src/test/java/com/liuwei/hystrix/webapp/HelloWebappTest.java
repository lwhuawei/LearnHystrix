package com.liuwei.hystrix.webapp;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HelloWebappTest {
    @Test
    public void hello() throws Exception {

    }

    @Test
    public void hystrixSynHello() throws Exception {
        /*//String urlStr = "http://localhost:8080/LearnHystrix/Hello/HelloWeb/hystrixSynHello";
        String urlStr = "http://localhost:8080/LearnHystrix/Hello/HelloWeb/hystrixASynHello";
        URL url = new URL(urlStr);

        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File("Test.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(wwb == null) return;

        //两个参数:第一个是工作表的名称，第二个是工作表在工作薄中的位置
        WritableSheet ws = wwb.createSheet("sheet", 0);

        int i = 1;
        while (i++ <= 100) {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //Read first line
            String lineString = buffReader.readLine();

            String[] sArray = lineString.split(" ");
            //System.out.println(lineString + ":" + sArray[0] + " " + sArray[1] + " " + sArray[2] );
            try  {
                //column,row,cell
                ws.addCell(new Label(1, i, sArray[0]));
                ws.addCell(new Label(2, i, sArray[1]));
                ws.addCell(new Label(3, i, sArray[2]));
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        /*conn.connect();

        StringBuffer sb = new StringBuffer();
        String lineString = "";
        BufferedReader URLinput = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((lineString = URLinput.readLine()) != null) {
            sb.append(lineString);
        }
        String lineString = URLinput.readLine();
        conn.disconnect();*/
    }

    @Test
    public void hystrixASynHello() throws Exception {

    }

}

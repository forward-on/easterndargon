package com.ly.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : ly.
 * @Date : 2018/6/4.
 */
public class ExcelPOI2Util {

    private static int START_ROW = 2;//第3行开始读取
    private static final int LIMIT_MAX_COL_NUM = 10;//限定读取最大有效列数是10列
    private static final int MAX_COL_NUM_FROM_ROW_NUM = 1;//从第几行读取最大列数，默认第二行
    //默认单元格内容为数字时格式
    private static DecimalFormat df = new DecimalFormat("0");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");
    // 格式化数字
    private static DecimalFormat nf = new DecimalFormat("0.00");

    /**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list, String []keys, String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//      Font f3=wb.createFont();
//      f3.setFontHeightInPoints((short) 10);
//      f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    public static ArrayList<ArrayList<String>> readExcel(String originalName, InputStream is){
        if(originalName.endsWith("xlsx")){
            //处理ecxel2007
            return readExcel2007(is);
        }else{
            //处理ecxel2003
            return readExcel2003(is);
        }
    }

    /*
     * @return 将返回结果存储在ArrayList内，存储结构与二位数组类似
     * lists.get(0).get(0)表示过去Excel中0行0列单元格
     */
    public static ArrayList<ArrayList<String>> readExcel2003(InputStream is){
        try{
            ArrayList<ArrayList<String>> rowList = new ArrayList<>();
            ArrayList<String> colList;
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;
            String value;
            if (sheet.getPhysicalNumberOfRows() < 2) {
                return null;
            }
            int maxColNum = sheet.getRow(MAX_COL_NUM_FROM_ROW_NUM).getLastCellNum();
            if (maxColNum > LIMIT_MAX_COL_NUM) {
                maxColNum = LIMIT_MAX_COL_NUM;
            }
            for(int i = START_ROW , rowCount = START_ROW; rowCount <= sheet.getLastRowNum(); i++ ){
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                rowCount++;
                if(row == null){
                    //当读取行为空时
                    if(i < sheet.getLastRowNum()){//判断是否是最后一行
                        for (int j = 0; j < maxColNum; j++) {
                            colList.add("");
                        }
                        rowList.add(colList);
                    }
                    continue;
                }
                for( int j = 0 ; j < maxColNum ;j++){
                    cell = row.getCell(j);
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        //当该单元格为空
                        if(j < maxColNum){//判断是否是该行中最后一个单元格
                            colList.add("");
                        }
                        continue;
                    }
                    switch(cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                                value = df.format(cell.getNumericCellValue());
                            } else if ("General".equals(cell.getCellStyle()
                                    .getDataFormatString())) {
                                value = nf.format(cell.getNumericCellValue());
                            } else {
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                        .getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            boolean bool = cell.getBooleanCellValue();
                            if (bool) {
                                value = "true";
                            } else {
                                value = "false";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }// end switch
                    colList.add(value);
                }//end for j
                rowList.add(colList);
            }//end for i
            return rowList;
        }catch(Exception e){
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<ArrayList<String>> readExcel2007(InputStream is){
        try{
            ArrayList<ArrayList<String>> rowList = new ArrayList<>();
            ArrayList<String> colList;
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            String value;
            if (sheet.getPhysicalNumberOfRows() < 2) {
                return null;
            }
            int maxColNum = sheet.getRow(MAX_COL_NUM_FROM_ROW_NUM).getLastCellNum();
            if (maxColNum > LIMIT_MAX_COL_NUM) {
                maxColNum = LIMIT_MAX_COL_NUM;
            }
            for(int i = START_ROW , rowCount = START_ROW; rowCount <= sheet.getLastRowNum(); i++ ){
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                rowCount++;
                if(row == null){
                    //当读取行为空时
                    if(i < sheet.getLastRowNum()){//判断是否是最后一行
                        for (int j = 0; j < maxColNum; j++) {
                            colList.add("");
                        }
                        rowList.add(colList);
                    }
                    continue;
                }
                for( int j = 0 ; j < maxColNum ;j++){
                    cell = row.getCell(j);
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        //当该单元格为空
                        if(j < maxColNum){//判断是否是该行中最后一个单元格
                            colList.add("");
                        }
                        continue;
                    }
                    switch(cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                                value = df.format(cell.getNumericCellValue());
                            } else if ("General".equals(cell.getCellStyle()
                                    .getDataFormatString())) {
                                value = nf.format(cell.getNumericCellValue());
                            } else {
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell
                                        .getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            boolean bool = cell.getBooleanCellValue();
                            if (bool) {
                                value = "true";
                            } else {
                                value = "false";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }// end switch
                    colList.add(value);
                }//end for j
                rowList.add(colList);
            }//end for i
            return rowList;
        }catch(Exception e){
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        String oriName = "";
        InputStream is = new FileInputStream("E:\\workspaces\\excel\\批量导入模板.xls");

        readExcel(oriName, is);

        /*String downloadFileName = "aaaa.xls";
        String batchErrorPath = "E:\\workspaces\\excel\\";
        Map<Integer, String> errorMap = new HashMap<>();
        ArrayList<ArrayList<String>> readResult = new ArrayList<>();
        errorMap.put(4, "ddd");
        errorMap.put(6, "dflaie");
        errorMap.put(7, "dfla的房间诶");
        errorMap.put(8, "dfla的8");
        for (int i = 0; i < 6; i++) {
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                list.add("错误" + j);
            }
            readResult.add(list);
        }
        createErrorWorkBook("幼儿园", downloadFileName, errorMap, readResult, batchErrorPath);*/

    }




    /**
     * 用于生成 导入幼儿和考勤卡绑定失败后创建excel
     *@param downloadFileName
     * @param errorMap
     * @param readResult
     * @return
     */
    public static Workbook createErrorWorkBook(String firstRowTxt, String downloadFileName, Map<Integer, String> errorMap, ArrayList<ArrayList<String>> readResult, String batchErrorPath) {

//        1.合并单元格，属于工作表，独立创建，应用于工作表
//        2.样式，属于工作表，由工作簿创建，应用于单元格
//        3.字体，属于工作表，由工作簿创建，应用于样式
//        4.设置背景颜色，一定要先设置颜色的填充模式

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("孩子信息导入出错列表");
        //在sheet里第一行增加合并单元格
        CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 6);
        sheet.addMergedRegion(cra);

        String[] columnNames = new String[]{"学生姓名", "手机号码", "家长关系", "设备卡号", "年级", "班级", "错误原因"};

        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<columnNames.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建3种字体
        Font f1 = wb.createFont();
        Font f2 = wb.createFont();
        Font f3 = wb.createFont();

        // 创建第一种字体样式（用于第一行）
        f1.setFontHeightInPoints((short) 10);
        f1.setColor(HSSFColor.BLACK.index);
        f1.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第一种字体样式（用于列名）
        f2.setFontHeightInPoints((short) 15);
        f2.setColor(HSSFColor.RED.index);
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f3.setFontHeightInPoints((short) 12);
        f3.setColor(IndexedColors.BLACK.getIndex());

//      Font f3=wb.createFont();
//      f3.setFontHeightInPoints((short) 10);
//      f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f1);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cs.setFillForegroundColor(HSSFColor.YELLOW.index);// 设置背景色
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f3);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        cs2.setVerticalAlignment(CellStyle.ALIGN_CENTER);


//        String[] columnNames = new String[]{"学生姓名", "家长姓名", "手机号码", "家长关系", "设备卡号", "创建类型", "年级", "班级", "产品有效期远程视频家长端", "错误原因"};
        // 创建第一行
        Row row1 = sheet.createRow(0);
        Cell r1c1 = row1.createCell(0);
        r1c1.setCellValue(firstRowTxt);
        r1c1.setCellStyle(cs);
        //创建第二行
        Row row2 = sheet.createRow(1);
        //设置列名
        int colNum = columnNames.length;
        for(int i=0;i<colNum;i++){
            Cell cell = row2.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs2);
        }

        //设置每行每列的值
        int actRowNum = 2;
        for (int i = 0; i < readResult.size(); i++) {
            if (!errorMap.containsKey(i)) {
                continue;
            }
            Row rowTmp = sheet.createRow(actRowNum);
            actRowNum++;
            ArrayList<String> rowTxt = readResult.get(i);
            // 在row行上创建一个方格
            for(short j=0;j<colNum;j++){
                Cell cell = rowTmp.createCell(j);
                if (j == colNum-1) {
                    cell.setCellValue(errorMap.get(i));
                } else if (j == 1 || j == 3) {
                    cell.setCellValue(dealExcelPoint(rowTxt.get(j)));
                } else {
                    cell.setCellValue(rowTxt.get(j));
                }
                cell.setCellStyle(cs2);
            }
        }

        //输出
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File(batchErrorPath + downloadFileName));
            wb.write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return wb;
    }

    //去掉小数点 3.00 --> 3
    private static String dealExcelPoint (String field) {
        int cardIndex = field.indexOf(".");
        if (cardIndex > 0) {
            field = field.substring(0, cardIndex);
        }
        return field;
    }

    //用户下载失败的excel
    public static void downloadFailFile (String path, OutputStream os) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        InputStream inputStream = null;
        DataInputStream dis = null;
        try {
            inputStream = new FileInputStream(file);
            dis = new DataInputStream(inputStream);
            byte[] b = new byte[1024];
            int length;
            while ((length = dis.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
//            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}

package com.ly.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
public class ExcelPOIUtil {

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

    /**
     * 读取Excel数据内容
     * @param is
     * @return Map 包含单元格数据内容的Map对象
     */
    public static List<List<String>> readExcelContent(InputStream is) {
        List<List<String>> content = new ArrayList<>();
        POIFSFileSystem fs;
        HSSFWorkbook wb;
        HSSFSheet sheet;
        HSSFRow row;
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(1);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第三行开始,第一行为表头的标题
            for (int i = 2; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                List<String> list = new ArrayList<>();
                while (j < colNum) {
                    // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                    // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                    // str += getStringCellValue(row.getCell((short) j)).trim() +
                    // "-";
                    str = getCellFormatValue(row.getCell((short) j)).trim();
                    list.add(str);
                    j++;
                }
                content.add(list);
                str = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Date格式

                        //方法1：这样子的date格式是带时分秒的：2017-10-12 00:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2017-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRING
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }


    /*********************************************************************************************/


    private static final int START_ROW = 2;//第三行开始读取
    //默认单元格内容为数字时格式
    private static DecimalFormat df = new DecimalFormat("0");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");
    // 格式化数字
    private static DecimalFormat nf = new DecimalFormat("0.00");

    public static ArrayList<ArrayList<Object>> readExcel(String originalName, InputStream is){
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
    public static ArrayList<ArrayList<Object>> readExcel2003(InputStream is){
        try{
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
            ArrayList<Object> colList;
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;
            Object value;
            for(int i = START_ROW , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                rowCount++;
                if(row == null){
                    //当读取行为空时
                    if(i < sheet.getPhysicalNumberOfRows()){//判断是否是最后一行
                        rowList.add(colList);
                    }
                    continue;
                }
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){
                    cell = row.getCell(j);
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        //当该单元格为空
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格
                            colList.add("");
                        }
                        continue;
                    }
                    switch(cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING:
                            System.out.println(i + "行" + j + " 列 is String type");
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
                            System.out.println(i + "行" + j
                                    + " 列 is Number type ; DateFormt:"
                                    + value.toString());
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(i + "行" + j + " 列 is Boolean type");
                            value = Boolean.valueOf(cell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            System.out.println(i + "行" + j + " 列 is Blank type");
                            value = "";
                            break;
                        default:
                            System.out.println(i + "行" + j + " 列 is default type");
                            value = cell.toString();
                    }// end switch
                    colList.add(value);
                }//end for j
                rowList.add(colList);
            }//end for i

            return rowList;
        }catch(Exception e){
            return null;
        }
    }

    public static ArrayList<ArrayList<Object>> readExcel2007(InputStream is){
        try{
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
            ArrayList<Object> colList;
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Object value;
            for(int i = START_ROW , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                rowCount++;
                if(row == null){
                    //当读取行为空时
                    if(i < sheet.getPhysicalNumberOfRows()){//判断是否是最后一行
                        rowList.add(colList);
                    }
                    continue;
                }
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){
                    cell = row.getCell(j);
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                        //当该单元格为空
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格
                            colList.add("");
                        }
                        continue;
                    }
                    switch(cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING:
                            System.out.println(i + "行" + j + " 列 is String type");
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
                            System.out.println(i + "行" + j
                                    + " 列 is Number type ; DateFormt:"
                                    + value.toString());
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(i + "行" + j + " 列 is Boolean type");
                            value = Boolean.valueOf(cell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            System.out.println(i + "行" + j + " 列 is Blank type");
                            value = "";
                            break;
                        default:
                            System.out.println(i + "行" + j + " 列 is default type");
                            value = cell.toString();
                    }// end switch
                    colList.add(value);
                }//end for j
                rowList.add(colList);
            }//end for i

            return rowList;
        }catch(Exception e){
            System.out.println("exception");
            return null;
        }
    }


    /*****************************************************************************/

    public static void writeExcel(ArrayList<ArrayList<Object>> result,String path){
        if(result == null){
            return;
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        for(int i = 0 ;i < result.size() ; i++){
            HSSFRow row = sheet.createRow(i);
            if(result.get(i) != null){
                for(int j = 0; j < result.get(i).size() ; j ++){
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(result.get(i).get(j).toString());
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try
        {
            wb.write(os);
        } catch (IOException e){
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        File file = new File(path);//Excel文件生成后存储的位置。
        OutputStream fos  = null;
        try
        {
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

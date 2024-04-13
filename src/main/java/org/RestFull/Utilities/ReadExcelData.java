package org.RestFull.Utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class ReadExcelData {

    private static Workbook book;
    private static Sheet sheet;
    private static final String FILE_NAME = "src/test/resources/Excel data files/Book1.xlsx";

    public static Object[][] getData(String sheetName) {

        System.out.println(FILE_NAME);
        try {
            FileInputStream file = new FileInputStream(FILE_NAME);
            sheet = WorkbookFactory.create(file).getSheet(sheetName);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
            }
        }
        return data;
    }
//    public static Object[][] getData1() {
//        return new Object[][] {
//                {"Harshal", "Parate", 1000, true, "2024-01-01", "2024-01-02", "Chicken"},
//                {"Jaiveek", "Parate", 1000, true, "2024-01-01", "2024-01-02", "Samosa"},
//                {"Devendra", "Parate", 1000, true, "2024-01-01", "2024-01-02", "Biryani"},
//                {"Jyoti", "Parate", 1000, true, "2024-01-01", "2024-01-02", "Chicken"},
//                {"Htanshu", "Bagde", 1000, true, "2024-01-01", "2024-01-02", "Eggs"}
//        };
//    }



//    @DataProvider
//    public Object[][] getDataA() {
//        return getData1();
//    }


//    @DataProvider
//    public Object[][] getDataB() {
//        return getData("Sheet1");
//    }



//    @Test(dataProvider = "getDataB")//, dataProviderClass = ReadExcelFile.class)
//    public void testLoginData(String firstName, String LastName, int paid, boolean depositePaid, String checkIn, String checkOut, String additional ) {
//        System.out.println(firstName+" "+LastName+" "+paid+" "+depositePaid+" "+checkIn+" "+checkOut+" "+additional);
//    }



}

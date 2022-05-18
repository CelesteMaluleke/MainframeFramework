package Checking_Customer_Profile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelFunctions {

    public static FileInputStream input_document;
    public static FileOutputStream output_document;
    private Cell cell = null;
    public static int ScenarioCount =1;
    public ArrayList columnsNames = new ArrayList();
    public static XSSFWorkbook wb;
    private XSSFSheet sheet;
    public static int totalColumns =0;


    public ExcelFunctions() {
        try {

            wb = new XSSFWorkbook(input_document);
            input_document.close();
            sheet = wb.getSheetAt(0);
            while (true){
                cell = sheet.getRow(0).getCell(totalColumns);
                if (cell == null) {
                    break;
                }
                String names = cell.getStringCellValue();
                columnsNames.add(names.trim());
                totalColumns++;

            }
            while (true){

                Row row = sheet.getRow(ScenarioCount);
                if (row == null) {
                    break;
                }
                ScenarioCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String ReadCell(int iScenario,int Column) throws IOException {

        cell = sheet.getRow(iScenario).getCell(Column);
        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(cell);
        return value;
    }

    public void WriteToCell(String runStatus, String clientCode, String aulRegister,String linkedAccounts,String IdNumber,String results,String overDraft, int iScenario,int ColumnRunStatus, int ColumnClientCode, int ColumnAulRegister, int ColumnLinkedAccounts, int ColumnResults, int ColumnOverDraft, int ColumnIdNumber) throws IOException {

        Row row = sheet.getRow(iScenario);
        Cell cell = row.getCell(ColumnRunStatus);
        if (cell == null) {
            cell = row.createCell(ColumnRunStatus);
        }
        cell.setCellValue(runStatus);

        Cell cell1 = row.getCell(ColumnClientCode);
        if(cell1 == null)
        {
            cell1 = row.createCell(ColumnClientCode);
        }
        cell1.setCellValue(clientCode);

        Cell cell2 = row.getCell(ColumnAulRegister);
        if(cell2 == null)
        {
            cell2 = row.createCell(ColumnAulRegister);
        }
        cell2.setCellValue(aulRegister);

        Cell cell3 = row.getCell(ColumnLinkedAccounts);
        if(cell3 == null)
        {
            cell3 = row.createCell(ColumnLinkedAccounts);
        }
        cell3.setCellValue(linkedAccounts);

        Cell cell4 = row.getCell(ColumnResults);
        if(cell4 == null)
        {
            cell4 = row.createCell(ColumnResults);
        }
        cell4.setCellValue(results);

        Cell cell5 = row.getCell(ColumnOverDraft);
        if(cell5 == null)
        {
            cell5 = row.createCell(ColumnOverDraft);
        }

        Cell cell6 = row.getCell(ColumnIdNumber);
        if(cell6 == null)
        {
            cell6 = row.createCell(ColumnIdNumber);
        }
        cell5.setCellValue(IdNumber);
        wb.write(output_document);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerOrder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author OLEG
 */
@Stateless
public class ReportManager {
    
    @EJB
    CustomerOrderFacade customerOrderFacade;

//    public void generateExcel() {
    public void generateExcel(OutputStream outputStream) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Order Data");

        Integer i = 1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put((i++).toString(), new Object[]{"N", "NAME", "LAST NAME", "E-MAIL", "AMOUNT", "DATE"});
        
        List<CustomerOrder> customerOrders = customerOrderFacade.findAll();

        for(CustomerOrder customerOrder : customerOrders) {
            data.put(i.toString(), new Object[] {
                i++, 
                customerOrder.getFirstName(),
                customerOrder.getLastName(),
                customerOrder.getEmail(),
                customerOrder.getAmount(),
                customerOrder.getDateCreated()
            });
        }
        
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            XSSFRow row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

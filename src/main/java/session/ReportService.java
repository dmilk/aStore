package session;

import entity.Order;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author OLEG
 */
@Stateless
public class ReportService {

    @EJB
    OrderFacade customerOrderFacade;

    public void generateExcel(OutputStream outputStream) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Order Data");

        Integer i = 0;
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
        data.put(i++, new Object[]{"N", "NAME", "LAST NAME", "E-MAIL", "AMOUNT", "DATE"});

        List<Order> customerOrders = customerOrderFacade.findAll();

        for (Order customerOrder : customerOrders) {
            data.put(i++, new Object[]{
                i - 1,
                customerOrder.getFirstName(),
                customerOrder.getLastName(),
                customerOrder.getEmail(),
                customerOrder.getAmount(),
                customerOrder.getDateCreated()
            });
        }

        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            HSSFRow row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Date) {
                    HSSFCellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("d-mmm-yy"));
                    cell.setCellValue((Date) obj);
                    cell.setCellStyle(cellStyle);
                } else if (obj instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) obj;
                    cell.setCellValue(bigDecimal.doubleValue());
                }
            }
        }
        try {
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
        }
    }

}

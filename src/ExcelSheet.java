import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelSheet {
	public static void main(String[] args) throws IOException, OpenXML4JException {
		String xlsname = "D:\\我的文档\\WeChat Files\\wxid_es1tm2bsdmh222\\FileStorage\\File\\2019-04\\机顶盒数据.xlsx";
		InputStream in = new FileInputStream(new File(xlsname));
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			@SuppressWarnings("resource")
			XSSFWorkbook workbook2 = new XSSFWorkbook(OPCPackage.open(in));	
			try {
				System.out.println(xlsname);
				XSSFSheet sheet = workbook2.getSheetAt(0);// 获取第一页
				int Rows = sheet.getPhysicalNumberOfRows();// 获取实际行数

				for (int i = 0; i < Rows; i++) { // 取得每一行
					
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {// 取得每一行中的每个单元格
						XSSFCell cell = row.getCell(j);
						if (cell == null) {
							System.out.println(123);
							continue;
						}
						if(j==0){
							System.out.println(cell.toString());
						}
						else if(j==1){
							System.out.println(cell.toString());
						}
					}			
				}
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}else{
			System.out.println("fail");
		}
	}
}

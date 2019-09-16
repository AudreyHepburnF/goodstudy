package com.personalstudy.goodstudy.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * PDF 生成demo
 */
@Controller
@RequestMapping("demo")
public class GenerPDF{

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/testPDF")
    public void testPDF(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            PrintPDFs(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PrintPDFs(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String fileNames = request.getParameter("fileNames");
        if (null == fileNames) {
            response.getWriter().write("<script>alert('无法找到文件');</script>");
            return;
        }
        List<InputStream> streamOfPDFFiles = new ArrayList<InputStream>();
        JSONArray jsonArr = new JSONArray(fileNames);

        if (jsonArr.length() < 1) {
            response.getWriter().write("<script>alert('无法找到文件');</script>");
            return;
        }
        String notExistFileNames = "";
        for (int i = 0; i < jsonArr.length(); ++i) {
            File file = new File(jsonArr.getString(i));
            if (!file.exists()) {
                notExistFileNames += jsonArr.getString(i);
            }
        }
        if (notExistFileNames.length() > 1) {
            notExistFileNames = notExistFileNames.substring(0,
                    notExistFileNames.length() - 1);
            response.getWriter().write(
                    "<script>alert('无法找到文件:" + notExistFileNames
                            + "');</script>");
            return;
        }
        for (int i = 0; i < jsonArr.length(); ++i) {
            InputStream in = new FileInputStream(jsonArr.getString(i));
            streamOfPDFFiles.add(in);
        }

        new GenerPDF().concatPDFs(streamOfPDFFiles, response
                .getOutputStream(), false);
    }

    public void concatPDFs(List<InputStream> streamOfPDFFiles,
                           OutputStream outputStream, boolean paginate) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);

                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
                                        + currentPageNumber + " of " + totalPages, 520,
                                5, 0);
                        cb.endText();
                    }
                    /*
                     * pdfs.size() != 1 检测当前PDF总数大于1时才执行加页操作
                     */
                    /*
                     * 检测到当前文档总页数为奇数页，则在最后一页的前一页加入空白页 使该文档成为偶数页文档，在打印时实现文档的分离
                     */
                    /* if ((pageOfCurrentReaderPDF + 1) == pdfReader */
                    /*
                     * 检测到当前文档总页数为奇数页，则在最后一页加入空白页 使该文档成为偶数页文档，在打印时实现文档的分离
                     */
                    if (pdfs.size() > 1 && pageOfCurrentReaderPDF == pdfReader.getNumberOfPages()
                            && pdfReader.getNumberOfPages() % 2 == 1) {
                        document.newPage();
                        document.add(new Paragraph("     "));
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

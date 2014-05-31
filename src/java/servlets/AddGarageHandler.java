package servlets;

import DAOS.GarageImp;
import errors.ErrorMessage;
import geolocator.AddressConverter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import pojo.Address;
import pojo.Garage;
import pojo.Map;

public class AddGarageHandler extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private File file;
    private String jspFilePath;
    private List<String> parameterNames ;
    private List<String> parameterValues ;
    private String theNameOfTheFile;
    private String fileExtention;
    String MapUrl = "";
    double lon, lat;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, Exception {
        
        parameterNames = new ArrayList();
        parameterValues = new ArrayList();
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String jspFilePath = getServletContext().getRealPath(request.getRequestURI()).replace('\\', '/');

        jspFilePath = jspFilePath.subSequence(0, jspFilePath.indexOf("/build")) + "/web/images/";

        out.println(jspFilePath);

        isMultipart = ServletFileUpload.isMultipartContent(request);

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.

        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!fi.isFormField()) {
                // Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String fileName = fi.getName();
                String contentType = fi.getContentType();
                //  boolean isInMemory = fi.isInMemory();
                long sizeInBytes = fi.getSize();
                // Write the file
                if (fileName.lastIndexOf("\\") >= 0) {
                    fileExtention = fileName.substring(fileName.lastIndexOf("\\", fileName.length()));
                    file = new File(jspFilePath + fileName.substring(fileName.lastIndexOf("\\")));

                } else {

                    file = new File(jspFilePath
                            + fileName.substring(fileName.lastIndexOf("\\") + 1));
                }
                fi.write(file);
                out.println("Uploaded Filename: " + theNameOfTheFile + "<br>");
            } else {
                out.print(fi.getFieldName() + " " + fi.getString());
                parameterNames.add(fi.getFieldName());
                parameterValues.add(fi.getString().trim());
            }
        }
        int result = save();    
        switch (result) {
            case -2:
                request.setAttribute("error", new ErrorMessage("This garage title is already added"));
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
            case -1:
                request.setAttribute("error", new ErrorMessage("Looks Like some error happend please contact adminstrator"));
                try {
                    file.delete();
                } catch (Exception ex) {
                    
                }
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
            case 0:
                file.renameTo(new File(jspFilePath + String.format("%s,%s.%s", lat, lon, FilenameUtils.getExtension(file.getName()))));
                request.setAttribute("error", new ErrorMessage("Garage added"));
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int save() {

        Garage g = new Garage();
        String garageName = parameterValues.get(0);
        int hourRateInRushHours = Integer.parseInt(parameterValues.get(1));
        double ratio = Double.parseDouble(parameterValues.get(2));
        int width = Integer.parseInt(parameterValues.get(3));
        int heigth = Integer.parseInt(parameterValues.get(4));
        String unit = parameterValues.get(5);
        lat = Double.parseDouble(parameterValues.get(6));
        lon = Double.parseDouble(parameterValues.get(7));
        MapUrl = String.format("%s,%s.%s", lat, lon, FilenameUtils.getExtension(file.getName()));
        Address address = new AddressConverter().getAddress(lat, lon);
        g.setHourRateInRush(hourRateInRushHours);
        g.setLat(lat);
        g.setLon(lon);
        g.setTitle(garageName);
        Map map = new Map();
        map.setHeight(heigth);
        map.setWidth(width);
        map.setUnit(unit);
        map.setRatio(ratio);
        map.setMapUrl(MapUrl);

        return GarageImp.getInstance().addGarage(map, g, address);

    }
}

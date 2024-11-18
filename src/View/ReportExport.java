package View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportExport {
    /**
     * Exports the given orders to an HTML file.
     * @param orders The list of orders to be exported.
     * @param filePath The file path for the HTML file to be saved.
     */
    public void exportToHTML(List<Object[]> orders, String filePath, String fromDate, String toDate) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head><title>Order Report: ")
                .append(fromDate)
                .append(" to ")
                .append(toDate)
                .append("</title></head><body>");
        htmlContent.append("<h1>Order Report ")
                .append(fromDate)
                .append(" to ")
                .append(toDate)
                .append("</h1>");
        htmlContent.append("<table border='1'>");
        htmlContent.append("<tr>")
                .append("<th>Order ID</th>")
                .append("<th>Date</th>")
                .append("<th>Guest Name</th>")
                .append("<th>Guest ID</th>")
                .append("<th>Bartender Name</th>")
                .append("<th>Drink Name</th>")
                .append("<th>Quantity</th>")
                .append("</tr>");

        // Populate table rows with order data
        for (Object[] order : orders) {
            htmlContent.append("<tr>");
            htmlContent.append("<td>").append(order[0] != null ? order[0].toString() : "").append("</td>"); // Order ID
            htmlContent.append("<td>").append(order[1] != null ? order[1].toString() : "").append("</td>"); // Order Date
            htmlContent.append("<td>").append(order[2] != null ? order[2].toString() : "").append("</td>"); // Guest Name
            htmlContent.append("<td>").append(order[3] != null ? order[3].toString() : "").append("</td>"); // Guest ID
            htmlContent.append("<td>").append(order[4] != null ? order[4].toString() : "").append("</td>"); // Bartender Name
            htmlContent.append("<td>").append(order[5] != null ? order[5].toString() : "").append("</td>"); // Drink Name
            htmlContent.append("<td>").append(order[6] != null ? order[6].toString() : "").append("</td>"); // Quantity
            htmlContent.append("</tr>");
        }

        htmlContent.append("</table></body></html>");

        // Write HTML content to file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent.toString());
            System.out.println("HTML report exported successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error exporting HTML report: " + e.getMessage());
        }
    }
}

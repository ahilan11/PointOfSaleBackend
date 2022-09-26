package com.za.carolsstore.identity.service;


import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.identity.dao.IdentityDao;
import com.za.carolsstore.identity.dao.iIdentityDao;
import com.za.carolsstore.message.model.Email;
import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.store.model.Store;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author patri
 */
public class IdentityGenerator implements iIdentityGenerator{
    private final iIdentityDao dao;

    public IdentityGenerator() {
        this.dao = new IdentityDao();
    }   

    @Override
    public String generateID(Category category) {
        StringBuilder sb = new StringBuilder();
        sb.append("CAT");
        char[] lastID = dao.getLastIDCategory();
        int numLength = String.valueOf(lastID, 3, 2).length();
        for (int i = 0; i < 2-numLength; i++) {
            sb.append("0");
        }
        sb.append(Integer.valueOf(String.valueOf(lastID, 3, 2))+1);        
        return sb.toString();
    }

    @Override
    public String generateID(Email email) {
        StringBuilder sb = new StringBuilder();
        sb.append("EML");
        char[] lastID = dao.getLastIDEmail();
        String thisDateTime = makeTemporalString(email.getDateSent(), email.getTimeSent());
        sb.append(thisDateTime);
        String lastDateTime = String.valueOf(lastID, 3, 12);
        if (thisDateTime.equals(lastDateTime)) {
            int numLength = String.valueOf(lastID, 15, 5).length();
            for (int i = 0; i < 5-numLength; i++) {
                sb.append("0");
            }
            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
        } else {
            sb.append("00000");
        }
        return sb.toString();
    }

    @Override
    public String generateID(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append("EMP");
        char[] lastID = dao.getLastIDEmployee();
        int numLength = String.valueOf(lastID, 3, 4).length();
        for (int i = 0; i < 4-numLength; i++) {
            sb.append("0");
        }
        sb.append(Integer.valueOf(String.valueOf(lastID, 3, 4))+1);
        switch (employee.getRole()){
            case 1:
                sb.append("TLR");
                break;
            case 2:
                sb.append("MGR");
                break;
        }
        return sb.toString();
    }

    @Override
    public String generateID(InterBranchTransfer ibt) {
        StringBuilder sb = new StringBuilder();
        sb.append("IBT");
        char[] lastID = dao.getLastIDIbt();
        String thisDateTime = makeTemporalString(ibt.getDateRequested(), ibt.getTimeRequested());
        sb.append(thisDateTime);
        String lastDateTime = String.valueOf(lastID, 3, 12);
        if (thisDateTime.equals(lastDateTime)) {
            int numLength = String.valueOf(lastID, 15, 5).length();
            for (int i = 0; i < 5-numLength; i++) {
                sb.append("0");
            }
            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
        } else {
            sb.append("00000");
        }
        return sb.toString();
    }

    @Override
    public String generateID(KeepAside keepAside) {
        StringBuilder sb = new StringBuilder();
        sb.append("KPA");
        char[] lastID = dao.getLastIDKeepAside();
        String thisDateTime = makeTemporalString(keepAside.getDateCreated(), keepAside.getTimeCreated());
        sb.append(thisDateTime);
        String lastDateTime = String.valueOf(lastID, 3, 12);
        if (thisDateTime.equals(lastDateTime)) {
            int numLength = String.valueOf(lastID, 15, 5).length();
            for (int i = 0; i < 5-numLength; i++) {
                sb.append("0");
            }
            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
        } else {
            sb.append("00000");
        }        
        return sb.toString();
    }

    @Override
    public String generateID(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRD");
        char[] lastID = dao.getLastIDProduct();
        int numLength = String.valueOf(lastID, 3, 4).length();
        for (int i = 0; i < 4-numLength; i++) {
            sb.append("0");
        }
        sb.append(Integer.valueOf(String.valueOf(lastID, 3, 4))+1);
        int sizeLength = product.getSize().length();
        for (int i = 0; i < 3-sizeLength; i++) {
            sb.append("7");
        }
        sb.append(product.getSize());
        return sb.toString();
    }

    @Override
    public String generateID(Category category, Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append("PCI");
        sb.append(category.getCategoryID().toCharArray(),3,2);
        sb.append(product.getId().toCharArray(),3,7);
        return sb.toString();
    }

    @Override
    public String generateID(Sale sale) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SLE");
//        char[] lastID = dao.getLastIDSale();
////        String thisDateTime = makeTemporalString(sale.getDateSold(), sale.getTimeSold());
////        sb.append(thisDateTime);
////        String lastDateTime = String.valueOf(lastID, 3, 12);
////        if (thisDateTime.equals(lastDateTime)) {
////            int numLength = String.valueOf(lastID, 15, 5).length();
////            for (int i = 0; i < 5-numLength; i++) {
////                sb.append("0");
////            }
//            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
//        } else {
//            sb.append("00000");
//        }       
//        return sb.toString();
return "id";
    }

    @Override
    public String generateID(Store store) {
        StringBuilder sb = new StringBuilder();
        sb.append("STR");
        char[] lastID = dao.getLastIDStore();
        int numLength = String.valueOf(lastID, 3, 7).length();
        for (int i = 0; i < 5-numLength; i++) {
            sb.append("0");
        }
        sb.append(Integer.valueOf(String.valueOf(lastID, 3, 7))+1);
        return sb.toString();
        
    }
    
//    @Override
//    public String generateID(Transaction transaction) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("STK");
//        char[] lastID = dao.getLastID("sale");
//        String thisDateTime = makeTemporalString(transaction.getDate(), transaction.getTime());
//        sb.append(thisDateTime);
//        String lastDateTime = String.valueOf(lastID, 3, 12);
//        if (thisDateTime.equals(lastDateTime)) {
//            int numLength = String.valueOf(lastID, 15, 5).length();
//            for (int i = 0; i < 5-numLength; i++) {
//                sb.append("0");
//            }
//            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
//        } else {
//            sb.append("00000");
//        }       
//        return sb.toString();
//    }
//    
//    @Override
//    public String generateID(Review review) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("RVW");
//        char[] lastID = dao.getLastID("sale");
//        String thisDateTime = makeTemporalString(review.getDate(), review.getTime());
//        sb.append(thisDateTime);
//        String lastDateTime = String.valueOf(lastID, 3, 12);
//        if (thisDateTime.equals(lastDateTime)) {
//            int numLength = String.valueOf(lastID, 15, 5).length();
//            for (int i = 0; i < 5-numLength; i++) {
//                sb.append("0");
//            }
//            sb.append(Integer.valueOf(String.valueOf(lastID, 15, 5))+1);
//        } else {
//            sb.append("00000");
//        }       
//        return sb.toString();
//    }
    
    private String makeTemporalString(LocalDate date, LocalTime time) {
        return date.format(DateTimeFormatter.ofPattern("YYMMDD"))
                + time.format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    
}
